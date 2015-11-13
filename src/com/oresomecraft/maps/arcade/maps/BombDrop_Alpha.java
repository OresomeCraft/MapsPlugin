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
        name = "bombdrop_alpha",
        fullName = "BombDrop (Alpha)",
        creators = {"kevlar_miner"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false
)
public class BombDrop_Alpha extends BombDropMap implements Listener {

    public BombDrop_Alpha() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 208, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);
    }

    @EventHandler
    public void onWorld(MapLoadEvent event) {
        if (event.getWorld().getName().equals(getName())) {
            loc1 = new Location(event.getWorld(), -18, 252, 18);
            loc2 = new Location(event.getWorld(), 18, 252, -18);

            bombs();
        }
    }

}
