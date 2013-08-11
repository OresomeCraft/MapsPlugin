package com.oresomecraft.BattleMaps.maps;

import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.*;
import org.bukkit.enchantments.*;
import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.api.*;
import com.oresomecraft.OresomeBattles.*;
import com.oresomecraft.OresomeBattles.events.*;

public class CanopyWarfare extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public CanopyWarfare() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "canopy";
    String fullName = "Canopy Warfare";
    String creators = "kyoxking, famas_1, _Moist and psgs";
    Gamemode[] modes = {Gamemode.TDM};
    //Map download link: http://www.mediafire.com/download/kvpf8rghaesvs4v/Canopy_Warfare_-_Download_Copy.rar

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        redSpawns.add(new Location(w, 13, 78, -24, 3, -45));

        blueSpawns.add(new Location(w, 26, 78, 86, 2, 139));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, 0, 99, 27, 2, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
        FFASpawns.add(new Location(w, 21, 105, -13, 0, 0));
        FFASpawns.add(new Location(w, 4, 106, -41, 0, 0));
        FFASpawns.add(new Location(w, -18, 101, 13, 0, 0));
        FFASpawns.add(new Location(w, 2, 104, 15, 0, 0));
        FFASpawns.add(new Location(w, -2, 109, -4, 0, 0));
        FFASpawns.add(new Location(w, 28, 105, 10, 0, 0));
        FFASpawns.add(new Location(w, 27, 96, 0, 0, 0));
        FFASpawns.add(new Location(w, 30, 105, -14, 0, 0));
        FFASpawns.add(new Location(w, -9, 106, 18, 0, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
        FFASpawns.add(new Location(w, 0, 99, 27, 0, 0));
        FFASpawns.add(new Location(w, -16, 108, -3, 0, 0));
        FFASpawns.add(new Location(w, -30, 108, -3, 0, 0));
        FFASpawns.add(new Location(w, -18, 101, 13, 0, 0));
        FFASpawns.add(new Location(w, -27, 88, 17, 0, 0));
        FFASpawns.add(new Location(w, -32, 76, 16, 0, 0));
        FFASpawns.add(new Location(w, -46, 97, 7, 0, 0));
        FFASpawns.add(new Location(w, 26, 105, -13, 0, 0));
        FFASpawns.add(new Location(w, 21, 94, 2, 0, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 1);
            ItemStack COOKED_FISH = new ItemStack(Material.COOKED_FISH, 5);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 16);
            ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE, 1);
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack MILK = new ItemStack(Material.MILK_BUCKET, 1);
            ItemStack JUNGLE_WOOD = new ItemStack(Material.WOOD, 32, (short) 3);

            ItemMeta arrows = ARROWS.getItemMeta();
            arrows.setDisplayName(ChatColor.GOLD + "Poison Darts");
            ARROWS.setItemMeta(arrows);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_BOOTS, LEATHER_CHESTPLATE, LEATHER_PANTS});

            p.getInventory().setBoots(LEATHER_BOOTS);
            p.getInventory().setLeggings(LEATHER_PANTS);
            p.getInventory().setChestplate(LEATHER_CHESTPLATE);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, IRON_AXE);
            i.setItem(3, JUNGLE_WOOD);
            i.setItem(4, GOLDEN_APPLE);
            i.setItem(5, COOKED_FISH);
            i.setItem(6, MILK);
            i.setItem(8, ARROWS);

            //Give players invincibility II and strength II for 15 seconds when they spawn
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 15 * 20, 2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 15 * 20, 2));

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 44;
    public int y1 = 107;
    public int z1 = -35;

    //Bottom right corner.
    public int x2 = -9;
    public int y2 = 63;
    public int z2 = 120;

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity().getWorld().getName().equals(name)) {
            Player damaged = (Player) e.getEntity();
            if (e.getDamager() instanceof Projectile) {
                Projectile proj = (Projectile) e.getEntity();
                Arrow arrow = (Arrow) proj;
                if (arrow.getShooter() instanceof Player) {
                    Player shooter = (Player) arrow.getShooter();
                    if (proj instanceof Arrow) {
                        if (arrow.getShooter() instanceof Player) {
                            damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (5 * 20), 2));
                        }
                    }
                }
            }
        }
    }
}
