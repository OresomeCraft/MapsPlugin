package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Sunshine extends BattleMap implements IBattleMap, Listener {

    public Sunshine() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.LEATHER_HELMET,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.WOOD_SWORD});
    }

    String name = "sunshine";
    String fullName = "Sunshine";
    String creators = "R3creat3, am51407 and _Moist";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        blueSpawns.add(new Location(w, -37, 89, 2));
        redSpawns.add(new Location(w, 32, 89, 2));
        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
        setKoTHMonument(new Location(w, -3, 88, 2));
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -37, 89, 2));
        FFASpawns.add(new Location(w, 32, 89, 2));

        setFFASpawns(name, FFASpawns);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        //Items
        ItemStack IRON_AXE = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 8);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 8);
        //prevent arrow camping
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);

        // Armor
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);

        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_AXE);
        i.setItem(1, BOW);
        i.setItem(2, BREAD);
        i.setItem(3, HEALTH);
        i.setItem(27, ARROW);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_BOOTS, LEATHER_PANTS, LEATHER_HELMET});

        //Give players invincibility and strength for 7 seconds when they spawn
        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 7 * 20, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 7 * 20, 1));

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

}
