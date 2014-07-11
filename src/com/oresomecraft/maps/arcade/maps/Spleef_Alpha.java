package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.ArcadeMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "spleef_alpha",
        fullName = "Spleef (Alpha)",
        creators = {"zachoz"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        blockBuildLimit = 105,
        disabledDrops = {Material.DIAMOND_SPADE, Material.COOKED_BEEF},
		allowPhysicalPlayerDamage = false
)
public class Spleef_Alpha extends ArcadeMap implements Listener {

    public Spleef_Alpha() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 2, 105, 14));
        FFASpawns.add(new Location(w, -1, 105, 33));
        FFASpawns.add(new Location(w, -2, 105, -3));
        FFASpawns.add(new Location(w, 10, 105, 14));
        FFASpawns.add(new Location(w, -16, 105, 14));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack DIAMOND_SPADE = new ItemStack(Material.DIAMOND_SPADE, 1);
        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 16);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        ItemUtils.nameItem(DIAMOND_SPADE, ChatColor.BLUE + "Spleefer's Shovel");

        i.setItem(0, DIAMOND_SPADE);
        i.setItem(1, SNOW_BALL);
        i.setItem(2, STEAK);
    }

}
