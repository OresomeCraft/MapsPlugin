package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.*;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
import org.bukkit.inventory.*;

@MapConfig(
        name = "battlement",
        fullName = "Battlement",
        creators = {"ShaunDepro97"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = 91,
        y1 = 149,
        z1 = -92,
        x2 = -108,
        y2 = -7,
        z2 = 106
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 10,
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class Battlement extends BattleMap {

    public Battlement() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 23, 78, -24, 1, 0));
        redSpawns.add(new Location(w, 11, 70, -12, 1, 0));

        blueSpawns.add(new Location(w, -26, 78, 16, 3, 0));
        blueSpawns.add(new Location(w, -14, 70, 4, 3, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -30, 70, 20, 3, 0));
        FFASpawns.add(new Location(w, -30, 74, 20, 3, 0));
        FFASpawns.add(new Location(w, -30, 78, 20, 3, 0));
        FFASpawns.add(new Location(w, 0, 53, 0, 2, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(8, ARROWS);
        i.setItem(4, EXP);

    }

}
