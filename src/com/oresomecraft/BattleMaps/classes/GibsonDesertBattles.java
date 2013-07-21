package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;
import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GibsonDesertBattles extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public GibsonDesertBattles(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();


    String name = "Desert";
    String fullName = "Gibson Desert Battles";
    String creators = "_Moist and niceman506";
    Gamemode[] modes = {Gamemode.TDM};
    //Map download link: http://www.mediafire.com/download/svpf8zb58qgprhq/Gibson_Desert_Battles_-_Download_Copy.rar

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
        addMap(name);
        addCreators(name, creators);
        setFullName(name, fullName);
        setGamemodes(name, modes);
    }

    @EventHandler
    public void setSpawns(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            readyTDMSpawns();
            readyFFASpawns();
        }
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 0, 99, 27, 2, 0);
        Location blueSpawn = new Location(w, -9, 110, -20, 0, 0);

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -9, 61, 13, 2, -163));

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 227, 61, -110, 0, 24));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 227, 61, -110, 0, 24);
        Location blueSpawn = new Location(w, -9, 61, 13, 2, -163);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {

        String par = event.getMessage();
        Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            //Items
            ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE, 1);
            ItemStack BREAD = new ItemStack(Material.BREAD, 8);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROW = new ItemStack(Material.ARROW, 1);
            ItemStack LADDER = new ItemStack(Material.LADDER, 8);
            ItemStack FLOWER_POT = new ItemStack(Material.FLOWER_POT, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
            ItemStack OAK_LOG = new ItemStack(Material.LOG, 32);

            // Armor
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);

            if (TDM.isBlue(p.getName())) {

                //Sets armor blue
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

                //Sets armor red
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


            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setBoots(LEATHER_BOOTS);


            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, IRON_PICKAXE);
            i.setItem(3, IRON_AXE);
            i.setItem(4, BREAD);
            i.setItem(5, OAK_LOG);
            i.setItem(6, LADDER);
            i.setItem(7, FLOWER_POT);
            i.setItem(27, ARROW);

            //Give players invincibility and strength for 15 seconds when they spawn
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 15 * 20, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 15 * 20, 1));

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
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    //Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

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
    public void preventblockbreak(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    // Code to prevent block placing.
    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockplace(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

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

            if (mat == Material.BOW || mat == Material.LEATHER_BOOTS
                    || mat == Material.LEATHER_LEGGINGS
                    || mat == Material.LEATHER_CHESTPLATE
                    || mat == Material.LEATHER_HELMET) {

                i.setType(Material.AIR);

            }

        }
    }

    // <-------------------- C4 ------------------>

    java.util.Map<Player, Block> placer = new java.util.HashMap<Player, Block>();
    java.util.Map<Block, Player> placerB = new java.util.HashMap<Block, Player>();

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void c4(org.bukkit.event.player.PlayerInteractEvent event) {

        Player p = event.getPlayer();
        Location loc = p.getLocation();
        org.bukkit.event.block.Action a = event.getAction();
        ItemStack i = p.getItemInHand();
        Material tool = i.getType();
        org.bukkit.block.BlockFace face = event.getBlockFace();
        Inventory inv = p.getInventory();
        String name = p.getName();

        if (!GameUtils.isSpectator(name)) {

            if (contains(loc, x1, x2, y1, y2, z1, z2)) {
                if (tool == Material.FLOWER_POT) {

                    if (a == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) {

                        Block b = event.getClickedBlock();
                        Block place = b.getRelative(face);

                        if (!placerB.containsValue(p)) {

                            place.setType(Material.REDSTONE_BLOCK);
                            p.playSound(loc, org.bukkit.Sound.STEP_GRAVEL, 1, 1);

                            placer.put(p, place);
                            placerB.put(place, p);

                        }
                    }

                }

                if (a == org.bukkit.event.block.Action.RIGHT_CLICK_AIR) {

                    if (placer.containsKey(p) && placerB.containsValue(p)) {

                        Block charge = placer.get(p);
                        Location chargeloc = charge.getLocation();
                        World w = charge.getWorld();
                        p.playSound(loc, org.bukkit.Sound.CLICK, 1, 1);
                        w.createExplosion(chargeloc, 4);

                        ItemStack POT = new ItemStack(Material.FLOWER_POT, 1);
                        org.bukkit.inventory.meta.ItemMeta pot = POT.getItemMeta();
                        pot.setDisplayName(org.bukkit.ChatColor.BLUE + "C4");
                        POT.setItemMeta(pot);

                        inv.removeItem(POT);
                        p.updateInventory();
                        placer.remove(p);
                        placerB.remove(charge);

                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void c4premature(org.bukkit.event.entity.ProjectileHitEvent event) {
        org.bukkit.entity.Projectile p = event.getEntity();
        World w = p.getWorld();
        World world = Bukkit.getWorld(name);
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (p instanceof org.bukkit.entity.Arrow) {
                world.playEffect(p.getLocation(), org.bukkit.Effect.STEP_SOUND, 10);
            }
        }

        Location loc = p.getLocation();
        Block b = loc.getBlock();
        Block bD = b.getRelative(org.bukkit.block.BlockFace.DOWN);
        Block bU = b.getRelative(org.bukkit.block.BlockFace.UP);
        Block bE = b.getRelative(org.bukkit.block.BlockFace.EAST);
        Block bW = b.getRelative(org.bukkit.block.BlockFace.WEST);
        Block bN = b.getRelative(org.bukkit.block.BlockFace.NORTH);
        Block bS = b.getRelative(org.bukkit.block.BlockFace.SOUTH);

        ItemStack POT = new ItemStack(Material.FLOWER_POT, 1);
        org.bukkit.inventory.meta.ItemMeta pot = POT.getItemMeta();
        pot.setDisplayName(org.bukkit.ChatColor.BLUE + "C4");
        POT.setItemMeta(pot);

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (placer.containsValue(b)) {
                Player own = placerB.get(b);
                Inventory i = own.getInventory();
                i.removeItem(POT);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(b);

            } else if (placer.containsValue(bD)) {
                Player own = placerB.get(bD);
                Inventory i = own.getInventory();
                i.removeItem(POT);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bD);

            } else if (placer.containsValue(bU)) {
                Player own = placerB.get(bU);
                Inventory i = own.getInventory();
                i.removeItem(POT);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bU);

            } else if (placer.containsValue(bE)) {
                Player own = placerB.get(bE);
                Inventory i = own.getInventory();
                i.removeItem(POT);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bE);

            } else if (placer.containsValue(bW)) {
                Player own = placerB.get(bW);
                // Inventory i = own.getInventory();
                // i.removeItem(POT);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bW);

            } else if (placer.containsValue(bN)) {
                Player own = placerB.get(bN);
                Inventory i = own.getInventory();
                i.removeItem(POT);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bN);

            } else if (placer.containsValue(bS)) {
                Player own = placerB.get(bS);
                // Inventory i = own.getInventory();
                // i.removeItem(POT);
                own.updateInventory();
                w.createExplosion(loc, 4);
                placerB.remove(bS);

            }
        }
    }

}

// Map configured by psgs. Need your map configured? Send an email to
// psgs@psgs.tk
