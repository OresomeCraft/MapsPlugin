package com.oresomecraft.maps.battles.maps;

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

@MapConfig
public class Mansion extends BattleMap implements Listener {

    public Mansion() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.SKULL_ITEM, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.STONE_SWORD});
        setFireSpread(false);
    }

    String name = "mansion";
    String fullName = "The Haunted Mansion";
    String[] creators = {"pegabeavercorn", "Hourani95", "kevlar_miner"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 442, 64, -324);
        Location blueSpawn = new Location(w, 442, 64, -324);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, 472, 64, -228));
        blueSpawns.add(new Location(w, 412, 64, -229));
        redSpawns.add(new Location(w, 428, 64, -311));
        blueSpawns.add(new Location(w, 454, 64, -311));
        redSpawns.add(new Location(w, 472, 64, -289));
        blueSpawns.add(new Location(w, 416, 66, -305));
        redSpawns.add(new Location(w, 416, 66, -305));
        blueSpawns.add(new Location(w, 414, 64, -279));
        redSpawns.add(new Location(w, 415, 64, -269));
        blueSpawns.add(new Location(w, 412, 64, -266));
        redSpawns.add(new Location(w, 419, 71, -260));
        blueSpawns.add(new Location(w, 416, 74, -307));
        redSpawns.add(new Location(w, 446, 74, -271));
        blueSpawns.add(new Location(w, 467, 74, -309));
        redSpawns.add(new Location(w, 416, 74, -305));
        blueSpawns.add(new Location(w, 454, 72, -267));
        redSpawns.add(new Location(w, 467, 64, -264));
        blueSpawns.add(new Location(w, 440, 71, -270));
        redSpawns.add(new Location(w, 465, 74, -274));
        blueSpawns.add(new Location(w, 466, 74, -251));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 442, 64, -324);
        Location blueSpawn = new Location(w, 442, 64, -324);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 472, 64, -228));
        FFASpawns.add(new Location(w, 412, 64, -229));
        FFASpawns.add(new Location(w, 428, 64, -311));
        FFASpawns.add(new Location(w, 454, 64, -311));
        FFASpawns.add(new Location(w, 472, 64, -289));
        FFASpawns.add(new Location(w, 416, 66, -305));
        FFASpawns.add(new Location(w, 416, 66, -305));
        FFASpawns.add(new Location(w, 414, 64, -279));
        FFASpawns.add(new Location(w, 415, 64, -269));
        FFASpawns.add(new Location(w, 412, 64, -266));
        FFASpawns.add(new Location(w, 419, 71, -260));
        FFASpawns.add(new Location(w, 446, 74, -271));
        FFASpawns.add(new Location(w, 467, 74, -310));
        FFASpawns.add(new Location(w, 416, 74, -305));
        FFASpawns.add(new Location(w, 454, 72, -267));
        FFASpawns.add(new Location(w, 467, 64, -264));
        FFASpawns.add(new Location(w, 440, 71, -270));
        FFASpawns.add(new Location(w, 465, 74, -274));
        FFASpawns.add(new Location(w, 466, 74, -251));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack MASK = new ItemStack(Material.SKULL_ITEM, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});
        MASK.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(MASK);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, EXP);
        i.setItem(9, ARROWS);

    }

    public int x1 = 477;
    public int y1 = 47;
    public int z1 = -333;
    public int x2 = 400;
    public int y2 = 106;
    public int z2 = -226;
}
