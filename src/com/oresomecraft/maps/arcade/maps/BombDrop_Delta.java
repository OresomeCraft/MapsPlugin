package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.MapLoadEvent;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.maps.arcade.games.BombDropMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "bombdrop_delta",
        fullName = "BombDrop (Delta)",
        creators = {"ViolentShadow", "ScruffyRules"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false,
        timeLock = Map.Time.DAY,
        allowPhysicalDamage = false
)
public class BombDrop_Delta extends BombDropMap implements Listener {

    public BombDrop_Delta() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 205, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);
    }

    @EventHandler
    public void onWorld(MapLoadEvent event) {
        if (event.getWorld().getName().equals(getName())) {
            loc1 = new Location(event.getWorld(), -24, 254, -24);
            loc2 = new Location(event.getWorld(), 24, 254, 24);

            bombs();
        }
    }

}
