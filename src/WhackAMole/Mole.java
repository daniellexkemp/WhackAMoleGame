/******************************************************
* Team: G1
* Project: WhackAMoleGame
* Author: Lindsey Lear
* Date: 12/13/2025
* Purpose: Class symbolizing a Mole in WhackAMole game. 
*******************************************************/
package WhackAMole;

public class Mole {

    private int column; //column on board
    private int row; //row on board
    private boolean active; //mole is active or inactive
    
    //Constructor
    public Mole() { //inactive mole, row and column are -1
        this.column = -1;
        this.row = -1;
        this.active = false;
    }
    
    //Active mole and removed mole
    public void activeMole(int r, int c) { //active mole that spawns on board
        this.column = c;
        this.row = r;
        this.active = true;
    }

    public void removeMole() { //mole is removed
        this.column = -1;
        this.row = -1;
        this.active = false;
    }

    /*
    * Checks if mole was whacked at the row and column, mole disapears if hit
    * Returns true if mole was hit, returns false if not
    */
    public boolean whackMole(int r, int c) {
        if (active && r == row && c == column) {
            removeMole(); //mole is removed
            return true;
        }
        return false;
    }
    
    //Getters
    public int getColumn() { //return column of mole
        return column;
    }

    public int getRow() { //return row of mole
        return row;
    }

    public boolean isActive() { //return true if mole is active
        return active;
    }
}
