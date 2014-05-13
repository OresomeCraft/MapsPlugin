package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class ClashOfClayII extends BattleMap implements Listener {

    public ClashOfClayII() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(15);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.STONE_PICKAXE, Material.STONE_SWORD});
        disableBlocks(new Material[]{Material.WORKBENCH, Material.PISTON_MOVING_PIECE});
        setAutoSpawnProtection(10);
    }

    String name = "clashofclayii";
    String fullName = "Clash Of Clay II";
    String[] creators = {"_Moist", "__R3"};
    Gamemode[] modes = {Gamemode.TDM};

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
        defineRegion(x1, x2, y1, y2, z1, z2);
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

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

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

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 256;
    public int y1 = 169;
    public int z1 = -95;

    //Bottom right corner.
    public int x2 = -1;
    public int y2 = 54;
    public int z2 = 25;

    @EventHandler
    public void breakListener(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        if (location.getWorld().getName().equals(name)) {

            if (contains(location, 255, 246, 69, 88, -33, -19)) event.setCancelled(true);
            if (contains(location, 15, 23, 70, 88, -17, -30)) event.setCancelled(true);

            if (event.getBlock().getType() == Material.IRON_BLOCK) event.setCancelled(true);
        }
    }

    @EventHandler
    public void placeListener(BlockPlaceEvent event) {
        Location location = event.getBlock().getLocation();
        if (location.getWorld().getName().equals(name)) {

            // Method may not work
            if (event.getBlockPlaced().getType() == Material.LAVA) event.setCancelled(true);

            if (contains(location, 255, 246, 69, 88, -33, -19)) event.setCancelled(true);
            if (contains(location, 15, 23, 70, 88, -17, -30)) event.setCancelled(true);
        }
    }
}
