// Provides access to OresomeBattles API. MANY more API additions to be added soon.
package com.oresomecraft.BattleMaps;

import java.util.ArrayList;

import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;
import com.oresomecraft.OresomeBattles.gamemodes.CTF;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.OresomeBattles;
import com.oresomecraft.OresomeBattles.Utility;
import org.bukkit.event.world.WorldLoadEvent;

public abstract class BattleMap implements Listener {

    public OresomeBattlesMaps plugin = OresomeBattlesMaps.getInstance();
    public OresomeBattles battles;
    public BattleMap config;

    public BattleMap() {
        battles = (OresomeBattles) Bukkit.getServer().getPluginManager().getPlugin("OresomeBattles");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * ***************************************************************
     * Map configuration variables and methods                       *
     * ***************************************************************
     */

    // Spawn lists. Inherited by class configs
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name;
    String fullName;
    String creators;
    Gamemode[] modes;

    @EventHandler // Add map
    public void readyMap(ReadyMapsEvent event) {
        addMap(name);
        setGamemodes(name, modes);
        addCreators(name, creators);
        setFullName(name, fullName);
    }

    @EventHandler // Set the spawns
    public void setSpawns(WorldLoadEvent event) { // Internal - Do not change
        if (event.getWorld().getName().equals(name)) {
            config.readyTDMSpawns();
            config.readyFFASpawns();
        }
    }

    // Initiates config
    protected final void initiate(BattleMap config) {
        this.config = config;
    }

    // Set map details
    protected final void setDetails(String name, String fullName, String creators, Gamemode[] modes) {
        this.name = name;
        this.fullName = fullName;
        this.creators = creators;
        this.modes = modes;
    }

    // Clear spawns ro remove world references and prevent memory leaks
    @EventHandler(priority = EventPriority.NORMAL)
    public void clearSpawns(ClearSpawnsEvent event) {
        redSpawns.clear();
        blueSpawns.clear();
        FFASpawns.clear();
    }

    public abstract void readyTDMSpawns();
    public abstract void readyFFASpawns();

    public boolean active = Utility.getArena().equals(name); // Wether or not map is currently being played

    /**
     * ***************************************************************
     * Methods to interact with OresomeBattles                       *
     * ***************************************************************
     */

    public void setRedSpawns(String name, ArrayList<Location> redSpawns) {
        battles.bp.setRedSpawns(name, redSpawns);
    }

    public void setBlueSpawns(String name, ArrayList<Location> blueSpawns) {
        battles.bp.setBlueSpawns(name, blueSpawns);
    }

    public void setFFASpawns(String name, ArrayList<Location> FFASpawns) {
        battles.bp.setFFASpawns(name, FFASpawns);
    }

    public void addMap(String name) {
        battles.addMap(name);
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

    public void setGamemodes(String name, Gamemode[] modes) {
        battles.setGamemodes(name, modes);
    }

    public void setCTFFlags(String name, Location redFlag, Location blueFlag) {
        if (Utility.getMode().equals("ctf")) {
            CTF.setRedFlag(name, redFlag);
            CTF.setBlueFlag(name, blueFlag);
        }
    }

    /**
     * ***************************************************************
     * Useful methods easily useable by all maps                     *
     * ***************************************************************
     */

    // Getting the region
    public boolean contains(Location loc, int x1, int x2, int y1,
                            int y2, int z1, int z2) {
        int bottomCornerX = x1 < x2 ? x1 : x2;
        int bottomCornerZ = z1 < z2 ? z1 : z2;
        int topCornerX = x1 > x2 ? x1 : x2;
        int topCornerZ = z1 > z2 ? z1 : z2;
        int bottomCornerY = y1 < y2 ? y1 : y2;
        int topCornerY = y1 > y2 ? y1 : y2;
        if (loc.getX() >= bottomCornerX && loc.getX() <= topCornerX) {
            if (loc.getZ() >= bottomCornerZ && loc.getZ() <= topCornerZ) {
                if (loc.getY() >= bottomCornerY && loc.getY() <= topCornerY) {
                    return true;
                }
            }
        }
        return false;

    }

}
