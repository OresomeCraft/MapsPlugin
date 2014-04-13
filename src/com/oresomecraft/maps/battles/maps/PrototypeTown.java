package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.Listener;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class PrototypeTown extends BattleMap implements Listener {

    public PrototypeTown() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
    }

    String name = "ninduck";
    String fullName = "Prototype Town";
    String creators = "ninsai, SuperDuckFace, beadycottonwood and XUHAVON";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.CTF, Gamemode.KOTH, Gamemode.TIOT};

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
        FFASpawns.add(new Location(w, -126, 66, -108));
    }

    public void applyInventory(final BattlePlayer p) {

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
