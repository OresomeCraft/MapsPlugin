package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.api.InvUtils;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemodes.TDM;
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

public class ClashOfClayII extends BattleMap implements IBattleMap, Listener {

    public ClashOfClayII() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "clashofclayii";
    String fullName = "Clash Of Clay II";
    String creators = "_Moist and R3creat3";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, 20, 77, -25);
        Location blueSpawn = new Location(w, 250, 77, -27);

        redSpawns.add(redSpawn);

        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, 20, 77, -25);
        Location blueSpawn = new Location(w, 250, 77, -27);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(redSpawn);
        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack WOODEN_SWORD = new ItemStack(Material.WOOD_SWORD, 1, (short) -16373);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
            ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
            ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
            ItemStack BLUE_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 11);
            ItemStack RED_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 14);
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LEATHER_CHESTPLATE.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
            ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
            ItemStack TORCH = new ItemStack(Material.TORCH, 16);
            ItemStack ARROW = new ItemStack(Material.ARROW, 1);

            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setHelmet(DIAMOND_HELMET);
            BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

            i.setItem(0, WOODEN_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, IRON_PICKAXE);
            i.setItem(3, PUMPKIN_PIE);
            i.setItem(4, APPLE);
            if (TDM.isRed(p.getName())) {
                i.setItem(5, RED_STAINED_CLAY);
            }
            if (TDM.isBlue(p.getName())) {
                i.setItem(5, BLUE_STAINED_CLAY);
            }

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
        if (!contains(event.getBlock().getLocation(), 250, 77, -27, 3, 132, 18)) event.setCancelled(true);
        //Prevent placing off the map
    }
}
