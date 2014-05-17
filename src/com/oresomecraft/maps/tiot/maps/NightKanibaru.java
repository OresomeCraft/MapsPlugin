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
public class NightKanibaru extends TiOTMap implements Listener {

    public NightKanibaru() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        lockTime("night");
    }

    // Map details
    String name = "themepark";
    String fullName = "Night Kanibaru";
    String[] creators = {"huxtech", "shavahn2003", "xblazingxirex1", "kytron", "SereneMango", "xZizle123"};
    Gamemode[] modes = {Gamemode.TIOT};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -43, 67, 2, -90, 0));
        FFASpawns.add(new Location(w, 1, 67, -55, 0, 0));
        FFASpawns.add(new Location(w, -41, 67, -47, -90, 0));
        FFASpawns.add(new Location(w, 56, 67, 29, 90, 0));
        FFASpawns.add(new Location(w, 57, 74, -58, 60, 0));

        setCriminalTester(new CuboidRegion(new Location(w, 21, 67, 40), new Location(w, 25, 71, 43)));
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
