package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.*;

import com.oresomecraft.OresomeBattles.api.*;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig
public class SkyFights extends BattleMap implements IBattleMap, Listener {

    public SkyFights() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.GOLD_BOOTS, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_HELMET, Material.IRON_SWORD, Material.BOW});
        setAllowBuild(true);
    }

    // Map details
    String name = "skyfights";
    String fullName = "Sky Fights";
    String creators = "Turt1eManLol";
    Gamemode[] modes = {Gamemode.INFECTION, Gamemode.TDM, Gamemode.KOTH};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -62, 162, -66, -30, 0);
        Location blueSpawn = new Location(w, 53, 157, 88, 137, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setKoTHMonument(new Location(w, 3, 158, 9));
    }

    public void readyFFASpawns() {

        FFASpawns.add(new Location(w, -62, 162, -66, -30, 0));
        FFASpawns.add(new Location(w, 53, 157, 88, 137, 0));
        FFASpawns.add(new Location(w, 27, 167, 87, 89, 0));
        FFASpawns.add(new Location(w, -20, 174, 77, -176, 0));
        FFASpawns.add(new Location(w, -104, 170, 25, -173, 0));
        FFASpawns.add(new Location(w, 81, 161, -51, 33, 0));

    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);
        ItemStack LOG = new ItemStack(Material.LOG, 64);
        ItemStack STONE = new ItemStack(Material.STONE, 64);

        ItemMeta fishing_rod = FISHING_ROD.getItemMeta();
        fishing_rod.setDisplayName(ChatColor.BLUE + "Grappling hook");
        FISHING_ROD.setItemMeta(fishing_rod);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, FISHING_ROD);
        i.setItem(3, HEALTH);
        i.setItem(4, STEAK);
        i.setItem(5, LOG);
        i.setItem(6, STONE);
        i.setItem(11, ARROWS);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

    }

    // Top left corner.
    public int x1 = -84;
    public int y1 = 153;
    public int z1 = 43;

    //Bottom right corner.
    public int x2 = 22;
    public int y2 = 23;
    public int z2 = -67;

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

}
