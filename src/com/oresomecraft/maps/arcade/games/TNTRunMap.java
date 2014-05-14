package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapLoadEvent;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;

public abstract class TNTRunMap extends ArcadeMap {

    boolean hasPassedGrace = false;

    @EventHandler
    public void onLoad(MapLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
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
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
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
