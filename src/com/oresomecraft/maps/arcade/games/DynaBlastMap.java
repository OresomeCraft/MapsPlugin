package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.map.Map;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public abstract class DynaBlastMap extends Map {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equals(getName())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(getName()) && !(event.getBlock().getType() == Material.TNT))
            event.setCancelled(true);
    }

    @EventHandler
    public void explode(EntityExplodeEvent event) {
        if (event.getLocation().getWorld().getName().equals(getName())) event.setYield(0);
    }

}
