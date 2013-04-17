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
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Skyislands extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Skyislands(OresomeBattlesMaps pl) {
	super(pl);
	plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "skyislands";
    String fullName = "Sky Islands";
    String creators = "tarko2411 and dutchy336";
    //Map download link: http://www.mediafire.com/?vgmk9bhmdi85sqf

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
	addVotes(name);
	readyTDMSpawns();
	readyFFASpawns();
	addCreators(name, creators); 
	setFullName(name, fullName);
	clearSpawns();
    }

    public void readyTDMSpawns() {
	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 738, 170, -1203, 179, 0);
	Location blueSpawn = new Location(w, 743, 170, -1268, 179, 0);

	redSpawns.add(redSpawn);
	redSpawns.add(new Location(w, 734, 170, -1219, 178, 0));
	redSpawns.add(new Location(w, 760, 170, -1218, -177, 0));
	redSpawns.add(new Location(w, 727, 182, -1222, 179, 0));
	redSpawns.add(new Location(w, 742, 209, -1235, -179, 0));

	blueSpawns.add(blueSpawn);
	blueSpawns.add(new Location(w, 738.99908, 170, -1203.43783, 179, 0));
	blueSpawns.add(new Location(w, 734, 170, -1255, 0, 0));
	blueSpawns.add(new Location(w, 771, 182, -1255, 5, 0));
	blueSpawns.add(new Location(w, 742, 209, -1241, 0, 0));        

	setRedSpawns(name, redSpawns);
	setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 738.99908, 170, -1203.43783, 179, 0);
	Location blueSpawn = new Location(w, 743, 170, -1268, 0, 0);

	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, 771, 182, -1255, 5, 0));
	FFASpawns.add(new Location(w, 180, 180, -1262, 92, 0));
	FFASpawns.add(new Location(w, 755, 176, -1194, -176, 0));
	FFASpawns.add(new Location(w, 728, 182, -1223, -86, 0));
	FFASpawns.add(new Location(w, 771, 190, -1233, 2, 0));
	FFASpawns.add(new Location(w, 736, 200, -1235, 2, 0));
	FFASpawns.add(new Location(w, 743, 177, -1236, 0, 0));
	FFASpawns.add(new Location(w, 746, 188, -1239, 39, 0));
	FFASpawns.add(new Location(w, 741, 199, -1227, -4, 0));
	FFASpawns.add(new Location(w, 748, 171, -1227, -179, 0));
	FFASpawns.add(new Location(w, 748, 193, -1230, -179, 0));
	FFASpawns.add(new Location(w, 721, 170, -1234, 1, 0));
	FFASpawns.add(new Location(w, 719, 179, -1225, -89, 0));
	FFASpawns.add(new Location(w, 710, 170, -1256, -88, 0));
	FFASpawns.add(new Location(w, 778, 170, -1239, 92, 0));
	FFASpawns.add(new Location(w, 750, 198, -1233, -88, 0));
	FFASpawns.add(new Location(w, 742, 182, -1237, -179, 0));
	FFASpawns.add(new Location(w, 764, 177, -1233, -179, 0));
	FFASpawns.add(new Location(w, 731, 178, -1240, -1, 0));

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
	    i.setItem(2, STEAK);
	    i.setItem(3, HEALTH_POTION);
	    i.setItem(4, ARROWS);
	    i.setItem(5, EXP);

	}
    }

    public void clearSpawns() {
	redSpawns.clear();
	blueSpawns.clear();
	FFASpawns.clear();
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 694;
    public int y1 = 170;
    public int z1 = -1185;

    //Bottom right corner.
    public int x2 = 786;
    public int y2 = 170;
    public int z2 = -1283;

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