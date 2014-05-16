package com.oresomecraft.maps;

import com.oresomecraft.OresomeBattles.api.BattlesAccess;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class InternalListener implements Listener {

    public InternalListener() {
        MapsPlugin.getInstance().getServer().getPluginManager().registerEvents(this, MapsPlugin.getInstance());
    }

    @EventHandler(priority = EventPriority.LOWEST) // load map
    public void onWorldLoad(MapLoadEvent event) { // Internal - Do not change
        if (MapsPlugin.getMaps().containsKey(event.getWorld().getName())) {
            Map map = MapsPlugin.getMaps().get(event.getWorld().getName());
            Bukkit.getPluginManager().registerEvents(map, MapsPlugin.getInstance());
            map.load(event);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBattleEnd(BattleEndEvent event) {
        HandlerList.unregisterAll(MapsPlugin.getMaps().get(BattlesAccess.getArena()));
    }

}
