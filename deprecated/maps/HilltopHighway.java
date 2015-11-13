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
        name = "hilltophighway",
        fullName = "Hilltop Highway",
        creators = {"SuperDuckFace", "ep1cn00bt00b", "Afridge1O1", "jslsa"},
        gamemodes = {Gamemode.ORESOMEKART}
)
@Attributes(
        allowBuild = false,
        fireSpread = false
)
public class HilltopHighway extends OresomeKartMap implements Listener {

    public HilltopHighway() {
        super.initiate(this);
        setFinishPoint(new CuboidRegion(new Location(w, -44, 91, 6), new Location(w, -41, 94, -5)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, -0, 98, 114), new Location(w, 2, 102, 126)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.SNOW_BLOCK});
        setBoostSurfaces(new Material[]{Material.IRON_BLOCK});
        setJumpSurfaces(new Material[]{Material.REDSTONE_BLOCK});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -44, 91, 4, 91, 0));
        FFASpawns.add(new Location(w, -44, 91, 2, 91, 0));
        FFASpawns.add(new Location(w, -44, 91, 0, 91, 0));
        FFASpawns.add(new Location(w, -44, 91, -1, 83, 0));
        FFASpawns.add(new Location(w, -44, 91, -3, 77, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        // No items needed
    }

}
