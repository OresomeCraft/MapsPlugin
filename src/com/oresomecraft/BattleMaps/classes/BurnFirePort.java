package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.BattleMaps.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class BurnFirePort extends BattleMap implements IBattleMap, Listener {

	OresomeBattlesMaps plugin;

	public BurnFirePort(OresomeBattlesMaps pl) {
		super(pl);
		plugin = pl;
	}

	public ArrayList<Location> redSpawns = new ArrayList<Location>();
	public ArrayList<Location> blueSpawns = new ArrayList<Location>();
	public ArrayList<Location> FFASpawns = new ArrayList<Location>();

	String name = "burnfireport";
	String fullName = "Burnfire Port";
	String creators = "bumsonfire";
	Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

	// Map download link:
	// http://www.mediafire.com/download/zmq80m8wg11r3h8/Burnfire_Port.rar

	// Direct Map Link:
	// http://205.196.123.120/p6cbc4rorjrg/zmq80m8wg11r3h8/Burnfire+Port.rar

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

		Location redSpawn = new Location(w, -130, 29, 152, 176, 0);
		Location blueSpawn = new Location(w, -52, 37, 41, 90, 0);

		redSpawns.add(redSpawn);
		redSpawns.add(new Location(w, -179, 27, 81, -89, 0));
		redSpawns.add(new Location(w, -149, 28, 93, -88, 0));
		redSpawns.add(new Location(w, -148, 26, 97, 0, 0));
		redSpawns.add(new Location(w, -88, 26, 106, 90, 0));

		blueSpawns.add(blueSpawn);
		blueSpawns.add(new Location(w, -99, 40, 41, 90, 0));
		blueSpawns.add(new Location(w, -11, 33, 62, 45, 0));
		blueSpawns.add(new Location(w, -115, 30, 79, 90, 0));
		blueSpawns.add(new Location(w, -86, 28, 87, 90, 0));

		setRedSpawns(name, redSpawns);
		setBlueSpawns(name, blueSpawns);
	}

	public void readyFFASpawns() {
		World w = Bukkit.getServer().getWorld(name);

		Location redSpawn = new Location(w, -106, 26, 112, 179, 0);
		Location blueSpawn = new Location(w, -50, 32, 71, 0, 0);

		FFASpawns.add(redSpawn);
		FFASpawns.add(blueSpawn);
		FFASpawns.add(new Location(w, -130, 29, 152, 176, 0));
		FFASpawns.add(new Location(w, -52, 37, 41, 90, 0));
		FFASpawns.add(new Location(w, -179, 27, 81, -89, 0));
		FFASpawns.add(new Location(w, -149, 28, 93, -88, 0));
		FFASpawns.add(new Location(w, -148, 26, 97, 0, 0));
		FFASpawns.add(new Location(w, -88, 26, 106, 90, 0));
		FFASpawns.add(new Location(w, -99, 40, 41, 90, 0));
		FFASpawns.add(new Location(w, -11, 33, 62, 45, 0));
		FFASpawns.add(new Location(w, -115, 30, 79, 90, 0));
		FFASpawns.add(new Location(w, -86, 28, 87, 90, 0));

		setFFASpawns(name, FFASpawns);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void applyInventory(InventoryEvent event) {
		String par = event.getMessage();
		Player p = event.getPlayer();
		Inventory i = p.getInventory();
		if (par.equalsIgnoreCase(name)) {
			clearInv(p);

			ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
			ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
			ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE,
					1);
			ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
			ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
			ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1,
					(short) 16373);
			ItemStack BOW = new ItemStack(Material.BOW, 1);
			ItemStack ARROW = new ItemStack(Material.ARROW, 64);
			ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

			p.getInventory().setBoots(IRON_BOOTS);
			p.getInventory().setLeggings(IRON_PANTS);
			p.getInventory().setChestplate(IRON_CHESTPLATE);
			p.getInventory().setHelmet(IRON_HELMET);
			i.setItem(0, IRON_SWORD);
			i.setItem(1, BOW);
			i.setItem(2, HEALTH_POTION);
			i.setItem(3, STEAK);
			i.setItem(10, ARROW);

		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void clearSpawns(ClearSpawnsEvent event) {
		redSpawns.clear();
		blueSpawns.clear();
		FFASpawns.clear();
	}

	public int x1 = -212;
	public int y1 = 77;
	public int z1 = 15;
	public int x2 = -2;
	public int y2 = 1;
	public int z2 = 215;

	// Getting the region
	public boolean contains(Location loc, int x1, int x2, int y1, int y2,
			int z1, int z2) {
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

	// Code to prevent block breaking
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

	// Code to prevent escaping map
	@EventHandler(priority = EventPriority.NORMAL)
	public void protection(PlayerMoveEvent event) {

		if (event.getPlayer().getLocation().getWorld().getName().equals(name)) {
			// Spectator is not exempt from check
			if (!(contains(event.getPlayer().getLocation(), x1, x2, y1, y2, z1,
					z2))) {
				event.setCancelled(true);
			}
		}
	}

}

// Map configured by psgs. Need your map configured? Send an email to
// psgs@psgs.tk
