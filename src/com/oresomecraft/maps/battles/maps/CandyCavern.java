package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "candycavern",
        fullName = "Candy Cavern",
        creators = {"PyroPolar"},
        gamemodes = {Gamemode.INFECTION, Gamemode.FFA, Gamemode.LTS, Gamemode.LMS}
)
@Region(
        x1 = -301,
        y1 = 179,
        z1 = 63,
        x2 = -379,
        y2 = 127,
        z2 = 143
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 10,
        disabledDrops = {Material.BOW, Material.IRON_SWORD, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.LEATHER_CHESTPLATE}
)
public class CandyCavern extends BattleMap {

    public CandyCavern() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -854, 156, 78, 1, 0));

        blueSpawns.add(new Location(w, -825, 150, 128, 179, 0));

    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -854, 156, 78, 1, 0));
        FFASpawns.add(new Location(w, -825, 150, 128, 179, 0));
        FFASpawns.add(new Location(w, -838, 139, 130, 179, 0));
        FFASpawns.add(new Location(w, -835, 140, 122, 90, 0));
        FFASpawns.add(new Location(w, -838, 142, 84, 1, 0));
        FFASpawns.add(new Location(w, -842, 139, 76, 1, 0));
        FFASpawns.add(new Location(w, -872, 135, 100, -90, 0));
        FFASpawns.add(new Location(w, -810, 137, 95, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BEEF = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        ItemUtils.nameItem(IRON_SWORD, ChatColor.WHITE + "Candy Cruncher");
        ItemUtils.nameItem(BOW, ChatColor.WHITE + "Lolly Lasher");
        ItemUtils.nameItem(BEEF, ChatColor.WHITE + "Licorice");
        ItemUtils.nameItem(HEALTH_POTION, ChatColor.WHITE + "Soda");
        ItemUtils.nameItem(ARROW, ChatColor.WHITE + "Gobstopper");

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, BEEF);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROW);
    }

}
