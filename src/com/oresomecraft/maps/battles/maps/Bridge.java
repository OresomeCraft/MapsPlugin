package com.oresomecraft.maps.battles.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.maps.*;
import com.oresomecraft.maps.battles.*;
import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Bridge extends BattleMap implements Listener {

    public Bridge() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setTDMTime(15);
        disableDrops(new Material[]{Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_CHESTPLATE, Material.LEATHER_HELMET, Material.BOW});
        setAutoSpawnProtection(15);
    }

    // Map details
    String name = "bridge";
    String fullName = "Bridge";
    String[] creators = {"fkBear", "ep1cn00bt00b"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.KOTH, Gamemode.CTF};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -4, 64, 8, 317.4F, 14F));
        blueSpawns.add(new Location(w, -27, 64, -218, 144.2F, 1.1F));

        setKoTHMonument(new Location(w, -15, 67, -105));
        setCTFFlags(name, new Location(w, -16, 57, -52), new Location(w, -15, 67, -158));

    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -14, 66, -193, 0, 0));
        FFASpawns.add(new Location(w, -17, 66, -2, 180, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack EXP_BOTTLE = new ItemStack(Material.EXP_BOTTLE, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 16);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_LEGGINGS, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_LEGGINGS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        // setItem() is a BattlePlayer method. Makes giving items a bit quicker.
        i.setItem(0, STONE_SWORD);
        i.setItem(2, GOLDEN_APPLE);
        i.setItem(1, BOW);
        i.setItem(9, ARROWS);
        i.setItem(3, STEAK);
        i.setItem(4, EXP_BOTTLE);
    }

    public int x1 = 45;
    public int y1 = 5;
    public int z1 = 35;

    public int x2 = -99;
    public int y2 = 127;
    public int z2 = -236;
}
