package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Relation extends BattleMap implements Listener {

    public Relation() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
        setAllowBuild(false);
    }

    String name = "relation";
    String fullName = "Relation";
    String[] creators = {"ShaunDepro97"};
    Gamemode[] modes = {Gamemode.FFA};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 64, 76, 64, 2, 0);
        Location blueSpawn = new Location(w, 64, 76, 64, 0, 0);
        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 64, 92, 64, 2, 0));
        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 64, 92, -64, 0, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 64, 76, 64, 2, 0);
        Location blueSpawn = new Location(w, 64, 76, 64, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 57, 84, 65, 2, 0));
        FFASpawns.add(new Location(w, 65, 84, 56, 2, 0));
        FFASpawns.add(new Location(w, 73, 84, 65, 2, 0));
        FFASpawns.add(new Location(w, 64, 84, 73, 2, 0));
        FFASpawns.add(new Location(w, 57, 76, 65, 2, 0));
        FFASpawns.add(new Location(w, 65, 76, 56, 2, 0));
        FFASpawns.add(new Location(w, 73, 76, 65, 2, 0));
        FFASpawns.add(new Location(w, 64, 76, 73, 2, 0));
        FFASpawns.add(new Location(w, 64, 92, 72, 2, 0));
        FFASpawns.add(new Location(w, 64, 92, 56, 2, 0));
        FFASpawns.add(new Location(w, 47, 88, 47, 2, 0));
        FFASpawns.add(new Location(w, 82, 88, 82, 2, 0));
        FFASpawns.add(new Location(w, 82, 80, 47, 2, 0));
        FFASpawns.add(new Location(w, 47, 80, 82, 2, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 92;
    public int y1 = 256;
    public int z1 = 92;

    //Bottom right corner.
    public int x2 = 38;
    public int y2 = 1;
    public int z2 = 38;

}
