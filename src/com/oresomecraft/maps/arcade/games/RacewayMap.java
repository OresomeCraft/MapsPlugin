package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapLoadEvent;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;

public abstract class RacewayMap extends ArcadeMap {

    boolean hasPassedGrace = false;

    public abstract void onLoad(MapLoadEvent event);

    public abstract void onEnd(BattleEndEvent event);

    public abstract void moveChecker(PlayerMoveEvent event);


}
