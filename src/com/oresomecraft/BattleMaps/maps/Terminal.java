package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Terminal extends BattleMap implements IBattleMap, Listener {

    public Terminal() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "terminal";
    String fullName = "Terminal";
    String creators = "Zachoz, XxXShadowSoul and Slider302";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -72, 71, -1208, -1, 0);

        Location blueSpawn = new Location(w, -116, 66, -1140, -178, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -72, 71, -1208, -1, 0);
        Location blueSpawn = new Location(w, -116, 66, -1140, -178, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -143, 66, -1211, -50, 0));
        FFASpawns.add(new Location(w, -142, 66, -1171, -108, 0));
        FFASpawns.add(new Location(w, -141, 66, -1142, -152, 0));
        FFASpawns.add(new Location(w, -127, 70, -1158, -162, 0));
        FFASpawns.add(new Location(w, -112, 66, -1162, 116, 0));
        FFASpawns.add(new Location(w, -125, 71, -1132, -95, 0));
        FFASpawns.add(new Location(w, -101, 71, -1159, -51, 0));
        FFASpawns.add(new Location(w, -109, 71, -1162, -132, 0));
        FFASpawns.add(new Location(w, -97, 66, -1171, 156, 0));
        FFASpawns.add(new Location(w, -83, 71, -1182, 41, 0));
        FFASpawns.add(new Location(w, -78, 71, -1188, -88, 0));
        FFASpawns.add(new Location(w, -43, 71, -1168, 124, 0));
        FFASpawns.add(new Location(w, -48, 71, -1197, 90, 0));
        FFASpawns.add(new Location(w, -91, 71, -1207, -89, 0));
        FFASpawns.add(new Location(w, -121, 71, -1190, 121, 0));
        FFASpawns.add(new Location(w, -139, 71, -1192, 89, 0));
        FFASpawns.add(new Location(w, -114, 66, -1191, 43, 0));
        FFASpawns.add(new Location(w, -77, 71, -1169, -90, 0));
        FFASpawns.add(new Location(w, -58, 71, -1147, 156, 0));
        FFASpawns.add(new Location(w, -91, 71, -1140, 141, 0));

        // Add spawns to list. (Don't change!)
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
            i.setItem(4, ARROWS);

        }
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

}
