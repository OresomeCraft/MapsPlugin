package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.api.InvUtils;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.GameUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class WarTrauma extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public WarTrauma() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "trauma";
    String fullName = "War Trauma";
    String creators = "_Moist, niceman506 and psgs";
    Gamemode[] modes = {Gamemode.TDM};
    //Hell no to ffa and infection.

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        redSpawns.add(new Location(w, -35, 70, 8));

        blueSpawns.add(new Location(w, -35, 70, 190));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, -35, 70, 8));

        FFASpawns.add(new Location(w, -35, 70, 190));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack FLOWER_POT = new ItemStack(Material.FLOWER_POT, 1);
            ItemStack BREAD = new ItemStack(Material.BREAD, 4);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack GAPPLE = new ItemStack(Material.GOLDEN_APPLE, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 32);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack AMMO = new ItemStack(Material.FLINT, 64);
            ItemStack DIRT = new ItemStack(Material.DIRT, 16);
            ItemStack IRON_SHOVEL = new ItemStack(Material.IRON_SPADE, 1);
            ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);

            ItemMeta bow = BOW.getItemMeta();
            bow.setDisplayName(ChatColor.RED + "Missile Launcher");
            BOW.setItemMeta(bow);

            /*ItemMeta bowLore = BOW.getItemMeta();
            bowLore.setLore("Stand on the sponge in the tanks for extra power!");
            BOW.setItemMeta(bowLore);*/

            ItemMeta arrows = ARROWS.getItemMeta();
            arrows.setDisplayName(ChatColor.RED + "Missiles");
            ARROWS.setItemMeta(arrows);

            ItemMeta blaze_rod = BLAZE_ROD.getItemMeta();
            blaze_rod.setDisplayName(ChatColor.BLUE + "High Power Gun");
            BLAZE_ROD.setItemMeta(blaze_rod);

            ItemMeta pot = FLOWER_POT.getItemMeta();
            pot.setDisplayName(ChatColor.GOLD + "C4");
            FLOWER_POT.setItemMeta(pot);

            ItemMeta ammo = AMMO.getItemMeta();
            ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
            AMMO.setItemMeta(ammo);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_PANTS, LEATHER_BOOTS});

            p.getInventory().setBoots(LEATHER_BOOTS);
            p.getInventory().setLeggings(LEATHER_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BLAZE_ROD);
            i.setItem(2, FLOWER_POT);
            i.setItem(3, IRON_SHOVEL);
            i.setItem(4, BREAD);
            i.setItem(5, DIRT);
            i.setItem(6, AMMO);
            i.setItem(7, GAPPLE);
            i.setItem(8, BOW);
            i.setItem(29, ARROWS);

            //Give players invincibility II and strength II for 15 seconds when they spawn
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 7 * 20, 2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 7 * 20, 2));

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -66;
    public int y1 = 117;
    public int z1 = 199;

    //Bottom right corner.
    public int x2 = -8;
    public int y2 = 56;
    public int z2 = 3;

    // <---------- C4 --------->
    public Map<Player, Block> placer = new HashMap<Player, Block>();
    public Map<Block, Player> placerB = new HashMap<Block, Player>();

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

                            place.setType(Material.BRICK);
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

    // <------- Spire Turrets ------->
    @EventHandler(priority = EventPriority.NORMAL)
    public void explodingArrow(org.bukkit.event.entity.ProjectileHitEvent event) {
        org.bukkit.entity.Entity projectile = event.getEntity();
        World w = projectile.getWorld();
        Location hit = projectile.getLocation();

        if (hit.getWorld().getName().equals(name)) {

            if (projectile instanceof org.bukkit.entity.Arrow) {
                org.bukkit.entity.Arrow arrow = (org.bukkit.entity.Arrow) projectile;
                org.bukkit.entity.Entity shooter = arrow.getShooter();
                Location l = shooter.getLocation();
                Block bl = l.getBlock();
                Block b = bl.getRelative(org.bukkit.block.BlockFace.DOWN, 2);
                Material mat = b.getType();

                if (shooter instanceof Player) {
                    Player p = (Player) shooter;
                    ItemStack is = p.getItemInHand();
                    Material i = is.getType();

                    if (i == Material.BOW && mat == Material.SPONGE) {
                        w.createExplosion(hit, 2);
                        Bukkit.getWorld(name).playEffect(arrow.getLocation(), org.bukkit.Effect.STEP_SOUND, 10);

                    }
                }
            }
        }
    }

    // <--------- Blaze Gun -------->
    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void gun(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        Action a = event.getAction();
        ItemStack i = p.getItemInHand();
        Inventory inv = p.getInventory();
        Material tool = i.getType();
        final World world = loc.getWorld();
        String name = p.getName();

        if (battles.spectator.contains(name) || p.getWorld().equals(name)) {
            return;
        } else {
            if (contains(loc, x1, x2, y1, y2, z1, z2)) {

                if (tool == Material.BLAZE_ROD) {

                    if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {

                        if (inv.contains(Material.FLINT)) {

                            p.launchProjectile(Arrow.class);
                            world.playSound(loc, Sound.COW_WALK, 10, 10);
                            ItemStack AMMO = new ItemStack(Material.FLINT, 1);
                            ItemMeta ammo = AMMO.getItemMeta();
                            ammo.setDisplayName(ChatColor.BLUE + "Ammunition");
                            AMMO.setItemMeta(ammo);
                            inv.removeItem(AMMO);
                            p.updateInventory();

                        }

                    } else {
                        world.playSound(loc, Sound.CLICK, 10, 10);
                    }

                }

            }

        }
    }

    // Sets arrow damage -7 hearts
    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayer(EntityDamageByEntityEvent e) {
        if (e.getEntity().getWorld().equals(name)) {
            Player damaged = (Player) e.getEntity();
            if (e.getDamager() instanceof Projectile) {
                Projectile proj = (Projectile) e.getEntity();
                Arrow arrow = (Arrow) proj;
                if (arrow.getShooter() instanceof Player) {
                    Player shooter = (Player) arrow.getShooter();
                    if (proj instanceof Arrow) {
                        if (arrow.getShooter() instanceof Player) {
                            damaged.damage(14);
                        }
                    }
                }
            }
        }
    }
}
