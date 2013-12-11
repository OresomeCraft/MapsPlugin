package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.games.PaintBallMap;
import com.oresomecraft.maps.arcade.games.RacewayMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Raceway_alpha extends RacewayMap implements Listener {

    public Raceway_alpha() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowPhysicalDamage(false);
        setAllowBuild(false);

        //RacewayMap attributes
        setStartArea(-19, -17, 65, 65, -14, 0);
        setSpawnLocation(-17, 65, -5);
        setFinishArea(-12, -15, 72, 64, 1, -15);
        setFootY(65);
    }

    // Map details
    String name = "raceway_alpha";
    String fullName = "Raceway (Alpha)";
    String creators = "Evil_Emo and Turt1eManLol";
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -17, 65, -5));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, STEAK);
    }


    @EventHandler
    public void moveChecker(PlayerMoveEvent e) {
        if (!e.getPlayer().getWorld().getName().equals(name)) return;
        if (contains(e.getPlayer().getLocation(), -16, -16, 65, 90, -16, 12)) {
            e.getPlayer().sendMessage(ChatColor.RED + "Did you actually try and do that? Race properly next time!");
            e.getPlayer().setHealth(0);
        }
    }
}
