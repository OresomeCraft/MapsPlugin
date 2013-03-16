package com.maps;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.OresomeBattles.InventoryEvent;
import com.oresomecraft.OresomeBattles.OresomeBattles;
import com.oresomecraft.OresomeBattles.ReadyMapsEvent;

public class HypnoMain implements Listener {

    OresomeBattlesMaps plugin;
    OresomeBattles Battles;
    public HypnoMain(OresomeBattlesMaps pl) {
	plugin = pl;
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
	Battles = (OresomeBattles) Bukkit.getServer().getPluginManager().getPlugin("OresomeBattles");
    }
    
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();
    
    String name = "hypno";
    String fullName = "Hypnosis";
    String creators = "zachoz, pegabeavercorn, DragonDrew and kevlar_miner";
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

	Location redSpawn = new Location(w, -783, 91, -1331, 159, 0);
	Location blueSpawn = new Location(w, -814, 83, -1389, -18, 0);

	redSpawns.add(redSpawn);
	blueSpawns.add(blueSpawn);
	redSpawns.add(new Location(w, -826, 72, -1439, -44, 0));
	blueSpawns.add(new Location(w, -781, 72, -1427, -2, 0));
	redSpawns.add(new Location(w, -817, 62, -1436, -43, 0));
	blueSpawns.add(new Location(w, -819, 96, -1413, -56, 0));
	redSpawns.add(new Location(w, -794, 98, -1380, -76, 0));
	blueSpawns.add(new Location(w, -801, 68, -1389, -44, 0));
	redSpawns.add(new Location(w, -761, 63, -1401, 10, 0));
	blueSpawns.add(new Location(w, -829, 110, -1328, -115, 0));
	redSpawns.add(new Location(w, -802, 69, -1298, -163, 0));
	blueSpawns.add(new Location(w, -810, 96, -1350, -83, 0));
	redSpawns.add(new Location(w, -760, 105, -1345, 149, 0));
	blueSpawns.add(new Location(w, -748, 81, -1344, 136, 0));
	redSpawns.add(new Location(w, -739, 64, -1371, 97, 0));
	blueSpawns.add(new Location(w, -791, 83, -1347, -145, 0));
	redSpawns.add(new Location(w, -767, 108, -1337, 117, 0));
	blueSpawns.add(new Location(w, -740, 93, -1362, 47, 0));
	redSpawns.add(new Location(w, -760, 68, -1398, 16, 0));
	blueSpawns.add(new Location(w, -800, 87, -1384, -103, 0));
	redSpawns.add(new Location(w, -781, 69, -1424, -18, 0));
	blueSpawns.add(new Location(w, -746, 73, -1358, 123, 0));

