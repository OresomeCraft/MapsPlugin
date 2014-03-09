package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@MapConfig
public class Courtyard extends BattleMap implements IBattleMap, Listener {

    public Courtyard() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setTDMTime(20);
        disableDrops(new Material[]{Material.FLINT, Material.BOW, Material.STONE_SWORD, Material.BLAZE_ROD, Material.WATCH,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_HELMET, Material.DIAMOND_HELMET,
                Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE,
                Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.STONE_SWORD, Material.WOOD_SWORD, Material.DIAMOND_SWORD, Material.GOLDEN_APPLE,
                Material.POTION, Material.TNT, Material.GOLD_SWORD});
    }

    String name = "courtyard";
    String fullName = "Wolfston Courtyard";
    String creators = "__R3, reggie449, _Arch_Rider, Boomyay and 123Oblivious";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, -25.5, 67, -3.5));
        redSpawns.add(new Location(w, 45.5, 67, -3.5));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -25.5, 67, -3.5));
        FFASpawns.add(new Location(w, 45.5, 67, -3.5));
    }

    public void applyInventory(final BattlePlayer p) {
        p.sendMessage(ChatColor.GOLD + "Interact with one of the signs to change class!");
    }

    ArrayList<String> selecting = new ArrayList<String>();

    @EventHandler
    public void end(BattleEndEvent event) {
        selecting.clear();
    }

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        try {
            Player player = event.getPlayer();
            if (selecting.contains(player.getName())) {
                player.sendMessage(ChatColor.RED + "You are already selecting a class, please wait!");
                return;
            }
            Block block = event.getClickedBlock();

            if (block.getType().equals(Material.SIGN_POST) || block.getType().equals(Material.WALL_SIGN)) {
                BlockState state = block.getState();
                Sign sign = (Sign) state;

                // Sign options
                if (sign.getLine(1).contains("Firearms")) {
                    handKit(player, Group.FIREARMS);
                    return;
                }
                if (sign.getLine(1).contains("Spy")) {
                    if (player.hasPermission("oresomebattles.rank.donator") || player.hasPermission("oresomebattles.rank.donator.plus")) {
                        handKit(player, Group.SPY);
                        return;
                    } else {
                        player.sendMessage(ChatColor.RED + "That's a donator class!");
                    }
                }
                if (sign.getLine(1).contains("Demolition")) {
                    if (player.hasPermission("oresomebattles.rank.donator.plus")) {
                        handKit(player, Group.DEMOLITION);
                        return;
                    } else {
                        player.sendMessage(ChatColor.RED + "That's a donator+ class!");
                    }
                }
                if (sign.getLine(1).contains("Knight")) {
                    handKit(player, Group.KNIGHT);
                    return;
                }
                if (sign.getLine(1).contains("Archer")) {
                    handKit(player, Group.ARCHER);
                    return;
                }
                if (sign.getLine(1).contains("Medic")) {
                    handKit(player, Group.MEDIC);
                    return;
                }
                if (sign.getLine(1).contains("Tank")) {
                    handKit(player, Group.TANK);
                    return;
                }
                if (sign.getLine(1).contains("Scout")) {
                    handKit(player, Group.SCOUT);
                }
            }
        } catch (NullPointerException ex) {
            // Catches null ClickedBlock
        }
    }

    private void handKit(final Player player, final Group group) {
        player.sendMessage(ChatColor.GREEN + "You have chosen " + ChatColor.AQUA + group.toString().toLowerCase() + ChatColor.GREEN + " as your class!");

        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR, 0));
        player.getInventory().setChestplate(new ItemStack(Material.AIR, 0));
        player.getInventory().setLeggings(new ItemStack(Material.AIR, 0));
        player.getInventory().setBoots(new ItemStack(Material.AIR, 0));

        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                switch (group) {
                    case FIREARMS:
                        ItemStack AMMO = new ItemStack(Material.FLINT, 32);
                        ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);

                        ItemMeta blaze_rod = BLAZE_ROD.getItemMeta();
                        blaze_rod.setDisplayName(ChatColor.BLUE + "Retro Wartown Gun");
                        BLAZE_ROD.setItemMeta(blaze_rod);

                        ItemMeta ammo = AMMO.getItemMeta();
                        ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
                        AMMO.setItemMeta(ammo);

                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
                        player.getInventory().setItem(1, BLAZE_ROD);
                        player.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));
                        player.getInventory().setItem(9, AMMO);
                        break;

                    case SPY:
                        ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 5);
                        ItemMeta spywatchMeta = SPY_WATCH.getItemMeta();
                        spywatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");
                        List<String> spyLore = new ArrayList<String>();
                        spyLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
                        spywatchMeta.setLore(spyLore);
                        SPY_WATCH.setItemMeta(spywatchMeta);

                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
                        player.getInventory().setItem(2, SPY_WATCH);
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        break;

                    case KNIGHT:
                        player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        break;

                    case DEMOLITION:
                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.GOLD_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.getInventory().setItem(2, new ItemStack(Material.TNT, 32));
                        break;

                    case TANK:
                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1, (short) -200));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000 * 20, 1));
                        break;

                    case ARCHER:
                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.BOW, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.getInventory().setItem(9, new ItemStack(Material.ARROW, 64));
                        break;

                    case MEDIC:
                        player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.POTION, 48, (short) 16437));
                        player.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));
                        break;

                    case SCOUT:
                        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                        player.getInventory().setChestplate(LEATHER_CHESTPLATE);

                        player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
                        player.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));

                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20000 * 20, 2));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000 * 20, 2));
                        break;
                }
                selecting.remove(player.getName());
            }
        }, 30L);
    }

    @EventHandler
    public void gun(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Action action = event.getAction();
        ItemStack i = player.getItemInHand();
        Inventory inv = player.getInventory();
        Material tool = i.getType();
        final World world = loc.getWorld();

        if (tool.equals(Material.BLAZE_ROD)) {

            if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

                if (inv.contains(Material.FLINT)) {
                    player.launchProjectile(Arrow.class);
                    world.playSound(loc, Sound.COW_WALK, 10, 10);
                    ItemStack AMMO = new ItemStack(Material.FLINT, 1);
                    inv.removeItem(AMMO);

                    ItemMeta ammo = AMMO.getItemMeta();
                    ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
                    AMMO.setItemMeta(ammo);
                    inv.removeItem(AMMO);

                    // Make it remove normal flints, too.
                    player.updateInventory();
                } else {
                    world.playSound(loc, Sound.CLICK, 10, 10);
                }

            }

        }

    }

    @EventHandler
    public void onSpyWatchInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getItemInHand().getType().equals(Material.WATCH)) {

                ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 1);
                ItemMeta spyWatchMeta = SPY_WATCH.getItemMeta();
                spyWatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");

                List<String> spyLore = new ArrayList<String>();
                spyLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
                spyWatchMeta.setLore(spyLore);
                SPY_WATCH.setItemMeta(spyWatchMeta);

                player.getInventory().removeItem(SPY_WATCH);
                player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                player.getInventory().removeItem(new ItemStack(Material.IRON_BOOTS, 1));
                player.getInventory().removeItem(new ItemStack(Material.IRON_LEGGINGS, 1));
                player.getInventory().removeItem(new ItemStack(Material.IRON_HELMET, 1));
                player.getInventory().removeItem(new ItemStack(Material.IRON_CHESTPLATE, 1));
                player.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
                player.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
                player.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
                player.getInventory().addItem(new ItemStack(Material.IRON_HELMET));

                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 15 * 20, 0));
            }
        }
    }

    public enum Group {
        FIREARMS,
        SPY,
        KNIGHT,
        TANK,
        ARCHER,
        MEDIC,
        DEMOLITION,
        SCOUT
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        event.setCancelled(true);
    }


    @EventHandler
    public void PotionsSplash(PotionSplashEvent event) {
        if (!event.getPotion().getWorld().getName().equals(name)) return;
        event.setCancelled(true);
        Iterator iterator = event.getAffectedEntities().iterator();
        while (iterator.hasNext()) {
            LivingEntity target = (LivingEntity) iterator.next();
            if (event.getEntity().getShooter().equals(target)) {
                Player p = (Player) target;
                p.sendMessage(ChatColor.RED + "You cannot heal yourself!");
            } else {
                target.addPotionEffect(event.getPotion().getEffects().iterator().next());
            }
        }
    }

    @EventHandler
    public void placeTnt(BlockPlaceEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (contains(event.getBlock().getLocation(), 39, 69, 59, 124, 33, -60) || contains(event.getBlock().getLocation(), -19, -40, 50, 101, -45, 42))
            return;
        if (event.getBlockPlaced().getType().equals(Material.TNT)) {
            event.getPlayer().getInventory().removeItem(new ItemStack(Material.TNT, 1));
            event.getPlayer().getWorld().spawnEntity(event.getBlockPlaced().getLocation().add(0, 1, 0), EntityType.PRIMED_TNT);
            event.getPlayer().getWorld().playSound(event.getBlockPlaced().getLocation(), Sound.FUSE, 1L, 1L);
        }
    }

    @EventHandler
    public void explode(EntityExplodeEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        event.blockList().clear();
    }

    @EventHandler
    public void arrowAway(org.bukkit.event.entity.ProjectileHitEvent event) {
        org.bukkit.entity.Entity projectile = event.getEntity();
        Location loc = projectile.getLocation();
        if (loc.getWorld().getName().equals(name)) {
            if (projectile instanceof org.bukkit.entity.Arrow) {
                Arrow a = (Arrow) projectile;
                a.remove();
            }
        }

    }
}
