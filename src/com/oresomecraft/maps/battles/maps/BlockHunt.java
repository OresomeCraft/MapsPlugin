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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
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
    String fullName = "The Block Hunt";
    String creators = "Psystrom, 123Oblivious, microstevey, __R3, AnomalousRei, AnomalousDyna, _Trezo_, Tatik, Fliine and SecretSeriousity";
    Gamemode[] modes = {Gamemode.CTF, Gamemode.TDM};

    private DisguiseManager disguiseManager = new DisguiseManager(MapsPlugin.getInstance());

    @EventHandler
    public void battleStart(WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            disguiseManager.startTask();
        }
    }

    @EventHandler
    public void battleEnd(BattleEndEvent event) {
        disguiseManager.cancelTask();
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 35.5, 68, -11.5));
        blueSpawns.add(new Location(w, -96.5, 68, -28.));

        setCTFFlags(name, new Location(w, 11, 69, -13), new Location(w, -71, 69, -30));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 35.5, 68, -11.5));
        FFASpawns.add(new Location(w, -96.5, 68, -28.));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 4);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack BOOTS = new ItemStack(Material.DIAMOND_BOOTS);

        ItemStack EMERALD = new ItemStack(Material.EMERALD, 2);
        ItemMeta itemMeta = EMERALD.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BLUE + "RIGHT CLICK A BLOCK TO DISGUISE AS IT!");
        EMERALD.setItemMeta(itemMeta);

        BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);
        setColouredArmorAccordingToTeam(p);
        p.getInventory().setBoots(BOOTS);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, HEALTH);
        i.setItem(11, ARROWS);
        i.setItem(3, new ItemStack(Material.BREAD, 3));
        i.setItem(8, EMERALD);

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
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!(event.getPlayer().getWorld().getName().equals(name))) return;
        disguiseManager.hideDisguisedPlayersFrom(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!(event.getPlayer().getWorld().getName().equals(name))) return;
        disguiseManager.undisguise(event.getPlayer());
    }

    @EventHandler
    public void onWorldChange(PlayerTeleportEvent event) {
        if (!(event.getPlayer().getWorld().getName().equals(name))) return;
        if (event.getTo().getWorld().getName().equals(event.getFrom().getWorld().getName())) return;
        if (BattlePlayer.getBattlePlayer(event.getPlayer()).isSpectator())
            disguiseManager.undisguise(event.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!(event.getPlayer().getWorld().getName().equals(name))) return;
        try {
            if (disguiseManager.isDisguisedBlock(event.getClickedBlock()) || !BattlePlayer.getBattlePlayer(event.getPlayer()).isSpectator()) {
                Player player = Bukkit.getPlayer(disguiseManager.undisguiseIfDisguised(event.getClickedBlock()).getName());
                player.sendMessage(ChatColor.RED + "You were revealed!");
                event.getPlayer().sendMessage(ChatColor.RED + "You revealed " + player.getName() + "!");
            }
        } catch (NullPointerException ex) {

        }

        try {
            if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
            if (event.getPlayer().getItemInHand().getType() == Material.EMERALD) {
                Player player = event.getPlayer();
                Block block = event.getClickedBlock();
                if (block.getType() == Material.WOOD || block.getType() == Material.RAILS || block.getTypeId() == 36 ||
                        block.getType() == Material.LOG || block.getType() == Material.TORCH || block.getTypeId() == 102 ||
                        block.getType() == Material.LONG_GRASS || block.getType() == Material.RED_ROSE
                        || block.getType() == Material.YELLOW_FLOWER || block.getType() == Material.LADDER ||
                        block.getType() == Material.GRASS || block.getType() == Material.LEAVES || block.getType()
                         == Material.STATIONARY_WATER || block.getType() == Material.WATER) {
                    player.sendMessage(ChatColor.RED + "That block is not allowed!");
                    return;
                }
                disguiseManager.disguise(player, player.getTargetBlock(null, 10).getType(), player.getTargetBlock(null, 10).getData());
                player.sendMessage(ChatColor.GREEN + "Disguised as a " + block.getType().toString().toLowerCase().replace("_", " "));

                ItemStack EMERALD = new ItemStack(Material.EMERALD, 1);
                ItemMeta itemMeta = EMERALD.getItemMeta();
                itemMeta.setDisplayName(ChatColor.BLUE + "RIGHT CLICK A BLOCK TO DISGUISE AS IT!");
                EMERALD.setItemMeta(itemMeta);
                event.getPlayer().getInventory().removeItem(EMERALD);
                event.getPlayer().updateInventory();
            }
        } catch (NullPointerException ex) {

        }
    }
}
