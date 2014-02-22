package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.*;

import com.oresomecraft.OresomeBattles.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@MapConfig
public class MutinyII extends BattleMap implements IBattleMap, Listener {

    public MutinyII() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(12);
        disableDrops(new Material[]{Material.IRON_AXE});
        setAutoSpawnProtection(2);
    }

    String name = "mutinyii";
    String fullName = "Mutiny II";
    String creators = "__R3, Buster1824 and MiCkEyMiCE";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 32, 94, 12));
        redSpawns.add(new Location(w, 3, 85, 6));
        redSpawns.add(new Location(w, -24, 91, 13));
        blueSpawns.add(new Location(w, 31, 91, -58));
        blueSpawns.add(new Location(w, 3, 85, -51));
        blueSpawns.add(new Location(w, -25, 94, -57));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 31, 91, -58));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack ALLPROTECT = new ItemStack(Material.EMERALD, 1);

        ItemMeta allprotect = ALLPROTECT.getItemMeta();
        allprotect.setDisplayName(ChatColor.GREEN + "All Protect Stone");

        List<String> stoneLore = new ArrayList<String>();
        stoneLore.add(org.bukkit.ChatColor.BLUE + "Hold this while being attacked to reduce damage");
        allprotect.setLore(stoneLore);
        ALLPROTECT.setItemMeta(allprotect);

        i.setItem(0, new ItemStack(Material.IRON_SWORD, 1));
        i.setItem(1, new ItemStack(Material.BOW, 1));
        i.setItem(2, new ItemStack(Material.IRON_PICKAXE, 1));
        i.setItem(3, new ItemStack(Material.IRON_AXE, 1));
        i.setItem(4, new ItemStack(Material.BAKED_POTATO, 3));
        i.setItem(6, new ItemStack(Material.LOG, 64, (short) 1));
        i.setItem(5, new ItemStack(Material.GOLDEN_APPLE, 3));
        i.setItem(8, ALLPROTECT);
        i.setItem(9, new ItemStack(Material.ARROW, 64));

        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

    }

    public int x1 = 52;
    public int y1 = 54;
    public int z1 = 35;

    public int x2 = -48;
    public int y2 = 156;
    public int z2 = -75;

    @EventHandler
    public void preventPlaceOutOfMap(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)
                && !contains(event.getBlock().getLocation(), x1, x2, y1, y2, z1, z2)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void protectStone(EntityDamageEvent event) {
        if(!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (p.getItemInHand().getType().equals(Material.EMERALD)) {
                Random random = new Random();
                if (random.nextBoolean() && random.nextBoolean()) {
                    event.setDamage(event.getDamage() - 3);
                }
            }
        }
    }
}
