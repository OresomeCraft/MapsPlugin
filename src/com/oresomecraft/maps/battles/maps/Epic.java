package com.oresomecraft.maps.battles.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.maps.*;
import com.oresomecraft.maps.battles.*;
import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Epic extends BattleMap implements IBattleMap, Listener {

    public Epic() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setTDMTime(10);
        disableDrops(new Material[]{Material.LEATHER_HELMET});
    }

    // Map details
    String name = "ep1c";
    String fullName = "Bridge";
    String creators = "fkbear and ep1cn00bt00b";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -14, 66, -193, 0, 0));
        blueSpawns.add(new Location(w, -17, 66, -2, 180, 0));

        setKoTHMonument(new Location(w, -16, 67, -106));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -14, 66, -193, 0, 0));
        FFASpawns.add(new Location(w, -17, 66, -2, 180, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack EXP_BOTTLE = new ItemStack(Material.EXP_BOTTLE, 3);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_LEGGINGS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        // setItem() is a BattlePlayer method. Makes giving items a bit quicker.
        i.setItem(0, WOOD_SWORD);
        i.setItem(1, GOLDEN_APPLE);
        i.setItem(2, STEAK);
        i.setItem(3, EXP_BOTTLE);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -67;
    public int y1 = 40;
    public int z1 = 15;

    // Bottom right corner.
    public int x2 = 36;
    public int y2 = 101;
    public int z2 = -223;

}