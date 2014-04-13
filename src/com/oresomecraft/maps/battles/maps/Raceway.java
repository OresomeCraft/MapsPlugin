package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Raceway extends BattleMap implements Listener {

    public Raceway() {
        setAllowBuild(false);
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
    }

    String name = "raceway_alpha";
    String fullName = "Raceway";
    String creators = "Evil_Emo and Turt1eManLol";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {

        redSpawns.add(new Location(w, -40, 65, -94));
        redSpawns.add(new Location(w, -6, 72, -111));
        redSpawns.add(new Location(w, -6, 72, -77));
        redSpawns.add(new Location(w, 19, 65, -160));

        blueSpawns.add(new Location(w, -95, 65, -86));
        blueSpawns.add(new Location(w, -40, 72, 16));
        blueSpawns.add(new Location(w, 9, 72, 16));
        blueSpawns.add(new Location(w, 30, 65, -16));

    }

    public void readyFFASpawns() {

        FFASpawns.add(new Location(w, -40, 65, -94));
        FFASpawns.add(new Location(w, -6, 72, -111));
        FFASpawns.add(new Location(w, -6, 72, -77));
        FFASpawns.add(new Location(w, 19, 65, -160));

        FFASpawns.add(new Location(w, -95, 65, -86));
        FFASpawns.add(new Location(w, -40, 72, 16));
        FFASpawns.add(new Location(w, 9, 72, 16));
        FFASpawns.add(new Location(w, 30, 65, -16));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(10, ARROWS);

    }
}
