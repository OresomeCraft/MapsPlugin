package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.*;

@MapConfig(
        name = "xenon",
        fullName = "Xenon",
        creators = {"kalikakitty"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION}
)
@Region(
        x1 = 7,
        y1 = 66,
        z1 = 52,
        x2 = 93,
        y2 = 0,
        z2 = -37
)
@Attributes(
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD},
        allowBuild = false,
        pearlDamage = false
)
public class Xenon extends BattleMap implements Listener {

    public Xenon() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 33, 2, 25);
        Location blueSpawn = new Location(w, 73, 2, -14);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 33, 2, 25);
        Location blueSpawn = new Location(w, 73, 2, -14);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 33, 2, -13));
        FFASpawns.add(new Location(w, 73, 2, 25));
        FFASpawns.add(new Location(w, 53, 3, 47));
        FFASpawns.add(new Location(w, 17, 2, 5));
        FFASpawns.add(new Location(w, 53, 3, -36));
        FFASpawns.add(new Location(w, 53, 11, 5));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 16);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, EXP);
        i.setItem(9, ARROWS);
    }

    @EventHandler
    public void onFireBow(EntityShootBowEvent event) {
        if (getArena().equals(getName())) {
            if (event.getEntityType() == EntityType.PLAYER) {

                Player player = (Player) event.getEntity();
                if (player.getInventory().contains(Material.ARROW)) {
                    event.setCancelled(true);
                    player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
                    player.launchProjectile(EnderPearl.class).setVelocity(event.getProjectile().getVelocity());
                } else {
                    event.setCancelled(true);
                }
            }
        }

    }
}
