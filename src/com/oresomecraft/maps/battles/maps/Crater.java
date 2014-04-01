package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import net.minecraft.util.io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class Crater extends BattleMap implements IBattleMap, Listener {

    public Crater() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
        lockTime("night");
        setAutoSpawnProtection(4);
        setTDMTime(10);
    }

    String name = "crater";
    String fullName = "Crater Site";
    String creators = "__R3, psgs and Spantezian";
    Gamemode[] modes = {Gamemode.CTF, Gamemode.TDM};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -55, 81, 57);
        Location blueSpawn = new Location(w, -14, 81, -53);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, -14, 84, 46);
        Location blueFlag = new Location(w, -57, 84, -43);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -55, 81, 57);
        Location blueSpawn = new Location(w, -14, 81, -53);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -34, 79, 18));
        FFASpawns.add(new Location(w, -33, 80, -22));
        FFASpawns.add(new Location(w, -5, 79, 23));
        FFASpawns.add(new Location(w, -60, 79, -16));
        FFASpawns.add(new Location(w, 0, 78, -25));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack S = new ItemStack(Material.STICK, 1);
        ItemStack I = new ItemStack(Material.IRON_INGOT, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        LEATHER_HELMET.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack SAND = new ItemStack(Material.SAND, 4);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(8, SAND);
        i.setItem(10, ARROWS);
        i.setItem(11, S);
        i.setItem(12, I);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -207;
    public int y1 = 52;
    public int z1 = -1220;

    //Bottom right corner.
    public int x2 = -38;
    public int y2 = 112;
    public int z2 = -1125;

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        if (getMode() == Gamemode.CTF) return;
        if (!event.getBlock().getWorld().getName().equals(name)) return;
        if (!(event.getBlock().getType() == Material.TNT)) return;
        event.getPlayer().getInventory().removeItem(new ItemStack(Material.TNT, 1));
        event.getBlock().getWorld().playSound(event.getBlock().getLocation(), Sound.FUSE, 1, 1);
        event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
    }


    @EventHandler
    public void pigZombieHit(EntityDamageEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Creeper) {
                event.setDamage(1000);
            }
        }
    }

    @EventHandler
    public void c(EntityDeathEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Creeper) {
                event.getDrops().clear();
                ItemStack FIRE = new ItemStack(Material.SULPHUR, 1);
                ItemMeta fMeta = FIRE.getItemMeta();
                fMeta.setDisplayName(ChatColor.BLUE + "Enhanced Gunpowder");

                List<String> fLore = new ArrayList<String>();
                fLore.add(org.bukkit.ChatColor.BLUE + "Make some TNT yo!");
                fMeta.setLore(fLore);
                FIRE.setItemMeta(fMeta);

                event.getDrops().add(FIRE);
            }
        }
    }

    @EventHandler
    public void decayLag(LeavesDecayEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) event.setCancelled(true);
    }
}
