package com.maps.classes;

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

import com.maps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.InventoryEvent;
import com.oresomecraft.OresomeBattles.OresomeBattles;
import com.oresomecraft.OresomeBattles.ReadyMapsEvent;

public class Fosscrest implements Listener {

    OresomeBattlesMaps plugin;
    OresomeBattles Battles;
    public Fosscrest(OresomeBattlesMaps pl) {
	plugin = pl;
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
	Battles = (OresomeBattles) Bukkit.getServer().getPluginManager().getPlugin("OresomeBattles");
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "fosscrest";
    String fullName = "Fosscrest Village";
    String creators = "R3creat3, danielschroeder, xXJazzerXx and zachoz";
    //Map download link: N/A

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
	Battles.addVotes(name);
	clearSpawns();
	readyTDMSpawns();
	readyFFASpawns();
	Battles.addCreators(name, creators); 
	Battles.setFullName(name, fullName);
    }

    public void readyTDMSpawns() {
	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 186, 112, -1293);
	Location blueSpawn = new Location(w, 185, 112, -1288);

	redSpawns.add(redSpawn);
	redSpawns.add(new Location(w, -186, 112, -1293));
	redSpawns.add(new Location(w, 241, 120, -1310));

	blueSpawns.add(blueSpawn);
	blueSpawns.add(new Location(w, 236, 94, -1325));
	blueSpawns.add(new Location(w, 235, 101, -1312));

	Battles.setRedSpawns(name, redSpawns);
	Battles.setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 186, 112, -1293);
	Location blueSpawn = new Location(w, 185, 112, -1288);

	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, 212, 131, -1274));
	FFASpawns.add(new Location(w, 237, 120, -1263 ));
	FFASpawns.add(new Location(w, 261, 116, -1294));
	FFASpawns.add(new Location(w, 241, 120, -1310));
	FFASpawns.add(new Location(w, 187, 112, -1269));
	FFASpawns.add(new Location(w, 219, 101, -1306));
	FFASpawns.add(new Location(w, 219, 101, -1306));
	FFASpawns.add(new Location(w, 244, 99, -1289));
	FFASpawns.add(new Location(w, 198, 113, -1288));
	FFASpawns.add(new Location(w, 187, 119, -1267));

	Battles.setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

	String par = event.getMessage();
	Player p = event.getPlayer();
	Inventory i = p.getInventory();
	if (par.equalsIgnoreCase(name)) {
	    Battles.utility.clearInv(p);

	    ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
	    ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
	    ItemStack BOW = new ItemStack(Material.BOW, 1);
	    ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
	    ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
	    ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
	    ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
	    ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
	    ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

	    p.getInventory().setBoots(IRON_BOOTS);
	    p.getInventory().setLeggings(IRON_PANTS);
	    p.getInventory().setChestplate(IRON_CHESTPLATE);
	    p.getInventory().setHelmet(IRON_HELMET);

	    i.setItem(0, IRON_SWORD);
	    i.setItem(1, BOW);
	    i.setItem(2, STEAK);
	    i.setItem(3, HEALTH_POTION);
	    i.setItem(4, ARROWS);

	}
    }

    public void clearSpawns() {
	redSpawns.clear();
	blueSpawns.clear();
	FFASpawns.clear();
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 86;
    public int y1 = 229;
    public int z1 = -1396;

    //Bottom right corner.
    public int x2 = 353;
    public int y2 = 73;
    public int z2 = -1187;

    // Getting the region
    public static boolean contains(Location loc, int x1, int x2, int y1,
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


    /*
     * Custom effects/items code goes here! This is also the place where you can
     * have code that says what blocks can and can't be broken.
     * 
     * Usually you should just leave this to Zachoz and pegabeavercorn to do and
     * leave them you suggestions via a comment. ("// Text here")
     * 
     * If you have experience in Java and the Bukkit API feel free to write your own!
     */

    // Code for disabling block breaking:

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
//Ruv you zarch - R3