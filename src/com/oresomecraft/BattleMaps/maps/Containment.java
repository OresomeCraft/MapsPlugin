package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Containment extends BattleMap implements IBattleMap, Listener {

    public Containment() {
        super.initiate(this, name, fullName, creators, modes);
        setAllowBuild(false);
        disableDrops(new Material[]{Material.TRIPWIRE_HOOK});
    }

    String name = "containment";
    String fullName = "Containment Breach";
    String creators = "R3creat3, MiCkEyMiCE, LanderA and _Moist";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        //Main spawn
        redSpawns.add(new Location(w, 9, 65, 5));

        //Sector D
        redSpawns.add(new Location(w, 9, 62, 16));
        redSpawns.add(new Location(w, 7, 47, 27));
        redSpawns.add(new Location(w, 8, 39, 19));
        blueSpawns.add(new Location(w, 31, 54, -58));
        blueSpawns.add(new Location(w, 9, 59, -17));

        //Sector C
        redSpawns.add(new Location(w, 10, 65, -17));
        redSpawns.add(new Location(w, -4, 55, -2));
        blueSpawns.add(new Location(w, 0, 65, -20));
        blueSpawns.add(new Location(w, 11, 71, -31));
        blueSpawns.add(new Location(w, 0, 73, -15));

        //Sector B  (small sector)
        redSpawns.add(new Location(w, 31, 62, 6));
        redSpawns.add(new Location(w, 44, 53, -5));
        blueSpawns.add(new Location(w, 24, 39, -24));

        //Sector A
        redSpawns.add(new Location(w, -4, 65, 19));
        redSpawns.add(new Location(w, -30, 73, 5));
        blueSpawns.add(new Location(w, -48, 73, -14));
        blueSpawns.add(new Location(w, -17, 73, -15));
        blueSpawns.add(new Location(w, -27, 39, -5));
    }

    public void readyFFASpawns() {
        //Main spawn
        FFASpawns.add(new Location(w, 9, 65, 5));

        //Sector D
        FFASpawns.add(new Location(w, 9, 62, 16));
        FFASpawns.add(new Location(w, 7, 47, 27));
        FFASpawns.add(new Location(w, 8, 39, 19));
        FFASpawns.add(new Location(w, 31, 54, -58));
        FFASpawns.add(new Location(w, 9, 59, -17));

        //Sector C
        FFASpawns.add(new Location(w, 10, 65, -17));
        FFASpawns.add(new Location(w, -4, 55, -2));
        FFASpawns.add(new Location(w, 0, 65, -20));
        FFASpawns.add(new Location(w, 11, 71, -31));
        FFASpawns.add(new Location(w, 0, 73, -15));

        //Sector B  (small sector)
        FFASpawns.add(new Location(w, 31, 62, 6));
        FFASpawns.add(new Location(w, 44, 53, -5));
        FFASpawns.add(new Location(w, 24, 39, -24));

        //Sector A
        FFASpawns.add(new Location(w, -4, 65, 19));
        FFASpawns.add(new Location(w, -30, 73, 5));
        FFASpawns.add(new Location(w, -48, 73, -14));
        FFASpawns.add(new Location(w, -17, 73, -15));
        FFASpawns.add(new Location(w, -27, 39, -5));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 3);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 20*20, 2));

        i.setItem(0, IRON_SWORD);
        i.setItem(1, STEAK);
        i.setItem(2, HEALTH_POTION);
        i.setItem(28, ARROWS);


    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 0;
    public int y1 = 1;
    public int z1 = 0;

    //Bottom right corner.
    public int x2 = 128;
    public int y2 = 255;
    public int z2 = 128;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equals(name)) {
            if (contains(event.getPlayer().getLocation(), -2, -16, 55, 69, 0, -11)) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 3 * 20, 0));
            }

        }
    }


    @EventHandler
    public void onKey(PlayerInteractEvent event) {
        if(!event.getPlayer().getWorld().equals(name)) return;
        Player p = event.getPlayer();
        Action a = event.getAction();
        Inventory i = p.getInventory();
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        Block b = event.getClickedBlock();
        if (!(b.getType() == Material.WALL_SIGN) && !(b.getType() == Material.STONE_BUTTON)) return;
        if (b.getType().equals(Material.WALL_SIGN)) {
            Sign s = (Sign) b.getState();
            if (s.getLine(0).contains("[Security]")) {
                i.remove(Material.TRIPWIRE_HOOK);
                if (s.getLine(2).contains("Level 1")) {
                    ItemStack KEY = new ItemStack(Material.TRIPWIRE_HOOK);
                    ItemMeta km = KEY.getItemMeta();
                    km.setDisplayName(ChatColor.BLUE + "Level 1 security key");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.BLUE + "Opens level 1 security doors");
                    km.setLore(lore);
                    KEY.setItemMeta(km);
                    i.addItem(KEY);
                } else if (s.getLine(2).contains("Level 2")) {
                    ItemStack KEY = new ItemStack(Material.TRIPWIRE_HOOK);
                    ItemMeta km = KEY.getItemMeta();
                    km.setDisplayName(ChatColor.GREEN + "Level 2 security key");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.GREEN + "Opens level 2 and lower security doors");
                    km.setLore(lore);
                    KEY.setItemMeta(km);
                    i.addItem(KEY);
                } else if (s.getLine(2).contains("Level 3")) {
                    ItemStack KEY = new ItemStack(Material.TRIPWIRE_HOOK);
                    ItemMeta km = KEY.getItemMeta();
                    km.setDisplayName(ChatColor.YELLOW + "Level 3 security key");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.YELLOW + "Opens level 3 and lower security doors");
                    km.setLore(lore);
                    KEY.setItemMeta(km);
                    i.addItem(KEY);
                } else if (s.getLine(2).contains("Level 4")) {
                    ItemStack KEY = new ItemStack(Material.TRIPWIRE_HOOK);
                    ItemMeta km = KEY.getItemMeta();
                    km.setDisplayName(ChatColor.RED + "Level 4 security key");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.RED + "Opens level 4 or lower security doors");
                    km.setLore(lore);
                    KEY.setItemMeta(km);
                    i.addItem(KEY);
                } else if (s.getLine(2).contains("Level 5")) {
                    ItemStack KEY = new ItemStack(Material.TRIPWIRE_HOOK);
                    ItemMeta km = KEY.getItemMeta();
                    km.setDisplayName(ChatColor.DARK_RED + "Level 5 security key");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.RED + "Highest level access key");
                    lore.add(ChatColor.RED + "Opens all doors");
                    km.setLore(lore);
                    KEY.setItemMeta(km);
                    i.addItem(KEY);
                }
                p.updateInventory();
            }
        }
        if (b.getType().equals(Material.STONE_BUTTON)) {
            if (b.getRelative(BlockFace.UP).getType().equals(Material.WALL_SIGN)) {
                Block rel = b.getRelative(BlockFace.UP);
                Sign s = (Sign) rel.getState();
                if (s.getLine(0).contains("[Clearance]")) {
                    event.setCancelled(true);
                    if (s.getLine(2).contains("Level 1")) {
                        if (getClearance(p.getItemInHand()) >= 1) {
                            event.setCancelled(false);
                        } else {
                            p.sendMessage(ChatColor.RED + "You need a security key with a higher clearance to open this door");
                        }
                    }
                    if (s.getLine(2).contains("Level 2")) {
                        if (getClearance(p.getItemInHand()) >= 2) {
                            event.setCancelled(false);
                        } else {
                            p.sendMessage(ChatColor.RED + "You need a security key with a higher clearance to open this door");
                        }
                    }
                    if (s.getLine(2).contains("Level 3")) {
                        if (getClearance(p.getItemInHand()) >= 3) {
                            event.setCancelled(false);
                        } else {
                            p.sendMessage(ChatColor.RED + "You need a security key with a higher clearance to open this door");
                        }
                    }
                    if (s.getLine(2).contains("Level 4")) {
                        if (getClearance(p.getItemInHand()) >= 4) {
                            event.setCancelled(false);
                        } else {
                            p.sendMessage(ChatColor.RED + "You need a security key with a higher clearance to open this door");
                        }
                    }
                    if (s.getLine(2).contains("Level 5")) {
                        if (getClearance(p.getItemInHand()) >= 5) {
                            event.setCancelled(false);
                        } else {
                            p.sendMessage(ChatColor.RED + "You need a security key with a higher clearance to open this door");
                        }
                    }
                }
            }
        }
    }


    public int getClearance(ItemStack i) {
        int clearance = 0;
        if(i.getType() != Material.TRIPWIRE_HOOK) return 0;
        ItemMeta im = i.getItemMeta();
        if (im.getDisplayName().equals(ChatColor.BLUE + "Level 1 security key")) clearance = 1;
        if (im.getDisplayName().equals(ChatColor.GREEN + "Level 2 security key")) clearance = 2;
        if (im.getDisplayName().equals(ChatColor.YELLOW + "Level 3 security key")) clearance = 3;
        if (im.getDisplayName().equals(ChatColor.RED + "Level 4 security key")) clearance = 4;
        if (im.getDisplayName().equals(ChatColor.DARK_RED + "Level 5 security key")) clearance = 5;
        return clearance;
    }
}
