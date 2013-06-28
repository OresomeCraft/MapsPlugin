package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.OresomeBattles.BattleHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Zoned extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;

    public Zoned(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "zoned";
    String fullName = "Zoned";
    String creators = "R3creat3, MiCkEyMiCe and _Moist";
    Gamemode[] modes = {Gamemode.CTF, Gamemode.INFECTION};
    //Map download link: N/A

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
        redSpawns.add(new Location(w, -2, 87, 68));
        blueSpawns.add(new Location(w, -2, 87, -45));
        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

        Location blueFlag = new Location(w, -3, 89, -56);
        Location redFlag = new Location(w, -3, 89, -56);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, -2, 87, 68));
        FFASpawns.add(new Location(w, -2, 89, -78));
        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
            ItemStack JUMP = new ItemStack(Material.FIREWORK, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 128);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack WEAPON = new ItemStack(Material.IRON_SWORD, 1);
            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, WEAPON);
            i.setItem(1, BOW);
            i.setItem(2, JUMP);
            i.setItem(3, STEAK);
            i.setItem(4, HEALTH);
            i.setItem(10, ARROWS);

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
    public int x1 = 158;
    public int y1 = 139;
    public int z1 = -160;
    //Bottom right corner.
    public int x2 = -171;
    public int y2 = 54;
    public int z2 = 156;

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

    // Code to prevent block placing.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (BattleHandler.activeArena.equals(name)) {
            Block b = event.getBlock();
            Location loc = b.getLocation();

            if (loc.getWorld().getName().equals(name)) {

                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onBlockPlace(PlayerInteractEvent event) {
        if (BattleHandler.activeArena.equals(name)) {
            Player p = event.getPlayer();
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (p.getItemInHand().getType() == Material.FIREWORK) {
                    p.getInventory().removeItem(new ItemStack(Material.FIREWORK, 1));
                    p.setVelocity(new Vector(0, 4, 0));
                }
            }
        }
    }
}
