package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.api.events.EndBattleEvent;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.world.WorldLoadEvent;

public abstract class WitherBrawlMap extends ArcadeMap {

    boolean hasPassedGrace = false;

    Entity wither;

    @EventHandler
    public void onLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    hasPassedGrace = true;
                    wither = Bukkit.getWorld(name).spawnEntity(witherSpawn, EntityType.WITHER);
                }
            }, 100L);
            while (!hasPassedGrace) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    BattlePlayer battlePlayer = BattlePlayer.getBattlePlayer(player);
                    if (battlePlayer.inBattle()) {
                        //HeadsUpDisplay.displayLoadingBar(ChatColor.RED + "Loading...", ChatColor.RED + "Game starting...", player, 10, true);
                        //TODO: Add support for a ten second bar countdown (similar to game start countdown)
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        hasPassedGrace = false;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity().getType() == EntityType.WITHER) {
                Bukkit.getPluginManager().callEvent(new EndBattleEvent(Gamemode.FFA));
            }
        }
    }

}
