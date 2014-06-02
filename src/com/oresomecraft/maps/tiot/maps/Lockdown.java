package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.map.Map;
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
        name = "elysiumruins",
        fullName = "Elysium Ruins",
        creators = {"ShaunDepro97"},
        gamemodes = {Gamemode.TIOT}
)
@Attributes(
        allowBuild = false
)
public class Lockdown extends TiOTMap {

    public Lockdown() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 440, 4, 360, 90, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 452, 7, 312), new Location(w, 448, 3, 316)));
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
