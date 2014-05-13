package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig
public class SnowyRidge extends BattleMap implements Listener {

    public SnowyRidge() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.FISHING_ROD, Material.BOW, Material.IRON_SWORD, Material.LEATHER_HELMET, Material.SNOW_BALL});
        setFireSpread(false);
    }

    String name = "snowyridge";
    String fullName = "Snowy Ridge";
    String[] creators = {"meganlovesmusic", "ninsai", "SuperDuckFace"};
    Gamemode[] modes = {Gamemode.FFA, Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -65, 62, -3, -88, 0));
        blueSpawns.add(new Location(w, 40, 62, 35, 137, 0));
        Bukkit.getWorld(name).setTime(12000);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -42, 70, -36, -90, 0));
        FFASpawns.add(new Location(w, -42, 53, -36, -90, 0));
        FFASpawns.add(new Location(w, -25, 43, -33, -28, 0));
        FFASpawns.add(new Location(w, -34, 40, -11, -90, 0));
        FFASpawns.add(new Location(w, 0, 35, -35, 46, 0));
        FFASpawns.add(new Location(w, -17, 59, -54, -0, 0));
        FFASpawns.add(new Location(w, -12, 43, 13, 180, 0));
        FFASpawns.add(new Location(w, 24, 35, 3, 107, 0));
        FFASpawns.add(new Location(w, 23, 39, -14, 62, 0));
        FFASpawns.add(new Location(w, 21, 54, -48, 38, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);
        ItemStack SNOWBALLS = new ItemStack(Material.SNOW_BALL, 16);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        LEATHER_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 3);
        InvUtils.nameItem(FISHING_ROD, ChatColor.GOLD + "Grappling Hook");
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, FISHING_ROD);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH_POTION);
        i.setItem(5, EXP);
        i.setItem(6, SNOWBALLS);
        i.setItem(9, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 106;
    public int y1 = 93;
    public int z1 = 98;

    //Bottom right corner.
    public int x2 = -113;
    public int y2 = 25;
    public int z2 = -103;

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
                    Snowball snowball = player.launchProjectile(Snowball.class);
                    snowball.setTicksLived(99999 * 20);
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
            if (proj.getTicksLived() < (99999 * 20))
                return; //So normal snowballs don't clash with the grapple snowballs. Grapple snowballs use TicksLived instead of metadata lol
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

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (player.getLocation().getWorld().getName().equals(name)) {

            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();

                if (block.getType() == Material.PISTON_BASE) {
                    if (block.getLocation().getBlockY() == 54) {
                        player.teleport(new Location(w, -42, 70, -36, -90, 0)); // To Top
                    } else if (block.getLocation().getBlockY() == 71) {
                        player.teleport(new Location(w, -42, 53, -36, -90, 0)); // To Bottom
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getPlayer().getLocation().getWorld().getName().equals(name)) {
            if (event.getTo().equals(new Location(w, -17, 59, -54, -0, 0))) {
                event.getPlayer().sendMessage(ChatColor.BOLD + "CONGRATS! You spawned in Zachoz's house!");
            }
        }
    }
}
