/**
 * Tests the Trainer class and updated Plant Dinosaur class with the updated use of the MonsterType enum
 * 
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
 */

import java.util.*;
public class TrainerTest {
    public static void main(String[] args){
        // Testing 3 argument constructor named Blue with 50.0 money and 
        Trainer blue = new Trainer("Blue", 50.0, "Charmander");
        blue.getTeam().add("Squirtle");

        // Testing default constructor named Red
        Trainer red = new Trainer();

        // Print out values for both trainer
        System.out.println("Copyright 2025 Howard Community College\n");

        System.out.println(blue);
        System.out.println(red);

        // User inputs new monster to be added to Blue's team
        System.out.println("\nSelect monster to be active");
        Scanner input = new Scanner(System.in);
        
        // Iterates through the current monsters in Blue's team and prints them out
        List<String> team = blue.getTeam();
        for (int i = 0; i < team.size(); i++) {
            System.out.println(i+1 + ". " + team.get(i));
        }

        // User chooses what monster to be active

        int choice = getValidatedSelection(input, 0, team.size() - 1);
        blue.setActiveMonsterIndex(choice);

        // Print out updated blue team
        System.out.println("\n"+ blue);

        // Test updated Plant Dinosaur
        PlantDinosaur dino = new PlantDinosaur();
        System.out.println("\n"+ dino);
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
}
