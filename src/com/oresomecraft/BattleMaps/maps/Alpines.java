package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.util.Vector;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Alpines extends BattleMap implements IBattleMap, Listener {

    public Alpines() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
    }

    String name = "alpines";
    String fullName = "Alpines";
    String creators = "R3creat3, simonwilson123 and Evil_Emo";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 2, 84, -48));
        blueSpawns.add(new Location(w, -3, 84, 58));

        setKoTHMonument(new Location(w, -1, 133, 4));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 2, 84, -48));
        FFASpawns.add(new Location(w, -3, 84, 58));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 4);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);

        InvUtils.nameItem(STONE_HOE, ChatColor.BLUE + "Dirt Hook");

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH);
        i.setItem(3, STONE_HOE);
        i.setItem(11, ARROWS);
        i.setItem(8, new ItemStack(Material.BREAD, 3));

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

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
        Player p = event.getPlayer();
        ItemStack i = p.getItemInHand();
        Material mat = i.getType();
        Action a = event.getAction();
        Location loc = p.getLocation();
        World world = Bukkit.getWorld(name);

        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (mat == Material.STONE_HOE) {
                if (a == Action.LEFT_CLICK_BLOCK) {
                    BlockFace f = event.getBlockFace();
                    Block b = event.getClickedBlock();
                    Material Bmat = b.getType();
                    if (Bmat == Material.DIRT || Bmat == Material.GRASS) {
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
