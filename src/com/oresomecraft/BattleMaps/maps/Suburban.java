package com.oresomecraft.BattleMaps.maps;

import java.util.ArrayList;

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

public class Suburban extends BattleMap implements IBattleMap, Listener {

    public Suburban() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "suburban";
    String fullName = "Suburban Complex";
    String creators = "R3creat3, zezo268, xannallax33 and kalikakitty";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        blueSpawns.add(new Location(w, 360, 40, -482));
        blueSpawns.add(new Location(w, 361, 52, -482));
        blueSpawns.add(new Location(w, 364, 41, -579));
        blueSpawns.add(new Location(w, 310, 47, -539));
        blueSpawns.add(new Location(w, 266, 41, -487));
        blueSpawns.add(new Location(w, 366, 41, -387));
        blueSpawns.add(new Location(w, 337, 47, -410));
        blueSpawns.add(new Location(w, 326, 53, -448));
        blueSpawns.add(new Location(w, 364, 54, -529));
        blueSpawns.add(new Location(w, 437, 58, -482));

        redSpawns.add(new Location(w, 360, 40, -482));
        redSpawns.add(new Location(w, 361, 52, -482));
        redSpawns.add(new Location(w, 364, 41, -579));
        redSpawns.add(new Location(w, 310, 47, -539));
        redSpawns.add(new Location(w, 266, 41, -487));
        redSpawns.add(new Location(w, 366, 41, -387));
        redSpawns.add(new Location(w, 337, 47, -410));
        redSpawns.add(new Location(w, 326, 53, -448));
        redSpawns.add(new Location(w, 364, 54, -529));
        redSpawns.add(new Location(w, 437, 58, -482));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, 360, 40, -482));
        FFASpawns.add(new Location(w, 361, 52, -482));
        FFASpawns.add(new Location(w, 364, 41, -579));
        FFASpawns.add(new Location(w, 310, 47, -539));
        FFASpawns.add(new Location(w, 266, 41, -487));
        FFASpawns.add(new Location(w, 366, 41, -387));
        FFASpawns.add(new Location(w, 337, 47, -410));
        FFASpawns.add(new Location(w, 326, 53, -448));
        FFASpawns.add(new Location(w, 364, 54, -529));
        FFASpawns.add(new Location(w, 437, 58, -482));
        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack PICK = new ItemStack(Material.IRON_PICKAXE, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack STONE = new ItemStack(Material.STONE, 64);
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
            i.setItem(2, PICK);
            i.setItem(3, STEAK);
            i.setItem(4, HEALTH_POTION);
            i.setItem(11, ARROWS);
            i.setItem(6, STONE);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 525;
    public int y1 = 0;
    public int z1 = 578;

    //Bottom right corner.
    public int x2 = -44;
    public int y2 = 232;
    public int z2 = 1136;

}
