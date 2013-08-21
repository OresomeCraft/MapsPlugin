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

public class ClashOfClay extends BattleMap implements IBattleMap, Listener {

    public ClashOfClay() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setTDMTime(20);
        disableDrops(new Material[]{Material.DIAMOND_HELMET, Material.WOOD_SWORD});
    }

    String name = "clashofclay";
    String fullName = "Clash Of Clay";
    String creators = "_Moist and niceman506";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, -22, 81, 8);
        Location blueSpawn = new Location(w, -23, 81, 164);

        redSpawns.add(redSpawn);

        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, -22, 81, 8);
        Location blueSpawn = new Location(w, -23, 81, 164);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(redSpawn);
        setFFASpawns(name, FFASpawns);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack WOODEN_SWORD = new ItemStack(Material.WOOD_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        //Make the pick semi-unlimited - R3
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack BLUE_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 11);
        ItemStack RED_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 14);
        //Give a LITTLE more clay - R3
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LEATHER_CHESTPLATE.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
        ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
        //Make the rushers a little less weak, about 10% less damage/
        ItemStack TORCH = new ItemStack(Material.TORCH, 16);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);

        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(DIAMOND_HELMET);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

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
    public void blockPlace(BlockPlaceEvent event) {
        if (contains(event.getBlock().getLocation(), -24, -21, 79, 84, 165, 162)) event.setCancelled(true);
        if (contains(event.getBlock().getLocation(), -21, -24, 79, 86, 7, 10)) event.setCancelled(true);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        if (contains(event.getBlock().getLocation(), -24, -21, 79, 84, 165, 162)) event.setCancelled(true);
        if (contains(event.getBlock().getLocation(), -21, -24, 79, 86, 7, 10)) event.setCancelled(true);
    }

    //May be incorrect, if not, fix.
    @EventHandler
    public void clay(ProjectileHitEvent event) {
        if (getArena().equals(name)) {
            Location loc = event.getEntity().getLocation();
            if (!contains(loc, -24, -21, 79, 84, 165, 162) || !(contains(loc, 24, -21, 79, 84, 165, 162))) {
                if (Math.random() > 0.25) {
                    Block b = Bukkit.getWorld(name).getBlockAt(loc);
                    b.setType(Material.CLAY);
                }
            }
        }
    }
}
