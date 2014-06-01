package com.oresomecraft.maps.arcade;

import com.oresomecraft.OresomeBattles.events.EndBattleEvent;
import com.oresomecraft.maps.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public abstract class ArcadeMap extends Map {

    private boolean physicalDamage = true;
    private boolean explosions = true;
    private boolean autoTNT = false;
    private boolean playerDamage = true;


    /**
     * Ends the battle
     */
    public void end() {
        Bukkit.getPluginManager().callEvent(new EndBattleEvent(getMode()));
    }


    /**
     * Disables PvP and mob damage
     *
     * @param check The boolean that defines whether physical damage is allowed
     */
    public void setAllowPhysicalDamage(boolean check) {
        physicalDamage = check;
    }

    /**
     * Disables PvP and mob damage
     *
     * @param check The boolean that defines whether terrain damage is allowed
     */
    public void setAllowTerrainExplosion(boolean check) {
        explosions = check;
    }

    /**
     * Disables PvP and mob damage
     *
     * @param check The boolean that defines wether tnt is auto-ignited
     */
    public void setAutoTNTIgnite(boolean check) {
        autoTNT = check;
    }

    public void setAllowPlayerDamage(boolean allow) {
        this.playerDamage = allow;
    }

    /**
     * Disables damage caused by ENTITY_ATTACK if disabled
     *
     * @param event an Event called by bukkit
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (!physicalDamage) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) event.setCancelled(true);
        }

        if (!playerDamage) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                    && event.getDamager() instanceof Player)
                event.setCancelled(true);
        }
    }


    /**
     * Disables terrain being damaged by ENTITY_EXPLOSION
     *
     * @param event an Event called by bukkit
     */
    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        // if (event.getEntity().getWorld().getName().equals(name) && !explosions) event.blockList().clear();
    }


    /**
     * Auto ignites TNT if defined
     *
     * @param event an Event called by bukkit
     */
    @EventHandler
    public void onExplode(BlockPlaceEvent event) {
        if (!event.getBlock().getWorld().getName().equals(name)) return;
        if (autoTNT && event.getBlock().getType() == Material.TNT) {
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
        }
    }

}
