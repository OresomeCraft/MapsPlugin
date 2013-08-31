package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Hartshire extends BattleMap implements IBattleMap, Listener {

    public Hartshire() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "hartshire";
    String fullName = "Hartshire";
    String creators = "R3creat3, kalikakitty and xannallax33";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 93, 39, -126, -1, 0);
        Location blueSpawn = new Location(w, 188, 45, -130, -178, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 154, 58, -158, -50, 0));
        redSpawns.add(new Location(w, 77, 40, -124, -50, 0));
        blueSpawns.add(new Location(w, 119, 67, -163, -50, 0));
        redSpawns.add(new Location(w, 175, 45, -245, -50, 0));
        blueSpawns.add(new Location(w, 184, 48, -210, -50, 0));
        redSpawns.add(new Location(w, 229, 56, -148, -50, 0));
        blueSpawns.add(new Location(w, 116, 41, -46, -50, 0));
        redSpawns.add(new Location(w, 94, 53, -106, -50, 0));
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 93, 39, -126, -1, 0);
        Location blueSpawn = new Location(w, 188, 45, -130, -178, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 154, 58, -158, -50, 0));
        FFASpawns.add(new Location(w, 77, 40, -124, -50, 0));
        FFASpawns.add(new Location(w, 119, 67, -163, -50, 0));
        FFASpawns.add(new Location(w, 175, 45, -245, -50, 0));
        FFASpawns.add(new Location(w, 184, 48, -210, -50, 0));
        FFASpawns.add(new Location(w, 229, 56, -148, -50, 0));
        FFASpawns.add(new Location(w, 116, 41, -46, -50, 0));
        FFASpawns.add(new Location(w, 94, 53, -106, -50, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 3);
        ItemStack S = new ItemStack(Material.STICK, 1);
        ItemStack I = new ItemStack(Material.IRON_INGOT, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(10, ARROWS);
        i.setItem(11, S);
        i.setItem(12, I);
        i.setItem(4, EXP);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -207;
    public int y1 = 52;
    public int z1 = -1220;

    //Bottom right corner.
    public int x2 = -38;
    public int y2 = 112;
    public int z2 = -1125;

    @EventHandler(priority = EventPriority.NORMAL)
    public void ointment(PlayerInteractEvent event) {
        if (active) {
            Player p = event.getPlayer();
            Action a = event.getAction();
            ItemStack i = p.getItemInHand();
            Inventory inv = p.getInventory();
            Material tool = i.getType();

            if (tool == Material.INK_SACK) {

                if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
                    ItemStack ointment = new ItemStack(p.getItemInHand());
                    ointment.setAmount(1);
                    inv.removeItem(ointment);
                }
            }
        }
    }

}
