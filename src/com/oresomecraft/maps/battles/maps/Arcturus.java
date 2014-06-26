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
        
        Location redFlag = new Location(w, 261, 39, 601);
        Location blueFlag = new Location(w, 259, 39, 708);
        setCTFFlags(name, redFlag, blueFlag);
        
        setKoTHMonument(new Location(w, 261, 55, 653));
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
        ItemStack BOW = new ItemStack(Material.BOW, 1);\
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack BOAT = new ItemStack(Material.BOAT, 1);]
        ItemStack DIAMOND_HOE = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemStack ENDER_PEARL = new ItemStack(Material.ENDER_PEARL, 1);

        IRON_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 3);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        ItemUtils.nameItem(DIAMOND_HOE, ChatColor.YELLOW + "Sandstone Hook");

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, DIAMOND_HOE);
        i.setItem(3, BOAT);
        i.setItem(4, STEAK);
        i.setItem(5, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }

}
