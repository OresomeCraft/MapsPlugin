package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.ArcadeMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class TNTDM_Charlie extends ArcadeMap implements Listener {

    public TNTDM_Charlie() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
    }

    // Map details
    String name = "tntdm_charlie";
    String fullName = "TNTDM (Charlie)";
    String[] creators = {"SuperDuckFace", "ninsai"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 39, 52, 0, 90, 0));
        FFASpawns.add(new Location(w, 0, 52, -39, -176, 0));
        FFASpawns.add(new Location(w, -38, 52, 0, -90, 0));
        FFASpawns.add(new Location(w, 0, 52, -38, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack TNT = new ItemStack(Material.TNT, 192);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, TNT);
        i.setItem(1, STEAK);

    }
}
