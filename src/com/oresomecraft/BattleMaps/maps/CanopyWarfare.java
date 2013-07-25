package com.oresomecraft.BattleMaps.maps;

import java.util.ArrayList;
import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemodes.TDM;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CanopyWarfare extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public CanopyWarfare() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "Canopy";
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

        String par = event.getMessage();
        BattlePlayer p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
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
            ItemStack JUNGLE_WOOD = new ItemStack(Material.WOOD, 32, (short)3);
            //You're welcome - R3

            ItemMeta arrows = ARROWS.getItemMeta();
            arrows.setDisplayName(ChatColor.GOLD + "Poison Darts");
            ARROWS.setItemMeta(arrows);

            if (TDM.isBlue(p.getName())) {

                //Sets blue armor
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
                bootsMeta.setColor(Color.BLUE);
                LEATHER_BOOTS.setItemMeta(bootsMeta);

                LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
                pantsMeta.setColor(Color.BLUE);
                LEATHER_PANTS.setItemMeta(pantsMeta);

                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
                chestplateMeta.setColor(Color.BLUE);
                LEATHER_CHESTPLATE.setItemMeta(chestplateMeta);

            }

            if (TDM.isRed(p.getName())) {

                //Sets red armor
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
                bootsMeta.setColor(Color.RED);
                LEATHER_BOOTS.setItemMeta(bootsMeta);

                LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
                pantsMeta.setColor(Color.RED);
                LEATHER_PANTS.setItemMeta(pantsMeta);

                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
                chestplateMeta.setColor(Color.RED);
                LEATHER_CHESTPLATE.setItemMeta(chestplateMeta);

            }


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
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    //Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

    // Code to prevent block breaking.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    // Code to prevent block placing.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayer(EntityDamageByEntityEvent e) {
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
