package com.maps;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.maps.classes.*;

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

	new BattleMap(this);
	new Carnival(this);
	new Perro(this);
	new Wartown(this);
	new Spire(this);
	new Mansion(this);
	new Hypno(this);
	new Arctic(this);
	new Nuketown(this);
	new Terminal(this);
	new Chaoscity(this);
	new Towers(this);
	new Hartshire(this);
	new Deepcaverns(this);
	new Skyislands(this);
	new Fosscrest(this);
	new Solitude(this);
	new Suburban(this);

    }

}


