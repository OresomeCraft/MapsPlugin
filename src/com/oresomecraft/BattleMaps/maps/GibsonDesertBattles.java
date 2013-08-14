package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class GibsonDesertBattles extends BattleMap implements IBattleMap, Listener {

    public GibsonDesertBattles() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LAVA_BUCKET});
    }

    String name = "desert";
    String fullName = "Gibson Desert Battles";
    String creators = "_Moist and niceman506";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        blueSpawns.add(new Location(w, -10, 60, 3));
        redSpawns.add(new Location(w, 226, 60, -100));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -10, 60, 3));
        FFASpawns.add(new Location(w, 226, 60, -100));

        setFFASpawns(name, FFASpawns);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        //Items
        ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 8);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack LADDER = new ItemStack(Material.LADDER, 8);
        ItemStack FLOWER_POT = new ItemStack(Material.FLOWER_POT, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack OAK_LOG = new ItemStack(Material.LOG, 32);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);

        // Armor
        ItemStack C = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack B = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack L = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack H = new ItemStack(Material.IRON_HELMET, 1);

        p.getInventory().setBoots(B);
        p.getInventory().setChestplate(C);
        p.getInventory().setLeggings(L);
        p.getInventory().setHelmet(H);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, IRON_AXE);
        i.setItem(4, BREAD);
        i.setItem(5, HEALTH);
        i.setItem(6, OAK_LOG);
        i.setItem(7, LADDER);
        i.setItem(27, ARROW);

        //Give players invincibility and strength for 15 seconds when they spawn
        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 15 * 20, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 15 * 20, 1));
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 41;
    public int y1 = 87;
    public int z1 = 29;

    //Bottom right corner.
    public int x2 = 220;
    public int y2 = 31;
    public int z2 = -117;

    //No diamond/iron blocks to drop from this, resulting in no diamond/iron armour ;3
    @EventHandler(priority = EventPriority.NORMAL)
    public void blockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name)) {
            if (event.getBlock().getType() == Material.DIAMOND_BLOCK || event.getBlock().getType() == Material.IRON_BLOCK) {
                event.setCancelled(true);
            }
        }
    }

}
