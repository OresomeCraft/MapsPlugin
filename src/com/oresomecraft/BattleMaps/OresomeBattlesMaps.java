package com.oresomecraft.BattleMaps;

import java.util.Set;
import java.util.logging.Logger;

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

    public void onEnable() {
        loadMaps("com.oresomecraft.BattleMaps.maps");
    }

    public static void loadMaps(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends BattleMap>> classes = reflections.getSubTypesOf(BattleMap.class);
        for (Class<? extends BattleMap> clazz : classes) {
            try {
                BattleMap battleMap = clazz.newInstance();
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

    }

    public static OresomeBattlesMaps getInstance() {
        return plugin;
    }

}