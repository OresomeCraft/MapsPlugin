package com.oresomecraft.maps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import com.oresomecraft.OresomeBattles.api.events.ClearSpawnsEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

/**
 * MapsPlugin | Component for OresomeBattles
 *
 * @author OresomeCraft, Zachoz
 */
public class MapsPlugin extends JavaPlugin {

    public static final Logger logger = Logger.getLogger("Minecraft");
    protected static MapsPlugin plugin;
    protected static YamlConfiguration oresomebattlesConfig;

    protected boolean battleMapsLoaded = false;
    protected boolean arcadeMapsLoaded = false;
    protected boolean tiotMapsLoaded = false;
    protected boolean kartMapsLoaded = false;

    public static final String BATTLE_MAPS_PACKAGE = "com.oresomecraft.maps.battles.maps";
    public static final String ARCADE_MAPS_PACKAGE = "com.oresomecraft.maps.arcade.maps";
    public static final String TIOT_MAPS_PACKAGE = "com.oresomecraft.maps.tiot.maps";
    public static final String ORESOMEKART_MAPS_PACKAGE = "com.oresomecraft.maps.oresomekart.maps";

    private static HashMap<String, Map> maps = new HashMap<String, Map>();

    public void onEnable() {
        oresomebattlesConfig = YamlConfiguration.loadConfiguration(new File("plugins/OresomeBattles/config.yml"));

        if (oresomebattlesConfig.getBoolean("arcade_mode")) { // Arcade
            loadMaps(ARCADE_MAPS_PACKAGE);
            arcadeMapsLoaded = true;
        } else if (oresomebattlesConfig.getBoolean("oresomekart_mode")) { // OresomeKart
            loadMaps(ORESOMEKART_MAPS_PACKAGE);
            kartMapsLoaded = true;
        } else if (oresomebattlesConfig.getBoolean("tiot_mode")) { // TiOT
            loadMaps(TIOT_MAPS_PACKAGE);
            tiotMapsLoaded = true;
        } else { // Battles
            loadMaps(BATTLE_MAPS_PACKAGE);
            battleMapsLoaded = false;

            // Temp hacky trial
            loadMaps(ARCADE_MAPS_PACKAGE);
            arcadeMapsLoaded = true;
        }
    }

    public static Map getMap(String map) {
        if (maps.containsKey(map)) return maps.get(map);
        else return null;
    }

    public static HashMap<String, Map> getMaps() {
        return maps;
    }

    public static void loadMaps(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(MapConfig.class);
        for (Class<?> clazz : classes) {
            try {
                Object map = clazz.newInstance();
                maps.put(((Map) map).getName(), (Map) map);
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
        for (Map map : maps.values()) HandlerList.unregisterAll(map); // Unregister events
        HandlerList.unregisterAll(this); // Unregister any remaining events from this plugin
        maps.clear();
    }

    public static MapsPlugin getInstance() {
        return plugin;
    }

    public boolean onCommand(final CommandSender sender, org.bukkit.command.Command cmd, String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("enablemaps")) {

            if (args.length < 1 && !args[0].equalsIgnoreCase("battles") && !args[0].equalsIgnoreCase("arcade")) {
                sender.sendMessage(ChatColor.RED + "Invalid map type! Options: 'battles','arcade'");
                return false;
            }

            String type = args[0];

            if (type.equalsIgnoreCase("battles")) {
                if (!battleMapsLoaded) {
                    loadMaps(BATTLE_MAPS_PACKAGE);
                    sender.sendMessage(ChatColor.DARK_AQUA + "Loaded battles maps!");
                    battleMapsLoaded = true;
                } else {
                    sender.sendMessage(ChatColor.RED + "Battles maps already loaded!");
                }
            }

            if (type.equalsIgnoreCase("arcade")) {
                if (!arcadeMapsLoaded) {
                    loadMaps(ARCADE_MAPS_PACKAGE);
                    sender.sendMessage(ChatColor.DARK_AQUA + "Loaded arcade maps!");
                    arcadeMapsLoaded = true;
                } else {
                    sender.sendMessage(ChatColor.RED + "Arcade maps already loaded!");
                }
            }

            if (type.equalsIgnoreCase("tiot")) {
                if (!arcadeMapsLoaded) {
                    loadMaps(TIOT_MAPS_PACKAGE);
                    sender.sendMessage(ChatColor.DARK_AQUA + "Loaded TiOT maps!");
                    tiotMapsLoaded = true;
                } else {
                    sender.sendMessage(ChatColor.RED + "TiOT maps already loaded!");
                }
            }

            if (type.equalsIgnoreCase("oresomekart")) {
                if (!arcadeMapsLoaded) {
                    loadMaps(ORESOMEKART_MAPS_PACKAGE);
                    sender.sendMessage(ChatColor.DARK_AQUA + "Loaded OresomeKart maps!");
                    kartMapsLoaded = true;
                } else {
                    sender.sendMessage(ChatColor.RED + "OresomeKart maps already loaded!");
                }
            }

        }
        return true;
    }

}