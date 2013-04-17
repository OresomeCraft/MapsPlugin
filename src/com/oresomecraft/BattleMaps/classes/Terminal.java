/*
 * When submitting the final config, please remove ALL
 * of the current comments! (Including this one!)
 */

/* This is a comment! */
// This is one too!

package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

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
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Terminal extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Terminal(OresomeBattlesMaps pl) {
	super(pl);
	plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "terminal";
    String fullName = "Terminal";
    String creators = "Zachoz, XxXShadowSoul and Slider302";
    //Map download link: N/A

    // Ready map (Don't change!)
    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
	addVotes(name);
	readyTDMSpawns();
	readyFFASpawns();
	addCreators(name, creators); 
	setFullName(name, fullName);
    }

    // Prepare TDM spawns
    public void readyTDMSpawns() {
	World w = Bukkit.getServer().getWorld(name);

	// Define main red spawn point
	Location redSpawn = new Location(w, -72, 71, -1208, -1, 0);

	// Define main blue spawn point
	Location blueSpawn = new Location(w, -116, 66, -1140, -178, 0);

	// Set red's spawn points (See example at the end of the method)
	redSpawns.add(redSpawn);
	redSpawns.add(new Location(w, -143, 66, -1211, -50, 0));
	redSpawns.add(new Location(w, -141, 66, -1142, -152, 0));
	redSpawns.add(new Location(w, -112, 66, -1162, 116, 0));
	redSpawns.add(new Location(w, -101, 71, -1159, -51, 0));
	redSpawns.add(new Location(w, -97, 66, -1171, 156, 0));
	redSpawns.add(new Location(w, -78, 71, -1188, -88, 0));
	redSpawns.add(new Location(w, -48, 71, -1197, 90, 0));
	redSpawns.add(new Location(w, -121, 71, -1190, 121, 0));
	redSpawns.add(new Location(w, -114, 66, -1191, 43, 0));
	redSpawns.add(new Location(w, -58, 71, -1147, 156, 0));

	// Set blue's spawn points (See example at the end of the method)
	blueSpawns.add(blueSpawn);
	blueSpawns.add(new Location(w, -142, 66, -1171, -108, 0));
	blueSpawns.add(new Location(w, -127, 70, -1158, -162, 0));
	blueSpawns.add(new Location(w, -125, 71, -1132, -95, 0));
	blueSpawns.add(new Location(w, -109, 71, -1162, -132, 0));
	blueSpawns.add(new Location(w, -83, 71, -1182, 41, 0));
	blueSpawns.add(new Location(w, -43, 71, -1168, 124, 0));
	blueSpawns.add(new Location(w, -91, 71, -1207, -89, 0));
	blueSpawns.add(new Location(w, -139, 71, -1192, 89, 0));
	blueSpawns.add(new Location(w, -77, 71, -1169, -90, 0));
	blueSpawns.add(new Location(w, -91, 71, -1140, 141, 0));

	// Add spawns to lists. (Don't change!)
	setRedSpawns(name, redSpawns);
	setBlueSpawns(name, blueSpawns);

	/*
	 * Key:
	 * w = world name. (Don't change!)
	 * (w, -143, 100, -121, -100, 0)
	 *       X    Y     Z    YAW 
	 *       
	 * Simply change those values for each spawn point.
	 * The same thing goes for the FFA spawns.
	 * 
	 */
    }

    // Prepare FFA spawns
    public void readyFFASpawns() {

	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, -72, 71, -1208, -1, 0);
	Location blueSpawn = new Location(w, -116, 66, -1140, -178, 0);

	// Set Free for all spawn points.
	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, -143, 66, -1211, -50, 0));
	FFASpawns.add(new Location(w, -142, 66, -1171, -108, 0));
	FFASpawns.add(new Location(w, -141, 66, -1142, -152, 0));
	FFASpawns.add(new Location(w, -127, 70, -1158, -162, 0));
	FFASpawns.add(new Location(w, -112, 66, -1162, 116, 0));
	FFASpawns.add(new Location(w, -125, 71, -1132, -95, 0));
	FFASpawns.add(new Location(w, -101, 71, -1159, -51, 0));
	FFASpawns.add(new Location(w, -109, 71, -1162, -132, 0));
	FFASpawns.add(new Location(w, -97, 66, -1171, 156, 0));
	FFASpawns.add(new Location(w, -83, 71, -1182, 41, 0));
	FFASpawns.add(new Location(w, -78, 71, -1188, -88, 0));
	FFASpawns.add(new Location(w, -43, 71, -1168, 124, 0));
	FFASpawns.add(new Location(w, -48, 71, -1197, 90, 0));
	FFASpawns.add(new Location(w, -91, 71, -1207, -89, 0));
	FFASpawns.add(new Location(w, -121, 71, -1190, 121, 0));
	FFASpawns.add(new Location(w, -139, 71, -1192, 89, 0));
	FFASpawns.add(new Location(w, -114, 66, -1191, 43, 0));
	FFASpawns.add(new Location(w, -77, 71, -1169, -90, 0));
	FFASpawns.add(new Location(w, -58, 71, -1147, 156, 0));
	FFASpawns.add(new Location(w, -91, 71, -1140, 141, 0));

	// Add spawns to list. (Don't change!)
	setFFASpawns(name, FFASpawns);
    }

    // Give player the maps inventory
    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
	// Don't change this stuff!
	String par = event.getMessage();
	Player p = event.getPlayer();
	Inventory i = p.getInventory();
	if (par.equalsIgnoreCase(name)) {
	    clearInv(p);

	    // Define items. (This is fairly straight forward right?)
	    ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
	    ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
	    ItemStack BOW = new ItemStack(Material.BOW, 1);
	    ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
	    ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
	    ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
	    ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
	    ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
	    ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

	    // Set armour. (This is straight forward, right?)
	    p.getInventory().setBoots(IRON_BOOTS);
	    p.getInventory().setLeggings(IRON_PANTS);
	    p.getInventory().setChestplate(IRON_CHESTPLATE);
	    p.getInventory().setHelmet(IRON_HELMET);

	    // Add items into inventory bar.
	    // The numer being the slot number. (Remember: Slot 1 is actually 0)
	    // Second arg is the item being added.
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
    public int x1 = -207;
    public int y1 = 52;
    public int z1 = -1220;

    //Bottom right corner.
    public int x2 = -38;
    public int y2 = 112;
    public int z2 = -1125;

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

	if (loc.getWorld().getName().equals(name)) {

	    event.setCancelled(true);

	}

    }

}
