package CodeOrDie;

/**
 * Class Item - an item in an adventure game.
 *
 * This class is part of the "Code or Die" application. 
 * "Code or Die" is a simple, text based adventure game.  
 *
 * An "Item" represents one object in the scenery of the game.  Items
 * can be placed in the different rooms, picked up or used.
 * 
 * @author  Michael Kölling and David J. Barnes, Updated by Gil Dekel
 * @version 2014.03.05
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private double weight;
    private int quantity;
    private boolean edible;

    /**
     * Constructor for objects of class Item.
     * 
     * @param name The name of the item.
     * @param description A short description of the item.
     * @param weight The weight of the item in lbs.
     */
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        quantity = 1;
        edible = false;
    }

    /**
     * Constructor for objects of class Item for when
     * quantity is more than 1.
     * 
     * @param name The name of the item.
     * @param description A short description of the item.
     * @param weight The weight of the item in lbs.
     * @param howMany The quantity of the item.
     */
    public Item(String name, String description,
                double weight, int howMany)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        quantity = howMany;
        edible = false;
    }
    
      /**
     * Constructor for objects of class Item for when
     * the item should be edible.
     * 
     * @param name The name of the item.
     * @param description A short description of the item.
     * @param weight The weight of the item in lbs.
     * @param isEdible Whether or not the item is edible.
     */
    public Item(String name, String description,
                double weight, boolean isEdible)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        quantity = 1;
        edible = isEdible;
    }

    /**
     * Returns the description of the item.
     * 
     * @return A String containing the description of the item.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Returns the weight of the item.
     * 
     * @return The item's weight as an int.
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * Returns the name of the item.
     * 
     * @return A String containing the name of the item.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the quantity of the item.
     * 
     * @return An integer representing the quantity of the item.
     */
    public int getQuantity()
    {
        return quantity;
    }
    
    /**
     * Returns whether or not the item is edible.
     * 
     * @return True if item is edible, false otherwise.
     */
    public boolean isEdible()
    {
        return edible;
    }

    /**
     * Increase item's quantity by specified quantity.
     * 
     * @param howMany The amount to add.
     */
    public void increaseQuantity(int howMany)
    {
        quantity += howMany;
    }

    /**
     * Decrease item's quantity by specified quantity. Returns
     * false if the parameter is larger than the existing
     * quantity.
     * 
     * @param howMany The amount to subtract.
     * @return True if the method was successful, False otherwise.
     */
    public boolean decreaseQuantity(int howMany)
    {
        if(howMany <= quantity) {
            quantity -= howMany;
            return true;
        }
        else
            return false;
    }
}
