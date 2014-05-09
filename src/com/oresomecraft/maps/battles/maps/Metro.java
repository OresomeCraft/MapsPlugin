package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Metro extends BattleMap implements Listener {

    public Metro() {
        super.initiate(this, name, fullName, creators, modes);
        lockTime("night");
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
    }

    String name = "metro";
    String fullName = "Metro";
    String creators = "zdav2002 and JacquiRose";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 4, 73, -26));
        redSpawns.add(new Location(w, 20, 73, -24));
        blueSpawns.add(new Location(w, -5, 69, 1));
        blueSpawns.add(new Location(w, -1, 68, -5));
    }

    public void readyFFASpawns() {
        redSpawns.add(new Location(w, 4, 73, -26));
        redSpawns.add(new Location(w, 20, 73, -24));
        blueSpawns.add(new Location(w, -5, 69, 1));
        blueSpawns.add(new Location(w, -1, 68, -5));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, SWORD);
        i.setItem(1, STEAK);
        i.setItem(2, HEALTH);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -51;
    public int y1 = 94;
    public int z1 = -50;

    //Bottom right corner.
    public int x2 = 59;
    public int y2 = 63;
    public int z2 = 34;
}