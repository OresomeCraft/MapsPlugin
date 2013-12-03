package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Subterrania extends BattleMap implements IBattleMap, Listener {

    public Subterrania() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.STONE_SWORD});
    }

    String name = "subterrania";
    String fullName = "Subterrania";
    String creators = "R3creat3, _Moist and the spuds who didn't help at all; Rynocraft, niceman506 and BlueVortexed";
    Gamemode[] modes = {Gamemode.INFECTION};

    public void readyTDMSpawns() {
        //Do you know what else is a dud, chazzo's face.
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

    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
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

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(4, EXP);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);

    }

    public int x1 = 410;
    public int y1 = 59;
    public int z1 = -329;
    public int x2 = 473;
    public int y2 = 101;
    public int z2 = -228;
}
