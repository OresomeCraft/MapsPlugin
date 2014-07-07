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
        name = "hiddenvillage",
        fullName = "Hidden Village",
        creators = {"SuperDuckFace"},
        gamemodes = {Gamemode.LMS, Gamemode.OITC}
)
@Region(
        x1 = -100,
        y1 = 123,
        z1 = 100,
        x2 = 0,
        y2 = 50,
        z2 = 0
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.ARROW, Material.BOW, Material.IRON_SWORD, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS}
)
public class HiddenVillage extends BattleMap implements Listener {

    public HiddenVillage() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        // null
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 31, 67, 42, 0, 0));
        FFASpawns.add(new Location(w, -26, 77, 61, 0, 90));
        FFASpawns.add(new Location(w, -75, 78, 73, 3, -120));
        FFASpawns.add(new Location(w, -32.5, 78, 31.5, 9, 54));
        FFASpawns.add(new Location(w, -39, 77, 75, 0, 180));
        FFASpawns.add(new Location(w, -41, 76, 35, 0, 0));
        FFASpawns.add(new Location(w, -71, 75, 55, 0, 0));
        FFASpawns.add(new Location(w, -63, 77, 27, 0, 0));
        FFASpawns.add(new Location(w, -55, 75, 54, 0, 0));
        FFASpawns.add(new Location(w, -50, 76, 59, 270, -90));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack GOLDEN_CARRETS = new ItemStack(Material.GOLDEN_CARROT, 6);
        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 32);

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        p.getInventory().setHelmet(IRON_HELMET);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setBoots(IRON_BOOTS);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, GOLDEN_CARRETS);
        i.setItem(3, HEALTH);
        i.setItem(9, ARROWS);
    }
}
