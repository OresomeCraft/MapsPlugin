package com.maps;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;

import com.oresomecraft.OresomeBattles.OresomeBattles;

public class CavernsMain implements Listener {

    OresomeBattles plugin;
    
    public CavernsMain(OresomeBattles pl) {
	plugin = pl;
    }
    
    // Spawn locations.
    public List<Location> dcSpawns = new ArrayList<Location>();
    
    // CTF
    public List<Location> dcrSpawns = new ArrayList<Location>();
    public List<Location> dcbSpawns = new ArrayList<Location>();
    
    public void readyTDMSpawns() {
	World w = Bukkit.getServer().getWorld("caverns");
    	Location drs = new Location(w, 0, 99, 27, 108, 0);
    	Location dbs = new Location(w, -9, 110, -20, -1, 0);
        Location ds1 = new Location(w, -7, 109, -5, -90, 0); 
        Location ds2 = new Location(w, 27, 104, 6, 58, 0);
        Location ds3 = new Location(w, -33, 105, 9.5, -132, 0);
        Location ds4 = new Location(w, -28, 78, 13, -48, 0);
        Location ds5 = new Location(w, -86, 85, -2, -75, 0);
        Location ds6 = new Location(w, -29, 105, -14, 79, 0);
        Location ds7 = new Location(w, 13, 105, -56, 49, 0);
        Location ds8 = new Location(w, 29, 95, 2, 134, 0);
        Location ds9 = new Location(w, 10, 110, 7, -179, 0);
        Location ds10 = new Location(w, -10, 100, 20, -66, 0);
        
		dcSpawns.add(drs);
		dcSpawns.add(dbs);
		dcSpawns.add(ds1);
		dcSpawns.add(ds2);
		dcSpawns.add(ds3);
		dcSpawns.add(ds4);
		dcSpawns.add(ds5);
		dcSpawns.add(ds6);
		dcSpawns.add(ds7);
		dcSpawns.add(ds8);
		dcSpawns.add(ds9);
		dcSpawns.add(ds10);
		plugin.tdmMa.Spawns.put("caverns", dcSpawns);
    }
    
    public void readyFFASpawns() {
	World w = Bukkit.getServer().getWorld("caverns");
    	Location drs = new Location(w, 0, 99, 27, 108, 0);
    	Location dbs = new Location(w, -9, 110, -20, -1, 0);
        Location ds1 = new Location(w, -7, 109, -5, -90, 0); 
        Location ds2 = new Location(w, 27, 104, 6, 58, 0);
        Location ds3 = new Location(w, -33, 105, 9.5, -132, 0);
        Location ds4 = new Location(w, -28, 78, 13, -48, 0);
        Location ds5 = new Location(w, -86, 85, -2, -75, 0);
        Location ds6 = new Location(w, -29, 105, -14, 79, 0);
        Location ds7 = new Location(w, 13, 105, -56, 49, 0);
        Location ds8 = new Location(w, 29, 95, 2, 134, 0);
        Location ds9 = new Location(w, 10, 110, 7, -179, 0);
        Location ds10 = new Location(w, -10, 100, 20, -66, 0);
        
		dcSpawns.add(drs);
		dcSpawns.add(dbs);
		dcSpawns.add(ds1);
		dcSpawns.add(ds2);
		dcSpawns.add(ds3);
		dcSpawns.add(ds4);
		dcSpawns.add(ds5);
		dcSpawns.add(ds6);
		dcSpawns.add(ds7);
		dcSpawns.add(ds8);
		dcSpawns.add(ds9);
		dcSpawns.add(ds10);
		
		plugin.ffaMa.Spawns.put("caverns", dcSpawns);
    }


    //Region border.
    
   public int x1 = -76;
   public int y1 = 136;
   public int z1 = 36;
   public int x2 = 140;
   public int y2 = 255;
   public int z2 = -115;

    public static boolean contains(Location loc, int x1, int x2, int y1,
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

// Effect shit here!

}
