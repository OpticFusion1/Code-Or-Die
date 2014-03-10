package CodeOrDie;

/**
 *  This class is the main class of the "Code or Die" application.
 *  "Code or Die" is a very simple, text based adventure game.
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes, Updated by Gil Dekel
 * @version 2014.03.05
 */

public class Game
{
    private Parser parser;
    private Player player;

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
        /**
         * Create the game and initialise its internal map.
         */
        public Game()
        {
            parser = new Parser();
            player = new Player(parser.getUsername());
            createRooms();
        }


        /**
         * Create all the rooms and link their exits together.
         */
    private void createRooms()
    {
        Room mainLobby, cafeteria, odditoriumReception, odditorium, staffEntrance,
                securityRoom, mainOffice, cubicle, bossOffice,
                geneSplicingOffice, geneSplicingLabA, geneSplicingLabB,
                geneSplicingStorage, geneMappingOffice, geneMappingLabA,
                geneMappingLabB, geneMappingStorage;

        // create the rooms
        mainLobby = new Room("in the main lobby of UniLink Genetics.");
        cafeteria = new Room("in the cafeteria.");
        odditoriumReception = new Room("in the odditorium reception.");
        odditorium = new Room("in the odditorium hall.");
        staffEntrance = new Room("in the staff entrance.");
        securityRoom = new Room("in the security room");
        mainOffice = new Room("in the main office. Your cubicle is located here.");
        cubicle = new Room("in your cubicle. You can access your computer from here.");
        bossOffice = new Room("in your boss' office.");
        geneSplicingOffice = new Room("in the Gene Splicing sector reception. ");
        geneSplicingLabA = new Room("in Gene Splicing Lab A.");
        geneSplicingLabB = new Room("in Gene Splicing Lab B.");
        geneSplicingStorage = new Room("in the storage unit of Gene Splicing sector.");
        geneMappingOffice = new Room("in the Gene Mapping sector reception. ");
        geneMappingLabA = new Room("in Gene Mapping Lab A.");
        geneMappingLabB = new Room("in Gene Mapping Lab B.");
        geneMappingStorage = new Room("in the storage unit of Gene Mapping sector.");

        // Place items in rooms
        cubicle.newItem("Computer", "Your work station. \n" +
                "\t     It's a desktop computer. " +
                "No, you cannot pick it up.", player.getMaxLiftWeight()+1);
        cubicle.newItem("Cellphone", "It's your cell phone.", 1.1);
        cubicle.newItem("Keys", "Your keys.", 0.2);
        cubicle.newItem("MemoStick", "It's your work issued memory stick.", 0.1);
        geneSplicingStorage.newItem("Cookie", "This cookie looks so good! However, you can't help" +
                " but wonder... \n" +
                "\t Of ALL the places in UniLink Genetics..." +
                " why was this cookie here?", 0.1, true);

        // initialise room exits
        // Level 1
        mainLobby.setExits("north", staffEntrance);
        mainLobby.setExits("east", cafeteria);
        mainLobby.setExits("west", odditoriumReception);
        cafeteria.setExits("west", mainLobby);
        odditoriumReception.setExits("north", odditorium);
        odditoriumReception.setExits("east", mainLobby);
        odditorium.setExits("south", odditoriumReception);
        staffEntrance.setExits("north", securityRoom);
        staffEntrance.setExits("up", mainOffice);
        staffEntrance.setExits("south", mainLobby);
        securityRoom.setExits("south", staffEntrance);

        // Level 2
        mainOffice.setExits("cubicle", cubicle);
        mainOffice.setExits("north", bossOffice);
        mainOffice.setExits("down", staffEntrance);
        mainOffice.setExits("south", geneMappingOffice);
        mainOffice.setExits("west", geneSplicingOffice);
        cubicle.setExits("out", mainOffice);
        bossOffice.setExits("south", mainOffice);
        geneSplicingOffice.setExits("east", mainOffice);
        geneSplicingOffice.setExits("south", geneSplicingLabB);
        geneSplicingOffice.setExits("west", geneSplicingLabA);
        geneSplicingLabA.setExits("east", geneSplicingOffice);
        geneSplicingLabA.setExits("south", geneSplicingStorage);
        geneSplicingLabB.setExits("north", geneSplicingOffice);
        geneSplicingLabB.setExits("west", geneSplicingStorage);
        geneSplicingStorage.setExits("north", geneSplicingLabA);
        geneSplicingStorage.setExits("east", geneSplicingLabB);
        geneMappingOffice.setExits("north", mainOffice);
        geneMappingOffice.setExits("south", geneMappingLabB);
        geneMappingOffice.setExits("west", geneMappingLabA);
        geneMappingLabA.setExits("east", geneMappingOffice);
        geneMappingLabA.setExits("south", geneMappingStorage);
        geneMappingLabB.setExits("north", geneMappingOffice);
        geneMappingLabB.setExits("west", geneMappingStorage);
        geneMappingStorage.setExits("north", geneMappingLabA);
        geneMappingStorage.setExits("east", geneMappingLabB);
        // Starts the game at the cubicle.
        player.setCurrentRoom(cubicle);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing " + player.getUsername() +
                ". \n" + "Good bye.");
        player.previousRoomsList().clear();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome " + player.getUsername() + ", to Code or Die!");
        System.out.println("Code or Die is a text based adventure game \n" +
                "where you take the role of Sofia Calahan, \n" +
                "a computer hacker/geek employed at the headquarters of \n" +
                "UniLink Genetics.");
        System.out.println("You begin your quest in your personal cubicle, \n" +
                "infront of your computer. While you work on some code, \n" +
                "you notice you have one unread email.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Print the current location information.
     */
    private void printLocationInfo()
    {
        System.out.println("+-----------------------------------------------------------+");
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println("+-----------------------------------------------------------+");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help": printHelp(); break;
            case "go": goRoom(command); break;
            case "back": goBack(); break;
            case "look": look(); break;
            case "use": use(); break;
            case "take": pickUp(command); break;
            case "drop": drop(command); break;
            case "inventory": printInventory(); break;
            case "eat": eat(command); break;
            case "quit": wantToQuit = quit(command); break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("You are Sofia Calahan, a computer programmer/hacker \n" +
                "employed by UniLink Genetics. You are a part of a \n" +
                "team in charge of creating customisable applications that \n" +
                "aid in the mapping and manipulation of genetic codes.");
        System.out.println("In the past 5 years, you have taken part in the \n" +
                "designing process and implementation of all the \n" +
                "different software and applications in use by UniLink \n" +
                "Genetics corp.");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println(parser.getAllCommands());
        printLocationInfo();
    }

    /**
     * Prints information about your surroundings.
     */
    private void look()
    {
        printLocationInfo();
    }

    /**
     * Use an item. This method is not complete.
     */
    private void use()
    {
        System.out.println("There is nothing to use around here...");
    }

    /**
     * Prints the list of items the character currently carrys.
     */
    private void printInventory()
    {
        System.out.println(player.getAllItems());
    }

    /**
     * Return to the previous room.
     */
    private void goBack()
    {
        //Check if there are any previous locations.
        //Print an error message if not.
        if(player.previousRoomsList().empty()) {
            System.out.println("You haven't been anywhere before this location.");
        }
        else {
            player.setCurrentRoom(player.getPreviousRoom());
            printLocationInfo();
        }
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        //Set nextRoom to hold the room at the specifed exit
        //from the last command.
        Room nextRoom = player.getCurrentRoom().getExit(command.getSecondWord());
        if (nextRoom == null) {
            System.out.println("There is no exit there!");
        }
        else {
            player.pushPreviousRoom(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }

    /**
     * Try to pick up an item. If the item exist and does not
     * exceeds the maximum weight limit, add it to the player's
     * inventory. Otherwise print an error message.
     *
     * @param command The command words form the user.
     */
    private void pickUp(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }

        Room currentRoom = player.getCurrentRoom();
        //Check if item is found around the room.
        if (currentRoom.getItemsMap().get(command.getSecondWord()) == null) {
            System.out.println("There is no such item here!");
        }
        //Check if item can be lifted at all.
        else if(currentRoom.getItemsMap().get(command.getSecondWord()).getWeight() >
                player.getMaxLiftWeight()) {
            System.out.println("You can't lift that! It's too heavy for you.");
        }
        //Check if adding this item won't exceed the maximum weight load
        //for the character.
        else if(currentRoom.getItemsMap().get(command.getSecondWord()).getWeight() +
                player.getCurrentWeightload() > player.getMaxLiftWeight()) {
            System.out.println("You cannot carry that much. It's time you get rid "
                    + "of some that junk anyway.");
        }
        //Add the items to the character's inventory and remove it from the room.
        //Print information message with the item that was picked up.
        else {
            player.takeItem(currentRoom.giveItem(currentRoom.getItemsMap().get(command.getSecondWord())));
            System.out.println("You just picked up " +
                    player.getInventory().get(command.getSecondWord()).getName()
                    + ".");
        }
    }

    /**
     * Try to drop an item. If the item exist, remove it
     * from the player's inventory. Otherwise print an error message.
     *
     * @param command The command words form the user.
     */
    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }

        Room currentRoom = player.getCurrentRoom();
        //Check if the character have the item in her inventory.
        //If the items is not there, print an error message.
        if (player.getInventory().get(command.getSecondWord()) == null) {
            System.out.println("There is no such item in your inventory.");
        }
        //Remove the item from the character's inventory and add it to the room.
        //Print information message with the item that was dropped.
        else {
            currentRoom.takeItem(player.giveItem(player.getInventory().get(command.getSecondWord())));
            System.out.println("You just droped " +
                    currentRoom.getItemsMap().get(command.getSecondWord()).getName()
                    + ".");
        }
    }

