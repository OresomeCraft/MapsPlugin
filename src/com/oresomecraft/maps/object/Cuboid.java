package com.oresomecraft.maps.object;

import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import com.oresomecraft.OresomeBattles.api.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Cuboid implements Iterable<Block> {
    protected String worldName;
    protected int x1, y1, z1;
    protected int x2, y2, z2;
    protected Team[] appliedTeams;

    public Cuboid(Location l1, Location l2, Team[] teams) {
        if (l1 == null || l2 == null)
            throw new NullPointerException("Location can't be null");
        else if (l1.getWorld() == null)
            throw new IllegalStateException("Can't create a Cuboid for an unloaded world");
        else if (!l1.getWorld().getName().equals(l2.getWorld().getName()))
            throw new IllegalStateException("Can't create a Cuboid between two different worlds");
        if (teams == null)
            teams = new Team[]{Team.TDM_BLUE, Team.CP_BLUE, Team.CP_RED, Team.CTF_BLUE, Team.CTF_RED, Team.FFA, Team.HUMANS,
                    Team.KOTH_BLUE, Team.KOTH_RED, Team.LMS, Team.LTS_BLUE, Team.LTS_RED, Team.ZOMBIES};
        worldName = l1.getWorld().getName();
        appliedTeams = teams;
        x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        y1 = Math.min(l1.getBlockY(), l2.getBlockY());
        z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        y2 = Math.max(l1.getBlockY(), l2.getBlockY());
        z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
    }

    public boolean isInside(Location loc) {
        if (!loc.getWorld().getName().equals(worldName))
            return false;
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        return x >= x1 && x <= x2 && y >= y1 && y <= y2 && z >= z1 && z <= z2;
    }

    public boolean checkLocation(BattlePlayer p) {
        if (!p.getLocation().getWorld().getName().equals(worldName))
            return false;
        int x = p.getLocation().getBlockX();
        int y = p.getLocation().getBlockY();
        int z = p.getLocation().getBlockZ();
        for (Team t : appliedTeams) {
            if (t == p.getTeam()) return x >= x1 && x <= x2 && y >= y1 && y <= y2 && z >= z1 && z <= z2;
        }
        return false;
    }

    public boolean checkLocation(Location l, Team team) {
        if (!l.getWorld().getName().equals(worldName))
            return false;
        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();
        for (Team t : appliedTeams) {
            if (t == team) return x >= x1 && x <= x2 && y >= y1 && y <= y2 && z >= z1 && z <= z2;
        }
        return false;
    }

    public void setBlocks(Material material) {
        if (material.isBlock())
            throw new IllegalArgumentException("'" + material.name() + "' isn't a valid block material");
        for (Block b : this)
            b.setType(material);
    }

    public boolean hasReachedHorizontalBorder(Location loc) {
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        return x == x1 || x == x2 || z == z1 || z == z2;
    }

    public List<Block> getBlocks() {
        World world = getWorld();
        List<Block> blocks = new ArrayList<Block>();
        for (int x = x1; x <= x2; x++)
            for (int z = z1; z <= z2; z++)
                for (int y = y1; y <= y2; y++)
                    blocks.add(world.getBlockAt(x, y, z));
        return blocks;
    }

    public int getSizeX() {
        return (x2 - x1) + 1;
    }

    public int getSizeY() {
        return (y2 - y1) + 1;
    }

    public int getSizeZ() {
        return (z2 - z1) + 1;
    }

    public int getVolume() {
        return getSizeX() * getSizeY() * getSizeZ();
    }

    public Location getLowerNE() {
        return new Location(this.getWorld(), this.x1, this.y1, this.z1);
    }

    public Location getUpperSW() {
        return new Location(this.getWorld(), this.x2, this.y2, this.z2);
    }

    public World getWorld() {
        World world = Bukkit.getWorld(worldName);
        if (world == null)
            throw new IllegalStateException("World '" + this.worldName + "' is not loaded");
        return world;
    }

    public Location getHorizontalMirrorLocation(Location loc) {
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        boolean xBorder = x == x1 || x == x2;
        boolean x1Border = xBorder ? x == x1 : false;
        boolean zBorder = z == z1 || z == z2;
        boolean z1Border = zBorder ? z == z1 : false;
        if (xBorder)
            loc.setX(x1Border ? x2 : x1 + 1);
        if (zBorder)
            loc.setZ(z1Border ? z2 : z1 + 1);
        return loc;
    }

    @Override
    public Iterator<Block> iterator() {
        return getBlocks().iterator();
    }
}