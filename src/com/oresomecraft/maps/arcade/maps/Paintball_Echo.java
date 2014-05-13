package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.games.TeamPaintBallMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Paintball_Echo extends TeamPaintBallMap implements Listener {

    public Paintball_Echo() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF, Material.SNOW_BALL});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
        setFireSpread(false);
        lockTime("night");
    }

    // Map details
    String name = "paintball_echo";
    String fullName = "Paintball (Echo)";
    String[] creators = {"meganlovesmusic", "SuperDuckFace"};
    Gamemode[] modes = {Gamemode.LMS};

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
        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, 38, 67, -15, 62, 0));
        blueSpawns.add(new Location(w, -16, 67, 42, -100, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 200);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 10);

        i.setItem(0, SNOW_BALL);
        i.setItem(1, STEAK);

    }
}
