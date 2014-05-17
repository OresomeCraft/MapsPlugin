package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Spleef extends ArcadeMap implements Listener {

    public Spleef() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.DIAMOND_SPADE});
        setAllowPhysicalDamage(false);
        setBuildLimit(66);
        setAllowPlace(false);
    }

    // Map details
    String name = "spleef";
    String fullName = "Spleef";
    String[] creators = {"zachoz"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 3, 66, 1));
        FFASpawns.add(new Location(w, 15, 66, 1));
        FFASpawns.add(new Location(w, -25, 66, 1));
        FFASpawns.add(new Location(w, -4, 66, -22));
        FFASpawns.add(new Location(w, -5, 66, 21));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack DIAMOND_SPADE = new ItemStack(Material.DIAMOND_SPADE, 1);
        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 16);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        InvUtils.nameItem(DIAMOND_SPADE, ChatColor.BLUE + "Spleefer's Shovel");

        i.setItem(0, DIAMOND_SPADE);
        i.setItem(1, SNOW_BALL);
        i.setItem(2, STEAK);
    }

}
