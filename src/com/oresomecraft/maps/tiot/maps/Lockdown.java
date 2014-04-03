package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import com.oresomecraft.maps.tiot.TiOTMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Lockdown extends TiOTMap implements IBattleMap, Listener {

    public Lockdown() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "Lockdown";
    String fullName = "Lockdown";
    String creators = "ShaunDepro97 ";
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 440, 4, 360, 90, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 452, 7, 312), new Location(w, 448, 3, 316)));
    }

    public void readyTDMSpawns() {
        // No TDM spawns
    }

    public void applyInventory(final BattlePlayer p) {
        p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
    }

}
