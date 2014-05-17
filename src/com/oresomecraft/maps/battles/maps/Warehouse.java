package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.InvUtils;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@MapConfig
public class Warehouse extends BattleMap implements Listener {

    public Warehouse() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(15);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.IRON_SWORD, Material.LEATHER_HELMET, Material.WOOL});
    }

    // Map details
    String name = "warehouse";
    String fullName = "Warehouse";
    String[] creators = {"SuperDuckFace", " meganlovesmusic", "_Husky_"};
    Gamemode[] modes = {Gamemode.TDM, Gamemode.CTF, Gamemode.LTS};

    public void readyTDMSpawns() {

        Location redFlag = new Location(w, -25, 75, 43);
        Location blueFlag = new Location(w, 61, 75, 43);

        if (!getMode().equals(Gamemode.LTS)) {
            redSpawns.add(new Location(w, -34, 74, 43));
            blueSpawns.add(new Location(w, 71, 74, 43));
        } else {
            redSpawns.add(new Location(w, -29, 74, 43));
            blueSpawns.add(new Location(w, 66, 75, 43));
        }

        setCTFFlags(name, redFlag, blueFlag);

    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -34, 74, 43));
        FFASpawns.add(new Location(w, 71, 74, 43));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 6);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 16);
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS, LEATHER_HELMET});

        p.getInventory().setBoots(LEATHER_BOOTS);
        p.getInventory().setLeggings(LEATHER_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(11, ARROWS);

        Bukkit.getScheduler().runTask(plugin, new Runnable() {
            public void run() {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000 * 20, 2));
            }
        });

    }

    // Top left corner.
    public int x1 = -46;
    public int y1 = 125;
    public int z1 = -11;

    //Bottom right corner.
    public int x2 = 76;
    public int y2 = 60;
    public int z2 = 97;

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (BattlePlayer.getBattlePlayer(player).isSpectator()) return;

        if (player.getLocation().getWorld().getName().equals(name)) {

            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();

                if (block.getType() == Material.PISTON_BASE) {
                    if (block.getLocation().equals(new Location(w, -38, 75, 43))) {
                        player.teleport(new Location(w, -29, 74, 43)); //red
                    } else if (block.getLocation().equals(new Location(w, 74, 75, 43))) {
                        player.teleport(new Location(w, 66, 75, 43)); //blue
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerSpectate(PlayerDeathEvent event) {
        if (event.getEntity().getLocation().getWorld().getName().equals(name)) {
            if (BattlePlayer.getBattlePlayer(event.getEntity()).isSpectator()) {
                event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000 * 20, 2));
            }
        }

    }
}
