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
public class Raceway extends OresomeKartMap implements Listener {

    public Raceway() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setFinishPoint(new CuboidRegion(new Location(w, -17, 77, 1), new Location(w, -15, 65, -15)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, -97, 72, -141), new Location(w, -95, 65, -153)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.COAL_BLOCK, Material.QUARTZ_BLOCK, Material.STONE});
        setBoostSurfaces(new Material[]{Material.GOLD_BLOCK, Material.DIAMOND_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});

        setFireSpread(false);
    }

    // Map details
    String name = "raceway_kart";
    String fullName = "Raceway";
    String[] creators = {"Evil_Emo", "Turt1eManLol"};
    Gamemode[] modes = {Gamemode.ORESOMEKART};

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
        // No items needed
    }

}
