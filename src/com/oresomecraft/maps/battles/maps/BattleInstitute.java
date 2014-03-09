package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Random;

@MapConfig
public class BattleInstitute extends BattleMap implements IBattleMap, Listener {

    public BattleInstitute() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.COOKED_BEEF, Material.POTION});
    }

    String name = "battleinstitute";
    String fullName = "The Battle Institute";
    String creators = "bishoptaj, microstevey, _Trezo_, __R3 and AnomalousDyna";
    Gamemode[] modes = {Gamemode.LTS};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(Bukkit.getWorld(name), 0.5, 65, 13.5, (float) 359.434, (float) 0.027));
        blueSpawns.add(new Location(Bukkit.getWorld(name), 0.5, 65, -17.5, (float) 180.056, (float) 2.057));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(Bukkit.getWorld(name), 0.5, 65, 13.5, (float) 359.434, (float) 0.027));
        FFASpawns.add(new Location(Bukkit.getWorld(name), 0.5, 65, -17.5, (float) 180.056, (float) 2.057));
    }

    public void applyInventory(final BattlePlayer p) {
        p.sendMessage(ChatColor.GOLD + "" + ChatColor.GOLD + "Wait until you are called and then punch the block!");
    }

    ArrayList<String> red = new ArrayList<String>();
    ArrayList<String> blue = new ArrayList<String>();

    String currentRed = "None";
    String currentBlue = "None";

    boolean blueEnter = false;
    boolean redEnter = false;

    @EventHandler
    public void end(BattleEndEvent e) {
        blueEnter = false;
        redEnter = false;
        currentBlue = "None";
        currentRed = "None";
        red.clear();
        blue.clear();
    }

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        if (!e.getPlayer().getWorld().getName().equals(name)) return;
        if (red.contains(e.getPlayer().getName()) || blue.contains(e.getPlayer().getName()))
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] A player has left!");
        red.remove(e.getPlayer().getName());
        blue.remove(e.getPlayer().getName());
        if (currentRed.equals(e.getPlayer().getName()) || currentBlue.equals(e.getPlayer().getName())) endRound();
    }

    @EventHandler
    public void death(PlayerDeathEvent e) {
        if (!e.getEntity().getWorld().getName().equals(name)) return;
        red.remove(e.getEntity().getName());
        blue.remove(e.getEntity().getName());
        if (red.contains(e.getEntity().getName()) || blue.contains(e.getEntity().getName()))
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] A player has quit!");
        if (currentRed.equals(e.getEntity().getName()) || currentBlue.equals(e.getEntity().getName())) endRound();
        e.getEntity().setHealth(20);
    }

    @EventHandler
    public void worldLoad(WorldLoadEvent e) {
        if (e.getWorld().getName().equalsIgnoreCase(name)) {
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] Starting up!");
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        try {
                            if (BattlePlayer.getBattlePlayer(p.getName()).getTeamType() == Team.LTS_RED) {
                                red.add(p.getName());
                            }
                            if (BattlePlayer.getBattlePlayer(p.getName()).getTeamType() == Team.LTS_BLUE) {
                                blue.add(p.getName());
                            }
                        } catch (Exception ex) {
                            // Really bad ugly and inefficient temp fix
                        }
                    }
                    newRound();
                }
            }, 80L);
        }
    }

    @EventHandler
    public void leavespec(PlayerCommandPreprocessEvent e) {
        if (!e.getPlayer().getWorld().getName().equals(name)) return;
        if (e.getMessage().toLowerCase().startsWith("/leave")) {
            if (red.contains(e.getPlayer().getName()) || blue.contains(e.getPlayer().getName()))
                Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] A player has left!");
            red.remove(e.getPlayer().getName());
            blue.remove(e.getPlayer().getName());
            if (currentRed.equals(e.getPlayer().getName()) || currentBlue.equals(e.getPlayer().getName())) endRound();
        }
        if (e.getMessage().toLowerCase().startsWith("/spectate")) {
            if (red.contains(e.getPlayer().getName()) || blue.contains(e.getPlayer().getName()))
                Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] A player has left!");
            red.remove(e.getPlayer().getName());
            blue.remove(e.getPlayer().getName());
            if (currentRed.equals(e.getPlayer().getName()) || currentBlue.equals(e.getPlayer().getName())) endRound();
        }
    }

    @EventHandler
    public void ironblock(PlayerInteractEvent e) {
        if (!e.getPlayer().getWorld().getName().equals(name)) return;
        try {
            if (e.getClickedBlock().getType().equals(Material.IRON_BLOCK)) {
                join(e.getPlayer().getName());
            }
        } catch (NullPointerException ex) {

        }
    }

    public void newRound() {
        String newRed = red.get(new Random().nextInt(red.size()));
        String newBlue = blue.get(new Random().nextInt(blue.size()));
        redEnter = false;
        blueEnter = false;
        Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] The following players have 5 seconds to click the iron block!");
        Bukkit.broadcastMessage(ChatColor.RED + newRed);
        Bukkit.broadcastMessage(ChatColor.BLUE + newBlue);
        currentBlue = newBlue;
        currentRed = newRed;
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                if (!blueEnter) join(currentBlue);
                if (!redEnter) join(currentRed);
            }
        }, 5 * 20L);
    }

    private void endRound() {
        Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] Round over! Picking new players!");
        try {
            Bukkit.getPlayer(currentBlue).teleport(new Location(Bukkit.getWorld(name), 0.5, 65, -17.5, (float) 180.056, (float) 2.057));
        } catch (NullPointerException ex) {

        }
        try {
            Bukkit.getPlayer(currentRed).teleport(new Location(Bukkit.getWorld(name), 0.5, 65, 13.5, (float) 359.434, (float) 0.027));
        } catch (NullPointerException ex) {

        }
        if (red.size() == 0 || blue.size() == 0) {
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] GAME OVER!");
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getInventory().clear();
            p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
            p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
            p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
            p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            p.updateInventory();
        }
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                newRound();
            }
        }, 40L);
    }

    private void join(String player) {
        if (currentBlue.equals(player) && !blueEnter) {
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] " + player + " joins!");
            blueEnter = true;
            Player p = Bukkit.getPlayer(currentBlue);
            p.teleport(new Location(Bukkit.getWorld(name), 0.5, 65, -11.5, 180, (float) -0.108));
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            handKit(p);
        }
        if (currentRed.equals(player) && !redEnter) {
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] " + player + " joins!");
            redEnter = true;
            Player p = Bukkit.getPlayer(currentRed);
            p.teleport(new Location(Bukkit.getWorld(name), 0.5, 65, 7.5, (float) 0.652, (float) -5.384));
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            handKit(p);
        }
    }

    private void handKit(Player p) {
        Inventory i = p.getInventory();
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 12);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
        p.updateInventory();
    }
}
