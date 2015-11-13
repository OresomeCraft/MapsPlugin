package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.maps.arcade.games.PaintBallMap;
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

@MapConfig(
        name = "paintball_beta",
        fullName = "Paintball (Beta)",
        creators = {"meganlovesmusic", "Geedubs01", "Ninsai", "SuperDuckFace"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.COOKED_BEEF, Material.SNOW_BALL},
        allowPhysicalDamage = true
)
public class Paintball_Beta extends PaintBallMap implements Listener {

    public Paintball_Beta() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -96, 72, 90));
        FFASpawns.add(new Location(w, -98, 72, 6));
        FFASpawns.add(new Location(w, -80, 73, 8));
        FFASpawns.add(new Location(w, -51, 71, 5));
        FFASpawns.add(new Location(w, -9, 72, 3));
        FFASpawns.add(new Location(w, 6, 72, 27));
        FFASpawns.add(new Location(w, 3, 77, 49));
        FFASpawns.add(new Location(w, 3, 86, 101));
        FFASpawns.add(new Location(w, -27, 85, 100));
        FFASpawns.add(new Location(w, -55, 83, 100));
        FFASpawns.add(new Location(w, -82, 74, 52));
        FFASpawns.add(new Location(w, -49, 81, 75));
        FFASpawns.add(new Location(w, -49, 73, 35));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 128);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

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

                // I don't know if 'PISTON_BLOCK' is a thing.
                if (b.getType().equals(Material.PISTON_BASE)) {
                    if (p.getLocation().getY() == 78) {
                        p.teleport(new Location(w, -45, 94, 53)); // To Top
                    } else if (p.getLocation().getY() == 95) {
                        p.teleport(new Location(w, -45, 77, 53)); // To Bottom
                    }
                }
            }
        }
    }
}
