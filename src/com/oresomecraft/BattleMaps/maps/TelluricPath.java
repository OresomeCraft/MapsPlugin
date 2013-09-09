package com.oresomecraft.BattleMaps.maps;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class TelluricPath extends BattleMap implements IBattleMap, Listener {

    public TelluricPath() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(20);
        setAllowBuild(false);
        disablePearlDamage(true);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS,
                Material.LEATHER_LEGGINGS, Material.STONE_SWORD, Material.FERMENTED_SPIDER_EYE, Material.IRON_PICKAXE});
    }

    String name = "telluricpath";
    String fullName = "Telluric Path";
    String creators = "R3creat3";
    Gamemode[] modes = {Gamemode.CTF, Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -133, 26, -36));
        blueSpawns.add(new Location(w, -1, 26, 34));
        redSpawns.add(new Location(w, -130, 26, 23));
        blueSpawns.add(new Location(w, -7, 26, -27));
        Location redFlag = new Location(w, -125, 27, -9);
        Location blueFlag = new Location(w, -12, 27, 6);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -133, 26, -36));
        FFASpawns.add(new Location(w, -1, 26, 34));
        FFASpawns.add(new Location(w, -130, 26, 23));
        FFASpawns.add(new Location(w, -7, 26, -27));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemStack poison = new ItemStack(Material.FERMENTED_SPIDER_EYE, 1);
        poison.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 1);
        ItemMeta po = poison.getItemMeta();
        po.setDisplayName(ChatColor.BLUE + "Poison Eye");

        List<String> pLore = new ArrayList<String>();
        pLore.add(org.bukkit.ChatColor.BLUE + "Hit players with this to poison them!");
        po.setLore(pLore);
        poison.setItemMeta(po);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, PUMPKIN_PIE);
        i.setItem(4, APPLE);
        i.setItem(8, poison);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta hel = (LeatherArmorMeta)LEATHER_HELMET.getItemMeta();
        hel.setColor(Color.PURPLE);
        LEATHER_HELMET.setItemMeta(hel);
        LeatherArmorMeta che = (LeatherArmorMeta)LEATHER_CHESTPLATE.getItemMeta();
        che.setColor(Color.PURPLE);
        LEATHER_CHESTPLATE.setItemMeta(che);
        LeatherArmorMeta leg = (LeatherArmorMeta)LEATHER_PANTS.getItemMeta();
        leg.setColor(Color.PURPLE);
        LEATHER_PANTS.setItemMeta(leg);
        LeatherArmorMeta boo = (LeatherArmorMeta)LEATHER_BOOTS.getItemMeta();
        boo.setColor(Color.PURPLE);
        LEATHER_BOOTS.setItemMeta(boo);
        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    //Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;
}
