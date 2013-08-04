package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class BurnFirePort extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public BurnFirePort() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "burnfireport";
    String fullName = "Burnfire Port";
    String creators = "bumsonfire";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

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
        blueSpawns.add(new Location(w, -86, 28, 87, 90, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {
        World w = Bukkit.getServer().getWorld(name);

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

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE,
                    1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1,
                    (short) 16373);
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
            i.setItem(2, HEALTH_POTION);
            i.setItem(3, STEAK);
            i.setItem(4, EXP);
            i.setItem(10, ARROW);

        }
    }

    public int x1 = -212;
    public int y1 = 77;
    public int z1 = 15;
    public int x2 = -2;
    public int y2 = 1;
    public int z2 = 215;

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventoutofmap(PlayerMoveEvent event) {

        if (event.getPlayer().getLocation().getWorld().getName().equals(name)) {
            if (!(contains(event.getPlayer().getLocation(), x1, x2, y1, y2, z1,
                    z2))) {
                event.setCancelled(true);
            }
        }
    }

}
