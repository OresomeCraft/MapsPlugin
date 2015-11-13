package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "spaceships",
        fullName = "SpaceShips",
        creators = {"sampighere", "zachoz", "Heartist"},
        gamemodes = {Gamemode.TDM}
)
@Region(
        x1 = 106,
        y1 = 110,
        z1 = 34,
        x2 = 246,
        y2 = 0,
        z2 = -191
)
@Attributes(
        blockBuildLimit = 72,
        autoSpawnProtection = true,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.ARROW, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_BOOTS, Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.LAVA_BUCKET}
)
public class Spaceships extends BattleMap implements Listener {

    public Spaceships() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location blueSpawn = new Location(w, 142, 43, -80, 0, 0);
        Location redSpawn = new Location(w, 210, 43, -80, 0, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location blueSpawn = new Location(w, 142, 43, -80, 0, 0);
        Location redSpawn = new Location(w, 210, 43, -80, 0, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, new ItemStack(Material.DIAMOND_SWORD, 1));
        i.setItem(1, new ItemStack(Material.BOW, 1));
        i.setItem(2, new ItemStack(Material.DIAMOND_PICKAXE, 1));
        i.setItem(3, new ItemStack(Material.COOKED_BEEF, 3));
        i.setItem(4, new ItemStack(Material.GOLDEN_APPLE, 2));
        i.setItem(5, new ItemStack(Material.LOG, 64));
        i.setItem(8, new ItemStack(Material.ENDER_PEARL, 1));
        i.setItem(9, new ItemStack(Material.ARROW, 64));
    }

    @EventHandler
    public void explode(EntityExplodeEvent event) {
        if (!event.getLocation().getWorld().getName().equals(getName())) {
            event.setYield(event.getYield() * 2);
        }
    }

    @EventHandler
    public void damage(EntityDamageEvent event) {
        if (!event.getEntity().getLocation().getWorld().getName().equals(getName())) {
            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
                event.setCancelled(true);
        }
    }
}
