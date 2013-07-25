package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Docks extends BattleMap implements IBattleMap, Listener {

    public Docks() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "docks";
    String fullName = "The Docks";
    String creators = "RhinoViru5, tomfoowe1 and yozy3";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 1796, 18, 490, -90, 0);
        Location blueSpawn = new Location(w, 1793, 23, 490, 90, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 1796, 18, 490, -90, 0));
        redSpawns.add(new Location(w, 1793, 23, 490, 90, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 1793, 23, 490, 90, 0));
        blueSpawns.add(new Location(w, 1700, 23, 490, 90, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

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

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 16);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
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
            i.setItem(2, SNOW_BALL);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, STEAK);
            i.setItem(5, EXP);
            i.setItem(8, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 1682;
    public int y1 = 545;
    public int z1 = 446;

    //Bottom right corner.
    public int x2 = 1813;
    public int y2 = 3;
    public int z2 = 531;

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockbreak(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockplace(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

    @EventHandler
    public void arrowBoom(org.bukkit.event.entity.ProjectileHitEvent event) {
        org.bukkit.entity.Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (Utility.getArena().equals(name)) {
            if (arrow instanceof org.bukkit.entity.Arrow) {
                world.playEffect(arrow.getLocation(), org.bukkit.Effect.STEP_SOUND, 119);
            }
        }
    }

}
