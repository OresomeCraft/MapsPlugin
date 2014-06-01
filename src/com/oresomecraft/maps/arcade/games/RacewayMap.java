package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.events.BattleEndEvent;
import com.oresomecraft.maps.MapLoadEvent;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.event.player.PlayerMoveEvent;

public abstract class RacewayMap extends ArcadeMap {

    boolean hasPassedGrace = false;

    public abstract void onLoad(MapLoadEvent event);

    public abstract void onEnd(BattleEndEvent event);

    public abstract void moveChecker(PlayerMoveEvent event);


}
