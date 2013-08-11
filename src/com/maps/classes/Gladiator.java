package com.oresomecraft.BattleMaps.maps;

import com.oresomecraft.BattleMaps.IBattleMap;
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
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;

public class Gladiator extends BattleMap implements IBattleMap, Listener {

    public Gladiator() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    // Map details
    String name = "gladiator";
    String fullName = " Gladiator";
    String creators = "eddie017";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};/i 

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 207, 101, -31, -90, 0);
        Location blueSpawn = new Location(w, 289, 101, -31, 90, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 207, 101, -31, -90, 0));
        redSpawns.add(new Location(w, 224, 92, -27, -90, 0));
        redSpawns.add(new Location(w, 224, 92, -35, -90, 0));
        redSpawns.add(new Location(w, 197, 93, -31, -90, 0));
  	redSpawns.add(new Location(w, 230, 92, -12, -135, 0));
		redSpawns.add(new Location(w, 229, 92, -49, -45, 0));
		redSpawns.add(new Location(w, 231, 110, -30, -90, 0));
		redSpawns.add(new Location(w, 222, 100, -5, -135, 0));
		

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 289, 101, -31, 90, 0));
        blueSpawns.add(new Location(w, 272, 92, -28, 90, 0));
        blueSpawns.add(new Location(w, 272, 92, -34, 90, 0));
        blueSpawns.add(new Location(w, 299, 93, -32, 90, 0));
		blueSpawns.add(new Location(w, 266, 92, -48, 45, 0));
		blueSpawns.add(new Location(w, 266, 92, -12, 135, 0));
		blueSpawns.add(new Location(w, 265, 110, -30, 90, 0));
		blueSpawns.add(new Location(w, 273, 100, -56, 45, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
		
		setKoTHMonument(new Location(w, 248, 125, -32));
		
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, 248, 101, -73, 0, 0));
        FFASpawns.add(new Location(w, 248, 101, 9, 180, 0));
		FFASpawns.add(new Location(w, 208, 101, -32, -90, 0));
		FFASpawns.add(new Location(w, 288, 101, -32, 90, 0));
		FFASpawns.add(new Location(w, 272, 92, -30, 90, 0));
		FFASpawns.add(new Location(w, 272, 92, -34, 90, 0));
		FFASpawns.add(new Location(w, 300, 93, -32, 90, 0));
		FFASpawns.add(new Location(w, 267, 92, -14, 135, 0));
		FFASpawns.add(new Location(w, 265, 92, -50, 45, 0));
		FFASpawns.add(new Location(w, 248, 93, 19, -180, 0));
		FFASpawns.add(new Location(w, 248, 93, -83, 0, 0));
		FFASpawns.add(new Location(w, 251, 92, -56, 0, 0));
		FFASpawns.add(new Location(w, 245, 92, -56, 0, 0));
		FFASpawns.add(new Location(w, 248, 92, -31, -125, 0));
		FFASpawns.add(new Location(w, 245, 92, -8, -180, 0));
		FFASpawns.add(new Location(w, 251, 92, -8, -180, 0));
		FFASpawns.add(new Location(w, 224, 92, -35, -90, 0));
		FFASpawns.add(new Location(w, 224, 92, -29, -90, 0));
		FFASpawns.add(new Location(w, 229, 92, -14, -135, 0));
		FFASpawns.add(new Location(w, 230, 92, -51, -45, 0));
		FFASpawns.add(new Location(w, 196, 93, -32, -90, 0));
		FFASpawns.add(new Location(w, 274, 100, -58, 45, 0));
		FFASpawns.add(new Location(w, 222, 100, -5, -135, 0));
		FFASpawns.add(new Location(w, 252, 104, -36, 45, 0));
		FFASpawns.add(new Location(w, 252, 104, -28, 135, 0));
		FFASpawns.add(new Location(w, 244, 104, -28, -135, 0));
		FFASpawns.add(new Location(w, 244, 104, -36, -45, 0));
		FFASpawns.add(new Location(w, 252, 109, -36, 45, 0));
		FFASpawns.add(new Location(w, 252, 109, -28, 135, 0));
		FFASpawns.add(new Location(w, 244, 109, -28, -135, 0));
		FFASpawns.add(new Location(w, 244, 109, -36, -45, 0));
		FFASpawns.add(new Location(w, 248, 108, -23, 180, 0));
		FFASpawns.add(new Location(w, 248, 108, -41, 0, 0));
		FFASpawns.add(new Location(w, 265, 110, -30, 90, 0));
		FFASpawns.add(new Location(w, 231, 110, -32, -90, 0));
		
        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 24);
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
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, ARROWS);

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -320;
    public int y1 = 82;
    public int z1 = 38;

    //Bottom right corner.
    public int x2 = 176;
    public int y2 = 148;
    public int z2 = -103;

}

  @EventHandler
  public void blockBreak(org.bukkit.event.block.BlockBreakEvent event) {
      org.bukkit.block.Block b = event.getBlock();
      Location loc = b.getLocation();
      if (loc.getWorld().getName().equals(name)) {
          event.setCancelled(true);
      }
  }
  
   @EventHandler
  public void blockPlace(org.bukkit.event.block.BlockPlaceEvent event) {
      org.bukkit.Block b = event.getBlock();
      Location loc = b.getLocation();
      if (loc.getWorld().getName().equals(name)) {
          event.setCancelled(true);
      }
  }
  
    @EventHandler(priority = EventPriority.NORMAL)
  public void fishing(org.bukkit.event.player.PlayerFishEvent event) {
      org.bukkit.event.player.PlayerFishEvent.State state = event.getState();
      Player p = event.getPlayer();
      ItemStack is = p.getItemInHand();
      Material mat = is.getType();
      Location loc = p.getLocation();
      if (loc.getWorld().getName().equals(name)) {
          if (mat == Material.FISHING_ROD) {
              if (state == State.IN_GROUND) {
                  p.launchProjectile(Snowball.class);
              }
          }
      }
  }

  @EventHandler(priority = EventPriority.NORMAL)
  public void grapple(org.bukkit.event.entity.ProjectileHitEvent event) {
      org.bukkit.entity.Entity proj = event.getEntity();
      Location hit = proj.getLocation();
      if (hit.getWorld().getName().equals(name)) {
          if (proj instanceof org.bukkit.entity.Snowball) {

              org.bukkit.entity.Snowball fish = (org.bukkit.entity.Snowball) proj;
              org.bukkit.entity.Entity shooter = fish.getShooter();

              if (shooter instanceof Player) {

                  Player p = (Player) shooter;
                  Location loc = p.getLocation();
                  ItemStack is = p.getItemInHand();
                  Material mat = is.getType();
                  if (mat == Material.FISHING_ROD) {
                      p.setFallDistance(0);
                      p.playSound(loc, org.bukkit.Sound.ARROW_HIT, 1, 1);

                      int hitx = hit.getBlockX();
                      int hity = hit.getBlockY();
                      int hitz = hit.getBlockZ();
                      int locx = loc.getBlockX();
                      int locy = loc.getBlockY();
                      int locz = loc.getBlockZ();
                      double co[] = new double[3];

                      if (hitx > locx) {
                          co[0] = 1.2;
                      } else if (hitx < locx) {
                          co[0] = -1.2;
                      } else if (hitx == locx) {
                          co[0] = 0;
                      }

                      if (hity > locy) {
                          co[1] = 1.4;
                      } else if (hity < locy) {
                          co[1] = -0.8;
                      } else if (hity == locy) {
                          co[1] = 0;
                      }

                      if (hitz > locz) {
                          co[2] = 1.2;
                      } else if (hitz < locz) {
                          co[2] = -1.2;
                      } else if (hitz == locz) {
                          co[2] = 0;
                      }

                      p.setVelocity(new org.bukkit.util.Vector(co[0], co[1], co[2]));

                  }
              }
          }
      }
  }
