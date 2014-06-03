package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.MapLoadEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public abstract class RacewayMap extends Map {

    boolean hasPassedGrace = false;

    public abstract void onLoad(MapLoadEvent event);

    public abstract void onEnd(BattleEndEvent event);

    public abstract void moveChecker(PlayerMoveEvent event);


}
