package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.games.PaintBallMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Paintball_Alpha extends PaintBallMap implements Listener {

    public Paintball_Alpha() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.DIAMOND_SPADE, Material.COOKED_BEEF, Material.SNOW_BALL});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
    }

    // Map details
    String name = "paintball_alpha";
    String fullName = "Paintball (Alpha)";
    String[] creators = {"Afridge1O1", "SuperDuckFace"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -87, 68, 65));
        FFASpawns.add(new Location(w, -137, 99, 101));
        FFASpawns.add(new Location(w, -33, 95, 36));
        FFASpawns.add(new Location(w, -63, 101, 114));
        FFASpawns.add(new Location(w, -139, 89, 54));
        FFASpawns.add(new Location(w, -30, 96, 54));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 128);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, SNOW_BALL);
        i.setItem(1, STEAK);
    }

}
