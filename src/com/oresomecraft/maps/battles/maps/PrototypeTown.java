package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class PrototypeTown extends BattleMap implements IBattleMap, Listener {

    public PrototypeTown() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
    }

    String name = "prototypetown";
    String fullName = "Prototype Town";
    String creators = "ninsai, SuperDuckFace, beadycottonwood and XUHAVON";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.CTF, Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -160, 65, -156));
        redSpawns.add(new Location(w, -158, 65, -127));
        redSpawns.add(new Location(w, -155, 65, -98));

        blueSpawns.add(new Location(w, -12, 65, -96));
        blueSpawns.add(new Location(w, -14, 65, -129));
        blueSpawns.add(new Location(w, -6, 65, -173));

        Location redFlag = new Location(w, -138, 66, -107);
        Location blueFlag = new Location(w, -6, 66, -150);
        setCTFFlags(name, redFlag, blueFlag);

        setKoTHMonument(new Location(w, -79, 68, -131));
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
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack ARROWS2 = new ItemStack(Material.ARROW, 64);


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
        i.setItem(11, ARROWS2);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -165;
    public int y1 = 112;
    public int z1 = -93;

    //Bottom right corner.
    public int x2 = 1;
    public int y2 = 54;
    public int z2 = -176;

}