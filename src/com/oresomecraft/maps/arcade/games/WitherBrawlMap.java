package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.api.events.EndBattleEvent;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.ArcadeMap;
import com.oresomecraft.maps.arcade.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.WorldLoadEvent;

public abstract class WitherBrawlMap extends ArcadeMap {

    boolean hasPassedGrace = false;
    Entity wither;

    @EventHandler
    public void onLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            if (!hasPassedGrace) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (BattlePlayer.getBattlePlayer(player).inBattle()) {
                        BarAPI.setMessage("Get ready for the wither!", 10);
                    }
                }
            }
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    hasPassedGrace = true;
                    wither = Bukkit.getWorld(name).spawnEntity(witherSpawn, EntityType.WITHER);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (BattlePlayer.getBattlePlayer(player).inBattle()) {
                            BarAPI.removeBar(player);
                            BarAPI.setMessage(player, "Get the wither before it gets you!");
                        }
                    }
                }
            }, 200L);
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (BattlePlayer.getBattlePlayer(player).inBattle()) {
                BarAPI.removeBar(player);
            }
        }
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

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!contains(wither.getLocation(), this.witherRegion1.getBlockX(), this.witherRegion2.getBlockX(), this.witherRegion1.getBlockY(), this.witherRegion2.getBlockY(), this.witherRegion1.getBlockZ(), this.witherRegion2.getBlockZ())) {
            wither.teleport(this.witherSpawn);
        }
    }

}
