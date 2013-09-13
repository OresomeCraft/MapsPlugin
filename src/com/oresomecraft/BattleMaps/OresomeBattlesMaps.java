package com.oresomecraft.BattleMaps;

import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

import com.oresomecraft.OresomeBattles.api.events.*;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

/**
 * OresomeBattlesMaps | Component for OresomeBattles
 *
 * @author OresomeCraft, Zachoz
 */
public class OresomeBattlesMaps extends JavaPlugin {

    public static final Logger logger = Logger.getLogger("Minecraft");
    protected static OresomeBattlesMaps plugin;
    private static ArrayList<BattleMap> maps = new ArrayList<BattleMap>();

    public void onEnable() {
        loadMaps("com.oresomecraft.BattleMaps.maps");
    }

    public static void loadMaps(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends BattleMap>> classes = reflections.getSubTypesOf(BattleMap.class);
        for (Class<? extends BattleMap> clazz : classes) {
            try {
                BattleMap battleMap = clazz.newInstance();
                maps.add(battleMap);
            } catch (Exception e) {
                logger.severe("Unable to load map: " + clazz.getName());
                e.printStackTrace();
            }
        }
    }

    public OresomeBattlesMaps() {
        plugin = this;
    }

    public void onDisable() {
        Bukkit.getPluginManager().callEvent(new ClearSpawnsEvent()); // Clear spawns

        for (BattleMap map : maps) { // Unregister events
            HandlerList.unregisterAll(map);
        }

        HandlerList.unregisterAll(this); // Unregister any remaining events from this plugin

        maps.clear(); // Remove some final references
    }

    public static OresomeBattlesMaps getInstance() {
        return plugin;
    }

}