package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.api.InvUtils;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Raid extends BattleMap implements IBattleMap, Listener {

    public Raid() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "raid";
    String fullName = "Raid";
    String creators = "ShaunDepro97";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

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

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 5, 68, -5, 1, 0);
        Location blueSpawn = new Location(w, -81, 69, -2, 3, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 5, 68, -5, 1, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack COOKED_FISH = new ItemStack(Material.COOKED_FISH, 2);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack CHAINMAIL_PANTS = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(CHAINMAIL_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(3, COOKED_FISH);
            i.setItem(4, HEALTH_POTION);
            i.setItem(28, ARROWS);

        }
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

    @EventHandler
    public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player p = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        for (ItemStack item : drops) {
            Material mat = item.getType();

            if (mat == Material.IRON_SWORD
                    || mat == Material.BOW
                    || mat == Material.IRON_BOOTS
                    || mat == Material.CHAINMAIL_LEGGINGS
                    || mat == Material.IRON_CHESTPLATE
                    || mat == Material.LEATHER_HELMET
                    || mat == Material.ARROW
                    || mat == Material.FISHING_ROD
                    || mat == Material.ENDER_PEARL) {

                item.setType(Material.AIR);
            }
        }
    }

}
