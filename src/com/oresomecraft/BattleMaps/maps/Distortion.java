package com.oresomecraft.BattleMaps.maps;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;

public class Distortion extends BattleMap implements IBattleMap, Listener {

    public Distortion() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(10);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.IRON_BOOTS, Material.IRON_LEGGINGS,
                Material.LEATHER_CHESTPLATE, Material.IRON_HELMET, Material.FISHING_ROD, Material.DIAMOND_PICKAXE, Material.ARROW, Material.DIAMOND});
    }

    String name = "gravity";
    String fullName = "Distortion";
    String creators = "R3creat3, _Moist, DynaDavidson, meganlovesmusic and LanderA";
    Gamemode[] modes = {Gamemode.TDM};
    protected boolean manipulation = false;

    @EventHandler
    public void onload(WorldLoadEvent event) {
        if(event.getWorld().getName().equals("gravity")){
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
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack DIAMOND_PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemStack ANTIGRAVITY = new ItemStack(Material.DIAMOND, 1);

        ItemMeta a = ANTIGRAVITY.getItemMeta();
        a.setDisplayName(ChatColor.BLUE + "Anti-Gravity Stone");

        List<String> aLore = new ArrayList<String>();
        aLore.add(org.bukkit.ChatColor.BLUE + "Hold this to be uninfluenced by the gravity manipulator!");
        a.setLore(aLore);
        ANTIGRAVITY.setItemMeta(a);

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
    public int x1 = 0;
    public int y1 = 1;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 128;
    public int y2 = 255;
    public int z2 = 128;

    int distort;

    public void gravityArrows() {
        Bukkit.getServer().getScheduler().cancelTask(distort);
        distort = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            public void run() {
                if (manipulation == false) return;
                if (getArena().equals(name)) {
                    World world = Bukkit.getWorld("gravity");
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
        if (!active) return;
        if (event.getBlock().getType() == Material.SPONGE) {
            event.getBlock().getWorld().getBlockAt(event.getBlock().getLocation()).setType(Material.OBSIDIAN);
            manipulation = false;
            Bukkit.broadcastMessage(ChatColor.AQUA + "The Gravity Manipulator was turned off! Gravity returned to normal!");
            return;
        }
        if (event.getBlock().getType() == Material.OBSIDIAN) {
            event.getBlock().getWorld().getBlockAt(event.getBlock().getLocation()).setType(Material.SPONGE);
            manipulation = true;
            Bukkit.broadcastMessage(ChatColor.AQUA + "The Gravity Manipulator was turned on! Gravity intensified!");
        }
    }

    @EventHandler
    public void manipulatorEffect(PlayerMoveEvent event) {
        if(!active) return;
        Player p = event.getPlayer();
        if (manipulation == true) {
            if (p.getItemInHand().getType() != Material.DIAMOND) {
                if (event.getFrom().getY() > event.getTo().getY()) {
                    p.setVelocity(new Vector(0, p.getVelocity().getY() - 0.3, 0));
                }
            }
        }
    }
}
