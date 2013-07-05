package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;
import java.util.List;

import com.oresomecraft.OresomeBattles.Utility;
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
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Mayhem extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Mayhem(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "mayhem";
    String fullName = "Mayhem";
    String creators = "ShaunDepro97, meganlovesmusic, and Kytria";
    Gamemode[] modes = {Gamemode.TDM};

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
            cyclePowerBlock();
        }
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 64, 112, 111, 2, 0);
        Location blueSpawn = new Location(w, 23, 112, 63, 3, 0);

        redSpawns.add(redSpawn);

        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 64, 112, 111, 2, 0);
        Location blueSpawn = new Location(w, 23, 112, 63, 3, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);

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
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack DIAMOND_PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 6);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);

            i.setItem(0, DIAMOND_PICKAXE);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(28, ARROWS);
            i.setItem(8, EXP);

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
    public int x1 = 0;
    public int y1 = 1;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 128;
    public int y2 = 255;
    public int z2 = 128;

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

    @EventHandler
    public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player p = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        for (ItemStack item : drops) {
            Material mat = item.getType();

            if (mat == Material.BOW
                    || mat == Material.IRON_BOOTS
                    || mat == Material.IRON_LEGGINGS
                    || mat == Material.IRON_CHESTPLATE
                    || mat == Material.FISHING_ROD
                    || mat == Material.DIAMOND_PICKAXE
                    || mat == Material.ARROW) {

                item.setType(Material.AIR);
            }
        }
    }

    int timer;
    int count = 0;
    Location powerBlock = new Location(Bukkit.getWorld(name), 64, 110, 64);

    private void cyclePowerBlock() {

        timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                Material[] blocks = {Material.REDSTONE_ORE, Material.GOLD_ORE, Material.COAL_ORE,
                        Material.EMERALD_ORE, Material.LAPIS_ORE, Material.DIAMOND_ORE};
                int max = blocks.length;

                World world = Bukkit.getWorld(name);
                world.getBlockAt(powerBlock).setType(blocks[count]);

                count = count > max ? count = 0 : count++;
            }

        }, (20 * 60), (20 * 60));

    }

    @EventHandler
    public void worldUnload(WorldUnloadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            Bukkit.getScheduler().cancelTask(timer);
        }
    }

    @EventHandler
    public void powerBlock(BlockBreakEvent event) {
        Player p = event.getPlayer();
        Material type = event.getBlock().getType();
        int potionTime = 45;

        if (Utility.compareLocations(powerBlock, event.getBlock().getLocation())) {
            if (type == Material.REDSTONE_ORE) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, potionTime, 2));
            } else if (type == Material.GOLD_ORE) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, potionTime, 2));
            } else if (type == Material.COAL_ORE) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, potionTime, 2));
            } else if (type == Material.LAPIS_ORE) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, potionTime, 2));
            } else if (type == Material.DIAMOND_ORE) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, potionTime, 2));
            }
        }
    }

}