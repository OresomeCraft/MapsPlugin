package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "deepcaverns",
        fullName = "Deep Caverns",
        creators = {"kalysar", "Veladan", "MR_SKINNA7"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -168,
        y1 = 236,
        z1 = -144,
        x2 = 151,
        y2 = 1,
        z2 = 184
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 10,
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class Deepcaverns extends BattleMap implements Listener {
    public Deepcaverns() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 0, 99, 27, 2, 0));
        redSpawns.add(new Location(w, -9, 110, -20, 0, 0));
        redSpawns.add(new Location(w, 21, 105, -13, 0, 0));
        redSpawns.add(new Location(w, 4, 106, -41, 0, 0));
        redSpawns.add(new Location(w, -18, 101, 13, 0, 0));
        redSpawns.add(new Location(w, 2, 104, 15, 0, 0));
        redSpawns.add(new Location(w, -2, 109, -4, 0, 0));
        redSpawns.add(new Location(w, 28, 105, 10, 0, 0));
        redSpawns.add(new Location(w, 27, 96, 0, 0, 0));
        redSpawns.add(new Location(w, 30, 105, -14, 0, 0));
        redSpawns.add(new Location(w, -9, 107, 14, 0, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -9, 110, -20, 0, 0));
        blueSpawns.add(new Location(w, 0, 99, 27, 0, 0));
        blueSpawns.add(new Location(w, -16, 108, -3, 0, 0));
        blueSpawns.add(new Location(w, -30, 108, -3, 0, 0));
        blueSpawns.add(new Location(w, -18, 101, 13, 0, 0));
        blueSpawns.add(new Location(w, -27, 88, 17, 0, 0));
        blueSpawns.add(new Location(w, -32, 76, 16, 0, 0));
        blueSpawns.add(new Location(w, -46, 97, 7, 0, 0));
        blueSpawns.add(new Location(w, 26, 105, -13, 0, 0));
        blueSpawns.add(new Location(w, 21, 94, 2, 0, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 0, 99, 27, 2, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
        FFASpawns.add(new Location(w, 21, 105, -13, 0, 0));
        FFASpawns.add(new Location(w, 4, 106, -41, 0, 0));
        FFASpawns.add(new Location(w, -18, 101, 13, 0, 0));
        FFASpawns.add(new Location(w, 2, 104, 15, 0, 0));
        FFASpawns.add(new Location(w, -2, 109, -4, 0, 0));
        FFASpawns.add(new Location(w, 28, 105, 10, 0, 0));
        FFASpawns.add(new Location(w, 27, 96, 0, 0, 0));
        FFASpawns.add(new Location(w, 30, 105, -14, 0, 0));
        FFASpawns.add(new Location(w, -9, 106, 18, 0, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
        FFASpawns.add(new Location(w, 0, 99, 27, 0, 0));
        FFASpawns.add(new Location(w, -16, 108, -3, 0, 0));
        FFASpawns.add(new Location(w, -30, 108, -3, 0, 0));
        FFASpawns.add(new Location(w, -18, 101, 13, 0, 0));
        FFASpawns.add(new Location(w, -27, 88, 17, 0, 0));
        FFASpawns.add(new Location(w, -32, 76, 16, 0, 0));
        FFASpawns.add(new Location(w, -46, 97, 7, 0, 0));
        FFASpawns.add(new Location(w, 26, 105, -13, 0, 0));
        FFASpawns.add(new Location(w, 21, 94, 2, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, ARROWS);
        i.setItem(5, EXP);
    }

}
