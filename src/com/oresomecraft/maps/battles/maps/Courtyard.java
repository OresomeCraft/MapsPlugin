package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class Courtyard extends BattleMap implements IBattleMap, Listener {

    public Courtyard() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setTDMTime(20);
        disableDrops(new Material[]{Material.FLINT, Material.BOW, Material.STONE_SWORD, Material.BLAZE_ROD, Material.WATCH, Material.COOKED_BEEF,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_HELMET, Material.DIAMOND_HELMET,
                Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE,
                Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.STONE_SWORD, Material.WOOD_SWORD, Material.DIAMOND_SWORD, Material.GOLDEN_APPLE});
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
        // Not needed due to custom classes
    }

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        try {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();

            if (block.getType().equals(Material.SIGN_POST) || block.getType().equals(Material.WALL_SIGN)) {
                BlockState state = block.getState();
                Sign sign = (Sign) state;

                // Sign options
                if (sign.getLine(1).contains("Firearms")) {
                    handKit(player, Group.FIREARMS);
                    return;
                }
                if (sign.getLine(1).contains("Spy") && player.hasPermission("oresomebattles.rank.donator")) {
                    handKit(player, Group.SPY);
                    return;
                } else {
                    if (!player.hasPermission("battles.rank.donator")) {
                        player.sendMessage(ChatColor.RED + "That's a donator class!");
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
                }
            }
        } catch (NullPointerException ex) {
            // Catches null ClickedBlock
        }
    }

    private void handKit(Player player, Group group) {
        player.sendMessage(ChatColor.GREEN + "You have chosen " + ChatColor.AQUA + group.toString().toLowerCase() + ChatColor.GREEN + " as your class!");

        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR, 0));
        player.getInventory().setChestplate(new ItemStack(Material.AIR, 0));
        player.getInventory().setLeggings(new ItemStack(Material.AIR, 0));
        player.getInventory().setBoots(new ItemStack(Material.AIR, 0));

        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        if (group.equals(Group.FIREARMS)) {
            ItemStack AMMO = new ItemStack(Material.FLINT, 64);
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

            player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
            player.getInventory().setItem(1, BLAZE_ROD);
            player.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));
            player.getInventory().setItem(9, AMMO);
        }
        if (group.equals(Group.SPY)) {
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
        }
        if (group.equals(Group.KNIGHT)) {
            player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
            player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
            player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
            player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));

            player.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1));
            player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
        }
        if (group.equals(Group.TANK)) {
            player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
            player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
            player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
            player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));

            player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
            player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
        }
        if (group.equals(Group.ARCHER)) {
            player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
            player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
            player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
            player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));

            player.getInventory().setItem(0, new ItemStack(Material.BOW, 1));
            player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
            player.getInventory().setItem(9, new ItemStack(Material.ARROW, 64));
        }
        if (group.equals(Group.MEDIC)) {
            player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
            player.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE, 1));
            player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
            player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));

            player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
            player.getInventory().setItem(1, new ItemStack(Material.POTION, 32, (short) 16437));
            player.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));
        }
        player.updateInventory();
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
            if (player.getItemInHand().getType() == Material.WATCH) {

                ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 1);
                ItemMeta spywatchMeta = SPY_WATCH.getItemMeta();
                spywatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");

                List<String> spyLore = new ArrayList<String>();
                spyLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
                spywatchMeta.setLore(spyLore);
                SPY_WATCH.setItemMeta(spywatchMeta);

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

                player.updateInventory();
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
        MEDIC;
    }

    @EventHandler
    public void drop(PlayerDropItemEvent e) {
        if (!e.getPlayer().getWorld().getName().equals(name)) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void PotionsSplash(PotionSplashEvent e) {
        if (e.getEntity().getShooter() instanceof Player && e.getAffectedEntities().contains(e.getEntity().getShooter())) {
            e.getEntity().getShooter().removePotionEffect(e.getPotion().getEffects().iterator().next().getType());
        }
    }
}
