package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Spleef extends BattleMap implements IBattleMap, Listener {

    public Spleef() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.DIAMOND_SPADE});
    }

    // Map details
    String name = "spleef";
    String fullName = "Spleef";
    String creators = "Zachoz ";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyTDMSpawns() {
        // Dud
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 3, 66, 1));
        FFASpawns.add(new Location(w, 15, 66, 1));
        FFASpawns.add(new Location(w, -25, 66, 1));
        FFASpawns.add(new Location(w, -4, 66, -22));
        FFASpawns.add(new Location(w, -5, 66, 21));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
        
        ItemStack DIAMOND_SPADE = new ItemStack(Material.DIAMOND_SPADE, 1);
        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 16);
        ItemStack CHAINMAIL_HELMET = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        ItemStack CHAINMAIL_CHESTPLATE = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
        ItemStack CHAINMAIL_PANTS = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        ItemStack CHAINMAIL_BOOTS = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        
        InvUtils.nameItem(DIAMOND_SPADE, ChatColor.BLUE + "Spleefer's Shovel");
        
        p.getInventory().setBoots(CHAINMAIL_BOOTS);
        p.getInventory().setLeggings(CHAINMAIL_PANTS);
        p.getInventory().setChestplate(CHAINMAIL_CHESTPLATE);
        p.getInventory().setHelmet(CHAINMAIL_HELMET);
        
        i.setItem(0, DIAMOND_SPADE);
        i.setItem(1, SNOW_BALL);
        i.setItem(2, STEAK);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    // Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

}
