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
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
import java.util.Random;

@MapConfig
public class Eriden extends BattleMap implements Listener {

    public Eriden() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(15);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS,
                Material.LEATHER_LEGGINGS, Material.STONE_SWORD, Material.FERMENTED_SPIDER_EYE});
        setAutoSpawnProtection(5);
        setAllowBuild(false);
    }

    // Map details
    String name = "eriden";
    String fullName = "Eriden Falls";
    String[] creators = {"__R3", "DanShrdr"};
    Gamemode[] modes = {Gamemode.CTF, Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -39, 75, 15, 21.8F, 11.1F));
        blueSpawns.add(new Location(w, -29, 75, -77, 212.8F, 11.1F));
        redSpawns.add(new Location(w, -81, 57, -27, 43.3F, 11.7F));
        blueSpawns.add(new Location(w, 11, 57, -37, 232.2F, -4.3F));

        Location redFlag = new Location(w, -56, 60, -9);
        Location blueFlag = new Location(w, -13, 60, -55);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -39, 75, 15, 21.8F, 11.1F));
        FFASpawns.add(new Location(w, -29, 75, -77, 212.8F, 11.1F));
        FFASpawns.add(new Location(w, -81, 57, -27, 43.3F, 11.7F));
        FFASpawns.add(new Location(w, 11, 57, -37, 232.2F, -4.3F));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);
        ItemStack SLOW = new ItemStack(Material.FERMENTED_SPIDER_EYE, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        LEATHER_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 4);

        LeatherArmorMeta helmet = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
        helmet.setColor(Color.GREEN);
        LEATHER_HELMET.setItemMeta(helmet);

        LeatherArmorMeta leggings = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
        leggings.setColor(Color.GREEN);
        LEATHER_PANTS.setItemMeta(leggings);

        LeatherArmorMeta boots = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
        boots.setColor(Color.GREEN);
        LEATHER_BOOTS.setItemMeta(boots);

        ItemMeta fMeta = SLOW.getItemMeta();
        fMeta.setDisplayName(ChatColor.BLUE + "Sluggish Eye");

        List<String> fLore = new ArrayList<String>();
        fLore.add(ChatColor.BLUE + "Hit players with this to slow them down!");
        fMeta.setLore(fLore);
        SLOW.setItemMeta(fMeta);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        SLOW.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, PUMPKIN_PIE);
        i.setItem(3, APPLE);
        i.setItem(8, SLOW);
        i.setItem(17, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -132;
    public int y1 = 137;
    public int z1 = 79;

    //Bottom right corner.
    public int x2 = 59;
    public int y2 = 0;
    public int z2 = -146;

    @EventHandler
    public void vHit(EntityDamageEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Villager) {
            event.setDamage(1000);
        }
    }

    @EventHandler
    public void vDeath(EntityDeathEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Villager) {
            event.getDrops().clear();
            ItemStack FIRE = new ItemStack(Material.FIREWORK, new Random().nextInt(3));
            ItemMeta fMeta = FIRE.getItemMeta();
            fMeta.setDisplayName(ChatColor.BLUE + "Jumpwork");

            List<String> fLore = new ArrayList<String>();
            fLore.add(org.bukkit.ChatColor.BLUE + "Interact with this to hover into the air!");
            fMeta.setLore(fLore);
            FIRE.setItemMeta(fMeta);

            event.getDrops().add(FIRE);
        }
    }

    @EventHandler
    public void blazeRod(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getPlayer().getItemInHand().getType() == Material.FIREWORK) {
                ItemStack FIRE = new ItemStack(Material.FIREWORK, 1);
                ItemMeta fMeta = FIRE.getItemMeta();
                fMeta.setDisplayName(ChatColor.BLUE + "Jumpwork");

                List<String> fLore = new ArrayList<String>();
                fLore.add(org.bukkit.ChatColor.BLUE + "Interact with this to hover into the air!");
                fMeta.setLore(fLore);
                FIRE.setItemMeta(fMeta);
                event.getPlayer().getInventory().removeItem(FIRE);
                event.getPlayer().setVelocity(new Vector(0, 1.5, 0));
            }
        }
    }
}
