package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.*;

import com.oresomecraft.OresomeBattles.api.*;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig
public class SkyFights extends BattleMap implements Listener {

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

    @EventHandler(priority = EventPriority.NORMAL)
    public void fishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Material material = itemStack.getType();
        Location location = player.getLocation();
        Location bobber = event.getHook().getLocation();

        if (location.getWorld().getName().equals(name)) {

            if (material == Material.FISHING_ROD) {

                if (event.getHook().getVelocity().getY() < 0.02 && isLocationNearBlock(bobber)) {
                    player.launchProjectile(Snowball.class);
                }
            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void grapple(ProjectileHitEvent event) {
        Entity proj = event.getEntity();
        Location hit = proj.getLocation();

        if (!event.getEntity().getWorld().getName().equals(name)) return;

        if (proj instanceof Snowball) {
            Snowball fish = (Snowball) proj;
            ProjectileSource shooter = fish.getShooter();

            if (shooter instanceof Player) {
                Player player = (Player) shooter;
                Location location = player.getLocation();
                ItemStack itemStack = player.getItemInHand();
                Material material = itemStack.getType();

                if (material == Material.FISHING_ROD) {

                    player.setFallDistance(0);
                    player.playSound(location, Sound.ARROW_HIT, 1, 1);

                    int hitx = hit.getBlockX();
                    int hity = hit.getBlockY();
                    int hitz = hit.getBlockZ();
                    int locx = location.getBlockX();
                    int locy = location.getBlockY();
                    int locz = location.getBlockZ();
                    double co[] = new double[3];

                    if (hitx > locx) {
                        co[0] = 1.2;
                    } else if (hitx < locx) {
                        co[0] = -1.2;
                    } else if (hitx == locx) {
                        co[0] = 0;
                    }

                    if (hity > locy) {
                        co[1] = 1.4;
                    } else if (hity < locy) {
                        co[1] = -0.8;
                    } else if (hity == locy) {
                        co[1] = 0;
                    }

                    if (hitz > locz) {
                        co[2] = 1.2;
                    } else if (hitz < locz) {
                        co[2] = -1.2;
                    } else if (hitz == locz) {
                        co[2] = 0;
                    }

                    player.setVelocity(new Vector(co[0], co[1] / 1.25, co[2]));

                }
            }
        }
    }
}
