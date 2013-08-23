package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Darknessofdusk extends BattleMap implements IBattleMap, Listener {

    public Darknessofdusk() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        lockTime("night");
    }

    String name = "dusk";
    String fullName = "Darkness of Dusk";
    String creators = "R3creat3, xannallax33, dinner1111 and pepsidawg00";
    Gamemode[] modes = {Gamemode.INFECTION};

    //Tdm isn't enabled on this, don't need to do spawns.
    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 62, 6, 23));
        FFASpawns.add(new Location(w, 62, 6, 149));
        FFASpawns.add(new Location(w, -46, 6, 147));
        FFASpawns.add(new Location(w, -48, 6, 44));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

        p.getInventory().setBoots(DIAMOND_BOOTS);
        p.getInventory().setLeggings(GOLD_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 115;
    public int y1 = 57;
    public int z1 = -14;

    //Bottom right corner.
    public int x2 = -88;
    public int y2 = 0;
    public int z2 = 191;

}
