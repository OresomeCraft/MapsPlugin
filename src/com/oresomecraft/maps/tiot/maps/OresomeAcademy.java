package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import com.oresomecraft.maps.tiot.TiOTMap;
import org.bukkit.Location;
import org.bukkit.event.Listener;

@MapConfig
public class OresomeAcademy extends TiOTMap implements IBattleMap, Listener {

    public OresomeAcademy() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "academy";
    String fullName = "OresomeTown Academy";
    String creators = "meganlovesmusic and SuperDuckFace";
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 4, 63, -3, 90, 0));
        FFASpawns.add(new Location(w, 13, 63, -12, -0, 0));
        FFASpawns.add(new Location(w, 19, 63, -11, -0, 0));
        FFASpawns.add(new Location(w, 54, 63, 18));
        FFASpawns.add(new Location(w, 85, 63, 56, 90, 0));
        FFASpawns.add(new Location(w, 53, 63, 71, -180, 0));
        FFASpawns.add(new Location(w, 35, 63, 76));
        FFASpawns.add(new Location(w, 27, 63, 25));
        FFASpawns.add(new Location(w, 23, 64, 64));
        FFASpawns.add(new Location(w, 81, 64, 32));
        FFASpawns.add(new Location(w, 82, 64, 4));
        FFASpawns.add(new Location(w, 0, 63, 11));
        FFASpawns.add(new Location(w, 0, 63, 51));
        FFASpawns.add(new Location(w, -26, 63, 36));
        FFASpawns.add(new Location(w, -26, 68, 24, -0, 0));
        FFASpawns.add(new Location(w, -1, 64, 64));
        FFASpawns.add(new Location(w, -15, 64, 65));
        FFASpawns.add(new Location(w, -21, 64, 64));
        FFASpawns.add(new Location(w, 40, 76, 20));

        setCriminalTester(new CuboidRegion(new Location(w, 21, 63, 68), new Location(w, 25, 67, 71)));
    }

    public void readyTDMSpawns() {
        // No TDM spawns
    }

    public void applyInventory(final BattlePlayer p) {
        // No predefined inventory
    }

}
