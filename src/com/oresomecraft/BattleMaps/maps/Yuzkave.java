package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Yuzkave extends BattleMap implements IBattleMap, Listener {

    public Yuzkave() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setTDMTime(15);
        disableDrops(new Material[]{Material.MUSHROOM_SOUP, Material.GOLDEN_APPLE});
        lockTime("night");
    }

    // Map details
    String name = "yuzkave";
    String fullName = "Yuzkave";
    String creators = "Yuzko";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.CTF, Gamemode.KOTH, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 247, 67, -4));
        redSpawns.add(new Location(w, 245, 67, -10));
        redSpawns.add(new Location(w, 257, 68, -7));

        blueSpawns.add(new Location(w, 319, 66, -107));
        blueSpawns.add(new Location(w, 320, 68, -99));
        blueSpawns.add(new Location(w, 310, 69, -103));

        Location redFlag = new Location(w, 256, 69, -9);
        Location blueFlag = new Location(w, 308, 70, -99);
        setCTFFlags(name, redFlag, blueFlag);

        setKoTHMonument(new Location(w, 281, 70, -57));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 247, 67, -4));
        FFASpawns.add(new Location(w, 319, 66, -107));
        FFASpawns.add(new Location(w, 267, 69, -53));
        FFASpawns.add(new Location(w, 242, 68, -52));
        FFASpawns.add(new Location(w, 273, 69, -100));
        FFASpawns.add(new Location(w, 330, 71, -76));
        FFASpawns.add(new Location(w, 303, 69, -74));
        FFASpawns.add(new Location(w, 307, 64, -33));
        FFASpawns.add(new Location(w, 285, 65, -9));
        FFASpawns.add(new Location(w, 284, 72, -33));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack SOUP = new ItemStack(Material.MUSHROOM_SOUP, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, SWORD);
        i.setItem(1, BOW);
        i.setItem(2, SOUP);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(5, EXP);
        i.setItem(10, ARROWS);


    }

    @EventHandler
    public void onEat(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player p = event.getPlayer();
            if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
                p.setHealth(p.getHealth() + 3);
                p.getItemInHand().setType(Material.AIR);
            }
        }

    }

    @EventHandler
    public void arrowAway(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        Location loc = projectile.getLocation();
        if (loc.getWorld().getName().equals(name)) {
            if (projectile instanceof Arrow) {
                Arrow a = (Arrow) projectile;
                a.remove();
            }
        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 208;
    public int y1 = 96;
    public int z1 = 13;

    //Bottom right corner.
    public int x2 = 362;
    public int y2 = 42;
    public int z2 = -142;

}
