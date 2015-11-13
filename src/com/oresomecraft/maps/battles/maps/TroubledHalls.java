package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@MapConfig(
        name = "halls",
        fullName = "Troubled Halls",
        creators = {"Heartist"},
        gamemodes = {Gamemode.TDM}
)
@Region(
        x1 = -5,
        y1 = 196,
        z1 = 264,
        x2 = 113,
        y2 = 104,
        z2 = 28
)
@Attributes(
        blockBuildLimit = 161,
        autoSpawnProtection = true,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.ARROW, Material.IRON_PICKAXE, Material.FISHING_ROD, Material.IRON_PICKAXE, Material.FISHING_ROD, Material.BAKED_POTATO, Material.LEAVES,
                Material.ARROW, Material.LOG}
)
public class TroubledHalls extends BattleMap implements Listener {

    String[] specificRules = {"Do not enter the enemy hall!", "Do not shoot into the enemy hall!"};
    Material[] allowedInHall = new Material[]{Material.GOLD_BLOCK, Material.BEACON, Material.FENCE, Material.GLOWSTONE};

    public TroubledHalls() {
        super.initiate(this);
        setMapSpecificRules(specificRules);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 55, 155, 46, 180, 0));
        blueSpawns.add(new Location(w, 55, 155, 233, 359.6F, 1.3F));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 55, 152, 161, 6.7F, 33.5F));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);

    }

    @EventHandler
    public void preventPlaceOutOfMap(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(getName())
                && !isInsideRegion(event.getBlock().getLocation())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void lBreak(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equals(getName())) {
            if (!Arrays.asList(allowedInHall).contains(event.getBlock().getType())) {
                if (contains(event.getBlock().getLocation(), -6, 116, 120, 196, 205, 266) ||
                        contains(event.getBlock().getLocation(), 112, -4, 120, 193, 77, 17)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void lBreak(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(getName())) {
            if (!Arrays.asList(allowedInHall).contains(event.getBlock().getType())) {
                if (contains(event.getBlock().getLocation(), -6, 116, 120, 196, 205, 266) ||
                        contains(event.getBlock().getLocation(), 112, -4, 120, 193, 77, 17)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void lExplode(EntityExplodeEvent event) {
        if (!event.getLocation().getWorld().equals(getName())) return;
        if (contains(event.getLocation(), -6, 116, 120, 196, 205, 266) ||
                contains(event.getLocation(), 112, -4, 120, 193, 77, 17)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void lMove(PlayerMoveEvent event) {
        if (!event.getTo().getWorld().equals(getName())) return;
        try {
            if (contains(event.getTo(), 50, 60, 151, 147, 220, 219)) {
                //check if red
                if (BattlePlayer.getBattlePlayer(event.getPlayer()).getTeamType() == Team.TDM_RED) {
                    event.setCancelled(true);
                }
            }
            if (contains(event.getTo(), 60, 50, 151, 148, 61, 62)) {
                //check if blue
                if (BattlePlayer.getBattlePlayer(event.getPlayer()).getTeamType() == Team.TDM_BLUE) {
                    event.setCancelled(true);
                }
            }
        } catch (Exception e) {
            //lol fix getTeamType @Zachoz.
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void blockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(getName())) {
            if (event.getBlock().getType() == Material.DIAMOND_BLOCK) {
                event.setCancelled(true);
                event.getBlock().setType(Material.DIRT);
                event.getPlayer().sendMessage(ChatColor.RED + "Diamond Blocks are disabled on this map!");
            }
        }
    }
}
