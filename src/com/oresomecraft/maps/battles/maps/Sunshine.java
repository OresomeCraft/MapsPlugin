package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

@MapConfig(
        name = "sunshine",
        fullName = "Sunshine",
        creators =  {"iR3", "am51407", "_Moist"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -58,
        y1 = 160,
        z1 = -51,
        x2 = 56,
        y2 = 68,
        z2 = 58
)
@Attributes(
        allowBuild = true,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.BOW, Material.LEATHER_HELMET,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.WOOD_SWORD},
        timeLock = Map.Time.DAY
)
public class Sunshine extends BattleMap implements Listener {

    public Sunshine() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, -37, 89, 2));
        redSpawns.add(new Location(w, 32, 89, 2));

        setKoTHMonument(new Location(w, -3, 88, 2));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -37, 89, 2));
        FFASpawns.add(new Location(w, 32, 89, 2));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        //Items
        ItemStack IRON_AXE = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 8);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 8);
        //prevent arrow camping
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);

        // Armor
        setColouredArmorAccordingToTeam(p);

        i.setItem(0, IRON_AXE);
        i.setItem(1, BOW);
        i.setItem(2, BREAD);
        i.setItem(3, HEALTH);
        i.setItem(27, ARROW);

    }

}
