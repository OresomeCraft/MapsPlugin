package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Paradise extends BattleMap implements Listener {

    public Paradise() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
        setAllowBuild(false);
    }

    // Map details
    String name = "modern";
    String fullName = "Paradise";
    String[] creators = {"SuperDuckFace", "meganlovesmusic", "ninsai"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -102, 108, 103));
        blueSpawns.add(new Location(w, 51, 111, -83));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -114, 108, -89, 54, 0));
        FFASpawns.add(new Location(w, -145, 108, -41, 115, 0));
        FFASpawns.add(new Location(w, -117, 108, -83, -90, 0));
        FFASpawns.add(new Location(w, -102, 108, 103, 0, 0));
        FFASpawns.add(new Location(w, -147, 108, -41, -115, 0));
        FFASpawns.add(new Location(w, -137, 108, 48, 0, 0));
        FFASpawns.add(new Location(w, -48, 106, 107, 90, 0));
        FFASpawns.add(new Location(w, 2, 108, 101, 128, 0));
        FFASpawns.add(new Location(w, 50, 112, 60, 90, 0));
        FFASpawns.add(new Location(w, 57, 109, 26, 176, 0));
        FFASpawns.add(new Location(w, 45, 111, -80, 20, 0));
        FFASpawns.add(new Location(w, 13, 117, -146, -70, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 32);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack PEARL = new ItemStack(Material.ENDER_PEARL, 2);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH_POTION);
        i.setItem(3, STEAK);
        i.setItem(11, ARROW);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -186;
    public int y1 = 189;
    public int z1 = -163;

    // Bottom right corner.
    public int x2 = 156;
    public int y2 = 38;
    public int z2 = 151;

}
