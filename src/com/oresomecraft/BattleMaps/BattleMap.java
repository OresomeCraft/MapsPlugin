package com.oresomecraft.BattleMaps;

import java.util.ArrayList;
import java.util.Arrays;

import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.ItemStack;

public abstract class BattleMap implements Listener {

	public OresomeBattlesMaps plugin = OresomeBattlesMaps.getInstance();
	public BattleMap config;

	public BattleMap() {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * ***************************************************************
	 * Map configuration variables and methods                       *
	 * ***************************************************************
	 */

	// Spawn lists. Inherited by class configs
	public ArrayList<Location> redSpawns = new ArrayList<Location>();
	public ArrayList<Location> blueSpawns = new ArrayList<Location>();
	public ArrayList<Location> FFASpawns = new ArrayList<Location>();

	private boolean allowBuild = true;
	private Material[] disabledDrops;
	private boolean pearlDamage = true;
	private Long timeLock;

	public World w;

	// Map details
	String name;
	String fullName;
	String creators;
	Gamemode[] modes;

	/**
	 * Readies maps to be played
	 *
	 * @param event An Event called by OresomeBattles
	 */
	@EventHandler // Add map
	public void readyMap(ReadyMapsEvent event) {
		addMap(name);
		setGamemodes(name, modes);
		addCreators(name, creators);
		setFullName(name, fullName);
	}

	/**
	 * Sets the spawn points for a map once its world has loaded
	 *
	 * @param event A WorldLoadEvent called by the server
	 */
	@EventHandler // Set the spawns
	public void setSpawns(WorldLoadEvent event) { // Internal - Do not change
		if (event.getWorld().getName().equals(name)) {
			this.w = event.getWorld();
			config.readyTDMSpawns();
			config.readyFFASpawns();
			setFFASpawns(name, FFASpawns);
			setRedSpawns(name, redSpawns);
			setBlueSpawns(name, blueSpawns);

			if (timeLock != null) startTimeLock();
		}
	}

	@EventHandler
	public void worldUnload(WorldUnloadEvent event) {
		this.w = null;
		Bukkit.getScheduler().cancelTask(timeLockScheduler);
	}

	/**
	 * Sets details for the map after initiation
	 *
	 * @param config   A BattleMap
	 * @param name     (Shortened) Name of map
	 * @param fullName Full name of the map
	 * @param creators Creators of the map
	 * @param modes    Gamemodes supported by thr map
	 */
	protected final void initiate(BattleMap config, String name, String fullName, String creators, Gamemode[] modes) {
		this.config = config;
		this.name = name;
		this.fullName = fullName;
		this.creators = creators;
		this.modes = modes;
	}

	/**
	 * Clears spawns for a map
	 *
	 * @param event Event called by the server
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void clearSpawns(ClearSpawnsEvent event) {
		redSpawns.clear();
		blueSpawns.clear();
		FFASpawns.clear();
	}

	/**
	 * Set the amount of time a TDM battle will go for
	 *
	 * @param time Time battle will go for in minutes
	 */
	public void setTDMTime(int time) {
		BattlesAccess.setTDMTime(name, time);
	}

	/**
	 * Set the amount of time a CP battle will go for
	 *
	 * @param time Time battle will go for in minutes
	 */
	public void setCPTime(int time) {
		BattlesAccess.setCPTime(name, time);
	}

	/**
	 * Sets location for King of the Hill monument
	 *
	 * @param monument Location of the monument
	 */
	public void setKoTHMonument(Location monument) {
		BattlesAccess.setKoTHMonument(monument);
	}

	/**
	 * Sets whether or not player's can build on the map
	 *
	 * @param allow Whether or not player's can build on the map
	 */
	public void setAllowBuild(boolean allow) {
		allowBuild = allow;
	}

	/**
	 * Disables certain items from being dropped on death
	 *
	 * @param items An ItemStack array of items not to drop
	 */
	public void disableDrops(Material[] items) {
		disabledDrops = items;
	}

	/**
	 * Prevents damage when moving using enderpearls
	 *
	 * @param allow Whether damage from enderpearls should be disabled or not
	 */
	public void disablePearlDamage(boolean allow) {
		pearlDamage = allow;
	}

	/**
	 * Sets a time lock on the map
	 *
	 * @param time The time to lock the map to. ("day", "dawn", "night")
	 */
	public void lockTime(String time) {
		if (time.equalsIgnoreCase("day")) timeLock = 0L;
		else if (time.equalsIgnoreCase("dawn")) timeLock = 23000L;
		else if (time.equalsIgnoreCase("night")) timeLock = 14000L;
		else if (time.equalsIgnoreCase("dusk")) timeLock = 13000L;
		else if (time.equalsIgnoreCase("midday")) timeLock = 5000L;
		else if (time.equalsIgnoreCase("midnight")) timeLock = 18000L;
	}

	/**
	 * Sets a time lock on the map
	 *
	 * @param time The time in ticks
	 */
	public void lockTime(long time) {
		this.timeLock = time;
	}

	/**
	 * Sets the capture points for a map
	 *
	 * @param monuments a Monument array
	 */
	public void setCapturePoints(Monument[] monuments) {
		BattlesAccess.setCapturePoints(monuments);
	}

	/**
	 * Sets TDM and CTF spawn points
	 */
	public abstract void readyTDMSpawns();

	/**
	 * Sets FFA and Infection spawn points (also used for spectators)
	 */
	public abstract void readyFFASpawns();

	/**
	 * Applies the map's inventory to a player
	 *
	 * @param p a BattlePlayer
	 */
	public abstract void applyInventory(BattlePlayer p);

	/**
	 * Returns if map is currently being played
	 */
	public boolean active = BattlesAccess.getArena().equals(name); // Whether or not map is currently being played

	/**
	 * Prevents block breaking if disabled by the map
	 *
	 * @param event an Event called by the server
	 */
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getBlock().getWorld().getName().equals(name) && !allowBuild) event.setCancelled(true);
	}

	/**
	 * Prevents block placing if disabled by the map
	 *
	 * @param event an Event called by the server
	 */
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlock().getWorld().getName().equals(name) && !allowBuild) event.setCancelled(true);
	}

	/**
	 * Disables dropping of certain items if declared
	 *
	 * @param event an Event called by the server
	 */
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if (event.getEntity().getWorld().getName().equals(name)) {
			for (ItemStack item : event.getDrops())
				if (disabledDrops != null && Arrays.asList(disabledDrops).contains(item.getType()))
					item.setType(Material.AIR);
		}

	}

	/**
	 * Disables damage caused by enderpearls if disabled by the map
	 *
	 * @param event an Event called by the server
	 */
	@EventHandler
	public void onPearlDamage(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		TeleportCause cause = event.getCause();
		Location destination = event.getTo();

		if (!BattlePlayer.getBattlePlayer(player).isSpectator()) {
			if (event.getPlayer().getLocation().getWorld().getName().equals(name)) {
				if (pearlDamage) {
					if (cause == TeleportCause.ENDER_PEARL) {
						player.teleport(destination);
						event.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void applyInventory(InventoryEvent event) {
		if (event.getMessage().equals(name)) {
			clearInv(event.getPlayer());
			config.applyInventory(event.getPlayer());
		}
	}

	int timeLockScheduler;

	public void startTimeLock() {
		timeLockScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if (Bukkit.getWorld(name) != null) {
					Bukkit.getWorld(name).setTime(timeLock);
				} else {
					Bukkit.getScheduler().cancelTask(timeLockScheduler);
				}
			}
		}, 100L, 100L);
	}

	/**
	 * ***************************************************************
	 * Methods to interact with OresomeBattles                       *
	 * ***************************************************************
	 */

	public void setRedSpawns(String name, ArrayList<Location> redSpawns) {
		BattlesAccess.setRedSpawns(name, redSpawns);
	}

	public void setBlueSpawns(String name, ArrayList<Location> blueSpawns) {
		BattlesAccess.setBlueSpawns(name, blueSpawns);
	}

	public void setFFASpawns(String name, ArrayList<Location> FFASpawns) {
		BattlesAccess.setFFASpawns(name, FFASpawns);
	}

	public void addMap(String name) {
		BattlesAccess.addMap(name);
	}

	public void addCreators(String name, String creators) {
		BattlesAccess.addCreators(name, creators);
	}

	public void setFullName(String name, String fullName) {
		BattlesAccess.setFullName(name, fullName);
	}

	public void clearInv(Player p) {
		BattlesAccess.clearInv(p);
	}

	public void setGamemodes(String name, Gamemode[] modes) {
		BattlesAccess.setGamemodes(name, modes);
	}

	public void setCTFFlags(String name, Location redFlag, Location blueFlag) {
		BattlesAccess.setCTFFlags(name, redFlag, blueFlag);
	}

	/**
	 * ***************************************************************
	 * Useful methods easily useable by all maps                     *
	 * ***************************************************************
	 */

	public String getArena() {
		return BattlesAccess.getArena();
	}

	public Gamemode getMode() {
		return BattlesAccess.getMode();
	}

	public boolean compareLocations(Location loc1, Location loc2) {
		return BattlesAccess.compareLocations(loc1, loc2);
	}

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