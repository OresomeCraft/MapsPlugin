package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.*;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

import java.util.List;

public class AvondaleCastle extends BattleMap implements IBattleMap, Listener {

    public AvondaleCastle() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    // Map details
    String name = "avondae";
    String fullName = "Avondale Castle";
    String creators = "bumsonfire";
    Gamemode[] modes = {Gamemode.TDM, Gamdemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -327, 60, -515, -90, 0);
        Location blueSpawn = new Location(w, -275, 60, -515, 90, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -323, 60, -511, -90, 0));
        redSpawns.add(new Location(w, -323, 60, -519, -90, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -279, 60, -511, 90, 0));
        blueSpawns.add(new Location(w, -279, 60, -519, 90, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, -327, 60, -515, -90, 0));
        FFASpawns.add(new Location(w, -275, 60, -515, 90, 0));
        FFASpawns.add(new Location(w, -323, 60, -511, -90, 0));
        FFASpawns.add(new Location(w, -323, 60, -519, -90, 0));
        FFASpawns.add(new Location(w, -279, 60, -511, 90, 0));
        FFASpawns.add(new Location(w, -279, 60, -519, 90, 0));
        FFASpawns.add(new Location(w, -346, 73, -517, -90, 0));
        FFASpawns.add(new Location(w, -256, 73, -517, 90, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            IRON_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 1);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, ARROWS);

        }

    }

    // Top left corner.
    public int x1 = -237;
    public int y1 = 123;
    public int z1 = -419;

    //Bottom right corner.
    public int x2 = -368;
    public int y2 = 56;
    public int z2 = -608;

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

    //Clears item/armor drops
    @EventHandler(priority = EventPriority.NORMAL)
    public void death(PlayerDeathEvent event) {

        List<ItemStack> drops = event.getDrops();
        int amount = drops.size();
        int count = 0;

        for (int none = 0; none < amount; none++) {

            ItemStack i = drops.get(count);
            count++;
            Material mat = i.getType();

            if (mat == Material.BOW || mat == Material.IRON_BOOTS
                    || mat == Material.IRON_CHESTPLATE
                    || mat == Material.IRON_LEGGINGS
                    || mat == Material.IRON_HELMET
                    || mat == Material.IRON_SWORD
                    || mat == Material.COOKED_BEEF
                    || mat == Material.BOW
                    || mat == Material.ARROW) {

                i.setType(Material.AIR);

            }

        }
    }

}