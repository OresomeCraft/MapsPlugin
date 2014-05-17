package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class GibsonDesertBattles extends BattleMap implements Listener {

    public GibsonDesertBattles() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.IRON_SWORD, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LAVA_BUCKET, Material.IRON_AXE});
        setAutoSpawnProtection(5);
    }

    String name = "desert";
    String fullName = "Gibson Desert Battles";
    String[] creators = {"_Moist", "niceman506"};
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, -10, 60, 3));
        redSpawns.add(new Location(w, 226, 60, -100));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -10, 60, 3));
        FFASpawns.add(new Location(w, 226, 60, -100));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        //Items
        ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 8);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack LADDER = new ItemStack(Material.LADDER, 8);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack OAK_LOG = new ItemStack(Material.LOG, 32);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);

        // Armor
        ItemStack C = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack B = new ItemStack(Material.LEATHER_BOOTS, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{C, B});

        p.getInventory().setBoots(B);
        p.getInventory().setChestplate(C);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, IRON_AXE);
        i.setItem(4, BREAD);
        i.setItem(5, HEALTH);
        i.setItem(6, OAK_LOG);
        i.setItem(8, LADDER);
        i.setItem(27, ARROW);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -55;
    public int y1 = 113;
    public int z1 = 52;

    //Bottom right corner.
    public int x2 = 293;
    public int y2 = 39;
    public int z2 = -164;

    @EventHandler(priority = EventPriority.NORMAL)
    public void blockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name)) {
            if (event.getBlock().getType() == Material.DIAMOND_BLOCK) {
                event.setCancelled(true);
                event.getBlock().setType(Material.DIRT);
                event.getPlayer().sendMessage(ChatColor.RED + "Diamond Blocks are disabled on this map!");
            }
        }
    }

}
