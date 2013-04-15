package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.BattleHandler;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Kapha extends BattleMap implements MapInterface, Listener {

  OresomeBattlesMaps plugin;
	public Kapha(OresomeBattlesMaps pl) {
		super(pl);
		plugin = pl;
	}

	// Spawn lists. (Don't change!)
	public ArrayList<Location> redSpawns = new ArrayList<Location>();
	public ArrayList<Location> blueSpawns = new ArrayList<Location>();
	public ArrayList<Location> FFASpawns = new ArrayList<Location>();

	// Map details
	String name = "Kapha District";
	String fullName = "kapha";
	String creators = "kalysar, AlphaMinecraft91, _Moist and dutchy336";
	//Map download link: N/A
	@EventHandler(priority = EventPriority.NORMAL)
	public void readyMap(ReadyMapsEvent event) {
		addVotes(name);
		clearSpawns();
		readyTDMSpawns();
		readyFFASpawns();
		addCreators(name, creators); 
		setFullName(name, fullName);
	}

	public void readyTDMSpawns() {
		World w = Bukkit.getServer().getWorld(name);
		Location redSpawn = new Location(w, 33, 2, 25);
		Location blueSpawn = new Location(w, 73, 2, -14);

		redSpawns.add(redSpawn);

		blueSpawns.add(blueSpawn);
		setRedSpawns(name, redSpawns);
		setBlueSpawns(name, blueSpawns);
	}

	public void readyFFASpawns() {

		World w = Bukkit.getServer().getWorld(name);

		Location redSpawn = new Location(w, 33, 2, 25);
		Location blueSpawn = new Location(w, 73, 2, -14);

		FFASpawns.add(redSpawn);
		FFASpawns.add(blueSpawn);
		FFASpawns.add(new Location(w, 33, 2, -13));
		FFASpawns.add(new Location(w, 73, 2, 25));
		FFASpawns.add(new Location(w, 53, 3, 47));
		FFASpawns.add(new Location(w, 17, 2, 5));
		FFASpawns.add(new Location(w, 53, 3, -36));
		FFASpawns.add(new Location(w, 53, 11, 5));
		setFFASpawns(name, FFASpawns);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void applyInventory(InventoryEvent event) {

		String par = event.getMessage();
		Player p = event.getPlayer();
		Inventory i = p.getInventory();
		if (par.equalsIgnoreCase(name)) {
			clearInv(p);

			ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
			ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
			ItemStack BOW = new ItemStack(Material.BOW, 1);
			ItemStack ARROWS = new ItemStack(Material.ARROW, 16);
			ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
			ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
			ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
			ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
			ItemStack IRON_PICK = new ItemStack(Material.IRON_PICKAXE, 1);
			ItemStack IRON_AXE = new ItemStack(Material.IRON_AXE, 1);
			IRON_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 4);

			p.getInventory().setBoots(IRON_BOOTS);
			p.getInventory().setLeggings(IRON_PANTS);
			p.getInventory().setChestplate(IRON_CHESTPLATE);
			p.getInventory().setHelmet(IRON_HELMET);

			i.setItem(0, IRON_SWORD);
			i.setItem(1, BOW);
			i.setItem(2, IRON_PICK);
			i.setItem(3, IRON_AXE);
			i.setItem(4, STEAK);
			i.setItem(5, HEALTH_POTION);
			i.setItem(6, ARROWS);

		}
	}

	public void clearSpawns() {
		redSpawns.clear();
		blueSpawns.clear();
		FFASpawns.clear();
	}

	// Region. (Top corner block and bottom corner block.
	// Top left corner.
	public int x1 = 9;
	public int y1 = 44;
	public int z1 = 16;

	//Bottom right corner.
	public int x2 = -192;
	public int y2 = 97;
	public int z2 = -237;

	// Getting the region
	public boolean contains(Location loc, int x1, int x2, int y1,
			int y2, int z1, int z2) {
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
	@EventHandler
	public void on36Interact(PlayerInteractEvent event) {
		if (BattleHandler.activeArena.equals(name)) {
			Player player = event.getPlayer();
			if(event.getClickedBlock() instanceof Block){
				if(event.getClickedBlock().getType() == Material.DRAGON_EGG){
					Block b = event.getClickedBlock();
					b.setType(Material.AIR);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*20, 2));
				}
			}
		}
	}
	@EventHandler
	public void onFireBow(EntityShootBowEvent event) {
		if (BattleHandler.activeArena.equals(name)) {

			if (event.getEntityType() == EntityType.PLAYER) {

				Player player = (Player) event.getEntity();
				if (player.getInventory().contains(Material.ARROW)) {
					event.setCancelled(true);
					player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
					player.launchProjectile(EnderPearl.class).setVelocity(event.getProjectile().getVelocity());
				} else {
					event.setCancelled(true);
				}
			}
		}

	}
}
