package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class Wartown extends BattleMap implements Listener {

    public Wartown() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD, Material.BLAZE_ROD});
    }

    String name = "wartown";
    String fullName = "Wartown";
    String[] creators = {"reub_youtube"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 175, 64, -268, 0, 0);
        Location blueSpawn = new Location(w, 175, 67, -146, 179, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
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
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack AMMO = new ItemStack(Material.FLINT, 64);
        ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);
        ItemStack EGG_WARTOWN = new ItemStack(Material.EGG, 1);
        ItemStack GUN_POWDER = new ItemStack(Material.SULPHUR, 1);

        ItemMeta blaze_rod = BLAZE_ROD.getItemMeta();
        blaze_rod.setDisplayName(ChatColor.BLUE + "Gun");
        BLAZE_ROD.setItemMeta(blaze_rod);

        ItemMeta ammo = AMMO.getItemMeta();
        ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
        AMMO.setItemMeta(ammo);

        ItemMeta egg_wartown = EGG_WARTOWN.getItemMeta();
        egg_wartown.setDisplayName(ChatColor.BLUE + "Frag grenade");
        EGG_WARTOWN.setItemMeta(egg_wartown);

        ItemMeta gun_powder = GUN_POWDER.getItemMeta();
        gun_powder.setDisplayName(ChatColor.BLUE + "C4");

        List<String> gunPowderLore = new ArrayList<String>();
        gunPowderLore.add(ChatColor.BLUE + "Drop this to activate C4!");
        gun_powder.setLore(gunPowderLore);
        GUN_POWDER.setItemMeta(gun_powder);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BLAZE_ROD);
        i.setItem(2, HEALTH_POTION);
        i.setItem(3, STEAK);
        i.setItem(4, GUN_POWDER);
        i.setItem(6, EGG_WARTOWN);
        i.setItem(9, AMMO);
    }

    public int x1 = 128;
    public int y1 = 54;
    public int z1 = -278;
    public int x2 = 225;
    public int y2 = 91;
    public int z2 = -145;

    @EventHandler
    public void c4(PlayerDropItemEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            if (event.getItemDrop().getItemStack().getType() == Material.SULPHUR) {
                if (!contains(event.getItemDrop().getLocation(), 186, 162, 63, 86, -257, -278) && !contains(event.getItemDrop().getLocation(), 154, 197, 77, 63, -185, -143)) {
                    event.getPlayer().getWorld().playSound(event.getItemDrop().getLocation(), Sound.FUSE, 1L, 1L);
                    TNTPrimed tnt = event.getPlayer().getWorld().spawn(event.getItemDrop().getLocation().subtract(0, 1, 0), TNTPrimed.class);
                    tnt.setFuseTicks(3 * 20);
                    Vector velocity;
                    velocity = tnt.getLocation().getDirection().multiply(1.4);
                    tnt.setVelocity(velocity);
                    event.getItemDrop().remove();
                } else {
                    event.getPlayer().sendMessage(ChatColor.RED + "You can't detonate C4 in a spawn!");
                    event.setCancelled(true);
                    event.getPlayer().updateInventory();
                }
            }
        }
    }

    @EventHandler
    public void gun(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            Player player = event.getPlayer();
            Location location = player.getLocation();
            Action action = event.getAction();
            ItemStack itemStack = player.getItemInHand();
            Inventory inventory = player.getInventory();
            Material tool = itemStack.getType();
            final World world = location.getWorld();

            if (tool == Material.BLAZE_ROD) {

                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {

                    if (inventory.contains(Material.FLINT)) {
                        player.launchProjectile(Arrow.class);
                        world.playSound(location, Sound.COW_WALK, 10, 10);
                        ItemStack AMMO = new ItemStack(Material.FLINT, 1);
                        inventory.removeItem(AMMO);

                        ItemMeta ammo = AMMO.getItemMeta();
                        ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
                        AMMO.setItemMeta(ammo);
                        inventory.removeItem(AMMO);

                        // Make it remove normal flints, too.
                        player.updateInventory();
                    } else {
                        world.playSound(location, Sound.CLICK, 10, 10);
                    }

                }

            }

        }
    }

    @EventHandler
    public void hit(ProjectileHitEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        for (Entity entity : event.getEntity().getNearbyEntities(3, 3, 3)) {
            if (entity instanceof TNTPrimed) {
                TNTPrimed tnt = (TNTPrimed) entity;
                tnt.setFuseTicks(1);
            }
        }
    }

    public int particles;

    public void arrowParticles() {

        Bukkit.getServer().getScheduler().cancelTask(particles);
        particles = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            public void run() {
                World world = Bukkit.getWorld(name);
                if (getArena().equals(name)) {
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
        Entity player = event.getEntity();
        Location location = player.getLocation();
        Block block = location.getBlock();
        Material material = block.getType();

        if (contains(location, x1, x2, y1, y2, z1, z2)) {

            if (player instanceof Arrow) {
                Arrow arrow = (Arrow) player;
                arrow.remove();

                if (material == Material.THIN_GLASS) {
                    block.breakNaturally();
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void molotovHit(EntityDamageByEntityEvent event) {

        Entity entity = event.getEntity();
        Entity proj = event.getDamager();
        Location location = entity.getLocation();

        if (location.getWorld().getName().equals(name)) {

            if (proj instanceof Egg) {

                entity.setFireTicks(500);

            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (getMode() != Gamemode.FFA) {
            if (contains(event.getBlockPlaced().getLocation(), 160, 190, 63, 80, -172, -144) || contains(event.getBlockPlaced().getLocation(), 165, 187, 63, 89, -259, -278)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (getMode() != Gamemode.FFA) {
            if (contains(event.getBlock().getLocation(), 160, 190, 63, 80, -172, -144) || contains(event.getBlock().getLocation(), 165, 187, 63, 89, -259, -278)) {
                event.setCancelled(true);
            }
        }
    }
}
