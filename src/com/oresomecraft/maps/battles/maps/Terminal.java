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

@MapConfig(
        name = "terminal",
        fullName = "Terminal",
        creators = {"zachoz", "XxXShadowSoul", "slider302"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION}
)
@Region(
        x1 = -207,
        y1 = 52,
        z1 = -1220,
        x2 = -38,
        y2 = 112,
        z2 = -1125
)
@Attributes(
        allowBuild = false,
        mergeTools = true,
        autoSpawnProtection = true,
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class Terminal extends BattleMap implements Listener {

    public Terminal() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, -116, 66, -1140, -178, 0));
        redSpawns.add(new Location(w, -72, 71, -1208, -1, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -72, 71, -1208, -1, 0);
        Location blueSpawn = new Location(w, -116, 66, -1140, -178, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -143, 66, -1211, -50, 0));
        FFASpawns.add(new Location(w, -142, 66, -1171, -108, 0));
        FFASpawns.add(new Location(w, -141, 66, -1142, -152, 0));
        FFASpawns.add(new Location(w, -127, 70, -1158, -162, 0));
        FFASpawns.add(new Location(w, -112, 66, -1162, 116, 0));
        FFASpawns.add(new Location(w, -125, 71, -1132, -95, 0));
        FFASpawns.add(new Location(w, -101, 71, -1159, -51, 0));
        FFASpawns.add(new Location(w, -109, 71, -1162, -132, 0));
        FFASpawns.add(new Location(w, -97, 66, -1171, 156, 0));
        FFASpawns.add(new Location(w, -83, 71, -1182, 41, 0));
        FFASpawns.add(new Location(w, -78, 71, -1188, -88, 0));
        FFASpawns.add(new Location(w, -43, 71, -1168, 124, 0));
        FFASpawns.add(new Location(w, -48, 71, -1197, 90, 0));
        FFASpawns.add(new Location(w, -91, 71, -1207, -89, 0));
        FFASpawns.add(new Location(w, -121, 71, -1190, 121, 0));
        FFASpawns.add(new Location(w, -139, 71, -1192, 89, 0));
        FFASpawns.add(new Location(w, -114, 66, -1191, 43, 0));
        FFASpawns.add(new Location(w, -77, 71, -1169, -90, 0));
        FFASpawns.add(new Location(w, -58, 71, -1147, 156, 0));
        FFASpawns.add(new Location(w, -91, 71, -1140, 141, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }

}
