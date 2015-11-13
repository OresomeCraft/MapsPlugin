package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.maps.arcade.games.TeamPaintBallMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "paintball_echo",
        fullName = "Paintball (Echo)",
        creators = {"meganlovesmusic", "SuperDuckFace"},
        gamemodes = {Gamemode.LTS}
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        timeLock = Map.Time.NIGHT,
        disabledDrops = {Material.COOKED_BEEF, Material.SNOW_BALL},
        allowPhysicalDamage = true
)
public class Paintball_Echo extends TeamPaintBallMap implements Listener {

    public Paintball_Echo() {
        super.initiate(this);
    }

    @Override
    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 38, 67, -15, 62, 0));
        FFASpawns.add(new Location(w, 11, 67, -40, -42, 0));
        FFASpawns.add(new Location(w, -26, 67, 20, -81, 0));
        FFASpawns.add(new Location(w, -16, 67, 42, -100, 0));
        FFASpawns.add(new Location(w, 3, 69, 39, 113, 0));
        FFASpawns.add(new Location(w, 8, 68, 17, -66, 0));
        FFASpawns.add(new Location(w, 33, 67, 2, 121, 0));
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(getName());
        redSpawns.add(new Location(w, 38, 67, -15, 62, 0));
        blueSpawns.add(new Location(w, -16, 67, 42, -100, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 200);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 10);

        i.setItem(0, SNOW_BALL);
        i.setItem(1, STEAK);

    }
}
