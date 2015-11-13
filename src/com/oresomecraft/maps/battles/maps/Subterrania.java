package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.*;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig(
        name = "subterrania",
        fullName = "Subterrania",
        creators = {"Heartist"},
        gamemodes = {Gamemode.FFA, Gamemode.TDM}
)
@Region(
        x1 = -163,
        y1 = 95,
        z1 = 143,
        x2 = 58,
        y2 = 2,
        z2 = -27
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 10,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.STONE_SWORD, Material.RAILS}
)
public class Subterrania extends BattleMap implements Listener {

    public Subterrania() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 18, 28, 84));
        redSpawns.add(new Location(w, 13, 25, 61));
        redSpawns.add(new Location(w, -10, 25, 38));
        redSpawns.add(new Location(w, -53, 23, 19));
        blueSpawns.add(new Location(w, -110, 21, 43));
        blueSpawns.add(new Location(w, -83, 21, 13));
        blueSpawns.add(new Location(w, -128, 49, 32));
        blueSpawns.add(new Location(w, -70, 49, 50));
        blueSpawns.add(new Location(w, -77, 50, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 18, 28, 84));
        FFASpawns.add(new Location(w, 13, 25, 61));
        FFASpawns.add(new Location(w, -10, 25, 38));
        FFASpawns.add(new Location(w, -53, 23, 19));
        FFASpawns.add(new Location(w, -110, 21, 43));
        FFASpawns.add(new Location(w, -83, 21, 13));
        FFASpawns.add(new Location(w, -128, 49, 32));
        FFASpawns.add(new Location(w, -70, 49, 50));
        FFASpawns.add(new Location(w, -77, 50, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 6);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack MASK = new ItemStack(Material.RAILS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setLeggings(LEATHER_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(MASK);

        pl.getInventory().getHelmet().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        pl.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 5000 * 20, 5000 * 20));

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(4, EXP);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }
}
