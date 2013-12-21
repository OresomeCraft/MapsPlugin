package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Voidsflag extends BattleMap implements IBattleMap, Listener {

    public Voidsflag() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(20);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_AXE, Material.STONE_PICKAXE});
        setAutoSpawnProtection(10);
    }

    String name = "voidsflag";
    String fullName = "Voids Flag";
    String creators = "_Moist, MiCkEyMiCE and R3creat3";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -2, 23, 49);
        Location blueSpawn = new Location(w, -348, 23, 51);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, -71, 45, 60);
        Location blueFlag = new Location(w, -281, 45, 40);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -2, 23, 49);
        Location blueSpawn = new Location(w, -348, 23, 51);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack STONE_AXE = new ItemStack(Material.STONE_AXE, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack COOKED_PORKCHOP = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack BIRCH_LOG = new ItemStack(Material.LOG, 64);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_BOOTS, LEATHER_PANTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(5, HEALTH_POTION);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, STONE_AXE);
        i.setItem(5, COOKED_PORKCHOP);
        i.setItem(6, BIRCH_LOG);
        i.setItem(11, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 9;
    public int y1 = 86;
    public int z1 = 112;

    //Bottom right corner.
    public int x2 = -365;
    public int y2 = 2;
    public int z2 = -15;

    // Code to prevent block breaking.
    @EventHandler(priority = EventPriority.NORMAL)
    public void preventoffmapbreak(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (getArena().equals(name)) {
            if (!contains(loc, x1, x2, y1, y2, z1, z2)) {

                event.setCancelled(true);
            }
        }
    }

    //@EventHandler(priority = EventPriority.NORMAL)
    public void explodingArrow(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        World w = projectile.getWorld();
        Location hit = projectile.getLocation();

        if (w.getName().equals(name)) {

            if (projectile instanceof Arrow) {
                Arrow arrow = (Arrow) projectile;
                Entity shooter = arrow.getShooter();
                Location l = shooter.getLocation();
                Block bl = l.getBlock();
                Block b = bl.getRelative(BlockFace.DOWN, 2);
                Material mat = b.getType();

                if (shooter instanceof Player) {
                    Player p = (Player) shooter;
                    ItemStack is = p.getItemInHand();
                    Material i = is.getType();
                    if (i == Material.BOW && mat == Material.SPONGE) {
                        p.getInventory().removeItem(new ItemStack(Material.ARROW, 20));
                        w.createExplosion(hit, 8);
                        int strikes = 0;
                        while (strikes < 20) {
                            strikes++;
                            w.strikeLightning(hit);
                        }
                    }
                    Bukkit.getWorld(name).playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);
                }
            }
        }
    }
}
