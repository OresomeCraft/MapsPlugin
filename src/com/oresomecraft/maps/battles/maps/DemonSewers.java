package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "demonsewers",
        fullName = "Demon Sewers",
        creators = {"Zdav2002", "xBlazingxFirex1", "shavahn2003"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -19,
        y1 = 58,
        z1 = 33,
        x2 = 177,
        y2 = 27,
        z2 = -79
)
@Attributes(
        allowBuild = false
        // TODO: fix disabling all item dropiing
)
public class DemonSewers extends BattleMap implements Listener {

    public DemonSewers() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 139, 38, -31, 1, 0));
        redSpawns.add(new Location(w, -9, 110, -20, 0, 0));
        redSpawns.add(new Location(w, 21, 105, -13, 0, 0));
        redSpawns.add(new Location(w, 4, 106, -41, 0, 0));

        blueSpawns.add(new Location(w, 19, 49, -18, 0, 0));
        blueSpawns.add(new Location(w, 46, 49, 8, 1, 0));
        blueSpawns.add(new Location(w, 46, 49, -19, 1, 0));
        blueSpawns.add(new Location(w, 4, 49, 13, 2, 0));
        blueSpawns.add(new Location(w, 65, 42, -9, 1, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 19, 49, -18, 0, 0));
        FFASpawns.add(new Location(w, 46, 49, 8, 1, 0));
        FFASpawns.add(new Location(w, 41, 42, -38, 3, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        p.setItem(1, Material.BOW, 1);
        p.setItem(2, Material.BREAD, 5);
        p.setItem(9, Material.ARROW, 5);
    }

}
