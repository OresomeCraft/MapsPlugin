package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

@MapConfig(
        name = "hypno",
        fullName = "Hypnosis",
        creators = {"zachoz", "pegabeavercorn", "DragonDrew", "kivluh"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -716,
        y1 = 5,
        z1 = -1262,
        x2 = -946,
        y2 = 146,
        z2 = -1484
)
@Attributes(
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD, Material.STONE_SPADE, Material.IRON_PICKAXE, Material.EMERALD}
)
public class Hypno extends BattleMap implements Listener {

    public Hypno() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -773, 102, -1338, 134, 0));
        redSpawns.add(new Location(w, -779, 92, -1349, 134, 0));
        redSpawns.add(new Location(w, -759, 81, -1355, 173, 0));
        redSpawns.add(new Location(w, -747, 63, -1376, 133, 0));
        redSpawns.add(new Location(w, -772, 63, -1372, -141, 0));
        redSpawns.add(new Location(w, -757, 72, -1353, 175, 0));

        blueSpawns.add(new Location(w, -791, 93, -1379, -89, 0));
        blueSpawns.add(new Location(w, -827, 106, -1379, -54, 0));
        blueSpawns.add(new Location(w, -810, 91, -1349, -89, 0));
        blueSpawns.add(new Location(w, -808, 79, -1379, -90, 0));
        blueSpawns.add(new Location(w, -784, 63, -1418, -34, 0));
        blueSpawns.add(new Location(w, -800, 62, -1434, -30, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -783, 91, -1331, 159, 0);
        Location blueSpawn = new Location(w, -814, 83, -1389, -18, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -826, 72, -1439, -44, 0));
        FFASpawns.add(new Location(w, -781, 72, -1427, -2, 0));
        FFASpawns.add(new Location(w, -817, 62, -1436, -43, 0));
        FFASpawns.add(new Location(w, -819, 96, -1413, -56, 0));
        FFASpawns.add(new Location(w, -794, 98, -1380, -76, 0));
        FFASpawns.add(new Location(w, -801, 68, -1389, -44, 0));
        FFASpawns.add(new Location(w, -761, 63, -1401, 10, 0));
        FFASpawns.add(new Location(w, -829, 110, -1328, -115, 0));
        FFASpawns.add(new Location(w, -802, 69, -1298, -163, 0));
        FFASpawns.add(new Location(w, -810, 96, -1350, -83, 0));
        FFASpawns.add(new Location(w, -760, 105, -1345, 149, 0));
        FFASpawns.add(new Location(w, -748, 81, -1344, 136, 0));
        FFASpawns.add(new Location(w, -739, 64, -1371, 97, 0));
        FFASpawns.add(new Location(w, -791, 83, -1347, -145, 0));
        FFASpawns.add(new Location(w, -767, 108, -1337, 117, 0));
        FFASpawns.add(new Location(w, -740, 93, -1362, 47, 0));
        FFASpawns.add(new Location(w, -760, 68, -1398, 16, 0));
        FFASpawns.add(new Location(w, -800, 87, -1384, -103, 0));
        FFASpawns.add(new Location(w, -781, 69, -1424, -18, 0));
        FFASpawns.add(new Location(w, -746, 73, -1358, 123, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SHOVEL = new ItemStack(Material.STONE_SPADE, 1);
        ItemStack IRON_PICK = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack EMERALD = new ItemStack(Material.EMERALD, 1);
        ItemStack EGG_HYPNO = new ItemStack(Material.EGG, 1);

        ItemMeta egg_hypno = EGG_HYPNO.getItemMeta();
        egg_hypno.setDisplayName(ChatColor.BLUE + "Flash bang grenade");

        List<String> eggLore = new ArrayList<String>();
        eggLore.add(ChatColor.BLUE + "Everyone's favourite item!");
        egg_hypno.setLore(eggLore);
        EGG_HYPNO.setItemMeta(egg_hypno);

        ItemMeta emerald = EMERALD.getItemMeta();
        emerald.setDisplayName(ChatColor.BLUE + "Nausea Stone");
        EMERALD.setItemMeta(emerald);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICK);
        i.setItem(4, STEAK);
        i.setItem(5, HEALTH_POTION);
        i.setItem(9, ARROWS);

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        int material = block.getTypeId();
        Location location = block.getLocation();
        if (location.getWorld().getName().equals(getName())) {
            if (isInsideRegion(location)) {

                if (material == 43 || material == 44 || material == 35 || material == 42
                        || material == 49 || material == 123 || material == 69 || material == 124) {

                    event.setCancelled(true);
                }
            }
        }
    }

    //@EventHandler(priority = EventPriority.NORMAL)
    public void hypnoGem(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity entity = event.getEntity();
        Location location = entity.getLocation();

        if (isInsideRegion(location)) {

            if (damager instanceof Player) {

                Player damagerPlayer = (Player) damager;
                ItemStack weapon = damagerPlayer.getItemInHand();
                Material material = weapon.getType();

                if (entity instanceof Player) {

                    Player p = (Player) entity;

                    if (material == Material.EMERALD) {

                        PotionEffectType confuse = PotionEffectType.CONFUSION;
                        PotionEffect confuseE = new PotionEffect(confuse, 400,
                                1);
                        p.addPotionEffect(confuseE);
                    }
                }
            }
        }
    }

    //@EventHandler(priority = EventPriority.NORMAL)
    public void hypnoBangGrenade(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();

        if (isInsideRegion(location)) {

            if (entity instanceof Egg) {

                world.playEffect(location, Effect.ENDER_SIGNAL, 5);
                world.playSound(location, Sound.EXPLODE, 10, 10);

                List<Entity> nearby = entity.getNearbyEntities(3, 3, 3);

                int amount = nearby.size();
                int count = 0;
                int counter;
                for (counter = 0; counter < amount; counter++) {

                    Entity near = nearby.get(count);

                    if (near instanceof Player && !BattlePlayer.getBattlePlayer((Player) near).isSpectator()) {

                        Player p = (Player) near;
                        Player s = null;
                        if (event.getEntity().getShooter() instanceof Player)
                            s = (Player) event.getEntity().getShooter();
                        BattlePlayer pp = null;
                        BattlePlayer ss = null;
                        try {
                            pp = BattlePlayer.getBattlePlayer(s);
                            ss = BattlePlayer.getBattlePlayer(p);
                        } catch (Exception ex) {
                            System.out.println("Couldn't cast player " + s.getName() + " or " + p.getName() + " to BattlePlayer!");
                            //erps.
                        }
                        if (ss.getTeamType().equals(pp.getTeamType())) return;

                        PotionEffectType blind = PotionEffectType.BLINDNESS;
                        PotionEffectType slow = PotionEffectType.SLOW;
                        PotionEffectType flash = PotionEffectType.NIGHT_VISION;

                        PotionEffect blindE = new PotionEffect(blind, 200, 2);
                        PotionEffect slowE = new PotionEffect(slow, 200, 2);
                        PotionEffect flashE = new PotionEffect(flash, 200, 2);

                        p.addPotionEffect(blindE);
                        p.addPotionEffect(slowE);
                        p.addPotionEffect(flashE);
                    }
                }
                count++;

            }

        }
    }

    @EventHandler
    public void arrowTrail(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(getName());
        if (getArena().equals(getName())) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 8);
            }
        }
    }
}
