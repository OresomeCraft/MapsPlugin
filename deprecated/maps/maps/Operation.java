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
        name = "operation",
        fullName = "Operation",
        creators = {"meganlovesmusic", "SuperDuckFace", "ninsai"},
        gamemodes = {Gamemode.TIOT}
)
@Attributes(
        allowBuild = false
)
public class Operation extends TiOTMap {

    // Map details
    String name = "operation";
    String fullName = "Operation";
    String[] creators = {"meganlovesmusic", "SuperDuckFace", "ninsai"};
    Gamemode[] modes = {Gamemode.TIOT};

    public Operation() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -17, 67, -17, 50, 0));
        FFASpawns.add(new Location(w, -32, 67, 17, -127, 0));
        FFASpawns.add(new Location(w, -15, 67, 15, -139, 0));
        FFASpawns.add(new Location(w, 6, 67, 5, 90, 0));
        FFASpawns.add(new Location(w, 19, 67, 15, 158, 0));
        FFASpawns.add(new Location(w, 12, 67, 20, 180, 0));
        FFASpawns.add(new Location(w, 2, 67, -9, -32, 0));
        FFASpawns.add(new Location(w, -5, 67, -12, -22, 0));
        FFASpawns.add(new Location(w, -12, 67, -18, 0, 0));

        setCriminalTester(new CuboidRegion(new Location(w, -28, 66, -12), new Location(w, -32, 70, -16)));
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
