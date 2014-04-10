package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@MapConfig
public class Oasis extends BattleMap implements IBattleMap, Listener {

    public Oasis() {
        setAllowBuild(false);
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS});
        lockTime("day");
        setAutoSpawnProtection(10);
    }

    String name = "chasm";
    String fullName = "The Chasm";
    String creators = "ep1cn00bt00b and miniwolf35";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.KOTH, Gamemode.CTF};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 6, 87, 76, 358.9F, -0.7F);
        Location blueSpawn = new Location(w, -6, 87, -151, 178.9F, -1.1F);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, 6, 82, 56);
        Location blueFlag = new Location(w, -6, 82, -130);
        setCTFFlags(name, redFlag, blueFlag);
        setKoTHMonument(new Location(w, 0, 87, -37));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 6, 87, 76, 358.9F, -0.7F);
        Location blueSpawn = new Location(w, -6, 87, -151, 178.9F, -1.1F);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 16);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
;
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(10, ARROWS);

    }
}
