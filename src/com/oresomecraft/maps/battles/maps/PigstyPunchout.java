package com.oresomecraft.maps.battles.maps;

import org.bukkit.*;
import org.bukkit.inventory.*;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.*;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.util.Vector;
import org.bukkit.enchantments.Enchantment;

@MapConfig(
        name = "pigsty",
        fullName = "Pigsty Punchout",
        creators = {"PyroPolar"},
        gamemodes = {Gamemode.KOTH}
        //https://www.dropbox.com/home/Pigsty%20Punchout
)
@Region(
        x1 = -88,
        y1 = 63,
        z1 = 949,
        x2 = -164,
        y2 = 45,
        z2 = 1029
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 10,
        disabledDrops = {Material.BOW, Material.WOOD_SWORD, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.Pork}
)
public class PigstyPunchhout extends BattleMap {

    public PigstyPunchout() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, -137, 53, 954, -90, 0));
        redSpawns.add(new Location(w, -116, 53, 954, 90, 0));
        
        blueSpawns.add(new Location(w, -137, 53, 1023, -90, 0));
        blueSpawns.add(new Location(w, -116, 53, 1023, 90, 0));
        
        setKoTHMonument(new Location(w, -126, 58, 989));
        
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 99, 27, 2, 0));
        FFASpawns.add(new Location(w, -9, 110, -20, 0, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack GRILLED_PORK = new ItemStack(Material.GRILLED_PORK, 5);
        ItemStack ARROW = new ItemStack(Material.ARROW, 64);
        
        WOOD_SWORD.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
       
        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        // setItem() is a BattlePlayer method. Makes giving items a bit quicker.
        p.setItem(0, WOOD_SWORD);
        p.setItem(1, Material.BOW, 1);
        p.setItem(2, Material.COOKED_PORK, 1);

        // This is the Bukkit way of doing it
        i.setItem(3, HEALTH_POTION);
        p.setItem(9, Material.ARROW, 64);
    }

@EventHandler
    public void vDeath(EntityDeathEvent event) {
        if (!event.getEntity().getWorld().getName().equals(getName())) return;
        if (event.getEntity() instanceof Pig) {
            event.getDrops().clear();
            ItemStack FIRE = new ItemStack(Material.FIREWORK, new Random().nextInt(3));
            ItemMeta fMeta = FIRE.getItemMeta();
            fMeta.setDisplayName(ChatColor.PINK + "Jumppork");

            List<String> fLore = new ArrayList<String>();
            fLore.add(org.bukkit.ChatColor.BLUE + "Jump high like the pig you just murdered!");
            fMeta.setLore(fLore);
            FIRE.setItemMeta(fMeta);

            event.getDrops().add(FIRE);
        }
    }

 @EventHandler
    public void onFireworkUse(PlayerInteractEvent event) {
        if (getArena().equals(getName())) {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.getItemInHand().getType() == Material.FIREWORK) {
                    player.getInventory().removeItem(new ItemStack(Material.FIREWORK, 1));
                    player.setVelocity(new Vector(0, 1.05, 0));
                }
            }
        }
    }
}

