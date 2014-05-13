package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Treetop extends BattleMap implements Listener {

    public Treetop() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
        setTDMTime(10);
        setAllowBuild(false);
        lockTime("night");
        setAutoSpawnProtection(2);
    }

    String name = "treetop";
    String fullName = "Treetop Warfare";
    String[] creators = {"meganlovesmusic", "_Husky_"};
    Gamemode[] modes = {Gamemode.KOTH, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -20, 71, 33));
        redSpawns.add(new Location(w, 17, 98, 69));
        redSpawns.add(new Location(w, 10, 98, 51));

        blueSpawns.add(new Location(w, -0, 96, 29));
        blueSpawns.add(new Location(w, -21, 103, 41));
        blueSpawns.add(new Location(w, 8, 68, 22));

        setKoTHMonument(new Location(w, 13, 119, 40));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -20, 71, 33));
        FFASpawns.add(new Location(w, 17, 98, 69));
        FFASpawns.add(new Location(w, 10, 98, 51));

        FFASpawns.add(new Location(w, 8, 68, 22));
        FFASpawns.add(new Location(w, -21, 103, 41));
        FFASpawns.add(new Location(w, -0, 96, 29));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 20);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 3);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH);
        i.setItem(11, ARROWS);
        i.setItem(2, BREAD);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 672;
    public int y1 = 4;
    public int z1 = 464;

    //Bottom right corner.
    public int x2 = 756;
    public int y2 = 4;
    public int z2 = 551;

}
