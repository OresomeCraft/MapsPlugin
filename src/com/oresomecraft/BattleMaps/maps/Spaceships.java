package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Spaceships extends BattleMap implements IBattleMap, Listener {

    public Spaceships() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setTDMTime(15);
    }

    String name = "spaceships";
    String fullName = "SpaceShips";
    String creators = "sampighere, zachoz and R3creat3";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location blueSpawn = new Location(w, 142, 43, -80, 0, 0);
        Location redSpawn = new Location(w, 210, 43, -80, 0, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location blueSpawn = new Location(w, 142, 43, -80, 0, 0);
        Location redSpawn = new Location(w, 210, 43, -80, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(8, new ItemStack(Material.BREAD, 3));

            //Give players invincibility for 8 seconds when they spawn.
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 8 * 20, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40 * 20, 1));
            //For people who chuck lava on the damned spawn
        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 106;
    public int y1 = 110;
    public int z1 = 34;

    //Bottom right corner.
    public int x2 = 246;
    public int y2 = 0;
    public int z2 = -191;

    @EventHandler
    public void preventspawnexplosion(EntityExplodeEvent event) {
        if (Utility.getArena().equals(name)) {
            Location loc = event.getLocation();
            // Red team
            if (contains(loc, 206, 214, 42, 38, -79, -60)) {
                event.setCancelled(true);
            }

            // Blue team
            if (contains(loc, 138, 146, 42, 38, -79, -58)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void preventspawnbreak(BlockBreakEvent event) {
        if (Utility.getArena().equals(name)) {
            Location loc = event.getBlock().getLocation();
            // Red team
            if (contains(loc, 206, 214, 42, 38, -79, -60)) {
                event.setCancelled(true);
            }

            // Blue team
            if (contains(loc, 138, 146, 42, 38, -79, -58)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void preventspawnplace(BlockPlaceEvent event) {
        if (Utility.getArena().equals(name)) {
            Location loc = event.getBlock().getLocation();
            // Red team
            if (contains(loc, 206, 214, 42, 38, -79, -60)) {
                event.setCancelled(true);
            }

            // Blue team
            if (contains(loc, 138, 146, 42, 38, -79, -58)) {
                event.setCancelled(true);
            }
        }
    }

}
