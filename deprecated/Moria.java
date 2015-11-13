package com.oresomecraft.maps.battles.maps.deprecated;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.teams.Team;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Moria extends BattleMap implements Listener {

    // Region. (Top corner block and bottom corner block
    // Top left corner
    public int x1 = 33;
    public int y1 = 180;
    public int z1 = 71;
    // Bottom right corner
    public int x2 = -250;
    public int y2 = 23;
    public int z2 = -80;
    String name = "moria";
    String fullName = "Mines of Moria";
    String[] creators = {"miniwolf35", "ep1cn00bt00b"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.KOTH, Gamemode.INFECTION};

    public Moria() {
        super.initiate(this, name, fullName, creators, modes);
        lockTime("day");
        disableDrops(new Material[]{Material.LEATHER_LEGGINGS, Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.DIAMOND_HELMET, Material.IRON_CHESTPLATE, Material.STONE_SWORD, Material.NAME_TAG,
                Material.CARROT_STICK, Material.NAME_TAG, Material.MONSTER_EGG, Material.DIAMOND_PICKAXE, Material.CHAINMAIL_BOOTS,
                Material.LEATHER_LEGGINGS});
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -188, 72, 34, -177, 0));
        blueSpawns.add(new Location(w, -39, 104, -5, -1, 0));
        setKoTHMonument(new Location(w, -107, 68, 12));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -62, 66, 15, 90, 0));
        FFASpawns.add(new Location(w, -110, 66, 18, -179, 0));
        FFASpawns.add(new Location(w, -165, 66, 5, -90, 0));
        FFASpawns.add(new Location(w, -39, 104, -5, 0, 0));
        FFASpawns.add(new Location(w, -188, 72, 34, -179, 0));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 32);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack DIAMOND_PICK = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemStack LADDER = new ItemStack(Material.LADDER, 16);
        ItemStack GOLDEN_APPLE = new ItemStack(Material.GOLDEN_APPLE, 1);

        ItemStack SADDLE = new ItemStack(Material.SADDLE, 1);
        ItemStack NAME_TAG = new ItemStack(Material.NAME_TAG, 1);
        ItemStack CARROT_STICK = new ItemStack(Material.CARROT_STICK, 1);
        ItemStack SPAWN_PIG = new ItemStack(Material.MONSTER_EGG, 1, (byte) 90);

        ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack CHAIN_BOOTS = new ItemStack(Material.CHAINMAIL_BOOTS, 1);

        ItemUtils.nameItem(NAME_TAG, "Trusty Steed");

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_PANTS});

        pl.getInventory().setHelmet(DIAMOND_HELMET);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setLeggings(LEATHER_PANTS);
        pl.getInventory().setBoots(CHAIN_BOOTS);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, DIAMOND_PICK);
        i.setItem(3, LADDER);
        i.setItem(4, GOLDEN_APPLE);
        i.setItem(5, STEAK);
        i.setItem(11, ARROWS);

        Team team = BattlePlayer.getBattlePlayer(p).getTeamType();
        if (team == Team.KOTH_BLUE || team == Team.TDM_BLUE || team == Team.HUMANS) {
            i.setItem(6, SADDLE);
            i.setItem(7, NAME_TAG);
            i.setItem(8, CARROT_STICK);
            i.setItem(9, SPAWN_PIG);
        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (event.getBlockPlaced().getType() != Material.LADDER) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (event.getBlock().getType() != Material.LADDER) {
            event.setCancelled(true);
        }
    }
}
