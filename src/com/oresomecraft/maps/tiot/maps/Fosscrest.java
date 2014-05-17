package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.tiot.TiOTMap;
import org.bukkit.Location;
import org.bukkit.event.Listener;

@MapConfig
public class Fosscrest extends TiOTMap implements Listener {

    public Fosscrest() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "fosscrest";
    String fullName = "Fosscrest Village";
    String[] creators = {"__R3", "danielschroeder", "xXJazzerXx"};
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 231, 103, -1292));
        FFASpawns.add(new Location(w, 234, 101, -1308));
        FFASpawns.add(new Location(w, 206, 107, -1287));
        FFASpawns.add(new Location(w, 203, 111, -1269));
        FFASpawns.add(new Location(w, 173, 119, -1268));

        setCriminalTester(new CuboidRegion(new Location(w, 185, 108, -1298), new Location(w, 189, 110, -1301)));
    }

    public void applyInventory(final BattlePlayer p) {
        // No predefined inventory
    }

}
