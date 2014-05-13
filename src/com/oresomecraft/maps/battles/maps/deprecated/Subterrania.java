package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig
public class Subterrania extends BattleMap implements Listener {

    public Subterrania() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.STONE_SWORD, Material.RAILS});
        setFireSpread(false);
    }

    String name = "subterrania";
    String fullName = "Subterrania";
    String[] creators = {" __R3", "_Moist"};
    Gamemode[] modes = {Gamemode.FFA, Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 18, 28, 84));
        redSpawns.add(new Location(w, 13, 25, 61));
        redSpawns.add(new Location(w, -10, 25, 38));
        redSpawns.add(new Location(w, -53, 23, 19));
        blueSpawns.add(new Location(w, -110, 21, 43));
        blueSpawns.add(new Location(w, -83, 21, 13));
        blueSpawns.add(new Location(w, -128, 49, 32));
        blueSpawns.add(new Location(w, -70, 49, 50));
        blueSpawns.add(new Location(w, -77, 50, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 18, 28, 84));
        FFASpawns.add(new Location(w, 13, 25, 61));
        FFASpawns.add(new Location(w, -10, 25, 38));
        FFASpawns.add(new Location(w, -53, 23, 19));
        FFASpawns.add(new Location(w, -110, 21, 43));
        FFASpawns.add(new Location(w, -83, 21, 13));
        FFASpawns.add(new Location(w, -128, 49, 32));
        FFASpawns.add(new Location(w, -70, 49, 50));
        FFASpawns.add(new Location(w, -77, 50, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 6);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack MASK = new ItemStack(Material.RAILS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(MASK);

        p.getInventory().getHelmet().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 5000 * 20, 5000 * 20));

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(4, EXP);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }

    public int x1 = -163;
    public int y1 = 95;
    public int z1 = 143;
    public int x2 = 58;
    public int y2 = 2;
    public int z2 = -27;
}
