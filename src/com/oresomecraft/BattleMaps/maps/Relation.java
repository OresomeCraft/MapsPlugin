package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Relation extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Relation() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "relation";
    String fullName = "Relation";
    String creators = "ShaunDepro97";
    Gamemode[] modes = {Gamemode.FFA};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 64, 76, 64, 2, 0);
        Location blueSpawn = new Location(w, 64, 76, 64, 0, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 64, 92, 64, 2, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 64, 92, -64, 0, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 64, 76, 64, 2, 0);
        Location blueSpawn = new Location(w, 64, 76, 64, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 57, 84, 65, 2, 0));
        FFASpawns.add(new Location(w, 65, 84, 56, 2, 0));
        FFASpawns.add(new Location(w, 73, 84, 65, 2, 0));
        FFASpawns.add(new Location(w, 64, 84, 73, 2, 0));
        FFASpawns.add(new Location(w, 57, 76, 65, 2, 0));
        FFASpawns.add(new Location(w, 65, 76, 56, 2, 0));
        FFASpawns.add(new Location(w, 73, 76, 65, 2, 0));
        FFASpawns.add(new Location(w, 64, 76, 73, 2, 0));
        FFASpawns.add(new Location(w, 64, 92, 72, 2, 0));
        FFASpawns.add(new Location(w, 64, 92, 56, 2, 0));
        FFASpawns.add(new Location(w, 47, 88, 47, 2, 0));
        FFASpawns.add(new Location(w, 82, 88, 82, 2, 0));
        FFASpawns.add(new Location(w, 82, 80, 47, 2, 0));
        FFASpawns.add(new Location(w, 47, 80, 82, 2, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        BattlePlayer p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 92;
    public int y1 = 256;
    public int z1 = 92;

    //Bottom right corner.
    public int x2 = 38;
    public int y2 = 1;
    public int z2 = 38;

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

    @EventHandler
    public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player p = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        for (ItemStack item : drops) {
            Material mat = item.getType();

            if (mat == Material.IRON_SWORD
                    || mat == Material.BOW
                    || mat == Material.IRON_BOOTS
                    || mat == Material.IRON_LEGGINGS
                    || mat == Material.IRON_CHESTPLATE
                    || mat == Material.ARROW) {

                item.setType(Material.AIR);
            }
        }
    }

}
