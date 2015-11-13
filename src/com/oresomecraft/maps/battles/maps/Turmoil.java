package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.maps.MapsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

@MapConfig(
        name = "turmoil",
        fullName = "Turmoil",
        creators = {"Synxi"},
        gamemodes = {Gamemode.FFA}
)
@Region(
        x1 = 0,
        y1 = 76,
        z1 = -1952,
        x2 = -76,
        y2 = 0,
        z2 = -1857
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class Turmoil extends BattleMap implements Listener {

    public Turmoil() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -45, 32, -1892, 2, 0));

        blueSpawns.add(new Location(w, -45, 32, -1892, 2, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -37, 26, -1878, 2, 0));
        FFASpawns.add(new Location(w, -60, 26, -1883, 3, 0));
        FFASpawns.add(new Location(w, -56, 19, -1905, 0, 0));
        FFASpawns.add(new Location(w, -40, 17, -1879, 1, 0));
        FFASpawns.add(new Location(w, -57, 9, -1894, 3, 0));
        FFASpawns.add(new Location(w, -37, 5, -1905, 3, 0));
        FFASpawns.add(new Location(w, -24, 9, -1918, 0, 0));
        FFASpawns.add(new Location(w, -20, 18, -1942, 1, 0));
        FFASpawns.add(new Location(w, -66, 17, -1942, 3, 0));
        FFASpawns.add(new Location(w, -49, 17, -1926, 1, 0));
        FFASpawns.add(new Location(w, -29, 18, -1932, 1, 0));
        FFASpawns.add(new Location(w, -53, 9, -1937, 0, 0));
        FFASpawns.add(new Location(w, -8, 9, -1937, 0, 0));
        FFASpawns.add(new Location(w, -8, 19, -1878, 1, 0));
        FFASpawns.add(new Location(w, -45, 17, -1910, 3, 0));
        FFASpawns.add(new Location(w, -28, 22, -1896, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        final Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);

        i.setItem(0, IRON_SWORD);
        p.setItem(1, Material.BOW, 1);
        p.setItem(2, Material.COOKED_BEEF, 2);
        i.setItem(3, HEALTH_POTION);
        p.setItem(28, Material.ARROW, 48);

        new BukkitRunnable() {
            public void run() {
                pl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600 * 20, 1));
                pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
            }
        }.runTaskLater(MapsPlugin.getInstance(), 10L);
    }
}
