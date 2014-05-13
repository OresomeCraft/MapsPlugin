package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;

@MapConfig
public class Mayhem extends BattleMap implements Listener {

    public Mayhem() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(10);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.EMERALD, Material.ARROW, Material.FISHING_ROD, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_BOOTS, Material.BOW, Material.IRON_BOOTS, Material.IRON_LEGGINGS,
                Material.IRON_CHESTPLATE, Material.FISHING_ROD, Material.DIAMOND_PICKAXE, Material.ARROW});
        setAutoSpawnProtection(2);
    }

    String name = "mayhem";
    String fullName = "Mayhem";
    String[] creators = {"ShaunDepro97", "meganlovesmusic", "Kytria"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.LTS};

    @EventHandler
    public void onload(WorldLoadEvent event) { // Register power block
        if (event.getWorld().getName().equals(name)) cyclePowerBlock();
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 64, 112, 111, 2, 0);
        Location blueSpawn = new Location(w, 23, 112, 63, 3, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 64, 112, 111, 2, 0);
        Location blueSpawn = new Location(w, 23, 112, 63, 3, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 2);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack DIAMOND_PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 6);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);

        i.setItem(0, DIAMOND_PICKAXE);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(8, EXP);
        i.setItem(28, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 5;
    public int y1 = 149;
    public int z1 = 5;

    //Bottom right corner.
    public int x2 = 122;
    public int y2 = 65;
    public int z2 = 126;

    int timer;
    int count = 1;
    Location powerBlock = new Location(Bukkit.getWorld(name), 64, 110, 64);

    private void cyclePowerBlock() {

        timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                Material[] blocks = {Material.REDSTONE_ORE, Material.GOLD_ORE, Material.COAL_ORE,
                        Material.EMERALD_ORE, Material.LAPIS_ORE, Material.DIAMOND_ORE};
                int max = blocks.length;

                World world = Bukkit.getWorld(name);
                world.getBlockAt(powerBlock).setType(blocks[(count - 1)]);

                count = count >= max ? count = 1 : (count + 1);
            }

        }, (20 * 40), (20 * 40));

    }

    @EventHandler
    public void battleEnd(BattleEndEvent event) {
        Bukkit.getScheduler().cancelTask(timer);
    }

    @EventHandler
    public void powerBlock(BlockBreakEvent event) {
        Player p = event.getPlayer();
        Material type = event.getBlock().getType();
        int potionTime = 45 * 20;

        if (compareLocations(powerBlock, event.getBlock().getLocation())) {

            if (type != Material.COBBLESTONE) {

                if (type == Material.REDSTONE_ORE || type == Material.GLOWING_REDSTONE_ORE) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, potionTime, 0));
                } else if (type == Material.GOLD_ORE) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, potionTime, 1));
                } else if (type == Material.COAL_ORE) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, potionTime, 0));
                } else if (type == Material.LAPIS_ORE) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, potionTime, 2));
                } else if (type == Material.DIAMOND_ORE) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, potionTime, 1));
                } else if (type == Material.EMERALD_ORE) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, potionTime, 1));
                }

                event.getBlock().setType(Material.COBBLESTONE);
            } else {
                p.sendMessage(ChatColor.DARK_AQUA + "The power block has already been broken!");
                p.sendMessage(ChatColor.DARK_AQUA + "Please wait for it to cycle again!");
            }
        }
    }
}
