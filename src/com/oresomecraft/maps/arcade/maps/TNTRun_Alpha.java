package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.maps.arcade.games.TNTRunMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "tntrun_alpha",
        fullName = "TNTRun (Alpha)",
        creators = {"SuperDuckFace"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.COOKED_BEEF}
)
public class TNTRun_Alpha extends TNTRunMap implements Listener {

    public TNTRun_Alpha() {
        super.initiate(this);
    }

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
