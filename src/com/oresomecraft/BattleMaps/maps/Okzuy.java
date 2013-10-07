package com.oresomecraft.BattleMaps.maps;
 
import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Gamemode;
import com.oresomecraft.OresomeBattles.api.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
 
public class Okzuy extends BattleMap implements IBattleMap, Listener {
 
    public Okzuy() {
        super.initiate(this, name, fullName, creators, modes);
    }
 
    String name = "Okzuy";
    String fullName = "Okzuy";
    String creators = "Yuzko";
    Gamemode[] modes = {Gamemode.FFA, Gamemode.INFECTION, Gamemode.KOTH};
 
    
    public void readyTDMSpawns() {
      
        Location redSpawn = new Location(w, -409, 77, 71);
        Location blueSpawn = new Location(w, -317, 74, 146);
        
        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        
        setKoTHMonument(new Location(w, -362, 76, 113));
    }
    
    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -424, 94, 52));
        FFASpawns.add(new Location(w, -413, 89, 48));
        FFASpawns.add(new Location(w, -428, 89, 61));
        FFASpawns.add(new Location(w, -449, 74, 27));
        FFASpawns.add(new Location(w, -378, 74, 28));
        FFASpawns.add(new Location(w, -379, 74, 70));
        FFASpawns.add(new Location(w, -405, 95, 90));
        FFASpawns.add(new Location(w, -423, 74, 97));
        FFASpawns.add(new Location(w, -449, 74, 98));
        FFASpawns.add(new Location(w, -441, 96, 56));
        
        FFASpawns.add(new Location(w, -343, 74, 84));
        FFASpawns.add(new Location(w, -333, 74, 60));
        FFASpawns.add(new Location(w, -328, 78, 95));
        FFASpawns.add(new Location(w, -298, 74, 88));
        FFASpawns.add(new Location(w, -275, 74, 85));
        FFASpawns.add(new Location(w, -281, 77, 71));
        FFASpawns.add(new Location(w, -274, 74, 63));
        FFASpawns.add(new Location(w, -308, 74, 34));
        FFASpawns.add(new Location(w, -428, 89, 61));
        FFASpawns.add(new Location(w, -341, 74, 39));
 
        FFASpawns.add(new Location(w, -307, 79, 189));
        FFASpawns.add(new Location(w, -323, 75, 183));
        FFASpawns.add(new Location(w, -333, 75, 178));
        FFASpawns.add(new Location(w, -287, 75, 18));
        FFASpawns.add(new Location(w, -280, 75, 169));
        FFASpawns.add(new Location(w, -346, 74, 177));
        FFASpawns.add(new Location(w, -344, 76, 144));
        FFASpawns.add(new Location(w, -298, 79, 132));
        
        FFASpawns.add(new Location(w, -379, 74, 145));
        FFASpawns.add(new Location(w, -326, 77, 159));
        FFASpawns.add(new Location(w, -407, 77, 113));
        FFASpawns.add(new Location(w, -408, 77, 113));
        FFASpawns.add(new Location(w, -316, 77, 114));
        
        FFASpawns.add(new Location(w, -326, 77, 113));
        
    }
 
    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();
 
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack _boots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        ItemStack _legs = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        ItemStack _chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
 
        p.getInventory().setBoots(_boots);
        p.getInventory().setLeggings(_legs);
        p.getInventory().setChestplate(_chest);
        
        if (p.getTeam().equals(Team.TDM_BLUE) || p.getTeam().equals(Team.KOTH_BLUE)) {
        	p.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK, 1));
        }
        
        if (p.getTeam().equals(Team.TDM_RED) || p.getTeam().equals(Team.KOTH_RED)) {
        	p.getInventory().setHelmet(new ItemStack(Material.NETHERRACK, 1));
        }
 
        // setItem() is a BattlePlayer method. Makes giving items a bit quicker.
        i.setItem(0, new ItemStack(Material.IRON_AXE, 1));
        i.setItem(1, new ItemStack(Material.BOW, 1));
        i.setItem(5, new ItemStack(Material.RED_ROSE, 1));
        i.setItem(4, HEALTH_POTION);
        i.setItem(3, new ItemStack(Material.COOKED_CHICKEN, 5));
        i.setItem(2, new ItemStack(Material.IRON_PICKAXE, 1));
        i.setItem(9, new ItemStack(Material.ARROW, 64));
    }
 
    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -256;
    public int y1 = 225;
    public int z1 = 14;
 
    // Bottom right corner.
    public int x2 = -468;
    public int y2 = 30;
    public int z2 = 220;
 
    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
    	Material blockType = e.getBlock().getType();
    	int blockID = e.getBlock().getTypeId();
    	Location l = e.getBlock().getLocation();
    	
    	if(blockID == 159)
    	{
    		l.getBlock().setType(Material.BEDROCK);
    		l.getWorld().dropItemNaturally(l, new ItemStack(Material.MUSHROOM_SOUP, 1));
    	}
    	else 
    	{
    		
    	}
    	
    	if(blockType == Material.SPONGE)
    	{
    		l.getBlock().setType(Material.BEDROCK);
    		l.getWorld().dropItemNaturally(l, new ItemStack(Material.GOLDEN_APPLE, 1));
    	}
    	else if(blockType == Material.NETHER_BRICK)
    	{
    		l.getBlock().setType(Material.BEDROCK);
    		l.getWorld().dropItemNaturally(l, new ItemStack(Material.IRON_SWORD, 1));
    	}
    	else if(blockType == Material.IRON_ORE)
    	{
    		l.getBlock().setType(Material.BEDROCK);
    		l.getWorld().dropItemNaturally(l, new ItemStack(Material.IRON_INGOT, 1));
    	}
    	else if(blockType == Material.QUARTZ_BLOCK)
    	{
    		l.getBlock().setType(Material.BEDROCK);
    		l.getWorld().dropItemNaturally(l, new ItemStack(Material.BOW, 1));
    		l.getWorld().dropItemNaturally(l, new ItemStack(Material.ARROW, 32));
    	}
    	else
    	{
    		
    	}
    	e.setCancelled(true);
    }
} 
