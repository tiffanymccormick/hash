
/**
 * Calculate total payment of order depending on user input for a Cafe
 * with the addition of the menu with input validation/error handling,
 * and a VIP discount of 5% applied only when the subtotal is $100 or more.
 *
 * @author Tiffany McCormick
 * @version 2.0
 */
import java.util.*;

public class Cafe {

    public static void main(String[] args) {
        // Define variables for available items + VIP discount. Variables are final, 
        // meaning they won't be changed
        final double CHEESEBURGER_COST = 5.50;
        final double FRIES_COST = 3.25;
        final double WATER_COST = 2.00;
        final double VIP_DISCOUNT = 1.50;

        // Initialize scanner
        Scanner scan = new Scanner(System.in);

        // Print out beginning of output
        System.out.println("Copyright 2025 Howard Community College\n");
        System.out.println("Welcome to the Quad Cafe ordering system!\n");

        // Variables to store counts for each item.
        int totalCheeseburgers = 0;
        int totalFries = 0;
        int totalWaters = 0;

        // Initialize choice to something other than 4
        int choice = -1;

        // Loop until user selects 4 (Checkout)
        while (choice != 4) {
            // Display the menu
            System.out.println("1. Add Cheeseburger");
            System.out.println("2. Add Fry");
            System.out.println("3. Add Water");
            System.out.println("4. Checkout");
            System.out.print("Please select a menu item (1-4): ");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    // Prompt for cheeseburgers
                    int cheeseburgersToAdd;
                    do {
                        System.out.print("\nHow many cheeseburgers would you like to order? ");
                        cheeseburgersToAdd = scan.nextInt();
                        if (cheeseburgersToAdd < 1) {
                            System.out.println("Error: You must order at least 1 cheeseburger.");
                        }
                    } while (cheeseburgersToAdd < 1);

                    totalCheeseburgers += cheeseburgersToAdd;
                    System.out.printf("Adding %d to order.\n\n", cheeseburgersToAdd);
                    break;

                case 2:
                    // Prompt for fries
                    int friesToAdd;
                    do {
                        System.out.print("\nHow many fries would you like to order? ");
                        friesToAdd = scan.nextInt();
                        if (friesToAdd < 1) {
                            System.out.println("Error: You must order at least 1 fry.");
                        }
                    } while (friesToAdd < 1);

                    totalFries += friesToAdd;
                    System.out.printf("Adding %d to order.\n\n", friesToAdd);
                    break;

                case 3:
                    // Prompt for water
                    int watersToAdd;
                    do {
                        System.out.print("\nHow many waters would you like to order? ");
                        watersToAdd = scan.nextInt();
                        if (watersToAdd < 1) {
                            System.out.println("Error: You must order at least 1 water.");
                        }
                    } while (watersToAdd < 1);

                    totalWaters += watersToAdd;
                    System.out.printf("Adding %d to order.\n\n", watersToAdd);
                    break;

                case 4:
                    // Checkout case
                    System.out.println("\nProceeding to checkout\n");
                    break;

                default:
                    // Invalid choice
                    System.out.println("\nInvalid selection. Please enter a number between 1 and 4.\n");
            }
        }

        // Close the scanner
        scan.close();

        // Calculate item totals and subtotal
        double cheeseburgerTotal = totalCheeseburgers * CHEESEBURGER_COST;
        double friesTotal = totalFries * FRIES_COST;
        double waterTotal = totalWaters * WATER_COST;
        double subtotal = cheeseburgerTotal + friesTotal + waterTotal;

        // Apply 5% discount if subtotal >= $100
        double discount = 0.0;
        if (subtotal >= 100) {
            discount = subtotal * VIP_DISCOUNT;
        }
        double discountedSubtotal = subtotal - discount;

        // Calculate tax (6% of discounted subtotal) and total
        double tax = discountedSubtotal * 0.06;
        double total = discountedSubtotal + tax;

        // Compute total items and average cost per item
        int totalItems = totalCheeseburgers + totalFries + totalWaters;
        double averageCostPerItem = 0.0;
        if (totalItems > 0) {
            averageCostPerItem = total / totalItems;
        }

        // Print receipt
        System.out.println("Receipt");
        System.out.println("-------\n");

        // Print each item line if the count of that item is > 0 
        if (totalCheeseburgers > 0) {
            if (totalCheeseburgers == 1) {
                System.out.printf("%d Cheeseburger       $%.2f\n", totalCheeseburgers, cheeseburgerTotal);
            } else {
                System.out.printf("%d Cheeseburgers      $%.2f\n", totalCheeseburgers, cheeseburgerTotal);
            }
        }
        if (totalFries > 0) {
            if (totalFries == 1) {
                System.out.printf("%d Fry                $%.2f\n", totalFries, friesTotal);
            } else {
                System.out.printf("%d Fries              $%.2f\n", totalFries, friesTotal);
            }
        }
        if (totalWaters > 0) {
            if (totalWaters == 1) {
                System.out.printf("%d Water              $%.2f\n", totalWaters, waterTotal);
            } else {
                System.out.printf("%d Waters             $%.2f\n", totalWaters, waterTotal);
            }
        }

        // If the discount was applied, then print it
        if (discount > 0) {
            System.out.printf("VIP 5%% Discount     -$%.2f\n", discount);
        }

        System.out.println();
        System.out.printf("Subtotal              $%.2f\n", discountedSubtotal);
        System.out.printf("Tax                   $%.2f\n", tax);
        System.out.printf("Total                 $%.2f\n", total);
        System.out.println();
        System.out.printf("%d Items Sold\n", totalItems);
        System.out.printf("$%.2f Average Cost Per Item\n", averageCostPerItem);
    }
}
