package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.OresomeBattles.gamemodes.Infection;
import com.oresomecraft.OresomeBattles.gamemodes.TDM;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Sandtrap extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;

    public Sandtrap(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "sandtrap";
    String fullName = "Sand Trap";
    String creators = "R3creat3, _Moist and JacquiRose";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.INFECTION};
    //Map download link: N/A

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
        addMap(name);
        setGamemodes(name, modes);
        readyTDMSpawns();
        readyFFASpawns();
        addCreators(name, creators);
        setFullName(name, fullName);
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, -18, 88, -10));
        redSpawns.add(new Location(w, -18, 88, -32));
        redSpawns.add(new Location(w, -9, 82, -30));
        redSpawns.add(new Location(w, -27, 82, -12));
        redSpawns.add(new Location(w, -8, 77, -11));
        redSpawns.add(new Location(w, -28, 77, -31));
        redSpawns.add(new Location(w, -8, 72, -31));
        redSpawns.add(new Location(w, -28, 72, -11));
        redSpawns.add(new Location(w, -8, 67, -11));
        redSpawns.add(new Location(w, -28, 67, -31));
        blueSpawns.add(new Location(w, -9, 82, 4));
        blueSpawns.add(new Location(w, -27, 82, 22));
        blueSpawns.add(new Location(w, -28, 77, 3));
        blueSpawns.add(new Location(w, -8, 77, 23));
        blueSpawns.add(new Location(w, -8, 72, 3));
        blueSpawns.add(new Location(w, -28, 72, 23));
        blueSpawns.add(new Location(w, -28, 67, 3));
        blueSpawns.add(new Location(w, -8, 67, 23));
        blueSpawns.add(new Location(w, -18, 88, 2));
        blueSpawns.add(new Location(w, -18, 88, 24));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, -18, 88, -10));
        FFASpawns.add(new Location(w, -18, 88, -32));
        FFASpawns.add(new Location(w, -9, 82, -30));
        FFASpawns.add(new Location(w, -27, 82, -12));
        FFASpawns.add(new Location(w, -8, 77, -11));
        FFASpawns.add(new Location(w, -28, 77, -31));
        FFASpawns.add(new Location(w, -8, 72, -31));
        FFASpawns.add(new Location(w, -28, 72, -11));
        FFASpawns.add(new Location(w, -8, 67, -11));
        FFASpawns.add(new Location(w, -28, 67, -31));
        FFASpawns.add(new Location(w, -9, 82, 4));
        FFASpawns.add(new Location(w, -27, 82, 22));
        FFASpawns.add(new Location(w, -28, 77, 3));
        FFASpawns.add(new Location(w, -8, 77, 23));
        FFASpawns.add(new Location(w, -8, 72, 3));
        FFASpawns.add(new Location(w, -28, 72, 23));
        FFASpawns.add(new Location(w, -28, 67, 3));
        FFASpawns.add(new Location(w, -8, 67, 23));
        FFASpawns.add(new Location(w, -18, 88, 2));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
            ItemStack GLASS = new ItemStack(Material.GLASS, 6);
            ItemStack SAND = new ItemStack(Material.SAND, 32);
            ItemStack TNT = new ItemStack(Material.TNT, 1);
            ItemStack TINDER = new ItemStack(Material.FLINT_AND_STEEL, 1);

            if (TDM.isBlue(p.getName())) {

                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
                helmetMeta.setColor(Color.BLUE);
                LEATHER_HELMET.setItemMeta(helmetMeta);

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

                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
                helmetMeta.setColor(Color.RED);
                LEATHER_HELMET.setItemMeta(helmetMeta);

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

                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
                helmetMeta.setColor(Color.GREEN);
                LEATHER_HELMET.setItemMeta(helmetMeta);

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

                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
                helmetMeta.setColor(Color.RED);
                LEATHER_HELMET.setItemMeta(helmetMeta);

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
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, STONE_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH);
            i.setItem(15, ARROWS);
            i.setItem(4, GLASS);
            i.setItem(5, SAND);
            i.setItem(6, TNT);
            i.setItem(7, TINDER);

        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void clearSpawns(ClearSpawnsEvent event) {
        redSpawns.clear();
        blueSpawns.clear();
        FFASpawns.clear();
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 0;
    public int y1 = 64;
    public int z1 = -41;

    //Bottom right corner.
    public int x2 = -38;
    public int y2 = 96;
    public int z2 = 32;

    // Getting the region
    public boolean contains(Location loc, int x1, int x2, int y1,
                            int y2, int z1, int z2) {
        int bottomCornerX = x1 < x2 ? x1 : x2;
        int bottomCornerZ = z1 < z2 ? z1 : z2;
        int topCornerX = x1 > x2 ? x1 : x2;
        int topCornerZ = z1 > z2 ? z1 : z2;
        int bottomCornerY = y1 < y2 ? y1 : y2;
        int topCornerY = y1 > y2 ? y1 : y2;
        if (loc.getX() >= bottomCornerX && loc.getX() <= topCornerX) {
            if (loc.getZ() >= bottomCornerZ && loc.getZ() <= topCornerZ) {
                if (loc.getY() >= bottomCornerY && loc.getY() <= topCornerY) {
                    return true;
                }
            }
        }
        return false;

    }

    // Code to prevent block breaking.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {
            if (!(contains(loc, x1, x2, y1, y2, z1, z2))) {
                event.setCancelled(true);
            }
        }

    }

    // Code to prevent block placing.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();
        if (loc.getWorld().getName().equals(name)) {
            if (!(contains(loc, x1, x2, y1, y2, z1, z2))) {
                event.setCancelled(true);
            }
        }

    }

    // Code to prevent escaping map
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(PlayerMoveEvent event) {

        if (event.getPlayer().getLocation().getWorld().getName().equals(name)) {
            if (!(contains(event.getPlayer().getLocation(), x1, x2, y1, y2, z1, z2))) {
                event.setCancelled(true);
            }
        }

    }

}
