package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.MapLoadEvent;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

@MapConfig
public class CreepersArmy extends BattleMap implements Listener {

    public CreepersArmy() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(Material.values());
    }

    String name = "creepers";
    String fullName = "[Classic] Creeper's Army";
    String[] creators = {"__R3"};
    Gamemode[] modes = {Gamemode.INFECTION};

    @EventHandler
    public void load(MapLoadEvent event) {
        active = true;
        pass = false;
        Bukkit.broadcastMessage(ChatColor.RED + "INVINCIBILITY WEARS OFF IN 30 SECONDS!");
        new BukkitRunnable() {
            public void run() {
                if (!active) return;
                pass = true;
                Bukkit.broadcastMessage(ChatColor.RED + "Invincibility has worn off, run into humans to infect them!");
            }
        }.runTaskLater(plugin, 30 * 20L);
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
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        p.sendMessage(ChatColor.GOLD + "Welcome to Creeper's Army!");
        p.sendMessage(ChatColor.GOLD + "This is a map that specialises in " + ChatColor.BOLD + "OresomeInfected classic-style infection!");
        p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "RUN INTO PEOPLE TO INFECT THEM!");
    }

    public boolean active = false;
    public boolean pass = false;

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 50;
    public int y1 = 62;
    public int z1 = -4;

    //Bottom right corner.
    public int x2 = 0;
    public int y2 = 93;
    public int z2 = -63;

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if (!event.getTo().getWorld().getName().equals(name) || !active || !pass) return;
        try {
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
        } catch (Exception ignored) {
            //Lol getTeam is a fail.
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) event.setCancelled(true);
    }
}
