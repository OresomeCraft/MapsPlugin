package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class TowerHill extends BattleMap implements Listener {

    public TowerHill() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.ARROW, Material.GOLD_BOOTS, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_HELMET, Material.IRON_SWORD, Material.BOW});
        setAllowBuild(false);
    }

    // Map details
    String name = "towerhill";
    String fullName = "Tower Hill";
    String[] creators = {"Hourani95"};
    Gamemode[] modes = {Gamemode.INFECTION, Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -31, 71, 13, -34, 0);
        Location blueSpawn = new Location(w, -40, 71, -31, -82, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -43, 71, 12, -64, 0));
        redSpawns.add(new Location(w, -11, 71, -1, 179, 0));
        redSpawns.add(new Location(w, -57, 71, -1, -165, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -12, 75, -7, 89, 0));
        blueSpawns.add(new Location(w, -57, 71, -26, -62, 0));
        blueSpawns.add(new Location(w, -56, 75, -8, -89, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -31, 71, 13, -34, 0));
        FFASpawns.add(new Location(w, -40, 71, -31, -82, 0));
        FFASpawns.add(new Location(w, -43, 71, 12, -64, 0));
        FFASpawns.add(new Location(w, -11, 71, -1, 179, 0));
        FFASpawns.add(new Location(w, -57, 71, -1, -165, 0));
        FFASpawns.add(new Location(w, -12, 75, -7, 89, 0));
        FFASpawns.add(new Location(w, -57, 71, -26, -62, 0));
        FFASpawns.add(new Location(w, -56, 75, -8, -89, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack GOLD_HELMET = new ItemStack(Material.GOLD_HELMET, 1);
        ItemStack GOLD_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack GOLD_BOOTS = new ItemStack(Material.GOLD_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        GOLD_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 2);

        p.getInventory().setBoots(GOLD_BOOTS);
        p.getInventory().setLeggings(GOLD_PANTS);
        p.getInventory().setChestplate(GOLD_CHESTPLATE);
        p.getInventory().setHelmet(GOLD_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(35, ARROWS);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

    }

    // Top left corner.
    public int x1 = -84;
    public int y1 = 153;
    public int z1 = 43;

    //Bottom right corner.
    public int x2 = 22;
    public int y2 = 23;
    public int z2 = -67;

}
