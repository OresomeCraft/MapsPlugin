package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class TempleLake extends BattleMap implements IBattleMap, Listener {

    public TempleLake() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{});
    }

    String name = "templelake";
    String fullName = "Temple Lake";
    String creators = "SquidMan9 ";
    Gamemode[] modes = {Gamemode.CTF, Gamemode.TDM, Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -31, 80, 14));
        blueSpawns.add(new Location(w, -31, 80, 114));

        Location blueFlag = new Location(w, -32, 76, 114);
        Location redFlag = new Location(w, -32, 76, 14);
        setCTFFlags(name, redFlag, blueFlag);

        setKoTHMonument(new Location(w, -33, 74, 64));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -31, 80, 14));
        FFASpawns.add(new Location(w, -31, 80, 114));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(10, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 158;
    public int y1 = 139;
    public int z1 = -160;
    //Bottom right corner.
    public int x2 = -171;
    public int y2 = 54;
    public int z2 = 156;
}
