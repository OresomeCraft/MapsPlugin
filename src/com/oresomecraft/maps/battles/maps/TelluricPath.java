package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

@MapConfig(
        name = "telluricpath",
        fullName = "Telluric Path",
        creators = {"Heartist"},
        gamemodes = {Gamemode.CTF, Gamemode.TDM}
)
@Region(
        x1 = -161,
        y1 = 83,
        z1 = -119,
        x2 = 16,
        y2 = 0,
        z2 = -119
)
@Attributes(
        tdmTime = 11,
        allowBuild = false,
        pearlDamage = false,
        autoSpawnProtection = true,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_PICKAXE, Material.BOW, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS,
                Material.LEATHER_LEGGINGS, Material.STONE_SWORD, Material.FERMENTED_SPIDER_EYE, Material.IRON_PICKAXE,
                Material.ENDER_PEARL}
)
public class TelluricPath extends BattleMap implements Listener {

    public TelluricPath() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {

        redSpawns.add(new Location(w, -133, 26, -36));
        blueSpawns.add(new Location(w, -1, 26, 34));
        redSpawns.add(new Location(w, -130, 26, 23));
        blueSpawns.add(new Location(w, -7, 26, -27));

        Location redFlag = new Location(w, -125, 27, -9);
        Location blueFlag = new Location(w, -12, 27, 6);
        setCTFFlags(getName(), redFlag, blueFlag);
    }

    public void readyFFASpawns() {

        FFASpawns.add(new Location(w, -133, 26, -36));
        FFASpawns.add(new Location(w, -1, 26, 34));
        FFASpawns.add(new Location(w, -130, 26, 23));
        FFASpawns.add(new Location(w, -7, 26, -27));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);
        ItemStack POISON = new ItemStack(Material.FERMENTED_SPIDER_EYE, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        LeatherArmorMeta helmet = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
        helmet.setColor(Color.PURPLE);
        LEATHER_HELMET.setItemMeta(helmet);

        LeatherArmorMeta chestplate = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
        chestplate.setColor(Color.PURPLE);
        LEATHER_CHESTPLATE.setItemMeta(chestplate);

        LeatherArmorMeta leggings = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
        leggings.setColor(Color.PURPLE);
        LEATHER_PANTS.setItemMeta(leggings);

        LeatherArmorMeta boots = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
        boots.setColor(Color.PURPLE);
        LEATHER_BOOTS.setItemMeta(boots);

        ItemMeta poisonMeta = POISON.getItemMeta();
        poisonMeta.setDisplayName(ChatColor.BLUE + "Poison Eye");

        List<String> poisonLore = new ArrayList<String>();
        poisonLore.add(org.bukkit.ChatColor.BLUE + "Hit players with this to poison them!");
        poisonMeta.setLore(poisonLore);
        POISON.setItemMeta(poisonMeta);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        POISON.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 1);

        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setLeggings(LEATHER_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, PUMPKIN_PIE);
        i.setItem(4, APPLE);
        i.setItem(8, POISON);
        i.setItem(17, ARROW);

    }

    @EventHandler
    public void endermanHit(EntityDamageEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        if (event.getEntity() instanceof Enderman) {
            event.setDamage(1000);
        }
    }

    @EventHandler
    public void enderHit(EntityDamageByEntityEvent event) {
        if (event.getEntity().getWorld().getName().equals(getName())) {
            if (event.getEntity() instanceof Player && event.getDamager() instanceof Enderman) {
                event.setDamage(0);
            }
        }
    }

    @EventHandler
    public void death(EntityDeathEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        if (event.getEntity() instanceof Enderman) {
            event.getDrops().clear();
            if (Math.random() >= 0.6) event.getDrops().add(new ItemStack(Material.ENDER_PEARL, 1));
        }
    }
}
