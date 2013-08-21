package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.scheduler.BukkitRunnable;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Treetop extends BattleMap implements IBattleMap, Listener {

    public Treetop() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setTDMTime(10);
        setAllowBuild(false);
    }

    String name = "treetop";
    String fullName = "Treetop Warfare";
    String creators = "meganlovesmusic";
    Gamemode[] modes = {Gamemode.KOTH, Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {

        World w = Bukkit.getServer().getWorld(name);

        redSpawns.add(new Location(w, 731, 37, 530));
        redSpawns.add(new Location(w, 724, 37, 512));
        redSpawns.add(new Location(w, 693, 11, 494));

        blueSpawns.add(new Location(w, 740, 5, 488));
        blueSpawns.add(new Location(w, 692, 42, 502));
        blueSpawns.add(new Location(w, 713, 35, 490));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

        setKoTHMonument(new Location(w, 727, 60, 501));

    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        setFFASpawns(name, FFASpawns);
        FFASpawns.add(new Location(w, 731, 37, 530));
        FFASpawns.add(new Location(w, 724, 37, 512));
        FFASpawns.add(new Location(w, 693, 11, 494));

        FFASpawns.add(new Location(w, 740, 5, 488));
        FFASpawns.add(new Location(w, 692, 42, 502));
        FFASpawns.add(new Location(w, 713, 35, 490));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 20);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 3);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH);
        i.setItem(11, ARROWS);
        i.setItem(8, BREAD);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 672;
    public int y1 = 4;
    public int z1 = 464;

    //Bottom right corner.
    public int x2 = 756;
    public int y2 = 4;
    public int z2 = 551;

    public void alwaysNight() {
        new BukkitRunnable() {
            public void run() {
                if (Bukkit.getWorld(name) != null) {
                    Bukkit.getWorld(name).setTime(12000L);
                } else {
                    this.cancel();
                }
            }
        }.runTaskLater(OresomeBattlesMaps.getInstance(), (60 * 20) * 2);
    }

    @EventHandler
    public void onLoad(WorldLoadEvent e) {
        if (e.getWorld().getName().equals(name)) alwaysNight();
    }

}