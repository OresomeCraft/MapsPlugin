package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.BattleMaps.IBattleMap;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Apollo extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Apollo(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "apollo";
    String fullName = "Apollo";
    String creators = "RokMelon and Invinsible_Jelly";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
        addMap(name);
        setGamemodes(name, modes);
        addCreators(name, creators);
        setFullName(name, fullName);
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

        Location redSpawn = new Location(w, -11, 150, 72, 89, 0);
        Location blueSpawn = new Location(w, -33, 150, 50, -1, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -55, 150, 72, -111, 0));
        redSpawns.add(new Location(w, -20, 133, 27, 48, 0));
        redSpawns.add(new Location(w, -81, 133, 88, -133, 0));
        redSpawns.add(new Location(w, -40, 110, 55, -89, 0));
        redSpawns.add(new Location(w, -53, 93, 54, -43, 0));
        redSpawns.add(new Location(w, -68, 122, 38, -45, 0));
        redSpawns.add(new Location(w, -53, 133, 57, -53, 0));
        redSpawns.add(new Location(w, -62, 136, 80, -131, 0));


        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -33, 150, 94, 179, 0));
        blueSpawns.add(new Location(w, -19, 133, 88, 137, 0));
        blueSpawns.add(new Location(w, -82, 133, 25, -44, 0));
        blueSpawns.add(new Location(w, -57, 109, 72, -2, 0));
        blueSpawns.add(new Location(w, -53, 93, 54, -43, 0));
        blueSpawns.add(new Location(w, -68, 122, 38, -45, 0));
        blueSpawns.add(new Location(w, -53, 133, 57, -53, 0));
        blueSpawns.add(new Location(w, -62, 136, 80, -131, 0));


        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, -11, 150, 72, 89, 0));
        FFASpawns.add(new Location(w, -33, 150, 50, -1, 0));
        FFASpawns.add(new Location(w, -55, 150, 72, -111, 0));
        FFASpawns.add(new Location(w, -20, 133, 27, 48, 0));
        FFASpawns.add(new Location(w, -81, 133, 88, -133, 0));
        FFASpawns.add(new Location(w, -40, 110, 55, -89, 0));
        FFASpawns.add(new Location(w, -53, 93, 54, -43, 0));
        FFASpawns.add(new Location(w, -68, 122, 38, -45, 0));
        FFASpawns.add(new Location(w, -53, 133, 57, -53, 0));
        FFASpawns.add(new Location(w, -62, 136, 80, -131, 0));
        FFASpawns.add(new Location(w, -33, 150, 94, 179, 0));
        FFASpawns.add(new Location(w, -19, 133, 88, 137, 0));
        FFASpawns.add(new Location(w, -82, 133, 25, -44, 0));
        FFASpawns.add(new Location(w, -57, 109, 72, -2, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        final Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
            ItemStack ENDER_PEARL = new ItemStack(Material.ENDER_PEARL, 3);
            IRON_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, ARROWS);
            i.setItem(5, EXP);
            i.setItem(6, ENDER_PEARL);

            Bukkit.getScheduler().runTask(plugin, new Runnable() {
                public void run() {
                    //Change jump effect to 3 - R3
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000 * 20, 2));
                }
            });

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
    public int x1 = -8;
    public int y1 = 164;
    public int z1 = 16;

    //Bottom right corner.
    public int x2 = -85;
    public int y2 = 62;
    public int z2 = 99;

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

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockbreak(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockplace(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

}