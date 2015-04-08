package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "clashofclayii",
        fullName = "Clash Of Clay II",
        creators = {"_Moist", "iR3"},
        gamemodes = {Gamemode.TDM}
)
@Region(
        x1 = 256,
        y1 = 169,
        z1 = -95,
        x2 = -1,
        y2 = 54,
        z2 = 25
)
@Attributes(
        tdmTime = 15,
        autoSpawnProtection = true,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.STONE_PICKAXE, Material.STONE_SWORD},
        disabledBlocks = {Material.WORKBENCH, Material.PISTON_MOVING_PIECE}
)
public class ClashOfClayII extends BattleMap implements Listener {

    public ClashOfClayII() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location blueSpawn = new Location(w, 20, 77, -25);
        Location redSpawn = new Location(w, 222, 77, -27);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 20, 77, -25);
        Location blueSpawn = new Location(w, 250, 77, -27);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(redSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack BLUE_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 11);
        ItemStack RED_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 14);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LEATHER_CHESTPLATE.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
        LEATHER_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);
        ItemStack TORCH = new ItemStack(Material.TORCH, 16);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        STONE_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 100);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, PUMPKIN_PIE);
        i.setItem(4, APPLE);

        if (p.getTeamType() == Team.TDM_RED || p.getTeamType() == Team.LTS_RED) i.setItem(5, RED_STAINED_CLAY);
        if (p.getTeamType() == Team.TDM_BLUE || p.getTeamType() == Team.LTS_BLUE) i.setItem(5, BLUE_STAINED_CLAY);

        i.setItem(6, TORCH);
        i.setItem(27, ARROW);
    }

    @EventHandler
    public void breakListener(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        if (location.getWorld().getName().equals(getName())) {

            if (contains(location, 255, 246, 69, 88, -33, -19)) event.setCancelled(true);
            if (contains(location, 15, 23, 70, 88, -17, -30)) event.setCancelled(true);

            if (event.getBlock().getType() == Material.IRON_BLOCK) event.setCancelled(true);
        }
    }

    @EventHandler
    public void placeListener(BlockPlaceEvent event) {
        Location location = event.getBlock().getLocation();
        if (location.getWorld().getName().equals(getName())) {

            // Method may not work
            if (event.getBlockPlaced().getType() == Material.LAVA) event.setCancelled(true);

            if (contains(location, 255, 246, 69, 88, -33, -19)) event.setCancelled(true);
            if (contains(location, 15, 23, 70, 88, -17, -30)) event.setCancelled(true);
        }
    }
}
