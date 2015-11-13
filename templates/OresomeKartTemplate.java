package com.oresomecraft.maps.oresomekart.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.types.OresomeKartMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@MapConfig(
        name = "kart",
        fullName = "Template",
        creators = {"zachoz", "ScruffyRules"},
        gamemodes = {Gamemode.ORESOMEKART}
)
@Region(
        x1 = 0,
        y1 = 0,
        z1 = 0,
        x2 = 0,
        y2 = 0,
        z2 = 0
)
@Attributes(
        allowBuild = false,
        fireSpread = false
)
public class OresomeKartTemplate extends OresomeKartMap {

    public OresomeKartTemplate() {
        super.initiate(this);

        setFinishPoint(new CuboidRegion(new Location(w, 37, 80, -47), new Location(w, 39, 74, -59)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, 35, 81, -7), new Location(w, 33, 72, 3)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.STONE, Material.DIAMOND_BLOCK});
        setBoostSurfaces(new Material[]{Material.GOLD_BLOCK, Material.DIAMOND_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 34, 78, -51, 1, 0));
        FFASpawns.add(new Location(w, 34, 78, -52, 1, 0));
        FFASpawns.add(new Location(w, 34, 78, -53, 1, 0));
        FFASpawns.add(new Location(w, 34, 78, -54, 1, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        // No items needed
    }

}
