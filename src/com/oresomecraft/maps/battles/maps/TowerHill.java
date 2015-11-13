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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig(
        name = "towerhill",
        fullName = "Tower Hill",
        creators = {"Hourani95"},
        gamemodes = {Gamemode.INFECTION, Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -84,
        y1 = 153,
        z1 = 43,
        x2 = 22,
        y2 = 23,
        z2 = -67
)
@Attributes(
        allowBuild = true,
        disabledDrops = {Material.ARROW, Material.GOLD_BOOTS, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_HELMET, Material.IRON_SWORD, Material.BOW}
)
public class TowerHill extends BattleMap implements Listener {

    public TowerHill() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -31, 71, 13, -34, 0);
        Location blueSpawn = new Location(w, -40, 71, -31, -82, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -43, 71, 12, -64, 0));
        redSpawns.add(new Location(w, -11, 71, -1, 179, 0));
        redSpawns.add(new Location(w, -57, 71, -1, -165, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -12, 75, -7, 89, 0));
        blueSpawns.add(new Location(w, -57, 71, -26, -62, 0));
        blueSpawns.add(new Location(w, -56, 75, -8, -89, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -31, 71, 13, -34, 0));
        FFASpawns.add(new Location(w, -40, 71, -31, -82, 0));
        FFASpawns.add(new Location(w, -43, 71, 12, -64, 0));
        FFASpawns.add(new Location(w, -11, 71, -1, 179, 0));
        FFASpawns.add(new Location(w, -57, 71, -1, -165, 0));
        FFASpawns.add(new Location(w, -12, 75, -7, 89, 0));
        FFASpawns.add(new Location(w, -57, 71, -26, -62, 0));
        FFASpawns.add(new Location(w, -56, 75, -8, -89, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack GOLD_HELMET = new ItemStack(Material.GOLD_HELMET, 1);
        ItemStack GOLD_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack GOLD_BOOTS = new ItemStack(Material.GOLD_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        GOLD_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 2);

        pl.getInventory().setBoots(GOLD_BOOTS);
        pl.getInventory().setLeggings(GOLD_PANTS);
        pl.getInventory().setChestplate(GOLD_CHESTPLATE);
        pl.getInventory().setHelmet(GOLD_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(35, ARROWS);

        pl.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

    }

}
