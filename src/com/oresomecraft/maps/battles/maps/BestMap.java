package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.OresomeBattles.api.Monument;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class BestMap extends BattleMap implements IBattleMap, Listener {

    public BestMap() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "bestmap";
    String fullName = "The Best Damned Map in the Entire Galaxy";
    String creators = "recreate and vortexedblue";
    Gamemode[] modes = {Gamemode.KOTH, Gamemode.INFECTION, Gamemode.FFA, Gamemode.TDM, Gamemode.LMS, Gamemode.CTF, Gamemode.LTS};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -4.5, 71, 0.5));

        blueSpawns.add(new Location(w, -0.5, 71, 0.5));

        setKoTHMonument(new Location(w, -3, 72, 0));
        setCapturePoints(new Monument[]{new Monument("THE MIDDLE", name, new Location(w, -3, 72, 0))});
        setCTFFlags(name, new Location(w, -9, 72, 0), new Location(w, 3, 72, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -4.5, 71, 0.5));

        FFASpawns.add(new Location(w, -0.5, 71, 0.5));

    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BALLS = new ItemStack(Material.SNOW_BALL, 999);

        i.setItem(0, BALLS);
        i.setItem(1, HEALTH);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 29;
    public int y1 = 142;
    public int z1 = 56;

    //Bottom right corner.
    public int x2 = -203;
    public int y2 = 42;
    public int z2 = -72;

}
