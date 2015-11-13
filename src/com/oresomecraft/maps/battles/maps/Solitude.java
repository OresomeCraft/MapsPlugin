package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import com.oresomecraft.OresomeBattles.teams.Team;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig(
        name = "solitude",
        fullName = "Solitude",
        creators = {"AnomalousRei", "dutchy336", "tarko2411", "PMC"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA}
)
@Region(
        x1 = 525,
        y1 = 0,
        z1 = 578,
        x2 = -44,
        y2 = 232,
        z2 = 1136
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.ARROW, Material.FISHING_ROD, Material.BOW, Material.IRON_SWORD, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET}
)
public class Solitude extends BattleMap implements Listener {

    public Solitude() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 380, 52, 960));
        redSpawns.add(new Location(w, 342, 72, 965));
        redSpawns.add(new Location(w, 260, 95, 939));
        redSpawns.add(new Location(w, 231, 69, 965));
        redSpawns.add(new Location(w, 231, 69, 965));
        redSpawns.add(new Location(w, 218, 88, 940));
        redSpawns.add(new Location(w, 269, 57, 893));
        redSpawns.add(new Location(w, 221, 57, 842));
        redSpawns.add(new Location(w, 216, 61, 870));
        redSpawns.add(new Location(w, 231, 53, 798));
        redSpawns.add(new Location(w, 231, 59, 748));
        redSpawns.add(new Location(w, 269, 57, 893));
        redSpawns.add(new Location(w, 406, 46, 966));
        redSpawns.add(new Location(w, 267, 92, 987));
        redSpawns.add(new Location(w, 296, 60, 951));

        blueSpawns.add(new Location(w, 380, 52, 960));
        blueSpawns.add(new Location(w, 342, 72, 965));
        blueSpawns.add(new Location(w, 260, 95, 939));
        blueSpawns.add(new Location(w, 231, 69, 965));
        blueSpawns.add(new Location(w, 231, 69, 965));
        blueSpawns.add(new Location(w, 218, 88, 940));
        blueSpawns.add(new Location(w, 269, 57, 893));
        blueSpawns.add(new Location(w, 221, 57, 842));
        blueSpawns.add(new Location(w, 216, 61, 870));
        blueSpawns.add(new Location(w, 231, 53, 798));
        blueSpawns.add(new Location(w, 231, 59, 748));
        blueSpawns.add(new Location(w, 269, 57, 893));
        blueSpawns.add(new Location(w, 406, 46, 966));
        blueSpawns.add(new Location(w, 267, 92, 987));
        blueSpawns.add(new Location(w, 296, 60, 951));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 380, 52, 960));
        FFASpawns.add(new Location(w, 342, 72, 965));
        FFASpawns.add(new Location(w, 260, 95, 939));
        FFASpawns.add(new Location(w, 231, 69, 965));
        FFASpawns.add(new Location(w, 231, 69, 965));
        FFASpawns.add(new Location(w, 218, 88, 940));
        FFASpawns.add(new Location(w, 269, 57, 893));
        FFASpawns.add(new Location(w, 221, 57, 842));
        FFASpawns.add(new Location(w, 216, 61, 870));
        FFASpawns.add(new Location(w, 231, 53, 798));
        FFASpawns.add(new Location(w, 231, 59, 748));
        FFASpawns.add(new Location(w, 269, 57, 893));
        FFASpawns.add(new Location(w, 406, 46, 966));
        FFASpawns.add(new Location(w, 267, 92, 987));
        FFASpawns.add(new Location(w, 296, 60, 951));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        // Items
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);

        // Armor
        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        ItemMeta fishing_rod = FISHING_ROD.getItemMeta();
        fishing_rod.setDisplayName(ChatColor.GOLD + "Grappling Hook");
        FISHING_ROD.setItemMeta(fishing_rod);

        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

        ItemMeta cooked_beef = STEAK.getItemMeta();
        cooked_beef.setDisplayName(ChatColor.GOLD + "Roast Beef");
        STEAK.setItemMeta(cooked_beef);

        ItemMeta arrows = ARROWS.getItemMeta();
        arrows.setDisplayName(ChatColor.GOLD + "Steel Arrow");
        ARROWS.setItemMeta(arrows);

        ItemMeta health_potion = HEALTH_POTION.getItemMeta();
        health_potion.setDisplayName(ChatColor.RED + "Potion of grand healing");
        HEALTH_POTION.setItemMeta(health_potion);

        ItemMeta iron_sword = IRON_SWORD.getItemMeta();
        iron_sword.setDisplayName(ChatColor.GOLD + "Steel Sword");
        IRON_SWORD.setItemMeta(iron_sword);

        if (p.getTeamType() == Team.TDM_BLUE) {

            ItemMeta bow = BOW.getItemMeta();
            bow.setDisplayName(ChatColor.GOLD + "Steel Bow");
            BOW.setItemMeta(bow);

            ItemMeta leather_helmet = LEATHER_HELMET.getItemMeta();
            leather_helmet.setDisplayName(ChatColor.BLUE + "StormCloak Helmet");
            LEATHER_HELMET.setItemMeta(leather_helmet);

            // Sets boots name "StormCloak Boots"
            ItemMeta leather_boots = LEATHER_BOOTS.getItemMeta();
            leather_boots.setDisplayName(ChatColor.BLUE + "StormCloak Boots");
            LEATHER_BOOTS.setItemMeta(leather_boots);

            // Sets pants name "StormCloak Leggings"
            ItemMeta leather_pants = LEATHER_PANTS.getItemMeta();
            leather_pants.setDisplayName(ChatColor.BLUE + "StormCloak Leggings");
            LEATHER_PANTS.setItemMeta(leather_pants);

            // Sets chestplate name "StormCloak ChestPlate"
            ItemMeta leather_chestplate = LEATHER_CHESTPLATE.getItemMeta();
            leather_chestplate.setDisplayName(ChatColor.BLUE + "StormCloak ChestPlate");
            LEATHER_CHESTPLATE.setItemMeta(leather_chestplate);

        }

        if (p.getTeamType() == Team.TDM_RED) {

            // Sets bow name "Imperial Bow"
            ItemMeta bow = BOW.getItemMeta();
            bow.setDisplayName(ChatColor.GOLD + "Imperial Bow");
            BOW.setItemMeta(bow);

            // Sets helmet name "Imperial Helmet"
            ItemMeta leather_helmet = LEATHER_HELMET.getItemMeta();
            leather_helmet.setDisplayName(ChatColor.RED + "Imperial Helmet");
            LEATHER_HELMET.setItemMeta(leather_helmet);

            // Sets boots name "Imperial Boots"
            ItemMeta leather_boots = LEATHER_BOOTS.getItemMeta();
            leather_boots.setDisplayName(ChatColor.RED + "Imperial Boots");
            LEATHER_BOOTS.setItemMeta(leather_boots);

            // Sets pants name "Imperial Leggings"
            ItemMeta leather_pants = LEATHER_PANTS.getItemMeta();
            leather_pants.setDisplayName(ChatColor.RED + "Imperial Leggings");
            LEATHER_PANTS.setItemMeta(leather_pants);

            // Sets chestplate name "Imperial ChestPlate"
            ItemMeta leather_chestplate = LEATHER_CHESTPLATE.getItemMeta();
            leather_chestplate.setDisplayName(ChatColor.RED + "Imperial ChestPlate");
            LEATHER_CHESTPLATE.setItemMeta(leather_chestplate);

        }

        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setLeggings(LEATHER_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, FISHING_ROD);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH_POTION);
        i.setItem(9, ARROWS);

        pl.getInventory().getBoots().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 9);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void fishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Material material = itemStack.getType();
        Location location = player.getLocation();
        Location bobber = event.getHook().getLocation();

        if (location.getWorld().getName().equals(getName())) {

            if (material == Material.FISHING_ROD) {

                if (event.getHook().getVelocity().getY() < 0.02 && isLocationNearBlock(bobber)) {
                    player.launchProjectile(Snowball.class);
                }
            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void grapple(ProjectileHitEvent event) {
        Entity proj = event.getEntity();
        Location hit = proj.getLocation();

        if (!event.getEntity().getWorld().getName().equals(getName())) return;

        if (proj instanceof Snowball) {
            Snowball fish = (Snowball) proj;
            ProjectileSource shooter = fish.getShooter();

            if (shooter instanceof Player) {
                Player player = (Player) shooter;
                Location location = player.getLocation();
                ItemStack itemStack = player.getItemInHand();
                Material material = itemStack.getType();

                if (material == Material.FISHING_ROD) {

                    player.setFallDistance(0);
                    player.playSound(location, Sound.ARROW_HIT, 1, 1);

                    int hitx = hit.getBlockX();
                    int hity = hit.getBlockY();
                    int hitz = hit.getBlockZ();
                    int locx = location.getBlockX();
                    int locy = location.getBlockY();
                    int locz = location.getBlockZ();
                    double co[] = new double[3];

                    if (hitx > locx) {
                        co[0] = 1.2;
                    } else if (hitx < locx) {
                        co[0] = -1.2;
                    } else if (hitx == locx) {
                        co[0] = 0;
                    }

                    if (hity > locy) {
                        co[1] = 1.4;
                    } else if (hity < locy) {
                        co[1] = -0.8;
                    } else if (hity == locy) {
                        co[1] = 0;
                    }

                    if (hitz > locz) {
                        co[2] = 1.2;
                    } else if (hitz < locz) {
                        co[2] = -1.2;
                    } else if (hitz == locz) {
                        co[2] = 0;
                    }

                    player.setVelocity(new Vector(co[0], co[1] / 1.25, co[2]));

                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void teamDeath(PlayerDeathEvent event) {

        if (getArena().equals(getName())) {

            Player player = event.getEntity();
            Location location = player.getLocation();
            World world = Bukkit.getWorld(getName());

            if (BattlePlayer.getBattlePlayer(player).getTeamType() == Team.TDM_RED) {
                // Show red particles (small)
                world.playEffect(location, Effect.STEP_SOUND, 152);
            } else if (BattlePlayer.getBattlePlayer(player).getTeamType() == Team.TDM_BLUE) {
                // Show blue particles (small)
                world.playEffect(location, Effect.STEP_SOUND, 22);
            }
        }
    }
}
