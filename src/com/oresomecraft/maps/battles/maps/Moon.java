package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig
public class Moon extends BattleMap implements IBattleMap, Listener {

    public Moon() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(true);
        disableDrops(new Material[]{Material.BOW, Material.IRON_HELMET, Material.IRON_LEGGINGS,
                Material.IRON_CHESTPLATE, Material.GOLD_BOOTS, Material.CARROT, Material.ARROW, Material.ENDER_STONE, Material.GLASS});
    }

    String name = "moon";
    String fullName = "Moon";
    String creators = "_Moist and broddikill ";
    Gamemode[] modes = {Gamemode.CTF};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -0, 29, -115, -88, 0);
        Location blueSpawn = new Location(w, 108, 29, -7, 89, 0);
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, 4, 31, -64);
        Location blueFlag = new Location(w, 104, 32, -52);
        setCTFFlags(name, redFlag, blueFlag);
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -0, 29, -115, -88, 0);
        Location blueSpawn = new Location(w, 108, 29, -7, 89, 0);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack CARROT = new ItemStack(Material.CARROT_ITEM, 16);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 32);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack GOLD_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack IRON_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemStack END_STONE = new ItemStack(Material.ENDER_STONE, 64);
        ItemStack GLASS = new ItemStack(Material.GLASS, 32);

        InvUtils.nameItem(IRON_HELMET, "Space Helmet");
        InvUtils.nameItem(IRON_CHESTPLATE, "Space Suit");
        InvUtils.nameItem(IRON_PANTS, "Space Suit");
        InvUtils.nameItem(GOLD_BOOTS, "Heavy Boots");
        InvUtils.nameItem(IRON_SWORD, "Premium Blade");
        InvUtils.nameItem(BOW, "Space Gun");
        InvUtils.nameItem(END_STONE, "Moon Rock");

        IRON_PICKAXE.addEnchantment(Enchantment.DIG_SPEED, 3);
        BOW.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        GOLD_BOOTS.addEnchantment(Enchantment.DURABILITY, 3);

        p.getInventory().setBoots(GOLD_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, CARROT);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, END_STONE);
        i.setItem(5, GLASS);
        i.setItem(28, ARROWS);


    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 141;
    public int y1 = 57;
    public int z1 = 19;

    //Bottom right corner.
    public int x2 = -51;
    public int y2 = -2;
    public int z2 = -160;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!event.getPlayer().getInventory().getHelmet().equals(Material.IRON_HELMET)) {
            event.getPlayer().setHealth(0);
        }

        Location location = event.getPlayer().getLocation();
        int i = 0;

        if (!contains(event.getPlayer().getLocation(), -3, 27, -118, 7, 41, -102) && !contains(event.getPlayer().getLocation(), 110, 28, -5, 101, 41, -21)) {
            while (i <= 3) {
                Location newLoc = new Location(w, location.getX(), location.getY(), location.getZ());
                if (!newLoc.getBlock().equals(Material.GLASS)) {
                    newLoc.setX(newLoc.getX() + 1);
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1 * 20, 1));
                    i++;
                } else {
                    event.getPlayer().removePotionEffect(PotionEffectType.POISON);
                }
            }

            while (i <= 3) {
                Location newLoc = new Location(w, location.getX(), location.getY(), location.getZ());
                if (!newLoc.getBlock().equals(Material.GLASS)) {
                    newLoc.setY(newLoc.getY() + 1);
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 1));
                    i++;
                } else {
                    event.getPlayer().removePotionEffect(PotionEffectType.POISON);
                }
            }

            while (i <= 3) {
                Location newLoc = new Location(w, location.getX(), location.getY(), location.getZ());
                if (!newLoc.getBlock().equals(Material.GLASS)) {
                    newLoc.setZ(newLoc.getZ() + 1);
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 1));
                    i++;
                } else {
                    event.getPlayer().removePotionEffect(PotionEffectType.POISON);
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        if (event.getBlock().getType().equals(Material.IRON_BLOCK)) event.setCancelled(true);
        if (event.getBlock().getType().equals(Material.GLOWSTONE)) event.setCancelled(true);
        if (event.getBlock().getType().equals(Material.FENCE)) event.setCancelled(true);
        if (event.getBlock().getType().equals(Material.GLASS)) event.setCancelled(true);
        if (event.getBlock().getType().equals(Material.SIGN)) event.setCancelled(true);
        if (event.getBlock().getType().equals(Material.DISPENSER)) event.setCancelled(true);
        if (event.getBlock().getType().equals(Material.ANVIL)) event.setCancelled(true);
        if (event.getBlock().getType().equals(Material.IRON_FENCE)) event.setCancelled(true);
        if (event.getBlock().getType().equals(Material.CARPET)) event.setCancelled(true);

    }

}
