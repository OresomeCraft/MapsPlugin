package com.oresomecraft.maps.object;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.maps.MapsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class GlobalController implements Listener {

    //Controller Attributes
    MapsPlugin plugin;
    private static ArrayList<Cuboid> cuboids = new ArrayList<Cuboid>();

    public GlobalController(MapsPlugin pl) {
        plugin = pl;
        pl.getServer().getPluginManager().registerEvents(this, pl);
    }

    /**
     * This event is to control cuboid regions and cancel them if they contain
     *
     * @param event An event called by the server
     */
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        for (Cuboid c : cuboids) {
            if (!c.getWorld().getName().equals(event.getBlock().getWorld().getName())) break;
            if (c.checkLocation(BattlePlayer.getBattlePlayer(event.getPlayer()))) {
                //Don't allow any further iteration
                event.setCancelled(true);
                return;
            }
        }
    }

    /**
     * This event is to control cuboid regions and cancel them if they contain
     *
     * @param event An event called by the server
     */
    @EventHandler
    public void onBreak(BlockPlaceEvent event) {
        for (Cuboid c : cuboids) {
            if (!c.getWorld().getName().equals(event.getBlock().getWorld().getName())) break;
            if (c.checkLocation(BattlePlayer.getBattlePlayer(event.getPlayer()))) {
                //Don't allow any further iteration
                event.setCancelled(true);
                return;
            }
        }
    }

    /**
     * A method to add a new protected cuboid region
     *
     * @param c A cuboid
     */
    public static void newProtectedRegion(Cuboid c) {
        cuboids.add(c);
    }
}
