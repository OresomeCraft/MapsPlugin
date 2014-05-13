package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
public class Perro extends BattleMap implements Listener {

    public Perro() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD, Material.FISHING_ROD});
        setTDMTime(15);
    }

    String name = "perro";
    String fullName = "Casa de Perro";
    String[] creators = {"zachoz", "pegabeavercorn", "Dogmode555"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -1397, 130, -2080, 138, 0);
        Location blueSpawn = new Location(w, -1419, 68, -2137, -1, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, -1421, 139, -2080, 179, 0));
        blueSpawns.add(new Location(w, -1400, 98, -2081, 116, 0));
        redSpawns.add(new Location(w, -1442, 114, -2089, -90, 0));
        blueSpawns.add(new Location(w, -1438, 88, -2093, -90, 0));
        redSpawns.add(new Location(w, -1426, 88, -2107, 93, 0));
        blueSpawns.add(new Location(w, -1398, 82, -2080, 155, 0));
        redSpawns.add(new Location(w, -1424, 79, -2079, -118, 0));
        blueSpawns.add(new Location(w, -1420, 95, -2076, -144, 0));
        redSpawns.add(new Location(w, -1410, 83, -2094, -92, 0));
        blueSpawns.add(new Location(w, -1412, 105, -2107, 39, 0));
        redSpawns.add(new Location(w, -1424, 105, -2093, 90, 0));
        blueSpawns.add(new Location(w, -1407, 120, -2081, 123, 0));
        redSpawns.add(new Location(w, -1396, 113, -2103, 50, 0));
        blueSpawns.add(new Location(w, -1426, 104, -2110, 0, 0));
        redSpawns.add(new Location(w, -1426, 104, -2110, 0, 0));
        blueSpawns.add(new Location(w, -1389, 67, -2137, 35, 0));
        redSpawns.add(new Location(w, -1395, 79, -2101, 90, 0));
        blueSpawns.add(new Location(w, -1439, 129, -2093, -87, 0));
        redSpawns.add(new Location(w, -1410, 114, -2081, -178, 0));
        blueSpawns.add(new Location(w, -1416, 98, -2081, -159, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -1397, 130, -2080, 138, 0);
        Location blueSpawn = new Location(w, -1419, 68, -2137, -1, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -1421, 139, -2080, 179, 0));
        FFASpawns.add(new Location(w, -1400, 97, -2081, 116, 0));
        FFASpawns.add(new Location(w, -1442, 114, -2089, -90, 0));
        FFASpawns.add(new Location(w, -1438, 88, -2093, -90, 0));
        FFASpawns.add(new Location(w, -1426, 88, -2107, 93, 0));
        FFASpawns.add(new Location(w, -1398, 82, -2080, 155, 0));
        FFASpawns.add(new Location(w, -1424, 79, -2079, -118, 0));
        FFASpawns.add(new Location(w, -1420, 95, -2076, -144, 0));
        FFASpawns.add(new Location(w, -1410, 83, -2094, -92, 0));
        FFASpawns.add(new Location(w, -1412, 105, -2107, 39, 0));
        FFASpawns.add(new Location(w, -1424, 105, -2093, 90, 0));
        FFASpawns.add(new Location(w, -1407, 120, -2081, 123, 0));
        FFASpawns.add(new Location(w, -1396, 113, -2103, 50, 0));
        FFASpawns.add(new Location(w, -1426, 104, -2110, 0, 0));
        FFASpawns.add(new Location(w, -1426, 104, -2110, 0, 0));
        FFASpawns.add(new Location(w, -1389, 67, -2137, 35, 0));
        FFASpawns.add(new Location(w, -1395, 79, -2101, 90, 0));
        FFASpawns.add(new Location(w, -1439, 129, -2093, -87, 0));
        FFASpawns.add(new Location(w, -1410, 114, -2081, -178, 0));
        FFASpawns.add(new Location(w, -1416, 98, -2081, -159, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
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

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, FISHING_ROD);
        i.setItem(5, EXP);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH_POTION);
        i.setItem(9, ARROWS);
        p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_FALL, 4);


    }

    public int x1 = -1451;
    public int y1 = 63;
    public int z1 = -2145;
    public int x2 = -1383;
    public int y2 = 159;
    public int z2 = -2066;

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
            ProjectileSource shooter = (ProjectileSource) fish.getShooter();
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

    @EventHandler(priority = EventPriority.NORMAL)
    public void glassShot(ProjectileHitEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;

        Entity proj = event.getEntity();
        Location hit = proj.getLocation();
        Block block = hit.getBlock();
        Material material = block.getType();

        if (proj instanceof Arrow) {
            if (material == Material.THIN_GLASS) {
                block.breakNaturally();
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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name) && event.getBlock().getType().getId() != 102)
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name)) event.setCancelled(true);
    }
}
