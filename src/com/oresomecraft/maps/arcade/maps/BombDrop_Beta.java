package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.maps.arcade.games.BombDropMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.maps.*;

@MapConfig
public class BombDrop_Beta extends BombDropMap implements Listener {

    public BombDrop_Beta() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
        lockTime("day");
    }

    // Map details
    String name = "bombdrop_beta";
    String fullName = "BombDrop (Beta)";
    String[] creators = {"ViolentShadow", "ScruffyRules"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 225, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);
    }

    @EventHandler
    public void onWorld(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            loc1 = new Location(event.getWorld(), -22, 254, -22);
            loc2 = new Location(event.getWorld(), 22, 254, 22);

            bombs();
        }
    }

}
