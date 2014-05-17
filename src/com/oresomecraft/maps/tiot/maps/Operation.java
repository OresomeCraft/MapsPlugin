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
public class Operation extends TiOTMap implements Listener {

    public Operation() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "operation";
    String fullName = "Operation";
    String[] creators = {"meganlovesmusic", "SuperDuckFace", "ninsai"};
    Gamemode[] modes = {Gamemode.TIOT};

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
