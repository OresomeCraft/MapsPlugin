package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import com.oresomecraft.OresomeBattles.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@MapConfig
public class Mutiny extends BattleMap implements Listener {

    public Mutiny() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.EMERALD, Material.ARROW, Material.BOW, Material.IRON_AXE, Material.DIAMOND_BOOTS, Material.GOLD_LEGGINGS, Material.IRON_CHESTPLATE, Material.LEATHER_HELMET});
        setAutoSpawnProtection(2);
    }

    String name = "mutiny";
    String fullName = "Mutiny";
    String[] creators = {"AnomalousDyna", "JacquiRose"};
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 3, 72, -38));
        redSpawns.add(new Location(w, 1, 77, -19));
        redSpawns.add(new Location(w, 1, 70, -60));
        blueSpawns.add(new Location(w, -38, 72, -38));
        blueSpawns.add(new Location(w, -36, 70, -18));
        blueSpawns.add(new Location(w, -36, 77, -56));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 3, 72, -38));
        FFASpawns.add(new Location(w, 1, 77, -19));
        FFASpawns.add(new Location(w, 1, 70, -60));
        FFASpawns.add(new Location(w, -38, 72, -38));
        FFASpawns.add(new Location(w, -36, 70, -18));
        FFASpawns.add(new Location(w, -36, 77, -56));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LOGS = new ItemStack(Material.LOG, 12);
        ItemStack AXE = new ItemStack(Material.IRON_AXE, 1);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack ALLPROTECT = new ItemStack(Material.EMERALD, 1);

        ItemMeta allProtect = ALLPROTECT.getItemMeta();
        allProtect.setDisplayName(ChatColor.GREEN + "All Protect Stone");

        List<String> stoneLore = new ArrayList<String>();
        stoneLore.add(ChatColor.BLUE + "Hold this while being attacked to reduce damage");
        allProtect.setLore(stoneLore);
        ALLPROTECT.setItemMeta(allProtect);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

        p.getInventory().setBoots(DIAMOND_BOOTS);
        p.getInventory().setLeggings(GOLD_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, AXE);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(4, LOGS);
        i.setItem(8, ALLPROTECT);
        i.setItem(10, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 19;
    public int y1 = 51;
    public int z1 = 3;

    //Bottom right corner.
    public int x2 = -57;
    public int y2 = 122;
    public int z2 = -93;

    @EventHandler
    public void preventPlaceOutOfMap(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) {
            if (!contains(event.getBlock().getLocation(), x1, x2, y1, y2, z1, z2)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChangeItem(InventoryInteractEvent event) {
        if (event.getInventory().contains(Material.WOOD_SWORD)) {
            event.getInventory().remove(Material.WOOD_SWORD);
        }
    }

    @EventHandler
    public void protectStone(EntityDamageEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (p.getItemInHand().getType() == Material.EMERALD) {
                if (Math.random() > 0.5) {
                    event.setDamage(event.getDamage() - 3);
                }
            }
        }
    }
}
