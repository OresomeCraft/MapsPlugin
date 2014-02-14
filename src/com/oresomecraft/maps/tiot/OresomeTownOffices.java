package com.oresomecraft.maps.tiot;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Location;
import org.bukkit.event.Listener;

@MapConfig
public class OresomeTownOffices extends BattleMap implements IBattleMap, Listener {

    public OresomeTownOffices() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "oresometownoffices";
    String fullName = "OresomeTown Offices";
    String creators = "meganlovesmusic, SuperDuckFace, ninsai and psgs";
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -47, 80, 0, -90, 0));
        FFASpawns.add(new Location(w, 48, 80, 0, 90, 0));
        FFASpawns.add(new Location(w, 0, 80, 48, 180, 0));
        FFASpawns.add(new Location(w, 0, 80, -47));
        FFASpawns.add(new Location(w, 16, 70, -17, 47, 0));
        FFASpawns.add(new Location(w, -17, 70, 16, -132, 0));
        FFASpawns.add(new Location(w, 4, 58, 40, 152, 0));
        FFASpawns.add(new Location(w, -3, 58, -50, -23, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 2, 65, 2), new Location(w, -2, 70, -2)));
    }

    public void readyTDMSpawns() {
        // No TDM spawns
    }

    public void applyInventory(final BattlePlayer p) {
        // No predefined inventory
    }

}
