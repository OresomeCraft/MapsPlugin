package com.oresomecraft.maps.battles.maps;
 
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
 
import java.util.ArrayList;
import java.util.List;
 
@MapConfig
public class Omoshiro extends BattleMap implements Listener {
 
    public Omoshiro() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setTDMTime(12);
        lockTime("night");
    }
 
    // Map details
    String name = "omoshiro";
    String fullName = "Omoshiro";
    String[] creators = {"Mr_Jaskirat"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};
 
    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 180, 21, 1450, -19, 0));
        redSpawns.add(new Location(w, 206, 26, 1562, -177, 0));
        redSpawns.add(new Location(w, 233, 21, 1506, 85, 0));
        redSpawns.add(new Location(w, 173, 20, 1506, -104, 0));
 
        blueSpawns.add(new Location(w, 180, 21, 1450, -19, 0));
        blueSpawns.add(new Location(w, 206, 26, 1562, -177, 0));
        blueSpawns.add(new Location(w, 233, 21, 1506, 85, 0));
        blueSpawns.add(new Location(w, 173, 20, 1506, -104, 0));
    }
 
    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 180, 21, 1450, -19, 0));
        FFASpawns.add(new Location(w, 206, 26, 1562, -177, 0));
        FFASpawns.add(new Location(w, 233, 21, 1506, 85, 0));
        FFASpawns.add(new Location(w, 173, 20, 1506, -104, 0));
 
        defineRegion(x1, x2, y1, y2, z1, z2);
    }
 
    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
 
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
 
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack IRON_INGOT = new ItemStack(Material.IRON_INGOT, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack COOKED_BEEF = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ENCHANTMENT_BOTTLE = new ItemStack(Material.EXP_BOTTLE, 5);
 
        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);
 
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS, LEATHER_HELMET});
        InvUtils.nameItem(STONE_SWORD, ChatColor.RED + "Samurai Sword Of Fury");
        InvUtils.nameItem(STONE_SWORD, ChatColor.BLUE + "Simple Sword");
 
        ItemMeta ironSwordMeta = STONE_SWORD.getItemMeta();
        List<String> ironSwordLore = new ArrayList<String>();
        ironSwordLore.add(org.bukkit.ChatColor.RED + "Forged from the loot of your enemies!");
        ironSwordMeta.setLore(ironSwordLore);
        STONE_SWORD.setItemMeta(ironSwordMeta);
 
        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, COOKED_BEEF);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, ENCHANTMENT_BOTTLE);
        i.setItem(7, IRON_INGOT);
        i.setItem(10, ARROW);
    }
 
    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 305;
    public int y1 = 91;
    public int z1 = 1321;
 
    // Bottom right corner.
    public int x2 = 97;
    public int y2 = 2;
    public int z2 = 1636;
 
    @EventHandler
    public void arrowAway(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        Location loc = projectile.getLocation();
        if (loc.getWorld().getName().equals(name)) {
            if (projectile instanceof Arrow) {
                Arrow arrow = (Arrow) projectile;
                arrow.remove();
            }
        }
    }
 
    public int particles;
 
    @EventHandler
    public void arrowParticles(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            particles = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    World world = Bukkit.getWorld(name);
                    if (getArena().equals(name)) {
                        for (Entity arrow : world.getEntities()) {
                            if (arrow != null) {
                                if (arrow instanceof Arrow) {
                                    world.playEffect(arrow.getLocation(), Effect.SMOKE, 10);
                                }
                            }
                        }
                    }
                }
            }, 5L, 5L);
        }
    }
 
    @EventHandler
    public void cancelParticles(BattleEndEvent event) {
        Bukkit.getScheduler().cancelTask(particles);
    }
 
}
