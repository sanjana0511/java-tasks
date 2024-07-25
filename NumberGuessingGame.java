import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int min = 1;
        int max = 100;
        int secretNumber = random.nextInt(max - min + 1) + min;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("Welcome to the Guess the Number game!");
        System.out.println("I've picked a number between " + min + " and " + max + ". Try to guess it!");

        while (!guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == secretNumber) {
                System.out.println("Congratulations! You guessed it right in " + attempts + " attempts.");
                guessedCorrectly = true;
            } else if (userGuess < secretNumber) {
                System.out.println("Try a higher number.");
            } else {
                System.out.println("Try a lower number.");
            }

            // Add code here to limit the number of attempts or implement other features.
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}