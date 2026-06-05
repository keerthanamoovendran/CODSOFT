import java.util.Random;
import java.util.Scanner;

public class App {

    static final int MIN        = 1;
    static final int MAX        = 200;
    static final int MAX_TRIES  = 8;   // max guesses per round

    public static void main(String[] args) {
        Scanner sc     = new Scanner(System.in);
        Random  rand   = new Random();
        int     totalScore = 0;
        int     round      = 1;

        System.out.println("=== Number Guessing Game ===");
        System.out.println("Range: " + MIN + " to " + MAX);
        System.out.println("Max attempts per round: " + MAX_TRIES);
        System.out.println("============================\n");

        while (true) {
            System.out.println("--- Round " + round + " ---");
            int secret  = rand.nextInt(MAX - MIN + 1) + MIN;
            int tries   = 0;
            boolean won = false;

            while (tries < MAX_TRIES) {
                int remaining = MAX_TRIES - tries;
                System.out.print("Guess (" + remaining + " attempts left): ");

                if (!sc.hasNextInt()) {
                    System.out.println("  Please enter a valid number.");
                    sc.next();  // discard bad input
                    continue;
                }

                int guess = sc.nextInt();
                tries++;

                if (guess < MIN || guess > MAX) {
                    System.out.println("  Out of range! Enter between " + MIN + " and " + MAX);
                } else if (guess < secret) {
                    System.out.println("  Too low!");
                } else if (guess > secret) {
                    System.out.println("  Too high!");
                } else {
                    // Correct! Score = higher score for fewer attempts
                    int roundScore = (MAX_TRIES - tries + 1) * 10;
                    totalScore += roundScore;
                    System.out.println("\n  Correct! The number was " + secret);
                    System.out.println("  Attempts used: " + tries);
                    System.out.println("  Round score  : " + roundScore);
                    System.out.println("  Total score  : " + totalScore + "\n");
                    won = true;
                    break;
                }
            }

            if (!won) {
                System.out.println("\n  Out of attempts! The number was " + secret);
                System.out.println("  Round score  : 0");
                System.out.println("  Total score  : " + totalScore + "\n");
            }

            System.out.print("Play another round? (yes / no): ");
            String again = sc.next().trim().toLowerCase();
            if (!again.equals("yes") && !again.equals("y")) break;
            round++;
            System.out.println();
        }

        System.out.println("==============================");
        System.out.println("Game over! Final score: " + totalScore);
        System.out.println("Total rounds played  : " + round);
        System.out.println("==============================");
        sc.close();
    }
}