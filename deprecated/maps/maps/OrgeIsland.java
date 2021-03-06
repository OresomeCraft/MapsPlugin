package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.TiOTMap;
import com.oresomecraft.OresomeBattles.region.CuboidRegion;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@MapConfig(
        name = "orgeisland",
        fullName = "Orge Island",
        creators = {"miniwolf35", "SereneMango"},
        gamemodes = {Gamemode.TIOT}
)
@Attributes(
        allowBuild = false
)
public class OrgeIsland extends TiOTMap {

    public OrgeIsland() {
        super.initiate(this);
    }

    public void readyFFASpawns() {

        FFASpawns.add(new Location(w, 40, 69, -9, 90, 0));
        FFASpawns.add(new Location(w, -24, 67, 6, -90, 0));
        FFASpawns.add(new Location(w, -26, 67, -27, 0, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 14, 66, 3), new Location(w, 10, 70, 0)));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        if (p.getTeamType() == Team.TIOT_INVESTIGATORS) {
            ItemStack INVESTIGATOR_HAT = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta hatMeta = (LeatherArmorMeta) INVESTIGATOR_HAT.getItemMeta();
            hatMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Investigator's Hat");
            hatMeta.setColor(Color.PURPLE);
            INVESTIGATOR_HAT.setItemMeta(hatMeta);
            pl.getInventory().setHelmet(INVESTIGATOR_HAT);
        }
    }

}