package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@MapConfig
public class Nuketown extends BattleMap implements Listener {

    public Nuketown() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.LEATHER_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
    }

    String name = "nuketown";
    String fullName = "NukeTown";
    String[] creators = {"Htban", "proportion", "reub_youtube"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 1, 7, 41, 0, 0);
        Location blueSpawn = new Location(w, 2, 7, 154, -179, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, -19, 7, 89, -90, 0));
        blueSpawns.add(new Location(w, 25, 11, 137, 89, 0));
        redSpawns.add(new Location(w, -19, 16, 61, -54, 0));
        blueSpawns.add(new Location(w, 4, 7, 84, 12, 0));
        redSpawns.add(new Location(w, 24, 7, 97, 114, 0));
        blueSpawns.add(new Location(w, -50, 7, 138, -65, 0));
        redSpawns.add(new Location(w, -29, 7, 143, -89, 0));
        blueSpawns.add(new Location(w, -5, 8.5, 141, -1, 0));
        redSpawns.add(new Location(w, 2, 16, 168, 179, 0));
        blueSpawns.add(new Location(w, -27, 7, 113, -83, 0));
        redSpawns.add(new Location(w, 23, 7, 60, 95, 0));
        blueSpawns.add(new Location(w, 21, 17, 91, 89, 0));
        redSpawns.add(new Location(w, -40, 7, 45, 40, 0));
        blueSpawns.add(new Location(w, 5, 12, 131, 178, 0));
        blueSpawns.add(new Location(w, -22, 12, 91, -110, 0));
        redSpawns.add(new Location(w, 54, 7, 128, 111, 0));
        blueSpawns.add(new Location(w, -2, 11, 156, 179, 0));
        redSpawns.add(new Location(w, 21, 11, 115, 42, 0));
        blueSpawns.add(new Location(w, 20, 16.5, 138, 119, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 1, 7, 41, 0, 0);
        Location blueSpawn = new Location(w, 2, 7, 154, -179, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -19, 7, 89, -90, 0));
        FFASpawns.add(new Location(w, 25, 11, 137, 89, 0));
        FFASpawns.add(new Location(w, -19, 16, 61, -54, 0));
        FFASpawns.add(new Location(w, 4, 7, 84, 12, 0));
        FFASpawns.add(new Location(w, 24, 7, 97, 114, 0));
        FFASpawns.add(new Location(w, -50, 7, 138, -65, 0));
        FFASpawns.add(new Location(w, -29, 7, 143, -89, 0));
        FFASpawns.add(new Location(w, -5, 8.5, 141, -1, 0));
        FFASpawns.add(new Location(w, 2, 16, 168, 179, 0));
        FFASpawns.add(new Location(w, -27, 7, 113, -83, 0));
        FFASpawns.add(new Location(w, 23, 7, 60, 95, 0));
        FFASpawns.add(new Location(w, 21, 17, 91, 89, 0));
        FFASpawns.add(new Location(w, -40, 7, 45, 40, 0));
        FFASpawns.add(new Location(w, 5, 12, 131, 178, 0));
        FFASpawns.add(new Location(w, -22, 12, 91, -110, 0));
        FFASpawns.add(new Location(w, 54, 7, 128, 111, 0));
        FFASpawns.add(new Location(w, -2, 11, 156, 179, 0));
        FFASpawns.add(new Location(w, 21, 11, 115, 42, 0));
        FFASpawns.add(new Location(w, 20, 16.5, 138, 119, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(10, ARROWS);

    }

    public int x1 = -60;
    public int y1 = 3;
    public int z1 = 1;
    public int x2 = 64;
    public int y2 = 56;
    public int z2 = 194;

    @EventHandler
    public void arrowTrail(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (getArena().equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);
            }
        }
    }

    @EventHandler
    public void arrowCollide(ProjectileHitEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getEntity();
                //Location loc1 = a.getLocation();
                List<Entity> nearby = arrow.getNearbyEntities(1, 1, 1);
                for (Entity entity : nearby) {
                    if (entity instanceof Player) {
                        //Player p = (Player) ent;
                        entity.playEffect(EntityEffect.WOLF_SMOKE);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            Player player = event.getPlayer();
            ItemStack itemStack = player.getItemInHand();
            Material material = itemStack.getType();
            if (material == Material.BOW) {
                if (event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1, 50);
                }
            }
        }
    }
}
