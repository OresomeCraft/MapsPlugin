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
public class OresomeTownOffices extends TiOTMap implements Listener {

    public OresomeTownOffices() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "oresometownoffices";
    String fullName = "OresomeTown Offices";
    String[] creators = {"meganlovesmusic", "SuperDuckFace", "ninsai", "psgs"};
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -47, 80, 0, -90, 0));
        FFASpawns.add(new Location(w, 48, 80, 0, 90, 0));
        FFASpawns.add(new Location(w, 0, 80, 48, 180, 0));
        FFASpawns.add(new Location(w, 0, 80, -47));
        FFASpawns.add(new Location(w, 16, 70, -17, 47, 0));
        FFASpawns.add(new Location(w, -17, 70, 16, -132, 0));
        FFASpawns.add(new Location(w, 4, 58, 40, 152, 0));
        FFASpawns.add(new Location(w, -3, 58, -50, -23, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 2, 65, 2), new Location(w, -2, 70, -2)));
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
