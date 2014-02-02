package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.util.Vector;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Roseley extends BattleMap implements IBattleMap, Listener {

    public Roseley() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.STONE_SWORD, Material.LEATHER_HELMET});
    }

    String name = "roseley";
    String fullName = "Roseley";
    String creators = "simonwilson123, DynaDavidson, meganlovesmusic, MintyPvP, callumary, PKBeam and __R3";
    Gamemode[] modes = {Gamemode.KOTH, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -131, 70, 1));
        blueSpawns.add(new Location(w, -74, 70, 126));

        setKoTHMonument(new Location(w, -104, 71, 63));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -131, 70, 1));
        FFASpawns.add(new Location(w, -74, 70, 126));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack JUMP = new ItemStack(Material.FIREWORK, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        setColouredArmorAccordingToTeam(p);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, JUMP);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(10, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 158;
    public int y1 = 139;
    public int z1 = -160;
    //Bottom right corner.
    public int x2 = -171;
    public int y2 = 54;
    public int z2 = 156;

    @EventHandler
    public void onBlockPlace(PlayerInteractEvent event) {
        if (getArena().equals(name)) {
            Player p = event.getPlayer();
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (p.getItemInHand().getType() == Material.FIREWORK) {
                    p.getInventory().removeItem(new ItemStack(Material.FIREWORK, 1));
                    p.setVelocity(new Vector(0, 1.5, 0));
                }
            }
        }
    }

}
