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
        name = "pillars",
        fullName = "Destiny Falls",
        creators = {"SuperDuckFace", "ep1cn00bt00b", "ninsai", "miniwolf35"},
        gamemodes = {Gamemode.ORESOMEKART}
)
@Attributes(
        allowBuild = false,
        fireSpread = false
)
public class DestinyFalls extends OresomeKartMap implements Listener {

    public DestinyFalls() {
        super.initiate(this);
        setFinishPoint(new CuboidRegion(new Location(w, -20, 103, -152), new Location(w, -23, 106, -159)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, -7, 122, 116), new Location(w, -10, 125, 125)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.STAINED_CLAY});
        setBoostSurfaces(new Material[]{Material.QUARTZ_BLOCK});
        setJumpSurfaces(new Material[]{Material.SMOOTH_BRICK});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -23, 104, -152, 90, 0));
        FFASpawns.add(new Location(w, -23, 104, -154, 90, 0));
        FFASpawns.add(new Location(w, -23, 104, -156, 90, 0));
        FFASpawns.add(new Location(w, -23, 104, -158, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        // No items needed
    }

}
