package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
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
public class Geomancy extends BattleMap implements Listener {

    public Geomancy() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(10);
        disableDrops(new Material[]{Material.ARROW, Material.IRON_PICKAXE, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.DIAMOND_HELMET, Material.WOOD_SWORD});
        setBuildLimit(173);
    }

    String name = "geomancy";
    String fullName = "Geomancy";
    String[] creators = {" __R3", " ep1cn00bt00b"};
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 74, 126, 0., 275.6F, 18.1F));
        blueSpawns.add(new Location(w, -2, 136, 53, 358.9F, -13.4F));
        blueSpawns.add(new Location(w, -50, 136, 1, 88F, -15.1F));
        blueSpawns.add(new Location(w, 1, 136, -47, 182.5F, -22.2F));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 1, 136, -47, 182.5F, -22.2F));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1, (short) -1400);
        ItemStack PUMPKIN_PIE = new ItemStack(Material.PUMPKIN_PIE, 8);
        ItemStack APPLE = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
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

        try {
            if (p.getTeamType() == Team.TDM_BLUE)
                i.setItem(2, new ItemStack(Material.DIAMOND_PICKAXE, 1, (short) -1000));
        } catch (Exception e) {
            System.out.println("[GEOMANCY CODE] SOMETHING WENT WRONG @ LINE 75");
        }

        i.setItem(27, ARROW);
    }

    public int x1 = -97;
    public int y1 = 204;
    public int z1 = 103;

    public int x2 = 101;
    public int y2 = 114;
    public int z2 = -97;

    @EventHandler
    public void breakListener(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (loc.getWorld().getName().equals(name)) {
            Material m = event.getBlock().getType();
            if (m != Material.GRASS && m != Material.DIRT && m != Material.OBSIDIAN && m != Material.STEP && m != Material.FENCE) {
                event.setCancelled(true);
            }
            int x = event.getBlock().getLocation().getBlockX();
            int y = event.getBlock().getLocation().getBlockY();
            int z = event.getBlock().getLocation().getBlockZ();
            try {
                if (x == 0 && y == 148 && z == 3) {
                    if (BattlePlayer.getBattlePlayer(event.getPlayer()).getTeamType() == Team.TDM_BLUE) {
                        event.setCancelled(false);
                        callEnd();
                    }
                }
            } catch (Exception e) {
                //fix this @Zachoz.
            }
        }
    }

    @EventHandler
    public void placeListener(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if (loc.getWorld().getName().equals(name)) {
            Material m = event.getBlock().getType();
            if (m == Material.STEP || m == Material.OBSIDIAN || m == Material.FENCE) {
                event.setCancelled(true);
            }
            if (event.getBlock().getLocation().distance(new Location(event.getBlock().getWorld(), 0, 148, 3)) <= 8)
                event.setCancelled(true);
        }
    }

    private void callEnd() {
        Bukkit.getPluginManager().callEvent(new BattleEndEvent(getMode()));
        Bukkit.broadcastMessage(ChatColor.BLUE + "##################################");
        Bukkit.broadcastMessage(ChatColor.BLUE + "Blue Team " + ChatColor.DARK_AQUA + "broke the monument and won!");
        Bukkit.broadcastMessage(ChatColor.BLUE + "##################################");
    }
}