    /**
     * The character consume an eadible item.
     *
     * @param command The command words form the user.
     */
    private void eat(Command command)
    {
        boolean canEat = true;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to eat...
            System.out.println("Eat what?");
            return;
        }

        // If the item exists in the character's inventory
        if(player.getInventory().containsKey(command.getSecondWord())) {
            //check if edible, else return an error message.
            if(player.getInventory().get(command.getSecondWord()).isEdible()) {
                player.giveItem(player.getInventory().get(command.getSecondWord()));
                player.raisMaxLiftWeight();
                System.out.println("Hmmmm... yum! You think we can find more?");
            }
            else
                canEat = false;
        }
        //If the item is around the room in which the character is currently at
        else if(player.getCurrentRoom().getItemsMap().containsKey(command.getSecondWord())) {
            //check if edible, else return an error message.
            if(player.getCurrentRoom().getItemsMap().get(command.getSecondWord()).isEdible()) {
                player.getCurrentRoom().giveItem(player.getCurrentRoom().getItemsMap().get(command.getSecondWord()));
                player.raisMaxLiftWeight();
                System.out.println("Hmmmm... yum! You think we can find more?");
            }
            else canEat = false;
        }
        //If item is not present in either the character's
        //inventory or around the room, print an error message.
        else {
            System.out.println("There is no such a thing to eat around here.\n" +
                    "You'll just have to stay hungry.");
        }
        //if item is not edible, print error message.
        if(!canEat)
            System.out.println("Are you crazy?! You can't eat that!");
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

