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
        name = "academy",
        fullName = "OresomeTown Academy",
        creators = {"meganlovesmusic", "SuperDuckFace"},
        gamemodes = {Gamemode.TIOT}
)
@Attributes(
        allowBuild = false
)
public class OresomeAcademy extends TiOTMap {

    public OresomeAcademy() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 4, 63, -3, 90, 0));
        FFASpawns.add(new Location(w, 13, 63, -12, -0, 0));
        FFASpawns.add(new Location(w, 19, 63, -11, -0, 0));
        FFASpawns.add(new Location(w, 54, 63, 18));
        FFASpawns.add(new Location(w, 85, 63, 56, 90, 0));
        FFASpawns.add(new Location(w, 53, 63, 71, -180, 0));
        FFASpawns.add(new Location(w, 35, 63, 76));
        FFASpawns.add(new Location(w, 27, 63, 25));
        FFASpawns.add(new Location(w, 23, 64, 64));
        FFASpawns.add(new Location(w, 81, 64, 32));
        FFASpawns.add(new Location(w, 82, 64, 4));
        FFASpawns.add(new Location(w, 0, 63, 11));
        FFASpawns.add(new Location(w, 0, 63, 51));
        FFASpawns.add(new Location(w, -26, 63, 36));
        FFASpawns.add(new Location(w, -26, 68, 24, -0, 0));
        FFASpawns.add(new Location(w, -1, 64, 64));
        FFASpawns.add(new Location(w, -15, 64, 65));
        FFASpawns.add(new Location(w, -21, 64, 64));
        FFASpawns.add(new Location(w, 40, 76, 20));

        setCriminalTester(new CuboidRegion(new Location(w, 21, 63, 68), new Location(w, 25, 67, 71)));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
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
