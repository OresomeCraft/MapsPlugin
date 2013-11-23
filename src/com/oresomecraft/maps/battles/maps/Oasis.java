package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Oasis extends BattleMap implements IBattleMap, Listener {

    public Oasis() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "ballons";
    String fullName = "Oasis";
    String creators = "_Moist and Sky_Aurora";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -107, 55, -44, -45, 0);
        Location blueSpawn = new Location(w, -0.3, 55, -0, 135, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -107, 55, -0, -135, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -0.3, 55, -44, 45, 0));

        setKoTHMonument(new Location(w, -53, 54, -22));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -39, 53, -4, 121, 0);
        Location blueSpawn = new Location(w, -105, 55, -22, -90, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 6);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack GAPPLE = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack SANDSTONE = new ItemStack(Material.SANDSTONE, 32);
        ItemStack LILYPAD = new ItemStack(Material.WATER_LILY, 1);

        LEATHER_CHESTPLATE.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        LEATHER_BOOTS.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, GAPPLE);
        i.setItem(3, IRON_PICKAXE);
        i.setItem(4, BREAD);
        i.setItem(5, HEALTH_POTION);
        i.setItem(6, SANDSTONE);
        i.setItem(7, LILYPAD);
        i.setItem(10, ARROWS);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_BOOTS, LEATHER_PANTS, LEATHER_HELMET});

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 1));
        p.sendMessage(ChatColor.GOLD + "Stand on wheat to gain health!");
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 10;
    public int y1 = 87;
    public int z1 = 13;

    //Bottom right corner.
    public int x2 = -122;
    public int y2 = 34;
    public int z2 = -60;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (p.getWorld().getName().equals(name)) {
            if (event.getTo().getBlock().getType().equals(Material.CROPS)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 3 * 20, 1));
            }
        }

    }
}