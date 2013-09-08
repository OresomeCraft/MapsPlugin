package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Equator extends BattleMap implements IBattleMap, Listener {

    public Equator() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
    }

    String name = "equator";
    String fullName = "Equator";
    String creators = "Afridge1O1, ViolentShadow, Numinex and SuperDuckFace";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.CTF, Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 53, 75, -24));
        redSpawns.add(new Location(w, 26, 74, -50));
        redSpawns.add(new Location(w, 60, 75, 12));

        blueSpawns.add(new Location(w, -53, 75, 24));
        blueSpawns.add(new Location(w, -26, 74, 50));
        blueSpawns.add(new Location(w, -60, 74, -12));

        Location redFlag = new Location(w, 75, 76, -4);
        Location blueFlag = new Location(w, -75, 76, 4);
        setCTFFlags(name, redFlag, blueFlag);

        setKoTHMonument(new Location(w, 0, 69, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 2, 84, -48));
        FFASpawns.add(new Location(w, -3, 84, 58));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);


        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(4, EXP);
        i.setItem(10, ARROWS);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 103;
    public int y1 = 115;
    public int z1 = 103;

    //Bottom right corner.
    public int x2 = -103;
    public int y2 = 0;
    public int z2 = -103;

}
