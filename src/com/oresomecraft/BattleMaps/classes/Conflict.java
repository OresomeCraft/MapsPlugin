package com.oresomecraft.BattleMaps.classes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.MapInterface;
import com.oresomecraft.BattleMaps.OresomeBattlesMaps;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.ClearSpawnsEvent;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.events.ReadyMapsEvent;

public class Conflict extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Conflict(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "Conflict";
    String fullName = "Conflict";
    String creators = "ShaunDepro97, Darkrai202, Evil_Emo";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};
    //Map download link: http://dl11.fileswap.com/download/?id=iL0cKhRYGFFPwHa4ioG%2FMOjyyQAXCB0Rd%2FmKoL2jjP0dC0V%2FervfvnLCo9jmU1hX2RT1CfDFNDstOMzfY44dT3A8xi4AXHi%2FTGbTxeb3b5%2F1IgDSQE%2FIosGvuWrAn%2BH1&h=46a0cfe023927e603231e70e9bb178ff&t=51aae321

    @EventHandler(priority = EventPriority.NORMAL)
    public void readyMap(ReadyMapsEvent event) {
        addMap(name);
        readyTDMSpawns();
        readyFFASpawns();
        setGamemodes(name, modes);
        addCreators(name, creators); 
        setFullName(name, fullName);
    }

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 115, 99, 64, 1, 0);
        Location blueSpawn = new Location(w, 12, 99, 64, 3, 0);

        redSpawns.add(redSpawn);
  	redSpawns.add(new Location(w, 119, 99, 71, 2, 0);
		redSpawns.add(new Location(w, 119, 99, 57, 0, 0);
		redSpawns.add(new Location(w, 98, 98, Z, 71, 0);
		redSpawns.add(new Location(w, 98, 98, Z, 57, 0);
		redSpawns.add(new Location(w, 125, 104, 64, 1, 0);
		redSpawns.add(new Location(w, 125, 109, 64, 1, 0);
		redSpawns.add(new Location(w, 113, 115, 70, 1, 0);
		redSpawns.add(new Location(w, 113, 115, 58, 1, 0);
		redSpawns.add(new Location(w, 104, 99, 64, 1, 0);

        blueSpawns.add(blueSpawn);
		blueSpawns.add(new Location(w, 9, 99, 57, 0, 0);
		blueSpawns.add(new Location(w, 9, 99, 71, 2, 0);
		blueSpawns.add(new Location(w, 28, 98, 57, 3, 0);
		blueSpawns.add(new Location(w, 28, 98, 71, 3, 0);
		blueSpawns.add(new Location(w, 2, 104, 64, 3, 0);
		blueSpawns.add(new Location(w, 2, 109, 64, 3, 0);
		blueSpawns.add(new Location(w, 14, 115, 70, 3, 0);
		blueSpawns.add(new Location(w, 14, 115, 58, 3, 0);
		blueSpawns.add(new Location(w, 23, 99, 64, 3, 0);

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 115, 99, 64, 1, 0);
        Location blueSpawn = new Location(w, 12, 99, 64, 3, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
		FFASpawns.add(new Location(w, 119, 99, 71, 2, 0);
		FFASpawns.add(new Location(w, 119, 99, 57, 0, 0);
		FFASpawns.add(new Location(w, 98, 98, Z, 71, 0);
		FFASpawns.add(new Location(w, 98, 98, Z, 57, 0);
		FFASpawns.add(new Location(w, 125, 104, 64, 1, 0);
		FFASpawns.add(new Location(w, 125, 109, 64, 1, 0);
		FFASpawns.add(new Location(w, 113, 115, 70, 1, 0);
		FFASpawns.add(new Location(w, 113, 115, 58, 1, 0);
		FFASpawns.add(new Location(w, 104, 99, 64, 1, 0);
		FFASpawns.add(new Location(w, 9, 99, 57, 0, 0);
		FFASpawns.add(new Location(w, 9, 99, 71, 2, 0);
		FFASpawns.add(new Location(w, 28, 98, 57, 3, 0);
		FFASpawns.add(new Location(w, 28, 98, 71, 3, 0);
		FFASpawns.add(new Location(w, 2, 104, 64, 3, 0);
		FFASpawns.add(new Location(w, 2, 109, 64, 3, 0);
		FFASpawns.add(new Location(w, 14, 115, 70, 3, 0);
		FFASpawns.add(new Location(w, 14, 115, 58, 3, 0);
		FFASpawns.add(new Location(w, 23, 99, 64, 3, 0);

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
            ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

			if (TDM.isBlue(p.getName())) {
              LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
              helmetMeta.setColor(Color.BLUE);
              LEATHER_HELMET.setItemMeta(helmetMeta);
          }
		  
		  	if (TDM.isBlue(p.getName())) {
              LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
              helmetMeta.setColor(Color.BLUE);
              LEATHER_HELMET.setItemMeta(helmetMeta);
          }
			
            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(IRON_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, ARROWS);
            i.setItem(5, EXP);

        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void clearSpawns(ClearSpawnsEvent event) {
        redSpawns.clear();
        blueSpawns.clear();
        FFASpawns.clear();
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 140;
    public int y1 = 140;
    public int z1 = 86;

    //Bottom right corner.
    public int x2 = -11;
    public int y2 = 1;
    public int z2 = 45;

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

    // Code to prevent block breaking.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    // Code to prevent block placing.
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection1(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

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
	
	 @EventHandler
  public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
      Player p = event.getEntity();
      TagAPI.refreshPlayer(p);
      List<ItemStack> drops = event.getDrops();

      for (ItemStack item : drops) {
          Material mat = item.getType();

          if (mat == Material.IRON_SWORD
                  || mat == Material.BOW
                  || mat == Material.IRON_BOOTS
                  || mat == Material.IRON_LEGGINGS
                  || mat == Material.IRON_CHESTPLATE
                  || mat == Material.IRON_HELMET
                  || mat == Material.ARROW
              item.setType(Material.AIR);
          }
      }
  }
  
    @EventHandler(priority = EventPriority.NORMAL)
  public void explodingArrow(org.bukkit.event.entity.ProjectileHitEvent event) {
      org.bukkit.entity.Entity projectile = event.getEntity();
      World w = projectile.getWorld();
      Location hit = projectile.getLocation();

      if (hit.getWorld().getName().equals(name)) {

          if (projectile instanceof org.bukkit.entity.Arrow) {
              org.bukkit.entity.Arrow arrow = (org.bukkit.entity.Arrow) projectile;
              org.bukkit.entity.Entity shooter = arrow.getShooter();
              Location l = shooter.getLocation();
              Block bl = l.getBlock();
              Block b = bl.getRelative(org.bukkit.block.BlockFace.DOWN, 1);
              Material mat = b.getType();

              if (shooter instanceof Player) {
                  Player p = (Player) shooter;
                  ItemStack is = p.getItemInHand();
                  Material i = is.getType();

                  if (i == Material.BOW && mat == Material.GOLD_BLOCK) {
                      player.launchProjectile(org.bukkit.entity.Fireball.class.setVelocity(event.getProjectile().getVelocity());
                      Bukkit.getWorld(name).playEffect(arrow.getLocation(), org.bukkit.Effect.STEP_SOUND, 10);
					  
                  if (i == Material.BOW && mat == Material.IRON_BLOCK) {
                      w.strikeLightning(hit);
                      Bukkit.getWorld(name).playEffect(arrow.getLocation(), org.bukkit.Effect.STEP_SOUND, 10);
                  }
              }
          }
      }
  }

}
