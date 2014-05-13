package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Battlement extends BattleMap implements Listener {

    public Battlement() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
    }

    String name = "battlement";
    String fullName = "Battlement";
    String[] creators = {"ShaunDepro97"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 19, 74, -20, 1, 0);
        Location blueSpawn = new Location(w, -22, 74, -12, 3, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 23, 78, -24, 1, 0));
        redSpawns.add(new Location(w, 11, 70, -12, 1, 0));
        redSpawns.add(new Location(w, 15, 65, -16, 3, 0));
        redSpawns.add(new Location(w, 27, 66, -28, 1, 0));
        redSpawns.add(new Location(w, 27, 70, -28, 1, 0));
        redSpawns.add(new Location(w, 27, 74, -28, 1, 0));
        redSpawns.add(new Location(w, 27, 78, -28, 1, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -26, 78, 16, 3, 0));
        blueSpawns.add(new Location(w, -14, 70, 4, 3, 0));
        blueSpawns.add(new Location(w, -18, 65, 8, 1, 0));
        blueSpawns.add(new Location(w, -30, 66, 20, 3, 0));
        blueSpawns.add(new Location(w, -30, 70, 20, 3, 0));
        blueSpawns.add(new Location(w, -30, 74, 20, 3, 0));
        blueSpawns.add(new Location(w, -30, 78, 20, 3, 0));
    }

    public void readyFFASpawns() {

        Location redSpawn = new Location(w, 19, 74, -20, 1, 0);
        Location blueSpawn = new Location(w, -22, 74, -12, 3, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 23, 78, -24, 1, 0));
        FFASpawns.add(new Location(w, 11, 70, -12, 1, 0));
        FFASpawns.add(new Location(w, 15, 65, -16, 3, 0));
        FFASpawns.add(new Location(w, 27, 66, -28, 1, 0));
        FFASpawns.add(new Location(w, 27, 70, -28, 1, 0));
        FFASpawns.add(new Location(w, 27, 74, -28, 1, 0));
        FFASpawns.add(new Location(w, 27, 78, -28, 1, 0));
        FFASpawns.add(new Location(w, -26, 78, 16, 3, 0));
        FFASpawns.add(new Location(w, -14, 70, 4, 3, 0));
        FFASpawns.add(new Location(w, -18, 65, 8, 1, 0));
        FFASpawns.add(new Location(w, -30, 66, 20, 3, 0));
        FFASpawns.add(new Location(w, -30, 70, 20, 3, 0));
        FFASpawns.add(new Location(w, -30, 74, 20, 3, 0));
        FFASpawns.add(new Location(w, -30, 78, 20, 3, 0));
        FFASpawns.add(new Location(w, 0, 53, 0, 2, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
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
        i.setItem(8, ARROWS);
        i.setItem(4, EXP);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 91;
    public int y1 = 149;
    public int z1 = -92;

    // Bottom right corner.
    public int x2 = -108;
    public int y2 = -7;
    public int z2 = 106;

}
