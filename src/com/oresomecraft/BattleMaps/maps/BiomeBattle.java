package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class BiomeBattle extends BattleMap implements IBattleMap, Listener {

    public BiomeBattle() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_HELMET});
        setAllowBuild(false);
    }

    // Map details
    String name = "biomebattle";
    String fullName = "BiomeBattle";
    String creators = "SuperDuckFace, Evil_Emo and Yuzko";
    Gamemode[] modes = {Gamemode.FFA, Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -61, 73, -54));
        blueSpawns.add(new Location(w, 48, 72, 42));

        setKoTHMonument(new Location(w, 0, 67, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -61, 73, -54));
        FFASpawns.add(new Location(w, 48, 72, 42));
        FFASpawns.add(new Location(w, -69, 72, 63));
        FFASpawns.add(new Location(w, -64, 70, 12));
        FFASpawns.add(new Location(w, -51, 87, 10));
        FFASpawns.add(new Location(w, -47, 80, -41));
        FFASpawns.add(new Location(w, -17, 70, -84));
        FFASpawns.add(new Location(w, 83, 78, -22));
        FFASpawns.add(new Location(w, 13, 69, -3));
        FFASpawns.add(new Location(w, 15, 74, 40));
        FFASpawns.add(new Location(w, -83, 74, -7));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack LEATHER_CAP = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_PANTS, LEATHER_CAP, LEATHER_BOOTS});

        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_CAP);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH_POTION);
        i.setItem(3, STEAK);
        i.setItem(5, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -122;
    public int y1 = 209;
    public int z1 = -125;

    //Bottom right corner.
    public int x2 = 118;
    public int y2 = -38;
    public int z2 = 109;

}
