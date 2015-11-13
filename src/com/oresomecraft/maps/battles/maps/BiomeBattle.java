package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@MapConfig(
        name = "biomebattle",
        fullName = "Biome Battle",
        creators = {"SuperDuckFace", "Werdna"},
        gamemodes = {Gamemode.FFA, Gamemode.KOTH}
)
@Region(
        x1 = 127,
        y1 = 153,
        z1 = 115,
        x2 = -134,
        y2 = 25,
        z2 = -112
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 10,
        disabledDrops = {Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.BLAZE_ROD, Material.ARROW, Material.IRON_CHESTPLATE, Material.BOW, Material.IRON_SWORD, Material.LEATHER_HELMET}
)
public class BiomeBattle extends BattleMap implements Listener {

    public BiomeBattle() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -61, 73, -54));
        blueSpawns.add(new Location(w, 48, 72, 42));

        setKoTHMonument(new Location(w, 0, 67, 0));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -61, 73, -54));
        FFASpawns.add(new Location(w, 48, 72, 42));
        FFASpawns.add(new Location(w, -69, 72, 63));
        FFASpawns.add(new Location(w, -64, 70, 12));
        FFASpawns.add(new Location(w, -51, 87, 10));
        FFASpawns.add(new Location(w, -47, 80, -41));
        FFASpawns.add(new Location(w, -17, 70, -84));
        FFASpawns.add(new Location(w, 83, 78, -22));
        FFASpawns.add(new Location(w, 13, 69, -3));
        FFASpawns.add(new Location(w, 15, 74, 40));
        FFASpawns.add(new Location(w, -83, 74, -7));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack LEATHER_CAP = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);

        ItemMeta fMeta = BLAZE_ROD.getItemMeta();
        fMeta.setDisplayName(ChatColor.BLUE + "Levitation Rod");

        List<String> fLore = new ArrayList<String>();
        fLore.add(ChatColor.BLUE + "Interact with this to hover into the air!");
        fMeta.setLore(fLore);
        BLAZE_ROD.setItemMeta(fMeta);

        BOW.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_PANTS, LEATHER_CAP, LEATHER_BOOTS});

        pl.getInventory().setLeggings(LEATHER_PANTS);
        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(LEATHER_CAP);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH_POTION);
        i.setItem(2, STEAK);
        i.setItem(4, BLAZE_ROD);
        i.setItem(8, ARROW);

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getWorld().getName().equals(getName())) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) {
                    ItemStack FIRE = new ItemStack(Material.BLAZE_ROD, 1);
                    ItemMeta fMeta = FIRE.getItemMeta();
                    fMeta.setDisplayName(ChatColor.BLUE + "Levitation Rod");

                    List<String> fLore = new ArrayList<String>();
                    fLore.add(ChatColor.BLUE + "Interact with this to hover into the air!");
                    fMeta.setLore(fLore);
                    FIRE.setItemMeta(fMeta);
                    event.getPlayer().getInventory().removeItem(FIRE);
                    event.getPlayer().setVelocity(new Vector(0, 1, 0));
                }
            }
        }
    }
}
