package com.oresomecraft.maps.battles;

import com.oresomecraft.maps.Map;
import com.oresomecraft.OresomeBattles.api.BattlesAccess;
import com.oresomecraft.OresomeBattles.api.Monument;
import org.bukkit.Location;

public abstract class BattleMap extends Map {

    /**
     * Set the amount of time a TDM battle will go for
     *
     * @param time Time battle will go for in minutes
     */
    public void setTDMTime(int time) {
        BattlesAccess.setTDMTime(name, time);
    }

    /**
     * Set the amount of time a CP battle will go for
     *
     * @param time Time battle will go for in minutes
     */
    public void setCPTime(int time) {
        BattlesAccess.setCPTime(name, time);
    }

    /**
     * Sets location for King of the Hill monument
     *
     * @param monument Location of the monument
     */
    public void setKoTHMonument(Location monument) {
        BattlesAccess.setKoTHMonument(monument);
    }

    /**
     * Sets the capture points for a map
     *
     * @param monuments a Monument array
     */
    public void setCapturePoints(Monument[] monuments) {
        BattlesAccess.setCapturePoints(monuments);
    }

    /**
     * Sets TDM, CTF and CP spawn points
     */
    public abstract void readyTDMSpawns();

}
