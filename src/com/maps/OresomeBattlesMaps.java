package com.maps;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class OresomeBattlesMaps extends JavaPlugin {
    public final Logger logger = Logger.getLogger("Minecraft");

    public void onDisable() {

	PluginDescriptionFile pdfFile = getDescription();
	this.logger.info(pdfFile.getName() + "is now disabled");
    }

    public void onEnable() {

	PluginDescriptionFile pdfFile = getDescription();
	this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + "is now enabled");
	loadMaps();
    }
    
    public void loadMaps() {
	
	new PerroMain(this);
	new WartownMain(this);
	new SpireMain(this);
	new MansionMain(this);
	new HypnoMain(this);
	new ArcticMain(this);
        new NuketownMain(this);
	new TerminalMain(this);
	new ChaoscityMain(this);
	new TowersMain(this);
	
    }

}


