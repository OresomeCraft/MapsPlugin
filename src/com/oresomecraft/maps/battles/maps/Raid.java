package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Raid extends BattleMap implements Listener {

    public Raid() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.ARROW, Material.FISHING_ROD, Material.IRON_CHESTPLATE, Material.BOW, Material.IRON_SWORD, Material.IRON_SWORD, Material.BOW, Material.GOLD_BOOTS, Material.CHAINMAIL_LEGGINGS,
                Material.IRON_CHESTPLATE, Material.LEATHER_HELMET, Material.ARROW, Material.FISHING_ROD, Material.ENDER_PEARL, Material.WOOL});
        disablePearlDamage(true);
    }

    String name = "raid";
    String fullName = "Raid";
    String[] creators = {"ShaunDepro97"};
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 5, 68, -5, 1, 0);
        Location blueSpawn = new Location(w, -81, 69, -2, 3, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -8, 66, -10, 1, 0));
        redSpawns.add(new Location(w, -19, 75, -12, 1, 0));
        redSpawns.add(new Location(w, 13, 68, 17, 1, 0));
        redSpawns.add(new Location(w, 31, 75, 1, 1, 0));
        redSpawns.add(new Location(w, 42, 75, -10, 1, 0));
        redSpawns.add(new Location(w, 22, 75, -33, 1, 0));
        redSpawns.add(new Location(w, 37, 78, -41, 1, 0));
        redSpawns.add(new Location(w, 28, 85, -65, 1, 0));
        redSpawns.add(new Location(w, 47, 82, -29, 1, 0));
        redSpawns.add(new Location(w, 3, 74, 61, 1, 0));
        redSpawns.add(new Location(w, -1, 66, 5, 1, 0));
        redSpawns.add(new Location(w, -1, 66, -22, 1, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -79, 66, -6, 3, 0));
        blueSpawns.add(new Location(w, -83, 66, -6, 3, 0));
        blueSpawns.add(new Location(w, -79, 65, -17, 3, 0));
        blueSpawns.add(new Location(w, -83, 65, -6, 3, 0));
        blueSpawns.add(new Location(w, -81, 65, -22, 3, 0));
        blueSpawns.add(new Location(w, -81, 66, -32, 3, 0));
        blueSpawns.add(new Location(w, -81, 62, -6, 3, 0));
        blueSpawns.add(new Location(w, -81, 62, -18, 3, 0));
        blueSpawns.add(new Location(w, -81, 62, -21, 3, 0));
        blueSpawns.add(new Location(w, -81, 62, -24, 3, 0));
        blueSpawns.add(new Location(w, -81, 62, -27, 3, 0));
        blueSpawns.add(new Location(w, -81, 62, -30, 3, 0));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 5, 68, -5, 1, 0);
        Location blueSpawn = new Location(w, -81, 69, -2, 3, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 5, 68, -5, 1, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack COOKED_FISH = new ItemStack(Material.COOKED_FISH, 2);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack CHAINMAIL_PANTS = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        ItemStack GOLD_BOOTS = new ItemStack(Material.GOLD_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

        p.getInventory().setBoots(GOLD_BOOTS);
        p.getInventory().setLeggings(CHAINMAIL_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, COOKED_FISH);
        i.setItem(4, HEALTH_POTION);
        i.setItem(28, ARROWS);

        if (p.getTeamType() == Team.TDM_BLUE) p.setItem(5, Material.ENDER_PEARL, 2);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -128;
    public int y1 = 1;
    public int z1 = -128;

    //Bottom right corner.
    public int x2 = 128;
    public int y2 = 256;
    public int z2 = 128;

}
