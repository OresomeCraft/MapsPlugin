package com.oresomecraft.BattleMaps.classes;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.Utility;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Nuketown extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Nuketown(OresomeBattlesMaps pl) {
	super(pl);
	plugin = pl;
    }

    // Spawn locations.
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    String name = "nuketown";
    String fullName = "NukeTown";
    String creators = "Htgan, proportion and reub_youtube";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};
    //Map download link: N/A

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
	addMap(name);
	readyTDMSpawns();
	readyFFASpawns();
	addCreators(name, creators); 
	setFullName(name, fullName);
	setGamemodes(name, modes);
    }

    public void readyTDMSpawns() {
	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 1, 7, 41, 0, 0);
	Location blueSpawn = new Location(w, 2, 7, 154, -179, 0);

	redSpawns.add(redSpawn);
	blueSpawns.add(blueSpawn);
	redSpawns.add(new Location(w, -19, 7, 89, -90, 0));
	blueSpawns.add(new Location(w, 25, 11, 137, 89, 0));
	redSpawns.add(new Location(w, -19, 16, 61, -54, 0));
	blueSpawns.add(new Location(w, 4, 7, 84, 12, 0));
	redSpawns.add(new Location(w, 24, 7, 97, 114, 0));
	blueSpawns.add(new Location(w, -50, 7, 138, -65, 0));
	redSpawns.add(new Location(w, -29, 7, 143, -89, 0));
	blueSpawns.add(new Location(w, -5, 8.5, 141, -1, 0));
	redSpawns.add(new Location(w, 2, 16, 168, 179, 0));
	blueSpawns.add(new Location(w, -27, 7, 113, -83, 0));
	redSpawns.add(new Location(w, 23, 7, 60, 95, 0));
	blueSpawns.add(new Location(w, 21, 17, 91, 89, 0));
	redSpawns.add(new Location(w, -40, 7, 45, 40, 0));
	blueSpawns.add(new Location(w, 5, 12, 131, 178, 0));
	blueSpawns.add(new Location(w, -22, 12, 91, -110, 0));
	redSpawns.add(new Location(w, 54, 7, 128, 111, 0));
	blueSpawns.add(new Location(w, -2, 11, 156, 179, 0));
	redSpawns.add(new Location(w, 21, 11, 115, 42, 0));
	blueSpawns.add(new Location(w, 20, 16.5, 138, 119, 0));

	setRedSpawns(name, redSpawns);
	setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {
	World w = Bukkit.getServer().getWorld(name);

	Location redSpawn = new Location(w, 1, 7, 41, 0, 0);
	Location blueSpawn = new Location(w, 2, 7, 154, -179, 0);

	FFASpawns.add(redSpawn);
	FFASpawns.add(blueSpawn);
	FFASpawns.add(new Location(w, -19, 7, 89, -90, 0));
	FFASpawns.add(new Location(w, 25, 11, 137, 89, 0));
	FFASpawns.add(new Location(w, -19, 16, 61, -54, 0));
	FFASpawns.add(new Location(w, 4, 7, 84, 12, 0));
	FFASpawns.add(new Location(w, 24, 7, 97, 114, 0));
	FFASpawns.add(new Location(w, -50, 7, 138, -65, 0));
	FFASpawns.add(new Location(w, -29, 7, 143, -89, 0));
	FFASpawns.add(new Location(w, -5, 8.5, 141, -1, 0));
	FFASpawns.add(new Location(w, 2, 16, 168, 179, 0));
	FFASpawns.add(new Location(w, -27, 7, 113, -83, 0));
	FFASpawns.add(new Location(w, 23, 7, 60, 95, 0));
	FFASpawns.add(new Location(w, 21, 17, 91, 89, 0));
	FFASpawns.add(new Location(w, -40, 7, 45, 40, 0));
	FFASpawns.add(new Location(w, 5, 12, 131, 178, 0));
	FFASpawns.add(new Location(w, -22, 12, 91, -110, 0));
	FFASpawns.add(new Location(w, 54, 7, 128, 111, 0));
	FFASpawns.add(new Location(w, -2, 11, 156, 179, 0));
	FFASpawns.add(new Location(w, 21, 11, 115, 42, 0));
	FFASpawns.add(new Location(w, 20, 16.5, 138, 119, 0));

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
	    ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
	    ItemStack BOW = new ItemStack(Material.BOW, 1);
	    ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
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
	    i.setItem(1, BOW);
	    i.setItem(2, ARROWS);
	    i.setItem(3, STEAK);
	    i.setItem(4, HEALTH_POTION);

	}
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void clearSpawns(ClearSpawnsEvent event) {
	redSpawns.clear();
	blueSpawns.clear();
	FFASpawns.clear();
    }

    //Region border.

    public int x1 = -60;
    public int y1 = 3;
    public int z1 = 1;
    public int x2 = 64;
    public int y2 = 56;
    public int z2 = 194;

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

    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {

	Block b = event.getBlock();
	Location loc = b.getLocation();

	if (loc.getWorld().getName().equals(name)) {

	    event.setCancelled(true);
	}

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockPlaceEvent event) {

	Block b = event.getBlock();
	Location loc = b.getLocation();

	if (loc.getWorld().getName().equals(name)) {

	    event.setCancelled(true);

	}

    }

    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
	Entity arrow = event.getEntity();
	World world = Bukkit.getWorld(name);
	if (Utility.getArena() == name) {
	    if (arrow instanceof Arrow) {
		world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);
	    }
	}
    }

    @EventHandler
    public void arrowCollide(ProjectileHitEvent e) {
	if (e.getEntity().getWorld().equals(name)) {
	    if (e.getEntity() instanceof Arrow) {
		Arrow a = (Arrow) e.getEntity();
		//Location loc1 = a.getLocation();
		List<Entity> nearby = a.getNearbyEntities(1, 1, 1);
		for (Entity ent : nearby) {
		    if (ent instanceof Player) {
			//Player p = (Player) ent;
			ent.playEffect(EntityEffect.WOLF_SMOKE);
		    }
		}
	    }
	}
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
	if (e.getPlayer().getWorld().equals(name)) {
	    Player p = e.getPlayer();
	    ItemStack iih = p.getItemInHand();
	    Material m = iih.getType();
	    if (m == Material.BOW) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR
			|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		    p.getWorld().playEffect(p.getLocation(),
			    Effect.MOBSPAWNER_FLAMES, 0, 50);
		}
	    }
	}
    }

}
