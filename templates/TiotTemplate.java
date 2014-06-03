package com.oresomecraft.maps.tiot.maps;

import org.bukkit.*;
import org.bukkit.inventory.*;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.*;
import com.oresomecraft.OresomeBattles.map.types.TiOTMap;
import com.oresomecraft.OresomeBattles.region.CuboidRegion;

@MapConfig(
        name = "template",
        fullName = "Template",
        creators = {"zachoz", "ScruffyRules"},
        gamemodes = {Gamemode.TIOT}
)
@Region(
        x1 = 0,
        y1 = 0,
        z1 = 0,
        x2 = 0,
        y2 = 0,
        z2 = 0
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.BOW, Material.IRON_SWORD}
)
public class TiotTemplate extends TiOTMap {

    public TiotTemplate() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 231, 103, -1292));
        FFASpawns.add(new Location(w, 234, 101, -1308));
        FFASpawns.add(new Location(w, 206, 107, -1287));
        FFASpawns.add(new Location(w, 203, 111, -1269));
        FFASpawns.add(new Location(w, 173, 119, -1268));

        setCriminalTester(new CuboidRegion(new Location(w, 185, 108, -1298), new Location(w, 189, 110, -1301)));
    }

    public void applyInventory(final BattlePlayer p) {
        // No predefined inventory
    }

}
