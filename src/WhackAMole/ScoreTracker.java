/***********************************************************
 * Team: G1
 * Project: WhackAMoleGame
 * Author: Susan Rogers
 * Date: 12/13/2025
 * Class: ScoreTracker
 * Description: Score tracking and game logic.
 ***********************************************************/
package WhackAMole;

public class ScoreTracker {
    // Current score
    private int score;
    /**
     * Default constructor initializes score to zero.
     */
    public ScoreTracker() {
        this.score = 0;
    }
    /**
     * Called when the player successfully whacks a mole. Adds 2 points per hit
     * (adjust if desired).
     */
    public void hit() {
        score += 2;
    }
    /**
     * Called when the player misses. Subtracts 1 point per miss, but never
     * below zero.
     */
    public void miss() {
        if (score > 0) {
            score -= 1;
        }
    }
    /**
     * @return the current score value.
     */
    public int getScore() {
        return score;
    }
    /**
     * Resets score to zero for a new game.
     */
    public void reset() {
        score = 0;
    }
    @Override
    public String toString() {
        return "Score: " + score;
    }
}

