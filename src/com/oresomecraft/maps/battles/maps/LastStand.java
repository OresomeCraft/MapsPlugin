package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "laststand",
        fullName = "Last Stand",
        creators = {"miniwolf35", "SereneMango"},
        gamemodes = {Gamemode.INFECTION}
)
// TODO: Region
@Region(
        x1 = 0,
        y1 = 0,
        z1 = 0,
        x2 = 0,
        y2 = 0,
        z2 = 0
)
@Attributes(
        allowBuild = true,
        disabledDrops = {}
)
public class LastStand extends BattleMap {

    public LastStand() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -13, 65, 43, 0, 0));
        redSpawns.add(new Location(w, -29, 65, -43, 0, 0));

        blueSpawns.add(new Location(w, -9, 65, 40, 180, 0));
        blueSpawns.add(new Location(w, -29, 65, -43, 0, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -29, 65, -43, 0, 0));
        FFASpawns.add(new Location(w, -29, 65, -43, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 32);

        i.setBoots(IRON_BOOTS);
        i.setLeggings(IRON_PANTS);
        i.setChestplate(IRON_CHESTPLATE);
        i.setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }

}
