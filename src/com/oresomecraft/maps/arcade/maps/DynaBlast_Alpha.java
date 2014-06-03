package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.maps.arcade.games.DynaBlastMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "dynablast_alpha",
        fullName = "DynaBlast (Alpha)",
        creators = {"Htgan"},
        gamemodes = {Gamemode.LMS}
)
@Region(
        x1 = -27,
        y1 = 73,
        z1 = 15,
        x2 = 15,
        y2 = 60,
        z2 = -27
)
@Attributes(
        allowBuild = false
)
public class DynaBlast_Alpha extends DynaBlastMap implements Listener {

    public DynaBlast_Alpha() {
        super.initiate(this);
    }

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

}
