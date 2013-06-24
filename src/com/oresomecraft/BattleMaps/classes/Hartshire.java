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
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};
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
            ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 3);

            ItemMeta fishing_rod = FISHING_ROD.getItemMeta();
            fishing_rod.setDisplayName(ChatColor.BLUE + "Grappling hook");
            FISHING_ROD.setItemMeta(fishing_rod);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, FISHING_ROD);
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
    public void protection1(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void fishing(PlayerFishEvent event) {
        PlayerFishEvent.State state = event.getState();
        Player p = event.getPlayer();
        ItemStack is = p.getItemInHand();
        Material mat = is.getType();
        Location loc = p.getLocation();

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (mat == Material.FISHING_ROD) {

                if (state == State.IN_GROUND) {
                    p.launchProjectile(Snowball.class);

                }
            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void grapple(ProjectileHitEvent event) {
        Entity proj = event.getEntity();
        Location hit = proj.getLocation();

        if (contains(hit, x1, x2, y1, y2, z1, z2)) {

            if (proj instanceof Snowball) {
                Snowball fish = (Snowball) proj;
                Entity shooter = fish.getShooter();

                if (shooter instanceof Player) {
                    Player p = (Player) shooter;
                    Location loc = p.getLocation();
                    ItemStack is = p.getItemInHand();
                    Material mat = is.getType();

                    if (mat == Material.FISHING_ROD) {

                        p.setFallDistance(0);
                        p.playSound(loc, Sound.ARROW_HIT, 1, 1);

                        int hitx = hit.getBlockX();
                        int hity = hit.getBlockY();
                        int hitz = hit.getBlockZ();
                        int locx = loc.getBlockX();
                        int locy = loc.getBlockY();
                        int locz = loc.getBlockZ();
                        double co[] = new double[3];

                        if (hitx > locx) {
                            co[0] = 1.2;
                        } else if (hitx < locx) {
                            co[0] = -1.2;
                        } else if (hitx == locx) {
                            co[0] = 0;
                        }

                        if (hity > locy) {
                            co[1] = 1.4;
                        } else if (hity < locy) {
                            co[1] = -0.8;
                        } else if (hity == locy) {
                            co[1] = 0;
                        }

                        if (hitz > locz) {
                            co[2] = 1.2;
                        } else if (hitz < locz) {
                            co[2] = -1.2;
                        } else if (hitz == locz) {
                            co[2] = 0;
                        }

                        p.setVelocity(new Vector(co[0], co[1], co[2]));

                    }
                }
            }

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

                inv.removeItem(new ItemStack(Material.INK_SACK, (short)12;

                p.updateInventory();
            }
        }

    }

}
