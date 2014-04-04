package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.Effect;

public class Arziri extends BattleMap implements IBattleMap, Listener {

    public Arziri() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_HELMET, Material.STONE_SWORD});
    }

    String name = "arziri";
    String fullName = "Arziri's crypt";
    String creators = "xZizle123 ";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.CTF, Gamemode.KOTH};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 99, 50, 11));
        redSpawns.add(new Location(w, 99, 50, 2));
        redSpawns.add(new Location(w, 99, 50, -6));

        blueSpawns.add(new Location(w, 25, 50, -6));
        blueSpawns.add(new Location(w, 25, 50, 2));
        blueSpawns.add(new Location(w, 25, 50, 11));

        Location redFlag = new Location(w, 108, 51, 2);
        Location blueFlag = new Location(w, 16, 51, 2);

        setCTFFlags(name, redFlag, blueFlag);
        setKoTHMonument(new Location(w, 62, 49, 2));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 99, 27, 2, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, SWORD);
        i.setItem(1, BOW);
        i.setItem(3, STEAK);
        i.setItem(2, HEALTH);
        i.setItem(4, EXP);
        i.setItem(10, ARROWS);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 13;
    public int y1 = 60;
    public int z1 = -41;

    //Bottom right corner.
    public int x2 = 113;
    public int y2 = 40;
    public int z2 = 48;

    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (getArena().equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);
            }
        }
    }

    @EventHandler
    public void arrowAway(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        Location location = projectile.getLocation();
        if (location.getWorld().getName().equals(name)) {
            if (projectile instanceof Arrow) {
                Arrow arrow = (Arrow) projectile;
                arrow.remove();
            }
        }
    }
}
