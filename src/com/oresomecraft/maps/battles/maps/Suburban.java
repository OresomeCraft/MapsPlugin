package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "suburban",
        fullName = "Suburban Complex",
        creators = {"__R3", "DanShrdr", "kalikakitty"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = 525,
        y1 = 0,
        z1 = 578,
        x2 = -44,
        y2 = 232,
        z2 = 1136
)
@Attributes(
        mergeTools = true
)
public class Suburban extends BattleMap implements Listener {

    public Suburban() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, 360, 40, -482));
        blueSpawns.add(new Location(w, 364, 41, -579));
        blueSpawns.add(new Location(w, 266, 41, -487));
        blueSpawns.add(new Location(w, 337, 47, -410));
        blueSpawns.add(new Location(w, 364, 54, -529));

        redSpawns.add(new Location(w, 364, 41, -579));
        redSpawns.add(new Location(w, 266, 41, -487));
        redSpawns.add(new Location(w, 337, 47, -410));
        redSpawns.add(new Location(w, 437, 58, -482));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 360, 40, -482));
        FFASpawns.add(new Location(w, 361, 52, -482));
        FFASpawns.add(new Location(w, 364, 41, -579));
        FFASpawns.add(new Location(w, 310, 47, -539));
        FFASpawns.add(new Location(w, 266, 41, -487));
        FFASpawns.add(new Location(w, 366, 41, -387));
        FFASpawns.add(new Location(w, 337, 47, -410));
        FFASpawns.add(new Location(w, 326, 53, -448));
        FFASpawns.add(new Location(w, 364, 54, -529));
        FFASpawns.add(new Location(w, 437, 58, -482));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack PICK = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE = new ItemStack(Material.STONE, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, PICK);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH_POTION);
        i.setItem(11, ARROWS);
        i.setItem(8, STONE);
    }

    @EventHandler
    public void breakListener(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (loc.getWorld().getName().equals(getName())) {

            if (event.getBlock().getLocation().distance(new Location(event.getBlock().getWorld(), 363, 54, -483)) <= 10) {
                if (getMode() == Gamemode.KOTH)
                    event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void placeListener(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if (loc.getWorld().getName().equals(getName())) {

            if (event.getBlock().getLocation().distance(new Location(event.getBlock().getWorld(), 363, 54, -483)) <= 10) {
                if (getMode() == Gamemode.KOTH)
                    event.setCancelled(true);
            }
        }
    }

}
