package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MapConfig(
        name = "dusk",
        fullName = "Darkness of Dusk",
        creators = {"xannallax33", "dinner1111", "pepsidawg00", "__R3"},
        gamemodes = {Gamemode.INFECTION}
)
@Region(
        x1 = -103,
        y1 = 52,
        z1 = -22,
        x2 = 101,
        y2 = -7,
        z2 = 199
)
@Attributes(
        allowBuild = false,
        timeLock = Map.Time.NIGHT,
        disabledDrops = {Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.IRON_SWORD, Material.STONE_SWORD, Material.GOLD_LEGGINGS, Material.DIAMOND_BOOTS, Material.LEATHER_HELMET}
)
public class Dusk extends BattleMap implements Listener {

    public Dusk() {
        super.initiate(this);
    }

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
        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET});

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

    ItemStack[] items = new ItemStack[]{new ItemStack(Material.EXP_BOTTLE, 3), new ItemStack(Material.SEEDS, 1),
            new ItemStack(Material.IRON_SWORD, 1), new ItemStack(Material.EGG, 6), new ItemStack(Material.SNOW_BALL, 6),
            new ItemStack(Material.FEATHER, 1), new ItemStack(Material.POTION, 1, (short) 16428), new ItemStack(Material.WOOD_SWORD, 1),
            new ItemStack(Material.DIAMOND_AXE, 1), new ItemStack(Material.BOAT, 6), new ItemStack(Material.GOLDEN_APPLE, 3),
            new ItemStack(Material.GOLD_RECORD, 1), new ItemStack(Material.CHAINMAIL_HELMET, 1), new ItemStack(Material.FIREWORK, 6),
            new ItemStack(Material.BAKED_POTATO, 12), new ItemStack(Material.EYE_OF_ENDER, 6), new ItemStack(Material.COOKIE, 32),
            new ItemStack(Material.SKULL), new ItemStack(Material.RECORD_3), new ItemStack(Material.CAKE, 1), new ItemStack(Material.BOOK_AND_QUILL)};

    @EventHandler
    public void onVirtualLuck(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(getName())) return;
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
                if (!Arrays.asList(items).contains(LUCKY_CANE))
                    items[items.length + 1] = LUCKY_CANE;
                player.getInventory().removeItem(LUCKY_CANE);
                Inventory inventory = Bukkit.createInventory(null, 18);
                for (ItemStack itemStack : items) {
                    boolean success = Math.random() <= 0.2;
                    if (success) inventory.addItem(itemStack);
                }
                player.openInventory(inventory);
            }
        }
    }
}
