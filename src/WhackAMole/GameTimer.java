/*************************************************************************
 * Team: G1
 * Project: WhackAMoleGame
 * Author: Danielle Kemp
 * Date: 12/13/2025
 * Class: GameTimer
 * Description: Tracks game time and adjusts difficulty for Whack-A-Mole.
 *************************************************************************/
package WhackAMole;

public class GameTimer {
    private int timeLeft;
    private int difficultyLevel;
    private boolean gameOver;
    /**
     * constructor initializes timer with total seconds
     * @param seconds - game length
     */
    public GameTimer(int seconds) {
        this.timeLeft = seconds;
        this.difficultyLevel = 1; // game starts at level 1
        this.gameOver = false;
    }
    // counter starts
    public void start() {
        new Thread(() -> {
            while (timeLeft > 0) {
                try {
                    Thread.sleep(1000); // 1 second intervals
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeLeft--;
                updateDifficulty();
            }
            gameOver = true;
        }).start();
    }
    // stops the timer early if needed
    public void stop() {
        gameOver = true;
    }
    // returns the seconds left
    public int getTimeLeft() {
        return timeLeft;
    }
    //returns current difficulty level
    public int getDifficultyLevel() {
        return difficultyLevel;
    }
    // updates difficulty based on time elapsed (Score or Mole hits)
    private void updateDifficulty() {
        difficultyLevel = 1 + (30 - timeLeft) / 10; // every 10 seconds
    }
    // returns true if time is up
    public boolean isGameOver() {
        return gameOver;
    }
}
