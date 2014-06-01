package com.oresomecraft.maps.oresomekart;

import com.oresomecraft.OresomeBattles.region.CuboidRegion;
import com.oresomecraft.maps.Map;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public abstract class OresomeKartMap extends Map {

    private int laps;
    private CuboidRegion finishPoint, halfwayCheckPoint;
    private Material[] drivableSurfaces, boostSurfaces, jumpSurfaces = {};
    private ArrayList<TeleportRegion> teleportRegions = new ArrayList<OresomeKartMap.TeleportRegion>();

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public int getLaps() {
        return this.laps;
    }

    public void setFinishPoint(CuboidRegion region) {
        this.finishPoint = region;
    }

    public CuboidRegion getFinishPoint() {
        return this.finishPoint;
    }

    public void setHalfwayCheckPoint(CuboidRegion region) {
        this.halfwayCheckPoint = region;
    }

    public CuboidRegion getHalfwayCheckPoint() {
        return this.halfwayCheckPoint;
    }

    public void setDrivableSurfaces(Material[] surfaces) {
        this.drivableSurfaces = surfaces;
    }

    public Material[] getDrivableSurfaces() {
        return this.drivableSurfaces;
    }

    public void setBoostSurfaces(Material[] surfaces) {
        this.boostSurfaces = surfaces;
    }

    public Material[] getBoostSurfaces() {
        return this.boostSurfaces;
    }

    public void setJumpSurfaces(Material[] surfaces) {
        this.jumpSurfaces = surfaces;
    }

    public Material[] getJumpSurfaces() {
        return this.jumpSurfaces;
    }

    public void addTeleportRegion(CuboidRegion region, Location loc) {
        teleportRegions.add(new TeleportRegion(region, loc));
    }

    public ArrayList<TeleportRegion> getTeleportRegions() {
        return this.teleportRegions;
    }

    public class TeleportRegion {
        CuboidRegion region;
        Location teleportTo;

        public TeleportRegion(CuboidRegion region, Location teleportTo) {
            this.region = region;
            this.teleportTo = teleportTo;
        }

        public CuboidRegion getRegion() {
            return this.region;
        }

        public Location getTeleportTo() {
            return this.teleportTo;
        }
    }

}
