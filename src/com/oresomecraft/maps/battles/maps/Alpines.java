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
public class Alpines extends BattleMap implements IBattleMap, Listener {

    public Alpines() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.STONE_SWORD, Material.DIAMOND_BOOTS});
        setAutoSpawnProtection(4);
    }

    String name = "alpines";
    String fullName = "Alpines";
    String creators = "simonwilson123, Evil_Emo and __R3";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 2, 84, -48, -1, 0));
        blueSpawns.add(new Location(w, -3, 84, 58, -178, 0));

        setKoTHMonument(new Location(w, -1, 115, 4));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 2, 84, -48, -1, 0));
        FFASpawns.add(new Location(w, -3, 84, 58, -178, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 4);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);
        ItemStack BOOTS = new ItemStack(Material.DIAMOND_BOOTS);

        BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);
        InvUtils.nameItem(STONE_HOE, ChatColor.BLUE + "Dirt Hook");

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH);
        i.setItem(3, STONE_HOE);
        i.setItem(11, ARROWS);
        i.setItem(4, new ItemStack(Material.BREAD, 3));

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 29;
    public int y1 = 142;
    public int z1 = 56;

    //Bottom right corner.
    public int x2 = -203;
    public int y2 = 42;
    public int z2 = -72;

    @EventHandler(priority = EventPriority.NORMAL)
    public void dirtPick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Material material = itemStack.getType();
        Action action = event.getAction();
        World world = Bukkit.getWorld(name);

        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (material.equals(Material.STONE_HOE)) {
                if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                    BlockFace blockFace = event.getBlockFace();
                    Block block = event.getClickedBlock();
                    Material blockMaterial = block.getType();
                    if (blockMaterial.equals(Material.DIRT) || blockMaterial.equals(Material.GRASS)) {
                        if (!blockFace.equals(BlockFace.UP) && !blockFace.equals(BlockFace.DOWN)) {
                            player.setVelocity(new Vector(0, 1, 0));
                            player.setFallDistance(0);
                            world.playEffect(block.getLocation(), Effect.STEP_SOUND, 3);
                        }
                    }
                }
            }
        }
    }

}
