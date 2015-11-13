package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.maps.arcade.games.TNTRunMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "tntrun_beta",
        fullName = "TNTRun (Beta)",
        creators = {"SuperDuckFace", "ScruffyRules"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.COOKED_BEEF},
        allowPhysicalPlayerDamage = false
)
public class TNTRun_Beta extends TNTRunMap implements Listener {

    public TNTRun_Beta() {
        super.initiate(this);
    }

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
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);

        p.sendMessage(ChatColor.RED + "RUN!!!");
    }

}
