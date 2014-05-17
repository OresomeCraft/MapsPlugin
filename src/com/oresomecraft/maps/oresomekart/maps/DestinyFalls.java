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
public class DestinyFalls extends OresomeKartMap implements Listener {

    public DestinyFalls() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setFinishPoint(new CuboidRegion(new Location(w, -20, 103, -152), new Location(w, -23, 106, -159)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, -7, 122, 116), new Location(w, -10, 125, 125)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.STAINED_CLAY});
        setBoostSurfaces(new Material[]{Material.QUARTZ_BLOCK});
        setJumpSurfaces(new Material[]{Material.SMOOTH_BRICK});

        setFireSpread(false);
    }

    // Map details
    String name = "pillars";
    String fullName = "Destiny Falls";
    String[] creators = {"SuperDuckFace", "ep1cn00bt00b", "ninsai", "miniwolf35"};
    Gamemode[] modes = {Gamemode.ORESOMEKART};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -23, 104, -152, 90, 0));
        FFASpawns.add(new Location(w, -23, 104, -154, 90, 0));
        FFASpawns.add(new Location(w, -23, 104, -156, 90, 0));
        FFASpawns.add(new Location(w, -23, 104, -158, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        // No items needed
    }

}
