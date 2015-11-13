package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@MapConfig(
        name = "zoned",
        fullName = "Zoned",
        creators = {"Heartist"},
        gamemodes = {Gamemode.INFECTION, Gamemode.KOTH, Gamemode.LTS}
)
@Region(
        x1 = 158,
        y1 = 139,
        z1 = -160,
        x2 = -171,
        y2 = 54,
        z2 = 156
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.ARROW, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_BOOTS, Material.STONE_SWORD, Material.LEATHER_HELMET, Material.WATCH}
)
public class Zoned extends BattleMap implements Listener {
    public Zoned() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -2, 88, 49));
        blueSpawns.add(new Location(w, -2, 88, -26));

        Location blueFlag = new Location(w, -3, 89, -56);
        Location redFlag = new Location(w, -3, 89, 78);
        setCTFFlags(getName(), redFlag, blueFlag);

        setKoTHMonument(new Location(w, -3, 92, 11));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -2, 87, 88));
        FFASpawns.add(new Location(w, -2, 87, -65));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack JUMP = new ItemStack(Material.FIREWORK, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);

        ItemMeta spywatchMeta = SPY_WATCH.getItemMeta();
        spywatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");
        List<String> spyLore = new ArrayList<String>();
        spyLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
        spywatchMeta.setLore(spyLore);
        SPY_WATCH.setItemMeta(spywatchMeta);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(4, JUMP);
        i.setItem(8, SPY_WATCH);
        i.setItem(10, ARROWS);

    }

    @EventHandler
    public void onFireworkUse(PlayerInteractEvent event) {
        if (getArena().equals(getName())) {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.getItemInHand().getType() == Material.FIREWORK) {
                    if (getMode() == Gamemode.INFECTION) {
                        player.sendMessage(ChatColor.RED + "Zoned fireworks on infection act more strangely!");
                        float x = (float) (Math.random() / 2);
                        float z = (float) Math.random() / 2;

                        if (Math.random() > 0.5) x = x - (x * 2);
                        if (Math.random() > 0.5) z = z - (z * 2);
                        player.getInventory().removeItem(new ItemStack(Material.FIREWORK, 1));
                        player.setVelocity(new Vector(x, 1.05, z));
                        return;
                    }
                    player.getInventory().removeItem(new ItemStack(Material.FIREWORK, 1));
                    player.setVelocity(new Vector(0, 1.05, 0));
                }
            }
        }
    }

    @EventHandler
    public void onSpyWatchInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(getName())) return;
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
                ArmourUtils.colourArmourAccordingToTeam(BattlePlayer.getBattlePlayer(player), new ItemStack[]{LEATHER_CHESTPLATE});
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

}
