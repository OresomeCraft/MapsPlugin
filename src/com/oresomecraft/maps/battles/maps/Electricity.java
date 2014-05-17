package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.event.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class Electricity extends BattleMap implements Listener {

    public Electricity() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.ARROW, Material.BOW, Material.GOLD_LEGGINGS, Material.GOLD_CHESTPLATE, Material.GOLD_HELMET, Material.GOLD_BOOTS, Material.STONE_SWORD});
    }

    String name = "electricity";
    String fullName = "Electricity";
    String[] creators = {"kingfisher83", "danielschroeder", " __R3", "_Moist"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 0, 76, 59));
        redSpawns.add(new Location(w, -58, 76, 0));
        redSpawns.add(new Location(w, -16, 76, 32));

        blueSpawns.add(new Location(w, 0, 76, -58));
        blueSpawns.add(new Location(w, 59, 76, 0));
        blueSpawns.add(new Location(w, 18, 76, -30));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 76, 59));
        FFASpawns.add(new Location(w, -58, 76, 0));
        FFASpawns.add(new Location(w, -16, 76, 32));
        FFASpawns.add(new Location(w, 0, 76, -58));
        FFASpawns.add(new Location(w, 59, 76, 0));
        FFASpawns.add(new Location(w, 18, 76, -30));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);

        ItemStack GOLD_HELMET = new ItemStack(Material.GOLD_HELMET, 1);
        ItemStack GOLD_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack GOLD_BOOTS = new ItemStack(Material.GOLD_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -1600);

        BOW.getItemMeta().setDisplayName(ChatColor.YELLOW + "Lightning Bow");

        p.getInventory().setBoots(GOLD_BOOTS);
        p.getInventory().setLeggings(GOLD_PANTS);
        p.getInventory().setChestplate(GOLD_CHESTPLATE);
        p.getInventory().setHelmet(GOLD_HELMET);


        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(15, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -69;
    public int y1 = 124;
    public int z1 = 82;

    //Bottom right corner.
    public int x2 = 64;
    public int y2 = 65;
    public int z2 = -85;

    @EventHandler(priority = EventPriority.NORMAL)
    public void arrowboom(ProjectileHitEvent event) {
        if (event.getEntity().getWorld().getName().equals(name)) {
            if (event.getEntity() instanceof Arrow) {
                event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
                event.getEntity().remove();
            }
        }

    }

}
