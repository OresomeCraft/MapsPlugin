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
        name = "modern",
        fullName = "Paradise",
        creators = {"SuperDuckFace", "meganlovesmusic", "ninsai"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -186,
        y1 = 189,
        z1 = -163,
        x2 = 156,
        y2 = 38,
        z2 = 151
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class Paradise extends BattleMap implements Listener {
    
           public BattleTemplate() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    public Paradise() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -102, 108, 103));
        blueSpawns.add(new Location(w, 51, 111, -83));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -114, 108, -89, 54, 0));
        FFASpawns.add(new Location(w, -145, 108, -41, 115, 0));
        FFASpawns.add(new Location(w, -117, 108, -83, -90, 0));
        FFASpawns.add(new Location(w, -102, 108, 103, 0, 0));
        FFASpawns.add(new Location(w, -147, 108, -41, -115, 0));
        FFASpawns.add(new Location(w, -137, 108, 48, 0, 0));
        FFASpawns.add(new Location(w, -48, 106, 107, 90, 0));
        FFASpawns.add(new Location(w, 2, 108, 101, 128, 0));
        FFASpawns.add(new Location(w, 50, 112, 60, 90, 0));
        FFASpawns.add(new Location(w, 57, 109, 26, 176, 0));
        FFASpawns.add(new Location(w, 45, 111, -80, 20, 0));
        FFASpawns.add(new Location(w, 13, 117, -146, -70, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 32);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH_POTION);
        i.setItem(3, STEAK);
        i.setItem(11, ARROW);
    }

}
