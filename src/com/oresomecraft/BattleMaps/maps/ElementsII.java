package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.Team;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.World;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ElementsII extends BattleMap implements IBattleMap, Listener {

    public ElementsII() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
    }

    String name = "elements2";
    String fullName = "Elements II";
    String creators = "broddikill, koolguydude4 and MiCkEyMiCE";
    Gamemode[] modes = {Gamemode.KOTH};

    public void readyTDMSpawns() {

        World w = Bukkit.getServer().getWorld(name);
        redSpawns.add(new Location(w, -160, 70, -1, 90, 0));
        redSpawns.add(new Location(w, -160, 70, -12, 90, 0));

        blueSpawns.add(new Location(w, -3, 70, 0, -90, 0));
        blueSpawns.add(new Location(w, -3, 70, 12, -90, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

        setKoTHMonument(new Location(w, -82, 115, 0));
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);
        FFASpawns.add(new Location(w, -160, 70, -1, 90, 0));
        FFASpawns.add(new Location(w, -160, 70, -12, 90, 0));
        FFASpawns.add(new Location(w, -3, 70, 0, -90, 0));
        FFASpawns.add(new Location(w, -3, 70, 12, -90, 0));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack FIRE = new ItemStack(Material.POTION, 1, (short) 8195);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack LOG = new ItemStack(Material.LOG, 25);
            ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);

            if (p.getTeam() == Team.TDM_RED) {
                i.setItem(7, FIRE);
            }

            i.setItem(0, STONE_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, HEALTH);
            i.setItem(3, LOG);
            i.setItem(11, ARROWS);
            i.setItem(8, new ItemStack(Material.BREAD, 3));

            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -51;
    public int y1 = 74;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 7;
    public int y2 = 171;
    public int z2 = 166;

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getWorld().getName().equals(name)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Block b = event.getClickedBlock();
                World w = Bukkit.getWorld(name);

                if (BattlePlayer.getBattlePlayer(p).getTeam() == Team.TDM_RED) {
                    if (b.getType().equals(Material.REDSTONE_BLOCK)) {
                        p.teleport(new Location(w, -111, 97, 14, 90, 0));
                    }
                } else {
                    if (BattlePlayer.getBattlePlayer(p).getTeam() == Team.TDM_BLUE) {
                        if (b.getType().equals(Material.LAPIS_BLOCK)) {
                            p.teleport(new Location(w, -53, 97, -14, -90, 0));
                        }
                    }
                }
            }
        }
    }
}