package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig
public class DesertCastle extends BattleMap implements Listener {

    public DesertCastle() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD, Material.FISHING_ROD});
        setTDMTime(15);
        setAllowBuild(false);
    }

    String name = "desertcastle";
    String fullName = "Desert Castle";
    String[] creators = {"Hourani95"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -37, 66, 30);
        Location blueSpawn = new Location(w, 38, 78, 29);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, -37, 78, 30));
        blueSpawns.add(new Location(w, 38, 66, 29));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -37, 66, 30);
        Location blueSpawn = new Location(w, 38, 78, 29);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -37, 78, 30));
        FFASpawns.add(new Location(w, 38, 66, 29));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);

        ItemMeta fishing_rod = FISHING_ROD.getItemMeta();
        fishing_rod.setDisplayName(ChatColor.BLUE + "Grappling hook");
        FISHING_ROD.setItemMeta(fishing_rod);

        IRON_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 4);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        if (p.getTeamType() == Team.TDM_RED || p.getTeamType() == Team.TDM_BLUE || p.getTeamType() == Team.FFA) {
            i.setItem(2, FISHING_ROD);
        }
        i.setItem(5, EXP);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH_POTION);
        i.setItem(9, ARROWS);

    }

    public int x1 = -80;
    public int y1 = 133;
    public int z1 = -29;
    public int x2 = 64;
    public int y2 = 59;
    public int z2 = 73;

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

    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (getArena().equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);
            }
        }

    }

    public int particles;

    @EventHandler
    public void arrowParticles(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            particles = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    World world = Bukkit.getWorld(name);
                    if (getArena().equals(name)) {
                        for (Entity arrow : world.getEntities()) {
                            if (arrow != null) {
                                if (arrow instanceof Arrow) {
                                    world.playEffect(arrow.getLocation(), Effect.SMOKE, 10);
                                }
                            }
                        }
                    }
                }
            }, 5L, 5L);
        }
    }

    @EventHandler
    public void cancelParticles(WorldUnloadEvent event) {
        Bukkit.getScheduler().cancelTask(particles);
    }

}
