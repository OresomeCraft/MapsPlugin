package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class DynaBlast extends ArcadeMap implements Listener {

    public DynaBlast() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowPhysicalDamage(false);
    }

    // Map details
    String name = "dynablast";
    String fullName = "DynaBlast";
    String creators = "Htgan";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -29, 65, -29, 0, 0));
        FFASpawns.add(new Location(w, 11, 65, -29, 0, 0));
        FFASpawns.add(new Location(w, -29, 65, 11, 0, 0));
        FFASpawns.add(new Location(w, 11, 65, 11, 0, 0));
        FFASpawns.add(new Location(w, 1, 65, 1, 0, 0));
        FFASpawns.add(new Location(w, -19, 65, 1, 0, 0));
        FFASpawns.add(new Location(w, -19, 65, -19, 0, 0));
        FFASpawns.add(new Location(w, 1, 65, -19, 0, 0));
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
    public int x1 = -30;
    public int y1 = 70;
    public int z1 = 12;

    // Bottom right corner.
    public int x2 = 12;
    public int y2 = 62;
    public int z2 = -30;

}