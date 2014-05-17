package com.oresomecraft.maps.battles.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.maps.*;
import com.oresomecraft.maps.battles.*;
import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Turmoil extends BattleMap implements Listener {

    public Turmoil() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
        setAllowBuild(false);
    }

    // Map details
    String name = "turmoil";
    String fullName = "Turmoil";
    String[] creators = {"ShaunDepro97"};
    Gamemode[] modes = {Gamemode.FFA};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -45, 32, -1892, 2, 0));

        blueSpawns.add(new Location(w, -45, 32, -1892, 2, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -37, 26, -1878, 2, 0));
        FFASpawns.add(new Location(w, -60, 26, -1883, 3, 0));
        FFASpawns.add(new Location(w, -56, 19, -1905, 0, 0));
        FFASpawns.add(new Location(w, -40, 17, -1879, 1, 0));
        FFASpawns.add(new Location(w, -57, 9, -1894, 3, 0));
        FFASpawns.add(new Location(w, -37, 5, -1905, 3, 0));
        FFASpawns.add(new Location(w, -24, 9, -1918, 0, 0));
        FFASpawns.add(new Location(w, -20, 18, -1942, 1, 0));
        FFASpawns.add(new Location(w, -66, 17, -1942, 3, 0));
        FFASpawns.add(new Location(w, -49, 17, -1926, 1, 0));
        FFASpawns.add(new Location(w, -29, 18, -1932, 1, 0));
        FFASpawns.add(new Location(w, -53, 9, -1937, 0, 0));
        FFASpawns.add(new Location(w, -8, 9, -1937, 0, 0));
        FFASpawns.add(new Location(w, -8, 19, -1878, 1, 0));
        FFASpawns.add(new Location(w, -45, 17, -1910, 3, 0));
        FFASpawns.add(new Location(w, -28, 22, -1896, 0, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);

        i.setItem(0, IRON_SWORD);
        p.setItem(1, Material.BOW, 1);
        p.setItem(2, Material.COOKED_BEEF, 2);
        i.setItem(3, HEALTH_POTION);
        p.setItem(28, Material.ARROW, 48);

        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600 * 20, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 0;
    public int y1 = 76;
    public int z1 = -1952;

    // Bottom right corner.
    public int x2 = -76;
    public int y2 = 0;
    public int z2 = -1857;

}
