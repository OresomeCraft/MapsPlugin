package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.TiOTMap;
import com.oresomecraft.OresomeBattles.region.CuboidRegion;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@MapConfig(
        name = "oresometownoffices",
        fullName = "OresomeTown Offices",
        creators = {"meganlovesmusic", "SuperDuckFace", "ninsai", "psgs"},
        gamemodes = {Gamemode.TIOT}
)
@Attributes(
        allowBuild = false
)
public class OresomeTownOffices extends TiOTMap {

    public OresomeTownOffices() {
        super.initiate(this);
    }

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
