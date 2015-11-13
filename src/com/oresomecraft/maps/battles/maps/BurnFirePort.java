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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

@MapConfig(
        name = "burnfireport",
        fullName = "Burnfire Port",
        creators = {"bumsonfire"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -199,
        y1 = 93,
        z1 = 197,
        x2 = -8,
        y2 = 1,
        z2 = 29
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class BurnFirePort extends BattleMap implements Listener {

    public BurnFirePort() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -130, 29, 152, 176, 0);
        Location blueSpawn = new Location(w, -52, 37, 41, 90, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -179, 27, 81, -89, 0));
        redSpawns.add(new Location(w, -149, 28, 93, -88, 0));
        redSpawns.add(new Location(w, -148, 26, 97, 0, 0));
        redSpawns.add(new Location(w, -88, 26, 106, 90, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -99, 40, 41, 90, 0));
        blueSpawns.add(new Location(w, -11, 33, 62, 45, 0));
        blueSpawns.add(new Location(w, -115, 30, 79, 90, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -106, 26, 112, 179, 0);
        Location blueSpawn = new Location(w, -50, 32, 71, 0, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -130, 29, 152, 176, 0));
        FFASpawns.add(new Location(w, -52, 37, 41, 90, 0));
        FFASpawns.add(new Location(w, -179, 27, 81, -89, 0));
        FFASpawns.add(new Location(w, -149, 28, 93, -88, 0));
        FFASpawns.add(new Location(w, -148, 26, 97, 0, 0));
        FFASpawns.add(new Location(w, -88, 26, 106, 90, 0));
        FFASpawns.add(new Location(w, -99, 40, 41, 90, 0));
        FFASpawns.add(new Location(w, -11, 33, 62, 45, 0));
        FFASpawns.add(new Location(w, -115, 30, 79, 90, 0));
        FFASpawns.add(new Location(w, -86, 28, 87, 90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 1);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH_POTION);
        i.setItem(2, STEAK);
        i.setItem(4, EXP);
        i.setItem(10, ARROW);
    }

    @EventHandler
    public void preventMoveOutOfMap(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equals(getName())
                && !isInsideRegion(event.getTo())) {
            event.getPlayer().teleport(FFASpawns.get(new Random().nextInt(FFASpawns.size())));
        }
    }

}
