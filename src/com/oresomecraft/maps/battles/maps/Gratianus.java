package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "gratianus",
        fullName = "Gratianus",
        creators = {"BlueVortexed"},
        gamemodes = {Gamemode.FFA, Gamemode.KOTH, Gamemode.INFECTION, Gamemode.LMS, Gamemode.LTS}
)
@Region(
        x1 = -100,
        y1 = 185,
        z1 = -105,
        x2 = 121,
        y2 = 34,
        z2 = 107
)
@Attributes(
        allowBuild = false,
        timeLock = Map.Time.DAY,
        disabledDrops = {Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.IRON_SWORD, Material.BOW, Material.ARROW}
)
public class Gratianus extends BattleMap implements Listener {

    public Gratianus() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {

        redSpawns.add(new Location(w, 0, 58, -59, -0, 0));
        blueSpawns.add(new Location(w, 0, 58, 59, -180, 0));

        setKoTHMonument(new Location(w, 0, 59, 0));

    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 0, 58, -59, -0, 0);
        Location blueSpawn = new Location(w, 0, 58, 59, -180, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 0.4, 88, -68.34, -0, 0));
        FFASpawns.add(new Location(w, 0, 88, 67, 180, 0));
        FFASpawns.add(new Location(w, 69, 88, 0, 90, 0));
        FFASpawns.add(new Location(w, -68.25, 88, 0, -88, 0));
        FFASpawns.add(new Location(w, -60, 58, 0, -90, 0));
        FFASpawns.add(new Location(w, 60, 58, 0, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 32);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH);
        i.setItem(4, STEAK);
        i.setItem(10, ARROWS);

    }

}
