package com.oresomecraft.BattleMaps.maps;

import java.util.ArrayList;
import java.util.List;


import com.oresomecraft.BattleMaps.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.Utility;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Hypno extends BattleMap implements IBattleMap, Listener {

    public Hypno() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    String name = "hypno";
    String fullName = "Hypnosis";
    String creators = "zachoz, pegabeavercorn, DragonDrew and kevlar_miner";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        redSpawns.add(new Location(w, -773, 102, -1338, 134, 0));
        redSpawns.add(new Location(w, -779, 92, -1349, 134, 0));
        redSpawns.add(new Location(w, -759, 81, -1355, 173, 0));
        redSpawns.add(new Location(w, -747, 63, -1376, 133, 0));
        redSpawns.add(new Location(w, -772, 63, -1372, -141, 0));
        redSpawns.add(new Location(w, -757, 72, -1353, 175, 0));

        blueSpawns.add(new Location(w, -791, 93, -1379, -89, 0));
        blueSpawns.add(new Location(w, -827, 106, -1379, -54, 0));
        blueSpawns.add(new Location(w, -810, 91, -1349, -89, 0));
        blueSpawns.add(new Location(w, -808, 79, -1379, -90, 0));
        blueSpawns.add(new Location(w, -784, 63, -1418, -34, 0));
        blueSpawns.add(new Location(w, -800, 62, -1434, -30, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {
        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, -783, 91, -1331, 159, 0);
        Location blueSpawn = new Location(w, -814, 83, -1389, -18, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -826, 72, -1439, -44, 0));
        FFASpawns.add(new Location(w, -781, 72, -1427, -2, 0));
        FFASpawns.add(new Location(w, -817, 62, -1436, -43, 0));
        FFASpawns.add(new Location(w, -819, 96, -1413, -56, 0));
        FFASpawns.add(new Location(w, -794, 98, -1380, -76, 0));
        FFASpawns.add(new Location(w, -801, 68, -1389, -44, 0));
        FFASpawns.add(new Location(w, -761, 63, -1401, 10, 0));
        FFASpawns.add(new Location(w, -829, 110, -1328, -115, 0));
        FFASpawns.add(new Location(w, -802, 69, -1298, -163, 0));
        FFASpawns.add(new Location(w, -810, 96, -1350, -83, 0));
        FFASpawns.add(new Location(w, -760, 105, -1345, 149, 0));
        FFASpawns.add(new Location(w, -748, 81, -1344, 136, 0));
        FFASpawns.add(new Location(w, -739, 64, -1371, 97, 0));
        FFASpawns.add(new Location(w, -791, 83, -1347, -145, 0));
        FFASpawns.add(new Location(w, -767, 108, -1337, 117, 0));
        FFASpawns.add(new Location(w, -740, 93, -1362, 47, 0));
        FFASpawns.add(new Location(w, -760, 68, -1398, 16, 0));
        FFASpawns.add(new Location(w, -800, 87, -1384, -103, 0));
        FFASpawns.add(new Location(w, -781, 69, -1424, -18, 0));
        FFASpawns.add(new Location(w, -746, 73, -1358, 123, 0));

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
            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack STONE_SHOVEL = new ItemStack(Material.STONE_SPADE, 1);
            ItemStack IRON_PICK = new ItemStack(Material.IRON_PICKAXE, 1);
            ItemStack EMERALD = new ItemStack(Material.EMERALD, 1);
            ItemStack EGG_HYPNO = new ItemStack(Material.EGG, 1);

            ItemMeta egg_hypno = EGG_HYPNO.getItemMeta();
            egg_hypno.setDisplayName(ChatColor.BLUE + "Flash bang grenade");

            List<String> eggLore = new ArrayList<String>();
            eggLore.add(org.bukkit.ChatColor.BLUE + "Everyone's favourite item!");
            egg_hypno.setLore(eggLore);
            EGG_HYPNO.setItemMeta(egg_hypno);

            ItemMeta emerald = EMERALD.getItemMeta();
            emerald.setDisplayName(ChatColor.BLUE + "Nausea Stone");
            EMERALD.setItemMeta(emerald);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);
            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, EMERALD);
            i.setItem(3, EGG_HYPNO);
            i.setItem(4, IRON_PICK);
            i.setItem(5, STONE_SHOVEL);
            i.setItem(6, STEAK);
            i.setItem(7, HEALTH_POTION);
            i.setItem(8, ARROWS);

        }
    }

    public int x1 = -831;
    public int y1 = 62;
    public int z1 = -1452;
    public int x2 = -726;
    public int y2 = 118;
    public int z2 = -1275;

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockbreak(BlockBreakEvent event) {
        Block b = event.getBlock();
        int mat = b.getTypeId();
        Location loc = b.getLocation();
        if (loc.getWorld().getName().equals(name)) {
            if (contains(loc, x1, x2, y1, y2, z1, z2)) {

                if (mat == 43 || mat == 44 || mat == 35 || mat == 42
                        || mat == 49 || mat == 123 || mat == 69 || mat == 124) {

                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void hypnoGem(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity e = event.getEntity();
        Location loc = e.getLocation();

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (damager instanceof Player) {

                Player damagerP = (Player) damager;
                ItemStack weapon = damagerP.getItemInHand();
                Material mat = weapon.getType();

                if (e instanceof Player) {

                    Player p = (Player) e;

                    if (mat == Material.EMERALD) {

                        PotionEffectType confuse = PotionEffectType.CONFUSION;
                        PotionEffect confuseE = new PotionEffect(confuse, 400,
                                1);
                        p.addPotionEffect(confuseE);
                    }

                }

            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void hypnoBangGrenade(ProjectileHitEvent event) {
        Entity e = event.getEntity();
        Location loc = e.getLocation();
        World world = loc.getWorld();

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (e instanceof Egg) {

                world.playEffect(loc, Effect.ENDER_SIGNAL, 5);
                world.playSound(loc, Sound.EXPLODE, 10, 10);

                List<Entity> nearby = e.getNearbyEntities(3, 3, 3);

                int amount = nearby.size();
                int count = 0;
                int counter;
                for (counter = 0; counter < amount; counter++) {

                    Entity near = nearby.get(count);

                    if (near instanceof Player) {

                        Player p = (Player) near;

                        PotionEffectType blind = PotionEffectType.BLINDNESS;
                        PotionEffectType slow = PotionEffectType.SLOW;
                        PotionEffectType flash = PotionEffectType.NIGHT_VISION;

                        PotionEffect blindE = new PotionEffect(blind, 200, 2);
                        PotionEffect slowE = new PotionEffect(slow, 200, 2);
                        PotionEffect flashE = new PotionEffect(flash, 200, 2);

                        p.addPotionEffect(blindE);
                        p.addPotionEffect(slowE);
                        p.addPotionEffect(flashE);

                    }
                    count++;

                }

            }
        }
    }

    @EventHandler
    public void arrowTrail(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (Utility.getArena().equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 8);
            }
        }

    }

}
