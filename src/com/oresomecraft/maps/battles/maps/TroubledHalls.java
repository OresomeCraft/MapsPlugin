package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@MapConfig
public class TroubledHalls extends BattleMap implements Listener {

    public TroubledHalls() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(20);
        setBuildLimit(161);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.ARROW, Material.IRON_PICKAXE, Material.FISHING_ROD, Material.IRON_PICKAXE, Material.FISHING_ROD, Material.BAKED_POTATO, Material.LEAVES,
                Material.ARROW, Material.LOG});
        setAutoSpawnProtection(2);
    }

    String name = "halls";
    String fullName = "Troubled Halls";
    String[] creators = {"__R3", "nitro20021", "Elite_Killer1"};
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 55, 155, 46, 180, 0));
        blueSpawns.add(new Location(w, 55, 155, 233, 359.6F, 1.3F));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 55, 152, 161, 6.7F, 33.5F));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);

    }

    public int x1 = -5;
    public int y1 = 196;
    public int z1 = 264;

    public int x2 = 113;
    public int y2 = 104;
    public int z2 = 28;

    @EventHandler
    public void preventPlaceOutOfMap(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)
                && !contains(event.getBlock().getLocation(), x1, x2, y1, y2, z1, z2)) {
            event.setCancelled(true);
        }
    }

    Material[] allowedInHall = new Material[]{Material.GOLD_BLOCK, Material.BEACON, Material.FENCE, Material.GLOWSTONE};

    @EventHandler
    public void lBreak(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) {
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
        if (event.getBlock().getWorld().getName().equals(name)) {
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
        if (!event.getLocation().getWorld().equals(name)) return;
        if (contains(event.getLocation(), -6, 116, 120, 196, 205, 266) ||
                contains(event.getLocation(), 112, -4, 120, 193, 77, 17)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void lMove(PlayerMoveEvent event) {
        if (!event.getTo().getWorld().equals(name)) return;
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
        if (event.getBlock().getLocation().getWorld().getName().equals(name)) {
            if (event.getBlock().getType() == Material.DIAMOND_BLOCK) {
                event.setCancelled(true);
                event.getBlock().setType(Material.DIRT);
                event.getPlayer().sendMessage(ChatColor.RED + "Diamond Blocks are disabled on this map!");
            }
        }
    }
}
