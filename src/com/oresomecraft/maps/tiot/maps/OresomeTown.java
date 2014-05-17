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
public class OresomeTown extends TiOTMap implements Listener {

    public OresomeTown() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "oresometowntiot";
    String fullName = "OresomeTown";
    String[] creators = {"ninsai", "beadycottonwood", "XUHAVON", "meganlovesmusic", "SuperDuckFace", "_Husky_"};
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 3, 70, -11));
        FFASpawns.add(new Location(w, -1, 70, -40));
        FFASpawns.add(new Location(w, 26, 70, -25));
        FFASpawns.add(new Location(w, 56, 70, -42));
        FFASpawns.add(new Location(w, 74, 70, -41));
        FFASpawns.add(new Location(w, 68, 76, -28));
        FFASpawns.add(new Location(w, 66, 70, 2));
        FFASpawns.add(new Location(w, 36, 70, 6));
        FFASpawns.add(new Location(w, 12, 70, 0));
        FFASpawns.add(new Location(w, 4, 70, 20));
        FFASpawns.add(new Location(w, 48, 70, 26));
        FFASpawns.add(new Location(w, 68, 70, 35));
        FFASpawns.add(new Location(w, 13, 70, 36));
        FFASpawns.add(new Location(w, -46, 70, 22));
        FFASpawns.add(new Location(w, -74, 70, 33));
        FFASpawns.add(new Location(w, -78, 70, -0));
        FFASpawns.add(new Location(w, -80, 70, -24));
        FFASpawns.add(new Location(w, -69, 70, -39));
        FFASpawns.add(new Location(w, -36, 70, -37));
        FFASpawns.add(new Location(w, -20, 70, -40));
        FFASpawns.add(new Location(w, 15, 81, -39));
        FFASpawns.add(new Location(w, 40, 76, 20));
        FFASpawns.add(new Location(w, 45, 82, -33));
        FFASpawns.add(new Location(w, -26, 70, 33));
        FFASpawns.add(new Location(w, -43, 70, -4));

        setCriminalTester(new CuboidRegion(new Location(w, -4, 73, -47), new Location(w, 0, 69, -45)));
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
