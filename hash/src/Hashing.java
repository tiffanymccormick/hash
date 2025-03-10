/**
 * Hashing - Performs hashing algorithm to convert a user supplied PIN number to
 * a quotient and remainder
 *
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
 */
import java.util.Scanner;

public class Hashing {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // Prompt the user for their PIN number of choice
        System.out.print("Enter Pin Num: ");
        int pin = scan.nextInt();

        // Scanner no longer needed, close the scanner
        scan.close();

        /**
         * Hashing algorithm 1. Transform PIN according to given value formula
         * 2. Use integer division to divide by 22 to get quotient and remainder
         */
        int value = (pin + 10) * 42 - 15;

        int quotient = value / 22;
        int remainder = value % 22;

        // Reverse Hashing algorithm to retore original PIN
        int product = (quotient * 22) + remainder;
        int reversedPin = ((product + 15) / 42) - 10;

        // Output results
        System.out.println("Copyright 2025 Howard Community College\n");
        System.out.printf("The original pin is: %d%n", pin);
        System.out.printf("The hash is: Quotient = %dr%d%n", quotient, remainder);
        System.out.printf("After reversing the pin is: %d%n", reversedPin);
    }
}