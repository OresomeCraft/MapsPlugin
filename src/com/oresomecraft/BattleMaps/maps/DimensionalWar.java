package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class DimensionalWar extends BattleMap implements IBattleMap, Listener {

    public DimensionalWar() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "dimensional";
    String fullName = "Dimensional War";
    String creators = "ninsai";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.CTF};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -11, 150, 72, 179, 0);
        Location blueSpawn = new Location(w, -1610, 55, 50, 0, 0); // what are the last 2 paramaters? :_:
        // location is new Location(world,x,y,z); no?

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

        Location blueFlag = new Location(w, -1610, 56, -263);
        Location redFlag = new Location(w, -1617, 56, -363);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -11, 150, 72, 179, 0);
        Location blueSpawn = new Location(w, -1610, 55, 50, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);

        setFFASpawns(name, FFASpawns);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, ARROWS);
        i.setItem(5, EXP);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -1631;
    public int y1 = 55;
    public int z1 = -369;

    //Bottom right corner.
    public int x2 = -1592;
    public int y2 = 55;
    public int z2 = -255;

}
