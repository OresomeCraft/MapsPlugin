package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.ArcadeMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "tntdm_charlie",
        fullName = "TNTDM (Charlie)",
        creators = {"SuperDuckFace", "ninsai"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        disabledDrops = {Material.COOKED_BEEF}
)
public class TNTDM_Charlie extends ArcadeMap implements Listener {

    public TNTDM_Charlie() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 39, 52, 0, 90, 0));
        FFASpawns.add(new Location(w, 0, 52, -39, -176, 0));
        FFASpawns.add(new Location(w, -38, 52, 0, -90, 0));
        FFASpawns.add(new Location(w, 0, 52, -38, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack TNT = new ItemStack(Material.TNT, 192);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, TNT);
        i.setItem(1, STEAK);

    }
}
