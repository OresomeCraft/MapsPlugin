package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.MapLoadEvent;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.maps.arcade.games.RacewayMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

@MapConfig(
        name = "raceway_alpha",
        fullName = "Raceway (Alpha)",
        creators = {"Evil_Emo", "Turt1eManLol"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false,
        allowPhysicalPlayerDamage = false
)
public class Raceway_Alpha extends RacewayMap implements Listener {

    boolean hasPassedGrace = false;
    ArrayList<String> checker = new ArrayList<String>();

    public Raceway_Alpha() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -18, 65, -7));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, STEAK);
    }

    @EventHandler
    public void onLoad(MapLoadEvent event) {
        if (event.getWorld().getName().equals(getName())) {
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.teleport(new Location(Bukkit.getWorld(getName()), -18, 65, -7));
                    }
                    Bukkit.broadcastMessage(ChatColor.RED + "Race is starting! Don't leave the start area!");
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        public void run() {
                            hasPassedGrace = true;
                            Bukkit.broadcastMessage(ChatColor.RED + "GO!");
                        }
                    }, (10 * 20)); // amount of seconds * 20 ticks
                }
            }, 20L);
        }

    }

    @EventHandler
    public void moveChecker(final PlayerMoveEvent event) {
        final Player p = event.getPlayer();
        if (!p.getWorld().getName().equals(getName())) return;
        if (contains(p.getLocation(), -17, -15, 81, 59, -27, 5)) {
            p.sendMessage(ChatColor.RED + "You can't go here!!");
            p.teleport(new Location(Bukkit.getWorld(getName()), -18, 65, -7));
        }
        if (!hasPassedGrace && !contains(p.getLocation(), -17, -24, 85, 54, -21, 8)) {
            p.sendMessage(ChatColor.RED + "You cannot leave this area yet!");
            p.teleport(new Location(Bukkit.getWorld(getName()), -18, 65, -7));
        }
        if (hasPassedGrace && contains(p.getLocation(), -12, -15, 72, 64, 1, -15)) {
            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has won the race!");
            Bukkit.getPluginManager().callEvent(new BattleEndEvent(getMode()));
        }
        if (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.COAL_BLOCK &&
                event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() != Material.QUARTZ_BLOCK &&
                event.getPlayer().getLocation().subtract(0, 2, 0).getBlock().getType() != Material.COAL_BLOCK &&
                event.getPlayer().getLocation().subtract(0, 2, 0).getBlock().getType() != Material.QUARTZ_BLOCK) {
            if (!hasPassedGrace) return;
            if (!checker.contains(p.getName())) {
                p.sendMessage(ChatColor.RED + "You have 1 second to get back on the road..");
                checker.add(p.getName());
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    public void run() {
                        if (event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.COAL_BLOCK ||
                                event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.QUARTZ_BLOCK ||
                                event.getPlayer().getLocation().subtract(0, 2, 0).getBlock().getType() == Material.COAL_BLOCK ||
                                event.getPlayer().getLocation().subtract(0, 2, 0).getBlock().getType() == Material.QUARTZ_BLOCK) {
                            event.getPlayer().sendMessage(ChatColor.GREEN + "That's better, now stay on the road!");
                        } else {
                            event.getPlayer().setHealth(0);
                            event.getPlayer().sendMessage(ChatColor.RED + "Game over bud, that's what you get for going off the road!");
                            BattlePlayer.getBattlePlayer(event.getPlayer()).spectate();
                        }
                        checker.remove(p.getName());
                    }
                }, (20));
            }
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        hasPassedGrace = false;
        checker.clear();
    }
}
