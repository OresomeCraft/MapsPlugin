package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Spaceships extends BattleMap implements IBattleMap, Listener {

    public Spaceships() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(15);
        disableDrops(new Material[]{Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.LAVA_BUCKET, Material.TNT});
    }

    String name = "spaceships";
    String fullName = "SpaceShips";
    String creators = "sampighere, zachoz and R3creat3";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        Location blueSpawn = new Location(w, 142, 43, -80, 0, 0);
        Location redSpawn = new Location(w, 210, 43, -80, 0, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location blueSpawn = new Location(w, 142, 43, -80, 0, 0);
        Location redSpawn = new Location(w, 210, 43, -80, 0, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);
        i.setItem(0, new ItemStack(Material.DIAMOND_SWORD, 1));
        i.setItem(1, new ItemStack(Material.BOW, 1));
        i.setItem(2, new ItemStack(Material.DIAMOND_PICKAXE, 1));
        i.setItem(3, new ItemStack(Material.COOKED_BEEF, 3));
        i.setItem(4, new ItemStack(Material.GOLDEN_APPLE, 2));
        i.setItem(5, new ItemStack(Material.LOG, 64));
        i.setItem(8, new ItemStack(Material.ENDER_PEARL, 1));
        i.setItem(9, new ItemStack(Material.ARROW, 64));
        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 8 * 20, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40 * 20, 1));

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 106;
    public int y1 = 110;
    public int z1 = 34;

    //Bottom right corner.
    public int x2 = 246;
    public int y2 = 0;
    public int z2 = -191;

}
