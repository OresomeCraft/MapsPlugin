package com.oresomecraft.maps.arcade.maps;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.*;
import com.oresomecraft.OresomeBattles.api.*;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.*;
import com.oresomecraft.maps.arcade.ArcadeMap;

@MapConfig
public class TNTRun_Beta extends ArcadeMap implements Listener {

    public TNTRun_Beta() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
    }

    // Map details
    String name = "tntrun_beta";
    String fullName = "TNTRun (Beta)";
    String creators = "SuperDuckFace and ScruffyRules ";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -0, 77, -21));
        FFASpawns.add(new Location(w, -15, 77, -15));
        FFASpawns.add(new Location(w, -22, 77, -0));
        FFASpawns.add(new Location(w, -15, 77, 14));
        FFASpawns.add(new Location(w, -0, 77, 21));
        FFASpawns.add(new Location(w, 14, 77, -14));
        FFASpawns.add(new Location(w, 21, 77, -0));
        FFASpawns.add(new Location(w, 14, 77, -15));
        FFASpawns.add(new Location(w, 3, 77, -4));
        FFASpawns.add(new Location(w, -4, 77, -4));
        FFASpawns.add(new Location(w, -4, 77, 3));
        FFASpawns.add(new Location(w, 3, 77, 3));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        i.setItem(0, STEAK);

        p.sendMessage(ChatColor.RED + "RUN!!!");
    }

    boolean hasPassedGrace = false;

    @EventHandler
    public void onLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    hasPassedGrace = true;
                }
            }, 100L);
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        hasPassedGrace = false;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (BattlePlayer.getBattlePlayer(event.getPlayer()).isSpectator()) return;
        Location loc = event.getPlayer().getLocation();
        if (hasPassedGrace) {
            Bukkit.getWorld(name).getBlockAt((int) loc.getX(), ((int) loc.getY() - 1), (int) loc.getZ()).setType(Material.AIR);
        }
    }

}