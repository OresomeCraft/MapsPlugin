package com.oresomecraft.BattleMaps.maps;

import java.util.HashMap;
import java.util.Map;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.Utility;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Wartown extends BattleMap implements IBattleMap, Listener {

    public Wartown() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "wartown";
    String fullName = "Wartown";
    String creators = "reub_youtube";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 175, 64, -268, 0, 0);
        Location blueSpawn = new Location(w, 175, 67, -146, 179, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 176, 74, -253, -50, 0));

        blueSpawns.add(blueSpawn);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 175, 64, -272);
        Location blueSpawn = new Location(w, 175, 67, -146, 179, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 176, 74, -253, -50, 0));
        FFASpawns.add(new Location(w, 162, 65, -251, -39, 0));
        FFASpawns.add(new Location(w, 168, 65, -236, 104, 0));
        FFASpawns.add(new Location(w, 162, 65, -223, 170, 0));
        FFASpawns.add(new Location(w, 152, 65, -237, 89, 0));
        FFASpawns.add(new Location(w, 134, 65, -237, -114, 0));
        FFASpawns.add(new Location(w, 148, 65, -253, -253, 0));
        FFASpawns.add(new Location(w, 143, 65, -214, 0, 0));
        FFASpawns.add(new Location(w, 158, 69, -207, 147, 0));
        FFASpawns.add(new Location(w, 142, 65, -198, 92, 0));
        FFASpawns.add(new Location(w, 131, 64, -148, -150, 0));
        FFASpawns.add(new Location(w, 220, 64, -150, 150, 0));
        FFASpawns.add(new Location(w, 217, 69, -269, 46, 0));
        FFASpawns.add(new Location(w, 132, 65, -272, -43, 0));
        FFASpawns.add(new Location(w, 177, 65, -223, 171, 0));
        FFASpawns.add(new Location(w, 183, 64, -216, -89, 0));
        FFASpawns.add(new Location(w, 159, 60, -203, 180, 0));
        FFASpawns.add(new Location(w, 183, 60, -230, -46, 0));
        FFASpawns.add(new Location(w, 200, 60, -251, 47, 0));
        FFASpawns.add(new Location(w, 174, 69, -234, 41, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack AMMO = new ItemStack(Material.FLINT, 64);
            ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);
            ItemStack EGG_WARTOWN = new ItemStack(Material.EGG, 1);
            ItemStack LEATHER = new ItemStack(Material.LEATHER, 1);

            ItemMeta blaze_rod = BLAZE_ROD.getItemMeta();
            blaze_rod.setDisplayName(ChatColor.BLUE + "Gun");
            BLAZE_ROD.setItemMeta(blaze_rod);

            ItemMeta ammo = AMMO.getItemMeta();
            ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
            AMMO.setItemMeta(ammo);

            ItemMeta egg_wartown = EGG_WARTOWN.getItemMeta();
            egg_wartown.setDisplayName(ChatColor.BLUE + "Frag grenade");
            EGG_WARTOWN.setItemMeta(egg_wartown);

            ItemMeta leather = LEATHER.getItemMeta();
            leather.setDisplayName(ChatColor.BLUE + "C4");
            LEATHER.setItemMeta(leather);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BLAZE_ROD);
            i.setItem(2, EGG_WARTOWN);
            i.setItem(3, LEATHER);
            i.setItem(4, STEAK);
            i.setItem(5, HEALTH_POTION);
            i.setItem(6, AMMO);

            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 8 * 20, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 8 * 20, 1));

        }
    }

    public int x1 = 128;
    public int y1 = 54;
    public int z1 = -278;
    public int x2 = 225;
    public int y2 = 91;
    public int z2 = -145;

    public Map<Player, Block> placer = new HashMap<Player, Block>();
    public Map<Block, Player> placerB = new HashMap<Block, Player>();

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void c4(PlayerInteractEvent event) {

        Player p = event.getPlayer();
        Location loc = p.getLocation();
        Action a = event.getAction();
        ItemStack i = p.getItemInHand();
        Material tool = i.getType();
        BlockFace face = event.getBlockFace();
        Inventory inv = p.getInventory();
        String name = p.getName();

        if (!battles.spectator.contains(name)) {

            if (contains(loc, x1, x2, y1, y2, z1, z2)) {
                if (tool == Material.LEATHER) {

                    if (a == Action.RIGHT_CLICK_BLOCK) {

                        Block b = event.getClickedBlock();
                        Block place = b.getRelative(face);

                        if (!placerB.containsValue(p)) {

                            place.setType(Material.BRICK);
                            p.playSound(loc, Sound.STEP_GRAVEL, 1, 1);

                            placer.put(p, place);
                            placerB.put(place, p);

                        }
                    }

                }

                if (a == Action.RIGHT_CLICK_AIR) {

                    if (placer.containsKey(p) && placerB.containsValue(p)) {

                        Block charge = placer.get(p);
                        Location chargeloc = charge.getLocation();
                        World w = charge.getWorld();
                        p.playSound(loc, Sound.CLICK, 1, 1);
                        w.createExplosion(chargeloc, 4);

                        ItemStack LEATHER = new ItemStack(Material.LEATHER, 1);
                        ItemMeta leather = LEATHER.getItemMeta();
                        leather.setDisplayName(ChatColor.BLUE + "C4");
                        LEATHER.setItemMeta(leather);

                        inv.removeItem(LEATHER);
                        p.updateInventory();
                        placer.remove(p);
                        placerB.remove(charge);

                    }
                }
            }

        }

    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void c4premature(ProjectileHitEvent event) {
        Projectile p = event.getEntity();
        World w = p.getWorld();
        World world = Bukkit.getWorld(name);
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (p instanceof Arrow) {

                world.playEffect(p.getLocation(), Effect.STEP_SOUND, 10);
            }
        }
        Location loc = p.getLocation();
        Block b = loc.getBlock();
        Block bD = b.getRelative(BlockFace.DOWN);
        Block bU = b.getRelative(BlockFace.UP);
        Block bE = b.getRelative(BlockFace.EAST);
        Block bW = b.getRelative(BlockFace.WEST);
        Block bN = b.getRelative(BlockFace.NORTH);
        Block bS = b.getRelative(BlockFace.SOUTH);

        ItemStack LEATHER = new ItemStack(Material.LEATHER, 1);
        ItemMeta leather = LEATHER.getItemMeta();
        leather.setDisplayName(ChatColor.BLUE + "C4");
        LEATHER.setItemMeta(leather);

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (placer.containsValue(b)) {
                Player own = placerB.get(b);
                Inventory i = own.getInventory();
                i.removeItem(LEATHER);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(b);

            } else if (placer.containsValue(bD)) {
                Player own = placerB.get(bD);
                Inventory i = own.getInventory();
                i.removeItem(LEATHER);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bD);

            } else if (placer.containsValue(bU)) {
                Player own = placerB.get(bU);
                Inventory i = own.getInventory();
                i.removeItem(LEATHER);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bU);

            } else if (placer.containsValue(bE)) {
                Player own = placerB.get(bE);
                Inventory i = own.getInventory();
                i.removeItem(LEATHER);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bE);

            } else if (placer.containsValue(bW)) {
                Player own = placerB.get(bW);
                // Inventory i = own.getInventory();
                // i.removeItem(LEATHER);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bW);

            } else if (placer.containsValue(bN)) {
                Player own = placerB.get(bN);
                Inventory i = own.getInventory();
                i.removeItem(LEATHER);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bN);

            } else if (placer.containsValue(bS)) {
                Player own = placerB.get(bS);
                // Inventory i = own.getInventory();
                // i.removeItem(LEATHER);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bS);

            }
        }

    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void gun(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        Action a = event.getAction();
        ItemStack i = p.getItemInHand();
        Inventory inv = p.getInventory();
        Material tool = i.getType();
        final World world = loc.getWorld();
        String name = p.getName();

        if (battles.spectator.contains(name) || p.getWorld().equals("world")) {
            //disable using the gun at the spawn
            return;
        } else {
            if (contains(loc, x1, x2, y1, y2, z1, z2)) {

                if (tool == Material.BLAZE_ROD) {

                    if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {

                        if (inv.contains(Material.FLINT)) {

                            p.launchProjectile(Arrow.class);
                            world.playSound(loc, Sound.COW_WALK, 10, 10);
                            ItemStack AMMO = new ItemStack(Material.FLINT, 1);
                            //Make it remove normal flints, too.
                            p.updateInventory();

                        }

                    } else {
                        world.playSound(loc, Sound.CLICK, 10, 10);
                    }

                }

            }

        }
    }

    public int particles;

    public void arrowParticles() {

        Bukkit.getServer().getScheduler().cancelTask(particles);
        particles = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            public void run() {
                World world = Bukkit.getWorld(name);
                if (Utility.getArena().equals(name)) {
                    if (!(world.getEntities() == null)) {
                        for (Entity arrow : world.getEntities()) {
                            if (arrow instanceof Arrow) {

                                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);
                            }
                        }
                    }
                }
            }

        }, 5L, 5L);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void moltov(ProjectileHitEvent event) {
        Entity p = event.getEntity();
        Location loc = p.getLocation();
        Block b = loc.getBlock();
        Block bW = b.getRelative(BlockFace.WEST);
        Block bE = b.getRelative(BlockFace.EAST);
        Block bN = b.getRelative(BlockFace.NORTH);
        Block bS = b.getRelative(BlockFace.SOUTH);
        Block bNE = b.getRelative(BlockFace.NORTH_EAST);
        Block bNW = b.getRelative(BlockFace.NORTH_WEST);
        Block bSE = b.getRelative(BlockFace.SOUTH_EAST);
        Block bSW = b.getRelative(BlockFace.SOUTH_WEST);

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (p instanceof Egg) {

                if (b.getType() == Material.AIR)
                    b.setType(Material.FIRE);
                if (bN.getType() == Material.AIR)
                    bN.setType(Material.FIRE);
                if (bS.getType() == Material.AIR)
                    bS.setType(Material.FIRE);
                if (bW.getType() == Material.AIR)
                    bE.setType(Material.FIRE);
                if (bNW.getType() == Material.AIR)
                    bW.setType(Material.FIRE);
                if (bNW.getType() == Material.AIR)
                    bNW.setType(Material.FIRE);
                if (bNE.getType() == Material.AIR)
                    bNE.setType(Material.FIRE);
                if (bSW.getType() == Material.AIR)
                    bSW.setType(Material.FIRE);
                if (bSE.getType() == Material.AIR)
                    bSE.setType(Material.FIRE);
            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void bulletAway(ProjectileHitEvent event) {
        Entity p = event.getEntity();
        Location loc = p.getLocation();
        Block b = loc.getBlock();
        Material mat = b.getType();

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (p instanceof Arrow) {
                Arrow a = (Arrow) p;
                a.remove();

                if (mat == Material.THIN_GLASS) {
                    b.breakNaturally();
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void moltovHit(EntityDamageByEntityEvent event) {

        Entity e = event.getEntity();
        Entity proj = event.getDamager();
        Location loc = e.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            if (proj instanceof Egg) {

                e.setFireTicks(500);

            }

        }

    }

}
