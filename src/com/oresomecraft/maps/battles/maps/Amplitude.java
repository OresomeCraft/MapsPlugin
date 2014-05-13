package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Amplitude extends BattleMap implements Listener {

    public Amplitude() {
        super.initiate(this, name, fullName, creators, modes);
        setAutoSpawnProtection(4);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.IRON_SWORD, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET});
    }

    String name = "amplitude";
    String fullName = "Amplitude";
    String[] creators = {"xXxTakumaxXx", "__R3", "Fliine"};
    Gamemode[] modes = {Gamemode.KOTH, Gamemode.INFECTION, Gamemode.FFA, Gamemode.TDM, Gamemode.LMS};

    @EventHandler
    public void onLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equalsIgnoreCase(name)) {
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
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 3);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        InvUtils.nameItem(IRON_SWORD, ChatColor.BLUE + "Aura Sword");
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH);
        i.setItem(4, BREAD);
        i.setItem(11, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -40;
    public int y1 = 95;
    public int z1 = -11;

    //Bottom right corner.
    public int x2 = 97;
    public int y2 = 68;
    public int z2 = 66;

    public int auraBlast;

    public void amplitudeTimer() {
        Bukkit.getServer().getScheduler().cancelTask(auraBlast);

        auraBlast = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
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
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
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
    public void onExplode(EntityExplodeEvent event) {
        if (!event.getLocation().getWorld().getName().equals(name)) return;
        event.blockList().clear();
    }
}
