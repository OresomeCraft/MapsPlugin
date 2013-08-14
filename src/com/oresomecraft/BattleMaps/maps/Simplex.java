package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Simplex extends BattleMap implements IBattleMap, Listener {

    /*
    *
    * WARNING:
    *
    * This map is probably very buggy, and will require a bit of beta testing.
    * Please be careful when first using this map.
    *
     */

    public Simplex() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "simplex";
    String fullName = "Simplex";
    String creators = "_Moist and psgs";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -143, 66, -51, -45, 0);
        Location blueSpawn = new Location(w, -1, 67, -49, 45, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -143, 66, -45, -134, 0));
        redSpawns.add(new Location(w, -137, 66, -45, 134, 0));
        redSpawns.add(new Location(w, -137, 66, -51, 45, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -1, 67, -43, 134, 0));
        blueSpawns.add(new Location(w, -7, 67, -43, -134, 0));
        blueSpawns.add(new Location(w, -7, 67, -49, -45, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

        setKoTHMonument(new Location(w, -72, 86, -41));
    }

    public void readyFFASpawns() {
        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, -143, 66, -51, -45, 0));
        FFASpawns.add(new Location(w, -1, 67, -49, 45, 0));
        FFASpawns.add(new Location(w, -143, 66, -45, -134, 0));
        FFASpawns.add(new Location(w, -137, 66, -45, 134, 0));
        FFASpawns.add(new Location(w, -137, 66, -51, 45, 0));
        FFASpawns.add(new Location(w, -1, 67, -43, 134, 0));
        FFASpawns.add(new Location(w, -7, 67, -43, -134, 0));
        FFASpawns.add(new Location(w, -7, 67, -49, -45, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack BREAD = new ItemStack(Material.BREAD, 3);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 32);
            ItemStack DIAMOND_SWORD = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemStack PAINT_GUN = new ItemStack(Material.STICK, 1);
            ItemStack SHEARS = new ItemStack(Material.SHEARS, 1);
            ItemStack GOLDEN_CARROT = new ItemStack(Material.GOLDEN_CARROT, 8);

            ItemStack BLUE_WOOL = new ItemStack(Material.WOOL, 32, (short) 11);
            ItemStack BLUE_DYE = new ItemStack(Material., 64);
            ItemStack RED_WOOL = new ItemStack(Material.WOOL, 32, (short) 14);
            ItemStack RED_DYE = new ItemStack(Material.RED_ROSE, 64);

            i.setItem(0, DIAMOND_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, PAINT_GUN);
            i.setItem(3, SHEARS);
            i.setItem(4, GOLDEN_CARROT);
            i.setItem(5, BREAD);

            i.setItem(8, ARROWS);

            if (BattlePlayer.getBattlePlayer(p).getTeam() == Team.KOTH_RED) {
                i.setItem(10, RED_WOOL);
                i.setItem(11, RED_DYE);
            } else {
                if (BattlePlayer.getBattlePlayer(p).getTeam() == Team.KOTH_BLUE) {
                    i.setItem(10, BLUE_WOOL);
                    i.setItem(11, BLUE_DYE);
                }
            }

            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5 * 20, 2));

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -187;
    public int y1 = 159;
    public int z1 = 22;

    //Bottom right corner.
    public int x2 = 55;
    public int y2 = 35;
    public int z2 = -130;

    @EventHandler
    public void onFireBow(org.bukkit.event.entity.EntityShootBowEvent event) {
        if (com.oresomecraft.OresomeBattles.BattleHandler.activeArena.equals(name)) {
            if (event.getEntityType() == org.bukkit.entity.EntityType.PLAYER) {
                Player player = (Player) event.getEntity();
                if (player.getInventory().contains(Material.ARROW)) {
                    event.setCancelled(true);
                    player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
                    player.launchProjectile(org.bukkit.entity.EnderPearl.class).setVelocity(event.getProjectile().getVelocity());
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onProjectileLand(ProjectileHitEvent event) {
        if (com.oresomecraft.OresomeBattles.BattleHandler.activearena.equals(name)) {
            if (event.getEntityType().equals(org.bukkit.entity.EnderPearl.class)) {
                World w = Bukkit.getServer().getWorld(name);
                Projectile proj = event.getEntity();
                Location projloc = proj.getLocation();

                if (proj.getShooter().getType().equals(EntityType.PLAYER)) {
                    Player shooter = (Player) proj.getShooter();

                    int x = projloc.getBlockX();
                    int y = projloc.getBlockY();
                    int z = projloc.getBlockZ();

                    int x2 = x += 3;
                    int y2 = y += 3;
                    int z2 = z += 3;

                    Location loc = new Location(w, x, y, z);

                    while (x <= x2 && y <= y2 && z <= z2) {

                        blueWool(loc, w, shooter);
                        redWool(loc, w, shooter);

                        x++;

                        blueWool(loc, w, shooter);
                        redWool(loc, w, shooter);

                        y++;

                        blueWool(loc, w, shooter);
                        redWool(loc, w, shooter);

                        z++;
                    }
                }
            }
        }
    }

    public void blueWool(Location loc, World w, Player shooter) {

        if (BattlePlayer.getBattlePlayer(shooter).getTeam() == Team.KOTH_RED) {
            if (loc.getBlock().getType().equals(Material.WOOL)) {
                loc.getBlock().setTypeIdAndData(Material.WOOL.getId(), DyeColor.RED.getDyeData(), false);
                w.playEffect(loc, Effect.STEP_SOUND, 152);
            }
        }
    }

    public void redWool(Location loc, World w, Player shooter) {
        if (BattlePlayer.getBattlePlayer(shooter).getTeam() == Team.KOTH_BLUE) {
            if (loc.getBlock().getType().equals(Material.WOOL)) {
                loc.getBlock().setTypeIdAndData(Material.WOOL.getId(), DyeColor.RED.getDyeData(), false);
                w.playEffect(loc, Effect.STEP_SOUND, 22);
            }
        }
    }
}


