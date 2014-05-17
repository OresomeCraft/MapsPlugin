package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Stimulation extends BattleMap implements Listener {

    public Stimulation() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.ARROW, Material.LEATHER_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD, Material.BOW});
    }

    // Map details
    String name = "stimulation";
    String fullName = "Stimulation";
    String[] creators = {"ShaunDepro97"};
    Gamemode[] modes = {Gamemode.FFA};

    public void readyTDMSpawns() {

        redSpawns.add(new Location(w, -293, 13, 1207, 2, 0));

        blueSpawns.add(new Location(w, -252, 15, 1207, 0, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -293, 13, 1207, 2, 0));
        FFASpawns.add(new Location(w, -252, 15, 1207, 1, 0));
        FFASpawns.add(new Location(w, -255, 20, 1207, 1, 0));
        FFASpawns.add(new Location(w, -275, 20, 1207, 2, 0));
        FFASpawns.add(new Location(w, -264, 25, 1210, 0, 0));
        FFASpawns.add(new Location(w, -248, 30, 1213, 0, 0));
        FFASpawns.add(new Location(w, -251, 35, 1204, 0, 0));
        FFASpawns.add(new Location(w, -274, 35, 1197, 1, 0));
        FFASpawns.add(new Location(w, -258, 40, 1210, 0, 0));
        FFASpawns.add(new Location(w, -256, 45, 1221, 1, 0));
        FFASpawns.add(new Location(w, -257, 45, 1195, 0, 0));
        FFASpawns.add(new Location(w, -249, 50, 1221, 1, 0));
        FFASpawns.add(new Location(w, -249, 55, 1197, 0, 0));
        FFASpawns.add(new Location(w, -250, 60, 1200, 0, 0));
        FFASpawns.add(new Location(w, -283, 13, 1227, 1, 0));
        FFASpawns.add(new Location(w, -253, 10, 1199, 0, 0));
        FFASpawns.add(new Location(w, -250, 5, 1211, 2, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack COOKED_FISH = new ItemStack(Material.COOKED_FISH, 2);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack CHAINMAIL_PANTS = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(CHAINMAIL_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, COOKED_FISH);
        i.setItem(4, HEALTH_POTION);
        i.setItem(28, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -228;
    public int y1 = 200;
    public int z1 = 1180;

    //Bottom right corner.
    public int x2 = -303;
    public int y2 = 1;
    public int z2 = 1239;

}
