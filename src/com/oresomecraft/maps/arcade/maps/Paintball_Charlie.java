package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.teams.Team;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.games.TeamPaintBallMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig(
        name = "warehouse",
        fullName = "Paintball (Charlie)",
        creators = {"meganlovesmusic", "_Husky_", "SuperDuckFace"},
        gamemodes = {Gamemode.LTS}
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.COOKED_BEEF, Material.SNOW_BALL}
)
public class Paintball_Charlie extends TeamPaintBallMap implements Listener {

    public Paintball_Charlie() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -34, 74, 43));
        FFASpawns.add(new Location(w, 71, 74, 43));
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(getName());

        redSpawns.add(new Location(w, -34, 74, 43));

        blueSpawns.add(new Location(w, 71, 74, 43));
    }

    public void applyInventory(final BattlePlayer p) {
        final Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 200);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 10);

        i.setItem(0, SNOW_BALL);
        i.setItem(1, STEAK);

        Bukkit.getScheduler().runTask(MapsPlugin.getInstance(), new Runnable() {
            public void run() {
                pl.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000 * 20, 2));
            }
        });
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
                    if (b.getLocation().equals(new Location(w, -38, 75, 43))) {
                        p.teleport(new Location(w, -29, 74, 43)); // red
                    } else if (b.getLocation().equals(new Location(w, 74, 75, 43))) {
                        p.teleport(new Location(w, 66, 75, 43)); // blue
                    }
                }
            }
        }
    }

}
