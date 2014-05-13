package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class Plasiaus extends BattleMap implements Listener {

    public Plasiaus() {
        disableDrops(new Material[]{Material.ARROW, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.LEATHER_CHESTPLATE, Material.WATCH, Material.WOOL});
        super.initiate(this, name, fullName, creators, modes);
    }

    String name = "plasiaus";
    String fullName = "Plasiaus Village";
    String[] creators = {"__R3", "kytron", "DanShrdr"};
    Gamemode[] modes = {Gamemode.CTF, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -51, 64, -47, 90.7F, 0.7F));
        blueSpawns.add(new Location(w, 71, 64, -47, 269.7F, 0.5F));

        Location redFlag = new Location(w, -43, 65, -47);
        Location blueFlag = new Location(w, 63, 65, -47);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -51, 64, -47, 90.7F, 0.7F));
        FFASpawns.add(new Location(w, 71, 64, -47, 269.7F, 0.5F));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack JUMP = new ItemStack(Material.FIREWORK, 3);

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);

        ItemMeta spyWatchMeta = SPY_WATCH.getItemMeta();
        spyWatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");
        List<String> spyLore = new ArrayList<String>();
        spyLore.add(ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
        spyWatchMeta.setLore(spyLore);
        SPY_WATCH.setItemMeta(spyWatchMeta);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, SPY_WATCH);
        i.setItem(9, ARROWS);
        i.setItem(8, JUMP);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 75;
    public int y1 = 97;
    public int z1 = 5;

    //Bottom right corner.
    public int x2 = -59;
    public int y2 = 53;
    public int z2 = -96;

    @EventHandler
    public void onSpyWatchInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType() == Material.WATCH) {
                player.getInventory().remove(player.getItemInHand());
                player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                player.getInventory().remove(new ItemStack(Material.IRON_BOOTS));
                player.getInventory().remove(new ItemStack(Material.IRON_LEGGINGS));
                player.getInventory().remove(new ItemStack(Material.IRON_HELMET));
                ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                InvUtils.colourArmourAccordingToTeam(BattlePlayer.getBattlePlayer(player), new ItemStack[]{LEATHER_CHESTPLATE});
                player.getInventory().remove(LEATHER_CHESTPLATE);
                player.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
                player.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
                player.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
                player.getInventory().addItem(LEATHER_CHESTPLATE);
                player.updateInventory();
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 15 * 20, 0));
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name)) {
            if (event.getBlock().getType().getId() != 160 && event.getBlock().getType().getId() != 5) {
                event.setCancelled(true);
            } else {
                if (event.getBlock().getType() == Material.WOOL) {
                    event.getBlock().getDrops().clear();
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name)) event.setCancelled(true);
    }

    @EventHandler
    public void onFireworkUse(PlayerInteractEvent event) {
        if (getArena().equals(name)) {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.getItemInHand().getType() == Material.FIREWORK) {
                    player.getInventory().removeItem(new ItemStack(Material.FIREWORK, 1));
                    player.setVelocity(new Vector(0, 1.05, 0));
                }
            }
        }
    }
}
