package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "equator",
        fullName = "Equator",
        creators = {"Afridge1O1", "SuperDuckFace", "Numinex", "XUHAVON", "beadycottonwood", "ViolentShadow"},
        gamemodes = {Gamemode.TDM, Gamemode.CTF}
)
@Region(
        x1 = -125,
        y1 = 122,
        z1 = -126,
        x2 = 114,
        y2 = 40,
        z2 = 102
)
@Attributes(
        allowBuild = false,
        autoSpawnProtection = true,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.STAINED_GLASS, Material.LEATHER_HELMET, Material.STONE_SWORD, Material.WOOL}
)
public class Equator extends BattleMap implements Listener {

    public Equator() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 53, 75, -24));
        redSpawns.add(new Location(w, 26, 74, -50));
        redSpawns.add(new Location(w, 60, 75, 12));

        blueSpawns.add(new Location(w, -53, 75, 24));
        blueSpawns.add(new Location(w, -26, 74, 50));
        blueSpawns.add(new Location(w, -60, 74, -12));

        Location redFlag = new Location(w, 75, 76, -4);
        Location blueFlag = new Location(w, -75, 76, 4);
        setCTFFlags(getName(), redFlag, blueFlag);

        setKoTHMonument(new Location(w, 0, 69, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 2, 84, -48));
        FFASpawns.add(new Location(w, -3, 84, 58));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack BLUE_GLASS = new ItemStack(Material.STAINED_GLASS, 1, (short) 11);
        ItemStack RED_GLASS = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 5);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);

        if (p.getTeamType() == Team.TDM_RED) p.getInventory().setHelmet(RED_GLASS);
        if (p.getTeamType() == Team.TDM_BLUE) p.getInventory().setHelmet(BLUE_GLASS);

        i.setItem(0, SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(4, EXP);
        i.setItem(10, ARROWS);

    }

    @EventHandler(ignoreCancelled = false)
    public void blockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().equals(getName())) {
            event.setCancelled(true);
            if (event.getBlock().getType() == Material.WOOL) {
                event.getBlock().getDrops().clear();
            }
        }
    }
}
