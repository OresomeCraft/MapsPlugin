package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.maps.arcade.games.TNTRunMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.*;
import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.*;
import com.oresomecraft.maps.arcade.ArcadeMap;

@MapConfig
public class TNTRun_Alpha extends TNTRunMap implements Listener {

    public TNTRun_Alpha() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
    }

    // Map details
    String name = "tntrun_alpha";
    String fullName = "TNTRun (Alpha)";
    String[] creators = {"SuperDuckFace"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 39, 69, 0, 90, 0));
        FFASpawns.add(new Location(w, 0, 69, -38, 0, 0));
        FFASpawns.add(new Location(w, -38, 69, 0, -90, 0));
        FFASpawns.add(new Location(w, 0, 69, 39, 179, 0));
        FFASpawns.add(new Location(w, -25, 69, 28, -135, 0));
        FFASpawns.add(new Location(w, -25, 69, -28, -42, 0));
        FFASpawns.add(new Location(w, 26, 69, -27, 43, 0));
        FFASpawns.add(new Location(w, 26, 69, 27, 136, 0));
        FFASpawns.add(new Location(w, 15, 69, 35, 179, 0));
        FFASpawns.add(new Location(w, -14, 69, 35, 179, 0));
        FFASpawns.add(new Location(w, -14, 69, -34, 0, 0));
        FFASpawns.add(new Location(w, 15, 69, -34, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);

        p.sendMessage(ChatColor.RED + "RUN!!!");
    }

}
