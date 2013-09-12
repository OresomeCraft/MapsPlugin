package com.oresomecraft.BattleMaps;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import com.oresomecraft.BattleMaps.maps.*;

/**
 * OresomeBattlesMaps | Component for OresomeBattles
 *
 * @author OresomeCraft, Zachoz
 */
public class OresomeBattlesMaps extends JavaPlugin {

    public final Logger logger = Logger.getLogger("Minecraft");
    protected static OresomeBattlesMaps plugin;

    public OresomeBattlesMaps() {
        plugin = this;
    }

    public void onDisable() {

    }

    public void onEnable() {
        loadMaps();
    }

    public void loadMaps() {
        new Apollo();
        new Zoned();
        new Mantle();
        new Roseley();
        new Perro();
        new Fairwick();
        new Wartown();
        new Spire();
        new Xenon();
        new Mansion();
        new Hypno();
        new Arctic();
        new Mutiny();
        new Nuketown();
        new Terminal();
        new Sandtrap();
        new Towers();
        new Hartshire();
        new Skyislands();
        new Fosscrest();
        new Solitude();
        new Suburban();
        new MutinyII();
        new Battlement();
        new Insanity();
        new Carnival();
        new Sub();
        new Deepcaverns();
        new Darknessofdusk();
        new Docks();
        new Spaceships();
        new Relation();
        new Raid();
        new Voidsflag();
        new DimensionalWar();
        new Mayhem();
        new BurnFirePort();
        new ClashOfClay();
        new Elements();
        new GibsonDesertBattles();
        new WarTrauma();
        new ClashOfClayII();
        new Sunshine();
        new Electricity();
        new HauntedHouse();
        new Crater();
        new Gladiator();
        new ElementsII();
        new TheBowl();
        new Simplex();
        new Treetop();
        new BiomeBattle();
        new Yuzkave();
        new Alpines();
        new Rainbow();
        new Equator();
        new Chasm();
        new TowerHill();
        new DesertCastle();
        new Oasis();
        new SkyFights();
        new TelluricPath();
        new ClashOfClayIII();
    }

    public static OresomeBattlesMaps getInstance() {
        return plugin;
    }

}