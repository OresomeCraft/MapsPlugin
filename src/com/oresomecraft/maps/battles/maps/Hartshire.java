package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig(
        name = "hartshire",
        fullName = "Hartshire",
        creators = {"Heartist"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION}
)
@Region(
        x1 = 18,
        y1 = 122,
        z1 = -2,
        x2 = 284,
        y2 = 0,
        z2 = -308
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.STONE_SWORD, Material.LEATHER_HELMET, Material.GOLDEN_APPLE}
)
public class Hartshire extends BattleMap implements Listener {

    public Hartshire() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 93, 39, -126, -1, 0);
        Location blueSpawn = new Location(w, 188, 45, -130, -178, 0);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 154, 58, -158, -50, 0));
        redSpawns.add(new Location(w, 77, 40, -124, -50, 0));
        blueSpawns.add(new Location(w, 119, 67, -163, -50, 0));
        redSpawns.add(new Location(w, 175, 45, -245, -50, 0));
        blueSpawns.add(new Location(w, 184, 48, -210, -50, 0));
        redSpawns.add(new Location(w, 229, 56, -148, -50, 0));
        blueSpawns.add(new Location(w, 116, 41, -46, -50, 0));
        redSpawns.add(new Location(w, 94, 53, -106, -50, 0));
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(getName());

        Location redSpawn = new Location(w, 93, 39, -126, -1, 0);
        Location blueSpawn = new Location(w, 188, 45, -130, -178, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 154, 58, -158, -50, 0));
        FFASpawns.add(new Location(w, 77, 40, -124, -50, 0));
        FFASpawns.add(new Location(w, 119, 67, -163, -50, 0));
        FFASpawns.add(new Location(w, 175, 45, -245, -50, 0));
        FFASpawns.add(new Location(w, 184, 48, -210, -50, 0));
        FFASpawns.add(new Location(w, 229, 56, -148, -50, 0));
        FFASpawns.add(new Location(w, 116, 41, -46, -50, 0));
        FFASpawns.add(new Location(w, 94, 53, -106, -50, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 3);
        ItemStack S = new ItemStack(Material.STICK, 1);
        ItemStack I = new ItemStack(Material.IRON_INGOT, 1);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setLeggings(LEATHER_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH);
        i.setItem(10, ARROWS);
        i.setItem(11, S);
        i.setItem(12, I);
        i.setItem(4, EXP);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void ointment(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack itemStack = player.getItemInHand();
        Inventory inventory = player.getInventory();
        Material tool = itemStack.getType();
        if (player.getWorld().getName().equalsIgnoreCase(getName())) {
            if (tool == Material.INK_SACK) {

                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 2));
                    ItemStack ointment = new ItemStack(player.getItemInHand());
                    ointment.setAmount(1);
                    inventory.removeItem(ointment);
                }
            }
        }
    }

}
