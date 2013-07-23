#### This configuration guide is limited! You should look at the [wiki]:https://github.com/OresomeCraft/OresomeBattles-Maps/wiki

## Where to start?

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
public class Arctic extends BattleMap implements, IBattleMap Listener {

    public Arctic() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }
```
Below defines the basic details of this map

```java
    String name = "arctic";  // Shorten & lowercase name of the map.
    String fullName = "Arctic"; // Full captilised map name.
    String creators = "Dant35tra5t, Derpherp"; // Map creators.
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION, Gamemode.CTF};
    // Map download link: http://some-link-here.com/map.zip  // Download link to map.
```

As you can see you're able to set which game modes the map can play. Current available options are: Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION and Gamemode.CTF

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

        // CTF stuff - Optional, put this here if you want CTF on your map!
        Location redFlag = new Location(w, 0, 1, -10, 0, 0); // Location of the red team's flag
        Location blueFlag = new Location(w, 0, 1, 10, 0, 0); // Location of the blue team's flag
        setCTFFlags(name, redFlag, blueFlag); // Add the flags!
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

        // Add spawns to list. (Don't change!)
        setFFASpawns(name, FFASpawns);
    }
```
The same goes for adding FFA spawns, however these spawns are used for all players. This is practically the same as configuring the TDM spawn points, just replace the variables as follows.

FFASpawns is also where the Infection spawns are defined. FFA and Infection share the same spawn points.

**Note:** ALL maps must have the FFA method with at least one spawn defined. This is what OresomeBattles uses to teleport spectators!

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

### End of basic map configuration.
No really, that's it. The map is configured! (To a basic level anyway, usually this is sufficient, though.)

### Advanced configuration

[click here]:https://github.com/OresomeCraft/OresomeBattles-Maps/wiki/Advanced-Map-Options
To do more advanced configuration for your battle map, check out our 'Advanced Configuration' guide. To get there [click here][]