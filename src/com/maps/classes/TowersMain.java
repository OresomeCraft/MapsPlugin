package com.maps.classes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.maps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.InventoryEvent;
import com.oresomecraft.OresomeBattles.OresomeBattles;
import com.oresomecraft.OresomeBattles.ReadyMapsEvent;

public class TowersMain implements Listener {
    
    OresomeBattlesMaps plugin;
    OresomeBattles Battles;
    public TowersMain(OresomeBattlesMaps pl) {
	plugin = pl;
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
	Battles = (OresomeBattles) Bukkit.getServer().getPluginManager().getPlugin("OresomeBattles");
    }

    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    String name = "towers";
    String fullName = "Towers";
    String creators = "bruuceey and Lyssieloo";
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
	World w = Bukkit.getWorld(name);

	Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
	Location blueSpawn = new Location(w, 761, 128, -72, 51, 0);

	redSpawns.add(redSpawn);
	blueSpawns.add(blueSpawn);
	redSpawns.add(new Location(w, 504, 10, -1142, -135, 0));
	blueSpawns.add(new Location(w, 542, 10, -1142, 134, 0));
	redSpawns.add(new Location(w, 542, 10, -1180, 44, 0));
	blueSpawns.add(new Location(w, 504, 10, -1180, -45, 0));
	redSpawns.add(new Location(w, 472, 16, -1149, 90, 0));
	blueSpawns.add(new Location(w, 464, 16, -1156, -89, 0));
	redSpawns.add(new Location(w, 463, 16, -1176, -91, 0));
	blueSpawns.add(new Location(w, 561, 12, -1189, 89, 0));
	redSpawns.add(new Location(w, 555, 19, -1117, 90, 0));
	blueSpawns.add(new Location(w, 570, 14, -1137, 90, 0));
	redSpawns.add(new Location(w, 565, 11, -1218, 36, 0));
	blueSpawns.add(new Location(w, 493, 16, -1129, -136, 0));
	redSpawns.add(new Location(w, 476, 16, -1119, -89, 0));
	blueSpawns.add(new Location(w, 469, 15, -1212, -89, 0));
	redSpawns.add(new Location(w, 580, 21, -1199, 90, 0));
	blueSpawns.add(new Location(w, 580, 19, -1147, 103, 0));
	redSpawns.add(new Location(w, 491, 13, -1136, -127, 0));
	blueSpawns.add(new Location(w, 555, 12, -1175, 66, 0));
	redSpawns.add(new Location(w, 539, 11, -1231, 12, 0));
	blueSpawns.add(new Location(w, 486, 13, -1188, -54, 0));

	Battles.setRedSpawns(name, redSpawns);
	Battles.setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {
	World w = Bukkit.getWorld(name);

	Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
	Location blueSpawn = new Location(w, 761, 128, -72, 51, 0);

	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, 504, 10, -1142, -135, 0));
	FFASpawns.add(new Location(w, 542, 10, -1142, 134, 0));
	FFASpawns.add(new Location(w, 542, 10, -1180, 44, 0));
	FFASpawns.add(new Location(w, 504, 10, -1180, -45, 0));
	FFASpawns.add(new Location(w, 472, 16, -1149, 90, 0));
	FFASpawns.add(new Location(w, 464, 16, -1156, -89, 0));
	FFASpawns.add(new Location(w, 463, 16, -1176, -91, 0));
	FFASpawns.add(new Location(w, 561, 12, -1189, 89, 0));
	FFASpawns.add(new Location(w, 555, 19, -1117, 90, 0));
	FFASpawns.add(new Location(w, 570, 14, -1137, 90, 0));
	FFASpawns.add(new Location(w, 565, 11, -1218, 36, 0));
	FFASpawns.add(new Location(w, 493, 16, -1129, -136, 0));
	FFASpawns.add(new Location(w, 476, 16, -1119, -89, 0));
	FFASpawns.add(new Location(w, 469, 15, -1212, -89, 0));
	FFASpawns.add(new Location(w, 580, 21, -1199, 90, 0));
	FFASpawns.add(new Location(w, 580, 19, -1147, 103, 0));
	FFASpawns.add(new Location(w, 491, 13, -1136, -127, 0));
	FFASpawns.add(new Location(w, 555, 12, -1175, 66, 0));
	FFASpawns.add(new Location(w, 539, 11, -1231, 12, 0));
	FFASpawns.add(new Location(w, 486, 13, -1188, -54, 0));

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
		
	    p.getInventory().setBoots(IRON_BOOTS);
	    p.getInventory().setLeggings(IRON_PANTS);
	    p.getInventory().setChestplate(IRON_CHESTPLATE);
	    p.getInventory().setHelmet(IRON_HELMET);
	    i.setItem(0, IRON_SWORD);
	    i.setItem(1, BOW);
	    i.setItem(2, ARROWS);
	    i.setItem(3, STEAK);
	    i.setItem(4, HEALTH_POTION);
	    i.setItem(5, EXP);
	    
	}
    }
    
    public void clearSpawns() {
	redSpawns.clear();
	blueSpawns.clear();
	FFASpawns.clear();
    }

    public int x1 = -207;
    public int y1 = 52;
    public int z1 = -1220;

    public int x2 = -38;
    public int y2 = 112;
    public int z2 = -1125;

    // getting the region
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
    
    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
	Entity arrow = event.getEntity();
	World world = Bukkit.getWorld(name);
	if (Battles.activeArena.get(0).equals(name)) {
	    if (arrow instanceof Arrow) {
		world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 8);
	    }
	}

    }

}