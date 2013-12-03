package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.world.WorldLoadEvent;

public abstract class PaintBallMap extends ArcadeMap {

    boolean hasPassedGrace = false;

    @EventHandler
    public void onLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    hasPassedGrace = true;
                    Bukkit.broadcastMessage(ChatColor.RED + "The grace period has ended! Paintball!");
                }
            }, 200L);
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        hasPassedGrace = false;
    }

    @EventHandler
    public void onEntityThrow(ProjectileLaunchEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Snowball && !hasPassedGrace && event.getEntity().getShooter() instanceof Player) {
                event.setCancelled(true);
                ((Player) event.getEntity().getShooter()).sendMessage(ChatColor.RED + "Grace period has not passed yet!");
            }
        }
    }

    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getDamager() instanceof Snowball && event.getEntity() instanceof Player) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(MapsPlugin.getInstance(), new Runnable() {
                    public void run() {
                        ((Player) event.getEntity()).setHealth(0.0);
                    }
                }, 1L);
            }
        }
    }

}
