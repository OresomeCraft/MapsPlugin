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
public class FigureEight extends OresomeKartMap implements Listener {

    public FigureEight() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setFinishPoint(new CuboidRegion(new Location(w, 13, 52, -10), new Location(w, -9, 37, -12)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, 11, 35, 9), new Location(w, -3, 29, -3)));
        setLaps(5);
        setDrivableSurfaces(new Material[]{Material.COAL_BLOCK, Material.QUARTZ_BLOCK, Material.STONE});
        setBoostSurfaces(new Material[]{Material.LOG, Material.LOG_2, Material.BRICK});
        setJumpSurfaces(new Material[]{Material.BRICK});
        setFireSpread(false);
    }

    // Map details
    String name = "figureeight";
    String fullName = "Figure Eight Circuit";
    String[] creators = {"__R3", "reggie449", "miniwolf35", "TiniDaDominator"};
    Gamemode[] modes = {Gamemode.ORESOMEKART};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 4, 42, -9, -30, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        // No items needed
    }

}
