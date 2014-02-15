package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig
public class Sandbox extends BattleMap implements IBattleMap, Listener {

    public Sandbox() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{});
    }

    String name = "sandbox";
    String fullName = "Sandbox";
    String creators = "_Moist, Stewpetasuarus, 123Oblivious and WiiiFreak123 ";
    Gamemode[] modes = {Gamemode.INFECTION, Gamemode.LMS};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 50, 75, -23, 128, 0));
        blueSpawns.add(new Location(w, -7, 65, 14, -180, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 50, 75, -23, 128, 0));
        FFASpawns.add(new Location(w, -7, 65, 14, -180, 0));
        FFASpawns.add(new Location(w, -3, 67, -33, -0, 0));
        FFASpawns.add(new Location(w, 19, 87, 9, 178, 0));
        FFASpawns.add(new Location(w, 20, 69, 9, 126, 0));
        FFASpawns.add(new Location(w, -17, 65, -2, -90, 0));
        FFASpawns.add(new Location(w, 2.1, 62, 28, 180, 0));
        FFASpawns.add(new Location(w, 50, 67, -10, 180, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack CARROT = new ItemStack(Material.CARROT_ITEM, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 8);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack WOODEN_SWORD = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);

        IRON_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 2);
        WOODEN_SWORD.addEnchantment(Enchantment.DURABILITY, 5);

        p.getInventory().setBoots(IRON_BOOTS);

        i.setItem(0, WOODEN_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, FISHING_ROD);
        i.setItem(3, CARROT);
        i.setItem(4, HEALTH);
        i.setItem(17, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 65;
    public int y1 = 124;
    public int z1 = -64;
    //Bottom right corner.
    public int x2 = -66;
    public int y2 = 35;
    public int z2 = 58;

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
