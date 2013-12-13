package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;

public abstract class RacewayMap extends ArcadeMap {

    boolean hasPassedGrace = false;

    public void setStartArea(int x1, int x2, int y1, int y2, int z1, int z2) {
        bx1 = x1;
        bx2 = x2;
        by1 = y1;
        by2 = y2;
        bz1 = z1;
        bz2 = z2;
    }

    public void setFinishArea(int x1, int x2, int y1, int y2, int z1, int z2) {
        fx1 = x1;
        fx2 = x2;
        fy1 = y1;
        fy2 = y2;
        fz1 = z1;
        fz2 = z2;
    }

    public void setFootY(int y) {
        footY = y;
    }

    int footY, bx1, bx2, by1, by2, bz1, bz2, fx1, fx2, fy1, fy2, fz1, fz2;


    @EventHandler
    public void onLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            Bukkit.broadcastMessage(ChatColor.RED + "RACE WILL START IN 10 SECONDS! JUMPING AND LEAVING THE START BOX IS DISALLOWED!");
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    hasPassedGrace = true;
                    Bukkit.broadcastMessage(ChatColor.RED + "GO!");
                }
            }, (10 * 20)); // amount of seconds * 20 ticks
        }
    }

    @EventHandler
    public void moveChecker(final PlayerMoveEvent event) {

        Player p = event.getPlayer();

        if (!p.getWorld().getName().equals(name)) return;

        if (!p.isOnGround()) {
            p.getLocation().setY(footY);
            p.sendMessage(ChatColor.RED + "Oi! No jumping!");
        }

        if (p.getLocation().getBlock().getType() != Material.COAL_BLOCK) {

            p.sendMessage(ChatColor.RED + "You have 3 seconds to get back on the road..");

            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {

                public void run() {

                    if (event.getPlayer().getLocation().getBlock().getType() == Material.COAL_BLOCK) {
                        event.getPlayer().sendMessage(ChatColor.GREEN + "That's better, now stay on the road!");
                    } else {
                        event.getPlayer().setHealth(0);
                        event.getPlayer().sendMessage(ChatColor.RED + "Game over, bud, that's what you get for going off the road!");
                    }

                }

            }, (3 * 20));

        }

        if (!hasPassedGrace && !contains(p.getLocation(), bx1, bx2, by1, by2, bz1, bz2)) {
            p.sendMessage(ChatColor.RED + "You cannot leave this area yet!");
            event.setCancelled(true);
        }

        if (hasPassedGrace && contains(p.getLocation(), fx1, fx2, fy1, fy2, fz1, fz2)) {

            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " WON!!!");

            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {

                    for (Player pl : event.getPlayer().getWorld().getPlayers()) {

                        if (!pl.getName().equals(event.getPlayer().getName())) {

                            pl.setHealth(0);

                        }

                    }

                }
            }, 1L);

        }

    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        hasPassedGrace = false;
    }

}
