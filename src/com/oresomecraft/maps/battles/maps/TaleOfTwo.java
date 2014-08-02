package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "taleoftwo",
        fullName = "Tale of Two",
        creators = {" __R3", "AnomalousDyna"},
        gamemodes = {Gamemode.TDM}
)
@Region(
        x1 = 0,
        y1 = 0,
        z1 = 0,
        x2 = 0,
        y2 = 0,
        z2 = 0
)
@Attributes(
        allowBuild = false,
        tdmTime = 15,
        disabledDrops = {Material.ARROW, Material.BOW, Material.STONE_SWORD, Material.LEATHER_HELMET,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS}
)
public class TaleOfTwo extends BattleMap implements Listener {

    public TaleOfTwo() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 0.5, 58.5, 1.5, 0, 2));
        blueSpawns.add(new Location(w, 0.5, 58.5, -49.5, 0, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0.5, 58.5, 1.5, 0, 2));
        FFASpawns.add(new Location(w, 0.5, 58.5, -49.5, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        setColouredArmorAccordingToTeam(p);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(10, ARROWS);

    }
}
