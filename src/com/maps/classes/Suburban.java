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

import com.maps.BattleMap;
import com.maps.MapInterface;
import com.maps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Suburban extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Suburban(OresomeBattlesMaps pl) {
  super(pl);
	plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "suburban";
    String fullName = "Suburban Complex: An evolution";
    String creators = "R3creat3, zezo268, xannallax33 and kalikakitty";
    //Map download link: N/A

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
	addVotes(name);
	clearSpawns();
	readyTDMSpawns();
	readyFFASpawns();
	addCreators(name, creators); 
	setFullName(name, fullName);
    }

    public void readyTDMSpawns() {
	World w = Bukkit.getServer().getWorld(name);
	Location redSpawn = new Location(w, 135, 40, -482);
	Location blueSpawn = new Location(w, 360, 40, -482);

	redSpawns.add(redSpawn);
	redSpawns.add(new Location(w, 135, 40, -482));

	blueSpawns.add(blueSpawn);
	blueSpawns.add(new Location(w, 360, 40, -482));
	setRedSpawns(name, redSpawns);
	setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

	World w = Bukkit.getServer().getWorld(name);

	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 135, 40, -482);
	Location blueSpawn = new Location(w, 360, 40, -482);

	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, 229, 41, -481));
	FFASpawns.add(new Location(w, 267, 41, -482));
	FFASpawns.add(new Location(w, 286, 47, -482));
	FFASpawns.add(new Location(w, 308, 47, -444));
	FFASpawns.add(new Location(w, 329, 47, -551));
	FFASpawns.add(new Location(w, 436, 58, -482));
	FFASpawns.add(new Location(w, 424, 41, -407));
	FFASpawns.add(new Location(w, 187, 47, -520));
	FFASpawns.add(new Location(w, 165, 47, -413));
	FFASpawns.add(new Location(w, 80, 55, -482));	FFASpawns.add(new Location(w, 84, 41, -399));
	FFASpawns.add(new Location(w, 103, 41, -572));
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
	    ItemStack STONEY_POOS = new ItemStack(Material.STONE, 64);
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
	    i.setItem(5, STONEY_POOS);
	    i.setItem(6, STONEY_POOS);

	}
    }

    public void clearSpawns() {
	redSpawns.clear();
	blueSpawns.clear();
	FFASpawns.clear();
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 525;
    public int y1 = 0;
    public int z1 = 578;
    
    //Bottom right corner.
    public int x2 = -44;
    public int y2 = 232;
    public int z2 = 1136;

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
    @EventHandler(priority = EventPriority.NORMAL)
    public void fishing(PlayerFishEvent event) {
	PlayerFishEvent.State state = event.getState();
	Player p = event.getPlayer();
	ItemStack is = p.getItemInHand();
	Material mat = is.getType();
	Location loc = p.getLocation();

	if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

	    if (mat == Material.FISHING_ROD) {

		if (state == State.IN_GROUND) {
		    p.launchProjectile(Snowball.class);

		}
	    }
	}

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void grapple(ProjectileHitEvent event) {
	Entity proj = event.getEntity();
	Location hit = proj.getLocation();

	if (contains(hit, x1, x2, y1, y2, z1, z2) == true) {

	    if (proj instanceof Snowball) {
		Snowball fish = (Snowball) proj;
		Entity shooter = fish.getShooter();

		if (shooter instanceof Player) {
		    Player p = (Player) shooter;
		    Location loc = p.getLocation();
		    ItemStack is = p.getItemInHand();
		    Material mat = is.getType();

		    if (mat == Material.FISHING_ROD) {

			p.setFallDistance(0);
			p.playSound(loc, Sound.ARROW_HIT, 1, 1);

			int hitx = hit.getBlockX();
			int hity = hit.getBlockY();
			int hitz = hit.getBlockZ();
			int locx = loc.getBlockX();
			int locy = loc.getBlockY();
			int locz = loc.getBlockZ();
			double co[] = new double[3];

			if (hitx > locx) {
			    co[0] = 1.2;
			} else if (hitx < locx) {
			    co[0] = -1.2;
			} else if (hitx == locx) {
			    co[0] = 0;
			}

			if (hity > locy) {
			    co[1] = 1.4;
			} else if (hity < locy) {
			    co[1] = -0.8;
			} else if (hity == locy) {
			    co[1] = 0;
			}

			if (hitz > locz) {
			    co[2] = 1.2;
			} else if (hitz < locz) {
			    co[2] = -1.2;
			} else if (hitz == locz) {
			    co[2] = 0;
			}

			p.setVelocity(new Vector(co[0], co[1], co[2]));

		    }
		}
	    }

	}
    }

}
