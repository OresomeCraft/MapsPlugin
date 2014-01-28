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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
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
        //dud
    }

    @EventHandler
    public void interact(PlayerInteractEvent e) {
        if (!e.getPlayer().getWorld().getName().equals(name)) return;
        try {
            Player p = e.getPlayer();
            Action a = e.getAction();
            ItemStack hand = p.getItemInHand();
            Block b = e.getClickedBlock();

            if (b.getType().equals(Material.SIGN_POST) || b.getType().equals(Material.WALL_SIGN)) {
                BlockState state = b.getState();
                Sign s = (Sign) state;

                //Sign stuff
                if (s.getLine(1).contains("Firearms")) {
                    handKit(p, "Firearms");
                    return;
                }
                if (s.getLine(1).contains("Spy") && p.hasPermission("battles.rank.donator")) {
                    handKit(p, "Spy");
                    return;
                } else {
                    if (!p.hasPermission("battles.rank.donator")) {
                        p.sendMessage(ChatColor.RED + "That's a donator class!");
                    }
                }
                if (s.getLine(1).contains("Knight")) {
                    handKit(p, "Knight");
                    return;
                }
                if (s.getLine(1).contains("Archer")) {
                    handKit(p, "Archer");
                    return;
                }
                if (s.getLine(1).contains("Medic")) {
                    handKit(p, "Medic");
                    return;
                }
                if (s.getLine(1).contains("Tank")) {
                    handKit(p, "Tank");
                    return;
                }
            }
        } catch (NullPointerException ex) {
            //oops null clickedblock
        }
    }

    private void handKit(Player p, String type) {
        p.sendMessage(ChatColor.GREEN + "You have chosen " + ChatColor.AQUA + type + ChatColor.GREEN + " as your class!");
        p.getInventory().clear();
        p.getInventory().setHelmet(new ItemStack(Material.AIR, 0));
        p.getInventory().setChestplate(new ItemStack(Material.AIR, 0));
        p.getInventory().setLeggings(new ItemStack(Material.AIR, 0));
        p.getInventory().setBoots(new ItemStack(Material.AIR, 0));
        for (PotionEffect po : p.getActivePotionEffects()) {
            p.removePotionEffect(po.getType());
        }
        if (type.equals("Firearms")) {
            ItemStack AMMO = new ItemStack(Material.FLINT, 64);
            ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);

            ItemMeta blaze_rod = BLAZE_ROD.getItemMeta();
            blaze_rod.setDisplayName(ChatColor.BLUE + "Retro Wartown Gun");
            BLAZE_ROD.setItemMeta(blaze_rod);

            ItemMeta ammo = AMMO.getItemMeta();
            ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
            AMMO.setItemMeta(ammo);

            p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
            p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
            p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

            p.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
            p.getInventory().setItem(1, BLAZE_ROD);
            p.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));
            p.getInventory().setItem(9, AMMO);
        }
        if (type.equals("Spy")) {
            ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 5);
            ItemMeta spywatchMeta = SPY_WATCH.getItemMeta();
            spywatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");
            List<String> spyLore = new ArrayList<String>();
            spyLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
            spywatchMeta.setLore(spyLore);
            SPY_WATCH.setItemMeta(spywatchMeta);

            p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
            p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
            p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

            p.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
            p.getInventory().setItem(2, SPY_WATCH);
            p.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
        }
        if (type.equals("Knight")) {
            p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
            p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
            p.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
            p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));

            p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1));
            p.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
        }
        if (type.equals("Tank")) {
            p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
            p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
            p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));

            p.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
            p.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
        }
        if (type.equals("Archer")) {
            p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
            p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
            p.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));

            p.getInventory().setItem(0, new ItemStack(Material.BOW, 1));
            p.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
            p.getInventory().setItem(9, new ItemStack(Material.ARROW, 64));
        }
        if (type.equals("Medic")) {
            p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
            p.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE, 1));
            p.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
            p.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));

            p.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
            p.getInventory().setItem(1, new ItemStack(Material.POTION, 32, (short) 16437));
            p.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));
        }
        p.updateInventory();
    }

    @EventHandler
    public void gun(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        Action a = event.getAction();
        ItemStack i = p.getItemInHand();
        Inventory inv = p.getInventory();
        Material tool = i.getType();
        final World world = loc.getWorld();
        String name = p.getName();

        if (tool == Material.BLAZE_ROD) {

            if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {

                if (inv.contains(Material.FLINT)) {
                    p.launchProjectile(Arrow.class);
                    world.playSound(loc, Sound.COW_WALK, 10, 10);
                    ItemStack AMMO = new ItemStack(Material.FLINT, 1);
                    inv.removeItem(AMMO);

                    ItemMeta ammo = AMMO.getItemMeta();
                    ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
                    AMMO.setItemMeta(ammo);
                    inv.removeItem(AMMO);

                    //Make it remove normal flints, too.
                    p.updateInventory();
                } else {
                    world.playSound(loc, Sound.CLICK, 10, 10);
                }

            }

        }

    }

    @EventHandler
    public void onSpyWatchInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player p = event.getPlayer();
        Action a = event.getAction();
        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            if (p.getItemInHand().getType() == Material.WATCH) {
                ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 1);
                ItemMeta spywatchMeta = SPY_WATCH.getItemMeta();
                spywatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");
                List<String> spyLore = new ArrayList<String>();
                spyLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
                spywatchMeta.setLore(spyLore);
                SPY_WATCH.setItemMeta(spywatchMeta);
                p.getInventory().removeItem(SPY_WATCH);
                p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                p.getInventory().removeItem(new ItemStack(Material.IRON_BOOTS, 1));
                p.getInventory().removeItem(new ItemStack(Material.IRON_LEGGINGS, 1));
                p.getInventory().removeItem(new ItemStack(Material.IRON_HELMET, 1));
                p.getInventory().removeItem(new ItemStack(Material.IRON_CHESTPLATE, 1));
                p.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
                p.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
                p.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
                p.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
                p.updateInventory();
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 15 * 20, 0));
            }
        }
    }
}
