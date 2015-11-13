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
        name = "racing",
        fullName = "Tree Top",
        creators = {"shavahn2003", "Yuzko"},
        gamemodes = {Gamemode.ORESOMEKART}
)
@Attributes(
        allowBuild = false,
        fireSpread = false
)
public class Treetop extends OresomeKartMap implements Listener {

    public Treetop() {
        super.initiate(this);
        setFinishPoint(new CuboidRegion(new Location(w, -51, 55, 0), new Location(w, -51, 63, -15)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, 21, 55, 38), new Location(w, 18, 60, 52)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.COAL_BLOCK, Material.QUARTZ_BLOCK});
        setBoostSurfaces(new Material[]{Material.GOLD_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -51, 56, -13, -90, 0));
        FFASpawns.add(new Location(w, -51, 56, -11, -90, 0));
        FFASpawns.add(new Location(w, -51, 56, -9, -90, 0));
        FFASpawns.add(new Location(w, -51, 56, -7, -90, 0));
        FFASpawns.add(new Location(w, -51, 56, -5, -90, 0));
        FFASpawns.add(new Location(w, -51, 56, -3, -90, 0));
        FFASpawns.add(new Location(w, -51, 56, -1, -90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        // No items needed
    }

}
