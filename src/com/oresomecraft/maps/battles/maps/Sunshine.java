package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Sunshine extends BattleMap implements Listener {

    public Sunshine() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.BOW, Material.LEATHER_HELMET,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.WOOD_SWORD});
        lockTime("day");
    }

    String name = "sunshine";
    String fullName = "Sunshine";
    String[] creators = {"__R3", "am51407", "_Moist"};
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, -37, 89, 2));
        redSpawns.add(new Location(w, 32, 89, 2));

        setKoTHMonument(new Location(w, -3, 88, 2));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -37, 89, 2));
        FFASpawns.add(new Location(w, 32, 89, 2));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        //Items
        ItemStack IRON_AXE = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 8);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 8);
        //prevent arrow camping
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);

        // Armor
        setColouredArmorAccordingToTeam(p);

        i.setItem(0, IRON_AXE);
        i.setItem(1, BOW);
        i.setItem(2, BREAD);
        i.setItem(3, HEALTH);
        i.setItem(27, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -58;
    public int y1 = 160;
    public int z1 = -51;

    //Bottom right corner.
    public int x2 = 56;
    public int y2 = 68;
    public int z2 = 58;
}
