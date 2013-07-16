package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import com.oresomecraft.BattleMaps.IBattleMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Spire extends BattleMap implements IBattleMap, Listener {

    OresomeBattlesMaps plugin;

    public Spire(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    String name = "spire";
    String fullName = "Spire";
    String creators = "zachoz and pegabeavercorn";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public int x1 = -1661;
    public int y1 = 67;
    public int z1 = -2392;
    public int x2 = -1499;
    public int y2 = 169;
    public int z2 = -2230;


    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) { // Internal - Do not change
        addMap(name);
        addCreators(name, creators);
        setFullName(name, fullName);
        setGamemodes(name, modes);
    }

    @EventHandler
    public void setSpawns(WorldLoadEvent event) { // Internal - Do not change
        if (event.getWorld().getName().equals(name)) {
            readyTDMSpawns();
            readyFFASpawns();
        }
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -1509, 75, -2310);
        Location blueSpawn = new Location(w, -1649, 75, -2310);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        blueSpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        redSpawns.add(new Location(w, -1580, 94, -2299, 97, 0));
        blueSpawns.add(new Location(w, -1581, 99, -2322, -87, 0));
        redSpawns.add(new Location(w, -1579, 74, -2234, 179, 0));
        blueSpawns.add(new Location(w, -1516, 74, -2348, 50, 0));
        redSpawns.add(new Location(w, -1520, 74, -2261, 132, 0));
        blueSpawns.add(new Location(w, -1633, 74, -2257, -132, 0));
        redSpawns.add(new Location(w, -1633, 74, -2362, -46, 0));
        blueSpawns.add(new Location(w, -1587, 84, -2302, 44, 0));
        redSpawns.add(new Location(w, -1573, 85, -2301, -41, 0));
        blueSpawns.add(new Location(w, -1573, 85, -2319, -128, 0));
        redSpawns.add(new Location(w, -1588, 85, -2316, 136, 0));
        blueSpawns.add(new Location(w, -1579, 74, -2386, 1, 0));
        redSpawns.add(new Location(w, -1567, 76, -2310, -49, 0));
        blueSpawns.add(new Location(w, -1590, 77, -2315, 118, 0));
        redSpawns.add(new Location(w, -1586, 77, -2301, 25, 0));
        blueSpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        redSpawns.add(new Location(w, -1580, 94, -2299, 97, 0));
        blueSpawns.add(new Location(w, -1581, 99, -2322, -87, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);

    }

    public void readyFFASpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, -1510, 74, -2311, -137, 0);
        Location blueSpawn = new Location(w, -1650, 74, -2311, 51, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        FFASpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        FFASpawns.add(new Location(w, -1580, 94, -2299, 97, 0));
        FFASpawns.add(new Location(w, -1581, 99, -2322, -87, 0));
        FFASpawns.add(new Location(w, -1579, 74, -2234, 179, 0));
        FFASpawns.add(new Location(w, -1516, 74, -2348, 50, 0));
        FFASpawns.add(new Location(w, -1520, 74, -2261, 132, 0));
        FFASpawns.add(new Location(w, -1633, 74, -2257, -132, 0));
        FFASpawns.add(new Location(w, -1633, 74, -2362, -46, 0));
        FFASpawns.add(new Location(w, -1587, 84, -2302, 44, 0));
        FFASpawns.add(new Location(w, -1573, 85, -2301, -41, 0));
        FFASpawns.add(new Location(w, -1573, 85, -2319, -128, 0));
        FFASpawns.add(new Location(w, -1588, 85, -2316, 136, 0));
        FFASpawns.add(new Location(w, -1579, 74, -2386, 1, 0));
        FFASpawns.add(new Location(w, -1567, 76, -2310, -49, 0));
        FFASpawns.add(new Location(w, -1590, 77, -2315, 118, 0));
        FFASpawns.add(new Location(w, -1586, 77, -2301, 25, 0));
        FFASpawns.add(new Location(w, -1578, 76, -2323, -152, 0));
        FFASpawns.add(new Location(w, -1580, 94, -2299, 97, 0));
        FFASpawns.add(new Location(w, -1581, 99, -2322, -87, 0));

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
            ItemStack STONE_SHOVEL = new ItemStack(Material.STONE_SPADE, 1);
            ItemStack IRON_PICK = new ItemStack(Material.IRON_PICKAXE, 1);

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(IRON_HELMET);
            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, IRON_PICK);
            i.setItem(3, STONE_SHOVEL);
            i.setItem(4, STEAK);
            i.setItem(5, HEALTH_POTION);
            i.setItem(6, ARROWS);

        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void clearSpawns(ClearSpawnsEvent event) {
        redSpawns.clear();
        blueSpawns.clear();
        FFASpawns.clear();
    }

    // getting the region
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

    // special arena effects
    @EventHandler(priority = EventPriority.NORMAL)
    public void spireTurret(PlayerInteractEvent eve) {
        Player p = eve.getPlayer();
        ItemStack item = eve.getItem();
        Action action = eve.getAction();
        Location l = p.getLocation();
        Block bl = l.getBlock();
        Block b = bl.getRelative(BlockFace.DOWN, 2);
        Material mat = b.getType();

        if (contains(l, x1, x2, y1, y2, z1, z2)) {

            if (mat == Material.SPONGE && action == Action.RIGHT_CLICK_AIR) {

                if (item.getType() == Material.IRON_SWORD) {

                    p.launchProjectile(Snowball.class);

                }
            }
        }
    }

    /*
    @EventHandler(priority = EventPriority.NORMAL)
    public void snowDamage(EntityDamageByEntityEvent event) {
	Entity snow = event.getDamager();
	Entity entity = event.getEntity();
	Location loc = entity.getLocation();

	if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

	    if (snow instanceof Snowball) {
		Snowball s = (Snowball) snow;
		LivingEntity shooter = s.getShooter();

		if (shooter instanceof Player) {
		    Player sh = (Player) shooter;
		    String shooterName = sh.getName();

		    if (entity instanceof Player) {
			Player p = (Player) entity;
			String pName = p.getName();

			if (Battles.redTeam.containsKey(shooterName)) {

			    if (Battles.blueTeam.containsKey(pName)) {
				p.damage(2, sh);
				sh.playSound(loc, Sound.PIG_IDLE, 1, 1);

			    } else {
				event.setCancelled(true);
			    }

			} else if (Battles.blueTeam.containsKey(shooterName)) {
			    if (Battles.redTeam.containsKey(pName)) {

				p.damage(2, sh);
				sh.playSound(loc, Sound.PIG_IDLE, 1, 1);

			    } else {
				event.setCancelled(true);
			    }
			} else {
			    event.setCancelled(true);
			}

		    }
		}
	    }
	}
    } */

    @EventHandler(priority = EventPriority.NORMAL)
    public void explodingArrow(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        World w = projectile.getWorld();
        Location hit = projectile.getLocation();

        if (contains(hit, x1, x2, y1, y2, z1, z2)) {

            if (projectile instanceof Arrow) {
                Arrow arrow = (Arrow) projectile;
                Entity shooter = arrow.getShooter();
                Location l = shooter.getLocation();
                Block bl = l.getBlock();
                Block b = bl.getRelative(BlockFace.DOWN, 2);
                Material mat = b.getType();

                if (shooter instanceof Player) {
                    Player p = (Player) shooter;
                    ItemStack is = p.getItemInHand();
                    Material i = is.getType();

                    if (i == Material.BOW && mat == Material.SPONGE) {
                        w.createExplosion(hit, 2);
                        Bukkit.getWorld(name).playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);

                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void superEgg(PlayerEggThrowEvent event) {
        Egg egg = event.getEgg();
        World w = egg.getWorld();
        Location location = egg.getLocation();
        // basic start and variables

        if (contains(location, x1, x2, y1, y2, z1, z2)) {
            event.setHatching(true);
            w.createExplosion(location, 25);

            int strikes = 0;
            while (strikes < 20) {
                strikes++;
                // This causes a counting affect to count 20 times by adding 1
                // to 0 till the number 20 for 20 lightning bolts
                w.strikeLightning(location);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void superBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material mat = block.getType();
        Location loc = block.getLocation();

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (mat == Material.LAPIS_ORE) {

                PotionEffectType resist = PotionEffectType.DAMAGE_RESISTANCE;
                PotionEffectType power = PotionEffectType.INCREASE_DAMAGE;
                PotionEffectType regen = PotionEffectType.REGENERATION;
                PotionEffectType speed = PotionEffectType.SPEED;
                PotionEffectType jump = PotionEffectType.JUMP;
                // effect duration power
                PotionEffect resiste = new PotionEffect(resist, 2400, 1);
                PotionEffect powere = new PotionEffect(power, 2400, 1);
                PotionEffect regene = new PotionEffect(regen, 2400, 0);
                PotionEffect speede = new PotionEffect(speed, 2400, 5);
                PotionEffect jumpe = new PotionEffect(jump, 2400, 4);

                player.addPotionEffect(resiste);
                player.addPotionEffect(powere);
                player.addPotionEffect(regene);
                player.addPotionEffect(speede);
                player.addPotionEffect(jumpe);
                player.playSound(loc, Sound.LEVEL_UP, 50, 50);

                String dName = player.getDisplayName();
                Bukkit.broadcastMessage(dName + ChatColor.GOLD + " Now Has The Power!");

            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void spongeGone(BlockBreakEvent event) {
        Block b = event.getBlock();
        Material mat = b.getType();
        Location loc = b.getLocation();

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (mat == Material.SPONGE) {
                b.setType(Material.GOLD_BLOCK);
            }
        }

    }


}
