package com.oresomecraft.maps.oresomekart.maps;

import com.oresomecraft.maps.oresomekart.OresomeKartMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import com.oresomecraft.maps.*;
import com.oresomecraft.maps.battles.*;
import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Kart extends OresomeKartMap implements Listener {

    public Kart() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setFinishPoint(new CuboidRegion(new Location(w, 37, 80, -47), new Location(w, 39, 74, -59)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, 35, 81, -7), new Location(w, 33, 72, 3)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.STONE, Material.DIAMOND_BLOCK});
        setBoostSurfaces(new Material[]{Material.GOLD_BLOCK, Material.DIAMOND_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});
    }

    // Map details
    String name = "kart";
    String fullName = "Kart";
    String[] creators = {"zachoz", "kevlar_miner", "pegabeavercorn"};
    Gamemode[] modes = {Gamemode.ORESOMEKART};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 34, 78, -51, 1, 0));
        FFASpawns.add(new Location(w, 34, 78, -52, 1, 0));
        FFASpawns.add(new Location(w, 34, 78, -53, 1, 0));
        FFASpawns.add(new Location(w, 34, 78, -54, 1, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        // No items needed
    }

}