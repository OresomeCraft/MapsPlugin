package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class Paradise extends BattleMap implements IBattleMap, Listener {

    public Paradise() {
        super.initiate(this, name, fullName, creators, modes);
    }

    // Map details
    String name = "modern";
    String fullName = "Paradise";
    String creators = "SuperDuckFace, meganlovesmusic and ninsai";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 0, -102, 108, 103, 0));

        blueSpawns.add(new Location(w, -9, 51, 111, -83, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, -102, 108, 103, 0));
        FFASpawns.add(new Location(w, -9, 51, 111, -83, 0));
        FFASpawns.add(new Location(w, 0, -112, -103));
        FFASpawns.add(new Location(w, -1, -123, 106, -111, -0));
        FFASpawns.add(new Location(w, 0, -62, 106, -92, 0));
        FFASpawns.add(new Location(w, 0, -105, 107, -84, 0));
        FFASpawns.add(new Location(w, 0, -120, 108, -52, 0));
        FFASpawns.add(new Location(w, -125, 108, 1));
        FFASpawns.add(new Location(w, -122, 107, 67));
        FFASpawns.add(new Location(w, -90, 105, 94));
        FFASpawns.add(new Location(w, -47, 77, 41));
        FFASpawns.add(new Location(w, -25, 80, 5));
        FFASpawns.add(new Location(w, -27, 76, -54));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 32);

        ItemMeta iMeta = IRON_AXE.getItemMeta();
        iMeta.setDisplayName(ChatColor.AQUA + "Ground Shaker");

        List<String> iLore = new ArrayList<String>();
        iLore.add(ChatColor.AQUA + "Right click with this to make a EarthQuake!");
        iMeta.setLore(iLore);
        IRON_AXE.setItemMeta(iMeta);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_AXE);
        i.setItem(3, HEALTH_POTION);
        i.setItem(11, ARROW);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    // Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getPlayer().getItemInHand().getType().equals(Material.IRON_AXE)) {
                    for (Entity entity : event.getPlayer().getNearbyEntities(10, 10, 10)) {
                        if (entity instanceof Player) {
                            Player player = (Player) entity;
                            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 5 * 20, 1));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 4 * 20, 1));
                        }
                    }

                }
            }
        }
    }
}