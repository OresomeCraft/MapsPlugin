package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.gamemodes.TDM;
import org.bukkit.*;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.Utility;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Voidsflag extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Voidsflag(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    String name = "voidsflag";
    String fullName = "Voids Flag";
    String creators = "_Moist, MiCkEyMiCE and R3creat3";
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
        }
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -2, 23, 49);
        Location blueSpawn = new Location(w, -348, 23, 51);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

        Location redFlag = new Location(w, -71, 45, 60);
        Location blueFlag = new Location(w, -281, 45, 40);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -2, 23, 49);
        Location blueSpawn = new Location(w, -348, 23, 51);
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

            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack STONE_PICKAXE = new ItemStack(Material.STONE_PICKAXE, 1);
            ItemStack STONE_AXE = new ItemStack(Material.STONE_AXE, 1);
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack COOKED_PORKCHOP = new ItemStack(Material.COOKED_BEEF, 4);
            ItemStack BIRCH_LOG = new ItemStack(Material.LOG, 64);
            ItemStack ARROW = new ItemStack(Material.ARROW, 64);

            p.getInventory().setBoots(LEATHER_BOOTS);
            p.getInventory().setLeggings(LEATHER_PANTS);
            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STONE_PICKAXE);
            i.setItem(3, STONE_AXE);
            i.setItem(4, COOKED_PORKCHOP);
            i.setItem(5, BIRCH_LOG);
            i.setItem(11, ARROW);

            if (TDM.isBlue(p.getName())) {
                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
                helmetMeta.setColor(Color.BLUE);
                LEATHER_HELMET.setItemMeta(helmetMeta);

                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
                bootsMeta.setColor(Color.BLUE);
                LEATHER_BOOTS.setItemMeta(bootsMeta);

                LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
                pantsMeta.setColor(Color.BLUE);
                LEATHER_PANTS.setItemMeta(pantsMeta);

                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
                chestplateMeta.setColor(Color.BLUE);
                LEATHER_CHESTPLATE.setItemMeta(chestplateMeta);
            }

            if (TDM.isRed(p.getName())) {
                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
                helmetMeta.setColor(Color.RED);
                LEATHER_HELMET.setItemMeta(helmetMeta);

                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
                bootsMeta.setColor(Color.RED);
                LEATHER_BOOTS.setItemMeta(bootsMeta);

                LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
                pantsMeta.setColor(Color.RED);
                LEATHER_PANTS.setItemMeta(pantsMeta);

                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
                chestplateMeta.setColor(Color.RED);
                LEATHER_CHESTPLATE.setItemMeta(chestplateMeta);
            }

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
    public int x1 = 9;
    public int y1 = 86;
    public int z1 = 112;

    //Bottom right corner.
    public int x2 = -365;
    public int y2 = 2;
    public int z2 = -15;

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
    public void preventoffmapbreak(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (Utility.getArena().equals(name)) {
            if (!contains(loc, x1, x2, y1, y2, z1, z2)) {

                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventmultipleplace(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();
        Player player = event.getPlayer();

        if (Utility.getArena().equals(name)) {
            if (b.getType().equals(Material.TNT)) {
                if (contains(loc, -357, -177, 3, 69, -8, 109) && TDM.isBlue(player.getName())) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "TNT can only be placed on the enemy side");
                }
                if (contains(loc, -186, 10, 71, 4, 108, -7) && TDM.isRed(player.getName())) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "TNT can only be placed on the enemy side");
                }
            }
        }
    }

}
