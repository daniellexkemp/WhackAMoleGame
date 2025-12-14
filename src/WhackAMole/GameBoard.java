/******************************************************
 * Team: G1
 * Project: WhackAMoleGame
 * Author: Nathaniel Johnson
 * Date: 12/13/2025
 * Class: GameBoard
 * Description: Maintains a 2D board for Whack-A-Mole.
 ******************************************************/
package WhackAMole;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private static final char EMPTY = '.';
    private static final char MOLE = 'M';
    private final int rows;
    private final int cols;
    private final char[][] grid;
    private final Random rng;
    private int moleCount; // Number of moles currently spawned
    // Store mole positions as pairs of (row, col)
    private ArrayList<int[]> moles;
    public GameBoard(int r, int c) {
        if (r <= 0 || c <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }
        this.rows = r;
        this.cols = c;
        this.grid = new char[rows][cols];
        this.rng = new Random();
        this.moles = new ArrayList<>();
        this.moleCount = 1; // default to 1 mole
        reset();
    }
    /**
     * Clears the board and removes all moles.
     */
    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = EMPTY;
            }
        }
        moles.clear();
    }
    /**
     * Spawns multiple moles up to moleCount (max 3).
     */
    public void spawnMoles(int moleCount) {
        reset(); // clear before placing new moles
        this.moleCount = Math.min(moleCount, 3); // limit to 3 moles max
        int placed = 0;
        while (placed < this.moleCount) {
            int r = rng.nextInt(rows);
            int c = rng.nextInt(cols);
            if (grid[r][c] == EMPTY) {
                grid[r][c] = MOLE;
                moles.add(new int[] { r, c });
                placed++;
            }
        }
    }
    /**
     * Attempts to whack at a location.
     * @return true if a mole was hit.
     */
    public boolean whackAt(int r, int c) {
        if (!inBounds(r, c)) {
            return false;
        }
        if (grid[r][c] == MOLE) {
            grid[r][c] = EMPTY;
            // Remove from list
            moles.removeIf(m -> m[0] == r && m[1] == c);
            return true;
        }
        return false;
    }
    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
    public int getRows() { return rows; }
    public int getColumns() { return cols; }
    public int getMoleCount() { return moles.size(); }
    /**
     * String rendering of the current board for console output.
     * Example (3x3):
     * . M .
     * . . .
     * . . .
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Column headers
        sb.append("     "); // 5 spaces, space for row numbers
        for (int c = 0; c < cols; c++) {
            sb.append(c + 1).append("  "); // 1-based numbering
        }
        sb.append('\n');

        // Rows
        for (int r = 0; r < rows; r++) {
            sb.append(r + 1).append(" | "); // row number with separator
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 'M') sb.append("🐹 "); // mole
                else sb.append("⬜ "); // empty hole
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}

