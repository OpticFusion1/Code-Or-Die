package CodeOrDie;

/**
 * This class is part of the "Code or Die" application. 
 * "Code or Die" is a simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes, Updated by Gil Dekel
 * @version 2014.03.05
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "go", "quit", "help", "look", "use", "back",
            "take", "drop", "inventory", "eat"
        };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Return a list of all possible commands,
     * for example, “Your command words are: go, help, quit”.
     * @return A description of valid commands.
     */
    public String getCommandString()
    {
        String returnString = "Your command words are: \n";
        for(String command : validCommands) {
            returnString += command + " ";
        }
        return returnString;
    }
}
