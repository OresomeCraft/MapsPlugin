package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;

import com.oresomecraft.OresomeBattles.api.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@MapConfig
public class BiomeBattle extends BattleMap implements Listener {

    public BiomeBattle() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.BLAZE_ROD, Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.IRON_SWORD, Material.LEATHER_HELMET});
        setAllowBuild(false);
    }

    // Map details
    String name = "biomebattle";
    String fullName = "BiomeBattle";
    String[] creators = {"SuperDuckFace", "Evil_Emo"};
    Gamemode[] modes = {Gamemode.FFA, Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -61, 73, -54));
        blueSpawns.add(new Location(w, 48, 72, 42));

        setKoTHMonument(new Location(w, 0, 67, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -61, 73, -54));
        FFASpawns.add(new Location(w, 48, 72, 42));
        FFASpawns.add(new Location(w, -69, 72, 63));
        FFASpawns.add(new Location(w, -64, 70, 12));
        FFASpawns.add(new Location(w, -51, 87, 10));
        FFASpawns.add(new Location(w, -47, 80, -41));
        FFASpawns.add(new Location(w, -17, 70, -84));
        FFASpawns.add(new Location(w, 83, 78, -22));
        FFASpawns.add(new Location(w, 13, 69, -3));
        FFASpawns.add(new Location(w, 15, 74, 40));
        FFASpawns.add(new Location(w, -83, 74, -7));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack LEATHER_CAP = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);

        ItemMeta fMeta = BLAZE_ROD.getItemMeta();
        fMeta.setDisplayName(ChatColor.BLUE + "Levitation Rod");

        List<String> fLore = new ArrayList<String>();
        fLore.add(ChatColor.BLUE + "Interact with this to hover into the air!");
        fMeta.setLore(fLore);
        BLAZE_ROD.setItemMeta(fMeta);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_PANTS, LEATHER_CAP, LEATHER_BOOTS});

        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_CAP);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH_POTION);
        i.setItem(2, STEAK);
        i.setItem(4, BLAZE_ROD);
        i.setItem(8, ARROW);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 127;
    public int y1 = 153;
    public int z1 = 115;

    //Bottom right corner.
    public int x2 = -134;
    public int y2 = 25;
    public int z2 = -112;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) {
                    ItemStack FIRE = new ItemStack(Material.BLAZE_ROD, 1);
                    ItemMeta fMeta = FIRE.getItemMeta();
                    fMeta.setDisplayName(ChatColor.BLUE + "Levitation Rod");

                    List<String> fLore = new ArrayList<String>();
                    fLore.add(ChatColor.BLUE + "Interact with this to hover into the air!");
                    fMeta.setLore(fLore);
                    FIRE.setItemMeta(fMeta);
                    event.getPlayer().getInventory().removeItem(FIRE);
                    event.getPlayer().setVelocity(new Vector(0, 1, 0));
                }
            }
        }
    }
}
