package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "electricity",
        fullName = "Electricity",
        creators = {"kingfisher83", "danielschroeder", "iR3", "_Moist"},
        gamemodes = {Gamemode.TDM, Gamemode.INFECTION}
)
@Region(
        x1 = -69,
        y1 = 124,
        z1 = 82,
        x2 = 64,
        y2 = 65,
        z2 = -85
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.ARROW, Material.BOW, Material.GOLD_LEGGINGS, Material.GOLD_CHESTPLATE, Material.GOLD_HELMET, Material.GOLD_BOOTS, Material.STONE_SWORD}
)
public class Electricity extends BattleMap implements Listener {

    public Electricity() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 0, 76, 59));
        redSpawns.add(new Location(w, -58, 76, 0));
        redSpawns.add(new Location(w, -16, 76, 32));

        blueSpawns.add(new Location(w, 0, 76, -58));
        blueSpawns.add(new Location(w, 59, 76, 0));
        blueSpawns.add(new Location(w, 18, 76, -30));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 76, 59));
        FFASpawns.add(new Location(w, -58, 76, 0));
        FFASpawns.add(new Location(w, -16, 76, 32));
        FFASpawns.add(new Location(w, 0, 76, -58));
        FFASpawns.add(new Location(w, 59, 76, 0));
        FFASpawns.add(new Location(w, 18, 76, -30));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);

        ItemStack GOLD_HELMET = new ItemStack(Material.GOLD_HELMET, 1);
        ItemStack GOLD_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE, 1);
        ItemStack GOLD_PANTS = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemStack GOLD_BOOTS = new ItemStack(Material.GOLD_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1, (short) -1600);

        BOW.getItemMeta().setDisplayName(ChatColor.YELLOW + "Lightning Bow");

        p.getInventory().setBoots(GOLD_BOOTS);
        p.getInventory().setLeggings(GOLD_PANTS);
        p.getInventory().setChestplate(GOLD_CHESTPLATE);
        p.getInventory().setHelmet(GOLD_HELMET);


        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(15, ARROWS);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void arrowboom(ProjectileHitEvent event) {
        if (event.getEntity().getWorld().getName().equals(getName())) {
            if (event.getEntity() instanceof Arrow) {
                event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
                event.getEntity().remove();
            }
        }

    }

}
