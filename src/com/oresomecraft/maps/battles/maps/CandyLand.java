package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@MapConfig
public class CandyLand extends BattleMap implements Listener {

    public CandyLand() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.BOW, Material.ARROW, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.WOOD_SWORD});
        lockTime("day");
    }

    String name = "candyland";
    String fullName = "Candy Land";
    String[] creators = {"iVelocityGaming", "BlueVortexed"};
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 35, 68, 0, 90, 0));
        blueSpawns.add(new Location(w, -34, 68, 0, -90, 0));

        setKoTHMonument(new Location(w, 0, 73, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 35, 68, 0, 90, 0));
        FFASpawns.add(new Location(w, -34, 68, 0, -90, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack WOODEN_SWORD = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 12);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
        helmetMeta.setColor(Color.WHITE);
        LEATHER_HELMET.setItemMeta(helmetMeta);
        WOODEN_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 32767);

        p.getInventory().setHelmet(LEATHER_HELMET);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);

        i.setItem(0, WOODEN_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);

    }

    // Top left corner
    public int x1 = 40;
    public int y1 = 97;
    public int z1 = -45;

    // Bottom right corner
    public int x2 = -36;
    public int y2 = 9;
    public int z2 = 49;

}
