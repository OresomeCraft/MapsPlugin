package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.event.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Electricity extends BattleMap implements IBattleMap, Listener {

    public Electricity() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.GOLD_LEGGINGS, Material.GOLD_CHESTPLATE, Material.GOLD_HELMET, Material.GOLD_BOOTS});
    }

    String name = "electricity";
    String fullName = "Electricity";
    String creators = "kingfisher83, danielschroeder, R3creat3 and _Moist";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, 0, 76, 59));
        redSpawns.add(new Location(w, -58, 76, 0));
        redSpawns.add(new Location(w, -16, 76, 32));

        blueSpawns.add(new Location(w, 0, 76, -58));
        blueSpawns.add(new Location(w, 59, 76, 0));
        blueSpawns.add(new Location(w, 18, 76, -30));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, 0, 76, 59));
        FFASpawns.add(new Location(w, -58, 76, 0));
        FFASpawns.add(new Location(w, -16, 76, 32));
        FFASpawns.add(new Location(w, 0, 76, -58));
        FFASpawns.add(new Location(w, 59, 76, 0));
        FFASpawns.add(new Location(w, 18, 76, -30));
        setFFASpawns(name, FFASpawns);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        BOW.getItemMeta().setDisplayName(ChatColor.YELLOW + "Lightning Bow");
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.GOLD_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.GOLD_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -1600);
        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);


        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(15, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 7;
    public int y1 = 66;
    public int z1 = 52;

    //Bottom right corner.
    public int x2 = 93;
    public int y2 = 0;
    public int z2 = -37;

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
