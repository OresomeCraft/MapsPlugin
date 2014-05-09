package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Hangar extends BattleMap implements Listener {

    public Hangar() {
        super.initiate(this, name, fullName, creators, modes);
        lockTime("night");
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.STONE_SWORD});
    }

    // Map details
    String name = "hangar";
    String fullName = "Hangar";
    String creators = "zdav2002 and kevlar_miner";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -31, 66, 15, 0, 0));
        redSpawns.add(new Location(w, -31, 66, -11, 0, 0));
        blueSpawns.add(new Location(w, 33, 66, -9, 0, 0));
        blueSpawns.add(new Location(w, 33, 66, 13, 0, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -30, 66, -10, 0, 0));
        FFASpawns.add(new Location(w, -30, 66, 17, 0, 0));
        FFASpawns.add(new Location(w, -2, 66, 37, 0, 0));
        FFASpawns.add(new Location(w, 8, 66, -2, 0, 0));
        FFASpawns.add(new Location(w, -18, 66, -26, 3, 0));
        FFASpawns.add(new Location(w, -3, 66, -28, 3, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
        ItemStack LEATHER_CAP = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_TUNIC = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_TUNIC);
        p.getInventory().setHelmet(LEATHER_CAP);

        i.setItem(0, STONE_SWORD);
        p.setItem(2, Material.COOKED_BEEF, 1);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 43;
    public int y1 = 77;
    public int z1 = 38;

    // Bottom right corner.
    public int x2 = -43;
    public int y2 = 64;
    public int z2 = -41;
}