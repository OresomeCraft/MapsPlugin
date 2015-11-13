package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "arsvilla",
        fullName = "Arsvilla",
        creators = {"ShaunDepro97"},
        gamemodes = {Gamemode.KOTH, Gamemode.LTS, Gamemode.LMS}
)
@Region(
        x1 = -61,
        y1 = 48,
        z1 = -58,
        x2 = 61,
        y2 = 112,
        z2 = 58
)
@Attributes(
        allowBuild = true,
        fireSpread = false,
        lives = 3,
        disabledDrops = {Material.BOW, Material.IRON_SWORD, Material.LEATHER_BOOTS,
                Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE,
                Material.IRON_HELMET, Material.COOKED_BEEF}
)
public class Arsvilla extends BattleMap {

    public Arsvilla() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 14, 75, -44, 0, 0));
        redSpawns.add(new Location(w, 2, 78, -49, 1, 0));
        redSpawns.add(new Location(w, 36, 69, -10, 2, 0));

        blueSpawns.add(new Location(w, -15, 66, -20, 0, 0));
        blueSpawns.add(new Location(w, -45, 69, -12, 1, 0));
        blueSpawns.add(new Location(w, 5, 66, 46, 2, 0));

        setKoTHMonument(new Location(w, -4, 88, -12));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 14, 75, -44, 0, 0));
        FFASpawns.add(new Location(w, 2, 78, -49, 1, 0));
        FFASpawns.add(new Location(w, 36, 69, -10, 2, 0));
        FFASpawns.add(new Location(w, -15, 66, -20, 3, 0));
        FFASpawns.add(new Location(w, -45, 69, -12, 2, 0));
        FFASpawns.add(new Location(w, 5, 66, 46, 1, 0));
        FFASpawns.add(new Location(w, 50, 67, 33, 0, 0));
        FFASpawns.add(new Location(w, 43, 69, 0, 1, 0));
        FFASpawns.add(new Location(w, 14, 82, 24, 2, 0));
        FFASpawns.add(new Location(w, -11, 81, -10, 3, 0));
        FFASpawns.add(new Location(w, -18, 66, -29, 2, 0));
        FFASpawns.add(new Location(w, -19, 77, -50, 1, 0));
        FFASpawns.add(new Location(w, 21, 69, 16, 0, 0));
        FFASpawns.add(new Location(w, 8, 66, -20, 1, 0));
        FFASpawns.add(new Location(w, 47, 75, -30, 2, 0));
        FFASpawns.add(new Location(w, 45, 77, 9, 3, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 24);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack CHAIN_TOP = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
        ItemStack CHAIN_LEGS = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setLeggings(CHAIN_LEGS);
        pl.getInventory().setChestplate(CHAIN_TOP);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }

    @EventHandler
    public void protection(org.bukkit.event.block.BlockBreakEvent event) {
        org.bukkit.block.Block b = event.getBlock();
        int mat = b.getTypeId();
        if (event.getBlock().getLocation().getWorld().getName().equals(getName())) {
            if (mat != 102) { //Make glass pane breakable
                event.setCancelled(true);
            }
        }
    }

}
