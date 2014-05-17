package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Mantle extends BattleMap implements Listener {

    public Mantle() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setAutoSpawnProtection(3);
        disableDrops(new Material[]{Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.WOOL});
    }

    String name = "mantle";
    String fullName = "The Mantle";
    String[] creators = {"__R3"};
    Gamemode[] modes = {Gamemode.CTF};

    public void readyTDMSpawns() {

        redSpawns.add(new Location(w, -34, 84, 169));
        blueSpawns.add(new Location(w, -34, 84, -29));

        Location redFlag = new Location(w, -35, 86, 121);
        Location blueFlag = new Location(w, -35, 86, 17);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -16, 84, 128));
        FFASpawns.add(new Location(w, -50, 84, 11));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
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
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(10, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 28;
    public int y1 = 61;
    public int z1 = -42;

    //Bottom right corner.
    public int x2 = -86;
    public int y2 = 117;
    public int z2 = 185;

    @EventHandler(ignoreCancelled = false)
    public void onWoolDrop(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(name)) {
            event.setCancelled(true);
            if (event.getBlock().getType() == Material.WOOL) {
                event.getBlock().getDrops().clear();
            }
        }
    }

}
