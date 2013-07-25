package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class ClashOfClay extends BattleMap implements IBattleMap, Listener {

    public ClashOfClay() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "clashofclay";
    String fullName = "Clash Of Clay";
    String creators = "_Moist and niceman506";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, -22, 81, 8);
        Location blueSpawn = new Location(w, -23, 81, 164);

        redSpawns.add(redSpawn);

        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, -22, 81, 8);
        Location blueSpawn = new Location(w, -23, 81, 164);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(redSpawn);
        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        BattlePlayer p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack WOODEN_SWORD = new ItemStack(Material.WOOD_SWORD, 1, (short) -16373);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
            //Make the pick semi-unlimited - R3
            ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
            ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
            ItemStack BLUE_STAINED_CLAY = new ItemStack(Material.CLAY, 48);
            //Give a LITTLE more clay - R3
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
            //Make the rushers a little less weak, about 10% less damage/
            ItemStack TORCH = new ItemStack(Material.TORCH, 16);
            ItemStack ARROW = new ItemStack(Material.ARROW, 1);

            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setHelmet(DIAMOND_HELMET);
            BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

            i.setItem(0, WOODEN_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, IRON_PICKAXE);
            i.setItem(3, PUMPKIN_PIE);
            i.setItem(4, APPLE);
            i.setItem(5, BLUE_STAINED_CLAY);
            i.setItem(6, TORCH);
            i.setItem(27, ARROW);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    //Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

    @EventHandler
    public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player p = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        for (ItemStack item : drops) {
            Material mat = item.getType();

            if (mat == Material.DIAMOND_HELMET || mat == Material.WOOD_SWORD) {

                item.setType(Material.AIR);
            }
        }
    }

    @EventHandler
    public void place(org.bukkit.event.block.BlockPlaceEvent event) {
        if (contains(event.getBlock().getLocation(), -24, -21, 81, 84, 165, 162)) event.setCancelled(true);
        if (contains(event.getBlock().getLocation(), -21, -24, 81, 86, 7, 10)) event.setCancelled(true);
    }
    //May be incorrect, if not, fix.
}
