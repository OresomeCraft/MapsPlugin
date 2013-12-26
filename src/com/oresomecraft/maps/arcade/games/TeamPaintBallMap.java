package com.oresomecraft.maps.arcade.games;

import org.bukkit.event.EventHandler;
import org.bukkit.event.world.WorldLoadEvent;

public abstract class TeamPaintBallMap extends PaintBallMap {

    public abstract void readyTDMSpawns();

    @EventHandler // Set the spawns
    public void spawns(WorldLoadEvent event) { // Internal - Do not change
        if (event.getWorld().getName().equals(name)) {
            this.w = event.getWorld();
            if (w.getName().equals(name)) {
                readyTDMSpawns();
                setRedSpawns(name, redSpawns);
                setBlueSpawns(name, blueSpawns);
            }
        }
    }

}
