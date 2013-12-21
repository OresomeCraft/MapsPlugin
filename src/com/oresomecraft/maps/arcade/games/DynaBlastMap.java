package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public abstract class DynaBlastMap extends ArcadeMap {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(name) && !(event.getBlock().getType() == Material.TNT))
            event.setCancelled(true);
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if (event.getLocation().getWorld().getName().equals(name)) event.setYield(0);
    }

}
