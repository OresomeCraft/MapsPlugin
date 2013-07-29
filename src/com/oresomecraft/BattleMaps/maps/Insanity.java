package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Effect;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Arrow;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.BattleHandler;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Insanity extends BattleMap implements IBattleMap, Listener {

    public Insanity() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "insanity";
    String fullName = "Insanity";
    String creators = "ShaunDepro97 and darkrai202";
    Gamemode[] modes = {Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        // This is a dud
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

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

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
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
            i.setItem(4, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -1410;
    public int y1 = 130;
    public int z1 = -571;

    // Bottom right corner.
    public int x2 = -1429;
    public int y2 = 85;
    public int z2 = -635;

    @EventHandler
    public void blood(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (BattleHandler.activeArena.equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_WIRE);
            }
        }
    }

}