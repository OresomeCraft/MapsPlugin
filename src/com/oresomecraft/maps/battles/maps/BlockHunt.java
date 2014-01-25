package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.events.BattleEndEvent;
import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.MapsPlugin;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

@MapConfig
public class BlockHunt extends BattleMap implements IBattleMap, Listener {

    public BlockHunt() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(8);
        setAllowBuild(false);
        setAutoSpawnProtection(10);
    }

    String name = "blockhunt";
    String fullName = "Block Hunt";
    String creators = "__R3 ";
    Gamemode[] modes = {Gamemode.CTF, Gamemode.INFECTION};

    //Disguise stuff
    //private DisguiseManager disguiseManager = new DisguiseManager(MapsPlugin.getInstance());

    /*@EventHandler
    public void battleStart(WorldLoadEvent e) {
        if (e.getWorld().getName().equals(name)) {
            disguiseManager.startTask();
        }
    }

    @EventHandler
    public void battleEnd(BattleEndEvent e) {
        disguiseManager.cancelTask();
    }*/

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 2, 84, -48, -1, 0));
        blueSpawns.add(new Location(w, -3, 84, 58, -178, 0));

        setKoTHMonument(new Location(w, -1, 115, 4));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 2, 84, -48, -1, 0));
        FFASpawns.add(new Location(w, -3, 84, 58, -178, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 4);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BOOTS = new ItemStack(Material.DIAMOND_BOOTS);

        BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);

        ItemStack e = new ItemStack(Material.EMERALD, 2);
        ItemMeta im = e.getItemMeta();
        im.setDisplayName(ChatColor.BLUE + "RIGHT CLICK A BLOCK TO DISGUISE AS IT!");
        e.setItemMeta(im);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH);
        i.setItem(11, ARROWS);
        i.setItem(3, new ItemStack(Material.BREAD, 3));
        i.setItem(8, e);

    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 29;
    public int y1 = 142;
    public int z1 = 56;

    //Bottom right corner.
    public int x2 = -203;
    public int y2 = 42;
    public int z2 = -72;

    /**
     * ALL CODE FOR DISGUISE MANAGER GOES BELOW HERE, DON'T TOUCH!
     */

    /*
    private class DisguisedPlayer {
        public final Material material;
        public final byte blockData;
        public Block lastBlock;
        public final String playerName;

        public DisguisedPlayer(String sPlayerName, Material material, byte blockData) {
            this.playerName = sPlayerName;
            this.material = material;
            this.blockData = blockData;
        }
    }

    private class DisguiseTask implements Runnable {
        private final DisguiseManager dm;

        public DisguiseTask(DisguiseManager dm) {
            this.dm = dm;
        }

        @Override
        public void run() {
            for (DisguisedPlayer disguisedPlayer : dm.players.values()) {
                Player pDisguisedPlayer = dm.plugin.getServer().getPlayer(disguisedPlayer.playerName);

                if (pDisguisedPlayer == null) {
                    continue;
                }

                if (disguisedPlayer.lastBlock == null) {
                    for (Player pHideFrom : pDisguisedPlayer.getWorld().getPlayers()) {
                        if (!pHideFrom.equals(pDisguisedPlayer)) {
                            pHideFrom.sendBlockChange(pDisguisedPlayer.getLocation(), disguisedPlayer.material, disguisedPlayer.blockData);
                        }
                    }

                    disguisedPlayer.lastBlock = pDisguisedPlayer.getLocation().getBlock();

                    continue;
                }

                Block currentBlock = pDisguisedPlayer.getLocation().getBlock();

                if (currentBlock != null
                        && !currentBlock.equals(disguisedPlayer.lastBlock)) {
                    for (Player pHideFrom : pDisguisedPlayer.getWorld().getPlayers()) {
                        if (!pDisguisedPlayer.equals(pHideFrom)) {
                            if (pDisguisedPlayer.getLocation().distanceSquared(pHideFrom.getLocation()) < 1000) {
                                pHideFrom.sendBlockChange(pDisguisedPlayer.getLocation(), disguisedPlayer.material, disguisedPlayer.blockData);
                            }

                            pHideFrom.sendBlockChange(
                                    disguisedPlayer.lastBlock.getLocation(),
                                    disguisedPlayer.lastBlock.getType(),
                                    disguisedPlayer.lastBlock.getData());
                        }
                    }

                    disguisedPlayer.lastBlock = currentBlock;
                } else {
                    for (Player pHideFrom : pDisguisedPlayer.getWorld().getPlayers()) {
                        if (!pDisguisedPlayer.equals(pHideFrom)) {
                            pHideFrom.sendBlockChange(pDisguisedPlayer.getLocation(), disguisedPlayer.material, disguisedPlayer.blockData);
                        }
                    }
                    disguisedPlayer.lastBlock = currentBlock;
                }
            }
        }
    }


    private class DisguiseManager {
        final MapsPlugin plugin;
        final Map<String, DisguisedPlayer> players;
        private int Task;

        public DisguiseManager(MapsPlugin plugin) {
            this.plugin = plugin;
            this.players = new HashMap<String, DisguisedPlayer>();
        }

        public boolean isDisguised(Player player) {
            return this.players.containsKey(player.getName());
        }

        public void cancelTask() {
            Bukkit.getScheduler().cancelTask(Task);
            for (DisguisedPlayer p : players.values()) {
                undisguise(Bukkit.getPlayer(p.playerName));
            }
            players.clear();
        }

        public void startTask() {
            Task = Bukkit.getScheduler().scheduleSyncRepeatingTask(
                    plugin,
                    new DisguiseTask(this),
                    1L,
                    1L
            );
        }

        public void disguise(Player player, Material material, byte blockData) {
            this.players.put(player.getName(), new DisguisedPlayer(player.getName(), material, blockData));
            for (Player pHideFrom : Bukkit.getOnlinePlayers()) {
                pHideFrom.hidePlayer(player);
            }
        }

        public void undisguise(Player player) {
            if (this.players.containsKey(player.getName())) {
                DisguisedPlayer dp = this.players.remove(player.getName());

                if (dp != null && dp.lastBlock != null) {
                    for (Player p : player.getWorld().getPlayers()) {
                        p.sendBlockChange(dp.lastBlock.getLocation(), dp.lastBlock.getType(), dp.lastBlock.getData());
                    }
                }
                for (Player pShowTo : Bukkit.getOnlinePlayers()) {
                    pShowTo.showPlayer(player);
                }
            }
        }

        public void hideDisguisedPlayersFrom(Player player) {
            for (DisguisedPlayer dp : this.players.values()) {
                player.hidePlayer(Bukkit.getPlayer(dp.playerName));
            }
        }

        public boolean isDisguisedBlock(Block block) {
            for (DisguisedPlayer dp : this.players.values()) {
                if (dp.lastBlock.equals(block)) {
                    return true;
                }
            }
            return false;
        }

        public Player undisguiseIfDisguised(Block block) {
            for (DisguisedPlayer dp : this.players.values()) {
                if (dp.lastBlock.equals(block)) {
                    Player pFound = Bukkit.getPlayer(dp.playerName);

                    this.undisguise(pFound);

                    return pFound;
                }
            }
            return null;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        disguiseManager.hideDisguisedPlayersFrom(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        disguiseManager.undisguise(e.getPlayer());
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        if (!(e.getPlayer().getWorld().getName().equals(name))) return;
        disguiseManager.undisguise(e.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        try {
            if (disguiseManager.isDisguisedBlock(e.getClickedBlock())) {
                Player p = Bukkit.getPlayer(disguiseManager.undisguiseIfDisguised(e.getClickedBlock()).getName());
                p.sendMessage(ChatColor.RED + "You were revealed!");
                e.getPlayer().sendMessage(ChatColor.RED + "You revealed " + p.getName() + "!");
            }
        } catch (NullPointerException ex) {
        }

        try {
            if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
            if (e.getPlayer().getItemInHand().getType().equals(Material.EMERALD)) {
                Player p = e.getPlayer();
                Block b = e.getClickedBlock();
                if (b.getType() == Material.WOOD || b.getType() == Material.RAILS || b.getTypeId() == 36 ||
                        b.getType() == Material.LOG || b.getType() == Material.TORCH || b.getTypeId() == 102 ||
                        b.getType() == Material.LONG_GRASS || b.getType() == Material.RED_ROSE
                        || b.getType() == Material.YELLOW_FLOWER || b.getType() == Material.LADDER ||
                        b.getType() == Material.GRASS || b.getType() == Material.LEAVES || b.getType()
                        == Material.STATIONARY_WATER || b.getType() == Material.WATER) {
                    p.sendMessage(ChatColor.RED + "That block is not allowed!");
                    return;
                }
                disguiseManager.disguise(p, p.getTargetBlock(null, 10).getType(), p.getTargetBlock(null, 10).getData());
                p.sendMessage(ChatColor.GREEN + "Disguised as a " + b.getType().toString().toLowerCase().replace("_", " "));
                ItemStack i = new ItemStack(Material.EMERALD, 1);
                ItemMeta im = i.getItemMeta();
                im.setDisplayName(ChatColor.BLUE + "RIGHT CLICK A BLOCK TO DISGUISE AS IT!");
                i.setItemMeta(im);
                e.getPlayer().getInventory().removeItem(i);
                e.getPlayer().updateInventory();
            }
        } catch (NullPointerException ex) {

        }
    }

    */
}
