package com.oresomecraft.maps.tiot.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.Map;
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
        name = "themepark",
        fullName = "Night Kanibaru",
        creators = {"huxtech", "shavahn2003", "xblazingxirex1", "kytron", "SereneMango", "xZizle123"},
        gamemodes = {Gamemode.TIOT}
)
@Attributes(
        allowBuild = false,
        timeLock = Map.Time.NIGHT
)
public class NightKanibaru extends TiOTMap {

    public NightKanibaru() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -43, 67, 2, -90, 0));
        FFASpawns.add(new Location(w, 1, 67, -55, 0, 0));
        FFASpawns.add(new Location(w, -41, 67, -47, -90, 0));
        FFASpawns.add(new Location(w, 56, 67, 29, 90, 0));
        FFASpawns.add(new Location(w, 57, 74, -58, 60, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 21, 67, 40), new Location(w, 25, 71, 43)));
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
