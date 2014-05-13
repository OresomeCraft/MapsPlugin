package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class SunsetBay extends BattleMap implements Listener {

    public SunsetBay() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.IRON_SWORD, Material.LEATHER_HELMET});
        setAllowBuild(false);
        lockTime(13116);
    }

    // Map details
    String name = "sunsetbay";
    String fullName = "Sunset Bay";
    String[] creators = {"SuperDuckFace"};
    Gamemode[] modes = {Gamemode.FFA};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -38, 85, 21, -66, 0));
        blueSpawns.add(new Location(w, -55, 97, 162, -112, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -38, 85, 21, -66, 0));
        FFASpawns.add(new Location(w, -29, 83, 6, -66, 0));
        FFASpawns.add(new Location(w, -25, 82, 6, -43, 0));
        FFASpawns.add(new Location(w, -9, 81, -9, -66, 0));
        FFASpawns.add(new Location(w, -8, 80, -5, -48, 0));
        FFASpawns.add(new Location(w, 20, 80, -2, 89, 0));
        FFASpawns.add(new Location(w, 25, 81, 1, 86, 0));
        FFASpawns.add(new Location(w, 34, 81, 15, 88, 0));
        FFASpawns.add(new Location(w, -87, 99, 43, -53, 0));
        FFASpawns.add(new Location(w, -79, 82, 42, -164, 0));
        FFASpawns.add(new Location(w, -73, 88, 46, -40, 0));
        FFASpawns.add(new Location(w, -66, 85, 56, -40, 0));
        FFASpawns.add(new Location(w, -96, 102, 54, -62, 0));
        FFASpawns.add(new Location(w, -69, 90, 41, -63, 0));
        FFASpawns.add(new Location(w, -101, 102, 68, -154, 0));
        FFASpawns.add(new Location(w, -111, 102, 87, 159, 0));
        FFASpawns.add(new Location(w, -129, 107, 106, -165, 0));
        FFASpawns.add(new Location(w, -107, 102, 126, -167, 0));
        FFASpawns.add(new Location(w, -95, 100, 133, -38, 0));
        FFASpawns.add(new Location(w, -86, 100, 141, -60, 0));
        FFASpawns.add(new Location(w, -62, 93, 138, -74, 0));
        FFASpawns.add(new Location(w, -65, 97, 156, -132, 0));
        FFASpawns.add(new Location(w, -55, 97, 162, -112, 0));
        FFASpawns.add(new Location(w, -34, 88, 158, -134, 0));
        FFASpawns.add(new Location(w, -31, 88, 165, -84, 0));
        FFASpawns.add(new Location(w, -24, 87, 173, -141, 0));
        FFASpawns.add(new Location(w, -14, 86, 175, -151, 0));
        FFASpawns.add(new Location(w, -7, 86, 178, -86, 0));
        FFASpawns.add(new Location(w, 14, 86, 178, -121, 0));
        FFASpawns.add(new Location(w, 39, 88, 168, 158, 0));
        FFASpawns.add(new Location(w, 56, 89, 156, -158, 0));
        FFASpawns.add(new Location(w, 58, 80, 142, -179, 0));
        FFASpawns.add(new Location(w, 65, 80, 131, -143, 0));
        FFASpawns.add(new Location(w, 71, 81, 117, 147, 0));
        FFASpawns.add(new Location(w, 75, 81, 105, 69, 0));
        FFASpawns.add(new Location(w, 70, 81, 76, 153, 0));
        FFASpawns.add(new Location(w, 77, 81, 61, 123, 0));
        FFASpawns.add(new Location(w, 63, 81, 52, 107, 0));
        FFASpawns.add(new Location(w, 54, 80, 43, 155, 0));
        FFASpawns.add(new Location(w, 50, 80, 31, 30, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
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
        ItemStack PEARL = new ItemStack(Material.ENDER_PEARL, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
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
        i.setItem(8, PEARL);
        i.setItem(9, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -143;
    public int y1 = 174;
    public int z1 = -41;

    //Bottom right corner.
    public int x2 = 107;
    public int y2 = 59;
    public int z2 = 192;

}
