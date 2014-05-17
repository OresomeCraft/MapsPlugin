package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@MapConfig
public class Pendrago extends BattleMap implements Listener {

    public Pendrago() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        setTDMTime(20);
        disableDrops(new Material[]{Material.EMERALD, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.BLAZE_ROD, Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.FLINT, Material.BOW, Material.STONE_SWORD, Material.BLAZE_ROD, Material.WATCH,
                Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_HELMET, Material.DIAMOND_HELMET,
                Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE,
                Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.STONE_SWORD, Material.WOOD_SWORD, Material.DIAMOND_SWORD, Material.GOLDEN_APPLE,
                Material.POTION, Material.TNT, Material.GOLD_SWORD, Material.NETHER_STAR, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_BOOTS,
                Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_HELMET, Material.GHAST_TEAR});
    }

    String name = "pendrago";
    String fullName = "Pendrago Square";
    String[] creators = {"__R3", "kytron", "123Oblivious"};
    Gamemode[] modes = {Gamemode.TDM};

    public void readyTDMSpawns() {
        blueSpawns.add(new Location(w, -84, 129, 6, (float) 90.085, (float) -0.784));
        redSpawns.add(new Location(w, 2, 129, 6, (float) 267.054, (float) 55.635));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -84, 129, 6, (float) 90.085, (float) -0.784));
        FFASpawns.add(new Location(w, 2, 129, 6, (float) 267.054, (float) 55.635));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        p.sendMessage(ChatColor.GOLD + "Interact with one of the signs to change class!");
    }

    public int x1 = -95;
    public int y1 = 152;
    public int z1 = -49;

    public int x2 = 10;
    public int y2 = 102;
    public int z2 = -49;

    ArrayList<String> selecting = new ArrayList<String>();

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        try {
            Player player = event.getPlayer();
            if (selecting.contains(player.getName())) {
                player.sendMessage(ChatColor.RED + "You are already selecting a class, please wait!");
                return;
            }
            Block block = event.getClickedBlock();

            if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
                BlockState state = block.getState();
                Sign sign = (Sign) state;
                // Sign options
                if (sign.getLine(1).contains("Musketeer")) {
                    handKit(player, Group.MUSKETEER);
                    return;
                }
                if (sign.getLine(1).contains("Captain")) {
                    handKit(player, Group.CAPTAIN);
                    return;
                }
                if (sign.getLine(1).contains("Scout Leader")) {
                    if (player.hasPermission("oresomebattles.rank.donator") || player.hasPermission("oresomebattles.rank.donator.plus")) {
                        handKit(player, Group.SCOUT_LEADER);
                        return;
                    } else {
                        player.sendMessage(ChatColor.RED + "That's a donator class!");
                    }
                }
                if (sign.getLine(1).contains("Engineer")) {
                    if (player.hasPermission("oresomebattles.rank.donator.plus")) {
                        handKit(player, Group.ENGINEER);
                        return;
                    } else {
                        player.sendMessage(ChatColor.RED + "That's a donator+ class!");
                    }
                }
                if (sign.getLine(1).contains("Mercenary")) {
                    handKit(player, Group.MERCENARY);
                    return;
                }
                if (sign.getLine(1).contains("Sniper")) {
                    handKit(player, Group.SNIPER);
                    return;
                }
                if (sign.getLine(1).contains("Cleric")) {
                    handKit(player, Group.CLERIC);
                    return;
                }
                if (sign.getLine(1).contains("Great Knight")) {
                    handKit(player, Group.GREAT_KNIGHT);
                    return;
                }
                if (sign.getLine(1).contains("Spy")) {
                    handKit(player, Group.SPY);
                    return;
                }
                if (sign.getLine(1).contains("Thief")) {
                    handKit(player, Group.THIEF);
                    return;
                }
                if (sign.getLine(1).contains("Scout Leader")) {
                    handKit(player, Group.SCOUT_LEADER);
                    return;
                }
                if (sign.getLine(1).contains("Rainbow Dasher")) {
                    handKit(player, Group.RAINBOW_DASHER);
                    return;
                }
                if (sign.getLine(1).contains("Potion Master")) {
                    if (player.hasPermission("oresomebattles.rank.donator") || player.hasPermission("oresomebattles.rank.donator.plus")) {
                        handKit(player, Group.POTION_MASTER);
                    } else {
                        player.sendMessage(ChatColor.RED + "That's a donator class!");
                    }
                }
            }
        } catch (NullPointerException ex) {
            // Catches null ClickedBlock
        }
    }

    private void handKit(final Player player, final Group group) {
        selecting.add(player.getName());
        player.sendMessage(ChatColor.GREEN + "You have chosen " + ChatColor.AQUA + group.toString().toLowerCase().replace("_", " ") + ChatColor.GREEN + " as your class!");

        player.getInventory().clear();
        player.updateInventory();
        player.getInventory().setHelmet(new ItemStack(Material.AIR, 0));
        player.getInventory().setChestplate(new ItemStack(Material.AIR, 0));
        player.getInventory().setLeggings(new ItemStack(Material.AIR, 0));
        player.getInventory().setBoots(new ItemStack(Material.AIR, 0));
        player.updateInventory();

        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                switch (group) {

                    case MUSKETEER:
                        ItemStack AMMO = new ItemStack(Material.FLINT, 16);
                        ItemStack BLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);

                        ItemMeta blaze_rod = BLAZE_ROD.getItemMeta();
                        blaze_rod.setDisplayName(ChatColor.BLUE + "Musket");
                        BLAZE_ROD.setItemMeta(blaze_rod);

                        ItemMeta ammo = AMMO.getItemMeta();
                        ammo.setDisplayName(ChatColor.BLUE + "Round");
                        AMMO.setItemMeta(ammo);

                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
                        player.getInventory().setItem(1, BLAZE_ROD);
                        player.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));
                        player.getInventory().setItem(9, AMMO);
                        break;

                    case CAPTAIN:
                        ItemStack KB = new ItemStack(Material.WOOD_SWORD, 1, (short) 2000);
                        KB.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);

                        ItemMeta captainAmmo = KB.getItemMeta();
                        captainAmmo.setDisplayName(ChatColor.BLUE + "Batter up!");
                        KB.setItemMeta(captainAmmo);

                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.getInventory().setItem(2, KB);
                        break;

                    case SPY:
                        ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 5);
                        ItemMeta spywatchMeta = SPY_WATCH.getItemMeta();
                        spywatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");
                        List<String> spyLore = new ArrayList<String>();
                        spyLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
                        spywatchMeta.setLore(spyLore);
                        SPY_WATCH.setItemMeta(spywatchMeta);

                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
                        player.getInventory().setItem(2, SPY_WATCH);
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        break;

                    case MERCENARY:
                        player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000 * 20, 0));
                        break;

                    case GREAT_KNIGHT:
                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));

                        player.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1, (short) -200));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000 * 20, 1));
                        break;

                    case SNIPER:
                        player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));
                        player.getInventory().setItem(0, new ItemStack(Material.BOW, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.getInventory().setItem(9, new ItemStack(Material.ARROW, 64));
                        break;

                    case CLERIC:
                        player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));
                        player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.POTION, 48, (short) 16437));
                        player.getInventory().setItem(2, new ItemStack(Material.COOKED_BEEF, 3));
                        break;

                    case THIEF:
                        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                        player.getInventory().setChestplate(LEATHER_CHESTPLATE);

                        player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));

                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20000 * 20, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000 * 20, 1));
                        break;

                    case SCOUT_LEADER:
                        ItemStack SCOUT_LEADER_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
                        player.getInventory().setChestplate(SCOUT_LEADER_CHESTPLATE);

                        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));

                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20000 * 20, 2));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000 * 20, 1));
                        break;

                    case RAINBOW_DASHER:
                        player.sendMessage(ChatColor.GREEN + "You are a Pendrago Rainbow Dasher!");

                        ItemStack rainbowKB = new ItemStack(Material.NETHER_STAR, 1);

                        ItemMeta rainbowAmmo = rainbowKB.getItemMeta();
                        rainbowAmmo.setDisplayName(ChatColor.BLUE + "Pendrago Rainbow Star");
                        rainbowKB.setItemMeta(rainbowAmmo);

                        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
                        ItemStack RAINBOW_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                        ItemStack LEATHER_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);


                        player.getInventory().setHelmet(LEATHER_HELMET);
                        player.getInventory().setChestplate(RAINBOW_CHESTPLATE);
                        player.getInventory().setLeggings(LEATHER_LEGGINGS);
                        player.getInventory().setBoots(LEATHER_BOOTS);

                        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.getInventory().setItem(8, rainbowKB);
                        break;

                    case ENGINEER:
                        ItemStack engineerKB = new ItemStack(Material.GHAST_TEAR, 1);

                        ItemMeta engineeerAmmo = engineerKB.getItemMeta();
                        engineeerAmmo.setDisplayName(ChatColor.BLUE + "Engineer's Lockpick");
                        engineerKB.setItemMeta(engineeerAmmo);

                        player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS, 1));


                        player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        player.getInventory().setItem(8, engineerKB);
                        break;

                    case POTION_MASTER:
                        player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));
                        player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD, 1));
                        player.getInventory().setItem(2, new ItemStack(Material.POTION, 12, (short) 16437));
                        player.getInventory().setItem(3, new ItemStack(Material.POTION, 12, (short) 16433));
                        player.getInventory().setItem(4, new ItemStack(Material.POTION, 12, (short) 16440));
                        player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 3));
                        break;
                }
                selecting.remove(player.getName());
            }
        }, 30L);
    }

    @EventHandler
    public void gun(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player player = event.getPlayer();
        Location location = player.getLocation();
        Action action = event.getAction();
        ItemStack itemStack = player.getItemInHand();
        Inventory inventory = player.getInventory();
        Material tool = itemStack.getType();
        final World world = location.getWorld();

        if (tool == Material.BLAZE_ROD) {

            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {

                if (inventory.contains(Material.FLINT)) {
                    player.launchProjectile(Arrow.class);
                    world.playSound(location, Sound.COW_WALK, 10, 10);
                    ItemStack AMMO = new ItemStack(Material.FLINT, 1);
                    inventory.removeItem(AMMO);

                    ItemMeta ammo = AMMO.getItemMeta();
                    ammo.setDisplayName(ChatColor.BLUE + "Round");
                    AMMO.setItemMeta(ammo);
                    inventory.removeItem(AMMO);

                    // Make it remove normal flints, too.
                    player.updateInventory();
                } else {
                    world.playSound(location, Sound.CLICK, 10, 10);
                }

            }

        }
    }

    @EventHandler
    public void onSpyWatchInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType() == Material.WATCH) {

                ItemStack SPY_WATCH = new ItemStack(Material.WATCH, 1);
                ItemMeta spyWatchMeta = SPY_WATCH.getItemMeta();
                spyWatchMeta.setDisplayName(ChatColor.BLUE + "Spy Watch");

                List<String> spyLore = new ArrayList<String>();
                spyLore.add(org.bukkit.ChatColor.BLUE + "Interact with this watch to go temporarily invisible!");
                spyWatchMeta.setLore(spyLore);
                SPY_WATCH.setItemMeta(spyWatchMeta);

                player.getInventory().removeItem(SPY_WATCH);
                player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                player.getInventory().removeItem(new ItemStack(Material.IRON_BOOTS, 1));
                player.getInventory().removeItem(new ItemStack(Material.IRON_LEGGINGS, 1));
                player.getInventory().removeItem(new ItemStack(Material.IRON_HELMET, 1));
                player.getInventory().removeItem(new ItemStack(Material.IRON_CHESTPLATE, 1));
                player.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
                player.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
                player.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
                player.getInventory().addItem(new ItemStack(Material.IRON_HELMET));

                player.updateInventory();
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 15 * 20, 0));
            }
        }
    }

    public enum Group {
        GREAT_KNIGHT,
        CLERIC,
        CAPTAIN,
        SNIPER,
        SPY,
        MUSKETEER,
        MERCENARY,
        THIEF,
        SCOUT_LEADER,
        RAINBOW_DASHER,
        ENGINEER,
        POTION_MASTER
    }


    @EventHandler
    public void PotionsSplash(PotionSplashEvent event) {
        if (!event.getPotion().getWorld().getName().equals(name)) return;
        event.setCancelled(true);
        for (LivingEntity target : event.getAffectedEntities()) {
            ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>(event.getPotion().getEffects());
            if (event.getEntity().getShooter().equals(target)) {
                Player player = (Player) target;
                if (effects.get(0).toString().startsWith("HEAL")) {
                    player.sendMessage(ChatColor.RED + "You cannot heal yourself!");
                } else {
                    target.addPotionEffect(event.getPotion().getEffects().iterator().next());
                }
            } else {
                target.addPotionEffect(event.getPotion().getEffects().iterator().next());
            }
        }
    }

    @EventHandler
    public void arrowAway(org.bukkit.event.entity.ProjectileHitEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        org.bukkit.entity.Entity projectile = event.getEntity();
        if (projectile instanceof Arrow) {
            Arrow arrow = (Arrow) projectile;
            arrow.remove();
        }
    }

    @EventHandler
    public void explode(EntityExplodeEvent event) {
        if (!event.getLocation().getWorld().getName().equals(name)) return;
        event.blockList().clear();
    }


    @EventHandler
    public void ignite(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        try {
            Player p = event.getPlayer();
            if (p.getItemInHand().getType() == Material.GHAST_TEAR && event.getClickedBlock().getType() == Material.GLASS) {
                p.sendMessage(ChatColor.RED + "*break*");
                event.getClickedBlock().setType(Material.AIR);
            }
            if (p.getItemInHand().getType() == Material.GHAST_TEAR && event.getClickedBlock().getType() == Material.TNT) {
                event.getClickedBlock().setType(Material.AIR);
                Bukkit.broadcastMessage(ChatColor.RED + "[PENDRAGO] " + p.getDisplayName() + ChatColor.RED + " SUMMONED THE COMET!");
                Bukkit.broadcastMessage(ChatColor.RED + "YOU HAVE 30 SECONDS TO TAKE COVER!");
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    public void run() {
                        Bukkit.broadcastMessage(ChatColor.RED + "THE COMET HAS BEEN SUMMONED!");
                        comet();
                    }
                }, 20 * 30);
            }
        } catch (NullPointerException ex) {
            //null catch for no block clicked
        }
    }

    ArrayList<Material> colors = new ArrayList<Material>();

    {
        colors.add(Material.GOLD_BLOCK);
        colors.add(Material.REDSTONE_BLOCK);
        colors.add(Material.EMERALD_BLOCK);
        colors.add(Material.PORTAL);
        colors.add(Material.LAPIS_BLOCK);
        colors.add(Material.DIAMOND_BLOCK);
        colors.add(Material.ICE);
    }

    ArrayList<Color> randomc = new ArrayList<Color>();

    {
        randomc.add(Color.GREEN);
        randomc.add(Color.BLUE);
        randomc.add(Color.YELLOW);
        randomc.add(Color.RED);
        randomc.add(Color.ORANGE);
        randomc.add(Color.PURPLE);
        randomc.add(Color.FUCHSIA);
    }

    @EventHandler
    public void rainbow(final PlayerMoveEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (event.getPlayer().getItemInHand().getType() == Material.NETHER_STAR && event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    if (event.getFrom().distanceSquared(event.getTo()) == 0) return;
                    Location location = event.getPlayer().getLocation();
                    location.getWorld().playEffect(location, Effect.STEP_SOUND, colors.get(new Random().nextInt(colors.size())));
                    event.getPlayer().getInventory().setHelmet(colorArmor(event.getPlayer().getInventory().getHelmet(), randomc.get(new Random().nextInt(randomc.size()))));
                    event.getPlayer().getInventory().setChestplate(colorArmor(event.getPlayer().getInventory().getChestplate(), randomc.get(new Random().nextInt(randomc.size()))));
                    event.getPlayer().getInventory().setLeggings(colorArmor(event.getPlayer().getInventory().getLeggings(), randomc.get(new Random().nextInt(randomc.size()))));
                    event.getPlayer().getInventory().setBoots(colorArmor(event.getPlayer().getInventory().getBoots(), randomc.get(new Random().nextInt(randomc.size()))));
                }
            }, 10L);
        }
    }

    ArrayList<Material> ORES = new ArrayList<Material>();

    {
        ORES.add(Material.DIAMOND_ORE);
        ORES.add(Material.GOLD_ORE);
        ORES.add(Material.EMERALD_ORE);
        ORES.add(Material.COAL_ORE);
        ORES.add(Material.REDSTONE_ORE);
        ORES.add(Material.EMERALD_ORE);
        ORES.add(Material.LAPIS_ORE);
        ORES.add(Material.IRON_ORE);
        ORES.add(Material.STONE);
        ORES.add(Material.STONE);
        ORES.add(Material.STONE);
    }

    protected void comet() {
        for (Block b : circle(new Location(Bukkit.getWorld(name), -41, 253, 6), 3, 2, false, true, 0)) {
            FallingBlock fb = Bukkit.getWorld(name).spawnFallingBlock(b.getLocation(), ORES.get(new Random().nextInt(ORES.size())), (byte) 1);
            fb.setDropItem(false);
            fb.setVelocity(new Vector(0, -2.7, 0));
        }
        final BukkitTask effects = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            public void run() {
                for (FallingBlock fallingBlock : Bukkit.getWorld(name).getEntitiesByClass(FallingBlock.class)) {
                    Location l1 = fallingBlock.getLocation();

                    //Make an entire new object from the original one because it will muck up the current one
                    Location l2 = new Location(l1.getWorld(), l1.getX(), l1.getY(), l1.getZ());

                    l1.subtract(0.5, 0, 0.5);
                    l2.subtract(0.5, 0, 0.5);

                    l1.subtract(0, 1, 0);
                    l1.getWorld().playEffect(l1, Effect.SMOKE, 0);

                    l1.subtract(0, 3, 0);
                    l1.getWorld().playEffect(l1, Effect.MOBSPAWNER_FLAMES, 0);

                    int times = 7;
                    while (times > 0) {
                        times--;
                        l1.add(0, 1, 0);
                        l1.getWorld().playEffect(l1, Effect.SMOKE, 0);
                    }

                }
            }
        }, 2L, 2L);
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                effects.cancel();
                int strikes = 20;
                while (strikes > 0) {
                    Bukkit.getWorld(name).strikeLightning(new Location(Bukkit.getWorld(name), -41, 108, 6));
                    strikes--;
                }

                Bukkit.getWorld(name).createExplosion(new Location(Bukkit.getWorld(name), -41, 108, 6), 90F);

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getWorld().getName().equals(name) && p.getGameMode() == GameMode.SURVIVAL) {
                        double distance = new Location(Bukkit.getWorld(name), -41, 108, 6).distance(p.getLocation());
                        if (distance <= 15) {
                            p.damage(40);
                        } else if (distance <= 30) {
                            p.damage(20);
                        } else if (distance > 30) {
                            p.damage(10);
                        }
                    }
                }
                circleSmoke(new Location(Bukkit.getWorld(name), -41, 108, 6));
            }
        }, 53);
    }

    protected static List<Block> circle(Location loc, int radius, int height, boolean hollow, boolean sphere, int plusY) {
        List<Block> circleblocks = new ArrayList<Block>();
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                for (int y = (sphere ? cy - radius : cy); y < (sphere ? cy + radius : cy + height); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);

                    if (dist < radius * radius && !(hollow && dist < (radius - 1) * (radius - 1))) {
                        Location l = new Location(loc.getWorld(), x, y + plusY, z);
                        circleblocks.add(l.getBlock());
                    }
                }
            }
        }

        return circleblocks;
    }

    private void circleSmoke(Location loc) {
        List<Integer> num = new ArrayList<Integer>();
        int x = 0;
        int z = 0;
        int y = (int) loc.getY();
        for (double i = 0.0; i < 360.0; i += 0.1) {
            double angle = i * Math.PI / 180;
            x = (int) (loc.getX() + 30 * Math.cos(angle));
            z = (int) (loc.getZ() + 30 * Math.sin(angle));
            Location loa = loc.getWorld().getBlockAt(x, y, z).getLocation();
            if (x < loc.getX() && !num.contains((int) loa.getZ())) {
                num.add((int) loa.getZ());
                int mid = (int) (loc.getX() - loa.getX());
                for (int ai = (int) loa.getX(); ai <= loc.getX() + mid; ai++) {
                    Location lc = new Location(loa.getWorld(), ai, y, loa.getZ());
                    lc.getWorld().playEffect(lc, Effect.SMOKE, 0);
                }
            }
        }
    }

    private ItemStack colorArmor(ItemStack itemStack, Color color) {
        try {
            LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
            meta.setColor(color);
            itemStack.setItemMeta(meta);
        } catch (NullPointerException ex) {

        }
        return itemStack;
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        event.setCancelled(true);
    }
}
