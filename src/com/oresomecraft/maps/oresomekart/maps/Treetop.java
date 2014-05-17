package com.oresomecraft.maps.oresomekart.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.oresomekart.OresomeKartMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MapConfig
public class Treetop extends OresomeKartMap implements Listener {

    public Treetop() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setFinishPoint(new CuboidRegion(new Location(w, -51, 55, 0), new Location(w, -51, 63, -15)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, 21, 55, 38), new Location(w, 18, 60, 52)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.COAL_BLOCK, Material.QUARTZ_BLOCK});
        setBoostSurfaces(new Material[]{Material.GOLD_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});

        setFireSpread(false);
    }

    // Map details
    String name = "racing";
    String fullName = "Tree Top";
    String[] creators = {"shavahn2003", "Yuzko"};
    Gamemode[] modes = {Gamemode.ORESOMEKART};

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
        // No items needed
    }

    @EventHandler
    public void onKartMove(PlayerMoveEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        List<Material> allowedSurfaces = new ArrayList<Material>(Arrays.asList(Material.COAL_BLOCK, Material.QUARTZ_BLOCK, Material.GOLD_BLOCK));
        if (!allowedSurfaces.contains(event.getTo().getBlock().getType())) {
            event.getPlayer().setHealth(event.getPlayer().getHealth() - 0.3);
        }
    }

}
