package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class GrandCanyon extends BattleMap implements Listener {

    public GrandCanyon() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setAutoSpawnProtection(5);
        disableDrops(new Material[]{Material.ARROW, Material.BOW, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_BOOTS, Material.STONE_SWORD});
    }

    String name = "grandcanyon";
    String fullName = "Grand Canyon";
    String[] creators = {"jslsa", "Corrigan1998"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {

        redSpawns.add(new Location(w, -50, 99, 71, -90, 0));
        redSpawns.add(new Location(w, -54, 92, 15, -90, 0));

        blueSpawns.add(new Location(w, 45, 100, 78, 90, 0));
        blueSpawns.add(new Location(w, 57, 99, 116, 90, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -4, 87, -4, -4, 0));
        FFASpawns.add(new Location(w, -45, 102, 113, -160, 0));
        FFASpawns.add(new Location(w, 54, 98, -17, 31, 0));
        FFASpawns.add(new Location(w, -50, 99, 71, -90, 0));
        FFASpawns.add(new Location(w, -54, 92, 15, -90, 0));
        FFASpawns.add(new Location(w, 45, 100, 78, 90, 0));
        FFASpawns.add(new Location(w, 57, 99, 116, 90, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
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
        i.setItem(2, HEALTH);
        i.setItem(3, STEAK);
        i.setItem(15, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -69;
    public int y1 = 140;
    public int z1 = -45;

    //Bottom right corner.
    public int x2 = 70;
    public int y2 = 26;
    public int z2 = 163;

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFly(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals(name)) {
            if (player.getGameMode() != GameMode.CREATIVE) {
                event.setCancelled(true);
                player.setAllowFlight(false);
                player.setFlying(false);
                player.setVelocity(player.getLocation().getDirection().multiply(1.6D).setY(1.0));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                player.setFallDistance(0);
            }
        }
    }

}
