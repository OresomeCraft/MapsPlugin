package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Granted extends BattleMap implements Listener {

    public Granted() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.IRON_SWORD, Material.BOW, Material.ARROW});
        lockTime("day");
    }

    String name = "raidii";
    String fullName = "Granted";
    String[] creators = {"FaazM, Turt1eManLol"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.KOTH};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 3, 74, 98, -119, 0);
        Location blueSpawn = new Location(w, 61, 74, -121, 60, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, 49, 71, 80, 179, 0));
        blueSpawns.add(new Location(w, 15, 71, -102, -1, 0));

        setKoTHMonument(new Location(w, 31, 71, -10));

    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 3, 74, 98, -119, 0);
        Location blueSpawn = new Location(w, 61, 74, -121, 60, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 49, 71, 80, 179, 0));
        FFASpawns.add(new Location(w, 15, 71, -102, -1, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH);
        i.setItem(4, STEAK);
        i.setItem(10, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -12;
    public int y1 = 126;
    public int z1 = -148;

    //Bottom right corner.
    public int x2 = 82;
    public int y2 = 6;
    public int z2 = 129;

}
