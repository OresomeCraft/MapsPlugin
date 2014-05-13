package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class SkyVillage extends BattleMap implements Listener {

    public SkyVillage() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.ARROW, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.IRON_HELMET});
        setFireSpread(false);
    }

    String name = "skyvillage";
    String fullName = "Sky Village";
    String[] creators = {"SuperDuckFace", "ninsai", "ep1cn00bt00b"};
    Gamemode[] modes = {Gamemode.FFA};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 15, 148, 133, 104, 0));
        blueSpawns.add(new Location(w, 15, 148, 133, 104, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 15, 148, 133, 104, 0));
        FFASpawns.add(new Location(w, 3, 145, 132, 115, 0));
        FFASpawns.add(new Location(w, -6, 142, 127, -176, 0));
        FFASpawns.add(new Location(w, -1, 138, 117, 117, 0));
        FFASpawns.add(new Location(w, -16, 135, 109, 112, 0));
        FFASpawns.add(new Location(w, -32, 128, 95, 175, 0));
        FFASpawns.add(new Location(w, -34, 123, 80, 171, 0));
        FFASpawns.add(new Location(w, -45, 90, 53, -156, 0));
        FFASpawns.add(new Location(w, -79, 81, 53, 45, 0));
        FFASpawns.add(new Location(w, -93, 84, 78, 35, 0));
        FFASpawns.add(new Location(w, -101, 88, 91, 41, 0));
        FFASpawns.add(new Location(w, -107, 91, 113, -17, 0));
        FFASpawns.add(new Location(w, -112, 89, 127, -2, 0));
        FFASpawns.add(new Location(w, -108, 88, 144, -36, 0));
        FFASpawns.add(new Location(w, -89, 90, 166, -69, 0));
        FFASpawns.add(new Location(w, -72, 90, 169, -57, 0));
        FFASpawns.add(new Location(w, -42, 94, 183, -91, 0));
        FFASpawns.add(new Location(w, -18, 94, 184, -59, 0));
        FFASpawns.add(new Location(w, 13, 95, 180, -90, 0));
        FFASpawns.add(new Location(w, 38, 95, 187, 178, 0));
        FFASpawns.add(new Location(w, 52, 99, 169, -150, 0));
        FFASpawns.add(new Location(w, 66, 96, 157, 179, 0));
        FFASpawns.add(new Location(w, 78, 95, 134, -161, 0));
        FFASpawns.add(new Location(w, 95, 96, 85, 131, 0));
        FFASpawns.add(new Location(w, -1, 93, 185, -110, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack COOKED_FISH = new ItemStack(Material.COOKED_FISH, 2);
        ItemStack CARROT = new ItemStack(Material.CARROT_ITEM, 3);

        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);

        p.getInventory().setHelmet(IRON_HELMET);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setBoots(IRON_BOOTS);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH_POTION);
        i.setItem(3, COOKED_FISH);
        i.setItem(4, CARROT);
        i.setItem(35, ARROWS);
        i.setItem(8, new ItemStack(Material.ENDER_PEARL, 1));

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 120;
    public int y1 = 204;
    public int z1 = -22;

    //Bottom right corner.
    public int x2 = -109;
    public int y2 = 50;
    public int z2 = 248;

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getWorld().getName().equals(name)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if (block.getType() == Material.PISTON_BASE) {
                    if (block.getLocation().getBlockY() == 153) {
                        player.teleport(new Location(w, 28, 169, 135, -90, 0)); // To Top
                    } else if (block.getLocation().getBlockY() == 170) {
                        player.teleport(new Location(w, 28, 152, 135, -90, 0)); // To Bottom
                    }
                }
            }
        }
    }
}
