package com.oresomecraft.maps.battles.maps;

import org.bukkit.*;
import org.bukkit.inventory.*;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.*;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;

@MapConfig(
        name = "arcturus",
        fullName = "Arcturus",
        creators = {"PyroPolar"},
        gamemodes = {Gamemode.INFECTION, Gamemode.FFA, Gamemode.LTS, Gamemode.LMS, Gamemode.TDM, Gamemode.KOTH, Gamemode.CTF}
        //http://www.mediafire.com/download/2ds4i2b3sn9b3eo/Arcturus.zip
)
@Region(
        x1 = 160,
        y1 = 123,
        z1 = 755,
        x2 = 362,
        y2 = 1,
        z2 = 554
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
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        InvUtils.nameItem(IRON_SWORD, ChatColor.WHITE + "Candy Cruncher");
        InvUtils.nameItem(Material.BOW, ChatColor.WHITE + "Lolly Lasher");
        InvUtils.nameItem(Material.COOKED_BEEF, ChatColor.WHITE + "Licorice");
        InvUtils.nameItem(HEALTH_POTION, ChatColor.WHITE + "Soda");
        InvUtils.nameItem(Material.ARROW, ChatColor.WHITE + "Gobstopper");
        
        // setItem() is a BattlePlayer method. Makes giving items a bit quicker.
        p.setItem(0, IRON_SWORD);
        p.setItem(1, Material.BOW, 1);
        p.setItem(2, Material.COOKED_BEEF, 1);

        // This is the Bukkit way of doing it
        i.setItem(3, HEALTH_POTION);
        p.setItem(9, Material.ARROW, 64);
    }

}
