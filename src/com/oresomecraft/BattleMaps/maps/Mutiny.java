package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mutiny extends BattleMap implements IBattleMap, Listener {

    public Mutiny() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.IRON_AXE, Material.DIAMOND_BOOTS, Material.GOLD_LEGGINGS, Material.IRON_CHESTPLATE, Material.LEATHER_HELMET});
    }

    String name = "mutiny";
    String fullName = "Mutiny";
    String creators = "DynaDavidson and JacquiRose";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 3, 72, -38));
        redSpawns.add(new Location(w, 1, 77, -19));
        redSpawns.add(new Location(w, 1, 70, -60));
        blueSpawns.add(new Location(w, -38, 72, -38));
        blueSpawns.add(new Location(w, -36, 70, -18));
        blueSpawns.add(new Location(w, -36, 77, -56));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 3, 72, -38));
        FFASpawns.add(new Location(w, 1, 77, -19));
        FFASpawns.add(new Location(w, 1, 70, -60));
        FFASpawns.add(new Location(w, -38, 72, -38));
        FFASpawns.add(new Location(w, -36, 70, -18));
        FFASpawns.add(new Location(w, -36, 77, -56));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LOGS = new ItemStack(Material.LOG, 12);
        ItemStack AXE = new ItemStack(Material.IRON_AXE, 1);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack ALLPROTECT = new ItemStack(Material.EMERALD, 1);

        ItemMeta allprotect = ALLPROTECT.getItemMeta();
        allprotect.setDisplayName(ChatColor.GREEN + "All Protect Stone");

        List<String> stoneLore = new ArrayList<String>();
        stoneLore.add(org.bukkit.ChatColor.BLUE + "Hold this while being attacked to reduce damage");
        allprotect.setLore(stoneLore);
        ALLPROTECT.setItemMeta(allprotect);

        p.getInventory().setBoots(DIAMOND_BOOTS);
        p.getInventory().setLeggings(GOLD_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(0, AXE);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(4, LOGS);
        i.setItem(8, ALLPROTECT);
        i.setItem(10, ARROWS);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 10 * 20, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 7 * 20, 1));


    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -71;
    public int y1 = 134;
    public int z1 = -100;

    //Bottom right corner.
    public int x2 = 29;
    public int y2 = 42;
    public int z2 = 25;

    @EventHandler
    public void preventPlaceOutOfMap(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) {
            if (event.getBlock().getLocation().getY() > 78 && getMode() == Gamemode.INFECTION) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You can't build past this height on infection!");
            }
            if (!contains(event.getBlock().getLocation(), x1, x2, y1, y2, z1, z2)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChangeItem(InventoryInteractEvent event) {
        if (event.getInventory().contains(Material.WOOD_SWORD)) {
            event.getInventory().remove(Material.WOOD_SWORD);
        }
    }

    @EventHandler
    public void protectStone(EntityDamageEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Player) {
                Player p = (Player) event.getEntity();
                if (p.getItemInHand().getType().equals(Material.EMERALD)) {
                    Random random = new Random();
                    if (random.nextBoolean()) {
                        event.setDamage(event.getDamage() - 5);
                    }
                }
            }
        }
    }
}
