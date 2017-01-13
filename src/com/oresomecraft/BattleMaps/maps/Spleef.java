package com.oresomecraft.BattleMaps.maps;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.oresomecraft.BattleMaps.*;
import com.oresomecraft.OresomeBattles.api.*;

public class Spleef extends BattleMap implements IBattleMap, Listener {

	public Spleef() {
		super.initiate(this, name, fullName, creators, modes);
		lockTime("day");
	}

	// Map details
	String name = "spleef";
	String fullName = "Spleef";
	String creators = "Yuzko";
	Gamemode[] modes = {Gamemode.FFA};

	public void readyFFASpawns() {
		FFASpawns.add(new Location(yuzko, -215, 69, -92));
	}

	// Region. (Top corner block and bottom corner block.
	// Top left corner.
	public int x1 = -202;
	public int y1 = 81;
	public int z1 = -72;

	//Bottom right corner.
	public int x2 = -227;
	public int y2 = 58;
	public int z2 = -116;

	public void onHit(EntityDamageByEntityEvent e)
	{
		Player p = (Player) e.getDamager();
		Player p1 = (Player) e.getEntity();
		if(p1 instanceof Player)
		{
			e.setCancelled(true);
		}
	}
	
	public void breakWool(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		Action a = e.getAction();
		
		if(e.getAction() == a.LEFT_CLICK_BLOCK)
		{
			if(b.getType() == Material.WOOL && b.getData() == 11) //blue wool
			{
				b.setType(Material.WOOL);
				b.setData((byte) 1);
			}
			 else
			 {
				 if(b.getType() == Material.WOOL && b.getData() == 1)
				 {
					 b.setType(Material.WOOL);
					 b.setData((byte) 14); //red wool
				 }
				 else
				 {
					 if(b.getType() == Material.WOOL && b.getData() == 14)
					 {
						 b.setType(Material.AIR);
					 }
				 }
			 }
		}
	}
	
	public void breakBlock(BlockBreakEvent e)
	{
		if(e.getBlock().getType() == Material.GLASS || e.getBlock().getType() == Material.STONE)
		{
			e.setCancelled(true);
		}
	}

}
