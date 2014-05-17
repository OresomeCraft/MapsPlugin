package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class Dusk extends BattleMap implements Listener {

    public Dusk() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        lockTime("night");
        disableDrops(new Material[]{Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.IRON_SWORD, Material.STONE_SWORD, Material.GOLD_LEGGINGS, Material.DIAMOND_BOOTS, Material.LEATHER_HELMET});
    }

    String name = "dusk";
    String fullName = "Darkness of Dusk";
    String[] creators = {"xannallax33", "dinner1111", "pepsidawg00", "__R3"};
    Gamemode[] modes = {Gamemode.INFECTION};

    //Tdm isn't enabled on this, don't need to do spawns.
    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 62, 6, 23));
        FFASpawns.add(new Location(w, 62, 6, 149));
        FFASpawns.add(new Location(w, -46, 6, 147));
        FFASpawns.add(new Location(w, -48, 6, 44));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        ItemStack LUCKY_CANE = new ItemStack(Material.SUGAR_CANE, 1);
        ItemMeta caneMeta = LUCKY_CANE.getItemMeta();
        caneMeta.setDisplayName(ChatColor.BLUE + "Lucky Cane");

        List<String> caneLore = new ArrayList<String>();
        caneLore.add(org.bukkit.ChatColor.BLUE + "What will you get? Who knows!");
        caneMeta.setLore(caneLore);
        LUCKY_CANE.setItemMeta(caneMeta);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

        p.getInventory().setBoots(DIAMOND_BOOTS);
        p.getInventory().setLeggings(GOLD_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(8, LUCKY_CANE);
        i.setItem(9, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -103;
    public int y1 = 52;
    public int z1 = -22;
    //Bottom right corner.
    public int x2 = 101;
    public int y2 = -7;
    public int z2 = 199;

    @EventHandler
    public void onVirtualLuck(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType() == Material.SUGAR_CANE) {
                ItemStack LUCKY_CANE = new ItemStack(Material.SUGAR_CANE, 3);
                ItemMeta caneMeta = LUCKY_CANE.getItemMeta();
                caneMeta.setDisplayName(ChatColor.BLUE + "Lucky Cane");

                List<String> caneLore = new ArrayList<String>();
                caneLore.add(ChatColor.BLUE + "What will you get? Who knows!");
                caneMeta.setLore(caneLore);
                LUCKY_CANE.setItemMeta(caneMeta);
                LUCKY_CANE.setAmount(1);

                player.getInventory().removeItem(LUCKY_CANE);
                Inventory inventory = Bukkit.createInventory(null, 18);
                ItemStack[] items = new ItemStack[]{new ItemStack(Material.EXP_BOTTLE, 3), new ItemStack(Material.SEEDS, 1),
                        new ItemStack(Material.IRON_SWORD, 1), new ItemStack(Material.EGG, 6), new ItemStack(Material.SNOW_BALL, 6),
                        new ItemStack(Material.FEATHER, 1), new ItemStack(Material.POTION, 1, (short) 16428), new ItemStack(Material.WOOD_SWORD, 1),
                        new ItemStack(Material.DIAMOND_AXE, 1), new ItemStack(Material.BOAT, 6), new ItemStack(Material.GOLDEN_APPLE, 3),
                        new ItemStack(Material.GOLD_RECORD, 1), new ItemStack(Material.CHAINMAIL_HELMET, 1), new ItemStack(Material.FIREWORK, 6),
                        new ItemStack(Material.BAKED_POTATO, 12), new ItemStack(Material.EYE_OF_ENDER, 6), new ItemStack(Material.COOKIE, 32),
                        LUCKY_CANE};
                for (ItemStack itemStack : items) {
                    boolean success = Math.random() <= 0.2;
                    if (success) inventory.addItem(itemStack);
                }
                player.openInventory(inventory);
            }
        }
    }
}
