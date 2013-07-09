package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;
import java.util.Random;

import com.oresomecraft.BattleMaps.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;
import com.oresomecraft.OresomeBattles.gamemodes.TDM;

public class Mantle extends BattleMap implements IBattleMap, Listener {

  OresomeBattlesMaps plugin;

	public Mantle(OresomeBattlesMaps pl) {
		super(pl);
		plugin = pl;
	}

	// Spawn lists. (Don't change!)
	public ArrayList<Location> redSpawns = new ArrayList<Location>();
	public ArrayList<Location> blueSpawns = new ArrayList<Location>();
	public ArrayList<Location> FFASpawns = new ArrayList<Location>();

	// Map details
	String name = "mantle";
	String fullName = "The Mantle";
	String creators = "R3creat3, eli12310987, chillhill3, MiCkEyMiCE and FaazM";
	Gamemode[] modes = {Gamemode.CTF, Gamemode.INFECTION};
	//Map download link: N/A

	@EventHandler(priority = EventPriority.NORMAL)
	public void readyMap(ReadyMapsEvent event) { // Internal - Do not change
		addMap(name);
		addCreators(name, creators);
		setFullName(name, fullName);
		setGamemodes(name, modes);
	}

	@EventHandler
	public void setSpawns(WorldLoadEvent event) { // Internal - Do not change
		if (event.getWorld().getName().equals(name)) {
			readyTDMSpawns();
			readyFFASpawns();
		}
	}

	public void readyTDMSpawns() {
		World w = Bukkit.getServer().getWorld(name);

		redSpawns.add(new Location(w, -16, 84, 128));
		blueSpawns.add(new Location(w, -50, 84, 11));

		setRedSpawns(name, redSpawns);
		setBlueSpawns(name, blueSpawns);

		Location redFlag = new Location(w, -35, 86, 121);
		Location blueFlag = new Location(w, -35, 86, 17);
		setCTFFlags(name, redFlag, blueFlag);
	}

	public void readyFFASpawns() {

		World w = Bukkit.getServer().getWorld(name);
		FFASpawns.add(new Location(w, -16, 84, 128));
		FFASpawns.add(new Location(w, -50, 84, 11));

		setFFASpawns(name, FFASpawns);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void applyInventory(InventoryEvent event) {

		String par = event.getMessage();
		Player p = event.getPlayer();
		Inventory i = p.getInventory();
		if (par.equalsIgnoreCase(name)) {
			clearInv(p);

			ItemStack HEALTH_POTION = new ItemStack(Material.GOLDEN_APPLE, 1);
			ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
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
	public int x1 = -85;
	public int y1 = 65;
	public int z1 = 0;

	//Bottom right corner.
	public int x2 = 15;
	public int y2 = 108;
	public int z2 = 138;

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
}
