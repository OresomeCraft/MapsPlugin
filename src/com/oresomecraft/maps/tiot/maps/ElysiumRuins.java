package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.tiot.TiOTMap;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@MapConfig
public class ElysiumRuins extends TiOTMap implements Listener {

    public ElysiumRuins() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        lockTime("night");
    }

    // Map details
    String name = "elysiumruins";
    String fullName = "Elysium Ruins";
    String[] creators = {"meganlovesmusic"};
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 30, 36, -180, 0));
        FFASpawns.add(new Location(w, 0, 30, -19, 0, 0));
        FFASpawns.add(new Location(w, 28, 30, 9, 90, 0));
        FFASpawns.add(new Location(w, -27, 30, 7, -90, 0));
        FFASpawns.add(new Location(w, -31, 34, -3, -65, 0));
        FFASpawns.add(new Location(w, -11, 34, -23, -20, 0));
        FFASpawns.add(new Location(w, 12, 34, -23, 20, 0));
        FFASpawns.add(new Location(w, 32, 34, -3, 70, 0));
        FFASpawns.add(new Location(w, 32, 34, 20, 111, 0));
        FFASpawns.add(new Location(w, 12, 34, 40, 158, 0));
        FFASpawns.add(new Location(w, -11, 34, 40, -158, 0));
        FFASpawns.add(new Location(w, -31, 34, 20, -113, 0));
        FFASpawns.add(new Location(w, -4, 32, -1, -30, 0));
        FFASpawns.add(new Location(w, 4, 34, 20, 158, 0));
        FFASpawns.add(new Location(w, 24, 30, 32, 138, 0));
        FFASpawns.add(new Location(w, 24, 30, -15, 44, 0));
        FFASpawns.add(new Location(w, -23, 30, 32, -138, 0));
        FFASpawns.add(new Location(w, 37, 35, 29, -162, 0));
        FFASpawns.add(new Location(w, 15, 30, 49, -180, 0));
        FFASpawns.add(new Location(w, -24, 30, 43, -1, 0));
        FFASpawns.add(new Location(w, -36, 34, 23, 155, 0));
        FFASpawns.add(new Location(w, -39, 33, -14, -90, 0));
        FFASpawns.add(new Location(w, -17, 35, -33, -53, 0));
        FFASpawns.add(new Location(w, 23, 33, -30, -27, 0));
        FFASpawns.add(new Location(w, 41, 30, -6, 157, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 2, 29, 6), new Location(w, -2, 33, 10)));
    }

    public void applyInventory(final BattlePlayer p) {
        if (p.getTeamType() == Team.TIOT_INVESTIGATORS) {
            ItemStack INVESTIGATOR_HAT = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta hatMeta = (LeatherArmorMeta) INVESTIGATOR_HAT.getItemMeta();
            hatMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Investigator's Hat");
            hatMeta.setColor(Color.PURPLE);
            INVESTIGATOR_HAT.setItemMeta(hatMeta);
            p.getInventory().setHelmet(INVESTIGATOR_HAT);
        }
    }

}
