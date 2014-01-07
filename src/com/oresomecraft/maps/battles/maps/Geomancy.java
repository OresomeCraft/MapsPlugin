package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Geomancy extends BattleMap implements IBattleMap, Listener {

    public Geomancy() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(60);
        disableDrops(new Material[]{Material.DIAMOND_HELMET, Material.WOOD_SWORD});
        setBuildLimit(173);
    }

    String name = "geomancy";
    String fullName = "Geomancy";
    String creators = "Fliine, danielschroeder, reggie449, __R3, Boomyay, ep1cn00bt00b, Kooper94 and Fameously";
    Gamemode[] modes = {Gamemode.TDM};

    boolean b1 = false;
    boolean b2 = false;
    boolean b3 = false;
    boolean b4 = false;
    boolean b5 = false;
    boolean b6 = false;
    boolean b7 = false;
    boolean b8 = false;
    boolean b9 = false;

    @EventHandler
    public void end(BattleEndEvent e) {
        b1 = false;
        b2 = false;
        b3 = false;
        b4 = false;
        b5 = false;
        b6 = false;
        b7 = false;
        b8 = false;
        b9 = false;
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -1.5, 132, -76.5));
        redSpawns.add(new Location(w, -77.5, 133, 3.5));
        redSpawns.add(new Location(w, 2.5, 132, 85.5));
        redSpawns.add(new Location(w, 82.5, 132, 1.5));
        blueSpawns.add(new Location(w, 26, 146, 3));
        blueSpawns.add(new Location(w, 0, 146, -22));
        blueSpawns.add(new Location(w, -23, 146, 3));
        blueSpawns.add(new Location(w, 0, 146, 28));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 146, 28));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 5);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack BLUE_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 11);
        ItemStack RED_STAINED_CLAY = new ItemStack(Material.STAINED_CLAY, 48, (short) 14);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack TORCH = new ItemStack(Material.TORCH, 16);
        ItemStack ARROW = new ItemStack(Material.ARROW, 1);

        p.getInventory().setHelmet(IRON_HELMET);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setLeggings(IRON_LEGGINGS);
        p.getInventory().setBoots(IRON_BOOTS);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, IRON_PICKAXE);
        i.setItem(3, PUMPKIN_PIE);
        i.setItem(4, APPLE);

        if (p.getTeam() == Team.TDM_RED) i.setItem(5, RED_STAINED_CLAY);
        if (p.getTeam() == Team.TDM_BLUE) i.setItem(5, BLUE_STAINED_CLAY);

        i.setItem(6, TORCH);
        i.setItem(27, ARROW);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    //Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

    @EventHandler
    public void breakListener(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (loc.getWorld().getName().equals(name)) {

            if (event.getBlock().getLocation().distance(new Location(event.getBlock().getWorld(), 0, 152, 3)) <= 10
                    && BattlePlayer.getBattlePlayer(event.getPlayer().getName()).getTeam() == Team.TDM_BLUE)

                //Middle breaking
                if (contains(loc, 2, -2, 144, 156, 5, 1)) event.setCancelled(true);

            //Listeners
            int x = event.getBlock().getLocation().getBlockX();
            int y = event.getBlock().getLocation().getBlockY();
            int z = event.getBlock().getLocation().getBlockZ();

            if (y == 148) {
                if (x == 1 && z == 4) {
                    b1 = true;
                    event.setCancelled(false);
                }
                if (x == 0 && z == 4) {
                    b2 = true;
                    event.setCancelled(false);
                }
                if (x == -1 && z == 4) {
                    b3 = true;
                    event.setCancelled(false);
                }
                if (x == -1 && z == 3) {
                    b4 = true;
                    event.setCancelled(false);
                }
                if (x == 0 && z == 3) {
                    b5 = true;
                    event.setCancelled(false);
                }
                if (x == 1 && z == 3) {
                    b6 = true;
                    event.setCancelled(false);
                }
                if (x == 1 && z == 2) {
                    b7 = true;
                    event.setCancelled(false);
                }
                if (x == 0 && z == 2) {
                    b8 = true;
                    event.setCancelled(false);
                }
                if (x == -1 && z == 2) {
                    b9 = true;
                    event.setCancelled(false);
                }

            }
            if (b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9) {
                callEndSandFall();
            }
        }
    }

    @EventHandler
    public void placeListener(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if (loc.getWorld().getName().equals(name)) {

            if (event.getBlock().getLocation().distance(new Location(event.getBlock().getWorld(), 0, 152, 3)) <= 10
                    && BattlePlayer.getBattlePlayer(event.getPlayer().getName()).getTeam() == Team.TDM_BLUE)
                event.setCancelled(true);
            //Middle placing
            if (contains(loc, 2, -2, 144, 156, 5, 1)) event.setCancelled(true);
        }
    }

    private void callEndSandFall() {
        Bukkit.getPluginManager().callEvent(new BattleEndEvent(getMode()));
        Bukkit.broadcastMessage(ChatColor.RED + "##################################");
        Bukkit.broadcastMessage(ChatColor.RED + "Red Team " + ChatColor.DARK_AQUA + "released all the sand and won the battle!");
        Bukkit.broadcastMessage(ChatColor.RED + "##################################");

    }
}
