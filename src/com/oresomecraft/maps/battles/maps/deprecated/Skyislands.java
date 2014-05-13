package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Skyislands extends BattleMap implements Listener {

    public Skyislands() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
        setAllowBuild(false);
    }

    String name = "skyislands";
    String fullName = "Sky Islands";
    String[] creators = {"tarko2411", "dutchy336"};
    Gamemode[] modes = {Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 738, 170, -1203, 179, 0);
        Location blueSpawn = new Location(w, 743, 170, -1268, 179, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 734, 170, -1219, 178, 0));
        redSpawns.add(new Location(w, 760, 170, -1218, -177, 0));
        redSpawns.add(new Location(w, 727, 182, -1222, 179, 0));
        redSpawns.add(new Location(w, 742, 209, -1235, -179, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 738, 170, -1203, 179, 0));
        blueSpawns.add(new Location(w, 734, 170, -1255, 0, 0));
        blueSpawns.add(new Location(w, 771, 182, -1255, 5, 0));
        blueSpawns.add(new Location(w, 742, 209, -1241, 0, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 738, 170, -1203, 179, 0);
        Location blueSpawn = new Location(w, 743, 170, -1268, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 771, 182, -1255, 5, 0));
        FFASpawns.add(new Location(w, 755, 176, -1194, -176, 0));
        FFASpawns.add(new Location(w, 728, 182, -1223, -86, 0));
        FFASpawns.add(new Location(w, 771, 190, -1233, 2, 0));
        FFASpawns.add(new Location(w, 736, 200, -1235, 2, 0));
        FFASpawns.add(new Location(w, 743, 177, -1236, 0, 0));
        FFASpawns.add(new Location(w, 746, 188, -1239, 39, 0));
        FFASpawns.add(new Location(w, 741, 199, -1227, -4, 0));
        FFASpawns.add(new Location(w, 748, 171, -1227, -179, 0));
        FFASpawns.add(new Location(w, 748, 193, -1230, -179, 0));
        FFASpawns.add(new Location(w, 721, 170, -1234, 1, 0));
        FFASpawns.add(new Location(w, 719, 179, -1225, -89, 0));
        FFASpawns.add(new Location(w, 710, 170, -1256, -88, 0));
        FFASpawns.add(new Location(w, 778, 170, -1239, 92, 0));
        FFASpawns.add(new Location(w, 750, 198, -1233, -88, 0));
        FFASpawns.add(new Location(w, 742, 182, -1237, -179, 0));
        FFASpawns.add(new Location(w, 764, 177, -1233, -179, 0));
        FFASpawns.add(new Location(w, 731, 178, -1240, -1, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
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
        i.setItem(9, ARROWS);
        i.setItem(4, EXP);


    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 694;
    public int y1 = 170;
    public int z1 = -1185;

    //Bottom right corner.
    public int x2 = 786;
    public int y2 = 170;
    public int z2 = -1283;

}
