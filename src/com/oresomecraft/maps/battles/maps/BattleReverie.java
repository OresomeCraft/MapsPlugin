package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.events.EndBattleEvent;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.MapLoadEvent;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.maps.MapsPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Random;

@MapConfig(
        name = "battlereverie",
        fullName = "The Battle Reverie",
        creators = {"Heartist"},
        gamemodes = {Gamemode.FFA}
)
@Attributes(
        allowBuild = false,
        requiredFFAScore = 13,
        disabledDrops = {Material.POTION, Material.COOKED_BEEF, Material.BOW, Material.ARROW, Material.CHAINMAIL_HELMET, Material.GOLD_CHESTPLATE, Material.IRON_LEGGINGS, Material.DIAMOND_BOOTS, Material.IRON_SWORD}
)
public class BattleReverie extends BattleMap implements Listener {

    public boolean ac = false;
    public boolean acc = false;
    String p1 = "???";
    String p2 = "???";
    String p3 = "???";
    String p4 = "???";
    String np1 = "???";
    String np2 = "???";
    String np3 = "???";
    String np4 = "???";

    public BattleReverie() {
        super.initiate(this);
    }

    @EventHandler
    public void end(EndBattleEvent event) {
        p1 = "???";
        p2 = "???";
        p3 = "???";
        p4 = "???";
        np1 = "???";
        np2 = "???";
        np3 = "???";
        np4 = "???";
        ac = false;
        acc = true;
    }

