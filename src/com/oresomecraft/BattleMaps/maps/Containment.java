package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Containment extends BattleMap implements IBattleMap, Listener {

    public Containment() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{});
    }

    String name = "containment";
    String fullName = "Containment Breach";
    String creators = "R3creat3, MiCkEyMiCE, LanderA and _Moist";
    Gamemode[] modes = {Gamemode.LMS, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        //Don't need anything.
    }

    public void readyFFASpawns() {
        //Main spawn
        FFASpawns.add(new Location(w, 9, 65, 5));

        //Sector D
        FFASpawns.add(new Location(w, 9, 62, 16));
        FFASpawns.add(new Location(w, 7, 47, 27));
        FFASpawns.add(new Location(w, 8, 39, 19));
        FFASpawns.add(new Location(w, 31, 54, -58));
        FFASpawns.add(new Location(w, 9, 59, -17));

        //Sector C
        FFASpawns.add(new Location(w, 10, 65, -17));
        FFASpawns.add(new Location(w, -4, 55, -2));
        FFASpawns.add(new Location(w, 0, 65, -20));
        FFASpawns.add(new Location(w, 11, 71, -31));
        FFASpawns.add(new Location(w, 0, 73, -15));

        //Sector B  (small sector)
        FFASpawns.add(new Location(w, 31, 62, 6));
        FFASpawns.add(new Location(w, 44, 53, -5));
        FFASpawns.add(new Location(w, 24, 39, -24));

        //Sector A
        FFASpawns.add(new Location(w, -4, 65, 19));
        FFASpawns.add(new Location(w, -30, 73, 5));
        FFASpawns.add(new Location(w, -48, 73, -14));
        FFASpawns.add(new Location(w, -17, 73, -15));
        FFASpawns.add(new Location(w, -27, 39, -5));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, STEAK);
        i.setItem(2, HEALTH_POTION);
        i.setItem(28, ARROWS);


    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 0;
    public int y1 = 1;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 128;
    public int y2 = 255;
    public int z2 = 128;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (contains(event.getPlayer().getLocation(), -2, -16, 55, 69, 0, -11)) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 3 * 20, 0));
            }

        }
    }

    @EventHandler
    public void onKeycard(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action a = event.getAction();
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (a == Action.LEFT_CLICK_BLOCK) {
                if (p.getItemInHand().getType() == Material.PAPER) {
                    if (event.getClickedBlock().getType() == Material.WOOL) {
                        if (event.getClickedBlock().getData() == 14) {
                            Location loc = event.getClickedBlock().getLocation();
                            loc.setY(loc.getY() + 1);
                            event.getPlayer().teleport(loc);
                        }
                    }
                }
            }
        }
    }
}
