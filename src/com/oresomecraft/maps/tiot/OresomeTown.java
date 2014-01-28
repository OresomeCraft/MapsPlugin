package com.oresomecraft.maps.tiot;

import org.bukkit.*;
import org.bukkit.event.*;

import com.oresomecraft.maps.*;
import com.oresomecraft.maps.battles.*;
import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class OresomeTown extends BattleMap implements IBattleMap, Listener {

    public OresomeTown() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "oresometowntiot";
    String fullName = "OresomeTown";
    String creators = "meganlovesmusic, SuperDuckFace, _Husky_";
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 70, -39));

        setCriminalTester(new CuboidRegion(new Location(w, -4, 73, -47), new Location(w, 0, 69, -45)));
    }

    public void readyTDMSpawns() {
        // No TDM spawns
    }

    public void applyInventory(final BattlePlayer p) {
        // No predefined inventory
    }

}