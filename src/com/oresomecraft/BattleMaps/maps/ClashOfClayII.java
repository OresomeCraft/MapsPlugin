package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class ClashOfClayII extends BattleMap implements IBattleMap, Listener {

    public ClashOfClayII() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(20);
        disableDrops(new Material[]{Material.DIAMOND_HELMET, Material.WOOD_SWORD});
    }

    String name = "clashofclayii";
    String fullName = "Clash Of Clay II";
    String creators = "_Moist and R3creat3";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 20, 77, -25);
        Location blueSpawn = new Location(w, 250, 77, -27);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 20, 77, -25);
        Location blueSpawn = new Location(w, 250, 77, -27);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(redSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack WOODEN_SWORD = new ItemStack(Material.WOOD_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack BLUE_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 11);
        ItemStack RED_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 14);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LEATHER_CHESTPLATE.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
        ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemStack TORCH = new ItemStack(Material.TORCH, 16);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        p.getInventory().setHelmet(DIAMOND_HELMET);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        i.setItem(0, WOODEN_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, PUMPKIN_PIE);
        i.setItem(4, APPLE);

        if (p.getTeam() == Team.TDM_RED) i.setItem(5, RED_STAINED_CLAY);
        if (p.getTeam() == Team.TDM_BLUE) i.setItem(5, BLUE_STAINED_CLAY);

        i.setItem(6, TORCH);
        i.setItem(27, ARROW);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    //Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

    @EventHandler
    public void noSpawnBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if(loc.getWorld().getName() == name){
            if (contains(loc, 255, 246, 69, 88, -33, -19)) event.setCancelled(true);
            if (contains(loc, 15, 23, 70, 88, -17, -30)) event.setCancelled(true);
        }
    }

    @EventHandler
    public void noSpawnPlace(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if(loc.getWorld().getName() == name){
            if (contains(loc, 255, 246, 69, 88, -33, -19)) event.setCancelled(true);
            if (contains(loc, 15, 23, 70, 88, -17, -30)) event.setCancelled(true);
        }
    }
}
