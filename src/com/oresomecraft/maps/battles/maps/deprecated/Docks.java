package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Entity;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Docks extends BattleMap implements Listener {

    public Docks() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
    }

    String name = "docks";
    String fullName = "The Docks";
    String[] creators = {"RhinoViru5", "tomfoowe1", "yozy3"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 1790, 30, 491, -90, 0);
        Location blueSpawn = new Location(w, 1793, 23, 490, 90, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 1793, 23, 490, 90, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 1793, 23, 490, 90, 0));
        blueSpawns.add(new Location(w, 1700, 23, 490, 90, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 1796, 18, 490, -90, 0));
        FFASpawns.add(new Location(w, 1792, 23, 490, 90, 0));
        FFASpawns.add(new Location(w, 1791, 26, 490, 90, 0));
        FFASpawns.add(new Location(w, 1792, 30, 490, 90, 0));
        FFASpawns.add(new Location(w, 1771, 22, 490, 90, 0));
        FFASpawns.add(new Location(w, 1750, 22, 509, 179, 0));
        FFASpawns.add(new Location(w, 1750, 22, 470, 0, 0));
        FFASpawns.add(new Location(w, 1721, 22, 489, 0, -90));
        FFASpawns.add(new Location(w, 1704, 18, 490, -90, 0));
        FFASpawns.add(new Location(w, 1704, 23, 490, -90, 0));
        FFASpawns.add(new Location(w, 1704, 26, 490, -90, 0));
        FFASpawns.add(new Location(w, 1704, 30, 490, -90, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 16);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(8, SNOW_BALL);
        i.setItem(3, HEALTH_POTION);
        i.setItem(2, STEAK);
        i.setItem(4, EXP);
        i.setItem(9, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 1674;
    public int y1 = 61;
    public int z1 = 439;

    //Bottom right corner.
    public int x2 = 1822;
    public int y2 = 3;
    public int z2 = 538;

    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (getArena().equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), org.bukkit.Effect.STEP_SOUND, 119);
            }
        }
    }

}
