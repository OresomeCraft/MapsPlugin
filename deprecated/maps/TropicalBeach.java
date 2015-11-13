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
        name = "tropicalbeach",
        fullName = "Tropical Beach",
        creators = {"SereneMango", "ScruffyRules"},
        gamemodes = {Gamemode.ORESOMEKART}
)
@Attributes(
        allowBuild = false,
        fireSpread = false
)
public class TropicalBeach extends OresomeKartMap implements Listener {

    public TropicalBeach() {
        super.initiate(this);
        setFinishPoint(new CuboidRegion(new Location(w, 16, 79, -41), new Location(w, 14, 92, -21)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, -90, 90, -68), new Location(w, -87, 95, -72)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.SAND});
        setBoostSurfaces(new Material[]{Material.GOLD_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 12, 80, -37, 90, 0));
        FFASpawns.add(new Location(w, 12, 80, -34, 90, 0));
        FFASpawns.add(new Location(w, 12, 80, -31, 90, 0));
        FFASpawns.add(new Location(w, 12, 80, -28, 90, 0));
        FFASpawns.add(new Location(w, 12, 80, -25, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        // No items needed
    }

}
