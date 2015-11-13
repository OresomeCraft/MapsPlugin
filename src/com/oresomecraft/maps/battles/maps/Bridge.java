package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
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
        name = "bridge",
        fullName = "Bridge",
        creators = {"fkBear", "ep1cn00bt00b"},
        gamemodes = {Gamemode.TDM, Gamemode.KOTH, Gamemode.CTF}
)
@Region(
        x1 = 45,
        y1 = 5,
        z1 = 35,
        x2 = -99,
        y2 = 127,
        z2 = -236
)
@Attributes(
        allowBuild = false,
        tdmTime = 15,
        autoSpawnProtection = true,
        disabledDrops = {Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_CHESTPLATE, Material.LEATHER_HELMET, Material.BOW}
)
public class Bridge extends BattleMap implements Listener {

    public Bridge() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -4, 64, 8, 317.4F, 14F));
        blueSpawns.add(new Location(w, -27, 64, -218, 144.2F, 1.1F));

        setKoTHMonument(new Location(w, -15, 67, -105));
        setCTFFlags(getName(), new Location(w, -16, 57, -52), new Location(w, -15, 67, -158));

    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -14, 66, -193, 0, 0));
        FFASpawns.add(new Location(w, -17, 66, -2, 180, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack EXP_BOTTLE = new ItemStack(Material.EXP_BOTTLE, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 16);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_LEGGINGS, LEATHER_BOOTS});

        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setLeggings(LEATHER_LEGGINGS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(2, GOLDEN_APPLE);
        i.setItem(1, BOW);
        i.setItem(9, ARROWS);
        i.setItem(3, STEAK);
        i.setItem(4, EXP_BOTTLE);
    }

}
