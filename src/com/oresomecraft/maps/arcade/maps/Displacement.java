package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.maps.arcade.games.PaintBallMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "displacement",
        fullName = "Displacement",
        creators = {"BlueVortexed", "BlueVortexed", "niceman506"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.DIAMOND_SPADE, Material.COOKED_BEEF, Material.SNOW_BALL}
)
public class Displacement extends PaintBallMap implements Listener {

    public Displacement() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 37, -2));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 128);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, SNOW_BALL);
        i.setItem(1, STEAK);
    }

}
