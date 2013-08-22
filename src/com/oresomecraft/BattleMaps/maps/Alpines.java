package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Alpines extends BattleMap implements IBattleMap, Listener {

    public Alpines() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
    }

    String name = "alpines";
    String fullName = "Alpines";
    String creators = "R3creat3, simonwilson123 and Evil_Emo";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {

        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, 2, 84, -48));

        blueSpawns.add(new Location(w, -3, 84, 58));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

        setKoTHMonument(new Location(w, -1, 133, 4));
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        setFFASpawns(name, FFASpawns);
        FFASpawns.add(new Location(w, 2, 84, -48));

        FFASpawns.add(new Location(w, -3, 84, 58));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH);
        i.setItem(11, ARROWS);
        i.setItem(8, new ItemStack(Material.BREAD, 3));

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 29;
    public int y1 = 142;
    public int z1 = 56;

    //Bottom right corner.
    public int x2 = -203;
    public int y2 = 42;
    public int z2 = -72;
}
