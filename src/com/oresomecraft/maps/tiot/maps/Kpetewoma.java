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
public class Kpetewoma extends TiOTMap implements Listener {

    public Kpetewoma() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "swamp";
    String fullName = "Kpetewoma";
    String[] creators = {"jslsa"};
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 16, 69, 83, 101, 0));
        FFASpawns.add(new Location(w, -47, 76, 68, -158, 0));
        FFASpawns.add(new Location(w, -33, 68, 57, -141, 0));
        FFASpawns.add(new Location(w, 19, 69, 25, 48, 0));
        FFASpawns.add(new Location(w, -32, 72, 84, -143, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 0, 69, 18), new Location(w, 4, 72, 14)));
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
