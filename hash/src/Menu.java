
/**
 * Menu - Implements a menu with a while loop, prompting the user to
 * choose the desired menu option until they want to checkout
 *
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
 */

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        //Initialize scaner
        Scanner scan = new Scanner(System.in);

        /**
         * Sentinel-controlled while loop that prompts the user to continuously
         * choose menu options until they want to checkout
         *
         * Use boolean to determine whether the user is currently checking out
         * or not
         */
        System.out.println("Copyright 2025 Howard Community College\n");

        boolean checkout = false;
        while (!checkout) {
            // Display the menu options
            System.out.println("Menu:");
            System.out.println("1. Add Cheeseburger");
            System.out.println("2. Add Fries");
            System.out.println("3. Add Water");
            System.out.println("4. Checkout");
            System.out.print("Enter your choice: ");

            int choice = scan.nextInt();

            // Menu selection changes the output and whether to continue running the simulation or not
            switch (choice) {
                case 1:
                    System.out.println("You added a Cheeseburger.");
                    break;
                case 2:
                    System.out.println("You added Fries.");
                    break;
                case 3:
                    System.out.println("You added Water.");
                    break;
                case 4:
                    System.out.println("Checking out. Thank you!");
                    checkout = false;  // Set checkout to false to exit loop
                    break;
                default:
                    /**
                     * If the user inputs any other value outside of the menu range,
                     * catch invalid input
                     */
                    System.out.println("Invalid selection. Please enter a number between 1 and 4.");
            }
        }
        scan.close();

    }
}
