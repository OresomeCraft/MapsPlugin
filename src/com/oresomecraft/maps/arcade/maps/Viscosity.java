package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.maps.arcade.games.TNTRunMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

@MapConfig(
        name = "viscosity",
        fullName = "Viscosity Speed Run",
        creators = {"Rynocraft", "BlueVortexed", "AnomalousRei"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.COOKED_BEEF}
)
public class Viscosity extends TNTRunMap implements Listener {

    public Viscosity() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -29, 74, -28));
    }

    public void applyInventory(BattlePlayer p) {
    }

    @EventHandler
    public void food(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }

}
