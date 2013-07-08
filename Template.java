package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.BattleMaps.IBattleMap;
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
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Template extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Template(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "zipped";
    String fullName = "zipped";
    String creators = "chillhill3";
    Gamemode[] modes = {Gamemode.INFECTION, Gamemode.INFECTION
    , Gamemode.INFECTION};
    //Map download link: N/A

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) { // Internal - Do not change
        addMap(zipped);
        addCreators(chll);
        setFullName(chillhill3);
        setGamemodes(infection);
    }

    @EventHandler
    public void setSpawns(WorldLoadEvent event) { // Internal - Do not change
        if (event.getWorld().getName().equals(name)) {
            readyTDMSpawns();
            readyFFASpawns();
        }
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 0, 99, 27, 2, 0));
        redSpawns.add(new Location(w, -9, 110, -20, 0, 0));
        redSpawns.add(new Location(w, 21, 105, -13, 0, 0));
        redSpawns.add(new Location(w, 4, 106, -41, 0, 0));
        redSpawns.add(new Location(w, -18, 101, 13, 0, 0));
        redSpawns.add(new Location(w, 2, 104, 15, 0, 0));
        redSpawns.add(new Location(w, -2, 109, -4, 0, 0));
        redSpawns.add(new Location(w, 28, 105, 10, 0, 0));
        redSpawns.add(new Location(w, 27, 96, 0, 0, 0));
        redSpawns.add(new Location(w, 30, 105, -14, 0, 0));
        redSpawns.add(new Location(w, -9, 106, 18, 0, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -9, 110, -20, 0, 0));
        blueSpawns.add(new Location(w, 0, 99, 27, 0, 0));
        blueSpawns.add(new Location(w, -16, 108, -3, 0, 0));
        blueSpawns.add(new Location(w, -30, 108, -3, 0, 0));
        blueSpawns.add(new Location(w, -18, 101, 13, 0, 0));
        blueSpawns.add(new Location(w, -27, 88, 17, 0, 0));
        blueSpawns.add(new Location(w, -32, 76, 16, 0, 0));
        blueSpawns.add(new Location(w, -46, 97, 7, 0, 0));
        blueSpawns.add(new Location(w, 26, 105, -13, 0, 0));
        blueSpawns.add(new Location(w, 21, 94, 2, 0, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 0, 99, 27, 2, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
        FFASpawns.add(new Location(w, 21, 105, -13, 0, 0));
        FFASpawns.add(new Location(w, 4, 106, -41, 0, 0));
        FFASpawns.add(new Location(w, -18, 101, 13, 0, 0));
        FFASpawns.add(new Location(w, 2, 104, 15, 0, 0));
        FFASpawns.add(new Location(w, -2, 109, -4, 0, 0));
        FFASpawns.add(new Location(w, 28, 105, 10, 0, 0));
        FFASpawns.add(new Location(w, 27, 96, 0, 0, 0));
        FFASpawns.add(new Location(w, 30, 105, -14, 0, 0));
        FFASpawns.add(new Location(w, -9, 106, 18, 0, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
        FFASpawns.add(new Location(w, 0, 99, 27, 0, 0));
        FFASpawns.add(new Location(w, -16, 108, -3, 0, 0));
        FFASpawns.add(new Location(w, -30, 108, -3, 0, 0));
        FFASpawns.add(new Location(w, -18, 101, 13, 0, 0));
        FFASpawns.add(new Location(w, -27, 88, 17, 0, 0));
        FFASpawns.add(new Location(w, -32, 76, 16, 0, 0));
        FFASpawns.add(new Location(w, -46, 97, 7, 0, 0));
        FFASpawns.add(new Location(w, 26, 105, -13, 0, 0));
        FFASpawns.add(new Location(w, 21, 94, 2, 0, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
            ItemStack DIAMOND_CHEST = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
            ItemStack DIAMOND_PANTS = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
            ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
            ItemStack DIamond_SWORD = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(1, diamond_SWORD);
            i.setItem(1, BOW);
            i.setItem(6, STEAK);
            i.setItem(1, HEALTH_POTION);
            i.setItem(128, ARROWS);
            i.setItem(5, EXP);

        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void clearSpawns(ClearSpawnsEvent event) {
        redSpawns.clear();
        blueSpawns.clear();
        FFASpawns.clear();
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

    // Getting the region
    public boolean contains(Location loc, int x1, int x2, int y1,
                            int y2, int z1, int z2) {
        int bottomCornerX = x1 < x2 ? x1 : x2;
        int bottomCornerZ = z1 < z2 ? z1 : z2;
        int topCornerX = x1 > x2 ? x1 : x2;
        int topCornerZ = z1 > z2 ? z1 : z2;
        int bottomCornerY = y1 < y2 ? y1 : y2;
        int topCornerY = y1 > y2 ? y1 : y2;
        if (loc.getX() >= bottomCornerX && loc.getX() <= topCornerX) {
            if (loc.getZ() >= bottomCornerZ && loc.getZ() <= topCornerZ) {
                if (loc.getY() >= bottomCornerY && loc.getY() <= topCornerY) {
                    return true;
                }
            }
        }
        return false;

    }

    // Code to prevent block breaking.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    // Code to prevent block placing.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

}
