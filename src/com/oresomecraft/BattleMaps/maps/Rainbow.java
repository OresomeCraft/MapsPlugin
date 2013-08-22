package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Rainbow extends BattleMap implements IBattleMap, Listener {

    public Rainbow() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
    }

    String name = "rainbow";
    String fullName = "Rainbow Road";
    String creators = "R3creat3, MintyPvP, JacquiRose, DynaDavidson, AlphaMinecraft91, simonwilson123 and _Moist";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -41, 73, -209);
        Location blueSpawn = new Location(w, -28, 73, 77);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -41, 73, -209);
        Location blueSpawn = new Location(w, -28, 73, 77);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);

        setFFASpawns(name, FFASpawns);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack PICK = new ItemStack(Material.IRON_PICKAXE, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_HELMET});
        
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, PICK);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(10, ARROWS);

    }

}
