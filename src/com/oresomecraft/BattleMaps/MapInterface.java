package com.oresomecraft.BattleMaps;

import org.bukkit.Location;

import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public interface MapInterface {

    public void readyMap(ReadyMapsEvent event);
    
    public void readyTDMSpawns();
    
    public void readyFFASpawns();
    
    public void applyInventory(InventoryEvent event);
    
    public void clearSpawns(ClearSpawnsEvent event);
    
    public boolean contains(Location loc, int x1, int x2, int y1, int y2, int z1, int z2);
    
}
