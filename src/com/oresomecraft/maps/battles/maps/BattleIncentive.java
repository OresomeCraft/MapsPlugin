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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
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
public class BattleIncentive extends BattleMap implements Listener {

    public BattleIncentive() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_SWORD, Material.IRON_BOOTS, Material.COOKED_BEEF, Material.POTION});
    }

    String name = "battleincentive";
    String fullName = "The Battle Incentive";
    String[] creators = {"__R3"};
    Gamemode[] modes = {Gamemode.LTS};

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -21, 72, 150, 359.6F, 8.9F));
        blueSpawns.add(new Location(w, -21, 72, 96, 179.9F, 10.8F));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -21, 72, 150, 359.6F, 8.9F));
        FFASpawns.add(new Location(w, -21, 72, 96, 179.9F, 10.8F));
        defineRegion(x1, x2, y1, y2, z1, z2);
    }

    public void applyInventory(final BattlePlayer p) {
        p.sendMessage(ChatColor.GOLD + "Welcome to The Battle Incentive!");
        p.sendMessage(ChatColor.GOLD + "This is an arena that specialises in " + ChatColor.BOLD + "2v2 tag-team!");
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -59;
    public int y1 = 65;
    public int z1 = 92;

    //Bottom right corner.
    public int x2 = 20;
    public int y2 = 105;
    public int z2 = 158;

    ArrayList<String> red = new ArrayList<String>();
    ArrayList<String> blue = new ArrayList<String>();

    String currentRed = "???";
    String currentRed2 = "???";
    String currentBlue = "???";
    String currentBlue2 = "???";

    @EventHandler
    public void end(BattleEndEvent event) {
        currentBlue = "???";
        currentBlue2 = "???";
        currentRed = "???";
        currentRed2 = "???";
        red.clear();
        blue.clear();
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        removeRed(event.getPlayer().getName());
        removeBlue(event.getPlayer().getName());
        Bukkit.broadcastMessage(ChatColor.GREEN + "[BattleIncentive] " + event.getPlayer().getName() + " quit!");
        endOrSwap(event.getPlayer().getName());
    }

    @EventHandler
    public void death(PlayerDeathEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        removeRed(event.getEntity().getName());
        removeBlue(event.getEntity().getName());
        endOrSwap(event.getEntity().getName());
    }

    @EventHandler
    public void worldLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equalsIgnoreCase("battleincentive")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "[BattleIncentive] Starting up!");
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        try {
                            if (BattlePlayer.getBattlePlayer(p.getName()).getTeamType() == Team.LTS_RED) {
                                addRed(p.getName());
                            } else if (BattlePlayer.getBattlePlayer(p.getName()).getTeamType() == Team.LTS_BLUE) {
                                addBlue(p.getName());
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
                                        addBlue(event.getPlayer().getName());
                                        Bukkit.broadcastMessage(ChatColor.RED + event.getPlayer().getName() + " joined red!");
                                    } else if (BattlePlayer.getBattlePlayer(event.getPlayer()).getTeamType() == Team.LTS_BLUE) {
                                        addBlue(event.getPlayer().getName());
                                        Bukkit.broadcastMessage(ChatColor.BLUE + event.getPlayer().getName() + " joined blue!");
                                    }
                                }
                            }
                        }, 2L);

                    }
                } catch (Exception exc) {
                    //lol I suck
                }
            }

            if (!event.getPlayer().getWorld().getName().equals(name)) return;
            // This is the blocked stuff
            if (event.getMessage().toLowerCase().startsWith("/potion")) event.setCancelled(true);
            if (event.getMessage().toLowerCase().startsWith("/leave") || event.getMessage().toLowerCase().startsWith("/spectate")) {
                if (red.contains(event.getPlayer().getName()) || blue.contains(event.getPlayer().getName())) {
                    Bukkit.broadcastMessage(ChatColor.RED + event.getPlayer().getName() + " left the round!");
                    endOrSwap(event.getPlayer().getName());
                    removeRed(event.getPlayer().getName());
                    removeBlue(event.getPlayer().getName());
                }
            }
        } catch (Exception e) {
            // TODO: This is inefficient, fix it.
        }
    }

    @EventHandler
    public void click(PlayerInteractEvent event) {
        if (!event.getPlayer().getWorld().getName().equals(name)) return;
        if (!safe) return;
        try {
            if (event.getClickedBlock().getType() == Material.LAPIS_BLOCK && currentBlue.equals(event.getPlayer().getName())) {
                swap(event.getPlayer().getName(), true);
                return;
            }
            if (event.getClickedBlock().getType() == Material.REDSTONE_BLOCK && currentRed.equals(event.getPlayer().getName())) {
                swap(event.getPlayer().getName(), true);
            }
        } catch (NullPointerException ex) {

        }
    }

    boolean safe = false;

    public void newRound() {
        String newRed = red.get(new Random().nextInt(red.size()));
        String newRed1 = "???";

        //This is ugly, if something goes wrong we're stuck in an infinite loop.
        while (true) {
            if (red.size() == 1) break;
            String temp = red.get(new Random().nextInt(red.size()));
            if (newRed.equals(temp)) {
                if (red.size() == 1) break;
                continue;
            } else {
                newRed1 = temp;
                break;
            }
        }

        String newBlue = blue.get(new Random().nextInt(blue.size()));
        String newBlue1 = "???";

        while (true) {
            if (blue.size() == 1) break;
            String temp = blue.get(new Random().nextInt(blue.size()));
            if (newBlue.equals(temp)) {
                if (blue.size() == 1) break;
                continue;
            } else {
                newBlue1 = temp;
                break;
            }
        }

        Bukkit.broadcastMessage(ChatColor.RED + "[BattleIncentive] The next match has been decided!");
        Bukkit.broadcastMessage(ChatColor.RED + c(newRed, newRed1));
        Bukkit.broadcastMessage(ChatColor.GREEN + "VERSUS");
        Bukkit.broadcastMessage(ChatColor.BLUE + c(newBlue, newBlue1));
        currentBlue = newBlue;
        currentRed = newRed;
        currentBlue2 = newBlue1;
        currentRed2 = newRed1;
        Bukkit.broadcastMessage(ChatColor.RED + "THE MATCH STARTS IN 10 SECONDS!");
        safe = true;
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            public void run() {
                join(currentBlue);
                join(currentBlue2);
                join(currentRed);
                join(currentRed2);
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game on, fellas! FIGHT!");
                safe = false;
            }
        }, 10 * 20L);
    }

    private void swap(String p, boolean manual) {
        if (manual) {
            Player pl = Bukkit.getPlayer(p);
            if (currentBlue.equals(p)) {
                if (currentBlue2.equals("???")) {
                    pl.sendMessage(ChatColor.RED + "You can't swap with no teammate!");
                    return;
                } else {
                    String temp = currentBlue + "";
                    String temp2 = currentBlue2 + "";

                    //le switcharoo
                    currentBlue2 = temp;
                    currentBlue = temp2;

                    Bukkit.getPlayer(currentBlue2).teleport(new Location(Bukkit.getWorld(name), -21, 72, 102, 179.6F, -2F));
                    Bukkit.getPlayer(currentBlue).teleport(new Location(Bukkit.getWorld(name), -21, 72, 111, 179.8F, -1.7F));

                    Bukkit.broadcastMessage(ChatColor.RED + "[BattleIncentive] " + ChatColor.BLUE + currentBlue + ChatColor.RED + " tagged " +
                            ChatColor.BLUE + currentBlue2 + ChatColor.RED + "!");
                    return;
                }
            }
            if (currentRed.equals(p)) {
                if (currentRed2.equals("???")) {
                    pl.sendMessage(ChatColor.RED + "You can't swap with no teammate!");
                    return;
                } else {
                    String temp = currentRed + "";
                    String temp2 = currentRed2 + "";

                    //le switcharoo
                    currentRed2 = temp;
                    currentRed = temp2;

                    Bukkit.getPlayer(currentRed2).teleport(new Location(Bukkit.getWorld("battleincentive"), -21, 72, 144, 359.8F, -3.2F));
                    Bukkit.getPlayer(currentRed).teleport(new Location(Bukkit.getWorld("battleincentive"), -21, 72, 135, 359.3F, -0.7F));

                    Bukkit.broadcastMessage(ChatColor.RED + "[BattleIncentive] " + currentRed + " tagged " + currentRed2 + "!");
                    return;
                }
            }
            return;
        }
        if (currentBlue2.equals(p)) {
            currentBlue2 = "???";
            currentBlue = p;
            Bukkit.getPlayer(currentBlue).teleport(new Location(Bukkit.getWorld("battleincentive"), -21, 72, 111, 179.8F, -1.7F));
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleIncentive] " + ChatColor.BLUE + p + " jumps in!");
            return;
        }
        if (currentRed2.equals(p)) {
            currentRed2 = "???";
            currentRed = p;
            Bukkit.getPlayer(currentRed).teleport(new Location(Bukkit.getWorld("battleincentive"), -21, 72, 135, 359.3F, -0.7F));
            Bukkit.broadcastMessage(ChatColor.RED + "[BattleIncentive] " + p + " jumps in!");
        }
    }

    private String c(String s1, String s2) {
        if (s2.equals("???")) return s1;
        return s1 + " and " + s2;
    }

    private void endOrSwap(String whoDied) {
        if (whoDied.equals(currentRed2)) {
            //we can ignore this, just set them to nonexistent.
            currentRed2 = "???";
        }
        if (whoDied.equals(currentBlue2)) {
            //we can ignore this, just set them to nonexistent.
            currentBlue2 = "???";
        }
        if (whoDied.equals(currentRed)) {
            if (currentRed2.equals("???")) {
                endRound();
            } else {
                swap(currentRed2, false);
            }
        }
        if (whoDied.equals(currentBlue)) {
            if (currentBlue2.equals("???")) {
                endRound();
            } else {
                swap(currentBlue2, false);
            }
        }
    }

    private void endRound() {
        if (red.size() == 0 || blue.size() == 0) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "[BattleIncentive] Game over!");
            return;
        }
        Bukkit.broadcastMessage(ChatColor.RED + "[BattleIncentive] Round over!");
        try {
            if (!currentRed.equals("???"))
                Bukkit.getPlayer(currentRed).teleport(new Location(Bukkit.getWorld(name), -21, 72, 150, 359.6F, 8.9F));
            if (!currentRed2.equals("???"))
                Bukkit.getPlayer(currentRed2).teleport(new Location(Bukkit.getWorld(name), -21, 72, 150, 359.6F, 8.9F));
            if (!currentBlue.equals("???"))
                Bukkit.getPlayer(currentBlue).teleport(new Location(Bukkit.getWorld(name), -21, 72, 96, 179.9F, 10.8F));
            if (!currentBlue2.equals("???"))
                Bukkit.getPlayer(currentBlue2).teleport(new Location(Bukkit.getWorld(name), -21, 72, 96, 179.9F, 10.8F));
        } catch (NullPointerException ex) {
            System.out.println("[BATTLINCENTIVE] ERROR: ONE OF THE PLAYERS HAS FAILED TO TELEPORT");
        }

        currentBlue = "???";
        currentBlue2 = "???";
        currentRed = "???";
        currentRed2 = "???";

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

    //Checked
    private void join(String player) {
        if (player.equals("???")) return;
        if (currentBlue.equals(player) || currentBlue2.equals(player)) {
            Player p = Bukkit.getPlayer(player);
            if (currentBlue.equals(player)) {
                p.teleport(new Location(Bukkit.getWorld("battleincentive"), -21, 72, 111, 179.8F, -1.7F));
            } else if (currentBlue2.equals(player)) {
                p.teleport(new Location(Bukkit.getWorld("battleincentive"), -21, 72, 102, 179.6F, -2F));
            }
            for (PotionEffect po : p.getActivePotionEffects()) {
                p.removePotionEffect(po.getType());
            }
            p.setHealth(20);
            p.setFoodLevel(20);
            handKit(p);
        }
        if (currentRed.equals(player) || currentRed2.equals(player)) {
            Player p = Bukkit.getPlayer(player);
            if (currentRed.equals(player)) {
                p.teleport(new Location(Bukkit.getWorld("battleincentive"), -21, 72, 135, 359.3F, -0.7F));
            } else if (currentRed2.equals(player)) {
                p.teleport(new Location(Bukkit.getWorld("battleincentive"), -21, 72, 144, 359.8F, -3.2F));
            }
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
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, HEALTH_POTION);
        p.updateInventory();
    }

    @EventHandler
    public void food(FoodLevelChangeEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        event.setFoodLevel(20);
    }

    @EventHandler
    public void idleHeal(EntityRegainHealthEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (currentBlue2.equals(p.getName()) || currentRed2.equals(p.getName())) {
                event.setCancelled(true);
                if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.MAGIC_REGEN) {
                    p.sendMessage(ChatColor.RED + "Nice try. :)");
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void damage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (currentBlue2.equals(p.getName()) || currentRed2.equals(p.getName())) {
                event.setCancelled(true);
                ((Player) event.getDamager()).sendMessage(ChatColor.RED + "You cannot damage the waiting enemy!");
            }
        }
    }

    public void addRed(String name) {
        if (red.contains(name)) return;
        red.add(name);
    }

    public void addBlue(String name) {
        if (blue.contains(name)) return;
        blue.add(name);
    }

    public void removeRed(String name) {
        int attempts = 20;
        while (attempts > 0) {
            if (!red.contains(name)) break;
            if (red.contains(name)) attempts--;
            red.remove(name);
        }
    }

    public void removeBlue(String name) {
        int attempts = 20;
        while (attempts > 0) {
            if (!blue.contains(name)) break;
            if (blue.contains(name)) attempts--;
            blue.remove(name);
        }
    }
}
