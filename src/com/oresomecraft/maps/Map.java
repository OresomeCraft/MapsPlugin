package com.oresomecraft.maps;

import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.api.events.InventoryEvent;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public abstract class Map implements Listener {

    public MapsPlugin plugin = MapsPlugin.getInstance();
    public Map config;

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
    private Material[] disabledBlocks = new Material[]{Material.AIR};
    private boolean pearlDamage = true;
    private Long timeLock;
    private boolean autoSpawnProtection;
    private int spawnProtectionDuration;
    private int blockLimit = 256;
    private int requiredFFAScore = 20;
    private boolean fireSpread = true;
    private boolean toolMerge = false;
    private Long timeLoad;
    public boolean arrowOnLand = false;
    protected int tdmTime = 15;
    public Location ctfRedFlag, ctfBlueFlag, kothFlag;
    public CuboidRegion criminalTester;

    //Region attributes
    public int rx1 = 0;
    public int ry1 = 0;
    public int rz1 = 0;

    public int rx2 = 0;
    public int ry2 = 0;
    public int rz2 = 0;

    public World w; // World variable

    // Map details
    public String name;
    private String fullName;
    private String[] creators;
    private Gamemode[] modes;

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String[] getCreators() {
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

    public void load(WorldLoadEvent event) { // Internal - Do not change
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
    protected final void initiate(Map config, String name, String fullName, String creators[], Gamemode[] modes) {
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
     * Define the region box of a map
     *
     * @param x1 First X co-ord
     * @param x2 Second X co-ord
     * @param y1 First Y co-ord
     * @param y2 Second Y co-ord
     * @param z1 First Z co-ord
     * @param z2 Second Z co-ord
     */
    public void defineRegion(int x1, int x2, int y1, int y2, int z1, int z2) {
        rx1 = x1;
        rx2 = x2;
        ry1 = y1;
        ry2 = y2;
        rz1 = z1;
        rz2 = z2;
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
     * Disables certain blocks being broken
     *
     * @param blocks A Material array of what blocks to disable
     */
    public void disableBlocks(Material[] blocks) {
        disabledBlocks = blocks;
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
     * Removes arrow on collision
     *
     * @param allow Whether or not the arrow is removed
     */
    public void removeArrowsOnLand(boolean allow) {
        arrowOnLand = allow;
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
     * Enables tool merging
     *
     * @param allow Whether tool merging is allowed or not
     */
    @Deprecated
    public void setToolMerge(boolean allow) {
        toolMerge = allow;
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
     * Sets a time to be set when the map loads
     *
     * @param time The time to lock the map to. ("day", "dawn", "night")
     */
    public void setTimeUponLoad(String time) {
        if (time.equalsIgnoreCase("day")) timeLoad = 0L;
        else if (time.equalsIgnoreCase("dawn")) timeLoad = 23000L;
        else if (time.equalsIgnoreCase("night")) timeLoad = 14000L;
        else if (time.equalsIgnoreCase("dusk")) timeLoad = 13000L;
        else if (time.equalsIgnoreCase("midday")) timeLoad = 5000L;
        else if (time.equalsIgnoreCase("midnight")) timeLoad = 18000L;
    }

    /**
     * Sets a time to be set when the map loads
     *
     * @param time The time in ticks
     */
    public void setTimeUponLoad(long time) {
        this.timeLoad = time;
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
        if (event.getBlock().getWorld().getName().equals(name) && Arrays.asList(disabledBlocks).contains(event.getBlock().getType()))
            event.setCancelled(true);
    }

    HashMap<Material, Short> max = new HashMap<Material, Short>();

    {
        for (Material m : Material.values()) {
            if (m.getMaxDurability() > 0) max.put(m, m.getMaxDurability());
        }
    }

    /**
     * Automatically merges a picked up item's durability to your current tool if picked up
     *
     * @param event an Event called by the server
     */
    //@EventHandler
    public void pickup(PlayerPickupItemEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name) || !toolMerge) return;
        ItemStack it = event.getItem().getItemStack();
        if (!event.getPlayer().getInventory().contains(it.getType())) return;
        if (max.get(it.getType()) == 0) return;
        for (Material m : max.keySet()) {
            if (m == it.getType()) {
                for (ItemStack is : event.getPlayer().getInventory()) {
                    if (is.getType() == it.getType()) {
                        short total = (short) (is.getDurability() - (max.get(m) - it.getDurability()));
                        if (total <= 0) total = (short) 0;
                        is.setDurability(total);
                        event.setCancelled(true);
                        event.getItem().remove();
                        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ITEM_PICKUP, 2L, 2L);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Removes arrows on collision with an entity is disabled by the map
     *
     * @param event an Event called by the server
     */
    @EventHandler
    public void arrowAway(ProjectileHitEvent event) {
        if (event.getEntity().getWorld().getName().equals(name) && arrowOnLand) {
            if (event.getEntity() instanceof Arrow) event.getEntity().remove();
        }
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
        if (event.getBlock().getWorld().getName().equals(name) && Arrays.asList(disabledBlocks).contains(event.getBlock().getType()))
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
            for (ItemStack item : event.getDrops()) {
                if (disabledDrops != null && Arrays.asList(disabledDrops).contains(item.getType()))
                    item.setType(Material.AIR);
                if (toolMerge && item.getType() == Material.ARROW)
                    item.setType(Material.AIR);
            }
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

        if (BattlePlayer.getBattlePlayer(player) != null &&
                !BattlePlayer.getBattlePlayer(player).isSpectator()) {
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
     * Checks if a location is near a block.
     *
     * @param loc A location
     * @return Returns if it is near it or not
     */
    public boolean isLocationNearBlock(Location loc) {
        World world = loc.getWorld();
        if (!HOLLOW_MATERIALS.contains(loc.getBlock().getTypeId())) return true;
        if (!HOLLOW_MATERIALS.contains(loc.getBlock().getRelative(BlockFace.DOWN).getTypeId())) return true;
        return false;
    }

    // The player can stand inside these materials
    public static final Set<Integer> HOLLOW_MATERIALS = new HashSet<Integer>();

    static {
        HOLLOW_MATERIALS.add(Material.AIR.getId());
        HOLLOW_MATERIALS.add(Material.SAPLING.getId());
        HOLLOW_MATERIALS.add(Material.POWERED_RAIL.getId());
        HOLLOW_MATERIALS.add(Material.DETECTOR_RAIL.getId());
        HOLLOW_MATERIALS.add(Material.LONG_GRASS.getId());
        HOLLOW_MATERIALS.add(Material.DEAD_BUSH.getId());
        HOLLOW_MATERIALS.add(Material.YELLOW_FLOWER.getId());
        HOLLOW_MATERIALS.add(Material.RED_ROSE.getId());
        HOLLOW_MATERIALS.add(Material.BROWN_MUSHROOM.getId());
        HOLLOW_MATERIALS.add(Material.RED_MUSHROOM.getId());
        HOLLOW_MATERIALS.add(Material.TORCH.getId());
        HOLLOW_MATERIALS.add(Material.REDSTONE_WIRE.getId());
        HOLLOW_MATERIALS.add(Material.SEEDS.getId());
        HOLLOW_MATERIALS.add(Material.SIGN_POST.getId());
        HOLLOW_MATERIALS.add(Material.WOODEN_DOOR.getId());
        HOLLOW_MATERIALS.add(Material.LADDER.getId());
        HOLLOW_MATERIALS.add(Material.RAILS.getId());
        HOLLOW_MATERIALS.add(Material.WALL_SIGN.getId());
        HOLLOW_MATERIALS.add(Material.LEVER.getId());
        HOLLOW_MATERIALS.add(Material.STONE_PLATE.getId());
        HOLLOW_MATERIALS.add(Material.IRON_DOOR_BLOCK.getId());
        HOLLOW_MATERIALS.add(Material.WOOD_PLATE.getId());
        HOLLOW_MATERIALS.add(Material.REDSTONE_TORCH_OFF.getId());
        HOLLOW_MATERIALS.add(Material.REDSTONE_TORCH_ON.getId());
        HOLLOW_MATERIALS.add(Material.STONE_BUTTON.getId());
        HOLLOW_MATERIALS.add(Material.SNOW.getId());
        HOLLOW_MATERIALS.add(Material.SUGAR_CANE_BLOCK.getId());
        HOLLOW_MATERIALS.add(Material.DIODE_BLOCK_OFF.getId());
        HOLLOW_MATERIALS.add(Material.DIODE_BLOCK_ON.getId());
        HOLLOW_MATERIALS.add(Material.PUMPKIN_STEM.getId());
        HOLLOW_MATERIALS.add(Material.MELON_STEM.getId());
        HOLLOW_MATERIALS.add(Material.VINE.getId());
        HOLLOW_MATERIALS.add(Material.FENCE_GATE.getId());
        HOLLOW_MATERIALS.add(Material.WATER_LILY.getId());
        HOLLOW_MATERIALS.add(Material.NETHER_WARTS.getId());
        try // 1.6 update
        {
            HOLLOW_MATERIALS.add(Material.CARPET.getId());
        } catch (java.lang.NoSuchFieldError e) {
            System.out.println("No such field!");
        }

        for (Integer integer : HOLLOW_MATERIALS) {
            HOLLOW_MATERIALS.add(integer);
        }
        HOLLOW_MATERIALS.add(Material.WATER.getId());
        HOLLOW_MATERIALS.add(Material.STATIONARY_WATER.getId());
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
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, spawnProtectionDuration * 20, 1));
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

    @EventHandler
    public void setLoadTime(WorldLoadEvent event) {
        if (!event.getWorld().getName().equals(name)) return;
        if (timeLoad != null) {
            event.getWorld().setTime(timeLoad);
        }
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

    public void setRequiredFFAScore(int score) {
        if (score < 31) // Max allowed FFA score is 30
            this.requiredFFAScore = score;
    }

    public int getRequiredFFAScore() {
        return this.requiredFFAScore;
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
     * Gives a BattlePlayer it's colored leather armor
     *
     * @param bp A BattlePlayer
     */
    public void setColouredArmorAccordingToTeam(BattlePlayer bp) {
        PlayerInventory i = bp.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta hm = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
        hm.setColor(parseTeamColor(bp.getTeamType()));
        LEATHER_HELMET.setItemMeta(hm);

        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta cm = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
        cm.setColor(parseTeamColor(bp.getTeamType()));
        LEATHER_CHESTPLATE.setItemMeta(cm);

        ItemStack LEATHER_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta lm = (LeatherArmorMeta) LEATHER_LEGGINGS.getItemMeta();
        lm.setColor(parseTeamColor(bp.getTeamType()));
        LEATHER_LEGGINGS.setItemMeta(lm);

        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bm = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
        bm.setColor(parseTeamColor(bp.getTeamType()));
        LEATHER_BOOTS.setItemMeta(bm);

        i.setHelmet(LEATHER_HELMET);
        i.setChestplate(LEATHER_CHESTPLATE);
        i.setLeggings(LEATHER_LEGGINGS);
        i.setBoots(LEATHER_BOOTS);
    }

    /**
     * Parses Team to Color
     *
     * @param t A team
     */
    public Color parseTeamColor(Team t) {
        // if (t == Team.CP_BLUE) return Color.BLUE;
        if (t == Team.KOTH_BLUE) return Color.BLUE;
        if (t == Team.CTF_BLUE) return Color.BLUE;
        if (t == Team.TDM_BLUE) return Color.BLUE;
        if (t == Team.LTS_BLUE) return Color.BLUE;
        // if (t == Team.CP_BLUE) return Color.RED;
        if (t == Team.KOTH_RED) return Color.RED;
        if (t == Team.CTF_RED) return Color.RED;
        if (t == Team.TDM_RED) return Color.RED;
        if (t == Team.LTS_RED) return Color.RED;
        if (t == Team.FFA) return Color.GREEN;
        if (t == Team.HUMANS) return Color.GREEN;
        if (t == Team.LMS) return Color.BLUE;
        if (t == Team.ZOMBIES) return Color.MAROON;
        return Color.WHITE;
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

    /**
     * Checks if a Location's coordinates is in between the region
     *
     * @return Whether or not the coordinates of 'loc' are inside the region
     */
    public boolean isInsideRegion(Location loc) {
        int bottomCornerX = rx1 < rx2 ? rx1 : rx2;
        int bottomCornerZ = rz1 < rz2 ? rz1 : rz2;
        int topCornerX = rx1 > rx2 ? rx1 : rx2;
        int topCornerZ = rz1 > rz2 ? rz1 : rz2;
        int bottomCornerY = ry1 < ry2 ? ry1 : ry2;
        int topCornerY = ry1 > ry2 ? ry1 : ry2;
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