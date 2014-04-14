package com.oresomecraft.maps.battles.maps;

/*
 * Map Currently under Construction
 *
 * Map created for Christmas, to be released at Christmas time, 2013
 */

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.*;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.meta.FireworkMeta;

import com.oresomecraft.OresomeBattles.api.*;

import java.util.Random;

@MapConfig
public class Christmas extends BattleMap implements Listener {

    public Christmas() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "christmas";
    String fullName = "Christmas";
    String creators = "psgs, _Moist, Geedubs01, shavahn2003";
    Gamemode[] modes = {Gamemode.KOTH, Gamemode.TDM};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -107, 55, -44, -45, 0);
        Location blueSpawn = new Location(w, -0.3, 55, -0, 135, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -107, 55, -0, -135, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -0.3, 55, -44, 45, 0));

        setKoTHMonument(new Location(w, -53, 55, -22));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -39, 53, -4, 121, 0);
        Location blueSpawn = new Location(w, -105, 55, -22, -90, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack PUDDING = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemStack GAPPLE = new ItemStack(Material.GOLDEN_APPLE, 1);

        LEATHER_CHESTPLATE.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        LEATHER_BOOTS.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);

        ItemMeta pudding = PUDDING.getItemMeta();
        pudding.setDisplayName(ChatColor.RED + "Pudding");
        PUDDING.setItemMeta(pudding);

        if (p.getTeamType() == Team.TDM_BLUE || p.getTeamType() == Team.KOTH_BLUE) {

            LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
            helmetMeta.setColor(Color.NAVY);
            LEATHER_HELMET.setItemMeta(helmetMeta);

            LeatherArmorMeta chestMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
            chestMeta.setColor(Color.NAVY);
            LEATHER_CHESTPLATE.setItemMeta(chestMeta);

            LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
            pantsMeta.setColor(Color.NAVY);
            LEATHER_PANTS.setItemMeta(pantsMeta);

            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
            bootsMeta.setColor(Color.NAVY);
            LEATHER_BOOTS.setItemMeta(bootsMeta);
        }

        if (p.getTeamType() == Team.TDM_RED || p.getTeamType() == Team.KOTH_RED) {

            LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
            helmetMeta.setColor(Color.FUCHSIA);
            LEATHER_HELMET.setItemMeta(helmetMeta);

            LeatherArmorMeta chestMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
            chestMeta.setColor(Color.FUCHSIA);
            LEATHER_CHESTPLATE.setItemMeta(chestMeta);

            LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
            pantsMeta.setColor(Color.FUCHSIA);
            LEATHER_PANTS.setItemMeta(pantsMeta);

            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
            bootsMeta.setColor(Color.FUCHSIA);
            LEATHER_BOOTS.setItemMeta(bootsMeta);
        }

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(8, GAPPLE);
        i.setItem(2, PUDDING);
        i.setItem(3, HEALTH_POTION);
        i.setItem(10, ARROWS);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 1));
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 0;
    public int y1 = 91;
    public int z1 = -73;

    //Bottom right corner.
    public int x2 = -82;
    public int y2 = 51;
    public int z2 = 16;

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();

        if (itemStack.getType() == Material.MUSHROOM_SOUP) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3 * 20, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 3 * 20, 1));
            player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
            player.sendMessage(ChatColor.RED + "Merry " + ChatColor.GREEN + "Christmas!");
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (p.getWorld().getName().equals(name)) {

                fireworkLaunch(p);
                p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                p.sendMessage(ChatColor.RED + "Merry " + ChatColor.GREEN + "Christmas!");
                final long waitUntil = System.currentTimeMillis() + 100;

                while (System.currentTimeMillis() == waitUntil) {
                    fireworkLaunch(p);
                    p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                }
            }
        }
    }

    public void fireworkLaunch(Player p) {
        FireworkMeta fireworkMeta = (FireworkMeta) (new ItemStack(Material.FIREWORK)).getItemMeta();
        Firework firework = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);

        fireworkMeta.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.GREEN).withColor(Color.RED).withFade(Color.YELLOW).withTrail().build());
        firework.setFireworkMeta(fireworkMeta);

    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
            Random random = new Random();
            if (random.nextBoolean()) {
                Player player = event.getEntity().getKiller();
                if (random.nextBoolean()) {
                    player.awardAchievement(Achievement.OVERKILL);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20, 1));
                    player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                } else {
                    player.awardAchievement(Achievement.KILL_ENEMY);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3 * 20, 1));
                    player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                }
            }
        }
    }
}
