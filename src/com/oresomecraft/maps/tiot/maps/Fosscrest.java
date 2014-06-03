package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.TiOTMap;
import com.oresomecraft.OresomeBattles.region.CuboidRegion;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import org.bukkit.Location;
import org.bukkit.event.Listener;

@MapConfig(
        name = "fosscrest",
        fullName = "Fosscrest Village",
        creators = {"__R3", "danielschroeder", "xXJazzerXx"},
        gamemodes = {Gamemode.TIOT}
)
@Attributes(
        allowBuild = false
)
public class Fosscrest extends TiOTMap {

    public Fosscrest() {
        super.initiate(this);
    }

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
