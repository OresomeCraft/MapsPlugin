package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig
public class SnowyRidge extends BattleMap implements IBattleMap, Listener {

    public SnowyRidge() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET});
        setFireSpread(false);
    }

    String name = "snowyridge";
    String fullName = "Snowy Ridge";
    String creators = "meganlovesmusic, ninsai and SuperDuckFace ";
    Gamemode[] modes = {Gamemode.FFA, Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -65, 62, -3, -88, 0));
        blueSpawns.add(new Location(w, 40, 62, 35, 137, 0));
        Bukkit.getWorld(name).setTime(12000);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -42, 70, -36, -90, 0));
        FFASpawns.add(new Location(w, -42, 53, -36, -90, 0));
        FFASpawns.add(new Location(w, -25, 43, -33, -28, 0));
        FFASpawns.add(new Location(w, -34, 40, -11, -90, 0));
        FFASpawns.add(new Location(w, 0, 35, -35, 46, 0));
        FFASpawns.add(new Location(w, -17, 59, -54, -0, 0));
        FFASpawns.add(new Location(w, -12, 43, 13, 180, 0));
        FFASpawns.add(new Location(w, 24, 35, 3, 107, 0));
        FFASpawns.add(new Location(w, 23, 39, -14, 62, 0));
        FFASpawns.add(new Location(w, 21, 54, -48, 38, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);
        ItemStack SNOWBALLS = new ItemStack(Material.SNOW_BALL, 10);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        InvUtils.nameItem(FISHING_ROD, ChatColor.GOLD + "Grappling Hook");
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, FISHING_ROD);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH_POTION);
        i.setItem(5, EXP);
        i.setItem(6, SNOWBALLS);
        i.setItem(9, ARROWS);
        p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_FALL, 3);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 106;
    public int y1 = 93;
    public int z1 = 98;

    //Bottom right corner.
    public int x2 = -113;
    public int y2 = 25;
    public int z2 = -103;

    @EventHandler(priority = EventPriority.NORMAL)
    public void fishing(PlayerFishEvent event) {
        if (!event.getHook().getWorld().getName().equals(name)) return;
        PlayerFishEvent.State state = event.getState();
        Player p = event.getPlayer();
        ItemStack is = p.getItemInHand();
        Material mat = is.getType();
        Location loc = p.getLocation();
        Location bobber = event.getHook().getLocation();

        if (mat == Material.FISHING_ROD) {
            if (event.getPlayer().getLocation().distanceSquared(event.getHook().getLocation()) < 8) {
                Location head = p.getEyeLocation();
                if (bobber.distanceSquared(head) > 2.6) {
                    p.sendMessage(ChatColor.RED + "You did not throw the grapple far enough!");
                }
            } else if (event.getHook().getVelocity().getY() < 0.02 && isLocationNearBlock(bobber)) {
                p.setFallDistance(0);
                p.playSound(loc, Sound.ARROW_HIT, 1, 1);

                Location loc2 = p.getLocation();//Get the location from the entity

                double deltaX = loc2.getX() - bobber.getX();//Get X Delta
                double deltaZ = loc2.getZ() - bobber.getZ();//Get Z delta
                double deltaY = loc2.getY() - bobber.getY();//Get Y delta

                Vector vec = new Vector(deltaX, deltaY / 2, deltaZ);//Create new vector
                vec.normalize();//Normalize it so we don't shoot the player into oblivion
                p.setVelocity(vec.multiply(-3));
            }
        }
    }

    private boolean isLocationNearBlock(Location loc) {
        World world = loc.getWorld();
        if (!HOLLOW_MATERIALS.contains(loc.getBlock().getTypeId())) return true;
        if (!HOLLOW_MATERIALS.contains(loc.getBlock().getRelative(BlockFace.DOWN).getTypeId())) return true;
        return false;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {

        Player p = event.getPlayer();

        if (p.getLocation().getWorld().getName().equals(name)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Block b = event.getClickedBlock();
                World w = Bukkit.getWorld(name);

                Team team = BattlePlayer.getBattlePlayer(p).getTeamType();
                if (b.getType().equals(Material.PISTON_BASE)) {
                    if (b.getLocation().getBlockY() < 70) {
                        p.teleport(new Location(w, -42, 70, -36, -90, 0)); // To Top
                    } else {
                        p.teleport(new Location(w, -42, 53, -36, -90, 0)); // To Bottom
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getPlayer().getLocation().getWorld().getName().equals(name)) {
            if (event.getTo().equals(new Location(w, -17, 59, -54, -0, 0))) {
                event.getPlayer().sendMessage(ChatColor.BOLD + "CONGRATS! You spawned in Zachoz' house!");
            }
        }
    }
}
