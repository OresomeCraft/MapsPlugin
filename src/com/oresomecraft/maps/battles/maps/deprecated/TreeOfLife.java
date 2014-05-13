package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.OresomeBattles.api.*;

import java.util.Random;

@MapConfig
public class TreeOfLife extends BattleMap implements Listener {

    public TreeOfLife() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
        setAllowBuild(false);
        setAutoSpawnProtection(2);
    }

    String name = "treeoflife";
    String fullName = "Tree of Life";
    String[] creators = {"Corrigan1998"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 14, 80, 40, -134, 0);
        Location blueSpawn = new Location(w, 109, 81, -64, 45, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

    }

    public void readyFFASpawns() {

        FFASpawns.add(new Location(w, 115, 79, -39, 67, 0));
        FFASpawns.add(new Location(w, 112, 79, -15, 113, 0));
        FFASpawns.add(new Location(w, 116, 80, 2, 98, 0));
        FFASpawns.add(new Location(w, 115, 83, 31, 101, 0));
        FFASpawns.add(new Location(w, 86, 81, 45, 135, 0));
        FFASpawns.add(new Location(w, 44, 79, 52, -179, 0));
        FFASpawns.add(new Location(w, 1, 78, -7, -89, 0));
        FFASpawns.add(new Location(w, -0, 77, -32, -101, 0));
        FFASpawns.add(new Location(w, 30, 78, -75, 14, 0));
        FFASpawns.add(new Location(w, 75, 80, -73, 24, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack STICK = new ItemStack(Material.STICK, 1);

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);

        ItemMeta stick = STICK.getItemMeta();
        stick.setDisplayName(ChatColor.GREEN + "Stick of Life");
        STICK.setItemMeta(stick);

        ItemMeta potion = HEALTH_POTION.getItemMeta();
        potion.setDisplayName(ChatColor.GREEN + "Potion of Life");
        HEALTH_POTION.setItemMeta(potion);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH_POTION);
        i.setItem(3, STEAK);
        i.setItem(4, STICK);
        i.setItem(14, ARROWS);

    }

    // Top Left
    public int x1 = 181;
    public int y1 = 224;
    public int z1 = -138;
    // Bottom Right
    public int x2 = -40;
    public int y2 = 25;
    public int z2 = 111;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase(name)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                Player player = event.getPlayer();
                if (player.getItemInHand().getType().equals(Material.STICK)) {
                    ItemStack item = player.getItemInHand();
                    item.setAmount(1);
                    player.getInventory().removeItem(item);
                    Random random = new Random();
                    // 2/5 Chance
                    if (random.nextInt((10 - 1) + 1) > 5) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));
                        player.sendMessage(ChatColor.GREEN + "The stick of life has given you health!");
                    } else {
                        player.getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.SKELETON);
                        player.getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.SPIDER);
                        player.getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.ZOMBIE);
                        player.sendMessage(ChatColor.GREEN + "The stick of life has forsaken you!");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) {
            event.setCancelled(true);
        }
    }

}
