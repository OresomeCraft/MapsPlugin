package com.oresomecraft.maps.oresomekart.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.oresomekart.OresomeKartMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;

@MapConfig
public class HilltopHighway extends OresomeKartMap implements Listener {

    public HilltopHighway() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setFinishPoint(new CuboidRegion(new Location(w, -44, 91, 6), new Location(w, -41, 94, -5)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, -0, 98, 114), new Location(w, 2, 102, 126)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.SNOW_BLOCK});
        setBoostSurfaces(new Material[]{Material.IRON_BLOCK});
        setJumpSurfaces(new Material[]{Material.REDSTONE_BLOCK});

        setFireSpread(false);
    }

    // Map details
    String name = "hilltophighway";
    String fullName = "Hilltop Highway";
    String[] creators = {"SuperDuckFace", "ep1cn00bt00b", "Afridge1O1", "jslsa"};
    Gamemode[] modes = {Gamemode.ORESOMEKART};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -44, 91, 4, 91, 0));
        FFASpawns.add(new Location(w, -44, 91, 2, 91, 0));
        FFASpawns.add(new Location(w, -44, 91, 0, 91, 0));
        FFASpawns.add(new Location(w, -44, 91, -1, 83, 0));
        FFASpawns.add(new Location(w, -44, 91, -3, 77, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        // No items needed
    }

}
