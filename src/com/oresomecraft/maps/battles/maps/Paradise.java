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
import org.bukkit.World;

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
        redSpawns.add(new Location(w, -102, 108, 103));

        blueSpawns.add(new Location(w, -9, 51, 111, -83));
    }

    public void readyFFASpawns() {
                   FFASpawns.add(new Location(w, -114, 108, -89, 54, 0));
                   FFASpawns.add(new Location(w, -145, 108, -41, 115, 0));
                   FFASpawns.add(new Location(w, -117, 108, -83, -90, 0));
                   FFASpawns.add(new Location(w, -102, 108, 103, 0, 0));
                   FFASpawns.add(new Location(w, -147, 108 -41, -115, 0));
                   FFASpawns.add(new Location(w, -137, 108, 48, 0, 0));
                   FFASpawns.add(new Location(w, -48, 106, 107, 90, 0));
                   FFASpawns.add(new Location(w, 2, 108, 101, 128, 0));
                   FFASpawns.add(new Location(w, 50, 112, 60, 90, 0));
                   FFASpawns.add(new Location(w, 57, 109, 26, 176, 0));
                   FFASpawns.add(new Location(w, 45, 111, -80, 20, 0));
                   FFASpawns.add(new Location(w, 13, 117, -146, -70, 0));



    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 32);



        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
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


}
