package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.World;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Elements extends BattleMap implements IBattleMap, Listener {

    public Elements() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setTDMTime(12);
    }

    String name = "elements";
    String fullName = "Elements";
    String creators = "broddikill, koolguydude4 and MiCkEyMiCE";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {

        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, -23, 87, 11));
        blueSpawns.add(new Location(w, -25, 86, 147));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -23, 87, 11));
        FFASpawns.add(new Location(w, -25, 86, 147));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);
            ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack LOG = new ItemStack(Material.LOG, 25);
            ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
            ItemStack STONE_PICK = new ItemStack(Material.STONE_PICKAXE, 1);

            i.setItem(0, STONE_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STONE_PICK);
            i.setItem(3, HEALTH);
            i.setItem(4, LOG);
            i.setItem(11, ARROWS);
            i.setItem(8, new ItemStack(Material.BREAD, 3));

            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 20 * 20, 2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15000 * 20, 2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 2));

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -51;
    public int y1 = 74;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 7;
    public int y2 = 171;
    public int z2 = 166;

    @EventHandler
    public void noSpawnBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (contains(loc, -34, -15, 85, 129, 155, 138)) event.setCancelled(true);
        if (contains(loc, -12, -33, 82, 130, 2, 20)) event.setCancelled(true);
    }

    @EventHandler
    public void noSpawnPlace(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if (contains(loc, -34, -15, 85, 129, 155, 138)) event.setCancelled(true);
        if (contains(loc, -12, -33, 82, 130, 2, 20)) event.setCancelled(true);
    }
}
