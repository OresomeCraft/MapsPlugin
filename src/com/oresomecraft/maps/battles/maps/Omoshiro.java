package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
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

@MapConfig(
        name = "omoshiro",
        fullName = "Omoshiro",
        creators = {"Mr_Jaskirat", "SereneMango"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = 305,
        y1 = 91,
        z1 = 1321,
        x2 = 97,
        y2 = 2,
        z2 = 1636
)
@Attributes(
        tdmTime = 12,
        allowBuild = false,
        timeLock = Map.Time.NIGHT,
        disabledDrops = {Material.BOW, Material.ARROW, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.STONE_SWORD}
)
public class Omoshiro extends BattleMap implements Listener {

    public Omoshiro() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 180, 21, 1451, -21, 0));
        redSpawns.add(new Location(w, 218, 19, 1478, -3, 0));
        redSpawns.add(new Location(w, 175, 18, 1483, -7, 0));
        redSpawns.add(new Location(w, 215, 22, 1492, -9, 0));
        
        blueSpawns.add(new Location(w, 181, 21, 1529, -179, 0));
        blueSpawns.add(new Location(w, 210, 22, 1542, 155, 0));
        blueSpawns.add(new Location(w, 236, 21, 1516, 174, 0));
        blueSpawns.add(new Location(w, 213, 26, 1513, 90, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 180, 21, 1451, -21, 0));
        FFASpawns.add(new Location(w, 218, 19, 1478, -3, 0));
        FFASpawns.add(new Location(w, 175, 18, 1483, -7, 0));
        FFASpawns.add(new Location(w, 215, 22, 1492, -9, 0));
        FFASpawns.add(new Location(w, 181, 21, 1529, -179, 0));
        FFASpawns.add(new Location(w, 210, 22, 1542, 155, 0));
        FFASpawns.add(new Location(w, 236, 21, 1516, 174, 0));
        FFASpawns.add(new Location(w, 213, 26, 1513, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_INGOT = new ItemStack(Material.GOLD_INGOT, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack COOKED_BEEF = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ENCHANTMENT_BOTTLE = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack STICK = new ItemStack(Material.STICK, 1);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS, LEATHER_HELMET});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        ItemUtils.nameItem(IRON_SWORD, ChatColor.RED + "Ancient Samurai Sword");

        ItemMeta ironSwordMeta = IRON_SWORD.getItemMeta();
        List<String> ironSwordLore = new ArrayList<String>();
        ironSwordLore.add(org.bukkit.ChatColor.GOLD + "A deadly blade capable of pulverising your opponents to a PULP!");
        ironSwordMeta.setLore(ironSwordLore);
        IRON_SWORD.setItemMeta(ironSwordMeta);
 
        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, COOKED_BEEF);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, ENCHANTMENT_BOTTLE);
        i.setItem(5, STICK);
        i.setItem(6, GOLD_INGOT);
        i.setItem(10, ARROW);
    }

    @EventHandler
    public void arrowAway(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        Location loc = projectile.getLocation();
        if (loc.getWorld().getName().equals(getName())) {
            if (projectile instanceof Arrow) {
                Arrow arrow = (Arrow) projectile;
                arrow.remove();
            }
        }
    }

    public int particles;

    @EventHandler
    public void arrowParticles(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(getName())) {
            particles = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    World world = Bukkit.getWorld(getName());
                    if (getArena().equals(getName())) {
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
