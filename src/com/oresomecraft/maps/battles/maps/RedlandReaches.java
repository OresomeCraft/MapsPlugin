package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class RedlandReaches extends BattleMap implements Listener {

    public RedlandReaches() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(18);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS,
                Material.LEATHER_LEGGINGS, Material.STONE_SWORD, Material.FERMENTED_SPIDER_EYE});
        setAllowBuild(false);
        setAutoSpawnProtection(2);
    }

    // Map details
    String name = "reaches";
    String fullName = "Redland Reaches";
    String creators = "__R3, SecretSeriosity and Sebby1976";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.LTS, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -146, 72, -1, 90.1F, -3.7F));
        blueSpawns.add(new Location(w, 2, 72, -1, 271.8F, -7.5F));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -108, 107, -25, 106.5F, 13.8F));
        FFASpawns.add(new Location(w, -111, 103, 25, 59.2F, 10.1F));
        FFASpawns.add(new Location(w, -37, 107, 22, 294.5F, 18));
        FFASpawns.add(new Location(w, -32, 103, -27, 239.2F, 11.2F));
        FFASpawns.add(new Location(w, -146, 72, -1, 90.1F, -3.7F));
        FFASpawns.add(new Location(w, 2, 72, -1, 271.8F, -7.5F));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);
        ItemStack FIRE = new ItemStack(Material.FERMENTED_SPIDER_EYE, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        LeatherArmorMeta helmet = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
        helmet.setColor(Color.SILVER);
        LEATHER_HELMET.setItemMeta(helmet);

        LeatherArmorMeta leggings = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
        leggings.setColor(Color.SILVER);
        LEATHER_PANTS.setItemMeta(leggings);

        LeatherArmorMeta boots = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
        boots.setColor(Color.SILVER);
        LEATHER_BOOTS.setItemMeta(boots);

        ItemMeta fMeta = FIRE.getItemMeta();
        fMeta.setDisplayName(ChatColor.BLUE + "Spacing Eye");

        List<String> fLore = new ArrayList<String>();
        fLore.add(ChatColor.BLUE + "Hit players with this to knock them away!");
        fMeta.setLore(fLore);
        FIRE.setItemMeta(fMeta);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        FIRE.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, PUMPKIN_PIE);
        i.setItem(4, APPLE);
        i.setItem(8, FIRE);
        i.setItem(17, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 50;
    public int y1 = 154;
    public int z1 = -132;
    //Bottom right corner.
    public int x2 = -175;
    public int y2 = 40;
    public int z2 = 107;

    @EventHandler
    public void feather(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getPlayer().getItemInHand().getType() == Material.FEATHER) {
                ItemStack FIRE = new ItemStack(Material.FEATHER, 1);
                ItemMeta fMeta = FIRE.getItemMeta();
                fMeta.setDisplayName(ChatColor.BLUE + "Lightweight");

                List<String> fLore = new ArrayList<String>();
                fLore.add(org.bukkit.ChatColor.BLUE + "Interact with this to speed up!");
                fMeta.setLore(fLore);
                FIRE.setItemMeta(fMeta);
                event.getPlayer().getInventory().removeItem(FIRE);
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 20, 1));
            }
        }
    }

    @EventHandler
    public void zombieHit(EntityDamageEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Zombie) {
            event.setDamage(1000);
        }
    }

    @EventHandler
    public void zombieHit(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Zombie) {
            event.setDamage(0);
        }
    }

    @EventHandler
    public void zombieDeath(EntityDeathEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Zombie) {
            event.getDrops().clear();
            ItemStack FIRE = new ItemStack(Material.FEATHER, 1);
            ItemMeta fMeta = FIRE.getItemMeta();
            fMeta.setDisplayName(ChatColor.BLUE + "Lightweight");

            List<String> fLore = new ArrayList<String>();
            fLore.add(org.bukkit.ChatColor.BLUE + "Interact with this to speed up!");
            fMeta.setLore(fLore);
            FIRE.setItemMeta(fMeta);

            event.getDrops().add(FIRE);
        }
    }
}
