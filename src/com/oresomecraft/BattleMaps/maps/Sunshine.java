package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.api.InvUtils;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemodes.TDM;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.World;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Sunshine extends BattleMap implements IBattleMap, Listener {

    public Sunshine() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setAllowBuild(false);
    }

    String name = "sunshine";
    String fullName = "Sunshine";
    String creators = "R3creat3, am51407 and _Moist";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);
        blueSpawns.add(new Location(w, -37, 89, 2));
        redSpawns.add(new Location(w, 32, 89, 2));
        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
        setKoTHMonument(new Location(w, -3, 88, 2));
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -37, 89, 2));
        FFASpawns.add(new Location(w, 32, 89, 2));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            //Items
            ItemStack IRON_AXE = new ItemStack(Material.WOOD_SWORD, 1);
            ItemStack BREAD = new ItemStack(Material.BREAD, 8);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROW = new ItemStack(Material.ARROW, 8);
            //prevent arrow camping
            ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);

            // Armor
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);

            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setBoots(LEATHER_BOOTS);
            p.getInventory().setLeggings(LEATHER_PANTS);
            p.getInventory().setHelmet(LEATHER_HELMET);


            i.setItem(0, IRON_AXE);
            i.setItem(1, BOW);
            i.setItem(2, BREAD);
            i.setItem(3, HEALTH);
            i.setItem(27, ARROW);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_BOOTS, LEATHER_PANTS, LEATHER_HELMET});

            //Give players invincibility and strength for 7 seconds when they spawn
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 7 * 20, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 7 * 20, 1));

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -100;
    public int y1 = 160;
    public int z1 = -70;

    //Bottom right corner.
    public int x2 = -70;
    public int y2 = 30;
    public int z2 = 50;

    //Clears armor drops
    @EventHandler(priority = EventPriority.NORMAL)
    public void death(PlayerDeathEvent event) {

        List<ItemStack> drops = event.getDrops();
        int amount = drops.size();
        int count = 0;

        for (int none = 0; none < amount; none++) {

            ItemStack i = drops.get(count);
            count++;
            Material mat = i.getType();

            if (mat == Material.BOW || mat == Material.LEATHER_BOOTS
                    || mat == Material.LEATHER_LEGGINGS
                    || mat == Material.LEATHER_CHESTPLATE
                    || mat == Material.LEATHER_HELMET) {

                i.setType(Material.AIR);

            }

        }
    }

}
