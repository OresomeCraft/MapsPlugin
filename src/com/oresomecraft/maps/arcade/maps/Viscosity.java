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
        name = "viscosity",
        fullName = "Viscosity Speed Run",
        creators = {"Rynocraft", "BlueVortexed", "AnomalousRei"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.COOKED_BEEF},
        allowPhysicalDamage = true
)
public class Viscosity extends TNTRunMap implements Listener {

    public Viscosity() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -29, 74, -28));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);

        p.sendMessage(ChatColor.RED + "RUN!!!");
    }

}