	Battles.setRedSpawns(name, redSpawns);
	Battles.setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {
	World w = Bukkit.getServer().getWorld(name);
	Location redSpawn = new Location(w, -783, 91, -1331, 159, 0);
	Location blueSpawn = new Location(w, -814, 83, -1389, -18, 0);

	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, -826, 72, -1439, -44, 0));
	FFASpawns.add(new Location(w, -781, 72, -1427, -2, 0));
	FFASpawns.add(new Location(w, -817, 62, -1436, -43, 0));
	FFASpawns.add(new Location(w, -819, 96, -1413, -56, 0));
	FFASpawns.add(new Location(w, -794, 98, -1380, -76, 0));
	FFASpawns.add(new Location(w, -801, 68, -1389, -44, 0));
	FFASpawns.add(new Location(w, -761, 63, -1401, 10, 0));
	FFASpawns.add(new Location(w, -829, 110, -1328, -115, 0));
	FFASpawns.add(new Location(w, -802, 69, -1298, -163, 0));
	FFASpawns.add(new Location(w, -810, 96, -1350, -83, 0));
	FFASpawns.add(new Location(w, -760, 105, -1345, 149, 0));
	FFASpawns.add(new Location(w, -748, 81, -1344, 136, 0));
	FFASpawns.add(new Location(w, -739, 64, -1371, 97, 0));
	FFASpawns.add(new Location(w, -791, 83, -1347, -145, 0));
	FFASpawns.add(new Location(w, -767, 108, -1337, 117, 0));
	FFASpawns.add(new Location(w, -740, 93, -1362, 47, 0));
	FFASpawns.add(new Location(w, -760, 68, -1398, 16, 0));
	FFASpawns.add(new Location(w, -800, 87, -1384, -103, 0));
	FFASpawns.add(new Location(w, -781, 69, -1424, -18, 0));
	FFASpawns.add(new Location(w, -746, 73, -1358, 123, 0));

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
	    ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
	    ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE,  1);
	    ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
	    ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
	    ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
	    ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
	    ItemStack BOW = new ItemStack(Material.BOW, 1);
	    ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
	    ItemStack STONE_SHOVEL = new ItemStack(Material.STONE_SPADE, 1);
	    ItemStack IRON_PICK = new ItemStack(Material.IRON_PICKAXE, 1);
	    ItemStack EMERALD = new ItemStack(Material.EMERALD, 1);
	    ItemStack EGG_HYPNO = new ItemStack(Material.EGG, 1);

	    ItemMeta egg_hypno = EGG_HYPNO.getItemMeta();
	    egg_hypno.setDisplayName(ChatColor.BLUE + "Flash bang grenade");
	    EGG_HYPNO.setItemMeta(egg_hypno);

	    ItemMeta emerald = EMERALD.getItemMeta();
	    emerald.setDisplayName(ChatColor.BLUE + "Nausea Stone");
	    EMERALD.setItemMeta(emerald);
		
	    p.getInventory().setBoots(IRON_BOOTS);
	    p.getInventory().setLeggings(IRON_PANTS);
	    p.getInventory().setChestplate(IRON_CHESTPLATE);
	    p.getInventory().setHelmet(IRON_HELMET);
	    i.setItem(0, IRON_SWORD);
	    i.setItem(1, BOW);
	    i.setItem(2, EMERALD);
	    i.setItem(3, EGG_HYPNO);
	    i.setItem(4, IRON_PICK);
	    i.setItem(5, STONE_SHOVEL);
	    i.setItem(6, STEAK);
	    i.setItem(7, HEALTH_POTION);
	    i.setItem(8, ARROWS);

	}
    }

    public void clearSpawns() {
	redSpawns.clear();
	blueSpawns.clear();
	FFASpawns.clear();
    }
    
    public int x1 = -831;
    public int y1 = 62;
    public int z1 = -1452;
    public int x2 = -726;
    public int y2 = 118;
    public int z2 = -1275;

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
    public void protection(BlockBreakEvent event) {
	Block b = event.getBlock();
	int mat = b.getTypeId();
	Location loc = b.getLocation();
	if (loc.getWorld().getName().equals(name)) {
	    if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

		if (mat == 43 || mat == 44 || mat == 35 || mat == 42
			|| mat == 49 || mat == 123 || mat == 69 || mat == 124) {

		    event.setCancelled(true);

		}
	    }

	}

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void hypnoGem(EntityDamageByEntityEvent event) {
	Entity damager = event.getDamager();
	Entity e = event.getEntity();
	Location loc = e.getLocation();

	if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

	    if (damager instanceof Player) {

		Player damagerP = (Player) damager;
		ItemStack weapon = damagerP.getItemInHand();
		Material mat = weapon.getType();

		if (e instanceof Player) {

		    Player p = (Player) e;

		    if (mat == Material.EMERALD) {

			PotionEffectType confuse = PotionEffectType.CONFUSION;
			PotionEffect confuseE = new PotionEffect(confuse, 400,
				1);
			p.addPotionEffect(confuseE);
		    }

		}

	    }
	}
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void hypnoBangGrenade(ProjectileHitEvent event) {
	Entity e = event.getEntity();
	Location loc = e.getLocation();
	World world = loc.getWorld();

	if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

	    if (e instanceof Egg) {

		world.playEffect(loc, Effect.ENDER_SIGNAL, 5);
		world.playSound(loc, Sound.EXPLODE, 10, 10);

		List<Entity> nearby = e.getNearbyEntities(3, 3, 3);

		int amount = nearby.size();
		int count = 0;
		int counter = 1;
		for (counter = 0; counter < amount; counter++) {

		    Entity near = nearby.get(count);

		    if (near instanceof Player) {

			Player p = (Player) near;

			PotionEffectType blind = PotionEffectType.BLINDNESS;
			PotionEffectType slow = PotionEffectType.SLOW;
			PotionEffectType flash = PotionEffectType.NIGHT_VISION;

			PotionEffect blindE = new PotionEffect(blind, 200, 2);
			PotionEffect slowE = new PotionEffect(slow, 200, 2);
			PotionEffect flashE = new PotionEffect(flash, 200, 2);

			p.addPotionEffect(blindE);
			p.addPotionEffect(slowE);
			p.addPotionEffect(flashE);

		    }
		    count++;

		}

	    }
	}
    }
    
    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
	Entity arrow = event.getEntity();
	World world = Bukkit.getWorld(name);
	if (Battles.activeArena.get(0).equals(name)) {
	    if (arrow instanceof Arrow) {
		world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 8);
	    }
	}

    }

}
