package com.oresomecraft.BattleMaps.maps;


import com.oresomecraft.BattleMaps.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Chaoscity extends BattleMap implements IBattleMap, Listener {

    public Chaoscity() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "chaoscity";
    String fullName = "Chaos City";
    String creators = "xTeChNoSoUl, NobleFable, Shavahn2003 and SuperDuckFace";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -42, 9, -96, 1, 0);
        Location blueSpawn = new Location(w, -48, 9, -1, -179, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, -42, 9, -96, 1, 0));
        blueSpawns.add(new Location(w, -48, 9, -1, -179, 0));
        redSpawns.add(new Location(w, -66, 9, -98, -1, 0));
        blueSpawns.add(new Location(w, -89, 9, -94, -47, 0));
        redSpawns.add(new Location(w, -48, 9, -91, 1, 0));
        blueSpawns.add(new Location(w, -28, 9, -97, -1, 0));
        redSpawns.add(new Location(w, -10, 9, -97, 22, 0));
        blueSpawns.add(new Location(w, -95, 9, -2, -139, 0));
        redSpawns.add(new Location(w, -70, 9, -1, 177, 0));
        blueSpawns.add(new Location(w, -48, 9, -4, 179, 0));
        redSpawns.add(new Location(w, -31, 9, 0, -178, 0));
        blueSpawns.add(new Location(w, -2, 9, 0, 174, 0));
        redSpawns.add(new Location(w, -7, 10, -26, 117, 0));
        blueSpawns.add(new Location(w, -95, 10, -29, -90, 0));
        redSpawns.add(new Location(w, -85, 10, -68, -76, 0));
        blueSpawns.add(new Location(w, -1, 10, -78, 87, 0));
        redSpawns.add(new Location(w, -28, 14, -64, 59, 0));
        blueSpawns.add(new Location(w, -20, 14, -50, -179, 0));
        redSpawns.add(new Location(w, -17, 9, -49, 92, 0));
        blueSpawns.add(new Location(w, -62, 14, 30, 135, 0));
        redSpawns.add(new Location(w, -71, 14, -41, -7, 0));
        blueSpawns.add(new Location(w, -74, 9, -43, -85, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {
        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, -42, 9, -96, 1, 0);
        Location blueSpawn = new Location(w, -48, 9, -1, -179, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);

        FFASpawns.add(new Location(w, -42, 9, -96, 1, 0));
        FFASpawns.add(new Location(w, -48, 9, -1, -179, 0));
        FFASpawns.add(new Location(w, -66, 9, -98, -1, 0));
        FFASpawns.add(new Location(w, -89, 9, -94, -47, 0));
        FFASpawns.add(new Location(w, -48, 9, -91, 1, 0));
        FFASpawns.add(new Location(w, -28, 9, -97, -1, 0));
        FFASpawns.add(new Location(w, -10, 9, -97, 22, 0));
        FFASpawns.add(new Location(w, -95, 9, -2, -139, 0));
        FFASpawns.add(new Location(w, -70, 9, -1, 177, 0));
        FFASpawns.add(new Location(w, -48, 9, -4, 179, 0));
        FFASpawns.add(new Location(w, -31, 9, 0, -178, 0));
        FFASpawns.add(new Location(w, -2, 9, 0, 174, 0));
        FFASpawns.add(new Location(w, -7, 10, -26, 117, 0));
        FFASpawns.add(new Location(w, -95, 10, -29, -90, 0));
        FFASpawns.add(new Location(w, -85, 10, -68, -76, 0));
        FFASpawns.add(new Location(w, -1, 10, -78, 87, 0));
        FFASpawns.add(new Location(w, -28, 14, -64, 59, 0));
        FFASpawns.add(new Location(w, -20, 14, -50, -179, 0));
        FFASpawns.add(new Location(w, -17, 9, -49, 92, 0));
        FFASpawns.add(new Location(w, -62, 14, 30, 135, 0));
        FFASpawns.add(new Location(w, -71, 14, -41, -7, 0));
        FFASpawns.add(new Location(w, -74, 9, -43, -85, 0));

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
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack AMMO = new ItemStack(Material.FLINT, 64);
            ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);

            ItemMeta blaze_rod = BLAZE_ROD.getItemMeta();
            blaze_rod.setDisplayName(ChatColor.BLUE + "Gun");
            BLAZE_ROD.setItemMeta(blaze_rod);

            ItemMeta ammo = AMMO.getItemMeta();
            ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
            AMMO.setItemMeta(ammo);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);
            i.setItem(0, IRON_SWORD);
            i.setItem(1, BLAZE_ROD);
            i.setItem(2, AMMO);
            i.setItem(3, STEAK);
            i.setItem(4, HEALTH_POTION);
            i.setItem(5, EXP);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top corner.
    public int x1 = -100;
    public int y1 = 5;
    public int z1 = -101;

    //Bottom corner.
    public int x2 = 4;
    public int y2 = 87;
    public int z2 = 2;

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void gun(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        Action a = event.getAction();
        ItemStack i = p.getItemInHand();
        Inventory inv = p.getInventory();
        Material tool = i.getType();
        World world = loc.getWorld();
        String name = p.getName();

        if (battles.spectator.contains(name)) {
            event.setCancelled(true);
        } else {
            if (contains(loc, x1, x2, y1, y2, z1, z2)) {

                if (tool == Material.BLAZE_ROD) {

                    if (a == Action.RIGHT_CLICK_AIR
                            || a == Action.RIGHT_CLICK_BLOCK) {

                        if (inv.contains(Material.FLINT)) {

                            p.launchProjectile(Arrow.class);
                            world.playSound(loc, Sound.COW_WALK, 10, 10);
                            ItemStack AMMO = new ItemStack(Material.FLINT, 1);
                            ItemMeta ammo = AMMO.getItemMeta();
                            ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
                            AMMO.setItemMeta(ammo);
                            inv.removeItem(AMMO);
                            p.updateInventory();

                        } else {
                            world.playSound(loc, Sound.CLICK, 10, 10);
                        }

                    }

                }

            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void bulletAway(ProjectileHitEvent event) {
        Entity p = event.getEntity();
        Location loc = p.getLocation();
        Block b = loc.getBlock();
        Material mat = b.getType();

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (p instanceof Arrow) {
                Arrow a = (Arrow) p;
                a.remove();

                if (mat == Material.THIN_GLASS) {
                    b.breakNaturally();
                }

            }
        }
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