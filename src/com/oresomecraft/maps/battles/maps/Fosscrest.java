package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

public class Fosscrest extends BattleMap implements IBattleMap, Listener {

    public Fosscrest() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "fosscrest";
    String fullName = "Fosscrest Village";
    String creators = "danielschroeder, xXJazzerXx and AnomalousRei";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 241, 120, -1310));
        redSpawns.add(new Location(w, 236, 94, -1325));
        blueSpawns.add(new Location(w, 236, 94, -1325));
        blueSpawns.add(new Location(w, 235, 101, -1312));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 186, 112, -1293);
        Location blueSpawn = new Location(w, 185, 112, -1288);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 212, 131, -1274));
        FFASpawns.add(new Location(w, 237, 120, -1263));
        FFASpawns.add(new Location(w, 261, 116, -1294));
        FFASpawns.add(new Location(w, 241, 120, -1310));
        FFASpawns.add(new Location(w, 187, 112, -1269));
        FFASpawns.add(new Location(w, 219, 101, -1306));
        FFASpawns.add(new Location(w, 219, 101, -1306));
        FFASpawns.add(new Location(w, 244, 99, -1289));
        FFASpawns.add(new Location(w, 198, 113, -1288));
        FFASpawns.add(new Location(w, 187, 119, -1267));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

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
        i.setItem(9, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 86;
    public int y1 = 229;
    public int z1 = -1396;

    //Bottom right corner.
    public int x2 = 353;
    public int y2 = 73;
    public int z2 = -1187;

}
