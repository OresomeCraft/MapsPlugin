package com.oresomecraft.maps.tiot;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;

@MapConfig
public class OresomeTown extends BattleMap implements IBattleMap, Listener {

    public OresomeTown() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "oresometowntiot";
    String fullName = "OresomeTown";
    String creators = "meganlovesmusic, SuperDuckFace and _Husky_";
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        World world = Bukkit.getWorld(name);

        FFASpawns.add(new Location(world, 3, 70, -11));
        FFASpawns.add(new Location(world, -1, 70, -40));
        FFASpawns.add(new Location(world, 26, 70, -25));
        FFASpawns.add(new Location(world, 56, 70, -42));
        FFASpawns.add(new Location(world, 74, 70, -41));
        FFASpawns.add(new Location(world, 68, 76, -28));
        FFASpawns.add(new Location(world, 66, 70, 2));
        FFASpawns.add(new Location(world, 36, 70, 6));
        FFASpawns.add(new Location(world, 12, 70, 0));
        FFASpawns.add(new Location(world, 4, 70, 20));
        FFASpawns.add(new Location(world, 48, 70, 26));
        FFASpawns.add(new Location(world, 68, 70, 35));
        FFASpawns.add(new Location(world, 13, 70, 36));
        FFASpawns.add(new Location(world, -46, 70, 22));
        FFASpawns.add(new Location(world, -74, 70, 33));
        FFASpawns.add(new Location(world, -78, 70, -0));
        FFASpawns.add(new Location(world, -80, 70, -24));
        FFASpawns.add(new Location(world, -69, 70, -39));
        FFASpawns.add(new Location(world, -36, 70, -37));
        FFASpawns.add(new Location(world, -20, 70, -40));
        FFASpawns.add(new Location(world, 15, 81, -39));
        FFASpawns.add(new Location(world, 40, 76, 20));
        FFASpawns.add(new Location(world, 45, 82, -33));
        FFASpawns.add(new Location(world, -26, 70, 33));
        FFASpawns.add(new Location(world, -43, 70, -4));

        setCriminalTester(new CuboidRegion(new Location(w, -4, 73, -47), new Location(w, 0, 69, -45)));
    }

    public void readyTDMSpawns() {
        // No TDM spawns
    }

    public void applyInventory(final BattlePlayer p) {
        // No predefined inventory
    }

}