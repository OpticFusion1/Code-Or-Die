package CodeOrDie;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.Collection;

/**
 * Class Player - a player in an adventure game.
 *
 * This class is part of the "Code or Die" application. 
 * "Code or Die" is a simple, text based adventure game.  
 *
 * A "Player" represents a user's character in the scenery of the game.
 * It holds the user's real name and the inventory of his character.
 * 
 * @author  Michael Kölling and David J. Barnes, Updated by Gil Dekel
 * @version 2014.03.05
 */
public class Player 
{
    private String username;
    private HashMap<String, Item> items;
    private Room currentRoom;
    private Stack<Room> previousRooms;
    private double maxLiftWeight;

    /**
     * Create a player with a name. Initially, the player has
     * a basic set of items, such as a cell phone, keys, etc.
     * @param name The player's name.
     */
    public Player(String name) 
    {
        username = name;
        maxLiftWeight = 130;
        items = new HashMap<String, Item>();
        previousRooms = new Stack<Room>();
    }

    /**
     * Returns the username of the player.
     * 
     * @return The username of the player.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Returns the character's maximum lift weight.
     * 
     * @return The maximum weight the character can lift.
     */
    public double getMaxLiftWeight()
    {
        return maxLiftWeight;
    }
    
    /**
     * Raises the character's maximum lift weight by 20 lbs.
     */
    public void raisMaxLiftWeight()
    {
        maxLiftWeight += 20;
    }
    
    /**
     * Returns the room the character is currently in.
     * 
     * @return The room the character is currently in.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Changes the room the character is currently in.
     * 
     * @param room The room the character should move into.
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Returns the stack list that keeps the character's
     * movement through the different rooms.
     * 
     * @return The stack list that keeps the character's
     * movement through the different rooms.
     */
    public Stack<Room> previousRoomsList()
    {
        return previousRooms;
    }
    
    /**
     * Pop and return the previous room for the Stack.
     * 
     * @return The previous room visited.
     */
    public Room getPreviousRoom()
    {
        return previousRooms.pop();
    }
    
    /**
     * Push the room the character just left to the Stack.
     * 
     * @param room The room to push in to the Stack.
     */
    public void pushPreviousRoom(Room room)
    {
        previousRooms.push(room);
    }
    
    /**
     * Adds an item to the character's list of items.
     * 
     * @param name The name of the item.
     * @param description A short description of the item.
     * @param weight The weight of the item.
     */
    public void newItem(String name, String description, double weight)
    {
        items.put(name.toLowerCase(), new Item(name, description, weight));
    }
    
    /**
     * Adds an item to the character's list of items.
     * 
     * @param name The name of the item.
     * @param description A short description of the item.
     * @param weight The weight of the item.
     * @param isEdible Whether or not the item is edible
     */
    public void newItem(String name, String description, 
                        double weight, boolean isEdible)
    {
        items.put(name.toLowerCase(), new Item(name, description, weight, isEdible));
    }

    /**
     * Adds an item to the character's list of items.
     * 
     * @param item The item to recieve.
     */
    public void takeItem(Item item)
    {
        items.put(item.getName().toLowerCase(), item);
    }

    /**
     * Removess an item from the room's list of items
     * and return it to the requester.
     * 
     * @param item The requested item.
     * @return The item to be given to the user.
     */
    public Item giveItem(Item item)
    {
        Item givenItem = item;
        items.remove(item.getName().toLowerCase());
        return givenItem;
    }
    
    /**
     * Returns the players inventory collection.
     * 
     * @return The item collection of the players.
     */
    public HashMap<String, Item> getInventory()
    {
        return items;
    }
    
    /**
     * Returns the current weight load of the character's inventory
     * 
     * @return Current weight load of the character's inventory
     */
    public double getCurrentWeightload()
    {
        double returnWeight = 0;
        Collection<Item> allItems = items.values();
        for(Item weight : allItems)
            returnWeight += weight.getWeight();
        return returnWeight;
    }
    
    /**
     * Returns a String with the list of objects the character
     * currently holds.
     * 
     * @return A String with the list of objects character
     * currently holds.
     */
    public String getAllItems()
    {
        if(items.isEmpty())
            return "Your inventory is currently empty.";
        else{
            System.out.println("+------------------------------------");
            String returnString = "| Your inventory: \n"
                                  + "+------------------------------------" +
                                  "\n" + "| ";
            Set<String> keys = items.keySet();
            for(String itemName : keys) {
                returnString += items.get(itemName).getName() + " - " +
                                items.get(itemName).getDescription() + "\n"+ "| ";
            }
            return returnString + "\n" + "| Total weight: " + 
            String.format("%.2f", getCurrentWeightload()) 
            + "lbs." + "\n" + "| Maximum you can carry: " + 
            getMaxLiftWeight() + "lbs. \n" + "+------------------------------------";
        }
    }
}
