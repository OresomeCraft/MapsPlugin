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
public class Frozen extends TiOTMap implements Listener {

    public Frozen() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "frozenspikes";
    String fullName = "Frozen Spikes";
    String[] creators = {"SuperDuckFace", "meganlovesmusic"};
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 68, 78, 78));
        FFASpawns.add(new Location(w, 126, 64, 85));
        FFASpawns.add(new Location(w, 71, 76, 44));
        FFASpawns.add(new Location(w, 107, 105, 58));
        FFASpawns.add(new Location(w, 130, 79, 46));
        FFASpawns.add(new Location(w, 65, 78, -6));
        FFASpawns.add(new Location(w, 110, 75, 8));
        FFASpawns.add(new Location(w, 122, 76, 35));
        FFASpawns.add(new Location(w, 53, 77, 10));
        FFASpawns.add(new Location(w, 16, 79, 23));
        FFASpawns.add(new Location(w, 14, 83, 84));
        FFASpawns.add(new Location(w, -11, 82, 0));
        FFASpawns.add(new Location(w, -17, 77, 56));
        FFASpawns.add(new Location(w, 4, 105, 61));
        FFASpawns.add(new Location(w, -1, 81, -25));
        FFASpawns.add(new Location(w, 20, 80, -37));
        FFASpawns.add(new Location(w, 43, 77, 74));
        FFASpawns.add(new Location(w, 55, 113, -19));
        FFASpawns.add(new Location(w, 17, 109, 2));
        FFASpawns.add(new Location(w, -4, 113, 46));

        setCriminalTester(new CuboidRegion(new Location(w, 83, 71, 39), new Location(w, 79, 64, 43)));
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
