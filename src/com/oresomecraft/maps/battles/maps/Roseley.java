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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@MapConfig(
        name = "roseley",
        fullName = "Roseley",
        creators = {"iR3"},
        gamemodes = {Gamemode.KOTH, Gamemode.INFECTION}
)
@Region(
        x1 = -141,
        y1 = 91,
        z1 = 134,
        x2 = -64,
        y2 = 55,
        z2 = -11
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.ARROW, Material.BOW, Material.STONE_SWORD,
                Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS}
)
public class Roseley extends BattleMap implements Listener {

    public Roseley() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -131, 70, 1));
        blueSpawns.add(new Location(w, -74, 70, 126));

        setKoTHMonument(new Location(w, -104, 71, 63));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -131, 70, 1));
        FFASpawns.add(new Location(w, -74, 70, 126));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack JUMP = new ItemStack(Material.FIREWORK, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        setColouredArmorAccordingToTeam(p);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, JUMP);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(10, ARROWS);

    }

    @EventHandler
    public void onBlockPlace(PlayerInteractEvent event) {
        if (getArena().equals(getName())) {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.getItemInHand().getType() == Material.FIREWORK) {
                    player.getInventory().removeItem(new ItemStack(Material.FIREWORK, 1));
                    player.setVelocity(new Vector(0, 1, 0));
                }
            }
        }
    }

}
