package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@MapConfig(
        name = "arctic",
        fullName = "Arctic",
        creators = {"Dant35tra5t", "CyclonicCJ"},
        gamemodes = {Gamemode.TDM, Gamemode.FFA, Gamemode.KOTH}
)
@Region(
        x1 = 894,
        y1 = 219,
        z1 = -3,
        x2 = 724,
        y2 = 101,
        z2 = 170
)
@Attributes(
        allowBuild = false,
        disabledDrops = {Material.BOW, Material.ARROW, Material.IRON_HELMET, Material.LEATHER_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD, Material.STONE_HOE}
)
public class Arctic extends BattleMap implements Listener {

    public Arctic() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
        Location blueSpawn = new Location(w, 761, 128, -72, 51, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 825, 135, -98, -43, 0));
        redSpawns.add(new Location(w, 834, 132, -84, 60, 0));
        redSpawns.add(new Location(w, 802, 137, -113, -3, 0));
        redSpawns.add(new Location(w, 779, 141, -110, -6, 0));
        redSpawns.add(new Location(w, 804, 137, -111, 0, 0));

        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 784, 136, -56, -150, 0));
        blueSpawns.add(new Location(w, 810, 135, -49, -179, 0));
        blueSpawns.add(new Location(w, 831, 144, -64, -175, 0));
        blueSpawns.add(new Location(w, 795, 155, -26, -154, 0));
        blueSpawns.add(new Location(w, 812, 128, -52, 173, 0));

        setKoTHMonument(new Location(w, 803, 182, -86));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 845, 130, -113, -137, 0);
        Location blueSpawn = new Location(w, 761, 128, -72, 51, 0);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, 795, 155, -28, -172, 0));
        FFASpawns.add(new Location(w, 822, 133, -67, 87, 0));
        FFASpawns.add(new Location(w, 813, 128, -41, -93, 0));
        FFASpawns.add(new Location(w, 773, 121, -110, -58, 0));
        FFASpawns.add(new Location(w, 779, 141, -110, -44, 0));
        FFASpawns.add(new Location(w, 804, 137, -111, 0, 0));
        FFASpawns.add(new Location(w, 820, 129, -68, 129, 0));
        FFASpawns.add(new Location(w, 788, 128, -81, -124, 0));
        FFASpawns.add(new Location(w, 784, 182, -85, -113, 0));
        FFASpawns.add(new Location(w, 761, 132, -133, -27, 0));
        FFASpawns.add(new Location(w, 820, 149, -88, 2, 0));
        FFASpawns.add(new Location(w, 779, 130, -87, -87, 0));
        FFASpawns.add(new Location(w, 832, 133, -67, 133, 0));
        FFASpawns.add(new Location(w, 798, 135, -72, -107, 0));
        FFASpawns.add(new Location(w, 790, 128, -78, 177, 0));
        FFASpawns.add(new Location(w, 830, 152, -142, 19, 0));
        FFASpawns.add(new Location(w, 855, 130, -50, 136, 0));
        FFASpawns.add(new Location(w, 798, 149, -74, -134, 0));
        FFASpawns.add(new Location(w, 789, 123, -94, 133, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack SNOWBALL = new ItemStack(Material.SNOW_BALL, 4);
        ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);
        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        IRON_BOOTS.addEnchantment(Enchantment.PROTECTION_FALL, 3);
        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE});
        ItemUtils.nameItem(STONE_HOE, ChatColor.BLUE + "Ice hook");

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(LEATHER_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STONE_HOE);
        i.setItem(8, SNOWBALL);
        i.setItem(5, EXP);
        i.setItem(4, HEALTH_POTION);
        i.setItem(3, STEAK);
        i.setItem(9, ARROWS);


    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void icePick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Material material = itemStack.getType();
        Action action = event.getAction();

        if (player.getWorld().getName().equals(getName())) {

            if (material == Material.STONE_HOE) {

                if (action == Action.LEFT_CLICK_BLOCK) {

                    BlockFace blockFace = event.getBlockFace();
                    Block block = event.getClickedBlock();
                    Material blockMaterial = block.getType();

                    if (blockMaterial == Material.STONE || blockMaterial == Material.ICE) {

                        if (blockFace != BlockFace.UP && blockFace != BlockFace.DOWN) {

                            player.setVelocity(new Vector(0, 1, 0));
                            player.setFallDistance(0);
                            player.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, 79);

                        }

                    }

                }
            }

        }

    }

}
