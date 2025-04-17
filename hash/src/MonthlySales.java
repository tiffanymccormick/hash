/**
 * User inputs the monthly sales for Quad Cafe, and calculates the highest, lowest, and average sales
 *
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
 */
import java.util.Scanner;

public class MonthlySales {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Array that will be populated with the sales for each month
        // The index corresponds to the month (0 = January, 1 = February...)
        double[] monthlySales = new double[12];
        
        System.out.println("Copyright 2025 Howard Community College\n");

        // Fill the monthlySales array with what the user i nputs for each month
        for (int i = 0; i < monthlySales.length; i++) {
            String month = getMonthString(i);
            monthlySales[i] = getValidatedscan(scan, month);
        }

        // To calculate the highest and lowest sales indices and compute the total sales
        int highIndex = 0;
        int lowIndex = 0;
        double sum = 0;
        for (int i = 0; i < monthlySales.length; i++) {
            sum += monthlySales[i];
            if (monthlySales[i] > monthlySales[highIndex]) {
                highIndex = i;
            }
            if (monthlySales[i] < monthlySales[lowIndex]) {
                lowIndex = i;
            }
        }

        // Print the sales report
        printReport(monthlySales, highIndex, lowIndex, sum);
        scan.close();
    }

    private static String getMonthString(int index) {
        switch (index) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "";
        }
    }

    private static double getValidatedscan(Scanner scan, String month) {
        double sales;
        do {
            System.out.print("Please enter sales for " + month + ": ");
            // Ensure the user enters a numeric value
            while (!scan.hasNextDouble()) {
                System.out.print("Invalid scan. Please enter a numeric value for " + month + ": ");
                scan.next(); 
            }
            sales = scan.nextDouble();
            if (sales < 0) {
                System.out.println("Error: Sales cannot be negative.");
            }
        } while (sales < 0);
        return sales;
    }

    private static void printReport(double[] monthlySales, int highIndex, int lowIndex, double sum) {
        for (int i = 0; i < monthlySales.length; i++) {
            System.out.printf("%-10s : $%.2f%n", getMonthString(i), monthlySales[i]);
        }
        System.out.printf("%nThe highest sales total was $%.2f in %s.%n",
                monthlySales[highIndex], getMonthString(highIndex));
        System.out.printf("The lowest sales total was $%.2f in %s.%n",
                monthlySales[lowIndex], getMonthString(lowIndex));
        double average = sum / monthlySales.length;
        System.out.printf("The averages sales were $%.2f/month.%n", average);
    }
}
