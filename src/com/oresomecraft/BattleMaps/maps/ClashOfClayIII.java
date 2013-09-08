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

public class ClashOfClayIII extends BattleMap implements IBattleMap, Listener {

    public ClashOfClayIII() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(20);
        disableDrops(new Material[]{Material.DIAMOND_HELMET, Material.WOOD_SWORD, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS});
    }

    String name = "clashofclay3";
    String fullName = "Clash Of Clay III";
    String creators = "_Moist and R3creat3";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -103, 30, -11, 177, 0);
        Location blueSpawn = new Location(w, -43, 71, -1, 87, 0);

        blueSpawns.add(new Location(w, -15, 70, -28, 91, 0));
        redSpawns.add(new Location(w, -108, 28, -51, -90, 0));

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setKoTHMonument(new Location(w, -63, 34, -26));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -103, 30, -11, 177, 0);
        Location blueSpawn = new Location(w, -43, 71, -1, 87, 0);

        FFASpawns.add(new Location(w, -15, 70, -28, 91, 0));
        FFASpawns.add(new Location(w, -108, 28, -51, -90, 0));

        FFASpawns.add(blueSpawn);
        FFASpawns.add(redSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack WOODEN_SWORD = new ItemStack(Material.WOOD_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        ItemStack POTATO = new ItemStack(Material.BAKED_POTATO, 3);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack TORCH = new ItemStack(Material.TORCH, 16);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);
        ItemStack BLUE_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 11);
        ItemStack RED_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 14);

        ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_BOOTS});

        p.getInventory().setHelmet(DIAMOND_HELMET);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setBoots(LEATHER_BOOTS);

        LEATHER_CHESTPLATE.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        i.setItem(0, WOODEN_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, POTATO);
        i.setItem(4, APPLE);
        i.setItem(6, TORCH);
        i.setItem(27, ARROW);

        if (p.getTeam() == Team.TDM_RED) i.setItem(5, RED_STAINED_CLAY);
        if (p.getTeam() == Team.TDM_BLUE) i.setItem(5, BLUE_STAINED_CLAY);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 52;
    public int y1 = 127;
    public int z1 = -110;

    //Bottom right corner.
    public int x2 = -179;
    public int y2 = -40;
    public int z2 = 56;

}
