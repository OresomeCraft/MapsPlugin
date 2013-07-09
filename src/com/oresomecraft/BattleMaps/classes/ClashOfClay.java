package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.GameUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class ClashOfClay extends BattleMap implements IBattleMap, Listener {

  OresomeBattlesMaps plugin;

	public ClashOfClay(OresomeBattlesMaps pl) {
		super(pl);
		plugin = pl;
	}

	// Spawn lists. (Don't change!)
	public ArrayList<Location> redSpawns = new ArrayList<Location>();
	public ArrayList<Location> blueSpawns = new ArrayList<Location>();
	public ArrayList<Location> FFASpawns = new ArrayList<Location>();

	// Map details
	String name = "clashofclay";
	String fullName = "Clash Of Clay";
	String creators = "_Moist and niceman506";
	Gamemode[] modes = {Gamemode.TDM};
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

		Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
		Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

		redSpawns.add(redSpawn);
		redSpawns.add(new Location(w, -22, 81, 8));

		blueSpawns.add(blueSpawn);
		blueSpawns.add(new Location(w, -22, 81, 164, 2, 178));


		setRedSpawns(name, redSpawns);
		setBlueSpawns(name, blueSpawns);
	}

	public void readyFFASpawns() {

		World w = Bukkit.getServer().getWorld(name);

		Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
		Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);
		FFASpawns.add(blueSpawn);
		FFASpawns.add(redSpawn);
		setFFASpawns(name, FFASpawns);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void applyInventory(InventoryEvent event) {

		String par = event.getMessage();
		Player p = event.getPlayer();
		Inventory i = p.getInventory();
		if (par.equalsIgnoreCase(name)) {
			clearInv(p);

			ItemStack WOODEN_SWORD = new ItemStack(Material.WOOD_SWORD, 1, (short) -16373);
			ItemStack BOW = new ItemStack(Material.BOW, 1);
			ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
			ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
			ItemStack BLUE_STAINED_CLAY = new ItemStack(Material.CLAY, 32);
			ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
			ItemStack TORCH = new ItemStack(Material.TORCH, 16);
			ItemStack ARROW = new ItemStack(Material.ARROW, 1);

			p.getInventory().setChestplate(LEATHER_CHESTPLATE);
			p.getInventory().setHelmet(LEATHER_HELMET);
			BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

			i.setItem(0, WOODEN_SWORD);
			i.setItem(1, BOW);
			i.setItem(2, IRON_PICKAXE);
			i.setItem(3, PUMPKIN_PIE);
			i.setItem(4, BLUE_STAINED_CLAY);
			i.setItem(5, TORCH);
			i.setItem(27, ARROW);

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
			public int x1 = -100;
	public int y1 = 160;
	public int z1 = -70;

	//Bottom right corner.
	public int x2 = -70;
	public int y2 = 30;
	public int z2 = 50;

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
}
