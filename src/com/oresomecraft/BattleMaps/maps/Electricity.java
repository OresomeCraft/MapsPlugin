package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.BattleHandler;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

import java.util.List;

public class Electricity extends BattleMap implements IBattleMap, Listener {

    public Electricity() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "electricity";
    String fullName = "Electricity";
    String creators = "kingfisher83, danielschroeder, R3creat3 and _Moist";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, 0, 76, 59));
        redSpawns.add(new Location(w, -58, 76, 0));
        redSpawns.add(new Location(w, -16, 76, 32));

        blueSpawns.add(new Location(w, 0, 76, -58));
        blueSpawns.add(new Location(w, 59, 76, 0));
        blueSpawns.add(new Location(w, 18, 76, -30));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, 0, 76, 59));
        FFASpawns.add(new Location(w, -58, 76, 0));
        FFASpawns.add(new Location(w, -16, 76, 32));
        FFASpawns.add(new Location(w, 0, 76, -58));
        FFASpawns.add(new Location(w, 59, 76, 0));
        FFASpawns.add(new Location(w, 18, 76, -30));
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
            BOW.getItemMeta().setDisplayName(ChatColor.YELLOW + "Lightning Bow");
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack LEATHER_HELMET = new ItemStack(Material.GOLD_HELMET, 1);
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.GOLD_BOOTS, 1);
            ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -1600);
            p.getInventory().setBoots(LEATHER_BOOTS);
            p.getInventory().setLeggings(LEATHER_PANTS);
            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);


            i.setItem(0, STONE_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH);
            i.setItem(15, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 7;
    public int y1 = 66;
    public int z1 = 52;

    //Bottom right corner.
    public int x2 = 93;
    public int y2 = 0;
    public int z2 = -37;

    @EventHandler(priority = EventPriority.NORMAL)
    public void arrowboom(ProjectileHitEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Arrow) {
                event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
                event.getEntity().remove();
            }
        }

    }

    //Clears armor drops
    @EventHandler(priority = EventPriority.NORMAL)
    public void death(PlayerDeathEvent event) {

        List<ItemStack> drops = event.getDrops();
        int amount = drops.size();
        int count = 0;

        for (int none = 0; none < amount; none++) {

            ItemStack i = drops.get(count);
            count++;
            Material mat = i.getType();

            if (mat == Material.BOW || mat == Material.GOLD_BOOTS
                    || mat == Material.GOLD_LEGGINGS
                    || mat == Material.GOLD_CHESTPLATE
                    || mat == Material.GOLD_HELMET) {

                i.setType(Material.AIR);

            }
        }
    }

}
