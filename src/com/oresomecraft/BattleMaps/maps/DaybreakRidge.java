package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.OresomeBattles.api.Monument;
import org.bukkit.*;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class DaybreakRidge extends BattleMap implements IBattleMap, Listener {

    public DaybreakRidge() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setCPTime(20);
        disableDrops(new Material[]{Material.STONE_SWORD, Material.LEATHER_HELMET});
    }

    String name = "daybreakridge";
    String fullName = "Daybreak Ridge";
    String creators = "LanderA, _Moist and AnomalousRei";
    Gamemode[] modes = {Gamemode.CP};

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, -118, 54, -109));
        redSpawns.add(new Location(w, 81, 54, -117));
        Monument m1 = new Monument("Alpha", name, new Location(w, -19, 92, -181));
        Monument m2 = new Monument("Beta", name, new Location(w, -19, 71, -138));
        Monument m3 = new Monument("Gamma", name, new Location(w, -18, 93, -89));
        Monument m4 = new Monument("Delta", name, new Location(w, -19, 85, -47));
        setCapturePoints(new Monument[]{m1, m2, m3, m4});
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -118, 54, -109));
        FFASpawns.add(new Location(w, 81, 54, -117));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(10, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -207;
    public int y1 = 52;
    public int z1 = -1220;

    //Bottom right corner.
    public int x2 = -38;
    public int y2 = 112;
    public int z2 = -1125;

    @EventHandler(priority = EventPriority.NORMAL)
    public void springFeather(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            Player p = event.getPlayer();
            Action a = event.getAction();
            ItemStack i = p.getItemInHand();
            Inventory inv = p.getInventory();
            Material tool = i.getType();
            if (p.getWorld().getName().equals(name)) {
                if (tool == Material.FEATHER) {

                    if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10 * 20, 5));
                        ItemStack ointment = new ItemStack(p.getItemInHand());
                        ointment.setAmount(1);
                        inv.removeItem(ointment);
                    }
                }
            }
        }
    }

    @EventHandler
    public void zombieHit(EntityDamageEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Zombie) {
                event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.GHAST_SCREAM, 1F, 1F);
                event.setDamage(1000);
            }
        }
    }

    @EventHandler
    public void zombieDeath(EntityDeathEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Zombie) {
                event.getDrops().clear();
                ItemStack FIRE = new ItemStack(Material.FEATHER, 1);
                ItemMeta fMeta = FIRE.getItemMeta();
                fMeta.setDisplayName(ChatColor.BLUE + "Spring Feather");

                List<String> fLore = new ArrayList<String>();
                fLore.add(org.bukkit.ChatColor.BLUE + "Who wants a jump boost??");
                fMeta.setLore(fLore);
                FIRE.setItemMeta(fMeta);

                event.getDrops().add(FIRE);
                //Lol, can't be stuffed changing the names again, someone else do it pls. ~ R3
            }
        }
    }
}
