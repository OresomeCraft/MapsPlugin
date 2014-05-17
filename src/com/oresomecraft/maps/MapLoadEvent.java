package com.oresomecraft.maps;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MapLoadEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String map;
    private World world;

    public MapLoadEvent(String m, World w) {
        map = m;
        world = w;
    }

    public String getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
