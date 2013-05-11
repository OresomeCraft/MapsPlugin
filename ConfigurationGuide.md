OresomeBattles map configuration guide
=

## Where to start?
// This is still a work in progress! More things will be added very soon!

Well, you've found your way to our maps repository, so there's a good start.

### What is this repo?
This repo stores our OresomeBattles-Maps plugin. Currently there are two pieces to OresomeBattles. OresomeBattles is the core plugin that handles majority of the functions, and this plugin specifically handles the maps.

### Okay, that's cool. How can I configure my own map?
All OresomeBattles map configurations are done in Java. This allows much more flexibility and customisation for each map.
Most people will see the word "Java" and think "OMG! CODE! COODE!", but trust me, it's really simple.
Below will be a step by step guide of how to configure it, and then even submit it to us.

## Down to business, Configuring the map

Follow the step by step guide below to sucessfully configure your own OresomeBattles map!

[HERE]:https://github.com/OresomeCraft/OresomeBattles-Maps/blob/master/Template.java
First off, copy the Template.java file, you can get that from [HERE][]
### Basic map details

The code below only needs to be slightly channged. Simple change where it says "Arctic" to the name of your map.
```java
public class Arctic extends BattleMap implements MapInterface, Listener {

    OresomeBattlesMaps plugin;
    public Arctic(OresomeBattlesMaps pl) {
        super(pl);
        plugin = pl;
    }
```
Below defines the basic details of this map

```java
    String name = "arctic";  // Shorten & lowercase name of the map.
    String fullName = "Arctic"; // Full captilised map name.
    String creators = "Dant35tra5t, Derpherp"; // Map creators.
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};
    //Map download link: http://some-link-here.com/map.zip  // Download link to map.
```

As you can see you're able to set which game modes the map can play. Current available options are: Gamemode.TDM, Gamemode.FFA and Gamemode.INFECTION

### What about this ReadyMap() and these ArrayLists and um, the imports?
These are internal things required to make the map function. DO NOT change these.

### Setting spawn points
Each team has their own defined spawn points. If more than one is defined, the plugin will automatically spawn players between each of them. 

#### TDM spawn points

```java
    public void readyTDMSpawns() { // Method to prepare TDM spawns.
        World w = Bukkit.getServer().getWorld(name); // Get world name. (Don't need to change)

        Location redSpawn = new Location(w, X, Y, Z, YAW, 0); // Define primary red spawn point

        Location blueSpawn = new Location(w, X, Y, Z, YAW, 0); // Define primary blue spawn point

        // Add red spawn points.
        redSpawns.add(redSpawn); // Make sure we add the primary spawn to the spawns list!
        redSpawns.add(new Location(w, X, Y, Z, YAW, 0);
        redSpawns.add(new Location(w, -141, 66, -1142, -152, 0));

        // Add blue spawn points.
        blueSpawns.add(blueSpawn); // Make sure we add the primary spawn to the spawns list!
        blueSpawns.add(new Location(w, X, Y, Z, YAW, 0);
        blueSpawns.add(new Location(w, -127, 70, -1158, -162, 0));

        // Adds spawns. (Don't modify)
        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }
```
X - X coordinate

Y - Y coordinate

Z - Z coordinate

YAW - Direction. (The number next to the compass direction in the F3 menu)

Simply replace these variables with the actual values of the coordinates. You may have as many or as little TDM spawn points as you wish, but there of course must be at least one. Easy, right?

#### FFA spawn points

```java
    public void readyFFASpawns() { // Method to prepare TDM spawns.

        World w = Bukkit.getServer().getWorld(name); // Get world name. (Don't need to change)

        // Set Free for all spawn points.
        FFASpawns.add(new Location(w, X, Y, Z, YAW, 0);
        FFASpawns.add(new Location(w, X, Y, Z, YAW, 0);
        FFASpawns.add(new Location(w, X, Y, Z, YAW, 0);
        FFASpawns.add(new Location(w, X, Y, Z, YAW, 0);
        FFASpawns.add(new Location(w, X, Y, Z, YAW, 0);
        FFASpawns.add(new Location(w, -125, 71, -1132, -95, 0));
        FFASpawns.add(new Location(w, -101, 71, -1159, -51, 0));
        FFASpawns.add(new Location(w, -109, 71, -1162, -132, 0));
        FFASpawns.add(new Location(w, -97, 66, -1171, 156, 0));
        FFASpawns.add(new Location(w, -83, 71, -1182, 41, 0));
        FFASpawns.add(new Location(w, -78, 71, -1188, -88, 0));
        FFASpawns.add(new Location(w, -43, 71, -1168, 124, 0));
        FFASpawns.add(new Location(w, -48, 71, -1197, 90, 0));
        FFASpawns.add(new Location(w, -91, 71, -1207, -89, 0));
        FFASpawns.add(new Location(w, -121, 71, -1190, 121, 0));
        FFASpawns.add(new Location(w, -139, 71, -1192, 89, 0));
        FFASpawns.add(new Location(w, -114, 66, -1191, 43, 0));
        FFASpawns.add(new Location(w, -77, 71, -1169, -90, 0));
        FFASpawns.add(new Location(w, -58, 71, -1147, 156, 0));
        FFASpawns.add(new Location(w, -91, 71, -1140, 141, 0));

        // Add spawns to list. (Don't change!)
        setFFASpawns(name, FFASpawns);
    }
```
The same goes for adding FFA spawns, however these spawns are used for all players. This is practically the same as configuring the TDM spawn points, just replace the variables as follows.

