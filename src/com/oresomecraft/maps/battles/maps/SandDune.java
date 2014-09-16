package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@MapConfig(
        name = "sanddune",
        fullName = "Sand Dune",
        creators = {"miniwolf35"},
        gamemodes = {Gamemode.TDM}
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
        allowBuild = false,
        disabledDrops = {Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class SandDune extends BattleMap {

    public SandDune() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -27, 66, 11, 0, 0));
        redSpawns.add(new Location(w, -27, 66, 42, 90, 0));

        blueSpawns.add(new Location(w, 11, 70.5, 3, 90, 0));
        blueSpawns.add(new Location(w, 36, 77, 13, 90, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -27, 66, 42, 90, 0));
        FFASpawns.add(new Location(w, 36, 77, 13, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        PlayerInventory i = p.getInventory();

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 6);
        ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);

        i.setBoots(IRON_BOOTS);
        i.setLeggings(IRON_PANTS);
        i.setChestplate(IRON_CHESTPLATE);
        i.setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, STEAK);
        i.setItem(2, GOLDEN_APPLE);
    }

}
