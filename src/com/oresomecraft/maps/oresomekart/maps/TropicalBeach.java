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
public class TropicalBeach extends OresomeKartMap implements Listener {

    public TropicalBeach() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setFinishPoint(new CuboidRegion(new Location(w, 16, 79, -41), new Location(w, 14, 92, -21)));
        setHalfwayCheckPoint(new CuboidRegion(new Location(w, -90, 90, -68), new Location(w, -87, 95, -72)));
        setLaps(3);
        setDrivableSurfaces(new Material[]{Material.SAND});
        setBoostSurfaces(new Material[]{Material.GOLD_BLOCK});
        setJumpSurfaces(new Material[]{Material.DIAMOND_BLOCK});
        setFireSpread(false);
        setTimeUponLoad("day");
    }

    // Map details
    String name = "tropicalbeach";
    String fullName = "Tropical Beach";
    String[] creators = {"SereneMango", "ScruffyRules"};
    Gamemode[] modes = {Gamemode.ORESOMEKART};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 12, 80, -37, 90, 0));
        FFASpawns.add(new Location(w, 12, 80, -34, 90, 0));
        FFASpawns.add(new Location(w, 12, 80, -31, 90, 0));
        FFASpawns.add(new Location(w, 12, 80, -28, 90, 0));
        FFASpawns.add(new Location(w, 12, 80, -25, 90, 0));
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
