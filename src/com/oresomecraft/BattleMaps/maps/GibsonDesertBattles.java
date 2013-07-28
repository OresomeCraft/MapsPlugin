package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.api.InvUtils;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GibsonDesertBattles extends BattleMap implements IBattleMap, Listener {

    public GibsonDesertBattles() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
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

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

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
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_BOOTS});

            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setBoots(LEATHER_BOOTS);


            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, IRON_PICKAXE);
            i.setItem(3, IRON_AXE);
            i.setItem(4, BREAD);
            i.setItem(5, HEALTH);
            i.setItem(6, OAK_LOG);
            i.setItem(7, LADDER);
            i.setItem(8, FLOWER_POT);
            i.setItem(27, ARROW);

            //Give players invincibility and strength for 15 seconds when they spawn
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 15 * 20, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 15 * 20, 1));

        }
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
        if (event.getBlock().getLocation().equals(name)) {
            if (event.getBlock().getType() == Material.DIAMOND_BLOCK || event.getBlock().getType() == Material.IRON_BLOCK) {
                event.setCancelled(true);
            }
        }
    }

    //Clears armor drops
    @EventHandler(priority = EventPriority.NORMAL)
    public void death(PlayerDeathEvent event) {

        List<ItemStack> drops = event.getDrops();
        int amount = drops.size();
        int count = 0;

        for (int none = 0; none < amount; none++) {

            ItemStack i = drops.get(count);
            count++;
            Material mat = i.getType();

            if (mat == Material.BOW || mat == Material.LEATHER_BOOTS
                    || mat == Material.LEATHER_LEGGINGS
                    || mat == Material.LEATHER_CHESTPLATE
                    || mat == Material.LEATHER_HELMET) {

                i.setType(Material.AIR);

            }

        }
    }

}
