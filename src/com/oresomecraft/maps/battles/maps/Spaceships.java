package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig
public class Spaceships extends BattleMap implements Listener {

    public Spaceships() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(15);
        setBuildLimit(72);
        setAutoSpawnProtection(5);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.ARROW, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_BOOTS, Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.LAVA_BUCKET});
    }

    String name = "spaceships";
    String fullName = "SpaceShips";
    String[] creators = {"sampighere", "zachoz", "__R3"};
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        Location blueSpawn = new Location(w, 142, 43, -80, 0, 0);
        Location redSpawn = new Location(w, 210, 43, -80, 0, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location blueSpawn = new Location(w, 142, 43, -80, 0, 0);
        Location redSpawn = new Location(w, 210, 43, -80, 0, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, new ItemStack(Material.DIAMOND_SWORD, 1));
        i.setItem(1, new ItemStack(Material.BOW, 1));
        i.setItem(2, new ItemStack(Material.DIAMOND_PICKAXE, 1));
        i.setItem(3, new ItemStack(Material.COOKED_BEEF, 3));
        i.setItem(4, new ItemStack(Material.GOLDEN_APPLE, 2));
        i.setItem(5, new ItemStack(Material.LOG, 64));
        i.setItem(8, new ItemStack(Material.ENDER_PEARL, 1));
        i.setItem(9, new ItemStack(Material.ARROW, 64));
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 106;
    public int y1 = 110;
    public int z1 = 34;

    //Bottom right corner.
    public int x2 = 246;
    public int y2 = 0;
    public int z2 = -191;

    @EventHandler
    public void explode(EntityExplodeEvent event) {
        if (!event.getLocation().getWorld().getName().equals(name)) {
            event.setYield(event.getYield() * 2);
        }
    }
}
