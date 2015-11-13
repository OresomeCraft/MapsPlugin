package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig(
        name = "apollo",
        fullName = "Apollo",
        creators = {"RokMelon", "Invinsible_Jelly"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION}
)
@Region(
        x1 = -1,
        y1 = 59,
        z1 = 1,
        x2 = -97,
        y2 = 186,
        z2 = 108
)
@Attributes(
        allowBuild = false,
        tdmTime = 10,
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD}
)
public class Apollo extends BattleMap implements Listener {

    public Apollo() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, -11, 150, 72, 89, 0);
        Location blueSpawn = new Location(w, -33, 150, 50, -1, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -55, 150, 72, -111, 0));
        redSpawns.add(new Location(w, -20, 133, 27, 48, 0));
        redSpawns.add(new Location(w, -81, 133, 88, -133, 0));
        redSpawns.add(new Location(w, -40, 110, 55, -89, 0));
        redSpawns.add(new Location(w, -53, 93, 54, -43, 0));
        redSpawns.add(new Location(w, -68, 122, 38, -45, 0));
        redSpawns.add(new Location(w, -53, 133, 57, -53, 0));
        redSpawns.add(new Location(w, -62, 136, 80, -131, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -33, 150, 94, 179, 0));
        blueSpawns.add(new Location(w, -19, 133, 88, 137, 0));
        blueSpawns.add(new Location(w, -82, 133, 25, -44, 0));
        blueSpawns.add(new Location(w, -57, 109, 72, -2, 0));
        blueSpawns.add(new Location(w, -53, 93, 54, -43, 0));
        blueSpawns.add(new Location(w, -68, 122, 38, -45, 0));
        blueSpawns.add(new Location(w, -53, 133, 57, -53, 0));
        blueSpawns.add(new Location(w, -62, 136, 80, -131, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -11, 150, 72, 89, 0));
        FFASpawns.add(new Location(w, -33, 150, 50, -1, 0));
        FFASpawns.add(new Location(w, -55, 150, 72, -111, 0));
        FFASpawns.add(new Location(w, -20, 133, 27, 48, 0));
        FFASpawns.add(new Location(w, -81, 133, 88, -133, 0));
        FFASpawns.add(new Location(w, -40, 110, 55, -89, 0));
        FFASpawns.add(new Location(w, -53, 93, 54, -43, 0));
        FFASpawns.add(new Location(w, -68, 122, 38, -45, 0));
        FFASpawns.add(new Location(w, -53, 133, 57, -53, 0));
        FFASpawns.add(new Location(w, -62, 136, 80, -131, 0));
        FFASpawns.add(new Location(w, -33, 150, 94, 179, 0));
        FFASpawns.add(new Location(w, -19, 133, 88, 137, 0));
        FFASpawns.add(new Location(w, -82, 133, 25, -44, 0));
        FFASpawns.add(new Location(w, -57, 109, 72, -2, 0));
    }

    public void applyInventory(BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack ENDER_PEARL = new ItemStack(Material.ENDER_PEARL, 3);

        IRON_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
        i.setItem(4, EXP);
        i.setItem(8, ENDER_PEARL);

        pl.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000 * 20, 2));

    }

    @EventHandler
    public void noFall(EntityDamageEvent event) {
        if (event.getEntity().getWorld().getName().equals(getName())) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }
    }

}
