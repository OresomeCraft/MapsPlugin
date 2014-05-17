package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Gratianus extends BattleMap implements Listener {

    public Gratianus() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.IRON_SWORD, Material.BOW, Material.ARROW});
        lockTime("day");
    }

    String name = "gratianus";
    String fullName = "Gratianus";
    String[] creators = {"BlueVortexed"};
    Gamemode[] modes = {Gamemode.FFA, Gamemode.KOTH, Gamemode.INFECTION, Gamemode.LMS, Gamemode.LTS};

    public void readyTDMSpawns() {

        redSpawns.add(new Location(w, 0, 58, -59, -0, 0));
        blueSpawns.add(new Location(w, 0, 58, 59, -180, 0));

        setKoTHMonument(new Location(w, 0, 59, 0));

    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 0, 58, -59, -0, 0);
        Location blueSpawn = new Location(w, 0, 58, 59, -180, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 0.4, 88, -68.34, -0, 0));
        FFASpawns.add(new Location(w, 0, 88, 67, 180, 0));
        FFASpawns.add(new Location(w, 69, 88, 0, 90, 0));
        FFASpawns.add(new Location(w, -68.25, 88, 0, -88, 0));
        FFASpawns.add(new Location(w, -60, 58, 0, -90, 0));
        FFASpawns.add(new Location(w, 60, 58, 0, 90, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 32);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH);
        i.setItem(4, STEAK);
        i.setItem(10, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 185;
    public int z1 = -105;

    //Bottom right corner.
    public int x2 = 121;
    public int y2 = 34;
    public int z2 = 107;

}
