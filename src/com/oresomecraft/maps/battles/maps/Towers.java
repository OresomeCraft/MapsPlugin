package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "towers",
        fullName = "Towers",
        creators = {"bruuceey", "Lyssieloo"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
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
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class Towers extends BattleMap implements Listener {

    public Towers() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 523, 11, -1095, -137, 0);
        Location blueSpawn = new Location(w, 523, 11, -1225, 51, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
        Location blueSpawn = new Location(w, 523, 11, -1225, 51, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 504, 10, -1142, -135, 0));
        FFASpawns.add(new Location(w, 542, 10, -1142, 134, 0));
        FFASpawns.add(new Location(w, 542, 10, -1180, 44, 0));
        FFASpawns.add(new Location(w, 504, 10, -1180, -45, 0));
        FFASpawns.add(new Location(w, 472, 16, -1149, 90, 0));
        FFASpawns.add(new Location(w, 464, 16, -1156, -89, 0));
        FFASpawns.add(new Location(w, 463, 16, -1176, -91, 0));
        FFASpawns.add(new Location(w, 561, 12, -1189, 89, 0));
        FFASpawns.add(new Location(w, 555, 19, -1117, 90, 0));
        FFASpawns.add(new Location(w, 570, 14, -1137, 90, 0));
        FFASpawns.add(new Location(w, 565, 11, -1218, 36, 0));
        FFASpawns.add(new Location(w, 493, 16, -1129, -136, 0));
        FFASpawns.add(new Location(w, 476, 16, -1119, -89, 0));
        FFASpawns.add(new Location(w, 469, 15, -1212, -89, 0));
        FFASpawns.add(new Location(w, 580, 21, -1199, 90, 0));
        FFASpawns.add(new Location(w, 580, 19, -1147, 103, 0));
        FFASpawns.add(new Location(w, 491, 13, -1136, -127, 0));
        FFASpawns.add(new Location(w, 555, 12, -1175, 66, 0));
        FFASpawns.add(new Location(w, 539, 11, -1231, 12, 0));
        FFASpawns.add(new Location(w, 486, 13, -1188, -54, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);
        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(9, ARROWS);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, EXP);

    }

    @EventHandler
    public void arrowParticle(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(getName());
        if (getArena().equals(getName()) && arrow instanceof Arrow)
            world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 8);
    }

}
