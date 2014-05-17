package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Xenon extends BattleMap implements Listener {

    public Xenon() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
        setAllowBuild(false);
        disablePearlDamage(true);
    }

    String name = "xenon";
    String fullName = "Xenon";
    String[] creators = {"kalikakitty"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

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
        defineRegion(x1, x2, y1, y2, z1, z2);
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

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 7;
    public int y1 = 66;
    public int z1 = 52;

    //Bottom right corner.
    public int x2 = 93;
    public int y2 = 0;
    public int z2 = -37;

    @EventHandler
    public void onFireBow(EntityShootBowEvent event) {
        if (getArena().equals(name)) {
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
