/**
 * Driver class for the Monster Collector Game that handles the game menu and actions for each item movement.
 * Adding refinements that utilize the trainer class to record all information about the trainer to allow for
 * the removal of static variables from the previous version of the Driver class.
 * 
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 2.0
 */

import java.util.*;

public class Driver {
    private static final int MENU_BATTLE=1;
    private static final int MENU_TRAINER=2;
    private static final int MENU_HEAL=3;
    private static final int MENU_SHOP=4;
    private static final int MENU_QUIT = 5;
    private static final double HEALING_COST = 10.0;
    private static final double BATTLE_WINNINGS = 5.0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Copyright 2025 Howard Community College\n");

        // User enters their name, name is now set to the user input and money is set to 0
        // + initialize trainer's team
        System.out.print("Please enter your name, trainer: ");
        String name = scan.nextLine();

        // User input to select their first monster
        System.out.println("Pick a starter monster from the list below:");
        for (int i = 0; i < GameResources.ALL_MONSTERS.length; i++) {
            System.out.println((i + 1) + ". " + GameResources.ALL_MONSTERS[i]);
        }
        int selection = getValidatedSelection(scan, 1, GameResources.ALL_MONSTERS.length);
        String chosenMonster = GameResources.ALL_MONSTERS[selection - 1];
        Trainer trainer = new Trainer(name, 10.0, chosenMonster);
        System.out.println("A wild " + chosenMonster + " appeared!");

        int menuChoice;
        do {
            printMenu(trainer);
            menuChoice = getValidatedSelection(scan, MENU_BATTLE, MENU_QUIT);
            switch (menuChoice) {
                case MENU_BATTLE:
                    menuBattle(scan, trainer);
                    break;
                case MENU_TRAINER:
                    menuTrainer(scan, trainer);
                    break;
                case MENU_HEAL:
                    menuHeal(scan, trainer);
                    break;
                case MENU_SHOP:
                    menuShop(scan, trainer);
                    break;
                case MENU_QUIT:
                    System.out.println("Thanks for playing, " + name + "!");
                    break;
                default:
                    // Should not occur due to input validation.
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (menuChoice != MENU_QUIT);
        
        scan.close();
    }

    /**
     * Prompts the user for a selection and validates that the input is within the range from min-max
     * 
     * @param input for reading the user input
     * @param min minimum valid integer
     * @param max maximum valid integer
     * @return an integer that is confirmed to be between the allotted range 
     */
    private static int getValidatedSelection(Scanner input, int min, int max){
        int selection = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter your selection: ");
            if (input.hasNextInt()) {
                selection = input.nextInt();
                input.nextLine(); // consume newline
                if (selection >= min && selection <= max) {
                    valid = true;
                } else {
                    System.out.println("\nError: Selection must be between " + min + " and " + max + ", inclusively.");
                }
            } else { // Incase user inputs something other than an integer
                System.out.println("\nError: Selection must be between " + min + " and " + max + ", inclusively.");
                input.nextLine(); 
            }
        }
        return selection;
    }

    /**
     * Prints the game’s main menu exactly as specified in the lab instructions.
     * 
     * @param trainer The trainer object containing the trainer's name
     */
    private static void printMenu(Trainer trainer) {
        System.out.println("\nWelcome to the CMSY-166 monster collector game, " + trainer.getName() + "!");
        System.out.println(MENU_BATTLE + ". Battle");
        System.out.println(MENU_TRAINER + ". Trainer");
        System.out.println(MENU_HEAL + ". Heal");
        System.out.println(MENU_SHOP + ". Shop");
        System.out.println(MENU_QUIT + ". Quit");
    }

