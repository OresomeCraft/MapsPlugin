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
public class TNTDM_Beta extends ArcadeMap implements Listener {

    public TNTDM_Beta() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
    }

    // Map details
    String name = "tntdm_beta";
    String fullName = "TNTDM (Beta)";
    String[] creators = {"zachoz"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 61, 0));
        FFASpawns.add(new Location(w, 0, 61, -26));
        FFASpawns.add(new Location(w, 0, 61, 26));
        FFASpawns.add(new Location(w, 27, 61, 0));
        FFASpawns.add(new Location(w, -27, 61, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack TNT = new ItemStack(Material.TNT, 128);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, TNT);
        i.setItem(1, STEAK);

    }
}
