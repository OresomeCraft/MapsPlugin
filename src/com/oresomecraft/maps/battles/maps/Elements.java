package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@MapConfig(
        name = "elements",
        fullName = "Elements",
        creators = {"broddikill", "koolguydude4", "MiCkEyMiCE"},
        gamemodes = {Gamemode.TDM}
)
@Region(
        x1 = -65,
        y1 = 190,
        z1 = 172,
        x2 = 5,
        y2 = 70,
        z2 = -5
)
@Attributes(
        tdmTime = 12,
        autoSpawnProtection = true,
        disabledDrops = {Material.EMERALD, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.STONE_SWORD, Material.STONE_PICKAXE, Material.LEATHER_HELMET, Material.DIAMOND_SWORD}
)
public class Elements extends BattleMap implements Listener {

    public Elements() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -23, 87, 11));
        blueSpawns.add(new Location(w, -25, 86, 147));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -23, 87, 11));
        FFASpawns.add(new Location(w, -25, 86, 147));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LOG = new ItemStack(Material.LOG, 25);
        ItemStack DIAMOND_SWORD = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemStack IRON_PICK = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack FILI = new ItemStack(Material.EMERALD, 1);

        ItemMeta a = FILI.getItemMeta();
        a.setDisplayName(ChatColor.BLUE + "Fili Shield");

        List<String> aLore = new ArrayList<String>();
        aLore.add(org.bukkit.ChatColor.BLUE + "Hold this to take less damage from enemy projectiles!");
        a.setLore(aLore);
        FILI.setItemMeta(a);

        i.setItem(0, DIAMOND_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICK);
        i.setItem(4, HEALTH);
        i.setItem(7, LOG);
        i.setItem(8, FILI);
        i.setItem(11, ARROWS);
        i.setItem(3, new ItemStack(Material.BREAD, 3));

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15 * 60 * 20, 1));
    }

    @EventHandler
    public void noSpawnBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (contains(loc, -34, -15, 85, 129, 155, 138)) event.setCancelled(true);
        if (contains(loc, -12, -33, 82, 130, 2, 20)) event.setCancelled(true);
    }

    @EventHandler
    public void noSpawnPlace(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if (contains(loc, -34, -15, 85, 129, 155, 138)) event.setCancelled(true);
        if (contains(loc, -12, -33, 82, 130, 2, 20)) event.setCancelled(true);
    }

    @EventHandler
    public void filiShield(EntityDamageByEntityEvent event) {
        if (event.getEntity().getWorld().getName().equals(getName())) {
            if (event.getEntity() instanceof Player) {
                Player p = (Player) event.getEntity();
                if (p.getItemInHand().getType() == Material.EMERALD && event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                    Random random = new Random();
                    if (random.nextBoolean()) {
                        event.setDamage(event.getDamage() - 3);
                    }
                }
            }
        }
    }
}
