package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class MutinyII extends BattleMap implements IBattleMap, Listener {

    public MutinyII() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "mutinyii";
    String fullName = "Mutiny II";
    String creators = "R3creat3, zachoz, Buster1824 and MiCkEyMiCE";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -18, 85, -52);
        Location blueSpawn = new Location(w, -18, 85, 13);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location obsSpawn = new Location(w, 7, 90, -19);
        FFASpawns.add(obsSpawn);

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        BattlePlayer p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
            p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
            p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

            // Give players invincibility for 8 seconds when they spawn.
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 8 * 20, 1));
        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 52;
    public int y1 = 54;
    public int z1 = 35;

    // Bottom right corner.
    public int x2 = -48;
    public int y2 = 156;
    public int z2 = -75;

    @EventHandler
    public void preventspawnexplosion(EntityExplodeEvent event) {
        if (Utility.getArena().equals(name)) {
            Location loc = event.getLocation();
            // Red team
            if (contains(loc, -12, -25, 88, 83, -46, -60)) event.setCancelled(true);


            // Blue team
            if (contains(loc, -12, -25, 88, 83, 20, 6)) event.setCancelled(true);
        }
    }

    @EventHandler
    public void spawnprotection(BlockBreakEvent event) {
        if (Utility.getArena().equals(name)) {
            Location loc = event.getBlock().getLocation();
            // Red team
            if (contains(loc, -12, -25, 88, 83, -46, -60)) event.setCancelled(true);

            // Blue team
            if (contains(loc, -12, -25, 88, 83, 20, 6)) event.setCancelled(true);
        }
    }

    @EventHandler
    public void spawnprotection2(BlockPlaceEvent event) {
        if (Utility.getArena().equals(name)) {
            Location loc = event.getBlock().getLocation();
            // Red team
            if (contains(loc, -12, -25, 88, 83, -46, -60)) {
                event.setCancelled(true);
            }

            // Blue team
            if (contains(loc, -12, -25, 88, 83, 20, 6)) {
                event.setCancelled(true);
            }
        }
    }

}