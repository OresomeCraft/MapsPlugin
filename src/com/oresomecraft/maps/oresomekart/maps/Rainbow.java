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
public class Rainbow extends OresomeKartMap implements Listener {

    public Rainbow() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setFinishPoint(new CuboidRegion(new Location(w, -1, 27, -1), new Location(w, 0, 39, 13)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, 19, 14, -54), new Location(w, 20, 50, -38)));
        setLaps(8);
        setDrivableSurfaces(new Material[]{Material.WOOL, Material.GOLD_BLOCK});
        setBoostSurfaces(new Material[]{Material.SANDSTONE, Material.DIAMOND_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});

        setFireSpread(false);

    }

    // Map details
    String name = "rainbow_ok";
    String fullName = "Rainbow Road Power Build";
    String[] creators = {"__R3", "reggie449", "WiiiFreak123"};
    Gamemode[] modes = {Gamemode.ORESOMEKART};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -4, 30, 6));
    }

    public void applyInventory(final BattlePlayer p) {
        // No items needed
    }

}