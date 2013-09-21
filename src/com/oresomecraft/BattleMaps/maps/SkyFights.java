package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class SkyFights extends BattleMap implements IBattleMap, Listener {

    public SkyFights() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.GOLD_BOOTS, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_HELMET, Material.IRON_SWORD, Material.BOW});
        setAllowBuild(true);
    }

    // Map details
    String name = "skyfights";
    String fullName = "Sky Fights";
    String creators = "Turt1eManLol";
    Gamemode[] modes = {Gamemode.INFECTION, Gamemode.TDM, Gamemode.KOTH};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -62, 162, -66, -30, 0);
        Location blueSpawn = new Location(w, 53, 157, 88, 137, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setKoTHMonument(new Location(w, 3, 158, 9));
    }

    public void readyFFASpawns() {

        FFASpawns.add(new Location(w, -62, 162, -66, -30, 0));
        FFASpawns.add(new Location(w, 53, 157, 88, 137, 0));
        FFASpawns.add(new Location(w, 27, 167, 87, 89, 0));
        FFASpawns.add(new Location(w, -20, 174, 77, -176, 0));
        FFASpawns.add(new Location(w, -104, 170, 25, -173, 0));
        FFASpawns.add(new Location(w, 81, 161, -51, 33, 0));

    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);
        ItemStack LOG = new ItemStack(Material.LOG, 64);
        ItemStack STONE = new ItemStack(Material.STONE, 64);

        ItemMeta fishing_rod = FISHING_ROD.getItemMeta();
        fishing_rod.setDisplayName(ChatColor.BLUE + "Grappling hook");
        FISHING_ROD.setItemMeta(fishing_rod);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, FISHING_ROD);
        i.setItem(3, HEALTH);
        i.setItem(4, STEAK);
        i.setItem(5, LOG);
        i.setItem(6, STONE);
        i.setItem(11, ARROWS);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

    }

    // Top left corner.
    public int x1 = -84;
    public int y1 = 153;
    public int z1 = 43;

    //Bottom right corner.
    public int x2 = 22;
    public int y2 = 23;
    public int z2 = -67;

    //TODO
    //If the region isn't defined properly the grapple hook WILL NOT WORK.


    @EventHandler
    public void fishing(org.bukkit.event.player.PlayerFishEvent event) {
        Material mat = event.getPlayer().getItemInHand().getType();
        if (event.getPlayer().getLocation().getWorld().getName().equals(name) && mat == Material.FISHING_ROD
                && event.getState() == org.bukkit.event.player.PlayerFishEvent.State.IN_GROUND)
            event.getPlayer().launchProjectile(org.bukkit.entity.Snowball.class);
    }

    @EventHandler
    public void grapple(org.bukkit.event.entity.ProjectileHitEvent event) {
        org.bukkit.entity.Entity proj = event.getEntity();
        Location hit = proj.getLocation();
        if (hit.getWorld().getName().equals(name) && proj instanceof org.bukkit.entity.Snowball) {
            org.bukkit.entity.Snowball fish = (org.bukkit.entity.Snowball) proj;
            org.bukkit.entity.Entity shooter = fish.getShooter();
            if (shooter instanceof org.bukkit.entity.Player) {
                org.bukkit.entity.Player p = (org.bukkit.entity.Player) shooter;
                Location loc = p.getLocation();
                Material mat = p.getItemInHand().getType();
                if (mat == Material.FISHING_ROD) {
                    p.setFallDistance(0);
                    p.playSound(loc, org.bukkit.Sound.ARROW_HIT, 1, 1);
                    int hitx = hit.getBlockX();
                    int hity = hit.getBlockY();
                    int hitz = hit.getBlockZ();
                    int locx = loc.getBlockX();
                    int locy = loc.getBlockY();
                    int locz = loc.getBlockZ();
                    double co[] = new double[3];

                    if (hitx > locx) co[0] = 1.3;
                    else if (hitx < locx) co[0] = -1.3;
                    else if (hitx == locx) co[0] = 0;

                    if (hity > locy) co[1] = 1.5;
                    else if (hity < locy) co[1] = -0.9;
                    else if (hity == locy) co[1] = 0;

                    if (hitz > locz) co[2] = 1.3;
                    else if (hitz < locz) co[2] = -1.3;
                    else if (hitz == locz) co[2] = 0;

                    p.setVelocity(new org.bukkit.util.Vector(co[0], co[1], co[2]));
                }
            }
        }
    }

}