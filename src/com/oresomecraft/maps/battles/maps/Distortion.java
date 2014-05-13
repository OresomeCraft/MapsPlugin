package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class Distortion extends BattleMap implements Listener {

    public Distortion() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(10);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.ARROW, Material.FISHING_ROD, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_BOOTS, Material.BOW, Material.IRON_BOOTS, Material.IRON_LEGGINGS,
                Material.LEATHER_CHESTPLATE, Material.IRON_HELMET, Material.FISHING_ROD, Material.DIAMOND_PICKAXE, Material.ARROW, Material.DIAMOND});
        setAutoSpawnProtection(2);
    }

    String name = "gravity";
    String fullName = "Distortion";
    String[] creators = {"__R3"};
    Gamemode[] modes = {Gamemode.TDM};
    protected boolean manipulation = false;

    @EventHandler
    public void onload(WorldLoadEvent event) {
        if (event.getWorld().getName().equalsIgnoreCase("gravity")) {
            gravityArrows();
        }
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 20, 71, -68, 179, 0);
        Location blueSpawn = new Location(w, 20, 71, 69, -1, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 20, 71, -68, 179, 0);
        Location blueSpawn = new Location(w, 20, 71, 69, -1, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack DIAMOND_PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemStack ANTIGRAVITY = new ItemStack(Material.DIAMOND, 1);

        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        ItemMeta gravityMeta = ANTIGRAVITY.getItemMeta();
        gravityMeta.setDisplayName(ChatColor.BLUE + "Anti-Gravity Stone");

        List<String> gravityLore = new ArrayList<String>();
        gravityLore.add(org.bukkit.ChatColor.BLUE + "Hold this to reverse the current effects of gravity!");
        gravityMeta.setLore(gravityLore);
        ANTIGRAVITY.setItemMeta(gravityMeta);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);

        i.setItem(0, DIAMOND_PICKAXE);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(8, ANTIGRAVITY);
        i.setItem(28, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -28;
    public int y1 = 155;
    public int z1 = -86;

    //Bottom right corner.
    public int x2 = 69;
    public int y2 = 36;
    public int z2 = 75;

    int distort;

    public void gravityArrows() {
        Bukkit.getServer().getScheduler().cancelTask(distort);
        distort = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            public void run() {
                if (!manipulation) return;
                if (getArena().equals(name)) {
                    World world = Bukkit.getWorld(name);
                    if (!(world.getEntities() == null)) {
                        for (Entity arrow : world.getEntities()) {
                            if (arrow instanceof Arrow) {
                                arrow.setVelocity(new Vector(arrow.getVelocity().getX(), arrow.getVelocity().getY() - 1, arrow.getVelocity().getZ()));
                            }
                        }
                    }
                }
            }

        }, 5L, 5L);
    }

    @EventHandler
    public void battleEnd(BattleEndEvent event) {
        Bukkit.getScheduler().cancelTask(distort);
    }

    @EventHandler
    public void manipulator(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equalsIgnoreCase(name)) {
            if (event.getBlock().getType() == Material.OBSIDIAN && manipulation) {
                manipulation = false;
                Bukkit.broadcastMessage(ChatColor.AQUA + "The Gravity Manipulator was turned off! Gravity returned to normal!");
                return;
            }
            if (event.getBlock().getType().equals(Material.OBSIDIAN) && !manipulation) {
                manipulation = true;
                Bukkit.broadcastMessage(ChatColor.AQUA + "The Gravity Manipulator was turned on! Gravity intensified!");
            }
        }
    }

    @EventHandler
    public void manipulatorEffect(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase(name)) {
            Player p = event.getPlayer();
            if (!BattlePlayer.getBattlePlayer(p).isSpectator() && manipulation) {
                if (!p.getItemInHand().getType().equals(Material.DIAMOND)) {
                    if (event.getFrom().getY() > event.getTo().getY()) {
                        p.setVelocity(new Vector(0, p.getVelocity().getY() - 0.3, 0));
                    }
                }
            }
            if (!BattlePlayer.getBattlePlayer(p).isSpectator() && !manipulation) {
                if (p.getItemInHand().getType().equals(Material.DIAMOND)) {
                    if (event.getFrom().getY() > event.getTo().getY()) {
                        p.setVelocity(new Vector(0, p.getVelocity().getY() - 0.3, 0));
                    }
                }
            }
        }
    }
}