    /**
     * Implements the battle functionality:
     * First, The user selects an opponent from the monster list.
     * - The first monster on the trainer’s team battles the selected opponent and always wins.
     * - The opponent is added to the team.
     * Finally, the trainer’s money is increased by BATTLE_WINNINGS.
     * 
     * @param input Scanner for reading user input
     * @param trainer The trainer object containing team and money
     */
    private static void menuBattle(Scanner input, Trainer trainer) {
        System.out.println("\nPick a monster to battle from the list below:");
        System.out.println("Select an opponent to battle:");
        for (int i = 0; i < GameResources.ALL_MONSTERS.length; i++) {
            System.out.println((i + 1) + ". " + GameResources.ALL_MONSTERS[i]);
        }
        int selection = getValidatedSelection(input, 1, GameResources.ALL_MONSTERS.length);
        String opponent = GameResources.ALL_MONSTERS[selection - 1];
        
        String battlingMonster = trainer.getTeam().get(trainer.getActiveMonsterIndex());
        System.out.println(battlingMonster + " battles " + opponent + " and wins!");
        trainer.getTeam().add(opponent);
        trainer.setMoney(trainer.getMoney() + BATTLE_WINNINGS);
        trainer.setBattlesWon(trainer.getBattlesWon() + 1);
        System.out.printf("You earned $%.2f! Your new balance is $%.2f.\n", BATTLE_WINNINGS, trainer.getMoney());
    }

    /**
     * Prints the trainer’s information:
     * - Trainer's name
     * - Money formatted as currency with a dollar sign and two digits after the decimal
     * - List of monsters on the trainer’s team.
     * 
     * @param input Scanner for user 
     * @param trainer The trainer object containing team and money
     */
    private static void menuTrainer(Scanner input, Trainer trainer) {
        System.out.println("\nTrainer Information\n------------------");
        System.out.println("Trainer Name: " + trainer.getName());
        System.out.printf("Money: $%.2f\n", trainer.getMoney());
        System.out.println("Battles Won: " + trainer.getBattlesWon());
        System.out.println("\nTeam\n----");
        for (int i = 0; i < trainer.getTeam().size(); i++) {
            String indicator = (i == trainer.getActiveMonsterIndex()) ? " " : "";
            System.out.println((i + 1) + ". " + trainer.getTeam().get(i) + indicator);
        }

        if (trainer.getTeam().size() > 1 && getValidatedYN(input, "\nChange active monster? (Y/N): ", "Invalid input.")) {
            int newIndex = getValidatedSelection(input, 1, trainer.getTeam().size()) - 1;
            trainer.setActiveMonsterIndex(newIndex);
        }
    }

    /**
     * Implements the healing functionality:
     * - Checks if the trainer has enough funds to heal (HEALING_COST is for all monsters)
     * - If funds are sufficient, iterates over each monster, prints that it has been healed,
     *   and deducts HEALING_COST from the trainer’s money
     * - Otherwise, prints a message stating insufficient funds
     * 
     * @param scan Scanner for reading user input
     * @param trainer The trainer object containing team and money
     */
    private static void menuHeal(Scanner scan, Trainer trainer) {
        System.out.println("\n-- Heal Menu --");
        if (trainer.getMoney() >= HEALING_COST) {
            System.out.println("Healing all monsters...");
            for (String monster : trainer.getTeam()) {
                System.out.println(monster + " has been healed.");
            }
            trainer.setMoney(trainer.getMoney() - HEALING_COST);
            System.out.printf("Healing cost of $%.2f deducted. New balance: $%.2f\n", HEALING_COST, trainer.getMoney());
        } else {
            System.out.println("Insufficient funds to heal all monsters.");
        }
    }

    /**
     * Implements the shop functionality.
     * Currently, this method prints a message stating that the shop feature is not yet implemented.
     * 
     * @param input Scanner for reading user input
     * @param trainer The trainer object containing team and money
     */
    private static void menuShop(Scanner input, Trainer trainer) {
        System.out.println("\nShop Menu\n---------");
        System.out.println("Not yet implemented.");
    }

     /**
     * Prompts the user for a Y/N response to ask whether they want to keep playing, then validates the input, and returns true if Y,
     * false if N.
     *
     * @param input is the Scanner object for input
     * @param prompt is the message to prompt the user
     * @param errorMessage is the error message to display for invalid input
     * @return true if the user enters 'Y', false if 'N', not case sensitive
     */
    private static boolean getValidatedYN(Scanner input, String prompt, String errorMessage) {
        System.out.print(prompt);
        String response = input.nextLine();
        while (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N")) {
            System.out.println(errorMessage);
            System.out.print(prompt);
            response = input.nextLine();
        }
        return response.equalsIgnoreCase("Y");
    }
}
