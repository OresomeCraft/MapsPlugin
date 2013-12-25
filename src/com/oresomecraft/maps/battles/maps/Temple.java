package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@MapConfig
public class Temple extends BattleMap implements IBattleMap, Listener {

    public Temple() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{});
    }

    String name = "temple";
    String fullName = "Temple";
    String creators = "Corrigan1998 and jslsa ";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.CTF, Gamemode.INFECTION, Gamemode.LMS};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -37, 83, 42, 180, 0));
        blueSpawns.add(new Location(w, -37, 83, -105, 0, 0));

        Location redFlag = new Location(w, -37, 85, 22);
        Location blueFlag = new Location(w, -37, 85, -86);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -37, 83, 42, 180, 0));
        FFASpawns.add(new Location(w, -37, 83, -105, 0, 0));
        FFASpawns.add(new Location(w, -111, 83, -31, -90, 0));
        FFASpawns.add(new Location(w, 36, 83, -31, 90, 0));
        FFASpawns.add(new Location(w, -16, 90, -52, 44, 0));
        FFASpawns.add(new Location(w, -16, 90, -10, 128, 0));
        FFASpawns.add(new Location(w, -59, 90, -9, -137, 0));
        FFASpawns.add(new Location(w, -59, 90, -52, -46, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 2);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.STONE_SWORD, 1);

        IRON_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 2);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(17, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 48;
    public int y1 = 173;
    public int z1 = -107;
    //Bottom right corner.
    public int x2 = -116;
    public int y2 = 45;
    public int z2 = 52;

    @EventHandler
    public void elevator(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (event.getFrom().getBlock().getType().equals((Material.BEACON)) || event.getTo().getBlock().getType().equals((Material.BEACON))) {
                event.getPlayer().setFallDistance(0);
                event.getPlayer().setVelocity(new Vector(0, 5, 0));
                event.getPlayer().setFallDistance(0);
            }
        }
    }
}