package com.oresomecraft.maps.battles.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.maps.*;
import com.oresomecraft.maps.battles.*;
import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class BattleTemplate extends BattleMap implements Listener {

    public BattleTemplate() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "template";
    String fullName = "Template";
    String[] creators = {"Contributor1, Contributor2 and Contributor3";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION, Gamemode.CTF, Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 0, 99, 27, 2, 0));
        redSpawns.add(new Location(w, -9, 110, -20, 0, 0));
        redSpawns.add(new Location(w, 21, 105, -13, 0, 0));
        redSpawns.add(new Location(w, 4, 106, -41, 0, 0));

        blueSpawns.add(new Location(w, -9, 110, -20, 0, 0));
        blueSpawns.add(new Location(w, 0, 99, 27, 0, 0));
        blueSpawns.add(new Location(w, -16, 108, -3, 0, 0));
        blueSpawns.add(new Location(w, -30, 108, -3, 0, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 99, 27, 2, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        // setItem() is a BattlePlayer method. Makes giving items a bit quicker.
        p.setItem(0, IRON_SWORD);
        p.setItem(1, Material.BOW, 1);
        p.setItem(2, Material.COOKED_BEEF, 1);

        // This is the Bukkit way of doing it
        i.setItem(3, HEALTH_POTION);
        p.setItem(8, Material.EXP_BOTTLE, 5);
        p.setItem(9, Material.ARROW, 64);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    // Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

}