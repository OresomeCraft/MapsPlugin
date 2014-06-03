package com.oresomecraft.maps;

import com.oresomecraft.OresomeBattles.api.MapManagerBridge;
import com.oresomecraft.OresomeBattles.map.MapProvider;
import com.oresomecraft.OresomeBattles.map.exception.InvalidMapProviderException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

/**
 * MapsPlugin | A Map Provider for OresomeBattles
 *
 * @author OresomeCraft, Zachoz
 */
public class MapsPlugin extends JavaPlugin implements MapProvider {

    public static final Logger logger = Logger.getLogger("Minecraft");
    protected static MapsPlugin plugin;
    protected static YamlConfiguration oresomebattlesConfig;

    public static final String BATTLE_MAPS_PACKAGE = "com.oresomecraft.maps.battles.maps";
    public static final String ARCADE_MAPS_PACKAGE = "com.oresomecraft.maps.arcade.maps";
    public static final String TIOT_MAPS_PACKAGE = "com.oresomecraft.maps.tiot.maps";
    public static final String ORESOMEKART_MAPS_PACKAGE = "com.oresomecraft.maps.oresomekart.maps";

    public void onEnable() {
        MapManagerBridge.registerMapProvider(this); // Hook into OresomeBattles and register provider
    }

    public void initiate() {
        try {
            MapManagerBridge.loadMaps(this, BATTLE_MAPS_PACKAGE);
            MapManagerBridge.loadMaps(this, ARCADE_MAPS_PACKAGE);
            MapManagerBridge.loadMaps(this, TIOT_MAPS_PACKAGE);
            MapManagerBridge.loadMaps(this, ORESOMEKART_MAPS_PACKAGE);
        } catch (InvalidMapProviderException ex) {
            ex.printStackTrace();
            // Impossible since we register as a provider on plugin enable
        }
    }

    public MapsPlugin() {
        plugin = this;
    }

    public void onDisable() {
        MapManagerBridge.deRegisterMapProvider(this); // De-register
    }

    public static MapsPlugin getInstance() {
        return plugin;
    }

}