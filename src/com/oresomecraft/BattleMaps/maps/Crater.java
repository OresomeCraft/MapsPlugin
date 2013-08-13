package com.oresomecraft.BattleMaps.maps;

import java.util.*;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.entity.*;
import org.bukkit.enchantments.*;
import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.api.*;
import com.oresomecraft.OresomeBattles.*;
import com.oresomecraft.OresomeBattles.events.*;

public class Crater extends BattleMap implements IBattleMap, Listener {

    public Crater() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "crater";
    String fullName = "Crater Site";
    String creators = "R3creat3, psgs and Spantezian";
    Gamemode[] modes = {Gamemode.CTF, Gamemode.FFA};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -55, 81, 57);
        Location blueSpawn = new Location(w, -14, 81, -53);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
        Location redFlag = new Location(w, -14, 84, 46);
        Location blueFlag = new Location(w, -57, 84, -43);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -55, 81, 57);
        Location blueSpawn = new Location(w, -14, 81, -53);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -34, 79, 18));
        FFASpawns.add(new Location(w, -33, 80, -22));
        FFASpawns.add(new Location(w, -5, 79, 23));
        FFASpawns.add(new Location(w, -60, 79, -16));
        FFASpawns.add(new Location(w, 0, 78, -25));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
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
        }
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
