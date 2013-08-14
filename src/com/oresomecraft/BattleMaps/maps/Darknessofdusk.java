package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.api.InvUtils;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.World;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Darknessofdusk extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Darknessofdusk() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "dusk";
    String fullName = "Darkness of Dusk";
    String creators = "R3creat3, xannallax33, dinner1111 and pepsidawg00";
    Gamemode[] modes = {Gamemode.INFECTION};

    //Tdm isn't enabled on this, don't need to do spawns.
    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, 62, 6, 23));
        FFASpawns.add(new Location(w, 62, 6, 149));
        FFASpawns.add(new Location(w, -46, 6, 147));
        FFASpawns.add(new Location(w, -48, 6, 44));

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
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
            ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
            ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

            p.getInventory().setBoots(DIAMOND_BOOTS);
            p.getInventory().setLeggings(GOLD_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, STONE_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 115;
    public int y1 = 57;
    public int z1 = -14;

    //Bottom right corner.
    public int x2 = -88;
    public int y2 = 0;
    public int z2 = 191;

}
