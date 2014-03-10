package CodeOrDie;

import java.util.Scanner;

/**
 * This class is part of the "Code or Die" application. 
 * "Code or Die" is a simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes, Updated by Gil Dekel
 * @version 2014.03.05
 */
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public String getAllCommands()
    {
        return commands.getCommandString();
    }

    /**
     * @return The username of the player.
     */
    public String getUsername()
    {
        System.out.println("+-----------------------------------------------------------+");        
        System.out.print("Please enter your username: ");
        String inputLine = reader.nextLine().trim();
        System.out.println("+-----------------------------------------------------------+");
        return inputLine;
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() 
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine().toLowerCase();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next().trim();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next().trim();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }
}
