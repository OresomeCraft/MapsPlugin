package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Elements extends BattleMap implements IBattleMap, Listener {

    public Elements() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(12);
    }

    String name = "elements";
    String fullName = "Elements";
    String creators = "broddikill, koolguydude4 and MiCkEyMiCE";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -23, 87, 11));
        blueSpawns.add(new Location(w, -25, 86, 147));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -23, 87, 11));
        FFASpawns.add(new Location(w, -25, 86, 147));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LOG = new ItemStack(Material.LOG, 25);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STONE_PICK = new ItemStack(Material.STONE_PICKAXE, 1);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STONE_PICK);
        i.setItem(4, HEALTH);
        i.setItem(8, LOG);
        i.setItem(11, ARROWS);
        i.setItem(3, new ItemStack(Material.BREAD, 3));

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 20 * 20, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15000 * 20, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 2));

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -51;
    public int y1 = 74;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 7;
    public int y2 = 171;
    public int z2 = 166;

    @EventHandler
    public void noSpawnBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (contains(loc, -34, -15, 85, 129, 155, 138)) event.setCancelled(true);
        if (contains(loc, -12, -33, 82, 130, 2, 20)) event.setCancelled(true);
    }

    @EventHandler
    public void noSpawnPlace(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if (contains(loc, -34, -15, 85, 129, 155, 138)) event.setCancelled(true);
        if (contains(loc, -12, -33, 82, 130, 2, 20)) event.setCancelled(true);
    }
}
