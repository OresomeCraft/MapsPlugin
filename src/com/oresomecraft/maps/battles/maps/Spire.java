package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

@MapConfig(
        name = "spire",
        fullName = "Spire",
        creators = {"zachoz", "pegabeavercorn"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = -1661,
        y1 = 67,
        z1 = -2392,
        x2 = -1499,
        y2 = 169,
        z2 = -2230
)
@Attributes(
        disabledDrops = {Material.ARROW, Material.IRON_PICKAXE, Material.STONE_SPADE, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.SAND, Material.SPONGE}
)
public class Spire extends BattleMap implements Listener {

    public Spire() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -1509, 75, -2310);
        Location blueSpawn = new Location(w, -1649, 75, -2310);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        blueSpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        redSpawns.add(new Location(w, -1580, 94, -2299, 97, 0));
        blueSpawns.add(new Location(w, -1581, 99, -2322, -87, 0));
        redSpawns.add(new Location(w, -1579, 74, -2234, 179, 0));
        blueSpawns.add(new Location(w, -1516, 74, -2348, 50, 0));
        redSpawns.add(new Location(w, -1520, 74, -2261, 132, 0));
        blueSpawns.add(new Location(w, -1633, 74, -2257, -132, 0));
        redSpawns.add(new Location(w, -1633, 74, -2362, -46, 0));
        blueSpawns.add(new Location(w, -1587, 84, -2302, 44, 0));
        redSpawns.add(new Location(w, -1573, 85, -2301, -41, 0));
        blueSpawns.add(new Location(w, -1573, 85, -2319, -128, 0));
        redSpawns.add(new Location(w, -1588, 85, -2316, 136, 0));
        blueSpawns.add(new Location(w, -1579, 74, -2386, 1, 0));
        redSpawns.add(new Location(w, -1567, 76, -2310, -49, 0));
        blueSpawns.add(new Location(w, -1590, 77, -2315, 118, 0));
        redSpawns.add(new Location(w, -1586, 77, -2301, 25, 0));
        blueSpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        redSpawns.add(new Location(w, -1580, 94, -2299, 97, 0));
        blueSpawns.add(new Location(w, -1581, 99, -2322, -87, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -1510, 74, -2311, -137, 0);
        Location blueSpawn = new Location(w, -1650, 74, -2311, 51, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        FFASpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        FFASpawns.add(new Location(w, -1580, 94, -2299, 97, 0));
        FFASpawns.add(new Location(w, -1581, 99, -2322, -87, 0));
        FFASpawns.add(new Location(w, -1579, 74, -2234, 179, 0));
        FFASpawns.add(new Location(w, -1516, 74, -2348, 50, 0));
        FFASpawns.add(new Location(w, -1520, 74, -2261, 132, 0));
        FFASpawns.add(new Location(w, -1633, 74, -2257, -132, 0));
        FFASpawns.add(new Location(w, -1633, 74, -2362, -46, 0));
        FFASpawns.add(new Location(w, -1587, 84, -2302, 44, 0));
        FFASpawns.add(new Location(w, -1573, 85, -2301, -41, 0));
        FFASpawns.add(new Location(w, -1573, 85, -2319, -128, 0));
        FFASpawns.add(new Location(w, -1588, 85, -2316, 136, 0));
        FFASpawns.add(new Location(w, -1579, 74, -2386, 1, 0));
        FFASpawns.add(new Location(w, -1567, 76, -2310, -49, 0));
        FFASpawns.add(new Location(w, -1590, 77, -2315, 118, 0));
        FFASpawns.add(new Location(w, -1586, 77, -2301, 25, 0));
        FFASpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        FFASpawns.add(new Location(w, -1580, 94, -2299, 97, 0));
        FFASpawns.add(new Location(w, -1581, 99, -2322, -87, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack STONE_SHOVEL = new ItemStack(Material.STONE_SPADE, 1);
        ItemStack IRON_PICK = new ItemStack(Material.IRON_PICKAXE, 1);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICK);
        i.setItem(3, STONE_SHOVEL);
        i.setItem(4, STEAK);
        i.setItem(5, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void spireTurret(PlayerInteractEvent eve) {
        Player player = eve.getPlayer();
        ItemStack item = eve.getItem();
        Action action = eve.getAction();
        Location location = player.getLocation();
        Block block = location.getBlock();
        Block block2 = block.getRelative(BlockFace.DOWN, 2);
        Material material = block.getType();

        if (isInsideRegion(location)) {

            if (material == Material.SPONGE && action == Action.RIGHT_CLICK_AIR) {

                if (item.getType() == Material.IRON_SWORD) {

                    player.launchProjectile(Snowball.class);

                }
            }
        }
    }

    @EventHandler
    public void sponge(BlockBreakEvent event) {
        if (!event.getBlock().getWorld().getName().equals(getName())) return;
        if (event.getBlock().getType() == Material.SPONGE) {
            event.setCancelled(true);
            event.getBlock().setType(Material.GOLD_BLOCK);
        }
    }

    @EventHandler
    public void explode(EntityExplodeEvent event) {
        if (!event.getLocation().getWorld().getName().equals(getName())) return;
        for (Block b : event.blockList()) {
            if (b.getType() == Material.SPONGE) {
                event.blockList().remove(b);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void explodingArrow(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        World w = projectile.getWorld();
        Location hit = projectile.getLocation();
        if (!w.getName().equals(getName())) return;

        if (projectile instanceof Arrow) {
            Arrow arrow = (Arrow) projectile;
            ProjectileSource shooter = arrow.getShooter();

            if (shooter instanceof Player) {
                Player player = (Player) shooter;
                Location location = player.getLocation();
                Block block = location.getBlock();
                Material material = block.getRelative(BlockFace.DOWN, 2).getType();
                Material item = player.getItemInHand().getType();

                if (item == Material.BOW && material == Material.SPONGE) {
                    w.createExplosion(hit, 2);
                    Bukkit.getWorld(getName()).playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);

                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void superEgg(PlayerEggThrowEvent event) {
        Egg egg = event.getEgg();
        World world = egg.getWorld();
        Location location = egg.getLocation();
        // basic start and variables
        if (world.getName().equals(getName())) {
            event.setHatching(true);
            world.createExplosion(location, 25);

            int strikes = 0;
            while (strikes < 20) {
                strikes++;
                // This causes a counting affect to count 20 times by adding 1
                // to 0 till the number 20 for 20 lightning bolts
                world.strikeLightning(location);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void superBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material material = block.getType();
        Location location = block.getLocation();

        if (location.getWorld().getName().equals(getName()) && material == Material.LAPIS_ORE) {

            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2400, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2400, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 5));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2400, 4));
            player.playSound(location, Sound.LEVEL_UP, 50, 50);

            Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.GOLD + " now has the power!");

        }
    }
}
