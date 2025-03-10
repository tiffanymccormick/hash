
/**
 * User is given a certain number of guesses to guess the magic number, given 
 * indivators of whether the guess is too low or too high to work towards the right number.
 * In addition, the user will be able to play the game up to the best of three games, but
 * has the option for infinite replays
 * 
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 3.0
 */
import java.security.SecureRandom;
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

        // To track whether the user wants to repeat the games or not, allowing for infinite replays
        String repeatGame;
        
        do {
            int totalWins = 0;  // Tracks the number of wins
            
            // For loop for three independent games
            for (int game = 1; game <= 3; game++) {
                System.out.println("Game " + game + " of 3:");
                
                // Random number between 1 and 1000 (inclusive)
                int magicNumber = random.nextInt(1001);
                
                int guessesTaken = 0;
                int guessesRemaining = MAX_GUESSES;
                boolean guessedCorrectly = false;
                int guess = 0;
                
                // User guesses until they run out of attempts or guess correctly
                while (guessesRemaining > 0 && !guessedCorrectly) {
                    System.out.print("Enter your guess (" + guessesRemaining 
                               + " remaining): ");
                    guess = scan.nextInt();
                    scan.nextLine();
                    
                    guessesTaken++;
                    
                    if (guess < magicNumber) {
                        System.out.println("Too low.");
                    } else if (guess > magicNumber) {
                        System.out.println("Too high.");
                    } else {
                        // Correct guess
                        guessedCorrectly = true;
                        String guessWord2;
                        if (guessesTaken == 1) {
                            guessWord2 = "guess";
                        } else {
                            guessWord2 = "guesses";
                        }
                        System.out.println("Congratulations, you guessed the magic number in " 
                                           + guessesTaken + " " + guessWord2 + "!");
                        totalWins++;
                    }
                    
                    if (!guessedCorrectly) {
                        guessesRemaining--;
                    }
                }
                
                // If the user didn't guess the number correctly within the allowed attempts
                if (!guessedCorrectly) {
                    System.out.println("Sorry, you're out of guesses. The magic number was " + magicNumber + ".");
                }
                System.out.println();
            }
            
            // After the best-of-three series, reports the number of wins
            System.out.println("You won " + totalWins + " out of 3 games.");
            
            // Prompts the user to play again, accepting only 'Y' or 'N' (while ignoring other invalid cases)
            System.out.print("Would you like to play again? (Y/N): ");
            repeatGame = scan.nextLine();
            
            while (!repeatGame.equalsIgnoreCase("Y") && !repeatGame.equalsIgnoreCase("N")) {
                System.out.println("Error: Please enter only Y or N.");
                System.out.print("Would you like to play again? (Y/N): ");
                repeatGame = scan.nextLine();
            }
            System.out.println();
        } while (repeatGame.equalsIgnoreCase("Y"));
        
        // If the user chooses to opt out of playing
        System.out.println("Thanks for playing!");
        scan.close();
    }
}
