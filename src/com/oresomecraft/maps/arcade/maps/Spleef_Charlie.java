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
public class Spleef_Charlie extends ArcadeMap implements Listener {

    public Spleef_Charlie() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.DIAMOND_SPADE, Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
        setAllowPlace(false);
    }

    // Map details
    String name = "spleef_charlie";
    String fullName = "Spleef (Charlie)";
    String creators = "zachoz ";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 66, -22));
        FFASpawns.add(new Location(w, -11, 66, 11));
        FFASpawns.add(new Location(w, -21, 66, -0));
        FFASpawns.add(new Location(w, -11, 66, 12));
        FFASpawns.add(new Location(w, -0, 66, 23));
        FFASpawns.add(new Location(w, 0, 66, 23));
        FFASpawns.add(new Location(w, 12, 66, 12));
        FFASpawns.add(new Location(w, 22, 66, 0));
        FFASpawns.add(new Location(w, 11, 66, -11));
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
