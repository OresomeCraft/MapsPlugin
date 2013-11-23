package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig
public class Okzuy extends BattleMap implements IBattleMap, Listener {

    public Okzuy() {
        super.initiate(this, name, fullName, creators, modes);
    }

    String name = "Okzuy";
    String fullName = "Okzuy";
    String creators = "Yuzko";
    Gamemode[] modes = {Gamemode.FFA, Gamemode.INFECTION, Gamemode.KOTH};


    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -409, 77, 71);
        Location blueSpawn = new Location(w, -317, 74, 146);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        setKoTHMonument(new Location(w, -362, 76, 113));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -424, 94, 52));
        FFASpawns.add(new Location(w, -413, 89, 48));
        FFASpawns.add(new Location(w, -428, 89, 61));
        FFASpawns.add(new Location(w, -449, 74, 27));
        FFASpawns.add(new Location(w, -378, 74, 28));
        FFASpawns.add(new Location(w, -379, 74, 70));
        FFASpawns.add(new Location(w, -405, 95, 90));
        FFASpawns.add(new Location(w, -423, 74, 97));
        FFASpawns.add(new Location(w, -449, 74, 98));
        FFASpawns.add(new Location(w, -441, 96, 56));

        FFASpawns.add(new Location(w, -343, 74, 84));
        FFASpawns.add(new Location(w, -333, 74, 60));
        FFASpawns.add(new Location(w, -328, 78, 95));
        FFASpawns.add(new Location(w, -298, 74, 88));
        FFASpawns.add(new Location(w, -275, 74, 85));
        FFASpawns.add(new Location(w, -281, 77, 71));
        FFASpawns.add(new Location(w, -274, 74, 63));
        FFASpawns.add(new Location(w, -308, 74, 34));
        FFASpawns.add(new Location(w, -428, 89, 61));
        FFASpawns.add(new Location(w, -341, 74, 39));

        FFASpawns.add(new Location(w, -307, 79, 189));
        FFASpawns.add(new Location(w, -323, 75, 183));
        FFASpawns.add(new Location(w, -333, 75, 178));
        FFASpawns.add(new Location(w, -287, 75, 18));
        FFASpawns.add(new Location(w, -280, 75, 169));
        FFASpawns.add(new Location(w, -346, 74, 177));
        FFASpawns.add(new Location(w, -344, 76, 144));
        FFASpawns.add(new Location(w, -298, 79, 132));

        FFASpawns.add(new Location(w, -379, 74, 145));
        FFASpawns.add(new Location(w, -326, 77, 159));
        FFASpawns.add(new Location(w, -407, 77, 113));
        FFASpawns.add(new Location(w, -408, 77, 113));
        FFASpawns.add(new Location(w, -316, 77, 114));

        FFASpawns.add(new Location(w, -326, 77, 113));

    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack COOKED_CHICKEN = new ItemStack(Material.COOKED_CHICKEN, 5);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack CHAIN_BOOTS = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        ItemStack CHAIN_LEGGINGS = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        ItemStack CHAIN_CHESTPLATE = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);

        p.getInventory().setBoots(CHAIN_BOOTS);
        p.getInventory().setLeggings(CHAIN_LEGGINGS);
        p.getInventory().setChestplate(CHAIN_CHESTPLATE);

        if (p.getTeam().equals(Team.KOTH_BLUE)) {
            p.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK, 1));
        }

        if (p.getTeam().equals(Team.KOTH_RED)) {
            p.getInventory().setHelmet(new ItemStack(Material.NETHERRACK, 1));
        }

        i.setItem(0, IRON_AXE);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH_POTION);
        i.setItem(3, COOKED_CHICKEN);
        i.setItem(4, IRON_PICKAXE);
        i.setItem(15, ARROW);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -256;
    public int y1 = 225;
    public int z1 = 14;

    // Bottom right corner.
    public int x2 = -468;
    public int y2 = 30;
    public int z2 = 220;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!event.getBlock().getWorld().getName().equals(name)) return;
        Location location = event.getBlock().getLocation();

        switch (event.getBlock().getType()) {

            case STAINED_CLAY:
                event.getBlock().setType(Material.BEDROCK);
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.MUSHROOM_SOUP, 1));
                event.getPlayer().playSound(location, Sound.LEVEL_UP, 10, 10);
                break;

            case SPONGE:
                event.getBlock().setType(Material.BEDROCK);
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.GOLDEN_APPLE, 1));
                event.getPlayer().playSound(location, Sound.LEVEL_UP, 10, 10);
                break;

            case NETHER_BRICK:
                event.getBlock().setType(Material.BEDROCK);
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_SWORD, 1));
                event.getPlayer().playSound(location, Sound.LEVEL_UP, 10, 10);
                break;

            case IRON_ORE:
                event.getBlock().setType(Material.BEDROCK);
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_INGOT, 1));
                event.getPlayer().playSound(location, Sound.LEVEL_UP, 10, 10);
                break;

            case QUARTZ_BLOCK:
                event.getBlock().setType(Material.BEDROCK);
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.BOW, 1));
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.ARROW, 32));
                event.getPlayer().playSound(location, Sound.LEVEL_UP, 10, 10);
                break;

            default:
                event.setCancelled(true);
        }
    }
}