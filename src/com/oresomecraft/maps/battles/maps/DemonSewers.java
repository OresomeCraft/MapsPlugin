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
public class DemonSewers extends BattleMap implements Listener {

    public DemonSewers() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    // Map details
    String name = "demonsewers";
    String fullName = "Demon Sewers";
    String creators[] = {"Zdav2002", "xBlazingxFirex1", "shavahn2003"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 139, 38, -31, 1, 0));
        redSpawns.add(new Location(w, -9, 110, -20, 0, 0));
        redSpawns.add(new Location(w, 21, 105, -13, 0, 0));
        redSpawns.add(new Location(w, 4, 106, -41, 0, 0));

        blueSpawns.add(new Location(w, 19, 49, -18, 0, 0));
        blueSpawns.add(new Location(w, 46, 49, 8, 1, 0));
        blueSpawns.add(new Location(w, 46, 49, -19, 1, 0));
        blueSpawns.add(new Location(w, 4, 49, 13, 2, 0));
        blueSpawns.add(new Location(w, 65, 42, -9, 1, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 19, 49, -18, 0, 0));
        FFASpawns.add(new Location(w, 46, 49, 8, 1, 0));
        FFASpawns.add(new Location(w, 41, 42, -38, 3, 0));
        
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

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
        p.setItem(1, Material.BOW, 1);
        p.setItem(2, Material.BREAD, 5);
        p.setItem(9, Material.ARROW, 5);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -19;
    public int y1 = 58;
    public int z1 = 33;

    // Bottom right corner.
    public int x2 = 177;
    public int y2 = 27;
    public int z2 = -79;

}
