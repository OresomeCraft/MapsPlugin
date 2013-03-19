package com.maps.classes;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.maps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.InventoryEvent;
import com.oresomecraft.OresomeBattles.OresomeBattles;
import com.oresomecraft.OresomeBattles.ReadyMapsEvent;


public class Arctic implements Listener {
    
    OresomeBattlesMaps plugin;
    OresomeBattles Battles;
    public Arctic(OresomeBattlesMaps pl) {
	plugin = pl;
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
	Battles = (OresomeBattles) Bukkit.getServer().getPluginManager().getPlugin("OresomeBattles");
    }

    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    String name = "arctic";
    String fullName = "Arctic";
    String creators = "Dant35tra5t";
    //Map download link: N/A
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
	Battles.addVotes(name);
	clearSpawns();
	readyTDMSpawns();
	readyFFASpawns();
	Battles.addCreators(name, creators); 
	Battles.setFullName(name, fullName);
    }
    
    public void readyTDMSpawns() {
	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
	Location blueSpawn = new Location(w, 761, 128, -72, 51, 0);

	redSpawns.add(redSpawn);
	blueSpawns.add(blueSpawn);
	redSpawns.add(new Location(w, 795, 155, -28, -172, 0));
	blueSpawns.add(new Location(w, 822, 133, -67, 87, 0));
	redSpawns.add(new Location(w, 813, 128, -41, -93, 0));
	blueSpawns.add(new Location(w, 773, 121, -110, -58, 0));
	redSpawns.add(new Location(w, 779, 141, -110, -44, 0));
	blueSpawns.add(new Location(w, 808, 159, -144, -2, 0));
	redSpawns.add(new Location(w, 820, 129, -68, 129, 0));
	blueSpawns.add(new Location(w, 788, 128, -81, -124, 0));
	redSpawns.add(new Location(w, 784, 182, -85, -113, 0));
	blueSpawns.add(new Location(w, 761, 132, -133, -27, 0));
	redSpawns.add(new Location(w, 820, 149, -88, 2, 0));
	blueSpawns.add(new Location(w, 820, 149, -88, 2, 0));
	redSpawns.add(new Location(w, 779, 130, -87, -87, 0));
	blueSpawns.add(new Location(w, 832, 133, -67, 133, 0));
	redSpawns.add(new Location(w, 798, 135, -72, -107, 0));
	blueSpawns.add(new Location(w, 790, 128, -78, 177, 0));
	redSpawns.add(new Location(w, 830, 152, -142, 19, 0));
	blueSpawns.add(new Location(w, 855, 130, -50, 136, 0));
	redSpawns.add(new Location(w, 798, 148, -74, -134, 0));
	blueSpawns.add(new Location(w, 789, 123, -94, 133, 0));

	Battles.setRedSpawns(name, redSpawns);
	Battles.setBlueSpawns(name, blueSpawns);
    }
    
    public void readyFFASpawns() {
	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
	Location blueSpawn = new Location(w, 761, 128, -72, 51, 0);

	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, 795, 155, -28, -172, 0));
	FFASpawns.add(new Location(w, 822, 133, -67, 87, 0));
	FFASpawns.add(new Location(w, 813, 128, -41, -93, 0));
	FFASpawns.add(new Location(w, 773, 121, -110, -58, 0));
	FFASpawns.add(new Location(w, 779, 141, -110, -44, 0));
	FFASpawns.add(new Location(w, 808, 159, -144, -2, 0));
	FFASpawns.add(new Location(w, 820, 129, -68, 129, 0));
	FFASpawns.add(new Location(w, 788, 128, -81, -124, 0));
	FFASpawns.add(new Location(w, 784, 182, -85, -113, 0));
	FFASpawns.add(new Location(w, 761, 132, -133, -27, 0));
	FFASpawns.add(new Location(w, 820, 149, -88, 2, 0));
	FFASpawns.add(new Location(w, 820, 149, -88, 2, 0));
	FFASpawns.add(new Location(w, 779, 130, -87, -87, 0));
	FFASpawns.add(new Location(w, 832, 133, -67, 133, 0));
	FFASpawns.add(new Location(w, 798, 135, -72, -107, 0));
	FFASpawns.add(new Location(w, 790, 128, -78, 177, 0));
	FFASpawns.add(new Location(w, 830, 152, -142, 19, 0));
	FFASpawns.add(new Location(w, 855, 130, -50, 136, 0));
	FFASpawns.add(new Location(w, 798, 148, -74, -134, 0));
	FFASpawns.add(new Location(w, 789, 123, -94, 133, 0));

	Battles.setFFASpawns(name, FFASpawns);
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
	String par = event.getMessage();
	Player p = event.getPlayer();
	Inventory i = p.getInventory();
	if (par.equalsIgnoreCase(name)) {
	    Battles.utility.clearInv(p);

	    ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
	    ItemStack BOW = new ItemStack(Material.BOW, 1);
	    ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
	    ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
	    ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
	    ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
	    ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
	    ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
	    ItemStack SNOWBALL = new ItemStack(Material.SNOW_BALL, 4);
	    ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);
	    ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
	    ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
	    
	    ItemMeta stone_hoe = STONE_HOE.getItemMeta();
	    stone_hoe.setDisplayName(ChatColor.BLUE + "Ice hook");
	    STONE_HOE.setItemMeta(stone_hoe);

	    p.getInventory().setBoots(IRON_BOOTS);
	    p.getInventory().setLeggings(IRON_PANTS);
	    p.getInventory().setChestplate(IRON_CHESTPLATE);
	    p.getInventory().setHelmet(IRON_HELMET);
	    i.setItem(0, IRON_SWORD);
	    i.setItem(1, BOW);
	    i.setItem(2, STONE_HOE);
	    i.setItem(3, SNOWBALL);
	    i.setItem(4, EXP);
	    i.setItem(5, HEALTH_POTION);
	    i.setItem(6, STEAK);
	    i.setItem(7, ARROWS);
	    p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_FALL, 3);

	}
    }
    
    public void clearSpawns() {
	redSpawns.clear();
	blueSpawns.clear();
	FFASpawns.clear();
    }
    

    public int x1 = 715;
    public int y1 = 107;
    public int z1 = -179;
    public int x2 = 903;
    public int y2 = 203;
    public int z2 = 10;
    
    // getting the region
    public static boolean contains(Location loc, int x1, int x2, int y1, int y2, int z1, int z2) {
	int bottomCornerX = x1 < x2 ? x1 : x2;
	int bottomCornerZ = z1 < z2 ? z1 : z2;
	int topCornerX = x1 > x2 ? x1 : x2;
	int topCornerZ = z1 > z2 ? z1 : z2;
	int bottomCornerY = y1 < y2 ? y1 : y2;
	int topCornerY = y1 > y2 ? y1 : y2;
	if (loc.getX() >= bottomCornerX && loc.getX() <= topCornerX) {
	    if (loc.getZ() >= bottomCornerZ && loc.getZ() <= topCornerZ) {
		if (loc.getY() >= bottomCornerY && loc.getY() <= topCornerY) {
		    return true;
		}
	    }
	}
	return false;
    }

    
    @EventHandler(priority = EventPriority.NORMAL)
    public void icePick(PlayerInteractEvent event) {
	Player p = event.getPlayer();
	ItemStack i = p.getItemInHand();
	Material mat = i.getType();
	Action a = event.getAction();
	Location loc = p.getLocation();

	ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);
	ItemMeta stone_hoe = STONE_HOE.getItemMeta();
	stone_hoe.setDisplayName(ChatColor.BLUE + "Ice hook");
	STONE_HOE.setItemMeta(stone_hoe);
	World world = Bukkit.getWorld(name);

	if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

	    if (mat == Material.STONE_HOE) {

		if (a == Action.LEFT_CLICK_BLOCK) {

		    BlockFace f = event.getBlockFace();
		    Block b = event.getClickedBlock();
		    Material Bmat = b.getType();

		    if (Bmat == Material.STONE || Bmat == Material.ICE) {

			if (f != BlockFace.UP && f != BlockFace.DOWN) {

			    p.setVelocity(new Vector(0, 1, 0));
			    p.setFallDistance(0);
			    world.playEffect(b.getLocation(), Effect.STEP_SOUND, 79);
			    world.playEffect(b.getLocation(), Effect.STEP_SOUND, 79);
			    world.playEffect(b.getLocation(), Effect.STEP_SOUND, 79);
			    world.playEffect(b.getLocation(), Effect.STEP_SOUND, 79);

			}

		    }

		}
	    }

	}

    }
 
      
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {

	Block b = event.getBlock();
	Location loc = b.getLocation();

	if (loc.getWorld().getName().equals(name)) {

		event.setCancelled(true);
	    
	}

    }

}
