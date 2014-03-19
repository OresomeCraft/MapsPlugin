package com.oresomecraft.maps.oresomekart;

import com.oresomecraft.OresomeBattles.api.CuboidRegion;
import com.oresomecraft.maps.Map;
import org.bukkit.Material;

public abstract class OresomeKartMap extends Map {

    private int laps;
    private CuboidRegion finishPoint, halfwayCheckPoint;
    private Material[] drivableSurfaces, boostSurfaces, jumpSurfaces = {};

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

}
