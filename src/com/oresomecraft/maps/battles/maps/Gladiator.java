package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig(
        name = "gladiator",
        fullName = "Gladiator",
        creators = {"eddie017"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA, Gamemode.KOTH}
)
@Region(
        x1 = 166,
        y1 = 155,
        z1 = -110,
        x2 = 356,
        y2 = 60,
        z2 = 72
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.ARROW, Material.FISHING_ROD, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.WOOL}
)
public class Gladiator extends BattleMap implements Listener {

    public Gladiator() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 207, 101, -31, -90, 0);
        Location blueSpawn = new Location(w, 289, 101, -31, 90, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 207, 101, -31, -90, 0));
        redSpawns.add(new Location(w, 224, 92, -27, -90, 0));
        redSpawns.add(new Location(w, 224, 92, -35, -90, 0));
        redSpawns.add(new Location(w, 197, 93, -31, -90, 0));
        redSpawns.add(new Location(w, 230, 92, -12, -135, 0));
        redSpawns.add(new Location(w, 229, 92, -49, -45, 0));
        redSpawns.add(new Location(w, 231, 110, -30, -90, 0));
        redSpawns.add(new Location(w, 222, 100, -5, -135, 0));


        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 289, 101, -31, 90, 0));
        blueSpawns.add(new Location(w, 272, 92, -28, 90, 0));
        blueSpawns.add(new Location(w, 272, 92, -34, 90, 0));
        blueSpawns.add(new Location(w, 299, 93, -32, 90, 0));
        blueSpawns.add(new Location(w, 266, 92, -48, 45, 0));
        blueSpawns.add(new Location(w, 266, 92, -12, 135, 0));
        blueSpawns.add(new Location(w, 265, 110, -30, 90, 0));
        blueSpawns.add(new Location(w, 273, 100, -56, 45, 0));

        setKoTHMonument(new Location(w, 248, 125, -32));

    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 248, 101, -73, 0, 0));
        FFASpawns.add(new Location(w, 248, 101, 9, 180, 0));
        FFASpawns.add(new Location(w, 208, 101, -32, -90, 0));
        FFASpawns.add(new Location(w, 288, 101, -32, 90, 0));
        FFASpawns.add(new Location(w, 272, 92, -30, 90, 0));
        FFASpawns.add(new Location(w, 272, 92, -34, 90, 0));
        FFASpawns.add(new Location(w, 300, 93, -32, 90, 0));
        FFASpawns.add(new Location(w, 267, 92, -14, 135, 0));
        FFASpawns.add(new Location(w, 265, 92, -50, 45, 0));
        FFASpawns.add(new Location(w, 248, 93, 19, -180, 0));
        FFASpawns.add(new Location(w, 248, 93, -83, 0, 0));
        FFASpawns.add(new Location(w, 251, 92, -56, 0, 0));
        FFASpawns.add(new Location(w, 245, 92, -56, 0, 0));
        FFASpawns.add(new Location(w, 248, 92, -31, -125, 0));
        FFASpawns.add(new Location(w, 245, 92, -8, -180, 0));
        FFASpawns.add(new Location(w, 251, 92, -8, -180, 0));
        FFASpawns.add(new Location(w, 224, 92, -35, -90, 0));
        FFASpawns.add(new Location(w, 224, 92, -29, -90, 0));
        FFASpawns.add(new Location(w, 229, 92, -14, -135, 0));
        FFASpawns.add(new Location(w, 230, 92, -51, -45, 0));
        FFASpawns.add(new Location(w, 196, 93, -32, -90, 0));
        FFASpawns.add(new Location(w, 274, 100, -58, 45, 0));
        FFASpawns.add(new Location(w, 222, 100, -5, -135, 0));
        FFASpawns.add(new Location(w, 252, 104, -36, 45, 0));
        FFASpawns.add(new Location(w, 252, 104, -28, 135, 0));
        FFASpawns.add(new Location(w, 244, 104, -28, -135, 0));
        FFASpawns.add(new Location(w, 244, 104, -36, -45, 0));
        FFASpawns.add(new Location(w, 252, 109, -36, 45, 0));
        FFASpawns.add(new Location(w, 252, 109, -28, 135, 0));
        FFASpawns.add(new Location(w, 244, 109, -28, -135, 0));
        FFASpawns.add(new Location(w, 244, 109, -36, -45, 0));
        FFASpawns.add(new Location(w, 248, 108, -23, 180, 0));
        FFASpawns.add(new Location(w, 248, 108, -41, 0, 0));
        FFASpawns.add(new Location(w, 265, 110, -30, 90, 0));
        FFASpawns.add(new Location(w, 231, 110, -32, -90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 24);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, ARROWS);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void fishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Material material = itemStack.getType();
        Location location = player.getLocation();
        Location bobber = event.getHook().getLocation();

        if (location.getWorld().getName().equals(getName())) {

            if (material == Material.FISHING_ROD) {

                if (event.getHook().getVelocity().getY() < 0.02 && isLocationNearBlock(bobber)) {
                    player.launchProjectile(Snowball.class);
                }
            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void grapple(ProjectileHitEvent event) {
        Entity proj = event.getEntity();
        Location hit = proj.getLocation();

        if (!event.getEntity().getWorld().getName().equals(getName())) return;

        if (proj instanceof Snowball) {
            Snowball fish = (Snowball) proj;
            ProjectileSource shooter = fish.getShooter();

            if (shooter instanceof Player) {
                Player player = (Player) shooter;
                Location location = player.getLocation();
                ItemStack itemStack = player.getItemInHand();
                Material material = itemStack.getType();

                if (material == Material.FISHING_ROD) {

                    player.setFallDistance(0);
                    player.playSound(location, Sound.ARROW_HIT, 1, 1);

                    int hitx = hit.getBlockX();
                    int hity = hit.getBlockY();
                    int hitz = hit.getBlockZ();
                    int locx = location.getBlockX();
                    int locy = location.getBlockY();
                    int locz = location.getBlockZ();
                    double co[] = new double[3];

                    if (hitx > locx) {
                        co[0] = 1.2;
                    } else if (hitx < locx) {
                        co[0] = -1.2;
                    } else if (hitx == locx) {
                        co[0] = 0;
                    }

                    if (hity > locy) {
                        co[1] = 1.4;
                    } else if (hity < locy) {
                        co[1] = -0.8;
                    } else if (hity == locy) {
                        co[1] = 0;
                    }

                    if (hitz > locz) {
                        co[2] = 1.2;
                    } else if (hitz < locz) {
                        co[2] = -1.2;
                    } else if (hitz == locz) {
                        co[2] = 0;
                    }

                    player.setVelocity(new Vector(co[0], co[1] / 1.25, co[2]));

                }
            }
        }
    }
}
