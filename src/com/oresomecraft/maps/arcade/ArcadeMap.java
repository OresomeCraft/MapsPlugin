package com.oresomecraft.maps.arcade;

import com.oresomecraft.OresomeBattles.api.events.EndBattleEvent;
import com.oresomecraft.maps.Map;
import org.bukkit.Bukkit;

public abstract class ArcadeMap extends Map {

    /**
     * Ends the battle
     */
    public void end() {
        Bukkit.getPluginManager().callEvent(new EndBattleEvent(getMode()));
    }

}
