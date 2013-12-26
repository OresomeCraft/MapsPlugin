package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.maps.MapConfig;

import com.oresomecraft.maps.arcade.games.TeamPaintBallMap;
import org.bukkit.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Paintball_Charlie extends TeamPaintBallMap implements Listener {

    public Paintball_Charlie() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF, Material.SNOW_BALL});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
    }

    @Override
    public void readyFFASpawns() {
        // Nothing to see here. :-)
    }

    // Map details
    String name = "warehouse";
    String fullName = "Paintball (Charlie)";
    String creators = "meganlovesmusic, SuperDuckFace and _Husky_";
    Gamemode[] modes = {Gamemode.LTS};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        redSpawns.add(new Location(w, -34, 74, 43));

        blueSpawns.add(new Location(w, 71, 74, 43));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 200);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 10);

        i.setItem(0, SNOW_BALL);
        i.setItem(1, STEAK);
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getWorld().getName().equals(name)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Block b = event.getClickedBlock();
                World w = Bukkit.getWorld(name);

                Team team = BattlePlayer.getBattlePlayer(p).getTeam();
                if (b.getType().equals(Material.PISTON_BASE)) {
                    if (team == Team.LTS_RED) {
                        p.teleport(new Location(w, -29, 74, 43));
                    } else if (team == Team.LTS_BLUE) {
                        p.teleport(new Location(w, -66, 75, 43));
                    }
                }
            }
        }
    }
}
