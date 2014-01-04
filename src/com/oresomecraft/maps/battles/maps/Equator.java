package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Equator extends BattleMap implements IBattleMap, Listener {

    public Equator() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.GLASS, Material.LEATHER_HELMET, Material.STONE_SWORD});
        setAutoSpawnProtection(4);
    }

    String name = "equator";
    String fullName = "Equator";
    String creators = "Afridge1O1, SuperDuckFace, Numinex, XUHAVON, beadycottonwood and ViolentShadow";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.CTF};

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

        ItemStack BLUE_GLASS = new ItemStack(Material.STAINED_GLASS, 1, (short) 11);
        ItemStack RED_GLASS = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);

        if(p.getTeam() == Team.TDM_RED); p.getInventory().setHelmet(RED_GLASS);
        if(p.getTeam() == Team.TDM_BLUE); p.getInventory().setHelmet(BLUE_GLASS);

        i.setItem(0, SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(4, EXP);
        i.setItem(10, ARROWS);

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
