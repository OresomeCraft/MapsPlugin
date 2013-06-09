package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;
import java.util.List;

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

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Relation extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;

    public Relation(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "relation";
    String fullName = "Relation";
    String creators = "ShaunDepro97";
    Gamemode[] modes = {Gamemode.FFA};
    //Map download link: http://dc194.2shared.com/download/SRg4KJD4/Relation.zip?tsid=20130608-064215-6919dc08

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
        addMap(name);
        readyTDMSpawns();
        readyFFASpawns();
        setGamemodes(name, modes);
        addCreators(name, creators);
        setFullName(name, fullName);
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 64, 76, 64, 2, 0);
        Location blueSpawn = new Location(w, 64, 76, 64, 0, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 64, 92, 64, 2, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 64, 92, -64, 0, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 64, 76, 64, 2, 0);
        Location blueSpawn = new Location(w, 64, 76, 64, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 57, 84, 65, 2, 0));
        FFASpawns.add(new Location(w, 65, 84, 56, 2, 0));
        FFASpawns.add(new Location(w, 73, 84, 65, 2, 0));
        FFASpawns.add(new Location(w, 64, 84, 73, 2, 0));
        FFASpawns.add(new Location(w, 57, 76, 65, 2, 0));
        FFASpawns.add(new Location(w, 65, 76, 56, 2, 0));
        FFASpawns.add(new Location(w, 73, 76, 65, 2, 0));
        FFASpawns.add(new Location(w, 64, 76, 73, 2, 0));
        FFASpawns.add(new Location(w, 64, 92, 72, 2, 0));
        FFASpawns.add(new Location(w, 64, 92, 56, 2, 0));
        FFASpawns.add(new Location(w, 47, 88, 47, 2, 0));
        FFASpawns.add(new Location(w, 82, 88, 82, 2, 0));
        FFASpawns.add(new Location(w, 82, 80, 47, 2, 0));
        FFASpawns.add(new Location(w, 47, 80, 82, 2, 0));

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

    @EventHandler(priority = EventPriority.NORMAL)
    public void clearSpawns(ClearSpawnsEvent event) {
        redSpawns.clear();
        blueSpawns.clear();
        FFASpawns.clear();
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 92;
    public int y1 = 256;
    public int z1 = 92;

    //Bottom right corner.
    public int x2 = 38;
    public int y2 = 1;
    public int z2 = 38;

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

    @EventHandler
    public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player p = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        for (ItemStack item : drops) {
            Material mat = item.getType();

            if (mat == Material.IRON_SWORD
                    || mat == Material.BOW
                    || mat == Material.IRON_BOOTS
                    || mat == Material.IRON_LEGGINGS
                    || mat == Material.IRON_CHESTPLATE
                    || mat == Material.ARROW) {

                item.setType(Material.AIR);
            }
        }
    }

}
