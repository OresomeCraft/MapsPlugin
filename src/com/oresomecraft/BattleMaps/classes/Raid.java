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

public class Raid extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Raid(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }

    // Spawn lists. (Don't change!)
    public ArrayList<Location> redSpawns = new ArrayList<Location>();
    public ArrayList<Location> blueSpawns = new ArrayList<Location>();
    public ArrayList<Location> FFASpawns = new ArrayList<Location>();

    // Map details
    String name = "raid";
    String fullName = "Raid";
    String creators = "ShaunDepro97";
    Gamemode[] modes = {Gamemode.TDM};
    //Map download link: http://www.filedropper.com/raid

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

        Location redSpawn = new Location(w, 5, 68, -5, 1, 0);
        Location blueSpawn = new Location(w, -81, 69, -2, 3, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, -8, 66, -10, 1, 0));
        redSpawns.add(new Location(w, -19, 75, -12, 1, 0));
        redSpawns.add(new Location(w, 13, 68, 17, 1, 0));
        redSpawns.add(new Location(w, 31, 75, 1, 1, 0));
        redSpawns.add(new Location(w, 42, 75, -10, 1, 0));
        redSpawns.add(new Location(w, 22, 75, -33, 1, 0));
        redSpawns.add(new Location(w, 37, 78, -41, 1, 0));
        redSpawns.add(new Location(w, 28, 85, -65, 1, 0));
        redSpawns.add(new Location(w, 47, 82, -29, 1, 0));
        redSpawns.add(new Location(w, 3, 74, 61, 1, 0));
        redSpawns.add(new Location(w, -1, 66, 5, 1, 0));
        redSpawns.add(new Location(w, -1, 66, -22, 1, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, -79, 66, -6, 3, 0));
  	blueSpawns.add(new Location(w, -83, 66, -6, 3, 0));
		blueSpawns.add(new Location(w, -79, 65, -17, 3, 0));
	    blueSpawns.add(new Location(w, -83, 65, -6, 3, 0));
		blueSpawns.add(new Location(w, -81, 65, -22, 3, 0));
		blueSpawns.add(new Location(w, -81, 66, -32, 3, 0));
		blueSpawns.add(new Location(w, -81, 62, -6, 3, 0));
		blueSpawns.add(new Location(w, -81, 62, -18, 3, 0));
		blueSpawns.add(new Location(w, -81, 62, -21, 3, 0));
		blueSpawns.add(new Location(w, -81, 62, -24, 3, 0));
		blueSpawns.add(new Location(w, -81, 62, -27, 3, 0));
		blueSpawns.add(new Location(w, -81, 62, -30, 3, 0));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        Location redSpawn = new Location(w, 5, 68, -5, 1, 0);
        Location blueSpawn = new Location(w, -81, 69, -2, 3, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 5, 68, -5, 1, 0));

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
            ItemStack STEAK = new ItemStack(Material.COOKED_FISH, 2);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 48);
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack CHAINMAIL_PANTS = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
			
			if (TDM.isBlue(p.getName())) {
              LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
              helmetMeta.setColor(Color.BLUE);
              LEATHER_HELMET.setItemMeta(helmetMeta);
			  ItemStack ENDER_PEARL = new ItemStack(Material.ENDER_PEARL, 1);
			  i.setItem(5, ENDER_PEARL);
          }

          if (TDM.isRed(p.getName())) {
              LeatherArmorMeta helmetMeta = (LeatherArmorMeta) LEATHER_HELMET.getItemMeta();
              helmetMeta.setColor(Color.RED);
              LEATHER_HELMET.setItemMeta(helmetMeta);
			  ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);
			  i.setItem(2, FISHING_ROD);
          }

            p.getInventory().setBoots(IRON_BOOTS);
            p.getInventory().setLeggings(CHAINMAIL_PANTS);
            p.getInventory().setChestplate(IRON_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(3, COOKED_FISH);
            i.setItem(4, HEALTH_POTION);
            i.setItem(28, ARROWS);

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
    public int x1 = -128;
    public int y1 = 1;
    public int z1 = -128;

    //Bottom right corner.
    public int x2 = 128;
    public int y2 = 256;
    public int z2 = 128;

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
  public void death(org.bukkit.event.entity.PlayerDeathEvent event) {
      Player p = event.getEntity();
      TagAPI.refreshPlayer(p);
      List<ItemStack> drops = event.getDrops();

      for (ItemStack item : drops) {
          Material mat = item.getType();

          if (mat == Material.IRON_SWORD
                  || mat == Material.BOW
                  || mat == Material.IRON_BOOTS
                  || mat == Material.CHAINMAIL_LEGGINGS
                  || mat == Material.IRON_CHESTPLATE
                  || mat == Material.LEATHER_HELMET
                  || mat == Material.ARROW
				  || mat == Material.FISHING_ROD
				  || mat == Material.ENDER_PEARL

              item.setType(Material.AIR);
          }
      }
  }

}
