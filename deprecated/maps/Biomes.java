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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig(
        name = "biom",
        fullName = "Biomes",
        creators = {"SereneMango", "miniwolf35"},
        gamemodes = {Gamemode.LMS, Gamemode.FFA}
)
@Region(
        x1 = -61,
        y1 = 228,
        z1 = 80,
        x2 = 171,
        y2 = 53,
        z2 = -149
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.BOW, Material.ARROW, Material.LEATHER_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.LEATHER_BOOTS, Material.IRON_SWORD}
)
public class Biomes extends BattleMap implements Listener {

    String name = "biom";
    String fullName = "Biomes";
    String[] creators = {"SereneMango", "miniwolf35"};
    Gamemode[] modes = {Gamemode.LMS, Gamemode.FFA};

    public Biomes() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 117, 84, -94, 0, 0));
        redSpawns.add(new Location(w, 102, 84, -115, 18, 0));
        redSpawns.add(new Location(w, 66, 84, -83, -141, 0));
        redSpawns.add(new Location(w, 10, 84, -110, -56, 0));
        redSpawns.add(new Location(w, -19, 84, -85, -56, 0));
        redSpawns.add(new Location(w, -14, 86, -54, -61, 0));

        blueSpawns.add(new Location(w, -13, 84, 1, -125, 0));
        blueSpawns.add(new Location(w, 1, 84, -10, -63, 0));
        blueSpawns.add(new Location(w, 107, 84, 47, 130, 0));
        blueSpawns.add(new Location(w, 93, 84, 3, -0, 0));
        blueSpawns.add(new Location(w, 120, 84, 18, -146, 0));
        blueSpawns.add(new Location(w, 123, 84, -11, 113, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 117, 84, -94, 0, 0));
        FFASpawns.add(new Location(w, 102, 84, -115, 18, 0));
        FFASpawns.add(new Location(w, 66, 84, -83, -141, 0));
        FFASpawns.add(new Location(w, 10, 84, -110, -56, 0));
        FFASpawns.add(new Location(w, -19, 84, -85, -56, 0));
        FFASpawns.add(new Location(w, -14, 86, -54, -61, 0));
        FFASpawns.add(new Location(w, -13, 84, 1, -125, 0));
        FFASpawns.add(new Location(w, 1, 84, -10, -63, 0));
        FFASpawns.add(new Location(w, 107, 84, 47, 130, 0));
        FFASpawns.add(new Location(w, 93, 84, 3, -0, 0));
        FFASpawns.add(new Location(w, 120, 84, 18, -146, 0));
        FFASpawns.add(new Location(w, 123, 84, -11, 113, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 32);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        pl.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1, 9999 * 9999));

        pl.getInventory().setHelmet(LEATHER_HELMET);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setBoots(LEATHER_BOOTS);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH_POTION);
        i.setItem(3, STEAK);
        i.setItem(9, ARROWS);

    }

}
