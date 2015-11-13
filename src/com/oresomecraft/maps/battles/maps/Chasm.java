package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.inventories.ArmourUtils;
import com.oresomecraft.OresomeBattles.inventories.ItemUtils;
import com.oresomecraft.OresomeBattles.map.Map;
import com.oresomecraft.OresomeBattles.map.annotations.*;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.*;
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
        name = "chasm",
        fullName = "The Chasm",
        creators = {"Heartist", "danshrdr", "Krontezian", "Trilexium"},
        gamemodes = {Gamemode.TDM, Gamemode.CTF}
)
@Region(
        x1 = -26,
        y1 = 140,
        z1 = 59,
        x2 = 119,
        y2 = -8,
        z2 = -181
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        autoSpawnProtection = true,
        timeLock = Map.Time.DAY,
        tdmTime = 10,
        disabledDrops = {Material.STONE_HOE, Material.LEATHER_CHESTPLATE, Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.ARROW, Material.BOW, Material.LEATHER_HELMET, Material.STONE_SWORD}
)
public class Chasm extends BattleMap implements Listener {

    public Chasm() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {

        Location redSpawn = new Location(w, 9, 92, -155);
        Location blueSpawn = new Location(w, 93, 92, 28);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);

        Location redFlag = new Location(w, 48, 88, -132);
        Location blueFlag = new Location(w, 54, 88, 4);
        setCTFFlags(null, redFlag, blueFlag);
        setKoTHMonument(new Location(w, 51, 92, -64));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, 9, 92, -155);
        Location blueSpawn = new Location(w, 93, 92, 28);
        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = Bukkit.getPlayer(p.getName());
        Inventory i = pl.getInventory();

        ItemStack HEALTH = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack STONE_HOE = new ItemStack(Material.STONE_HOE, 1);

        ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);

        ItemUtils.nameItem(STONE_HOE, ChatColor.BLUE + "Ice Hook");
        ArmourUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_HELMET, LEATHER_BOOTS});
        LEATHER_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);

        pl.getInventory().setBoots(LEATHER_BOOTS);
        pl.getInventory().setLeggings(LEATHER_PANTS);
        pl.getInventory().setChestplate(LEATHER_CHESTPLATE);
        pl.getInventory().setHelmet(LEATHER_HELMET);

        i.setItem(0, STONE_SWORD);
        i.setItem(1, BOW);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH);
        i.setItem(2, STONE_HOE);
        i.setItem(10, ARROWS);

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void icePick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Material material = itemStack.getType();
        Action action = event.getAction();
        World world = Bukkit.getWorld(getName());

        if (event.getPlayer().getWorld().getName().equals(getName())) {
            if (material == Material.STONE_HOE) {
                if (action == Action.LEFT_CLICK_BLOCK) {
                    BlockFace blockFace = event.getBlockFace();
                    Block block = event.getClickedBlock();
                    Material blockMaterial = block.getType();
                    if (blockMaterial == Material.STONE || blockMaterial == Material.ICE) {
                        if (blockFace != BlockFace.UP && blockFace != BlockFace.DOWN) {
                            player.setVelocity(new Vector(0, 1, 0));
                            player.setFallDistance(0);
                            world.playEffect(block.getLocation(), Effect.STEP_SOUND, 79);
                        }
                    }
                }
            }
        }
    }

}
