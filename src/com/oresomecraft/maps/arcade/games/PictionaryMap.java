package com.oresomecraft.maps.arcade.games;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.arcade.ArcadeMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.world.WorldLoadEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class PictionaryMap extends ArcadeMap implements CommandExecutor {

    boolean hasPassedDelay = false;
    List<Player> players;
    Player pictionaryBuilder;
    List<Player> hasPlayed;
    String currentBuild;

    public String[] builds;

    public int x1;
    public int y1;
    public int z1;

    public int x2;
    public int y2;
    public int z2;

    /**
     * Sets the available builds
     *
     * @param builds The builds that a player can be asked to build
     */
    public void setBuilds(String[] builds) {
        this.builds = builds;
    }

    @EventHandler
    public void onLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            Bukkit.getScheduler().runTaskLater(MapsPlugin.getInstance(), new Runnable() {
                public void run() {
                    hasPassedDelay = true;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (BattlePlayer.getBattlePlayer(player).inBattle()) {
                            players.add(player);
                        }
                    }
                    Random random = new Random();
                    setPictionaryBuilder(players.get(random.nextInt(players.size())));
                }
            }, 200L);
        }
    }

    @EventHandler
    public void onEnd(BattleEndEvent event) {
        hasPassedDelay = false;
        players.clear();
        pictionaryBuilder = null;
        hasPlayed.clear();
        currentBuild = null;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) {
            if (!event.getPlayer().equals(pictionaryBuilder)) {
                event.setCancelled(true);
            } else {
                if (!contains(event.getBlockPlaced().getLocation(), x1, x2, y1, y2, z1, z2)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equals(name)) {
            if (!event.getPlayer().equals(pictionaryBuilder)) {
                event.setCancelled(true);
            } else {
                if (!contains(event.getBlock().getLocation(), x1, x2, y1, y2, z1, z2)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public void setPictionaryBuilder(Player player) {
        pictionaryBuilder = player;
        hasPlayed.add(player);
        Random random = new Random();
        currentBuild = builds[random.nextInt(builds.length)];
        player.sendMessage(ChatColor.GOLD + "You're the pictionary builder! Build a " + ChatColor.GREEN + currentBuild + "!");

        for (Player spectatingPlayer : Bukkit.getOnlinePlayers()) {
            if (BattlePlayer.getBattlePlayer(player).inBattle()) {
                if (spectatingPlayer != player) {
                    spectatingPlayer.sendMessage(ChatColor.GOLD + "Try to guess what " + player.getName() + " is building!");
                    spectatingPlayer.sendMessage(ChatColor.GOLD + "Use " + ChatColor.GREEN + "/guess <What you think they're building>" + ChatColor.GOLD + " to guess!");
                }
            }
        }
    }

    public void switchPlayer() {
        Random random = new Random();
        for (int i = 0; i <= players.size(); i++) {
            final int selectedPlayer = random.nextInt(players.size());
            if (!hasPlayed.contains(players.get(selectedPlayer)) && players.get(selectedPlayer).isOnline()) {
                if (BattlePlayer.getBattlePlayer(players.get(selectedPlayer)).inBattle()) {
                    setPictionaryBuilder(players.get(selectedPlayer));
                } else {
                    players.get(selectedPlayer).remove();
                }
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("guess")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.getWorld().getName().equals(name)) {
                    if (BattlePlayer.getBattlePlayer(player).inBattle()) {
                        if (player != pictionaryBuilder) {
                            if (args.length >= 1) {
                                if (args[0].equalsIgnoreCase(currentBuild)) {
                                    Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + ChatColor.GOLD + " has won!");
                                    Bukkit.broadcastMessage(ChatColor.GOLD + "The correct answer was " + ChatColor.GREEN + currentBuild);
                                    switchPlayer();
                                    return true;
                                }
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "You can't guess at what " + ChatColor.ITALIC + "you're" + ChatColor.RESET + " building!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "You must be in the game to guess at a pictionary build!");
                    }
                }
            }
        }
        return false;
    }
}
