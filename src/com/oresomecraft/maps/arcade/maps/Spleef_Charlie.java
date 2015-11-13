package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.ArcadeMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@MapConfig(
        name = "spleef_charlie",
        fullName = "Spleef (Charlie)",
        creators = {"zachoz", "ScruffyRules"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        blockBuildLimit = 66,
        disabledDrops = {Material.DIAMOND_SPADE, Material.COOKED_BEEF},
        allowPhysicalPlayerDamage = false
)
public class Spleef_Charlie extends ArcadeMap implements Listener {

    public Spleef_Charlie() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 66, -22));
        FFASpawns.add(new Location(w, -11, 66, 11));
        FFASpawns.add(new Location(w, -21, 66, -0));
        FFASpawns.add(new Location(w, -11, 66, 12));
        FFASpawns.add(new Location(w, -0, 66, 23));
        FFASpawns.add(new Location(w, 0, 66, 23));
        FFASpawns.add(new Location(w, 12, 66, 12));
        FFASpawns.add(new Location(w, 22, 66, 0));
        FFASpawns.add(new Location(w, 11, 66, -11));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack DIAMOND_SPADE = new ItemStack(Material.DIAMOND_SPADE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        ItemUtils.nameItem(DIAMOND_SPADE, ChatColor.BLUE + "Advanced Spleefer's Shovel");

        ItemMeta diamond_spade = DIAMOND_SPADE.getItemMeta();
        List<String> spade_lore = new ArrayList<String>();
        spade_lore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Right click to shoot snowballs!");
        diamond_spade.setLore(spade_lore);
        DIAMOND_SPADE.setItemMeta(diamond_spade);

        i.setItem(0, DIAMOND_SPADE);
        i.setItem(1, STEAK);
    }

    @EventHandler
    public void onSpadeInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(getName())) return;

        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType() == Material.DIAMOND_SPADE) {
                player.launchProjectile(Snowball.class);
            }
        }
    }
}
