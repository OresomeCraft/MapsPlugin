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
public class Arcturus extends BattleMap {

    public Arcturus() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 262, 18, 566, 1, 0));

        blueSpawns.add(new Location(w, 261, 18, 743, 179, 0));
        
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 238, 56, 721, -135, 0));
        FFASpawns.add(new Location(w, 290, 24, 687, 135, 0));
        FFASpawns.add(new Location(w, 289, 66, 629, 45, 0));
        FFASpawns.add(new Location(w, 329, 63, 626, 90, 0));
        FFASpawns.add(new Location(w, 282, 45, 577, 45, 0));
        FFASpawns.add(new Location(w, 247, 55, 617, -45, 0));
        FFASpawns.add(new Location(w, 216, 24, 618, -45, 0));
        FFASpawns.add(new Location(w, 190, 72, 653, -90, 0));
        FFASpawns.add(new Location(w, 207, 27, 653, -90, 0));
        FFASpawns.add(new Location(w, 224, 90, 685, -35, 0));
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