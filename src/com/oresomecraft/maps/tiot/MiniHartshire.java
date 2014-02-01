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
public class MiniHartshire extends BattleMap implements IBattleMap, Listener {

    public MiniHartshire() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "minihartshire";
    String fullName = "Mini Hartshire TiOT";
    String creators = "__R3, kalikakitty, xannallax33 and 123Oblivious";
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -61, 91, 50));

        setCriminalTester(new CuboidRegion(new Location(w, -58, 91, 42), new Location(w, -57, 94, 39)));
    }

    public void readyTDMSpawns() {
        // No TDM spawns
    }

    public void applyInventory(final BattlePlayer p) {
        // No predefined inventory
    }

}