package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Hartshire extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Hartshire(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "hartshire";
    String fullName = "Hartshire";
    String creators = "R3creat3, kalikakitty and xannallax33";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};
    //Map download link: N/A

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
        addMap(name);
        readyTDMSpawns();
        readyFFASpawns();
        addCreators(name, creators); 
        setFullName(name, fullName);
        setGamemodes(name, modes);
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 93, 39, -126, -1, 0);
        Location blueSpawn = new Location(w, 188, 45, -130, -178, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 154, 58, -158, -50, 0));
        redSpawns.add(new Location(w, 77, 40, -124, -50, 0));
        blueSpawns.add(new Location(w, 119, 67, -163, -50, 0));
        redSpawns.add(new Location(w, 175, 45, -245, -50, 0));
        blueSpawns.add(new Location(w, 184, 48, -210, -50, 0));
        redSpawns.add(new Location(w, 229, 56, -148, -50, 0));
        blueSpawns.add(new Location(w, 116, 41, -46, -50, 0));
        redSpawns.add(new Location(w, 94, 53, -106, -50, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 93, 39, -126, -1, 0);
        Location blueSpawn = new Location(w, 188, 45, -130, -178, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 154, 58, -158, -50, 0));
        FFASpawns.add(new Location(w, 77, 40, -124, -50, 0));
        FFASpawns.add(new Location(w, 119, 67, -163, -50, 0));
        FFASpawns.add(new Location(w, 175, 45, -245, -50, 0));
        FFASpawns.add(new Location(w, 184, 48, -210, -50, 0));
        FFASpawns.add(new Location(w, 229, 56, -148, -50, 0));
        FFASpawns.add(new Location(w, 116, 41, -46, -50, 0));
        FFASpawns.add(new Location(w, 94, 53, -106, -50, 0));

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
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 3);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(2, BOW);
            i.setItem(3, STEAK);
            i.setItem(4, HEALTH_POTION);
            i.setItem(5, ARROWS);
            i.setItem(6, EXP);

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
    public int x1 = -207;
    public int y1 = 52;
    public int z1 = -1220;

    //Bottom right corner.
    public int x2 = -38;
    public int y2 = 112;
    public int z2 = -1125;

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
    public void protection(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void ointment(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action a = event.getAction();
        ItemStack i = p.getItemInHand();
        Inventory inv = p.getInventory();
        Material tool = i.getType();

        if (tool == Material.INK_SACK) {

            if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                p.removePotionEffect(PotionEffectType.WITHER);

                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));

                inv.removeItem(new ItemStack(Material.INK_SACK, 12));

                p.updateInventory();
            }
        }

    }

}
