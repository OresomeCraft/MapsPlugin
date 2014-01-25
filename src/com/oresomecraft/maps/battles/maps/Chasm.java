package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.util.Vector;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Chasm extends BattleMap implements IBattleMap, Listener {

    public Chasm() {
        setAllowBuild(false);
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
        lockTime("day");
        setAutoSpawnProtection(10);
    }

    String name = "chasm";
    String fullName = "The Chasm";
    String creators = "__R3, danielschroeder, Spantezian and DynaDavidson";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.KOTH};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 9, 92, -155);
        Location blueSpawn = new Location(w, 93, 92, 28);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, 48, 88, -132);
        Location blueFlag = new Location(w, 54, 88, 4);
        setCTFFlags(name, redFlag, blueFlag);
        setKoTHMonument(new Location(w, 51, 92, -64));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 9, 92, -155);
        Location blueSpawn = new Location(w, 93, 92, 28);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        InvUtils.nameItem(STONE_HOE, ChatColor.BLUE + "Ice Hook");
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        LEATHER_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(2, STONE_HOE);
        i.setItem(10, ARROWS);

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void icePick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack i = p.getItemInHand();
        Material mat = i.getType();
        Action a = event.getAction();
        World world = Bukkit.getWorld(name);

        if (event.getPlayer().getWorld().getName().equals(name)) {
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
