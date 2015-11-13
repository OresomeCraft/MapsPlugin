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
        name = "rainbow_ok",
        fullName = "Rainbow Road Power Build",
        creators = {"__R3", "reggie449", "WiiiFreak123"},
        gamemodes = {Gamemode.ORESOMEKART}
)
@Attributes(
        allowBuild = false,
        fireSpread = false
)
public class Rainbow extends OresomeKartMap implements Listener {

    public Rainbow() {
        super.initiate(this);
        setFinishPoint(new CuboidRegion(new Location(w, -1, 27, -1), new Location(w, 0, 39, 13)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, 19, 14, -54), new Location(w, 20, 50, -38)));
        setLaps(8);
        setDrivableSurfaces(new Material[]{Material.WOOL, Material.GOLD_BLOCK});
        setBoostSurfaces(new Material[]{Material.SANDSTONE, Material.DIAMOND_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -4, 30, 6));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        // No items needed
    }

}