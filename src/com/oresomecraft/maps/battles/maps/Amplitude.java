package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.events.BattleEndEvent;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.map.MapLoadEvent;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.maps.MapsPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "amplitude",
        fullName = "Amplitude",
        creators = {"Heartist"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -40,
        y1 = 95,
        z1 = -11,
        x2 = -97,
        y2 = 68,
        z2 = 66
)
@Attributes(
        allowBuild = false,
        autoSpawnProtection = true,
        disabledDrops = {Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.IRON_SWORD, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET}
)
public class Amplitude extends BattleMap implements Listener {

    public int auraBlast;

    public Amplitude() {
        super.initiate(this);
    }

    @EventHandler
    public void onLoad(MapLoadEvent event) {
        if (event.getWorld().getName().equalsIgnoreCase(getName())) {
            amplitudeTimer();
        }
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -27, 66, 31));

        blueSpawns.add(new Location(w, 85, 66, 31));

        setKoTHMonument(new Location(w, 28, 69, 31));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 27, 66, 31));
        FFASpawns.add(new Location(w, 0, 66, 31));
        FFASpawns.add(new Location(w, -11, 66, 52));
        FFASpawns.add(new Location(w, -12, 66, 11));
        FFASpawns.add(new Location(w, -26, 66, 19));
        FFASpawns.add(new Location(w, -27, 66, 43));
        FFASpawns.add(new Location(w, 85, 66, 31));
        FFASpawns.add(new Location(w, 82, 66, 9));
        FFASpawns.add(new Location(w, 70, 66, 48));
        FFASpawns.add(new Location(w, 56, 66, 39));
        FFASpawns.add(new Location(w, 55, 66, 4));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 3);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        ItemUtils.nameItem(IRON_SWORD, ChatColor.BLUE + "Aura Sword");
        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setLeggings(LEATHER_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH);
        i.setItem(4, BREAD);
        i.setItem(11, ARROWS);
    }

    public void amplitudeTimer() {
        Bukkit.getServer().getScheduler().cancelTask(auraBlast);

        auraBlast = Bukkit.getScheduler().scheduleSyncRepeatingTask(MapsPlugin.getInstance(), new Runnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.isSneaking() && p.getItemInHand().getType() == Material.IRON_SWORD) {

                        if (p.getTotalExperience() >= 50) {
                            p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1F, (p.getTotalExperience() / 12));
                            return;
                        }

                        if (p.getTotalExperience() == 49) {
                            p.sendMessage(ChatColor.GREEN + "Aura blast fully charged! Hit a player to strike them!");
                        }

                        p.setTotalExperience(p.getTotalExperience() + 1);
                        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1F, (p.getTotalExperience() / 10));
                    }
                }
            }

        }, 4L, 4L);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        Player p = (Player) event.getDamager();

        if (p.getTotalExperience() >= 50) {
            int strikes = 20;
            while (strikes > 0) {
                p.getWorld().strikeLightningEffect(event.getEntity().getLocation());
                strikes--;
            }
            p.getWorld().createExplosion(event.getEntity().getLocation(), 4F, false);
            event.setDamage(event.getDamage() * 2);
            p.setTotalExperience(0);
            return;
        }

        if (p.getTotalExperience() >= 40) {
            p.getWorld().strikeLightningEffect(event.getEntity().getLocation());
            p.getWorld().createExplosion(event.getEntity().getLocation(), 2F, false);
            event.setDamage(event.getDamage() + 4);
            p.setTotalExperience(0);
            return;
        }

        if (p.getTotalExperience() >= 30) {
            event.setDamage(event.getDamage() + 2);
            p.setTotalExperience(0);
        }
    }

    @EventHandler
    public void battleEnd(BattleEndEvent event) {
        Bukkit.getScheduler().cancelTask(auraBlast);
    }


    @EventHandler
    public void explode(EntityExplodeEvent event) {
        if (!event.getLocation().getWorld().getName().equals(getName())) return;
        event.blockList().clear();
    }
}
