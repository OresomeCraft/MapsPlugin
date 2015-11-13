package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "shootout",
        fullName = "Shoot Out",
        creators = {"Mr_Jaskirat", "SereneMango"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION, Gamemode.LMS, Gamemode.LTS}
)
@Region(
        x1 = 1486,
        y1 = 87,
        z1 = 437,
        x2 = 1319,
        y2 = -4,
        z2 = 666
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.ARROW, Material.BOW, Material.WOOD_SWORD, Material.LEATHER_HELMET}
)
public class ShootOut extends BattleMap implements Listener {

    public ShootOut() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 1451, 13, 545, 91, 0));
        redSpawns.add(new Location(w, 1436, 15, 557, 87, 0));
        redSpawns.add(new Location(w, 1453, 13, 526, 87, 0));

        blueSpawns.add(new Location(w, 1354, 12, 547, -92, 0));
        blueSpawns.add(new Location(w, 1365, 10, 524, -88, 0));
        blueSpawns.add(new Location(w, 1372, 12, 572, -98, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 1451, 13, 545, 91, 0));
        FFASpawns.add(new Location(w, 1354, 12, 547, -92, 0));
        FFASpawns.add(new Location(w, 1394, 17, 593, -177, 0));
        FFASpawns.add(new Location(w, 1415, 12, 480, 8, 0));
        FFASpawns.add(new Location(w, 1399, 11, 517, -36, 0));
        FFASpawns.add(new Location(w, 1415, 12, 539, 78, 0));
        FFASpawns.add(new Location(w, 1385, 11, 556, -131, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack JUMP = new ItemStack(Material.FIREWORK, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD, 1);

        pl.getInventory().setHelmet(LEATHER_HELMET);
        setColouredArmorAccordingToTeam(p);

        i.setItem(0, WOOD_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, JUMP);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
    }

    @EventHandler
    public void arrowAway(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        Location location = projectile.getLocation();
        if (location.getWorld().getName().equals(getName())) {
            if (projectile instanceof Arrow) {
                Arrow arrow = (Arrow) projectile;
                arrow.remove();
            }
        }
    }

    @EventHandler
    public void onPlayDeath(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;

        if (event.getEntity().getType() == EntityType.PLAYER) {
            if (event.getDamager().getType() == EntityType.PLAYER) {
                if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    Player damager = (Player) event.getDamager();
                    damager.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                }

            }
        }
    }
}
