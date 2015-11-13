package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.teams.Team;
import com.oresomecraft.maps.arcade.games.TeamPaintBallMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "snowyridge",
        fullName = "Paintball (Delta aka. Snowy Ridge)",
        creators = {"meganlovesmusic", "ninsai", "SuperDuckFace"},
        gamemodes = {Gamemode.LTS}
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        disabledDrops = {Material.COOKED_BEEF, Material.SNOW_BALL}
)
public class Paintball_Delta extends TeamPaintBallMap implements Listener {

    public Paintball_Delta() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -42, 70, -36, -90, 0));
        FFASpawns.add(new Location(w, -42, 53, -36, -90, 0));
        FFASpawns.add(new Location(w, -25, 43, -33, -28, 0));
        FFASpawns.add(new Location(w, -34, 40, -11, -90, 0));
        FFASpawns.add(new Location(w, 0, 35, -35, 46, 0));
        FFASpawns.add(new Location(w, -17, 59, -54, -0, 0));
        FFASpawns.add(new Location(w, -12, 43, 13, 180, 0));
        FFASpawns.add(new Location(w, 24, 35, 3, 107, 0));
        FFASpawns.add(new Location(w, 23, 39, -14, 62, 0));
        FFASpawns.add(new Location(w, 21, 54, -48, 38, 0));
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(getName());
        redSpawns.add(new Location(w, -65, 62, -3, -88, 0));
        blueSpawns.add(new Location(w, 40, 62, 35, 137, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 200);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 10);

        i.setItem(0, SNOW_BALL);
        i.setItem(1, STEAK);

    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {

        Player p = event.getPlayer();

        if (p.getLocation().getWorld().getName().equals(getName())) {

            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Block b = event.getClickedBlock();
                World w = Bukkit.getWorld(getName());

                Team team = BattlePlayer.getBattlePlayer(p).getTeamType();
                if (b.getType().equals(Material.PISTON_BASE)) {
                    if (b.getLocation().getBlockY() == 54) {
                        p.teleport(new Location(w, -42, 70, -36, -90, 0)); // To Top
                    } else if (b.getLocation().getBlockY() == 71) {
                        p.teleport(new Location(w, -42, 53, -36, -90, 0)); // To Bottom
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getPlayer().getLocation().getWorld().getName().equals(getName())) {
            if (event.getTo().equals(new Location(w, -17, 59, -54, -0, 0))) {
                event.getPlayer().sendMessage(ChatColor.BOLD + "CONGRATS! You spawned in Zachoz's house!");
            }
        }
    }

}
