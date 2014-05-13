package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.maps.arcade.games.DynaBlastMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class DynaBlast_Alpha extends DynaBlastMap implements Listener {

    public DynaBlast_Alpha() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowPhysicalDamage(false);
    }

    // Map details
    String name = "dynablast_alpha";
    String fullName = "DynaBlast (Alpha)";
    String[] creators = {"Htgan"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -28.50, 65.00, -28.50, 0, 0));
        FFASpawns.add(new Location(w, 11.50, 65.00, -28.50, 0, 0));
        FFASpawns.add(new Location(w, -28.50, 65.00, 11.50, 0, 0));
        FFASpawns.add(new Location(w, 11.50, 65.00, 11.50, 0, 0));
        FFASpawns.add(new Location(w, 1.50, 65.50, 1.50, 0, 0));
        FFASpawns.add(new Location(w, -18.50, 65.00, 1.50, 0, 0));
        FFASpawns.add(new Location(w, -18.50, 65.00, -18.50, 0, 0));
        FFASpawns.add(new Location(w, 1.50, 65.00, -18.50, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack TNT = new ItemStack(Material.TNT, 128);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, TNT);
        i.setItem(1, STEAK);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -27;
    public int y1 = 73;
    public int z1 = 15;

    // Bottom right corner.
    public int x2 = 15;
    public int y2 = 60;
    public int z2 = -27;

}
