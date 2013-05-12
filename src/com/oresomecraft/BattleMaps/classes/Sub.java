package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Sub extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Sub(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "sub";
    String fullName = "Submerged";
    String creators = "eddie017 and Brandon267";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};
    //Map download link: http://bit.ly/Zu3LRs

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
        addMap(name);
        readyTDMSpawns();
        readyFFASpawns();
        addCreators(name, creators); 
        setFullName(name, fullName);
        setGamemodes(name, modes);
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -62, 40, -62, 561, 0);
        Location blueSpawn = new Location(w, -65, 45, 6, 562, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -62, 40, -62, 561, 0));
        redSpawns.add(new Location(w, -65, 36, -92, 585, 0));
        redSpawns.add(new Location(w, -65, 33, 90, 580, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -65, 45, 6, 562, 0));
        blueSpawns.add(new Location(w, -65, 39, 12, 592, 0));
        blueSpawns.add(new Location(w, -65, 28, 10, 583, 0));
        blueSpawns.add(new Location(w, -66, 46, -61, 572, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);


        FFASpawns.add(new Location(w, -62, 40, -62, 561, 0));
        FFASpawns.add(new Location(w, -65, 36, -92, 585, 0));
        FFASpawns.add(new Location(w, -65, 33, 90, 580, 0));
        FFASpawns.add(new Location(w, -65, 45, 6, 562, 0));
        FFASpawns.add(new Location(w, -65, 39, 12, 592, 0));
        FFASpawns.add(new Location(w, -65, 28, 10, 583, 0));
        FFASpawns.add(new Location(w, -66, 46, -61, 572, 0));
        FFASpawns.add(new Location(w, -58, 44, -19, 799, 0));
        FFASpawns.add(new Location(w, -58, 44, -33, 512, 0));
        FFASpawns.add(new Location(w, -62, 44, -40, 566, 0));
        FFASpawns.add(new Location(w, -65, 38, -75, 602, 0));
        FFASpawns.add(new Location(w, -65, 41, -101, 565, 0));
        FFASpawns.add(new Location(w, -65, 40, -92, 766, 0));
        FFASpawns.add(new Location(w, -64, 44, -90, 689, 0));
        FFASpawns.add(new Location(w, -66, 40, -14, 572, 0));
        FFASpawns.add(new Location(w, -66, 36, -14, 572, 0));
        FFASpawns.add(new Location(w, -65, 30, -81, 726, 0));
        FFASpawns.add(new Location(w, -65, 29, -76, 617, 0));
        FFASpawns.add(new Location(w, -65, 32, -52, 559, 0));
        FFASpawns.add(new Location(w, -65, 29, -17, 550, 0));
        FFASpawns.add(new Location(w, -56, 44, 5, 726, 0));

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
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 16);
            ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(DIAMOND_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, EXP);
            i.setItem(5, ARROWS);

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
    public int x1 = -42;
    public int y1 = 59;
    public int z1 = 22;

    //Bottom right corner.
    public int x2 = -87;
    public int y2 = 26;
    public int z2 = -112;

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

    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();
        int mat = b.getTypeId();

        if (loc.getWorld().getName().equals(name)) {
            if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

                if (mat == 42 || mat == 35 || mat == 57 || mat == 43
                        || mat == 49 || mat == 155 || mat == 69 || mat == 101 || mat == 124 || mat == 47 || mat == 116 || mat == 65 || mat == 50 || mat == 89 || mat == 330) {
                    event.setCancelled(true);

                }

            }

        }
    }
}
