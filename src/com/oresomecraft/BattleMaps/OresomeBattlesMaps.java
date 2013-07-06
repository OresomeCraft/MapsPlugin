package com.oresomecraft.BattleMaps;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.oresomecraft.BattleMaps.classes.*;

public class OresomeBattlesMaps extends JavaPlugin {
    public final Logger logger = Logger.getLogger("Minecraft");

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

        new Zoned(this);
        new BattleMap(this);
        new Perro(this);
        new Fairwick(this);
        new Wartown(this);
        new Spire(this);
        new Xenon(this);
        new Mansion(this);
        new Hypno(this);
        new Arctic(this);
        new Mutiny(this);
        new Nuketown(this);
        new Terminal(this);
        new Sandtrap(this);
        new Towers(this);
        new Hartshire(this);
        new Skyislands(this);
        new Fosscrest(this);
        new Solitude(this);
        new Suburban(this);
        new MutinyII(this);
        new Battlement(this);
        new Insanity(this);
        new Carnival(this);
        new Sub(this);
        new Deepcaverns(this);
        new Chaoscity(this);
        new Darknessofdusk(this);
        new Docks(this);
        new Spaceships(this);
        new Relation(this);
        new Conflict(this);
        new Apollo(this);
        new Raid(this);
        new Voidsflag(this);
        new DimensionalWar(this);
        new Mayhem(this);
        
    }

}


