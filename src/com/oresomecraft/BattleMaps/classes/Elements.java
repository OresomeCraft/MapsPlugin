package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.BattleMaps.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
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

public class Elements extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Elements(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "elements";
    String fullName = "Elements";
    String creators = "broddikill, koolguydude4 and MiCkEyMiCE";
    Gamemode[] modes = {Gamemode.TDM};
    //Map download link: N/A

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) { // Internal - Do not change
        addMap(name);
        addCreators(name, creators);
        setFullName(name, fullName);
        setGamemodes(name, modes);
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
        redSpawns.add(new Location(w, -23, 87, 11));
        blueSpawns.add(new Location(w, -25, 86, 147));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -23, 87, 11));
        FFASpawns.add(new Location(w, -25, 86, 147));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack LOG = new ItemStack(Material.LOG, 64);
            ItemStack IRON_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
            ItemStack STONE_PICK = new ItemStack(Material.STONE_PICKAXE, 1);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, STONE_SWORD);
            i.setItem(1, STONE_PICK);
            i.setItem(2, BOW);
            i.setItem(3, LOG);
            i.setItem(4, HEALTH);
            i.setItem(11, ARROWS);

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
    public int x1 = -51;
    public int y1 = 74;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 7;
    public int y2 = 171;
    public int z2 = 166;

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

    // Code to prevent block placing outside of map.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {
            if(!contains(loc, x1, x2, y1, y2, z1, z2)){
            event.setCancelled(true);
            }
        }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {
            if(!contains(loc, x1, x2, y1, y2, z1, z2)){
            event.setCancelled(true);
            }
        }
    }
}
