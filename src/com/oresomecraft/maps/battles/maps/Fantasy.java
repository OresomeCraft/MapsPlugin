package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fantasy extends BattleMap implements IBattleMap, Listener {

    public Fantasy() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_BOOTS, Material.STONE_SWORD});
    }

    String name = "fantasy";
    String fullName = "Fantasy";
    String creators = "_Moist and psgs";
    Gamemode[] modes = {Gamemode.CTF};

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -49.7, 113, 1.9, -90, 0);
        Location blueSpawn = new Location(w, -1.3, 113, -88.9, 89, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, -5, 102, 1);
        Location blueFlag = new Location(w, -45, 102, -88);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -49.7, 113, 1.9, -90, 0);
        Location blueSpawn = new Location(w, -1.3, 113, -88.9, 89, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BRINGER = new ItemStack(Material.MAGMA_CREAM, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5 * 20, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5 * 10, 2));

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH);
        i.setItem(3, STEAK);
        i.setItem(4, BRINGER);
        i.setItem(15, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 40;
    public int y1 = 145;
    public int z1 = -127;

    //Bottom right corner.
    public int x2 = -88;
    public int y2 = 77;
    public int z2 = 41;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getPlayer().getWorld().getName().equals(name) &&
                (event.getItem() != null && event.getItem().getType().equals(Material.MAGMA_CREAM))) {
            transport(p);
        }
    }

    public void transport(Player p) {
        Location blockLoc = new Location(w, -25, 108, -43);

        p.setVelocity(p.getLocation().getDirection().setX(blockLoc.getX()));
        p.setVelocity(p.getLocation().getDirection().setY(blockLoc.getY()));
        p.setVelocity(p.getLocation().getDirection().setZ(blockLoc.getZ()));
        p.setVelocity(p.getLocation().getDirection().multiply(3));
    }

}
