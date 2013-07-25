package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemodes.Infection;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import java.util.List;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.gamemodes.TDM;

public class Mansion extends BattleMap implements IBattleMap, Listener {

    public Mansion() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "mansion";
    String fullName = "The haunted mansion";
    String creators = "pegabeavercorn, Hourani95 and kevlar_miner";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 442, 64, -324);
        Location blueSpawn = new Location(w, 442, 64, -324);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, 472, 64, -228));
        blueSpawns.add(new Location(w, 412, 64, -229));
        redSpawns.add(new Location(w, 428, 64, -311));
        blueSpawns.add(new Location(w, 454, 64, -311));
        redSpawns.add(new Location(w, 472, 64, -289));
        blueSpawns.add(new Location(w, 416, 66, -305));
        redSpawns.add(new Location(w, 416, 66, -305));
        blueSpawns.add(new Location(w, 414, 64, -279));
        redSpawns.add(new Location(w, 415, 64, -269));
        blueSpawns.add(new Location(w, 412, 64, -266));
        redSpawns.add(new Location(w, 419, 71, -260));
        blueSpawns.add(new Location(w, 416, 74, -307));
        redSpawns.add(new Location(w, 446, 74, -271));
        blueSpawns.add(new Location(w, 467, 74, -310));
        redSpawns.add(new Location(w, 416, 74, -305));
        blueSpawns.add(new Location(w, 454, 72, -267));
        redSpawns.add(new Location(w, 467, 64, -264));
        blueSpawns.add(new Location(w, 440, 71, -270));
        redSpawns.add(new Location(w, 465, 74, -274));
        blueSpawns.add(new Location(w, 466, 74, -251));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {
        World w = Bukkit.getServer().getWorld(name);
        Location redSpawn = new Location(w, 442, 64, -324);
        Location blueSpawn = new Location(w, 442, 64, -324);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 472, 64, -228));
        FFASpawns.add(new Location(w, 412, 64, -229));
        FFASpawns.add(new Location(w, 428, 64, -311));
        FFASpawns.add(new Location(w, 454, 64, -311));
        FFASpawns.add(new Location(w, 472, 64, -289));
        FFASpawns.add(new Location(w, 416, 66, -305));
        FFASpawns.add(new Location(w, 416, 66, -305));
        FFASpawns.add(new Location(w, 414, 64, -279));
        FFASpawns.add(new Location(w, 415, 64, -269));
        FFASpawns.add(new Location(w, 412, 64, -266));
        FFASpawns.add(new Location(w, 419, 71, -260));
        FFASpawns.add(new Location(w, 416, 74, -307));
        FFASpawns.add(new Location(w, 446, 74, -271));
        FFASpawns.add(new Location(w, 467, 74, -310));
        FFASpawns.add(new Location(w, 416, 74, -305));
        FFASpawns.add(new Location(w, 454, 72, -267));
        FFASpawns.add(new Location(w, 467, 64, -264));
        FFASpawns.add(new Location(w, 440, 71, -270));
        FFASpawns.add(new Location(w, 465, 74, -274));
        FFASpawns.add(new Location(w, 466, 74, -251));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        String par = event.getMessage();
        BattlePlayer p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack MASK = new ItemStack(Material.SKULL_ITEM, 1);
            ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

            if (TDM.isBlue(p.getName())) {

                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
                bootsMeta.setColor(Color.BLUE);
                LEATHER_BOOTS.setItemMeta(bootsMeta);

                LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
                pantsMeta.setColor(Color.BLUE);
                LEATHER_PANTS.setItemMeta(pantsMeta);

                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
                chestplateMeta.setColor(Color.BLUE);
                LEATHER_CHESTPLATE.setItemMeta(chestplateMeta);
            }

            if (TDM.isRed(p.getName())) {

                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
                bootsMeta.setColor(Color.RED);
                LEATHER_BOOTS.setItemMeta(bootsMeta);

                LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
                pantsMeta.setColor(Color.RED);
                LEATHER_PANTS.setItemMeta(pantsMeta);

                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
                chestplateMeta.setColor(Color.RED);
                LEATHER_CHESTPLATE.setItemMeta(chestplateMeta);
            }

            if (Infection.isHuman(p.getName())) {

                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
                bootsMeta.setColor(Color.GREEN);
                LEATHER_BOOTS.setItemMeta(bootsMeta);

                LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
                pantsMeta.setColor(Color.GREEN);
                LEATHER_PANTS.setItemMeta(pantsMeta);

                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
                chestplateMeta.setColor(Color.GREEN);
                LEATHER_CHESTPLATE.setItemMeta(chestplateMeta);
            }

            if (Infection.isZombie(p.getName())) {

                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) LEATHER_BOOTS.getItemMeta();
                bootsMeta.setColor(Color.RED);
                LEATHER_BOOTS.setItemMeta(bootsMeta);

                LeatherArmorMeta pantsMeta = (LeatherArmorMeta) LEATHER_PANTS.getItemMeta();
                pantsMeta.setColor(Color.RED);
                LEATHER_PANTS.setItemMeta(pantsMeta);

                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) LEATHER_CHESTPLATE.getItemMeta();
                chestplateMeta.setColor(Color.RED);
                LEATHER_CHESTPLATE.setItemMeta(chestplateMeta);
            }

            p.getInventory().setBoots(LEATHER_BOOTS);
            p.getInventory().setLeggings(LEATHER_PANTS);
            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setHelmet(MASK);

            i.setItem(0, STONE_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, EXP);
            i.setItem(3, STEAK);
            i.setItem(4, HEALTH_POTION);
            i.setItem(5, ARROWS);
            p.getInventory().getHelmet().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);

        }
    }

    public int x1 = 410;
    public int y1 = 59;
    public int z1 = -329;
    public int x2 = 473;
    public int y2 = 101;
    public int z2 = -228;

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockbreak(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockplace(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventburn(BlockBurnEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

    @EventHandler
    public void preventspread(BlockSpreadEvent event) {

        if (event.getBlock().getWorld().getName().equals(name)) {
            if ((event.getBlock().getTypeId() != 2) || (event.getBlock().getTypeId() != 3)) {

                event.setCancelled(true);

            }
        }

    }

    @EventHandler
    public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player p = event.getEntity();
        List<ItemStack> drops = event.getDrops();

        for (ItemStack item : drops) {
            Material mat = item.getType();

            if (mat == Material.STONE_SWORD) {

                item.setType(Material.AIR);
            }
        }
    }
}
