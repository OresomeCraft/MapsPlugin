package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Insanity extends BattleMap implements Listener {

    public Insanity() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
    }

    String name = "insanity";
    String fullName = "Insanity";
    String[] creators = {"ShaunDepro97", "darkrai202"};
    Gamemode[] modes = {Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        // This is a dud
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -1441, 94, -619, 2, 0);
        Location blueSpawn = new Location(w, -1441, 94, -587, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -1441, 94, -591, 2, 0));
        FFASpawns.add(new Location(w, -1441, 94, -615, 0, 0));
        FFASpawns.add(new Location(w, -1453, 94, -603, 3, 0));
        FFASpawns.add(new Location(w, -1438, 94, -603, 1, 0));
        FFASpawns.add(new Location(w, -1441, 94, -591, 2, 0));
        FFASpawns.add(new Location(w, -1441, 94, -615, 0, 0));
        FFASpawns.add(new Location(w, -1453, 94, -603, 3, 0));
        FFASpawns.add(new Location(w, -1438, 94, -603, 1, 0));
        FFASpawns.add(new Location(w, -1433, 99, -603, 1, 0));
        FFASpawns.add(new Location(w, -1441, 99, -587, 2, 0));
        FFASpawns.add(new Location(w, -1457, 99, -603, 3, 0));
        FFASpawns.add(new Location(w, -1441, 99, -619, 0, 0));
        FFASpawns.add(new Location(w, -1455, 99, -614, 0, 0));
        FFASpawns.add(new Location(w, -1425, 99, -599, 2, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -1413;
    public int y1 = 143;
    public int z1 = -638;

    // Bottom right corner.
    public int x2 = -1465;
    public int y2 = 63;
    public int z2 = -572;

    @EventHandler
    public void blood(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (getArena().equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
            }
        }
    }
}
