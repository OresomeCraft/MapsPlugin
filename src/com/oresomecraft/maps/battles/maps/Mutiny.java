package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@MapConfig(
        name = "mutiny",
        fullName = "Mutiny",
        creators = {"Trilexium", "JacquiRose", "Heartist"},
        gamemodes = {Gamemode.TDM}
)
@Region(
        x1 = 19,
        y1 = 51,
        z1 = 3,
        x2 = -57,
        y2 = 122,
        z2 = -93
)
@Attributes(
        autoSpawnProtection = true,
        disabledDrops = {Material.EMERALD, Material.ARROW, Material.BOW, Material.IRON_AXE, Material.DIAMOND_BOOTS, Material.GOLD_LEGGINGS, Material.IRON_CHESTPLATE, Material.LEATHER_HELMET}
)
public class Mutiny extends BattleMap implements Listener {

    public Mutiny() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 1, 77, -19));
        redSpawns.add(new Location(w, 1, 70, -60));
        blueSpawns.add(new Location(w, -38, 72, -38));
        blueSpawns.add(new Location(w, -36, 70, -18));
        blueSpawns.add(new Location(w, -36, 77, -56));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 1, 77, -19));
        FFASpawns.add(new Location(w, 1, 70, -60));
        FFASpawns.add(new Location(w, -38, 72, -38));
        FFASpawns.add(new Location(w, -36, 70, -18));
        FFASpawns.add(new Location(w, -36, 77, -56));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

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

        ItemMeta allProtect = ALLPROTECT.getItemMeta();
        allProtect.setDisplayName(ChatColor.GREEN + "All Protect Stone");

        List<String> stoneLore = new ArrayList<String>();
        stoneLore.add(ChatColor.BLUE + "Hold this while being attacked to reduce damage");
        allProtect.setLore(stoneLore);
        ALLPROTECT.setItemMeta(allProtect);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

        pl.getInventory().setBoots(DIAMOND_BOOTS);
        pl.getInventory().setLeggings(GOLD_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, AXE);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(4, LOGS);
        i.setItem(8, ALLPROTECT);
        i.setItem(10, ARROWS);

    }

    @EventHandler
    public void preventPlaceOutOfMap(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(getName())) {
            if (!isInsideRegion(event.getBlock().getLocation())) {
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
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (p.getItemInHand().getType() == Material.EMERALD) {
                if (Math.random() > 0.5) {
                    event.setDamage(event.getDamage() - 3);
                }
            }
        }
    }
}
