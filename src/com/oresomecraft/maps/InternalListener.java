package com.oresomecraft.maps;

import com.oresomecraft.OresomeBattles.api.BattlesAccess;
import com.oresomecraft.OresomeBattles.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class InternalListener implements Listener {

    private Map lastMapPlayed;

    public InternalListener() {
        MapsPlugin.getInstance().getServer().getPluginManager().registerEvents(this, MapsPlugin.getInstance());
    }

    @EventHandler(priority = EventPriority.LOWEST) // load map
    public void onWorldLoad(WorldLoadEvent event) { // Internal - Do not change
        if (MapsPlugin.getMaps().containsKey(event.getWorld().getName())) {
            Map map = MapsPlugin.getMaps().get(event.getWorld().getName());
            Bukkit.getPluginManager().registerEvents(map, MapsPlugin.getInstance());
            MapLoadEvent mapLoadEvent = new MapLoadEvent(event.getWorld().getName(), event.getWorld());
            map.load(mapLoadEvent);
            Bukkit.getPluginManager().callEvent(mapLoadEvent);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBattleEnd(BattleEndEvent event) {
        HandlerList.unregisterAll(MapsPlugin.getMaps().get(BattlesAccess.getArena()));
        lastMapPlayed = MapsPlugin.getMaps().get(BattlesAccess.getArena());
    }

    @EventHandler
    public void clearSpawns(ClearSpawnsEvent event) {
        if (lastMapPlayed != null) lastMapPlayed.clearSpawns();
    }

}
