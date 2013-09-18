package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Crater extends BattleMap implements IBattleMap, Listener {

    public Crater() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
        lockTime("night");
        setTDMTime(10);
    }

    String name = "crater";
    String fullName = "Crater Site";
    String creators = "R3creat3, psgs and Spantezian";
    Gamemode[] modes = {Gamemode.CTF, Gamemode.TDM};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -55, 81, 57);
        Location blueSpawn = new Location(w, -14, 81, -53);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, -14, 84, 46);
        Location blueFlag = new Location(w, -57, 84, -43);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -55, 81, 57);
        Location blueSpawn = new Location(w, -14, 81, -53);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -34, 79, 18));
        FFASpawns.add(new Location(w, -33, 80, -22));
        FFASpawns.add(new Location(w, -5, 79, 23));
        FFASpawns.add(new Location(w, -60, 79, -16));
        FFASpawns.add(new Location(w, 0, 78, -25));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack S = new ItemStack(Material.STICK, 1);
        ItemStack I = new ItemStack(Material.IRON_INGOT, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        LEATHER_HELMET.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(10, ARROWS);
        i.setItem(11, S);
        i.setItem(12, I);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -207;
    public int y1 = 52;
    public int z1 = -1220;

    //Bottom right corner.
    public int x2 = -38;
    public int y2 = 112;
    public int z2 = -1125;

}
