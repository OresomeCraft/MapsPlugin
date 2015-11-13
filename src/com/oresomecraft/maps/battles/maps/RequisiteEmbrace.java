package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.Bukkit;
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
        name = "requisiteembrace",
        fullName = "Requisite Embrace",
        creators = {"Heartist"},
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
        disabledDrops = {Material.ARROW, Material.BOW, Material.STONE_SWORD,
                Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS}
)
public class RequisiteEmbrace extends BattleMap implements Listener {

    public RequisiteEmbrace() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -10, 106, -73));
        redSpawns.add(new Location(w, 66, 76, -24));
        redSpawns.add(new Location(w, 31, 97, 68));
        redSpawns.add(new Location(w, -44, 106, 55));
        blueSpawns.add(new Location(w, 0, 125, 0, 180F, -48.3F));

        setKoTHMonument(new Location(w, -104, 71, 63));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 125, 0, 180F, -48.3F));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 2);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack JUMP = new ItemStack(Material.FIREWORK, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack ENDERPEARL = new ItemStack(Material.ENDER_PEARL, 1);
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        setColouredArmorAccordingToTeam(p);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, JUMP);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        if (p.getTeamType() != null && p.getTeamType() == Team.TDM_RED)
            i.setItem(4, ENDERPEARL);
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
