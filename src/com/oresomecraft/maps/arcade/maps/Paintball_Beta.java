package com.oresomecraft.maps.arcade.maps;
 
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.games.PaintBallMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
 
@MapConfig
public class Paintball_Beta extends PaintBallMap implements Listener {
 
    public Paintball_Beta() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.COOKED_BEEF, Material.SNOW_BALL});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
    }
 
    // Map details
    String name = "hideandgoseek"; // Maybe 'hideandgoseek'?
    String fullName = "Paintball (Beta)";
    String creators = "Meganlovesmusic, Geedubs01, Ninsai and SuperDuckFace";
    Gamemode[] modes = {Gamemode.LMS};
 
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
        Inventory i = p.getInventory();
 
        ItemStack SNOW_BALL = new ItemStack(Material.SNOW_BALL, 128);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
 
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
                
                // I don't know if 'PISTON_BLOCK' is a thing.
                if (b.getType().equals(Material.PISTON_BLOCK)) {
                    if (p.getLocation().getY() < 80) {
                        p.teleport(new Location(w, -45, 94, 53));
                    } else if (p.getLocation().getY() > 90) {
                        p.teleport(new Location(w, -45, 77, 53));
                    }
                }
            }
        }
    }
}
