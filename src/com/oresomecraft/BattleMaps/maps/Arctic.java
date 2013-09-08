package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Arctic extends BattleMap implements IBattleMap, Listener {

    public Arctic() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "arctic";
    String fullName = "Arctic";
    String creators = "Dant35tra5t";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION, Gamemode.KOTH};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
        Location blueSpawn = new Location(w, 761, 128, -72, 51, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 825, 135, -98, -43, 0));
        redSpawns.add(new Location(w, 834, 132, -84, 60, 0));
        redSpawns.add(new Location(w, 802, 137, -113, -3, 0));
        redSpawns.add(new Location(w, 779, 141, -110, -6, 0));
        redSpawns.add(new Location(w, 807, 159, -114, 7, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 784, 136, -56, -150, 0));
        blueSpawns.add(new Location(w, 810, 135, -49, -179, 0));
        blueSpawns.add(new Location(w, 831, 144, -64, -175, 0));
        blueSpawns.add(new Location(w, 795, 155, -26, -154, 0));
        blueSpawns.add(new Location(w, 812, 128, -52, 173, 0));
        setKoTHMonument(new Location(w, 803, 182, -86));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
        Location blueSpawn = new Location(w, 761, 128, -72, 51, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 795, 155, -28, -172, 0));
        FFASpawns.add(new Location(w, 822, 133, -67, 87, 0));
        FFASpawns.add(new Location(w, 813, 128, -41, -93, 0));
        FFASpawns.add(new Location(w, 773, 121, -110, -58, 0));
        FFASpawns.add(new Location(w, 779, 141, -110, -44, 0));
        FFASpawns.add(new Location(w, 808, 159, -144, -2, 0));
        FFASpawns.add(new Location(w, 820, 129, -68, 129, 0));
        FFASpawns.add(new Location(w, 788, 128, -81, -124, 0));
        FFASpawns.add(new Location(w, 784, 182, -85, -113, 0));
        FFASpawns.add(new Location(w, 761, 132, -133, -27, 0));
        FFASpawns.add(new Location(w, 820, 149, -88, 2, 0));
        FFASpawns.add(new Location(w, 820, 149, -88, 2, 0));
        FFASpawns.add(new Location(w, 779, 130, -87, -87, 0));
        FFASpawns.add(new Location(w, 832, 133, -67, 133, 0));
        FFASpawns.add(new Location(w, 798, 135, -72, -107, 0));
        FFASpawns.add(new Location(w, 790, 128, -78, 177, 0));
        FFASpawns.add(new Location(w, 830, 152, -142, 19, 0));
        FFASpawns.add(new Location(w, 855, 130, -50, 136, 0));
        FFASpawns.add(new Location(w, 798, 148, -74, -134, 0));
        FFASpawns.add(new Location(w, 789, 123, -94, 133, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack SNOWBALL = new ItemStack(Material.SNOW_BALL, 4);
        ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);

        ItemMeta stone_hoe = STONE_HOE.getItemMeta();
        stone_hoe.setDisplayName(ChatColor.BLUE + "Ice hook");
        STONE_HOE.setItemMeta(stone_hoe);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);
        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STONE_HOE);
        i.setItem(8, SNOWBALL);
        i.setItem(5, EXP);
        i.setItem(4, HEALTH_POTION);
        i.setItem(3, STEAK);
        i.setItem(9, ARROWS);
        p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_FALL, 3);


    }

    public int x1 = 715;
    public int y1 = 107;
    public int z1 = -179;
    public int x2 = 903;
    public int y2 = 203;
    public int z2 = 10;

    @EventHandler(priority = EventPriority.NORMAL)
    public void icePick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack i = p.getItemInHand();
        Material mat = i.getType();
        Action a = event.getAction();
        Location loc = p.getLocation();

        ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);
        ItemMeta stone_hoe = STONE_HOE.getItemMeta();
        stone_hoe.setDisplayName(ChatColor.BLUE + "Ice hook");
        STONE_HOE.setItemMeta(stone_hoe);
        World world = Bukkit.getWorld(name);

        if (loc.getWorld().getName().equals(name)) {

            if (mat == Material.STONE_HOE) {

                if (a == Action.LEFT_CLICK_BLOCK) {

                    BlockFace f = event.getBlockFace();
                    Block b = event.getClickedBlock();
                    Material Bmat = b.getType();

                    if (Bmat == Material.STONE || Bmat == Material.ICE) {

                        if (f != BlockFace.UP && f != BlockFace.DOWN) {

                            p.setVelocity(new Vector(0, 1, 0));
                            p.setFallDistance(0);
                            world.playEffect(b.getLocation(), Effect.STEP_SOUND, 79);

                        }

                    }

                }
            }

        }

    }

}
