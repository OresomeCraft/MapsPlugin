package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.maps.arcade.games.TNTRunMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.maps.*;

@MapConfig
public class TNTRun_Beta extends TNTRunMap implements Listener {

    public TNTRun_Beta() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
    }

    // Map details
    String name = "tntrun_beta";
    String fullName = "TNTRun (Beta)";
    String[] creators = {"SuperDuckFace", "ScruffyRules"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -0, 77, -21));
        FFASpawns.add(new Location(w, -15, 77, -15));
        FFASpawns.add(new Location(w, -22, 77, -0));
        FFASpawns.add(new Location(w, -15, 77, 14));
        FFASpawns.add(new Location(w, -0, 77, 21));
        FFASpawns.add(new Location(w, 14, 77, -14));
        FFASpawns.add(new Location(w, 21, 77, -0));
        FFASpawns.add(new Location(w, 14, 77, -15));
        FFASpawns.add(new Location(w, 3, 77, -4));
        FFASpawns.add(new Location(w, -4, 77, -4));
        FFASpawns.add(new Location(w, -4, 77, 3));
        FFASpawns.add(new Location(w, 3, 77, 3));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);

        p.sendMessage(ChatColor.RED + "RUN!!!");
    }

}
