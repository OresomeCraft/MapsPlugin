package com.oresomecraft.maps;

import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

import com.oresomecraft.OresomeBattles.api.events.*;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

/**
 * MapsPlugin | Component for OresomeBattles
 *
 * @author OresomeCraft, Zachoz
 */
public class MapsPlugin extends JavaPlugin {

    public static final Logger logger = Logger.getLogger("Minecraft");
    protected static MapsPlugin plugin;
    private static ArrayList<Map> maps = new ArrayList<Map>();

    public void onEnable() {
        loadMaps("com.oresomecraft.maps.battles.maps");
        loadMaps("com.oresomecraft.maps.arcade.maps");
    }

    public static void loadMaps(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Map>> classes = reflections.getSubTypesOf(Map.class);
        for (Class<? extends Map> clazz : classes) {
            try {
                Map map = clazz.newInstance();
                maps.add(map);
            } catch (Exception e) {
                logger.severe("Unable to load map: " + clazz.getName());
                e.printStackTrace();
            }
        }
    }

    public MapsPlugin() {
        plugin = this;
    }

    public void onDisable() {
        Bukkit.getPluginManager().callEvent(new ClearSpawnsEvent()); // Clear spawns

        for (Map map : maps) { // Unregister events
            HandlerList.unregisterAll(map);
        }

        HandlerList.unregisterAll(this); // Unregister any remaining events from this plugin

        maps.clear(); // Remove some final references
    }

    public static MapsPlugin getInstance() {
        return plugin;
    }

}