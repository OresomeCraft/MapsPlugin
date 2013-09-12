package com.oresomecraft.BattleMaps.maps;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Fairwick extends BattleMap implements IBattleMap, Listener {

    public Fairwick() {
        super.initiate(this, name, fullName, creators, modes);
    }

    String name = "fairwick";
    String fullName = "Fairwick Village";
    String creators = "R3creat3, ninsai and zachoz";
    Gamemode[] modes = {Gamemode.CTF};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 72, 73, 1));
        blueSpawns.add(new Location(w, 72, 73, 133));

        Location redFlag = new Location(w, 72, 74, -2);
        Location blueFlag = new Location(w, 72, 74, 136);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 72, 73, 1));
        FFASpawns.add(new Location(w, 72, 73, 133));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.GOLDEN_APPLE, 1);
        //Limit golden apples to one, since absorption was added.
        ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 1);
        ItemMeta s = SPY_WATCH.getItemMeta();
        s.setDisplayName(ChatColor.BLUE + "Spy Watch");

        List<String> sLore = new ArrayList<String>();
        sLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
        s.setLore(sLore);
        SPY_WATCH.setItemMeta(s);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, SPY_WATCH);
        i.setItem(9, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -15;
    public int y1 = 75;
    public int z1 = 231;

    //Bottom right corner.
    public int x2 = 129;
    public int y2 = 184;
    public int z2 = -10;

    @EventHandler
    public void onSpyWatchInteract(PlayerInteractEvent event){
        if(!event.getPlayer().getWorld().getName().equals(name)) return;
        Player p = event.getPlayer();
        Action a = event.getAction();
        if(a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK){
            if(p.getItemInHand().getType() == Material.WATCH){
                p.getInventory().remove(new ItemStack(Material.WATCH, 1));
                p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                p.getInventory().remove(new ItemStack(Material.IRON_BOOTS));
                p.getInventory().remove(new ItemStack(Material.IRON_LEGGINGS));
                p.getInventory().remove(new ItemStack(Material.IRON_HELMET));
                p.getInventory().remove(new ItemStack(Material.LEATHER_CHESTPLATE));
                p.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
                p.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
                p.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
                ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                InvUtils.colourArmourAccordingToTeam(BattlePlayer.getBattlePlayer(p), new ItemStack[]{LEATHER_CHESTPLATE});
                p.getInventory().addItem(LEATHER_CHESTPLATE);
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 15*20, 0));
            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name) && event.getBlock().getType().getId() != 102)
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name)) event.setCancelled(true);
    }
}
