
/**
 * Number guess game, where the user guesses a magic number with indicators of "too high"
 * and "too low" is simplified to only execute independent games, code simplified
 * and optimized to use methods. User guess history is tracked using an ArrayList
 * to calculate end of game statistics.
 * 
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 5.0
 */
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NumberGuess {

    public static void main(String[] args) {
        // Random class instance
        Random random = new SecureRandom();

        // Maximum amount of guesses the user receives
        final int MAX_GUESSES = 10;

        // Create instance of Scanner
        Scanner scan = new Scanner(System.in);
        System.out.println("Copyright 2025 Howard Community College\n");

        // ArrayList to track the outcome of each game (number of guesses used or -1 if unsuccessful)
        ArrayList<Integer> outcomes = new ArrayList<Integer>();

        // To track whether the user wants to repeat the games or not, allowing for infinite replays
        boolean repeatGame;
        
        do {
            // Generate a random magic number between 1 and 1000 (inclusive)
            int magicNumber = random.nextInt(1000) + 1;
            
            // Run one playthrough of the game and add the outcome to the outcomes ArrayList
            int guessesUsed = runGame(scan, magicNumber, MAX_GUESSES);
            outcomes.add(guessesUsed);
            
            // Prompt to play again using getValidatedYN method
            repeatGame = getValidatedYN(scan, "Would you like to play again? (y or n) ", "Error: Please enter Y or N");
            System.out.println();
        } while (repeatGame);

        System.out.println("Game History\n-----------");

        // Track the gameplay statistics to be printed when the user opts out of playing
        int successfulGames = 0;
        int unsuccessfulGames = 0;
        int totalGuessesSuccessful = 0;
        
        // To iterate over outcomes, print each outcome and update statistics
        for (int outcome : outcomes) {
            if (outcome == -1) {
                // Unsuccessful game
                System.out.println("Unsuccessful");
                unsuccessfulGames++;
            } else {
                // Successful game
                if (outcome == 1) {
                    System.out.println("1 guess");
                } else {
                    System.out.println(outcome + " guesses");
                }
                successfulGames++;
                totalGuessesSuccessful += outcome;
            }
        }
        
        // Display gameplay statistics
        System.out.println("\nStatistics\n-----------");
        int totalGames = outcomes.size();
        if (totalGames == 1) {
            System.out.println("You played 1 game.");
        } else {
            System.out.println("You played " + totalGames + " games.");
        }
        
        if (unsuccessfulGames == 1) {
            System.out.println("1 was unsuccessful.");
        } else {
            System.out.println(unsuccessfulGames + " were unsuccessful.");
        }
        
        if (successfulGames == 1) {
            System.out.println("1 was successful.");
        } else {
            System.out.println(successfulGames + " were successful.");
        }
        
        if (successfulGames > 0) {
            double average = (double) totalGuessesSuccessful / successfulGames;
            System.out.printf("%.2f average guesses per successful game.%n", average);
        }
        
        System.out.println("Thanks for playing!");
        scan.close();
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
    
    /**
     * Runs one playthrough of the number guess game. The user has a maximum number of 
     * guesses in order to guess the magic number. 
     * If the user guesses correctly, the method returns the number
     * of guesses taken; otherwise, it returns -1
     *
     * @param input is the Scanner object for input
     * @param magicNumber is the magic number to be guessed
     * @param maxGuesses is the maximum number of allowed guesses
     * @return the number of guesses taken if successful; -1 if unsuccessful
     */
    private static int runGame(Scanner input, int magicNumber, int maxGuesses) {
        int guessesTaken = 0;
        int guessesRemaining = maxGuesses;
        boolean guessedCorrectly = false;
        int guess = 0;
        
        while (guessesRemaining > 0 && !guessedCorrectly) {
            System.out.print("Enter your guess (" + guessesRemaining + " remaining): ");
            guess = input.nextInt();
            input.nextLine(); 
            
            guessesTaken++;
            
            if (guess < magicNumber) {
                System.out.println("Too low.");
            } else if (guess > magicNumber) {
                System.out.println("Too high.");
            } else {
                guessedCorrectly = true;
                String guessWord;
                if (guessesTaken == 1) {
                    guessWord = "guess";
                } else {
                    guessWord = "guesses";
                }
                System.out.println("Congratulations, you guessed the number in " 
                                   + guessesTaken + " " + guessWord + "!");
            }
            
            if (!guessedCorrectly) {
                guessesRemaining--;
            }
        }
        
        if (guessedCorrectly) {
            return guessesTaken;
        } else {
            System.out.println("You are out of guesses. The magic number was " + magicNumber + ".");
            return -1;
        }
    }

    
}
