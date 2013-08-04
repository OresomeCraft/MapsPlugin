package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class MutinyII extends BattleMap implements IBattleMap, Listener {

    public MutinyII() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "mutinyii";
    String fullName = "Mutiny II";
    String creators = "R3creat3, zachoz, Buster1824 and MiCkEyMiCE";
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, 32, 94, 12));
        redSpawns.add(new Location(w, 3, 85, 6));
        redSpawns.add(new Location(w, -24, 91, 13));
        blueSpawns.add(new Location(w, 31, 91, -58));
        blueSpawns.add(new Location(w, 3, 85, -51));
        blueSpawns.add(new Location(w, -25, 94, -57));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, 31, 91, -58));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
            p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
            p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
            i.setItem(0, new ItemStack(Material.IRON_SWORD, 1));
            i.setItem(1, new ItemStack(Material.BOW, 1));
            i.setItem(2, new ItemStack(Material.IRON_PICKAXE, 1));
            i.setItem(3, new ItemStack(Material.IRON_AXE, 1));
            i.setItem(4, new ItemStack(Material.BAKED_POTATO, 3));
            i.setItem(5, new ItemStack(Material.LOG, 64, (short) 1));
            i.setItem(6, new ItemStack(Material.GOLDEN_APPLE, 3));
            i.setItem(9, new ItemStack(Material.ARROW, 64));
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 8 * 20, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 8 * 20, 1));
        }
    }

    public int x1 = 52;
    public int y1 = 54;
    public int z1 = 35;

    public int x2 = -48;
    public int y2 = 156;
    public int z2 = -75;
}
