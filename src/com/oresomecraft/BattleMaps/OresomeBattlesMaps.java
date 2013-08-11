package com.oresomecraft.BattleMaps;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.oresomecraft.BattleMaps.maps.*;

public class OresomeBattlesMaps extends JavaPlugin {
    public final Logger logger = Logger.getLogger("Minecraft");
    protected static OresomeBattlesMaps plugin;

    public OresomeBattlesMaps() {
        plugin = this;
    }

    public void onDisable() {

        PluginDescriptionFile pdfFile = getDescription();
        this.logger.info(pdfFile.getName() + " is now disabled");
    }

    public void onEnable() {

        PluginDescriptionFile pdfFile = getDescription();
        this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + "is now enabled");
        loadMaps();
    }

    public void loadMaps() {

        new Apollo();
        new Zoned();
        new Mantle();
        new Bakery();
        new Perro();
        new Fairwick();
        new Wartown();
        new Spire();
        new Xenon();
        new Mansion();
        new Hypno();
        new Arctic();
        new Mutiny();
        new Nuketown();
        new Terminal();
        new Sandtrap();
        new Towers();
        new Hartshire();
        new Skyislands();
        new Fosscrest();
        new Solitude();
        new Suburban();
        new MutinyII();
        new Battlement();
        new Insanity();
        new Carnival();
        new Sub();
        new Deepcaverns();
        new Chaoscity();
        new Darknessofdusk();
        new Docks();
        new Spaceships();
        new Relation();
        new Raid();
        new Voidsflag();
        new DimensionalWar();
        new Mayhem();
        new BurnFirePort();
        new ClashOfClay();
        new Elements();
        new GibsonDesertBattles();
        new WarTrauma();
        new CanopyWarfare();
        new ClashOfClayII();
        new Sunshine();
        new Electricity();
        new HauntedHouse();
        new Sloped();
        new Crater();
    }

    public static OresomeBattlesMaps getInstance() {
        return plugin;
    }

}


