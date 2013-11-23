package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Factory extends BattleMap implements IBattleMap, Listener {

    public Factory() {
        setAllowPhysicalDamage(false);
        super.initiate(this, name, fullName, creators, modes);
    }

    //Yeah, I am for real.
    public Location[] spawns;

    @EventHandler
    public void onload(WorldLoadEvent event) {
        if (event.getWorld().getName().equalsIgnoreCase("factory")) {
            stuff();
            spawns = new Location[]{new Location(Bukkit.getWorld("factory"), 43.5, 87, -39.5),
                    new Location(Bukkit.getWorld("factory"), 37.5, 87, -39.5),
                    new Location(Bukkit.getWorld("factory"), 31.5, 87, -39.5),
                    new Location(Bukkit.getWorld("factory"), 25.5, 87, -39.5),
                    new Location(Bukkit.getWorld("factory"), 21.5, 87, -39.5),
                    new Location(Bukkit.getWorld("factory"), 15.5, 87, -39.5),
                    new Location(Bukkit.getWorld("factory"), 9.5, 87, -39.5),
                    new Location(Bukkit.getWorld("factory"), 3.5, 87, -39.5),
                    new Location(Bukkit.getWorld("factory"), 43.5, 87, -33.5),
                    new Location(Bukkit.getWorld("factory"), 37.5, 87, -33.5),
                    new Location(Bukkit.getWorld("factory"), 31.5, 87, -33.5),
                    new Location(Bukkit.getWorld("factory"), 25.5, 87, -33.5),
                    new Location(Bukkit.getWorld("factory"), 21.5, 87, -33.5),
                    new Location(Bukkit.getWorld("factory"), 15.5, 87, -33.5),
                    new Location(Bukkit.getWorld("factory"), 9.5, 87, -33.5),
                    new Location(Bukkit.getWorld("factory"), 3.5, 87, -33.5),
                    new Location(Bukkit.getWorld("factory"), 43.5, 87, -27.5),
                    new Location(Bukkit.getWorld("factory"), 37.5, 87, -27.5),
                    new Location(Bukkit.getWorld("factory"), 31.5, 87, -27.5),
                    new Location(Bukkit.getWorld("factory"), 25.5, 87, -27.5),
                    new Location(Bukkit.getWorld("factory"), 21.5, 87, -27.5),
                    new Location(Bukkit.getWorld("factory"), 15.5, 87, -27.5),
                    new Location(Bukkit.getWorld("factory"), 9.5, 87, -27.5),
                    new Location(Bukkit.getWorld("factory"), 3.5, 87, -27.5),
                    new Location(Bukkit.getWorld("factory"), 43.5, 87, -19.5),
                    new Location(Bukkit.getWorld("factory"), 37.5, 87, -19.5),
                    new Location(Bukkit.getWorld("factory"), 31.5, 87, -19.5),
                    new Location(Bukkit.getWorld("factory"), 25.5, 87, -19.5),
                    new Location(Bukkit.getWorld("factory"), 21.5, 87, -19.5),
                    new Location(Bukkit.getWorld("factory"), 15.5, 87, -19.5),
                    new Location(Bukkit.getWorld("factory"), 9.5, 87, -19.5),
                    new Location(Bukkit.getWorld("factory"), 3.5, 87, -19.5),
                    new Location(Bukkit.getWorld("factory"), 43.5, 87, -11.5),
                    new Location(Bukkit.getWorld("factory"), 37.5, 87, -11.5),
                    new Location(Bukkit.getWorld("factory"), 31.5, 87, -11.5),
                    new Location(Bukkit.getWorld("factory"), 25.5, 87, -11.5),
                    new Location(Bukkit.getWorld("factory"), 21.5, 87, -11.5),
                    new Location(Bukkit.getWorld("factory"), 15.5, 87, -11.5),
                    new Location(Bukkit.getWorld("factory"), 9.5, 87, -11.5),
                    new Location(Bukkit.getWorld("factory"), 3.5, 87, -11.5),
                    new Location(Bukkit.getWorld("factory"), 43.5, 87, -5.5),
                    new Location(Bukkit.getWorld("factory"), 37.5, 87, -5.5),
                    new Location(Bukkit.getWorld("factory"), 31.5, 87, -5.5),
                    new Location(Bukkit.getWorld("factory"), 25.5, 87, -5.5),
                    new Location(Bukkit.getWorld("factory"), 21.5, 87, -5.5),
                    new Location(Bukkit.getWorld("factory"), 15.5, 87, -5.5),
                    new Location(Bukkit.getWorld("factory"), 9.5, 87, -5.5),
                    new Location(Bukkit.getWorld("factory"), 3.5, 87, -5.5),
                    new Location(Bukkit.getWorld("factory"), 43.5, 87, -1.5),
                    new Location(Bukkit.getWorld("factory"), 37.5, 87, -1.5),
                    new Location(Bukkit.getWorld("factory"), 31.5, 87, -1.5),
                    new Location(Bukkit.getWorld("factory"), 25.5, 87, -1.5),
                    new Location(Bukkit.getWorld("factory"), 21.5, 87, -1.5),
                    new Location(Bukkit.getWorld("factory"), 15.5, 87, -1.5),
                    new Location(Bukkit.getWorld("factory"), 9.5, 87, -1.5),
                    new Location(Bukkit.getWorld("factory"), 3.5, 87, -1.5)};
        }
    }

    // Map details
    String name = "factory";
    String fullName = "FNG Factory";
    String creators = "R3creat3 ";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyTDMSpawns() {
        // Dud
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 24, 65, -20));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 3);

        ItemStack BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);

        p.getInventory().setBoots(BOOTS);

        i.setItem(0, GOLDEN_APPLE);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    // Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;


    public int drop;
    private int wave = 1;
    private int count = 6;

    public void stuff() {
        Bukkit.broadcastMessage(ChatColor.RED + "[Intercom] Wave 1 bomb drop starting!");
        drop = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            public void run() {
                doRound(wave);
                Bukkit.broadcastMessage(ChatColor.RED + "Dropping!");
                count--;
                if (count <= 0) {
                    wave++;
                    Bukkit.broadcastMessage(ChatColor.RED + "[Intercom] Wave " + wave + "!");
                    count = 6;
                }
            }

        }, 200L, 200L);
    }

    @EventHandler
    public void onAddedEffects(EntityDeathEvent e) {
        if (!e.getEntity().getWorld().getName().equals(name)) return;
        if (e.getEntity().getType().equals(EntityType.PIG)) {
            Location loc = e.getEntity().getLocation();
            Entity en = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            Entity en2 = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            Entity en3 = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            Entity en4 = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            en.setVelocity(new Vector(0.1, 0.5, -0.1));
            en2.setVelocity(new Vector(0.1, 0.5, 0.1));
            en3.setVelocity(new Vector(-0.1, 0.5, 0.1));
            en4.setVelocity(new Vector(-0.1, 0.5, -0.1));
        }
        if (e.getEntity().getType().equals(EntityType.COW)) {
            Location loc = e.getEntity().getLocation();
            loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
            Entity en = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            Entity en2 = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            Entity en3 = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            Entity en4 = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
            en.setVelocity(new Vector(0.2, 0.5, -0.2));
            en2.setVelocity(new Vector(0.2, 0.5, 0.2));
            en3.setVelocity(new Vector(-0.2, 0.5, 0.2));
            en4.setVelocity(new Vector(-0.2, 0.5, -0.2));
        }
    }

    private void doRound(int wave) {
        if (count == 1) {
            Bukkit.broadcastMessage(ChatColor.RED + "[Intercom] Special wave!");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getWorld().getName().equals("factory")) {
                    p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
                }
            }
            for (Location loc : spawns) {
                if (Math.random() > 0.25) {

                } else {
                    loc.getWorld().spawnFallingBlock(loc, Material.TNT, (byte) 0);
                }

            }
            return;
        }
        if (wave == 1) {
            for (Location loc : spawns) {
                if (Math.random() > 0.5) {

                } else {
                    Bukkit.getWorld("factory").spawnEntity(loc, EntityType.PRIMED_TNT);
                }
            }
            return;
        }
        if (wave == 2) {
            for (Location loc : spawns) {
                if (Math.random() > 0.4) {

                } else {
                    if (Math.random() >= 0.5) {
                        Bukkit.getWorld("factory").spawnEntity(loc, EntityType.PRIMED_TNT);
                    } else {
                        Bukkit.getWorld("factory").spawnEntity(loc, EntityType.PIG);
                    }
                }
            }
            return;
        }
        if (wave >= 3 && wave <= 5) {
            for (Location loc : spawns) {
                if (Math.random() > 0.3) {

                } else {
                    if (Math.random() >= 0.5) {
                        Bukkit.getWorld("factory").spawnEntity(loc, EntityType.PRIMED_TNT);
                    } else {
                        Bukkit.getWorld("factory").spawnEntity(loc, EntityType.COW);
                    }
                }
            }
        }
        if (wave >= 6) {
            for (Location loc : spawns) {
                if (Math.random() < 0.2) {
                    Bukkit.getWorld("factory").spawnEntity(loc, EntityType.PRIMED_TNT);
                } else {
                    if (Math.random() >= 0.1) {
                        Bukkit.getWorld("factory").spawnEntity(loc, EntityType.COW);
                    } else {
                        Bukkit.getWorld("factory").spawnEntity(loc, EntityType.PIG);
                    }
                }
            }
        }
        return;
    }

    @EventHandler
    public void battleEnd(BattleEndEvent event) {
        Bukkit.getScheduler().cancelTask(drop);
    }

    @EventHandler
    public void onAddedEffects(EntityDamageEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION)
                && e.getEntity().getWorld().getName().equals("factory")
                && !(e.getEntity() instanceof Player)) e.getEntity().remove();
    }

}
