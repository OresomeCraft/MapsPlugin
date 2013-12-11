package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
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

    public void setSpawnLocation(int x, int y, int z) {
        bx = x;
        by = y;
        bz = z;
    }

    public void setFinishArea(int x1, int x2, int y1, int y2, int z1, int z2) {
        fx1 = x1;
        fx2 = x2;
        fy1 = y1;
        fy2 = y2;
        fz1 = z1;
        fz2 = z2;
    }

    public void setFootY(int y){
        footY = y;
    }

    int footY;

    int bx;
    int by;
    int bz;

    int bx1;
    int bx2;
    int by1;
    int by2;
    int bz1;
    int bz2;

    int fx1;
    int fx2;
    int fy1;
    int fy2;
    int fz1;
    int fz2;

    @EventHandler
    public void onLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            Bukkit.broadcastMessage(ChatColor.RED + "RACE WILL START IN 10 SECONDS! JUMPING AND LEAVING THE START BOX IS DISALLOWED!");
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    hasPassedGrace = true;
                    Bukkit.broadcastMessage(ChatColor.RED + "GO!");
                }
            }, 200L);
        }
    }

    @EventHandler
    public void moveChecker(PlayerMoveEvent e) {
        if (!e.getPlayer().getWorld().getName().equals(name)) return;
        if (e.getPlayer().getLocation().getY() >= 66) {
            e.getPlayer().getLocation().setY(footY);
            e.getPlayer().sendMessage(ChatColor.RED + "No jumping!");
        }
        if (!hasPassedGrace && !contains(e.getPlayer().getLocation(), bx1, bx2, by1, by2, bz1, bz2)) {
            e.getPlayer().sendMessage(ChatColor.RED + "You cannot leave this area yet!");
            e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), bx, by, bz));
        }
        if (hasPassedGrace && contains(e.getPlayer().getLocation(), fx1, fx2, fy1, fy2, fz1, fz2)) {
            Bukkit.broadcastMessage(ChatColor.RED + e.getPlayer().getName() + " WON!!!");
            for (Player p : e.getPlayer().getWorld().getPlayers()) {
                if (!p.getName().equals(e.getPlayer().getName())) {
                    p.setHealth(0);
                }
            }
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        hasPassedGrace = false;
    }

}
