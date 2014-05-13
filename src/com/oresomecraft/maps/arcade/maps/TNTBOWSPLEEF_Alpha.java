package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.xml.stream.events.EntityDeclaration;
import java.util.ArrayList;
import java.util.List;

@MapConfig
public class TNTBOWSPLEEF_Alpha extends ArcadeMap implements Listener {

    public TNTBOWSPLEEF_Alpha() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.BOW, Material.COOKED_BEEF});
        setAllowPhysicalDamage(false);
        setAllowBuild(false);
    }

    // Map details
    String name = "tntbowspleef_alpha";
    String fullName = "TnT Bow Spleef (Alpha)";
    String[] creators = {"Rynocraft", "BlueVortexed"};
    Gamemode[] modes = {Gamemode.LMS};

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 1, 57, -22, -0, 0));
        FFASpawns.add(new Location(w, -22, 57, -0, -90, 0));
        FFASpawns.add(new Location(w, 1, 57, 22, 180, 0));
        FFASpawns.add(new Location(w, 22, 57, -0, 90, 0));
        FFASpawns.add(new Location(w, 18, 57, 16, 135, 0));
        FFASpawns.add(new Location(w, -13, 57, 15, -135, 0));
        FFASpawns.add(new Location(w, -17, 57, -15, -51, 0));
        FFASpawns.add(new Location(w, 15, 57, -16, 40, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack ARROW = new ItemStack(Material.ARROW, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 2);

        ItemMeta bowMeta = BOW.getItemMeta();
        List<String> bowLore = new ArrayList<String>();
        bowLore.add(org.bukkit.ChatColor.BLUE + "Shoot TnT under other players to make them fall into the void!");
        bowMeta.setLore(bowLore);
        BOW.setItemMeta(bowMeta);

        InvUtils.nameItem(BOW, ChatColor.BLUE + "Spleefer's Bow");
        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        BOW.addEnchantment(Enchantment.ARROW_FIRE, 1);

        i.setItem(0, BOW);
        i.setItem(1, STEAK);
        i.setItem(14, ARROW);
    }

    @EventHandler
    public void arrow(EntityDamageEvent e) {
        if (!e.getEntity().getWorld().getName().equals(name)) return;
        if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) e.setCancelled(true);
        if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) e.setCancelled(true);

    }

}
