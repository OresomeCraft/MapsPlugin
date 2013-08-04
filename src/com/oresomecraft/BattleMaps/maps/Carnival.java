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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Carnival extends BattleMap implements IBattleMap, Listener {

    public Carnival() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "carnival";
    String fullName = "Carnival";
    String creators = "R3creat3 and FaazM";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        redSpawns.add(new Location(w, -83, 39, 1836));
        redSpawns.add(new Location(w, -116, 33, 1832));
        redSpawns.add(new Location(w, -19, 29, 1832));
        redSpawns.add(new Location(w, -120, 29, 1831));
        redSpawns.add(new Location(w, -72, 25, 1831));
        redSpawns.add(new Location(w, -16, 21, 1831));
        redSpawns.add(new Location(w, -81, 16, 1831));
        redSpawns.add(new Location(w, -131, 12, 1831));
        redSpawns.add(new Location(w, -18, 5, 1831));
        redSpawns.add(new Location(w, -79, 21, 1831));
        redSpawns.add(new Location(w, -17, 49, 1831));

        blueSpawns.add(new Location(w, -83, 39, 1836));
        blueSpawns.add(new Location(w, -116, 33, 1832));
        blueSpawns.add(new Location(w, -19, 29, 1832));
        blueSpawns.add(new Location(w, -120, 29, 1831));
        blueSpawns.add(new Location(w, -72, 25, 1831));
        blueSpawns.add(new Location(w, -16, 21, 1831));
        blueSpawns.add(new Location(w, -81, 16, 1831));
        blueSpawns.add(new Location(w, -131, 12, 1831));
        blueSpawns.add(new Location(w, -18, 5, 1831));
        blueSpawns.add(new Location(w, -79, 21, 1831));
        blueSpawns.add(new Location(w, -17, 49, 1831));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, -83, 39, 1836));
        FFASpawns.add(new Location(w, -116, 33, 1832));
        FFASpawns.add(new Location(w, -19, 29, 1832));
        FFASpawns.add(new Location(w, -120, 29, 1831));
        FFASpawns.add(new Location(w, -72, 25, 1831));
        FFASpawns.add(new Location(w, -16, 21, 1831));
        FFASpawns.add(new Location(w, -81, 16, 1831));
        FFASpawns.add(new Location(w, -131, 12, 1831));
        FFASpawns.add(new Location(w, -18, 5, 1831));
        FFASpawns.add(new Location(w, -79, 21, 1831));
        FFASpawns.add(new Location(w, -17, 49, 1831));
        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(11, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -152;
    public int y1 = 0;
    public int z1 = 1816;

    //Bottom right corner.
    public int x2 = 5;
    public int y2 = 64;
    public int z2 = 1852;

}
