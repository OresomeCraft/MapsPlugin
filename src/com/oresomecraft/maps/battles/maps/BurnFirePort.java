package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

import java.util.Random;

@MapConfig
public class BurnFirePort extends BattleMap implements Listener {

    public BurnFirePort() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
    }

    String name = "burnfireport";
    String fullName = "Burnfire Port";
    String[] creators = {"bumsonfire"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -130, 29, 152, 176, 0);
        Location blueSpawn = new Location(w, -52, 37, 41, 90, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -179, 27, 81, -89, 0));
        redSpawns.add(new Location(w, -149, 28, 93, -88, 0));
        redSpawns.add(new Location(w, -148, 26, 97, 0, 0));
        redSpawns.add(new Location(w, -88, 26, 106, 90, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -99, 40, 41, 90, 0));
        blueSpawns.add(new Location(w, -11, 33, 62, 45, 0));
        blueSpawns.add(new Location(w, -115, 30, 79, 90, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -106, 26, 112, 179, 0);
        Location blueSpawn = new Location(w, -50, 32, 71, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -130, 29, 152, 176, 0));
        FFASpawns.add(new Location(w, -52, 37, 41, 90, 0));
        FFASpawns.add(new Location(w, -179, 27, 81, -89, 0));
        FFASpawns.add(new Location(w, -149, 28, 93, -88, 0));
        FFASpawns.add(new Location(w, -148, 26, 97, 0, 0));
        FFASpawns.add(new Location(w, -88, 26, 106, 90, 0));
        FFASpawns.add(new Location(w, -99, 40, 41, 90, 0));
        FFASpawns.add(new Location(w, -11, 33, 62, 45, 0));
        FFASpawns.add(new Location(w, -115, 30, 79, 90, 0));
        FFASpawns.add(new Location(w, -86, 28, 87, 90, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH_POTION);
        i.setItem(2, STEAK);
        i.setItem(4, EXP);
        i.setItem(10, ARROW);
    }

    public int x1 = -199;
    public int y1 = 93;
    public int z1 = 197;

    public int x2 = -8;
    public int y2 = 1;
    public int z2 = 29;

    @EventHandler
    public void preventMoveOutOfMap(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)
                && !contains(event.getTo(), x1, x2, y1, y2, z1, z2)) {
            event.getPlayer().teleport(FFASpawns.get(new Random().nextInt(FFASpawns.size())));
        }
    }

}
