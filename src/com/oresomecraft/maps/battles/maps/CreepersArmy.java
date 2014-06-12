package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.MapLoadEvent;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

@MapConfig(
        name = "creepers",
        fullName = "[Classic] Creeper's Army",
        creators = {"__R3"},
        gamemodes = {Gamemode.INFECTION}
)
@Region(
        x1 = 50,
        y1 = 62,
        z1 = -4,
        x2 = 0,
        y2 = 93,
        z2 = -63
)
public class CreepersArmy extends BattleMap implements Listener {

    public CreepersArmy() {
        super.initiate(this);
    }

    @EventHandler
    public void start(MapLoadEvent event) {
        active = true;
        pass = false;
        Bukkit.broadcastMessage(ChatColor.RED + "INVINCIBILITY WEARS OFF IN 30 SECONDS!");
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                if (!active) return;
                pass = true;
                Bukkit.broadcastMessage(ChatColor.RED + "Invincibility has worn off, run into humans to infect them!");
            }
        }, 30 * 20L);
    }

    @EventHandler
    public void end(BattleEndEvent event) {
        active = false;
        pass = false;
    }


    public void readyTDMSpawns() {
        //null
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 26, 64, -25, 353, -2));
        FFASpawns.add(new Location(w, 26, 64, -25, 353, -2));
        FFASpawns.add(new Location(w, 26, 64, -25, 353, -2));
    }

    public void applyInventory(BattlePlayer p) {
        p.sendMessage(ChatColor.GOLD + "Welcome to Creeper's Army!");
        p.sendMessage(ChatColor.GOLD + "This is a map that specialises in " + ChatColor.BOLD + "OresomeInfected classic-style infection!");
        p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "RUN INTO PEOPLE TO INFECT THEM!");
    }

    public boolean active = false;
    public boolean pass = false;

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if (!event.getTo().getWorld().getName().equals(getName()) || !active || !pass) return;
        if (BattlePlayer.getBattlePlayer(event.getPlayer()).getTeamType() == Team.ZOMBIES) {
            for (Entity entity : event.getPlayer().getNearbyEntities(0.7, 0.7, 0.7)) {
                if (entity instanceof Player) {
                    if (BattlePlayer.getBattlePlayer(((Player) entity)).getTeamType() == Team.HUMANS) {
                        ((Player) entity).damage(200);
                        Bukkit.broadcastMessage(event.getPlayer().getDisplayName() + ChatColor.YELLOW + " infected " + ((Player) entity).getDisplayName() + ChatColor.YELLOW + "!");
                    }
                }
            }
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) event.setCancelled(true);
    }

    @EventHandler
    public void place(BlockPlaceEvent event) {
        if (!event.getBlockPlaced().getWorld().getName().equals(getName())) return;
        if (!isInsideRegion(event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void bBreak(BlockBreakEvent event) {
        if (!event.getBlock().getWorld().getName().equals(getName())) return;
        if (!isInsideRegion(event.getBlock().getLocation())) event.setCancelled(true);
    }
}
