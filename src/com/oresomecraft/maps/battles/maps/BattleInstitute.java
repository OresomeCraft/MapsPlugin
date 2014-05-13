package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Random;

@MapConfig
public class BattleInstitute extends BattleMap implements Listener {

    public BattleInstitute() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.ARROW, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.BOW, Material.IRON_SWORD, Material.IRON_BOOTS, Material.COOKED_BEEF, Material.POTION});
    }

    String name = "battleinstitute";
    String fullName = "The Battle Institute";
    String[] creators = {"__R3"};
    Gamemode[] modes = {Gamemode.LTS};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(Bukkit.getWorld(name), 0.5, 65, 13.5, (float) 359.434, (float) 0.027));
        blueSpawns.add(new Location(Bukkit.getWorld(name), 0.5, 65, -17.5, (float) 180.056, (float) 2.057));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(Bukkit.getWorld(name), 0.5, 65, 13.5, (float) 359.434, (float) 0.027));
        FFASpawns.add(new Location(Bukkit.getWorld(name), 0.5, 65, -17.5, (float) 180.056, (float) 2.057));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        p.sendMessage(ChatColor.GOLD + "Wait until you are called and then punch the block!");
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 23;
    public int y1 = 89;
    public int z1 = 21;

    //Bottom right corner.
    public int x2 = -23;
    public int y2 = 58;
    public int z2 = -23;

    ArrayList<String> red = new ArrayList<String>();
    ArrayList<String> blue = new ArrayList<String>();

    String currentRed = "None";
    String currentBlue = "None";

    boolean blueEnter = false;
    boolean redEnter = false;

    @EventHandler
    public void end(BattleEndEvent event) {
        blueEnter = false;
        redEnter = false;
        currentBlue = "None";
        currentRed = "None";
        red.clear();
        blue.clear();
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        red.remove(event.getPlayer().getName());
        blue.remove(event.getPlayer().getName());
        Bukkit.broadcastMessage(ChatColor.GREEN + "[BattleInstitute] " + event.getPlayer().getName() + " quit!");
        if (currentRed.equals(event.getPlayer().getName()) || currentBlue.equals(event.getPlayer().getName()))
            endRound();
    }

    @EventHandler
    public void death(PlayerDeathEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        red.remove(event.getEntity().getName());
        blue.remove(event.getEntity().getName());
        if (currentRed.equals(event.getEntity().getName()) || currentBlue.equals(event.getEntity().getName()))
            endRound();
    }

    @EventHandler
    public void worldLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equalsIgnoreCase(name)) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "[BattleInstitute] Starting up!");
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        try {
                            if (BattlePlayer.getBattlePlayer(p.getName()).getTeamType() == Team.LTS_RED) {
                                red.add(p.getName());
                            } else if (BattlePlayer.getBattlePlayer(p.getName()).getTeamType() == Team.LTS_BLUE) {
                                blue.add(p.getName());
                            }
                        } catch (Exception ex) {
                            // Really bad ugly and inefficient temp fix
                        }
                    }
                    newRound();
                }
            }, 80L);
        }
    }

    @EventHandler
    public void leaveSpec(final PlayerCommandPreprocessEvent event) {
        try {
            if (event.getMessage().toLowerCase().startsWith("/join")) {
                try {
                    if (!red.contains(event.getPlayer().getName()) && !blue.contains(event.getPlayer().getName()) && Bukkit.getWorld(name).getPlayers().size() != 0) {
                        if (Bukkit.getWorld(name).getPlayers().size() == 0) return;
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            public void run() {
                                if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                                    if (BattlePlayer.getBattlePlayer(event.getPlayer()).getTeamType() == Team.LTS_RED) {
                                        if (!red.contains(event.getPlayer().getName()))
                                            red.add(event.getPlayer().getName());
                                        Bukkit.broadcastMessage(ChatColor.RED + event.getPlayer().getName() + " joined red!");
                                    } else if (BattlePlayer.getBattlePlayer(event.getPlayer()).getTeamType() == Team.LTS_BLUE) {
                                        if (!blue.contains(event.getPlayer().getName()))
                                            blue.add(event.getPlayer().getName());
                                        Bukkit.broadcastMessage(ChatColor.BLUE + event.getPlayer().getName() + " joined blue!");
                                    }
                                }
                            }
                        }, 2L);

                    }
                } catch (Exception exc) {
                    // TODO: This is inefficient, fix it.
                }
            }

            // This is the blocked stuff
            if (!event.getPlayer().getWorld().getName().equals(name)) return;
            if (event.getMessage().toLowerCase().startsWith("/potion")) event.setCancelled(true);
            if (event.getMessage().toLowerCase().startsWith("/leave") || event.getMessage().toLowerCase().startsWith("/spectate")) {
                if (red.contains(event.getPlayer().getName()) || blue.contains(event.getPlayer().getName())) {
                    Bukkit.broadcastMessage(ChatColor.RED + event.getPlayer().getName() + " left the round!!");
                    if (currentRed.equals(event.getPlayer().getName()) || currentBlue.equals(event.getPlayer().getName()))
                        endRound();
                    red.remove(event.getPlayer().getName());
                    blue.remove(event.getPlayer().getName());
                }
            }
        } catch (Exception e) {

        }
    }

    @EventHandler
    public void ironBlock(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        try {
            if (event.getClickedBlock().getType() == Material.IRON_BLOCK) {
                join(event.getPlayer().getName());
            }
        } catch (NullPointerException ex) {

        }
    }

    public void newRound() {
        String newRed = red.get(new Random().nextInt(red.size()));
        String newBlue = blue.get(new Random().nextInt(blue.size()));
        redEnter = false;
        blueEnter = false;
        Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] The following players have 5 seconds to click the iron block!");
        Bukkit.broadcastMessage(ChatColor.RED + newRed);
        Bukkit.broadcastMessage(ChatColor.BLUE + newBlue);
        currentBlue = newBlue;
        currentRed = newRed;
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                if (!blueEnter) join(currentBlue);
                if (!redEnter) join(currentRed);
            }
        }, 5 * 20L);
    }

    private void endRound() {
        Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] Round over! Picking new players!");
        try {
            Bukkit.getPlayer(currentBlue).teleport(new Location(Bukkit.getWorld(name), 0.5, 65, -17.5, (float) 180.056, (float) 2.057));
            Bukkit.getPlayer(currentRed).teleport(new Location(Bukkit.getWorld(name), 0.5, 65, 13.5, (float) 359.434, (float) 0.027));
        } catch (NullPointerException ex) {
            System.out.println("[MapsPlugin] [ERROR] A NullPointerException occured when trying to teleport players to a floating point location!");
            System.out.println("[MapsPlugin] [ERROR] battles/maps/BattleInstitute:193");
        }
        if (red.size() == 0 || blue.size() == 0) {
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] GAME OVER!");
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (BattlePlayer.getBattlePlayer(p).inBattle() && !BattlePlayer.getBattlePlayer(p).isSpectator()) {
                p.getInventory().clear();
                p.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                p.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                p.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                p.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                for (PotionEffect po : p.getActivePotionEffects()) {
                    p.removePotionEffect(po.getType());
                }
                p.setHealth(20);
                p.setFoodLevel(20);
                p.updateInventory();
            }
        }
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                newRound();
            }
        }, 40L);
    }

    private void join(String player) {
        if (currentBlue.equals(player) && !blueEnter) {
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] " + player + " joins!");
            blueEnter = true;
            Player p = Bukkit.getPlayer(currentBlue);
            p.teleport(new Location(Bukkit.getWorld(name), 0.5, 65, -11.5, 180, (float) -0.108));
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            handKit(p);
        }
        if (currentRed.equals(player) && !redEnter) {
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleInstitute] " + player + " joins!");
            redEnter = true;
            Player p = Bukkit.getPlayer(currentRed);
            p.teleport(new Location(Bukkit.getWorld(name), 0.5, 65, 7.5, (float) 0.652, (float) -5.384));
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            handKit(p);
        }
    }

    private void handKit(Player p) {
        Inventory i = p.getInventory();
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 12);
        ItemStack IRON_HELMET = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(9, ARROWS);
        p.updateInventory();
    }

    @EventHandler
    public void worldLoad(FoodLevelChangeEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        event.setFoodLevel(20);
    }
}
