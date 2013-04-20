package com.maps.classes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.maps.BattleMap;
import com.maps.MapInterface;
import com.maps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class TheShaft extends BattleMap implements MapInterface, Listener {

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
	String name = "theshaft";
	String fullName = "The Shaft";
	String creators = "DynaDavidson, _Moist and R3creat3";
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

		redSpawns.add(new Location(w, -10, 71, 57));
		redSpawns.add(new Location(w, -42, 71, 78));
		redSpawns.add(new Location(w, -60, 71, 73));
		redSpawns.add(new Location(w, -76, 71, 54));
		blueSpawns.add(new Location(w,-77, 71, -1));
		blueSpawns.add(new Location(w,-59, 71, 3));
		blueSpawns.add(new Location(w,-35, 71, 11));
		blueSpawns.add(new Location(w,-19, 71, 11));
		setRedSpawns(name, redSpawns);
		setBlueSpawns(name, blueSpawns);
	}

	public void readyFFASpawns() {

		World w = Bukkit.getServer().getWorld(name);

		FFASpawns.add(new Location(w, -10, 71, 57));
		FFASpawns.add(new Location(w, -42, 71, 78));
		FFASpawns.add(new Location(w, -60, 71, 73));
		FFASpawns.add(new Location(w, -76, 71, 54));
		FFASpawns.add(new Location(w,-77, 71, -1));
		FFASpawns.add(new Location(w,-59, 71, 3));
		FFASpawns.add(new Location(w,-35, 71, 11));
		FFASpawns.add(new Location(w,-19, 71, 11));
		FFASpawns.add(new Location(w,-29, 71, 31));
		FFASpawns.add(new Location(w,-39, 71, 48));
		FFASpawns.add(new Location(w,-63, 71, 36));
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
			ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
			ItemStack BOW = new ItemStack(Material.BOW, 1);
			ItemStack ARROWS = new ItemStack(Material.ARROW, 128);
			ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
			ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
			ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
			ItemStack WEAPON = new ItemStack(Material.DIAMOND_PICKAXE, 1);
			ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
			ItemStack TNT = new ItemStack(Material.CLAY_BRICK, 1);
			WEAPON.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
			p.getInventory().setBoots(IRON_BOOTS);
			p.getInventory().setLeggings(IRON_PANTS);
			p.getInventory().setChestplate(IRON_CHESTPLATE);
			p.getInventory().setHelmet(IRON_HELMET);

			i.setItem(0, WEAPON);
			i.setItem(1, BOW);
			i.setItem(2, STEAK);
			i.setItem(3, EXP);
			i.setItem(4, TNT);
			i.setItem(5, HEALTH_POTION);
			i.setItem(8, ARROWS);

		}
	}

	public void clearSpawns() {
		redSpawns.clear();
		blueSpawns.clear();
		FFASpawns.clear();
	}

	// Region. (Top corner block and bottom corner block.
	// Top left corner.
	public int x1 = -89;
	public int y1 = 104;
	public int z1 = -9;
	//Bottom right corner.
	public int x2 = -2;
	public int y2 = 64;
	public int z2 = 84;
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
		Player player = event.getPlayer();
		if (BattleHandler.activeArena.equals(name)) {
			if(!contains(player.getLocation(), x1, y1, z1, x2, y2, z2)){
				//No building off the map bitch! :D
				return;
			}
			Block b = event.getBlock();
			Location loc = b.getLocation();

			if (loc.getWorld().getName().equals(name)) {

				event.setCancelled(true);
			}
		}

	}

	// Code to prevent block placing.
	@EventHandler(priority = EventPriority.NORMAL)
	public void protection1(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (BattleHandler.activeArena.equals(name)) {
			if(!contains(player.getLocation(), x1, y1, z1, x2, y2, z2)){
				//No building off the map bitch! :D
				return;
			}
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
			EntityType tnt = EntityType.PRIMED_TNT;
			Player p = event.getPlayer();
			if(p.getItemInHand().getType() == Material.CLAY_BRICK){
				p.getInventory().removeItem(new ItemStack(Material.CLAY_BRICK, 1));
				p.getWorld().spawnEntity(p.getLocation(), tnt);
			}

		}
	}
}