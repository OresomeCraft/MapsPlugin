package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@MapConfig
public class HauntedHouse extends BattleMap implements Listener {

    // Top left corner.
    public int x1 = -285;
    public int y1 = 136;
    public int z1 = 103;
    //Bottom right corner.
    public int x2 = -206;
    public int y2 = 69;
    public int z2 = 0;
    // Map details
    String name = "hauntedhouse";
    String fullName = "Haunted House";
    String[] creators = {"bumsonfire"};
    Gamemode[] modes = {Gamemode.FFA};

    public HauntedHouse() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD});
        setTDMTime(5);
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -230, 78, 17, 0, 0);
        Location blueSpawn = new Location(w, -267, 85, 87, -145, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -255, 79, 46, 0, 0));
        redSpawns.add(new Location(w, -269, 83, 25, -68, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -214, 83, 92, 150, 0));
        blueSpawns.add(new Location(w, -258, 80, 64, 90, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -230, 78, 17, 0, 0));
        FFASpawns.add(new Location(w, -267, 85, 87, -145, 0));
        FFASpawns.add(new Location(w, -255, 79, 46, 0, 0));
        FFASpawns.add(new Location(w, -269, 83, 25, -68, 0));
        FFASpawns.add(new Location(w, -214, 83, 92, 150, 0));
        FFASpawns.add(new Location(w, -258, 80, 64, 90, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
        i.setItem(4, EXP);

    }

    @EventHandler
    public void preventMoveOutOfMap(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)
                && !contains(event.getTo(), x1, x2, y1, y2, z1, z2)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You're not allowed of the map! Get back in!");
        }
    }
}
