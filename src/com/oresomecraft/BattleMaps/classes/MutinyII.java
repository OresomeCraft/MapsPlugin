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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class MutinyII extends BattleMap implements MapInterface, Listener {

  OresomeBattlesMaps plugin;
	public MutinyII(OresomeBattlesMaps pl) {
		super(pl);
		plugin = pl;
	}

	// Spawn lists. (Don't change!)
	public ArrayList<Location> redSpawns = new ArrayList<Location>();
	public ArrayList<Location> blueSpawns = new ArrayList<Location>();
	public ArrayList<Location> FFASpawns = new ArrayList<Location>();

	// Map details
	String name = "mutinyII";
	String fullName = "Mutiny II";
	String creators = "R3creat3, zachoz and Buster1824";
	Gamemode[] modes = {Gamemode.TDM};
	//Map download link: N/A

	@EventHandler(priority = EventPriority.NORMAL)
	public void readyMap(ReadyMapsEvent event) {
		addMap(name);
		readyTDMSpawns();
		readyFFASpawns();
		addCreators(name, creators); 
		setFullName(name, fullName);
	}

	public void readyTDMSpawns() {
		World w = Bukkit.getServer().getWorld(name);

		Location redSpawn = new Location(w, -18, 85, -52);
		Location blueSpawn = new Location(w, -18, 85, 13);

		redSpawns.add(redSpawn);
		blueSpawns.add(blueSpawn);
		
		setRedSpawns(name, redSpawns);
		setBlueSpawns(name, blueSpawns);
	}

	public void readyFFASpawns() {

		World w = Bukkit.getServer().getWorld(name);

		Location obsSpawn = new Location(w, 7, 1122, -19);
		FFASpawns.add(obsSpawn);

		setFFASpawns(name, FFASpawns);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void applyInventory(InventoryEvent event) {

		String par = event.getMessage();
		Player p = event.getPlayer();
		Inventory i = p.getInventory();
		if (par.equalsIgnoreCase(name)) {
			clearInv(p);
			//Give players invincibility for 15 seconds when they spawn.
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 15*20, 1));
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
	public int x1 = 52;
	public int y1 = 54;
	public int z1 = 35;

	//Bottom right corner.
	public int x2 = -48;
	public int y2 = 156;
	public int z2 = -75;

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
	public void protection1(BlockBreakEvent event) {

		Block b = event.getBlock();
		Location loc = b.getLocation();

		if (loc.getWorld().getName().equals(name)) {
			if(!(contains(loc, x1, x2, y1, y2, z1, z2))){

				event.setCancelled(true);
			}
		}

	}

	// Code to prevent block placing.
	@EventHandler(priority = EventPriority.NORMAL)
	public void protection1(BlockPlaceEvent event) {

		Block b = event.getBlock();
		Location loc = b.getLocation();

		if (loc.getWorld().getName().equals(name)) {
			if(!(contains(loc, x1, x2, y1, y2, z1, z2))){

				event.setCancelled(true);
			}
		}

	}
}
