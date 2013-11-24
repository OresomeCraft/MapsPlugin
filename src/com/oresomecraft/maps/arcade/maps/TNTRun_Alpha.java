package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class TNTRun_Alpha extends ArcadeMap implements Listener {

    public TNTRun_Alpha() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
    }

    // Map details
    String name = "tntrun_alpha";
    String fullName = "TNTRun (Alpha)";
    String creators = "SuperDuckFace ";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 39, 67.4, 0, 90, 0));
        FFASpawns.add(new Location(w, 0, 67.4, -38, 0, 0));
        FFASpawns.add(new Location(w, -38, 67.4, 0, -90, 0));
        FFASpawns.add(new Location(w, 0, 67.4, 39, 179, 0));
        FFASpawns.add(new Location(w, -25, 67.4, 28, -135, 0));
        FFASpawns.add(new Location(w, -25, 67.4, -28, -42, 0));
        FFASpawns.add(new Location(w, 26, 67.4, -27, 43, 0));
        FFASpawns.add(new Location(w, 26, 67.4, 27, 136, 0));
        FFASpawns.add(new Location(w, 15, 67.4, 35, 179, 0));
        FFASpawns.add(new Location(w, -14, 67.4, 35, 179, 0));
        FFASpawns.add(new Location(w, -14, 67.4, -34, 0, 0));
        FFASpawns.add(new Location(w, 15, 67.4, -34, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);

        p.sendMessage(ChatColor.RED + "RUN!!!");

    }
}