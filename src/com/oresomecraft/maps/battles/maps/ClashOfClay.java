package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class ClashOfClay extends BattleMap implements Listener {

    public ClashOfClay() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(15);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.DIAMOND_HELMET, Material.WOOD_SWORD});
        disableBlocks(new Material[]{Material.WORKBENCH});
    }

    String name = "clashofclay";
    String fullName = "Clash Of Clay";
    String[] creators = {"_Moist", "niceman506"};
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -22, 81, 8));
        blueSpawns.add(new Location(w, -23, 81, 164));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -22, 81, 8));
        FFASpawns.add(new Location(w, -23, 81, 164));
        defineRegion(x1, x2, y1, y2, z1, z2);
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
        ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemStack TORCH = new ItemStack(Material.TORCH, 16);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);

        LEATHER_CHESTPLATE.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(DIAMOND_HELMET);

        i.setItem(0, WOODEN_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, PUMPKIN_PIE);
        i.setItem(4, APPLE);

        if (p.getTeamType() == Team.TDM_RED) i.setItem(5, RED_STAINED_CLAY);
        if (p.getTeamType() == Team.TDM_BLUE) i.setItem(5, BLUE_STAINED_CLAY);

        i.setItem(6, TORCH);
        i.setItem(27, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 15;
    public int y1 = 126;
    public int z1 = -15;

    //Bottom right corner.
    public int x2 = -58;
    public int y2 = 51;
    public int z2 = 181;

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Location location = event.getBlock().getLocation();
        if (location.getWorld().getName().equals(name)) {
            if (contains(event.getBlock().getLocation(), -24, -21, 79, 84, 165, 162)) event.setCancelled(true);
            if (contains(event.getBlock().getLocation(), -21, -24, 79, 86, 7, 10)) event.setCancelled(true);
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        if (location.getWorld().getName().equals(name)) {
            if (contains(event.getBlock().getLocation(), -24, -21, 79, 84, 165, 162)) event.setCancelled(true);
            if (contains(event.getBlock().getLocation(), -21, -24, 79, 86, 7, 10)) event.setCancelled(true);
        }
    }
}
