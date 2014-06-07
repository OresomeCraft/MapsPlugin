package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "desert",
        fullName = "Gibson Desert Battles",
        creators = {"_Moist", "niceman506"},
        gamemodes = {Gamemode.TDM}
)
@Region(
        x1 = -55,
        y1 = 113,
        z1 = 52,
        x2 = 293,
        y2 = 39,
        z2 = -164
)
@Attributes(
        autoSpawnProtection = true,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.IRON_SWORD, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LAVA_BUCKET, Material.IRON_AXE}
)
public class GibsonDesertBattles extends BattleMap implements Listener {

    public GibsonDesertBattles() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, -10, 60, 3));
        redSpawns.add(new Location(w, 226, 60, -100));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -10, 60, 3));
        FFASpawns.add(new Location(w, 226, 60, -100));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 8);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack LADDER = new ItemStack(Material.LADDER, 8);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack OAK_LOG = new ItemStack(Material.LOG, 32);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);

        ItemStack C = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack B = new ItemStack(Material.LEATHER_BOOTS, 1);
        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{C, B});

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

    @EventHandler(priority = EventPriority.NORMAL)
    public void blockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(getName())) {
            if (event.getBlock().getType() == Material.DIAMOND_BLOCK) {
                event.setCancelled(true);
                event.getBlock().setType(Material.DIRT);
                event.getPlayer().sendMessage(ChatColor.RED + "Diamond Blocks are disabled on this map!");
            }
        }
    }

}
