package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig(
        name = "elements2",
        fullName = "Elements II",
        creators = {"_Moist", "psgs", "broddikill"},
        gamemodes = {Gamemode.KOTH}
)
@Region(
        x1 = -169,
        y1 = 52,
        z1 = -37,
        x2 = -169,
        y2 = 124,
        z2 = -32
)
@Attributes(
        allowBuild = false,
        autoSpawnProtection = true,
        disabledDrops = {Material.ARROW, Material.BOW, Material.STONE_SWORD, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET}
)
public class ElementsII extends BattleMap implements Listener {

    public ElementsII() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -160, 70, -1, 90, 0));
        redSpawns.add(new Location(w, -160, 70, -12, 90, 0));

        blueSpawns.add(new Location(w, -3, 70, 0, -90, 0));
        blueSpawns.add(new Location(w, -3, 70, 12, -90, 0));

        setKoTHMonument(new Location(w, -83, 115, -1));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -160, 70, -1, 90, 0));
        FFASpawns.add(new Location(w, -160, 70, -12, 90, 0));
        FFASpawns.add(new Location(w, -3, 70, 0, -90, 0));
        FFASpawns.add(new Location(w, -3, 70, 12, -90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack FIRE = new ItemStack(Material.POTION, 1, (short) 8227); // Keep this here even if it's not used plz
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BREAD = new ItemStack(Material.BREAD, 3);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, HEALTH);
        i.setItem(11, ARROWS);
        i.setItem(4, BREAD);

        if (p.getTeamType() == Team.KOTH_RED) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 120 * 20, 2));
            i.setItem(8, FIRE);
        }

    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getWorld().getName().equals(getName())) {

            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block b = event.getClickedBlock();
                World w = Bukkit.getWorld(getName());

                if (BattlePlayer.getBattlePlayer(p).getTeamType() == Team.KOTH_RED) {
                    if (b.getType() == Material.REDSTONE_BLOCK) {
                        p.teleport(new Location(w, -111, 97, 14, 90, 0));
                    }
                } else {
                    if (BattlePlayer.getBattlePlayer(p).getTeamType() == Team.KOTH_BLUE) {
                        if (b.getType() == Material.LAPIS_BLOCK) {
                            p.teleport(new Location(w, -53, 97, -14, -90, 0));
                        }
                    }
                }
            }
        }
    }
}
