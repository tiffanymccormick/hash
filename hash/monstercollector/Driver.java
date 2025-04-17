/**
 * Driver class for the Monster Collector Game that handles the game menu and actions for each item movement
 *
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
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

    private static List<String> team;
    private static double money;
    private static String name;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Copyright 2025 Howard Community College\n");

        // User enters their name, name is now set to the user input and money is set to 0
        // + initialize trainer's team
        System.out.print("Please enter your name, trainer: ");
        name = scan.nextLine();
        money = 0.0;
        team = new ArrayList<>();

        // User input to select their first monster
        System.out.println("Pick a starter monster from the list below:");
        for (int i = 0; i < GameResources.ALL_MONSTERS.length; i++) {
            System.out.println((i + 1) + ". " + GameResources.ALL_MONSTERS[i]);
        }
        int selection = getValidatedSelection(scan, 1, GameResources.ALL_MONSTERS.length);
        String chosenMonster = GameResources.ALL_MONSTERS[selection - 1];
        team.add(chosenMonster);
        System.out.println("A wild " + chosenMonster + " appeared!");

        int menuChoice;
        do {
            printMenu();
            menuChoice = getValidatedSelection(scan, MENU_BATTLE, MENU_QUIT);
            switch (menuChoice) {
                case MENU_BATTLE:
                    menuBattle(scan);
                    break;
                case MENU_TRAINER:
                    menuTrainer(scan);
                    break;
                case MENU_HEAL:
                    menuHeal();
                    break;
                case MENU_SHOP:
                    menuShop(scan);
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
     */
    private static void printMenu() {
        System.out.println("\nWelcome to the CMSY-166 monster collector game, " + name + "!");
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
     */
    private static void menuBattle(Scanner input) {
        System.out.println("\n-- Battle Menu --");
        System.out.println("Select an opponent to battle:");
        for (int i = 0; i < GameResources.ALL_MONSTERS.length; i++) {
            System.out.println((i + 1) + ". " + GameResources.ALL_MONSTERS[i]);
        }
        int selection = getValidatedSelection(input, 1, GameResources.ALL_MONSTERS.length);
        String opponent = GameResources.ALL_MONSTERS[selection - 1];
        
        String battlingMonster = team.get(0);
        System.out.println(battlingMonster + " battles " + opponent + " and wins!");
        team.add(opponent);
        money += BATTLE_WINNINGS;
        System.out.printf("You earned $%.2f! Your new balance is $%.2f.\n", BATTLE_WINNINGS, money);
    }

    /**
     * Prints the trainer’s information:
     * - Trainer's name
     * - Money formatted as currency with a dollar sign and two digits after the decimal
     * - List of monsters on the trainer’s team.
     * 
     * @param input Scanner for user input
     */
    private static void menuTrainer(Scanner input) {
        System.out.println("\nTrainer Information\n------------------");
        System.out.println("Trainer Name: " + name);
        System.out.printf("Money: $%.2f\n", money);
        System.out.println("\nTeam\n----");
        for (int i = 0; i < team.size(); i++) {
            System.out.println((i + 1) + ". " + team.get(i));
        }
    }

    /**
     * Implements the healing functionality:
     * - Checks if the trainer has enough funds to heal (HEALING_COST is for all monsters)
     * - If funds are sufficient, iterates over each monster, prints that it has been healed,
     *   and deducts HEALING_COST from the trainer’s money
     * - Otherwise, prints a message stating insufficient funds
     */
    private static void menuHeal() {
        System.out.println("\n-- Heal Menu --");
        if (money >= HEALING_COST) {
            System.out.println("Healing all monsters...");
            for (String monster : team) {
                System.out.println(monster + " has been healed.");
            }
            money -= HEALING_COST;
            System.out.printf("Healing cost of $%.2f deducted. New balance: $%.2f\n", HEALING_COST, money);
        } else {
            System.out.println("Insufficient funds to heal all monsters.");
        }
    }

    /**
     * Implements the shop functionality.
     * Currently, this method prints a message stating that the shop feature is not yet implemented.
     * 
     * @param input Scanner for reading user input
     */
    private static void menuShop(Scanner input) {
        System.out.println("\nShop Menu\n---------");
        System.out.println("Not yet implemented.");
    }
}
