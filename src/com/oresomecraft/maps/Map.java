package com.oresomecraft.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.BattlesAccess;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.api.events.InventoryEvent;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Map implements Listener {

    public MapsPlugin plugin = MapsPlugin.getInstance();
    public Map config;

    public Map() {
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

    // Map attributes
    private boolean allowBuild = true;
    private boolean allowPlace = true;
    private Material[] disabledDrops;
    private boolean pearlDamage = true;
    private Long timeLock;
    private boolean autoSpawnProtection;
    private int spawnProtectionDuration;
    private int blockLimit = 256;
    private boolean fireSpread = true;
    protected int tdmTime = 15;
    public Location ctfRedFlag, ctfBlueFlag, kothFlag;
    public CuboidRegion criminalTester;

    public World w; // World variable

    // Map details
    public String name;
    private String fullName;
    private String creators;
    private Gamemode[] modes;

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getCreators() {
        return this.creators;
    }

    public Gamemode[] getModes() {
        return this.modes;
    }

    public Location getSpectatorSpawn() {
        Random spawns = new Random();
        int index = spawns.nextInt(FFASpawns.size());
        return FFASpawns.get(index);
    }

    /**
     * Sets the spawn points for a map once it's world has loaded
     *
     * @param event A WorldLoadEvent called by the server
     */
    @EventHandler // Set the spawns
    public void setSpawns(WorldLoadEvent event) { // Internal - Do not change
        if (event.getWorld().getName().equals(name)) {
            this.w = event.getWorld();
            if (this.config instanceof BattleMap) {
                ((BattleMap) config).readyTDMSpawns();
            }

            config.readyFFASpawns();

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
     * @param modes    Gamemodes supported by the map
     */
    protected final void initiate(Map config, String name, String fullName, String creators, Gamemode[] modes) {
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
        ctfBlueFlag = null;
        ctfRedFlag = null;
        kothFlag = null;
        criminalTester = null;
    }

    /**
     * Sets whether or not players can build on the map
     *
     * @param allow Whether or not player's can build on the map
     */
    public void setAllowBuild(boolean allow) {
        allowBuild = allow;
    }

    public void setCriminalTester(CuboidRegion region) {
        this.criminalTester = region;
    }

    /**
     * Sets auto spawn protection for players
     *
     * @param duration How long it lasts for
     */
    public void setAutoSpawnProtection(int duration) {
        autoSpawnProtection = true;
        spawnProtectionDuration = duration;
    }

    /**
     * Sets whether or not players can place blocks on the map
     *
     * @param allow Whether or not player's can place blocks on the map
     */
    public void setAllowPlace(boolean allow) {
        allowPlace = allow;
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
     * Disables fire spread
     *
     * @param allow Whether or not fire spreads and catches
     */
    public void setFireSpread(boolean allow) {
        fireSpread = allow;
    }

    /**
     * Disables block interaction above a certain height
     *
     * @param limit The block limit in Y
     */
    public void setBuildLimit(int limit) {
        blockLimit = limit;
    }

    /**
     * Prevents damage when moving using enderpearls
     *
     * @param allow Whether damage from enderpearls should be disabled or not
     */
    public void disablePearlDamage(boolean allow) {
        pearlDamage = allow;
    }

    public int getTdmTime() {
        return this.tdmTime;
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
     * Also prevents block breaking above the height limit
     *
     * @param event an Event called by the server
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equals(name) && !allowBuild) event.setCancelled(true);
        if (event.getBlock().getWorld().getName().equals(name) && event.getBlock().getY() > blockLimit)
            event.setCancelled(true);
    }

    /**
     * Prevents block placing if disabled by the map
     * Also prevents block placing above the height limit
     *
     * @param event an Event called by the server
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(name) && !allowBuild) event.setCancelled(true);
        if (event.getBlock().getWorld().getName().equals(name) && !allowPlace) event.setCancelled(true);
        if (event.getBlock().getWorld().getName().equals(name) && event.getBlock().getY() > blockLimit)
            event.setCancelled(true);

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
                        player.setFallDistance(0);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    /**
     * Disables blocks catching on fire if disabled by the map
     *
     * @param event an Event called by the server
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void preventburn(BlockBurnEvent event) {
        if (fireSpread) return;
        if (event.getBlock().getWorld().getName().equals(name)) event.setCancelled(true);
    }

    /**
     * Disables fire spread if disabled by the map
     *
     * @param event an Event called by the server
     */
    @EventHandler
    public void preventspread(BlockSpreadEvent event) {
        if (fireSpread) return;
        if (event.getBlock().getWorld().getName().equals(name))
            if ((event.getBlock().getTypeId() != 2) || (event.getBlock().getTypeId() != 3)) event.setCancelled(true);
    }

    /**
     * Applies map inventory to a BattlePlayer
     *
     * @param event An event called by OresomeBattles
     */
    @EventHandler
    public void applyInventory(InventoryEvent event) {
        if (getArena().equals(name)) {
            if (autoSpawnProtection) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, spawnProtectionDuration * 20, 1));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, spawnProtectionDuration * 20, 1));
            }
            clearInv(event.getPlayer());
            config.applyInventory(event.getPlayer());
        }
    }

    int timeLockScheduler;

    /**
     * Starts time lock timer
     */
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

    public void clearInv(Player p) {
        BattlesAccess.clearInv(p);
    }


    public void setCTFFlags(String name, Location redFlag, Location blueFlag) {
        this.ctfRedFlag = redFlag;
        this.ctfBlueFlag = blueFlag;
    }

    /**
     * ***************************************************************
     * Useful methods easily use-able by all maps                     *
     * ***************************************************************
     */

    /**
     * Gets the current map/arena being played.
     *
     * @return Current map/arena being played.
     */
    public String getArena() {
        return BattlesAccess.getArena();
    }

    /**
     * Gets the current game mode being played
     *
     * @return Current game mode being played.
     */
    public Gamemode getMode() {
        return BattlesAccess.getMode();
    }

    /**
     * Compares two Location objects to see if they share the same co-ordinates
     *
     * @param loc1 A Location
     * @param loc2 A Location
     * @return True if coordinates are the same for both Location objects.
     */
    public boolean compareLocations(Location loc1, Location loc2) {
        return BattlesAccess.compareLocations(loc1, loc2);
    }

    /**
     * Checks if a Location's coordinates is in between a certain co-ord range
     *
     * @param loc A Location
     * @param x1  First X co-ord
     * @param x2  Second X co-ord
     * @param y1  First Y co-ord
     * @param y2  Second Y co-ord
     * @param z1  First Z co-ord
     * @param z2  Second Z co-ord
     * @return Whether or not the coordinates of 'loc' are inside the co-ord range
     */
    public boolean contains(Location loc, int x1, int x2, int y1, int y2, int z1, int z2) {
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