package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class ElementsII extends BattleMap implements IBattleMap, Listener {

    public ElementsII() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
    }

    String name = "elements2";
    String fullName = "Elements II";
    String creators = "_Moist, psgs and broddikill";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -160, 70, -1, 90, 0));
        redSpawns.add(new Location(w, -160, 70, -12, 90, 0));

        blueSpawns.add(new Location(w, -3, 70, 0, -90, 0));
        blueSpawns.add(new Location(w, -3, 70, 12, -90, 0));

        setKoTHMonument(new Location(w, -83, 115, -1));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -160, 70, -1, 90, 0));
        FFASpawns.add(new Location(w, -160, 70, -12, 90, 0));
        FFASpawns.add(new Location(w, -3, 70, 0, -90, 0));
        FFASpawns.add(new Location(w, -3, 70, 12, -90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack FIRE = new ItemStack(Material.POTION, 1, (short) 8227);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        if (p.getTeam() == Team.KOTH_RED) {
            i.setItem(7, FIRE);
        }

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH);
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

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getWorld().getName().equals(name)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Block b = event.getClickedBlock();
                World w = Bukkit.getWorld(name);

                if (BattlePlayer.getBattlePlayer(p).getTeam() == Team.KOTH_RED) {
                    if (b.getType().equals(Material.REDSTONE_BLOCK)) {
                        p.teleport(new Location(w, -111, 97, 14, 90, 0));
                    }
                } else {
                    if (BattlePlayer.getBattlePlayer(p).getTeam() == Team.KOTH_BLUE) {
                        if (b.getType().equals(Material.LAPIS_BLOCK)) {
                            p.teleport(new Location(w, -53, 97, -14, -90, 0));
                        }
                    }
                }
            }
        }
    }
}
