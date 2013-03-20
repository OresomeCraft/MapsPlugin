package com.maps.classes;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.maps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.InventoryEvent;
import com.oresomecraft.OresomeBattles.OresomeBattles;
import com.oresomecraft.OresomeBattles.ReadyMapsEvent;


public class Perro implements Listener {

    OresomeBattlesMaps plugin;
    OresomeBattles Battles;
    public Perro(OresomeBattlesMaps pl) {
	plugin = pl;
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
	Battles = (OresomeBattles) Bukkit.getServer().getPluginManager().getPlugin("OresomeBattles");
    }
    
    // Spawn locations.
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    String name = "perro";
    String fullName = "Casa de Perro";
    String creators = "zachoz, pegabeavercorn and dogmode555";
    //Map download link: N/A
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
	Battles.addVotes(name);
	clearSpawns();
	readyTDMSpawns();
	readyFFASpawns();
	Battles.addCreators(name, creators); 
	Battles.setFullName(name, fullName);
	arrowParticles();
    }

    public void readyTDMSpawns() {
	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, -1397, 130, -2080, 138, 0);
	Location blueSpawn = new Location(w, -1419, 68, -2137, -1, 0);

	redSpawns.add(redSpawn);
	blueSpawns.add(blueSpawn);
	redSpawns.add(new Location(w, -1421, 139, -2080, 179, 0));
	blueSpawns.add(new Location(w, -1400, 97, -2081, 116, 0));
	redSpawns.add(new Location(w, -1442, 114, -2089, -90, 0));
	blueSpawns.add(new Location(w, -1438, 88, -2093, -90, 0));
	redSpawns.add(new Location(w, -1426, 88, -2107, 93, 0));
	blueSpawns.add(new Location(w, -1398, 82, -2080, 155, 0));
	redSpawns.add(new Location(w, -1424, 79, -2079, -118, 0));
	blueSpawns.add(new Location(w, -1420, 95, -2076, -144, 0));
	redSpawns.add(new Location(w, -1410, 83, -2094, -92, 0));
	blueSpawns.add(new Location(w, -1412, 105, -2107, 39, 0));
	redSpawns.add(new Location(w, -1424, 105, -2093, 90, 0));
	blueSpawns.add(new Location(w, -1407, 120, -2081, 123, 0));
	redSpawns.add(new Location(w, -1396, 113, -2103, 50, 0));
	blueSpawns.add(new Location(w, -1426, 104, -2110, 0, 0));
	redSpawns.add(new Location(w, -1426, 104, -2110, 0, 0));
	blueSpawns.add(new Location(w, -1389, 67, -2137, 35, 0));
	redSpawns.add(new Location(w, -1395, 79, -2101, 90, 0));
	blueSpawns.add(new Location(w, -1439, 129, -2093, -87, 0));
	redSpawns.add(new Location(w, -1410, 114, -2081, -178, 0));
	blueSpawns.add(new Location(w, -1416, 98, -2081, -159, 0));

	Battles.setRedSpawns(name, redSpawns);
	Battles.setBlueSpawns(name, blueSpawns);
    }
    
    public void readyFFASpawns() {
	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, -1397, 130, -2080, 138, 0);
	Location blueSpawn = new Location(w, -1419, 68, -2137, -1, 0);

	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, -1421, 139, -2080, 179, 0));
	FFASpawns.add(new Location(w, -1400, 97, -2081, 116, 0));
	FFASpawns.add(new Location(w, -1442, 114, -2089, -90, 0));
	FFASpawns.add(new Location(w, -1438, 88, -2093, -90, 0));
	FFASpawns.add(new Location(w, -1426, 88, -2107, 93, 0));
	FFASpawns.add(new Location(w, -1398, 82, -2080, 155, 0));
	FFASpawns.add(new Location(w, -1424, 79, -2079, -118, 0));
	FFASpawns.add(new Location(w, -1420, 95, -2076, -144, 0));
	FFASpawns.add(new Location(w, -1410, 83, -2094, -92, 0));
	FFASpawns.add(new Location(w, -1412, 105, -2107, 39, 0));
	FFASpawns.add(new Location(w, -1424, 105, -2093, 90, 0));
	FFASpawns.add(new Location(w, -1407, 120, -2081, 123, 0));
	FFASpawns.add(new Location(w, -1396, 113, -2103, 50, 0));
	FFASpawns.add(new Location(w, -1426, 104, -2110, 0, 0));
	FFASpawns.add(new Location(w, -1426, 104, -2110, 0, 0));
	FFASpawns.add(new Location(w, -1389, 67, -2137, 35, 0));
	FFASpawns.add(new Location(w, -1395, 79, -2101, 90, 0));
	FFASpawns.add(new Location(w, -1439, 129, -2093, -87, 0));
	FFASpawns.add(new Location(w, -1410, 114, -2081, -178, 0));
	FFASpawns.add(new Location(w, -1416, 98, -2081, -159, 0));
	
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
	    ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
	    ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);
	    
	    ItemMeta fishing_rod = FISHING_ROD.getItemMeta();
	    fishing_rod.setDisplayName(ChatColor.BLUE + "Grappling hook");
	    FISHING_ROD.setItemMeta(fishing_rod);

	    p.getInventory().setBoots(IRON_BOOTS);
	    p.getInventory().setLeggings(IRON_PANTS);
	    p.getInventory().setChestplate(IRON_CHESTPLATE);
	    p.getInventory().setHelmet(IRON_HELMET);
	    i.setItem(0, IRON_SWORD);
	    i.setItem(1, BOW);
	    i.setItem(2, FISHING_ROD);
	    i.setItem(3, EXP);
	    i.setItem(4, STEAK);
	    i.setItem(5, HEALTH_POTION);
	    i.setItem(6, ARROWS);
	    p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_FALL, 4);

 	}
     }
    
    public void clearSpawns() {
	redSpawns.clear();
	blueSpawns.clear();
	FFASpawns.clear();
    }
    

    //Region border.
    
   public int x1 = -1451;
   public int y1 = 63;
   public int z1 = -2145;
   public int x2 = -1383;
   public int y2 = 159;
   public int z2 = -2066;

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

    @EventHandler(priority = EventPriority.NORMAL)
    public void glassShot(ProjectileHitEvent event) {
	Entity proj = event.getEntity();
	Location hit = proj.getLocation();
	Block b = hit.getBlock();
	Material mat = b.getType();

	if (contains(hit, x1, x2, y1, y2, z1, z2) == true) {

	    if (proj instanceof Arrow) {

		if (mat == Material.THIN_GLASS) {

		    b.breakNaturally();

		}

	    }

	}

    }
    
    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
	Entity arrow = event.getEntity();
	World world = Bukkit.getWorld(name);
	if (Battles.activeArena.get(0).equals(name)) {
	    if (arrow instanceof Arrow) {
		world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);
	    }
	}

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {

	Block b = event.getBlock();
	Material mat = b.getType();
	Location loc = b.getLocation();

	if (loc.getWorld().getName().equals(name)) {
	    if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

		if (mat == Material.THIN_GLASS) {

		} else {
		    event.setCancelled(true);
		}
	    }
	}

    }
    
    public int particles;
    
    public void arrowParticles() {

	Bukkit.getServer().getScheduler().cancelTask(particles);
	particles = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

	    public void run() {
		World world = Bukkit.getWorld(name);
		if (Battles.activeArena.get(0).equals(name)) {
		    if (!(world.getEntities() == null)) {
			for (Entity arrow : world.getEntities()) {
			    if (arrow instanceof Arrow) {

				world.playEffect(arrow.getLocation(), Effect.SMOKE, 10);
			    }
			}
		    }
		}
	    }

	}, 5L, 5L);
    }
}
