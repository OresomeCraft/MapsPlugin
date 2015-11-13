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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "mantle",
        fullName = "The Mantle",
        creators = {"Heartist"},
        gamemodes = {Gamemode.CTF}
)
@Region(
        x1 = 28,
        y1 = 61,
        z1 = -42,
        x2 = -86,
        y2 = 117,
        z2 = 185
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 10,
        autoSpawnProtection = true,
        spawnProtectionDuration = 3,
        disabledDrops = {Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.WOOL}
)
public class Mantle extends BattleMap implements Listener {

    public Mantle() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {

        redSpawns.add(new Location(w, -34, 84, 169));
        blueSpawns.add(new Location(w, -34, 84, -29));

        Location redFlag = new Location(w, -35, 86, 121);
        Location blueFlag = new Location(w, -35, 86, 17);
        setCTFFlags(getName(), redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -16, 84, 128));
        FFASpawns.add(new Location(w, -50, 84, 11));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
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
        i.setItem(10, ARROWS);

    }

    @EventHandler(ignoreCancelled = false)
    public void onWoolDrop(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(getName())) {
            event.setCancelled(true);
            if (event.getBlock().getType() == Material.WOOL) {
                event.getBlock().getDrops().clear();
            }
        }
    }

}
