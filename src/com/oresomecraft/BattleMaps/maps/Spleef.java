package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Spleef extends BattleMap implements IBattleMap, Listener {

    public Spleef() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.DIAMOND_SPADE});
    }

    // Map details
    String name = "spleef";
    String fullName = "Spleef";
    String creators = "Zachoz ";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyTDMSpawns() {
        // Dud
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 3, 66, 1));
        FFASpawns.add(new Location(w, 15, 66, 1));
        FFASpawns.add(new Location(w, -25, 66, 1));
        FFASpawns.add(new Location(w, -4, 66, -22));
        FFASpawns.add(new Location(w, -5, 66, 21));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
        p.setItem(0, Material.DIAMOND_SPADE, 1);
        p.setItem(1, Material.SNOW_BALL, 16);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    // Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

}