###Inventories
Out of all things here inventories are probably the most complex, but they're actually super simple!

```java

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        // Don't change this stuff!
        String par = event.getMessage();
        Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (par.equalsIgnoreCase(name)) {
            clearInv(p);

            // Define items. (This is fairly straight forward right?)
            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
            ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
            ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
            ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
            ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

            p.getInventory().setBoots(IRON_BOOTS); // Set boots
            p.getInventory().setLeggings(IRON_PANTS); // Set pants
            p.getInventory().setChestplate(IRON_CHESTPLATE); // Set chest plate
            p.getInventory().setHelmet(IRON_HELMET); // Set helmet

            // Add items into inventory bar.
            // The numer being the slot number. (Remember: Slot 1 is actually 0, Slot 2 is 1, etc)
            // Second arg is the item being added.
            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, ARROWS);

        }
    }
```
Now the thing that's confusing with Inventories is actually defining the item, especially since not all items are named as you think they would be. Here's a breakdown of the code to actually define the item:
```java
   ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
```
ItemStack: Showing that we're defining an item(s).

IRON_SWORD: Name of the item's variable.

new ItemStack: Again, a new Item, leave that as is.

Material.IRON_SWORD: Getting the actual item.

1: Amount of that item to give.

### Defining the region
```java
    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -207; // X location
    public int y1 = 52; // Y location
    public int z1 = -1220; // Z location
    
    //Bottom right corner.
    public int x2 = -38;  // X location
    public int y2 = 112; // Y location
    public int z2 = -1125; // Z location
```
This is very important, it allows for the plugin to make specific checks and make sure things are only happening inside this specific map. Simple get the block locations from the top left and the bottom right corner.

### What about these other "methods" like clearSpawns() and contains() ?
These don't need to be changed, just leave them as is. However, they are both important and must remain there!

### // End of basic map configuration.
No really, that's it. The map is configured! (To a basic level anyway, usually this is sufficient, though.)

## Advanced configurion
There are many more things you can do with these map configurations, this is one of the great benefits of having it coded in Java. Please note that these examples will have less documentation than the configuration explained above.

### Disable block breaking
```java
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {
        Block b = event.getBlock();
        Location loc = b.getLocation();
        if (loc.getWorld().getName().equals(name)) {
            event.setCancelled(true);
        }
    }
```
This disables all block breaking in a map.
Make sure you ```import org.bukkit.event.block.BlockBreakEvent;``` and ```import org.bukkit.Block;``` if you want to use this!

### Disable breaking of certain blocks
```java
    @EventHandler(priority = EventPriority.NORMAL)
    public void protection(BlockBreakEvent event) {
        Block b = event.getBlock();
        int mat = b.getTypeId();
        Location loc = b.getLocation();
        if (loc.getWorld().getName().equals(name)) {
            if (contains(loc, x1, x2, y1, y2, z1, z2) == true) {

                if (mat == 43 || mat == 44 || mat == 35 || mat == 42
                        || mat == 49 || mat == 123 || mat == 69 || mat == 124) {

                    event.setCancelled(true);
                }
            }
        }
    }
```
This will disable the breaking block of these specific blocks.
Make sure you ```import org.bukkit.event.block.BlockBreakEvent;``` and ```import org.bukkit.Block;``` if you want to use this!

Key:

mat - Get the block being hit

|| - Or operator

Again, these topics are more suited for people who have more of a clue what they're doing. If you would like to have this but don't know how to do it yourself, just leave a comment (using // <Text here> ) in the class file explaining what blocks you don't want them to be able to break.

### Naming items
This is a pretty cool feature but again is not for the basic people. This is mostly useful if you implement your own custom effects.
```java
ItemMeta egg_hypno = EGG_HYPNO.getItemMeta();
egg_hypno.setDisplayName(ChatColor.BLUE + "Flash bang grenade");
EGG_HYPNO.setItemMeta(egg_hypno);
```
I won't go into too much here, but basically we're defining the item meta for the item, setting the name of it, then applying this meta to the item itself. Pretty straight forward. (Make sure this code is before where the inventory is applied!)

Make sure you ```import org.bukkit.inventory.meta.ItemMeta;``` if you want to use this.

### Creating particles where a projectile hits
This can help create some oresome visual effects. I highly recommend people experiment with this if they're in the advanced category of people.
```java
    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (Battles.activeArena.get(0).equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 8);
            }
        }
    }
```
You can make this play any sort of particles, but you'll need to do your own research to find them. The example above makes a block breaking particle effect. The number defined at the end is the ID of the block, so in this case water. If you wanted a golden explosion, you would change that number to the ID of a gold block.

You may also notice the if statement that makes sure this is an arrow, you could change this to say snowballs, it's up to you!
Make sure you  ```import org.bukkit.Effect;```, ```import org.bukkit.event.entity.ProjectileHitEvent;```, ```import org.bukkit.entity.Entity; ``` and ```import org.bukkit.entity.Arrow; ``` if you want to use this.



Written by Zach De Koning (Zachoz | https://github.com/Zachoz)

Last updated: 11/05/2013
