package CodeOrDie;

import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Code or Die" application. 
 * "Code or Die" is a simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes, Updated by Gil Dekel
 * @version 2014.03.05
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**Return a long description of this room, of the form:
     *      You are in the kitchen.
     *      Exits: north west
     *
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getAllItems() +
        "\n" + getExitString();
    }

    /**
     * A method to return the room that is located at the
     * specified direction.
     * 
     * @param The inquierd direction
     * @return The room object that is located at the specified
     * direction.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * Return a description of the room’s exits,
     * for example, “Exits: north  west”.
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys)
            returnString += " " + exit;
        return returnString;
    }

    /**
     * Adds an item to the room's list of items.
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
     * Adds an item to the room's list of items.
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
     * Adds an item to the room's list of items.
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
     * Returns the room's inventory collection.
     * 
     * @return The item collection of the room.
     */
    public HashMap<String, Item> getItemsMap()
    {
        return items;
    }

    /**
     * Returns a String with the list of objects in the room.
     * 
     * @return A String with the list of objects in the room.
     */
    public String getAllItems()
    {
        if(items.isEmpty())
            return "There are no noticable items in this room.";
        else{
            String returnString = "This room contains the following items: \n";
            Set<String> keys = items.keySet();
            for(String itemName : keys) {
                returnString += items.get(itemName).getName() + " ";
            }
            return returnString;
        }
    }
}
