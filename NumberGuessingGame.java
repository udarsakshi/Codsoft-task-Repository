import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static int playRound(int lower, int upper, int maxAttempts, Scanner scanner) {
        Random rand = new Random();
        int secret = rand.nextInt(upper - lower + 1) + lower;
        System.out.println("\nI have picked a number between " + lower + " and " + upper + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess it.");

        int attempts = 0;
        while (attempts < maxAttempts) {
            attempts++;
            System.out.print("Attempt " + attempts + "/" + maxAttempts + ". Enter your guess: ");
            int guess;
            try {
                guess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // consume invalid input
                attempts--;
                continue;
            }

            if (guess == secret) {
                System.out.println(
                        "🎉 Correct! The number was " + secret + ". You guessed it in " + attempts + " attempt(s).");
                return attempts;
            } else if (guess < secret) {
                System.out.println("Too low! Try a higher number.");
            } else {
                System.out.println("Too high! Try a lower number.");
            }
        }

        System.out.println("😞 You've used all " + maxAttempts + " attempts. The correct number was: " + secret);
        return -1; // indicates failure
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalRounds = 0;
        int roundsWon = 0;
        int sumAttempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            totalRounds++;
            int result = playRound(1, 100, 7, scanner);
            if (result > 0) {
                roundsWon++;
                sumAttempts += result;
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                break;
            }
        }

        System.out.println("\nGame over!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Rounds won: " + roundsWon);
        if (roundsWon > 0) {
            double avgAttempts = (double) sumAttempts / roundsWon;
            System.out.printf("Average attempts per win: %.2f%n", avgAttempts);
        } else {
            System.out.println("No successful guesses to compute average attempts.");
        }

        scanner.close();
    }
}