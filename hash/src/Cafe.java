
/**
 * Track the user's order and inventory using arrays. This way, Quad Cafe can
 * prevent overselling by checking inventory before adding items to the order.
 *
 * @author Tiffany McCormick
 * @version 5.0
 */
import java.util.*;

public class Cafe {

    // Constants for array indices
    private static final int INDEX_BURGER = 0;
    private static final int INDEX_FRIES = 1;
    private static final int INDEX_WATER = 2;

    public static void main(String[] args) {
        // Define variables for available items. Variables are final, 
        // meaning they won't be changed
        final double CHEESEBURGER_COST = 5.50;
        final double FRIES_COST = 3.25;
        final double WATER_COST = 2.00;

        // Initialize inventories array (each item starts with 30 in stock)
        int[] inventories = {30, 30, 30};
        // Initialize orders array (all counts start at 0)
        int[] orders = {0, 0, 0};

        // Initialize scanner
        Scanner scan = new Scanner(System.in);

        // Print out beginning of output
        System.out.println("Copyright 2025 Howard Community College\n");
        System.out.println("Welcome to the Quad Cafe ordering system!\n");

        // Initialize choice to something other than 4 (this is so the program doesn't automatically go to checkout)
        int choice = -1;

        // Loop until checkout 
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
                    // Update orders array at INDEX_BURGER using getItemCount method.
                    orders[INDEX_BURGER] += getItemCount(scan, "Cheeseburger", inventories);
                    break;
                case 2:
                    orders[INDEX_FRIES] += getItemCount(scan, "Fry", inventories);
                    break;
                case 3:
                    orders[INDEX_WATER] += getItemCount(scan, "Water", inventories);
                    break;
                case 4:
                    System.out.println("Proceeding to checkout\n");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.\n");
            }
        }

        // Close the scanner
        scan.close();

        // Calculate gross sales (the cost before any discount)
        double grossSales = (orders[INDEX_BURGER] * CHEESEBURGER_COST)
                + (orders[INDEX_FRIES] * FRIES_COST)
                + (orders[INDEX_WATER] * WATER_COST);

        // If the gross sales is over $100, apply the discount
        boolean applyDiscount = (grossSales >= 100);

        // Print receipt header
        printReceiptSection();

        // For each of the food, print a line for each and calculate the discount value, if any
        double discountCheeseburgers = printReceiptSection(orders[INDEX_BURGER], "Cheeseburger", CHEESEBURGER_COST, applyDiscount);
        double discountFries = printReceiptSection(orders[INDEX_FRIES], "Fry", FRIES_COST, applyDiscount);
        double discountWaters = printReceiptSection(orders[INDEX_WATER], "Water", WATER_COST, applyDiscount);
        double totalDiscount = discountCheeseburgers + discountFries + discountWaters;

        // Print sales summary section using the orders array
        printReceiptSection(grossSales, totalDiscount, orders);

        // Print the remaining inventory section
        printReceiptSection(inventories);
    }

    /**
     * Prompts the user for the count of a given item, validates the input, and
     * then ensures that the order does not exceed available inventory. Updates
     * the inventories array accordingly.
     *
     * Outcomes: 1. If inventory is 0, prints a message and returns 0. 2. If
     * requested count > available, prints a message and returns available
     * count. 3. Otherwise, returns the requested count.
     *
     * @param input the input Scanner object
     * @param itemName the name of the item ("Cheeseburger", "Fry", "Water")
     * @param inventories the inventories array tracking available stock
     * @return the count to add to the order for the given item
     */
    private static int getItemCount(Scanner input, String itemName, int[] inventories) {
        // Determine the inventory index based on itemName
        int index;
        if (itemName.equalsIgnoreCase("Cheeseburger")) {
            index = INDEX_BURGER;
        } else if (itemName.equalsIgnoreCase("Fry")) {
            index = INDEX_FRIES;
        } else if (itemName.equalsIgnoreCase("Water")) {
            index = INDEX_WATER;
        } else {
            return 0; // Unknown item
        }

        // Check if inventory for this item is already exhausted
        if (inventories[index] == 0) {
            System.out.println("\nSorry, we are out of " + itemName + "s.");
            return 0;
        }

        int count;
        do {
            if(!(itemName.equals("Fry"))){
                System.out.print("\nHow many " + itemName.toLowerCase() + "s would you like to order? ");
            } else{
                System.out.print("\nHow many fries would you like to order? ");
            }
            count = input.nextInt();
            if (count < 1) {
                System.out.println("Error: You must order at least 1 " + itemName.toLowerCase() + ".");
            }
        } while (count < 1);

        // If requested count exceeds available inventory, adjust count
        if (count > inventories[index]) {
            System.out.println("Only " + inventories[index] + " " + itemName.toLowerCase()
                    + "s available. Adding " + inventories[index] + " to order.");
            count = inventories[index];
        }

        // Update the inventory to keep track of items
        inventories[index] -= count;
        System.out.printf("Adding %d to order.\n\n", count);
        return count;
    }

    /*
     * Prints the receipt label and the sectioning -'s under it
     */
    private static void printReceiptSection() {
        System.out.println("Receipt");
        System.out.println("-------\n");
    }

    /*
     * Prints a line for each item with the name and cost
     * If applyDiscount is true, then every tenth item is free
     * after each tenth item, however, an additional line is printed showing the free item
     * It returns the total discount amount for that item type.
     * 
     * @param count is the count ordered of this item
     * @param name is the name for this item
     * @param cost is the cost of one unit of this item
     * @param applyDiscount true if the discount applies 
     * @return the total discount amount for free items
     */
    private static double printReceiptSection(int count, String name, double cost, boolean applyDiscount) {
        double discountTotal = 0.0;
        for (int i = 1; i <= count; i++) {
            System.out.printf("%-3s%-17s $%6.2f\n", "1", name, cost);
            if (applyDiscount && i % 10 == 0) {
                System.out.printf("%-20s ($%6.2f)\n", "10th " + name + " free", cost);
                discountTotal += cost;
            }
        }

        return discountTotal;
    }

    /*
     * Prints the sales summary section of the receipt
     * It prints Gross Sales and VIP Discount lines when applicable, then prints
     * the Subtotal, Tax, Total, Items Sold, and Average Per Item
     *
     * @param grossSales is the total value before any discount
     * @param discount is the total discount value from free items
     * @param orders     the orders array (for computing total items sold)
     */
    private static void printReceiptSection(double grossSales, double discount, int[] orders) {
        // Compute total items sold by summing the orders array.
        int totalItems = orders[INDEX_BURGER] + orders[INDEX_FRIES] + orders[INDEX_WATER];
        double discountedSubtotal = grossSales - discount;
        double tax = discountedSubtotal * 0.06;
        double total = discountedSubtotal + tax;
        double averageCost = (totalItems > 0) ? total / totalItems : 0.0;

        if (discount > 0) {
            System.out.printf("\n%-20s $%6.2f\n", "Gross Sales", grossSales);
            System.out.printf("%-20s -$%6.2f\n", "VIP Discount", discount);
        }
        System.out.println();
        System.out.printf("%-20s $%6.2f\n", "Subtotal", discountedSubtotal);
        System.out.printf("%-20s $%6.2f\n", "Tax", tax);
        System.out.printf("%-20s $%6.2f\n", "Total", total);
        System.out.println();
        System.out.printf("%d Items Sold\n", totalItems);
        System.out.printf("$%.2f Average Cost Per Item\n", averageCost);
    }

    /**
     * Prints the remaining inventory at the end of the receipt. Uses the
     * inventories array and prints each item in a formatted manner
     *
     * @param inventories the array containing remaining inventory for each item
     */
    private static void printReceiptSection(int[] inventories) {
        System.out.println("\nInventory");
        System.out.println("---------");
        System.out.printf("Cheeseburger %d\n", inventories[INDEX_BURGER]);
        System.out.printf("Fry          %d\n", inventories[INDEX_FRIES]);
        System.out.printf("Water        %d\n", inventories[INDEX_WATER]);
    }
}