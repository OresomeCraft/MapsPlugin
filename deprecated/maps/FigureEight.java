package com.oresomecraft.maps.oresomekart.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.OresomeKartMap;
import com.oresomecraft.OresomeBattles.region.CuboidRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

@MapConfig(
        name = "figureeight",
        fullName = "Figure Eight Circuit",
        creators = {"__R3", "reggie449", "miniwolf35", "TiniDaDominator"},
        gamemodes = {Gamemode.ORESOMEKART}
)
@Attributes(
        allowBuild = false,
        fireSpread = false
)
public class FigureEight extends OresomeKartMap implements Listener {

    public FigureEight() {
        super.initiate(this);
        setFinishPoint(new CuboidRegion(new Location(w, 13, 52, -10), new Location(w, -9, 37, -12)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, 11, 35, 9), new Location(w, -3, 29, -3)));
        setLaps(5);
        setDrivableSurfaces(new Material[]{Material.COAL_BLOCK, Material.QUARTZ_BLOCK, Material.STONE});
        setBoostSurfaces(new Material[]{Material.LOG, Material.LOG_2, Material.BRICK});
        setJumpSurfaces(new Material[]{Material.BRICK});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 4, 42, -9, -30, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        // No items needed
    }

}
