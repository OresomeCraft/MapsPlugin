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
        name = "raceway_kart",
        fullName = "Raceway",
        creators = {"Evil_Emo", "Turt1eManLol"},
        gamemodes = {Gamemode.ORESOMEKART}
)
@Attributes(
        allowBuild = false,
        fireSpread = false
)
public class Raceway extends OresomeKartMap implements Listener {

    public Raceway() {
        super.initiate(this);
        setFinishPoint(new CuboidRegion(new Location(w, -17, 77, 1), new Location(w, -15, 65, -15)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, -97, 72, -141), new Location(w, -95, 65, -153)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.COAL_BLOCK, Material.QUARTZ_BLOCK, Material.STONE});
        setBoostSurfaces(new Material[]{Material.GOLD_BLOCK, Material.DIAMOND_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -18, 67, 0, 1, 0));
        FFASpawns.add(new Location(w, -18, 67, -2, 1, 0));
        FFASpawns.add(new Location(w, -18, 67, -4, 1, 0));
        FFASpawns.add(new Location(w, -18, 67, -6, 1, 0));
        FFASpawns.add(new Location(w, -18, 67, -8, 1, 0));
        FFASpawns.add(new Location(w, -18, 67, -10, 1, 0));
        FFASpawns.add(new Location(w, -18, 67, -12, 1, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        // No items needed
    }

}
