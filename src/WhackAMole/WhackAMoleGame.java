/************************************************************
 * Team: G1
 * Project: WhackAMoleGame
 * Author: Jacob Clark
 * Date: 12/13/2025
 * Class: WhackAMole
 * Description: Main WhackAMole class that runs the game loop
 ************************************************************/
package WhackAMole;

import java.util.Scanner;

public class WhackAMole {
    // Other class objects
    private static ScoreTracker score; // Score Tracker
    private static GameBoard board; // Game Board
    private static GameTimer timer; // Game Timer

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // scanner object for input
        boolean playAgain; // if player wants to replay

        do { // start of replay loop

            // Game objects
            score = new ScoreTracker(); // Score Tracker
            board = new GameBoard(3, 3); // 3x3 grid
            timer = new GameTimer(30); // 30-second timer

            System.out.println("======================");
            System.out.println("Welcome to WhackAMole!");
            System.out.println("======================");
            System.out.println("How To Play:");
            System.out.println("The game will get harder as you play!");
            System.out.println("Type in the row and column numbers to whack a mole.");
            System.out.println("You have 30 seconds total — hit as many moles as possible!");
            System.out.println("Enter -1 to quit at any time.");
            System.out.println("Good Luck! :) \n");

            // Prompt to start
            System.out.println("Press Enter to start the game...");
            scan.nextLine(); // waits for the player to press Enter

            timer.start(); // start timer for difficulty tracking

            // Main game loop
            boolean playing = true;
            String lastAction = ""; // hit/miss message
            int moves = 0; // track moves

            // One mole showing
            board.reset();
            board.spawnMoles(timer.getDifficultyLevel());

            while (playing && !timer.isGameOver()) {
                // Display game board
                System.out.println();
                System.out.println();
                System.out.println(board.toString());

                // Display the score and last action on the same line
                System.out.println("Score: " + score.getScore() + "   |  " + lastAction);

                //Display the difficulty below
                System.out.println("Difficulty Level: " + timer.getDifficultyLevel());
                System.out.println(); // optional spacing before input


                // Prompt for user input (1-based)
                System.out.print("Enter a row (1-" + board.getRows() + "): ");
                int rowInput = scan.nextInt();
                if (rowInput == -1) break;

                System.out.print("Enter a column (1-" + board.getColumns() + "): ");
                int colInput = scan.nextInt();
                if (colInput == -1) break;

                // Convert to 0-index for internal use
                int row = rowInput - 1;
                int col = colInput - 1;

                // Check if user hit mole
                if (board.whackAt(row, col)) {
                    score.hit();
                    lastAction = "Hit! +2 points!";
                } else {
                    score.miss();
                    lastAction = "Miss! -1 point!";
                }

                //Spawn new mole(s) based on difficulty
                int moleCount = timer.getDifficultyLevel();
                board.spawnMoles(moleCount);
                moves++; // count moves

                //End game after 10 moves or timer is up
                if (moves == 10 || timer.isGameOver()) {
                    playing = false;
                }
            }
            System.out.println();
            System.out.println("Game over! Final score: " + score.getScore());
            timer.stop(); // stop timer

            // Ask player if they want to play again
            System.out.print("Would you like to play again? (Y/N): ");
            scan.nextLine(); // consume leftover newline
            String response = scan.nextLine();
            playAgain = response.equalsIgnoreCase("Y");
        } while (playAgain);  // Repeat game if yes

        scan.close();
        System.out.println("Thanks for playing!\n");

    }
}
