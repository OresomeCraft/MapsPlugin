package com.oresomecraft.BattleMaps.api;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.Team;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class InvUtils {

    /**
     * Automatically sets the colour of Leather Armour to match a player's team colour
     *
     * @param p      A BattlePlayer
     * @param armour ItemStack array to get the armour from
     */
    public static void colourArmourAccordingToTeam(BattlePlayer p, ItemStack[] armour) {
        Color teamColour = null;

        switch (p.getTeam()) {
            case HUMANS:
                teamColour = Color.GREEN;
                break;
            case ZOMBIES:
                teamColour = Color.RED;
                break;
            case FFA:
                teamColour = Color.GREEN;
                break;
        }

        if (p.getTeam() == Team.TDM_RED || p.getTeam() == Team.CTF_RED || p.getTeam() == Team.KOTH_RED) teamColour = Color.RED;
        if (p.getTeam() == Team.TDM_BLUE || p.getTeam() == Team.CTF_BLUE || p.getTeam() == Team.KOTH_BLUE) teamColour = Color.BLUE;

        for (ItemStack i : armour) {
            LeatherArmorMeta iMeta = (LeatherArmorMeta) i.getItemMeta();
            iMeta.setColor(teamColour);
            i.setItemMeta(iMeta);
        }
    }

    /**
     * Sets the display name of an item
     *
     * @param item An ItemStack
     * @param name Name for the item to be called
     */
    public static void nameItem(ItemStack item, String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
    }

}
