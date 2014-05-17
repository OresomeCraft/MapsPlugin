package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.maps.MapConfig;
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

@MapConfig
public class Paintball_Charlie extends TeamPaintBallMap implements Listener {

    public Paintball_Charlie() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF, Material.SNOW_BALL});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
    }

    // Map details
    String name = "warehouse";
    String fullName = "Paintball (Charlie)";
    String[] creators = {"meganlovesmusic", "_Husky_", "SuperDuckFace"};
    Gamemode[] modes = {Gamemode.LTS};

    @Override
    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -34, 74, 43));
        FFASpawns.add(new Location(w, 71, 74, 43));
    }

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

        Bukkit.getScheduler().runTask(plugin, new Runnable() {
            public void run() {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000 * 20, 2));
            }
        });
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {

        Player p = event.getPlayer();

        if (p.getLocation().getWorld().getName().equals(name)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Block b = event.getClickedBlock();
                World w = Bukkit.getWorld(name);

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