    public void readyTDMSpawns() {
        //null
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 87, 0, 87.998F, 7.1F));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        pl.sendMessage(ChatColor.GOLD + "Welcome to The Battle Reverie!");
        pl.sendMessage(ChatColor.GOLD + "This is an arena that specialises in " + ChatColor.BOLD + "quadruple brawling!");
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(getName())) return;
        if (isChosen(event.getPlayer().getName())) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] " + event.getPlayer().getName() + " quit!");
            endOrGo(event.getPlayer().getName());
        }
    }

    @EventHandler
    public void death(PlayerDeathEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        if (isChosen(event.getEntity().getName()))
            endOrGo(event.getEntity().getName());
    }

    @EventHandler
    public void worldLoad(MapLoadEvent event) {
        if (event.getWorld().getName().equalsIgnoreCase(getName())) {
            ac = true;
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] Starting up!");
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    newRound();
                }
            }, 80L);
        }
    }

    @EventHandler
    public void leaveSpec(final PlayerCommandPreprocessEvent event) {
        // This is the blocked stuff
        if (!event.getPlayer().getWorld().getName().equals(getName())) return;
        if (event.getMessage().toLowerCase().startsWith("/potion") || event.getMessage().toLowerCase().startsWith("/dog"))
            event.setCancelled(true);
        if (event.getMessage().toLowerCase().startsWith("/leave") || event.getMessage().toLowerCase().startsWith("/spectate")) {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                Bukkit.broadcastMessage(ChatColor.RED + event.getPlayer().getName() + " left the round!!");
                if (isChosen(event.getPlayer().getName()))
                    endOrGo(event.getPlayer().getName());
            }
        }
    }

    public void newRound() {
        acc = true;
        np1 = "???";
        np2 = "???";
        np3 = "???";
        np4 = "???";
        if (getInGame().size() <= 3) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] Not enough players! :(");
            MapsPlugin.getInstance().getServer().getPluginManager().callEvent(new EndBattleEvent(Gamemode.FFA));
            return;
        }
        if (getInGame().size() > 0)
            np1 = getRandom(1);
        if (getInGame().size() > 1)
            np2 = getRandom(2);
        if (getInGame().size() > 2)
            np3 = getRandom(3);
        if (getInGame().size() > 3)
            np4 = getRandom(4);
        if (!ac) return;
        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] " + ChatColor.GOLD + "The following players have been chosen!");
        if (!np1.equals("???")) Bukkit.broadcastMessage(ChatColor.RED + np1);
        if (!np2.equals("???")) Bukkit.broadcastMessage(ChatColor.BLUE + np2);
        if (!np3.equals("???")) Bukkit.broadcastMessage(ChatColor.GREEN + np3);
        if (!np4.equals("???")) Bukkit.broadcastMessage(ChatColor.YELLOW + np4);
        p1 = np1;
        p2 = np2;
        p3 = np3;
        p4 = np4;
        Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
            public void run() {
                if (!ac) return;
                if (!p1.equals("???")) join(p1);
                if (!p2.equals("???")) join(p2);
                if (!p3.equals("???")) join(p3);
                if (!p4.equals("???")) join(p4);
                acc = false;
            }
        }, 5 * 20L);
    }

    private void endRound() {
        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] " + ChatColor.GOLD + "Round over!");
        try {
            if (!p1.equals("???"))
                Bukkit.getPlayer(p1).teleport(new Location(Bukkit.getWorld(getName()), 0, 87, 0, 87.998F, 7.1F));
            if (!p2.equals("???"))
                Bukkit.getPlayer(p2).teleport(new Location(Bukkit.getWorld(getName()), 0, 87, 0, 87.998F, 7.1F));
            if (!p3.equals("???"))
                Bukkit.getPlayer(p3).teleport(new Location(Bukkit.getWorld(getName()), 0, 87, 0, 87.998F, 7.1F));
            if (!p4.equals("???"))
                Bukkit.getPlayer(p4).teleport(new Location(Bukkit.getWorld(getName()), 0, 87, 0, 87.998F, 7.1F));
        } catch (NullPointerException ex) {
        }
        p1 = "???";
        p2 = "???";
        p3 = "???";
        p4 = "???";
        for (Player p : getInGame()) {
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
        Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
            public void run() {
                newRound();
            }
        }, 40L);
    }

    private void endOrGo(String whoDied) {
        if (whoDied.equals(p1)) {
            p1 = "???";
        } else if (whoDied.equals(p2)) {
            p2 = "???";
        } else if (whoDied.equals(p3)) {
            p3 = "???";
        } else if (whoDied.equals(p4)) {
            p4 = "???";
        }
        if (getPeopleIn() <= 1) endRound();
        try {
            Bukkit.getPlayer(whoDied).setHealth(20);
            Bukkit.getPlayer(whoDied).teleport(new Location(Bukkit.getWorld(getName()), 0, 87, 0, 87.998F, 7.1F));
        } catch (Exception ex) {

        }
    }

    private void join(String player) {
        if (p1.equals(player)) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] " + ChatColor.GOLD + player + " joins!");
            Player p = Bukkit.getPlayer(p1);
            p.teleport(new Location(Bukkit.getWorld(getName()), 14, 72, 0, 269.9F, 0.8F));
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            handKit(p);
        } else if (p2.equals(player)) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] " + ChatColor.GOLD + player + " joins!");
            Player p = Bukkit.getPlayer(p2);
            p.teleport(new Location(Bukkit.getWorld(getName()), 0, 72, 14, 0.7F, 0.1F));
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            handKit(p);
        } else if (p3.equals(player)) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] " + ChatColor.GOLD + player + " joins!");
            Player p = Bukkit.getPlayer(p3);
            p.teleport(new Location(Bukkit.getWorld(getName()), -14, 72, 0, 89.7F, 0.1F));
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            handKit(p);
        } else if (p4.equals(player)) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[BattleReverie] " + ChatColor.GOLD + player + " joins!");
            Player p = Bukkit.getPlayer(p4);
            p.teleport(new Location(Bukkit.getWorld(getName()), 0, 72, -14, 178.6F, 2.3F));
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
        ItemStack IRON_HELMET = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
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

    @EventHandler
    public void foodChange(FoodLevelChangeEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        event.setFoodLevel(20);
    }

    private boolean isChosen(String s) {
        if (p1.equals(s)) return true;
        if (p2.equals(s)) return true;
        if (p3.equals(s)) return true;
        if (p4.equals(s)) return true;
        return false;
    }

    private int getPeopleIn() {
        int count = 0;
        if (!p1.equals("???")) count++;
        if (!p2.equals("???")) count++;
        if (!p3.equals("???")) count++;
        if (!p4.equals("???")) count++;
        return count;
    }

    public ArrayList<Player> getInGame() {
        ArrayList<Player> inGame = new ArrayList<Player>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getGameMode() == GameMode.SURVIVAL) inGame.add(p);
        }
        return inGame;
    }

    public String getRandom(int count) {
        String random = getInGame().get(new Random().nextInt(getInGame().size())).getName();
        if (count == 1) return random;
        if (count == 2)
            if (np1.equals(random)) {
                return getRandom(count);
            } else {
                return random;
            }
        if (count == 3)
            if (np1.equals(random) || np2.equals(random)) {
                return getRandom(count);
            } else {
                return random;
            }
        if (count == 4)
            if (np1.equals(random) || np2.equals(random) || np3.equals(random)) {
                return getRandom(count);
            } else {
                return random;
            }
        return "???";
    }

    @EventHandler(ignoreCancelled = true)
    public void damage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (!p1.equals(p.getName()) && !p2.equals(p.getName()) && !p3.equals(p.getName()) && !p4.equals(p.getName())) {
                event.setCancelled(true);
                ((Player) event.getDamager()).sendMessage(ChatColor.RED + "Please wait until you are selected before fighting!");
            } else if (acc) {
                event.setCancelled(true);
                ((Player) event.getDamager()).sendMessage(ChatColor.RED + "Please wait until you are selected before fighting!");
            }
        }
    }
}
