package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Rainbow extends BattleMap implements Listener {

    public Rainbow() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS});
    }

    String name = "rainbow";
    String fullName = "Rainbow Road";
    String creators = "__R3, MintyPvP, JacquiRose, AnomalousDyna, AlphaMinecraft91, simonwilson123, callumary and Spod";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -41, 73, -209);
        Location blueSpawn = new Location(w, -28, 73, 77);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -41, 73, -209);
        Location blueSpawn = new Location(w, -28, 73, 77);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STONE = new ItemStack(Material.STONE, 64);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack GOLD_LEGGINGS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack GOLD_BOOTS = new ItemStack(Material.GOLD_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack PICK = new ItemStack(Material.IRON_PICKAXE, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_HELMET});

        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);
        p.getInventory().setBoots(GOLD_BOOTS);
        p.getInventory().setLeggings(GOLD_LEGGINGS);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, PICK);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(8, STONE);
        i.setItem(10, ARROWS);

    }

}
