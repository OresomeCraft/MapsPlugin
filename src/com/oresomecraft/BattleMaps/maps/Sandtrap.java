package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.api.InvUtils;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.World;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Sandtrap extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Sandtrap() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setTDMTime(5);
    }

    String name = "sandtrap";
    String fullName = "Sand Trap";
    String creators = "R3creat3 and danielschroeder";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, -18, 100, -30));
        redSpawns.add(new Location(w, -18, 95, -11));
        redSpawns.add(new Location(w, -18, 81, -31));
        redSpawns.add(new Location(w, -18, 67, -11));
        blueSpawns.add(new Location(w, -18, 100, -68));
        blueSpawns.add(new Location(w, -18, 86, -86));
        blueSpawns.add(new Location(w, -18, 72, -68));
        blueSpawns.add(new Location(w, -18, 67, -87));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -18, 100, -30));
        FFASpawns.add(new Location(w, -18, 95, -11));
        FFASpawns.add(new Location(w, -18, 81, -31));
        FFASpawns.add(new Location(w, -18, 67, -11));
        FFASpawns.add(new Location(w, -18, 100, -68));
        FFASpawns.add(new Location(w, -18, 86, -86));
        FFASpawns.add(new Location(w, -18, 72, -68));
        FFASpawns.add(new Location(w, -18, 67, -87));
        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack STONE_SWORD = new ItemStack(Material.DIAMOND_SPADE, 1);

            ItemStack GLASS = new ItemStack(Material.GLASS, 22);
            ItemStack SAND = new ItemStack(Material.SAND, 64);
            ItemStack TNT = new ItemStack(Material.TNT, 1);
            ItemStack TINDER = new ItemStack(Material.FLINT_AND_STEEL, 1);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

            p.getInventory().setBoots(LEATHER_BOOTS);
            p.getInventory().setLeggings(LEATHER_PANTS);
            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, STONE_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH);
            i.setItem(15, ARROWS);
            i.setItem(4, GLASS);
            i.setItem(5, SAND);
            i.setItem(6, TNT);
            i.setItem(7, TINDER);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 3;
    public int y1 = 63;
    public int z1 = -90;

    //Bottom right corner.
    public int x2 = -41;
    public int y2 = 130;
    public int z2 = -10;

    @EventHandler
    public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player p = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        for (ItemStack item : drops) {
            Material mat = item.getType();

            if (mat == Material.DIAMOND_SPADE) {

                item.setType(Material.AIR);
            }
        }
    }
}
