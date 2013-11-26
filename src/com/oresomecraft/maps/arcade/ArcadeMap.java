package com.oresomecraft.maps.arcade;

import com.oresomecraft.OresomeBattles.api.events.EndBattleEvent;
import com.oresomecraft.maps.Map;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public abstract class ArcadeMap extends Map {

    private boolean damage = true;
    private boolean explosions = true;


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
        damage = check;
    }

    /**
     * Disables PvP and mob damage
     *
     * @param check The boolean that defines whether physical damage is allowed
     */
    public void setAllowTerrainExplosion(boolean check) {
        explosions = check;
    }


    /**
     * Disables damage caused by ENTITY_ATTACK if disabled
     *
     * @param event an Event called by bukkit
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getWorld().getName().equals(name)) return;
        if (damage) return;
        if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) event.setCancelled(true);
    }


    /**
     * Disables terrain being damaged by ENTITY_EXPLOSION
     *
     * @param event an Event called by bukkit
     */
    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if (event.getEntity().getWorld().getName().equals(name) && !explosions) event.blockList().clear();
    }

}
