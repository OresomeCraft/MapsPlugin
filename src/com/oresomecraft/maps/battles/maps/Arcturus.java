package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@MapConfig(
        name = "arcturus",
        fullName = "Arcturus",
        creators = {"PyroPolar"},
        gamemodes = {Gamemode.INFECTION, Gamemode.FFA, Gamemode.LTS, Gamemode.LMS, Gamemode.TDM, Gamemode.KOTH, Gamemode.CTF}
)
@Region(
        x1 = 160,
        y1 = 123,
        z1 = 755,
        x2 = 362,
        y2 = 1,
        z2 = 554
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 10,
        disabledDrops = {Material.BOW, Material.IRON_SWORD, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.LEATHER_CHESTPLATE, Material.DIAMOND_HOE}
)
public class Arcturus extends BattleMap {

    public Arcturus() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 262, 18, 566, 1, 0));

        blueSpawns.add(new Location(w, 261, 18, 743, 179, 0));

        Location redFlag = new Location(w, 261, 39, 601);
        Location blueFlag = new Location(w, 259, 39, 708);
        setCTFFlags(getName(), redFlag, blueFlag);

        setKoTHMonument(new Location(w, 261, 55, 653));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 238, 56, 721, -135, 0));
        FFASpawns.add(new Location(w, 290, 24, 687, 135, 0));
        FFASpawns.add(new Location(w, 289, 66, 629, 45, 0));
        FFASpawns.add(new Location(w, 329, 63, 626, 90, 0));
        FFASpawns.add(new Location(w, 282, 45, 577, 45, 0));
        FFASpawns.add(new Location(w, 247, 55, 617, -45, 0));
        FFASpawns.add(new Location(w, 216, 24, 618, -45, 0));
        FFASpawns.add(new Location(w, 190, 72, 653, -90, 0));
        FFASpawns.add(new Location(w, 207, 27, 653, -90, 0));
        FFASpawns.add(new Location(w, 224, 90, 685, -35, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack BOAT = new ItemStack(Material.BOAT, 1);
        ItemStack DIAMOND_HOE = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemStack ENDER_PEARL = new ItemStack(Material.ENDER_PEARL, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 4);

        IRON_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 3);
        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        ItemUtils.nameItem(DIAMOND_HOE, ChatColor.YELLOW + "Sandstone Hook");

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, DIAMOND_HOE);
        i.setItem(3, BOAT);
        i.setItem(4, ENDER_PEARL);
        i.setItem(5, STEAK);
        i.setItem(6, HEALTH_POTION);
        i.setItem(9, ARROWS);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void sandstonePick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Material material = itemStack.getType();
        Action action = event.getAction();

        if (player.getWorld().getName().equals(getName())) {

            if (material == Material.DIAMOND_HOE) {

                if (action == Action.LEFT_CLICK_BLOCK) {

                    BlockFace blockFace = event.getBlockFace();
                    Block block = event.getClickedBlock();
                    Material blockMaterial = block.getType();

                    if (blockMaterial == Material.DIAMOND || blockMaterial == Material.SANDSTONE) {

                        if (blockFace != BlockFace.UP && blockFace != BlockFace.DOWN) {

                            player.setVelocity(new Vector(0, 1.5, 0));
                            player.setFallDistance(0);
                            player.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 79);

                        }
                    }
                }
            }
        }
    }
}