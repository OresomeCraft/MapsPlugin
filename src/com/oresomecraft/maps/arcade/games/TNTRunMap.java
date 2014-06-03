package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.MapLoadEvent;
import com.oresomecraft.maps.MapsPlugin;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public abstract class TNTRunMap extends Map {

    boolean hasPassedGrace = false;

    @EventHandler
    public void onLoad(MapLoadEvent event) {
        if (event.getWorld().getName().equals(getName())) {
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    hasPassedGrace = true;
                }
            }, 100L);
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        hasPassedGrace = false;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(getName())) return;
        if (BattlePlayer.getBattlePlayer(event.getPlayer()).isSpectator()) return;
        final Location loc = event.getPlayer().getLocation();
        if (hasPassedGrace) {
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    new Location(loc.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ()).getBlock().setType(Material.AIR);
                }
            }, 10L);
        }
    }

}
