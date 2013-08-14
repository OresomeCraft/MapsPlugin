package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Mantle extends BattleMap implements IBattleMap, Listener {

    public Mantle() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "mantle";
    String fullName = "The Mantle";
    String creators = "R3creat3, eli12310987, chillhill3, MiCkEyMiCE and FaazM";
    Gamemode[] modes = {Gamemode.CTF};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        redSpawns.add(new Location(w, -16, 84, 128));
        blueSpawns.add(new Location(w, -50, 84, 11));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

        Location redFlag = new Location(w, -35, 86, 121);
        Location blueFlag = new Location(w, -35, 86, 17);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -16, 84, 128));
        FFASpawns.add(new Location(w, -50, 84, 11));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

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
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -85;
    public int y1 = 65;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 15;
    public int y2 = 108;
    public int z2 = 138;

}
