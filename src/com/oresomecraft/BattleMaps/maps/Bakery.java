package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.World;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Bakery extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Bakery() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "bakery";
    String fullName = "The Bakery";
    String creators = "R3creat3, DynaDavidson, _Moist, FaazM, callumary and ShaunDepro97";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.INFECTION};

    public void readyTDMSpawns() {

        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, -81, 70, 26));
        redSpawns.add(new Location(w, 65, 70, 27));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -81, 70, 26));
        FFASpawns.add(new Location(w, 65, 70, 27));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack IRON_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack STONE_SWORD = new ItemStack(Material.CAKE, 1);
            STONE_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);

            i.setItem(0, STONE_SWORD);
            i.setItem(1, BOW);
            i.setItem(3, HEALTH);
            i.setItem(11, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -50;
    public int y1 = 50;
    public int z1 = -76;

    //Bottom right corner.
    public int x2 = 13;
    public int y2 = 119;
    public int z2 = -4;

}
