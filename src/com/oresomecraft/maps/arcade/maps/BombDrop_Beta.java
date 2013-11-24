package com.oresomecraft.maps.arcade.maps;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.*;
import com.oresomecraft.maps.arcade.ArcadeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@MapConfig
public class BombDrop_Beta extends ArcadeMap implements Listener {

    public BombDrop_Beta() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
        lockTime("day");
    }

    // Map details
    String name = "bombdrop_beta";
    String fullName = "BombDrop (Beta)";
    String creators = "ViolentShadow and ScruffyRules";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 225, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);
    }

    @EventHandler
    public void onWorld(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            loc1 = new Location(event.getWorld(), -26, 256, -26);
            loc2 = new Location(event.getWorld(), 26, 256, 26);

            bombs();
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        loc1 = null;
        loc2 = null;
    }

    Location loc1;
    Location loc2;
    static List<Integer> bombs = new ArrayList<Integer>();

    public void bombs() {
        new BukkitRunnable() {
            public void run() {

                if (!getArena().equals(name)) {
                    bombs.clear();
                    this.cancel();
                    return;
                }

                Random rdom = new Random();
                Location loc = getBlocks().get(rdom.nextInt(getBlocks().size())).getLocation();

                bombs.add(1);

                List<Class> type = new ArrayList<Class>();

                type.add(Creeper.class);
                if (bombs.size() > 20) type.add(Cow.class);
                if (bombs.size() > 30) type.add(Pig.class);
                if (bombs.size() > 40) type.add(Sheep.class);
                if (bombs.size() > 50) type.add(Wolf.class);
                if (bombs.size() > 55) {
                    type.add(Spider.class);
                    type.add(Skeleton.class);
                }
                if (bombs.size() > 60) {
                    type.add(Silverfish.class);
                    type.add(CaveSpider.class);
                }
                if (bombs.size() > 65) {

                    type.add(Villager.class);
                }
                if (bombs.size() > 70) {
                    type.add(MushroomCow.class);
                    type.add(Slime.class);
                }
                if (bombs.size() > 80) type.add(MagmaCube.class);

                Class T = type.get(rdom.nextInt(type.size()));

                loc.getWorld().spawn(loc, T);
            }
        }.runTaskTimer(MapsPlugin.getInstance(), 30, 30);
    }

    @EventHandler
    public void onDrop(EntityDamageEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Player) return;

        final Entity e = event.getEntity();
        EntityType et = e.getType();
        final Location loc = e.getLocation();
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (et == EntityType.CREEPER) {
                Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                    public void run() {
                        loc.getWorld().createExplosion(loc, (float) 4);
                    }
                }, 30);
            } else if (et == EntityType.COW) {
                Random rdom = new Random();

                for (int i = 0; i <= 3; i++) {
                    loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT).setVelocity(new Vector(rdom.nextDouble() - 0.5, 1.5, rdom.nextDouble() - 0.5));
                }

            } else if (et == EntityType.PIG) {
                Random rdom = new Random();
                loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE).setVelocity(new Vector(rdom.nextDouble(), 3, rdom.nextDouble()));
            } else if (et == EntityType.PIG_ZOMBIE) {
                Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                    public void run() {
                        loc.getWorld().createExplosion(loc, (float) 6);
                    }
                }, 30);
            } else if (et == EntityType.SHEEP) {
                Random rdom = new Random();

                for (int i = 0; i <= 10; i++) {
                    loc.getWorld().spawnFallingBlock(loc, Material.WOOL, (byte) 0).setVelocity(new Vector(rdom.nextDouble() - 0.5, 2, rdom.nextDouble() - 0.5));
                }

            } else if (et == EntityType.SQUID) {
                Random rdom = new Random();
                loc.getWorld().spawnFallingBlock(loc, Material.WATER, (byte) 0).setVelocity(new Vector(rdom.nextDouble() - 0.5, 1, rdom.nextDouble() - 0.5));
            } else if (et == EntityType.WOLF) {
                Random rdom = new Random();
                List<Entity> l = e.getNearbyEntities(3, 3, 3);

                for (Entity ee : l) {
                    if (ee instanceof Player) {
                        Player p = (Player) ee;
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 1));
                    }
                }
            } else if (et == EntityType.OCELOT) {
                Random rdom = new Random();
                List<Entity> l = e.getNearbyEntities(3, 3, 3);
                for (Entity ee : l) {
                    if (ee instanceof Player) {
                        Player p = (Player) ee;
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 1));
                    }
                }
            } else if (et == EntityType.SPIDER) {
                Random rdom = new Random();

                for (int i = 0; i <= 4; i++) {
                    loc.getWorld().spawnFallingBlock(loc, Material.WEB, (byte) 0).setVelocity(new Vector(rdom.nextDouble() - 0.5, 1, rdom.nextDouble() - 0.5));
                }
            } else if (et == EntityType.HORSE) {
                Random rdom = new Random();
                List<Entity> l = e.getNearbyEntities(10, 10, 10);
                for (Entity ee : l) {
                    if (ee instanceof Player) {
                        Player p = (Player) ee;
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 2));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 2));
                    }
                }
            } else if (et == EntityType.SKELETON) {
                Random rdom = new Random();

                for (int i = 0; i <= 4; i++) {
                    loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE).setVelocity(new Vector(rdom.nextDouble() - 0.5, 3, rdom.nextDouble() - 0.5));
                }

            } else if (et == EntityType.SILVERFISH) {
                Random rdom = new Random();
                loc.getWorld().spawnFallingBlock(loc, Material.MONSTER_EGGS, (byte) 0).setVelocity(new Vector(rdom.nextDouble() - 0.5, 3, rdom.nextDouble() - 0.5));
                Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                    public void run() {
                        loc.getWorld().createExplosion(loc, (float) 2);
                    }
                }, 30);
            } else if (et == EntityType.CAVE_SPIDER) {
                Random rdom = new Random();
                for (int i = 0; i <= 3; i++) {
                    loc.getWorld().spawnEntity(loc, EntityType.SPIDER).setVelocity(new Vector(rdom.nextDouble() - 0.5, 3, rdom.nextDouble() - 0.5));
                }
            } else if (et == EntityType.WITHER_SKULL) {
                Random rdom = new Random();


            }
            if (et == EntityType.ZOMBIE) {
                Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                    public void run() {
                        loc.getWorld().strikeLightning(loc);
                    }
                }, 30);
            } else if (et == EntityType.IRON_GOLEM) {
                Random rdom = new Random();
                for (int i = 0; i <= 3; i++) {
                    loc.getWorld().spawnEntity(loc, EntityType.VILLAGER).setVelocity(new Vector(rdom.nextDouble() - 0.5, 3, rdom.nextDouble() - 0.5));
                }
            } else if (et == EntityType.VILLAGER) {
                Random rdom = new Random();

                for (int i = 0; i <= 3; i++) {
                    loc.getWorld().spawnFallingBlock(loc, Material.ANVIL, (byte) 0).setVelocity(new Vector(rdom.nextDouble() - 0.5, 1.5, rdom.nextDouble() - 0.5));
                }
            } else if (et == EntityType.MUSHROOM_COW) {
                Random rdom = new Random();
                for (int i = 0; i <= 5; i++) {
                    loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT).setVelocity(new Vector(rdom.nextDouble() - 0.5, 1.5, rdom.nextDouble() - 0.5));
                }
            }
            if (et == EntityType.SLIME) {
                Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                    public void run() {
                        loc.getWorld().createExplosion(loc, (float) 6);
                    }
                }, 30);
            } else if (et == EntityType.MAGMA_CUBE) {
                Random rdom = new Random();
                loc.getWorld().spawnFallingBlock(loc, Material.LAVA, (byte) 0).setVelocity(new Vector(rdom.nextDouble() - 0.5, 1, rdom.nextDouble() - 0.5));
            }
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) event.getBlock().getDrops().clear();
    }

    public List<Block> getBlocks() {
        List<Block> blocks = new ArrayList<Block>();
        if (loc1 == null || loc2 == null) return blocks;
        for (int x = (int) Math.min(loc1.getX(), loc2.getX()); x <= (int) Math.max(loc1.getX(), loc2.getX()); x++) {
            for (int y = (int) Math.min(loc1.getY(), loc2.getY()); y <= (int) Math.max(loc1.getY(), loc2.getY()); y++) {
                for (int z = (int) Math.min(loc1.getZ(), loc2.getZ()); z <= (int) Math.max(loc1.getZ(), loc2.getZ()); z++) {
                    if (loc1.getWorld().getBlockAt(x, y, z).getType() == Material.AIR) {
                        blocks.add(loc1.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
        }
        return blocks;
    }

}