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
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class HazyPass extends BattleMap implements Listener {

    public HazyPass() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(20);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.BLAZE_ROD, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS,
                Material.LEATHER_LEGGINGS, Material.STONE_SWORD, Material.FERMENTED_SPIDER_EYE});
        setAutoSpawnProtection(2);
    }

    // Map details
    String name = "hazypass";
    String fullName = "Hazy Pass";
    String[] creators = {"AnomalousRei", "__R3", "DanShrdr"};
    Gamemode[] modes = {Gamemode.CTF, Gamemode.TDM, Gamemode.LTS};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 9, 100, -2));
        blueSpawns.add(new Location(w, 148, 100, -10));
        redSpawns.add(new Location(w, 18, 100, 17));
        blueSpawns.add(new Location(w, 138, 100, -30));
        redSpawns.add(new Location(w, 29, 91, 14));
        blueSpawns.add(new Location(w, 125, 91, -36));
        redSpawns.add(new Location(w, 17, 91, -14));
        blueSpawns.add(new Location(w, 140, 91, 0));
        redSpawns.add(new Location(w, 25, 82, -18));
        blueSpawns.add(new Location(w, 131, 82, 5));
        redSpawns.add(new Location(w, 42, 82, 23));
        blueSpawns.add(new Location(w, 112, 82, -40));

        Location redFlag = new Location(w, 1, 110, 8);
        Location blueFlag = new Location(w, 155, 110, -22);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 9, 100, -2));
        FFASpawns.add(new Location(w, 148, 100, -10));
        FFASpawns.add(new Location(w, 18, 100, 17));
        FFASpawns.add(new Location(w, 138, 100, -30));
        FFASpawns.add(new Location(w, 29, 91, 14));
        FFASpawns.add(new Location(w, 125, 91, -36));
        FFASpawns.add(new Location(w, 17, 91, -14));
        FFASpawns.add(new Location(w, 140, 91, 0));
        FFASpawns.add(new Location(w, 25, 82, -18));
        FFASpawns.add(new Location(w, 131, 82, 5));
        FFASpawns.add(new Location(w, 42, 82, 23));
        FFASpawns.add(new Location(w, 112, 82, -40));
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
        helmet.setColor(Color.ORANGE);
        LEATHER_HELMET.setItemMeta(helmet);

        LeatherArmorMeta leggings = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
        leggings.setColor(Color.ORANGE);
        LEATHER_PANTS.setItemMeta(leggings);

        LeatherArmorMeta boots = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
        boots.setColor(Color.ORANGE);
        LEATHER_BOOTS.setItemMeta(boots);

        ItemMeta fMeta = FIRE.getItemMeta();
        fMeta.setDisplayName(ChatColor.BLUE + "Fire Eye");

        List<String> fLore = new ArrayList<String>();
        fLore.add(org.bukkit.ChatColor.BLUE + "Hit players with this to set them on fire!");
        fMeta.setLore(fLore);
        FIRE.setItemMeta(fMeta);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        FIRE.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);

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
    public int x1 = -42;
    public int y1 = 170;
    public int z1 = 97;
    //Bottom right corner.
    public int x2 = 192;
    public int y2 = 51;
    public int z2 = -93;

    @EventHandler
    public void blazeRod(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) {
                    ItemStack FIRE = new ItemStack(Material.BLAZE_ROD, 1);
                    ItemMeta fMeta = FIRE.getItemMeta();
                    fMeta.setDisplayName(ChatColor.BLUE + "Levitation Rod");

                    List<String> fLore = new ArrayList<String>();
                    fLore.add(org.bukkit.ChatColor.BLUE + "Interact with this to hover into the air!");
                    fMeta.setLore(fLore);
                    FIRE.setItemMeta(fMeta);
                    event.getPlayer().getInventory().removeItem(FIRE);
                    event.getPlayer().setVelocity(new Vector(0, 1, 0));
                }
            }
        }
    }

    @EventHandler
    public void pigZombieHit(EntityDamageEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof PigZombie) {
                event.setDamage(1000);
            }
        }
    }

    @EventHandler
    public void pigZombieHit(EntityDamageByEntityEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Player && event.getDamager() instanceof PigZombie) {
                event.setDamage(0);
            }
        }
    }

    @EventHandler
    public void pigZombieDeath(EntityDeathEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof PigZombie) {
                event.getDrops().clear();
                ItemStack FIRE = new ItemStack(Material.BLAZE_ROD, 1);
                ItemMeta fMeta = FIRE.getItemMeta();
                fMeta.setDisplayName(ChatColor.BLUE + "Levitation Rod");

                List<String> fLore = new ArrayList<String>();
                fLore.add(org.bukkit.ChatColor.BLUE + "Interact with this to hover into the air!");
                fMeta.setLore(fLore);
                FIRE.setItemMeta(fMeta);

                event.getDrops().add(FIRE);
            }
        }
    }

    @EventHandler
    public void blockStuff(BlockPlaceEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (getMode() == Gamemode.INFECTION) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "Your power to build was suppressed!");
            }
            if (event.getBlock().getWorld().getBlockAt(1, 110, 8).getLocation().distance(event.getBlock().getLocation()) < 15) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You can't build around the flag!");
            }
            if (event.getBlock().getWorld().getBlockAt(155, 109, -22).getLocation().distance(event.getBlock().getLocation()) < 15) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You can't build around the flag!");
            }
        }
    }

    @EventHandler
    public void blockStuff(BlockBreakEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (getMode() == Gamemode.INFECTION) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "Your power to build was suppressed!");
            }
        }
    }
}
