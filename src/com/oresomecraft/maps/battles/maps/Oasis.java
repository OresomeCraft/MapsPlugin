package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class Oasis extends BattleMap implements Listener {

    public Oasis() {
        setAllowBuild(false);
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.LEATHER_HELMET, Material.STONE_SWORD, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS});
        lockTime("day");
        setAutoSpawnProtection(10);
    }

    String name = "oasis";
    String fullName = "Oasis";
    String[] creators = {"ep1cn00bt00b", "miniwolf35"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.KOTH, Gamemode.CTF};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 6, 87, 76, -179, 0);
        Location blueSpawn = new Location(w, -6, 87, -151, 0, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, 6, 82, 56);
        Location blueFlag = new Location(w, -6, 82, -130);
        setCTFFlags(name, redFlag, blueFlag);
        setKoTHMonument(new Location(w, 0, 87, -37));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 6, 87, 76, -179, 0);
        Location blueSpawn = new Location(w, -6, 87, -151, 0, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 16);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack ROSE = new ItemStack(Material.RED_ROSE, 1, (short) 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        ItemMeta roseMeta = ROSE.getItemMeta();
        roseMeta.setDisplayName(ChatColor.AQUA + "Water Walking Rose");

        List<String> roseLore = new ArrayList<String>();
        roseLore.add(org.bukkit.ChatColor.BLUE + "Hold this frosty rose to walk over water!");
        roseMeta.setLore(roseLore);
        ROSE.setItemMeta(roseMeta);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(8, ROSE);
        i.setItem(10, ARROWS);

    }

    public int x1 = 80;
    public int y1 = 62;
    public int z1 = 90;

    public int x2 = -74;
    public int y2 = 122;
    public int z2 = -166;

    public void iceTemp(final Block block, final Player p) {
        block.setType(Material.PACKED_ICE);
        new BukkitRunnable() {
            public void run() {
                if (Bukkit.getWorld(name) != null) {
                    block.setType(Material.STATIONARY_WATER);
                    if (block.getLocation().distanceSquared(p.getLocation()) <= 3) iceTemp(block, p);
                }
            }
        }.runTaskLater(plugin, 3 * 20L);
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (!(event.getPlayer().getItemInHand().getType() == Material.RED_ROSE)) return;
        for (Block block : circle(event.getPlayer().getLocation(), 3, 3, false, true, 0)) {
            if (block.getType() == Material.WATER || block.getType() == Material.STATIONARY_WATER) {
                iceTemp(block, event.getPlayer());
            }
        }
    }

    protected static List<Block> circle(Location loc, int radius, int height, boolean hollow, boolean sphere, int plusY) {
        List<Block> circleblocks = new ArrayList<Block>();
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                for (int y = (sphere ? cy - radius : cy); y < (sphere ? cy + radius : cy + height); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (dist < radius * radius && !(hollow && dist < (radius - 1) * (radius - 1))) {
                        Location l = new Location(loc.getWorld(), x, y + plusY, z);
                        circleblocks.add(l.getBlock());
                    }
                }
            }
        }

        return circleblocks;
    }
}
