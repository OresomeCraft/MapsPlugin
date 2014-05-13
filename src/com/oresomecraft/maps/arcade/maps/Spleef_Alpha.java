package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Spleef_Alpha extends ArcadeMap implements Listener {

    public Spleef_Alpha() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.DIAMOND_SPADE, Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
        setBuildLimit(105);
        setAllowPlace(false);
    }

    // Map details
    String name = "spleef_alpha";
    String fullName = "Spleef (Alpha)";
    String[] creators = {"zachoz"};
    Gamemode[] modes = {Gamemode.LMS};

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

        InvUtils.nameItem(DIAMOND_SPADE, ChatColor.BLUE + "Spleefer's Shovel");

        i.setItem(0, DIAMOND_SPADE);
        i.setItem(1, SNOW_BALL);
        i.setItem(2, STEAK);
    }

}
