package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class HauntedHouse extends BattleMap implements IBattleMap, Listener {

    public HauntedHouse() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setTDMTime(5);
    }

    // Map details
    String name = "hauntedhouse";
    String fullName = "Haunted House";
    String creators = "bumsonfire";
    Gamemode[] modes = {Gamemode.FFA};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -230, 78, 17, 0, 0);
        Location blueSpawn = new Location(w, -267, 85, 87, -145, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -255, 79, 46, 0, 0));
        redSpawns.add(new Location(w, -269, 83, 25, -68, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -214, 83, 92, 150, 0));
        blueSpawns.add(new Location(w, -258, 80, 64, 90, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -230, 78, 17, 0, 0));
        FFASpawns.add(new Location(w, -267, 85, 87, -145, 0));
        FFASpawns.add(new Location(w, -255, 79, 46, 0, 0));
        FFASpawns.add(new Location(w, -269, 83, 25, -68, 0));
        FFASpawns.add(new Location(w, -214, 83, 92, 150, 0));
        FFASpawns.add(new Location(w, -258, 80, 64, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
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

    // Top left corner.
    public int x1 = -8;
    public int y1 = 164;
    public int z1 = 16;

    //Bottom right corner.
    public int x2 = -85;
    public int y2 = 62;
    public int z2 = 99;
}
