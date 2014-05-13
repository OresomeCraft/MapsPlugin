package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.games.BombDropMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class BombDrop_Alpha extends BombDropMap implements Listener {

    public BombDrop_Alpha() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
        lockTime("day");
    }

    // Map details
    String name = "bombdrop_alpha";
    String fullName = "BombDrop (Alpha)";
    String[] creators = {"kevlar_miner"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 208, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);
    }

    @EventHandler
    public void onWorld(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            loc1 = new Location(event.getWorld(), -18, 252, 18);
            loc2 = new Location(event.getWorld(), 18, 252, -18);

            bombs();
        }
    }

}
