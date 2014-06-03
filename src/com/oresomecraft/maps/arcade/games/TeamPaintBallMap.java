package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.map.MapLoadEvent;
import org.bukkit.event.EventHandler;

public abstract class TeamPaintBallMap extends PaintBallMap {

    public abstract void readyTDMSpawns();

    @EventHandler // Set the spawns
    public void spawns(MapLoadEvent event) { // Internal - Do not change
        if (event.getWorld().getName().equals(getName())) {
            this.w = event.getWorld();
            if (w.getName().equals(getName())) {
                readyTDMSpawns();
            }
        }
    }

}
