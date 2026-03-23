import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int attempts = 0;
            int maxAttempts = 7;
            boolean hasGuessedCorrectly = false;

            System.out.println("\n🎯 Welcome to the Number Guessing Game!");
            System.out.println("Guess a number between 1 and 100");
            System.out.println("You have " + maxAttempts + " attempts.\n");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("✅ Correct! You guessed it in " + attempts + " attempts.");
                    hasGuessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!hasGuessedCorrectly) {
                System.out.println("❌ You've used all attempts!");
                System.out.println("The correct number was: " + numberToGuess);
            }

            // Score (based on attempts)
            int score = hasGuessedCorrectly ? (maxAttempts - attempts + 1) * 10 : 0;
            System.out.println("🏆 Your score: " + score);

            // Play again option
            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next();

            if (!response.equalsIgnoreCase("yes")) {
                playAgain = false;
                System.out.println("👋 Thanks for playing!");
            }
        }

        scanner.close();
    }
}
