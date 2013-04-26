// Provides access to OresomeBattles API. MANY more API additions to be added soon.
package com.oresomecraft.BattleMaps;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.oresomecraft.OresomeBattles.OresomeBattles;
import com.oresomecraft.OresomeBattles.Utility;

public class BattleMap implements Listener {
    
    OresomeBattlesMaps plugin;
    public OresomeBattles battles;
    public BattleMap(OresomeBattlesMaps pl) {
	plugin = pl;
	battles = (OresomeBattles) Bukkit.getServer().getPluginManager().getPlugin("OresomeBattles");
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public void setRedSpawns(String name, ArrayList<Location> redSpawns) {
	battles.bp.setRedSpawns(name, redSpawns);
    }

    public void setBlueSpawns(String name, ArrayList<Location> blueSpawns) {
	battles.bp.setBlueSpawns(name, blueSpawns);
    }

    public void setFFASpawns(String name, ArrayList<Location> FFASpawns) {
	battles.bp.setFFASpawns(name, FFASpawns);
    }
    
    public void addVotes(String name) {
	battles.addVotes(name);
    }

    public void addCreators(String name, String creators) {
	battles.addCreators(name, creators);
    }

    public void setFullName(String name, String fullName) {
	battles.setFullName(name, fullName);
    }
    
    public void clearInv(Player p) {
	Utility.clearInv(p);
    }

}
