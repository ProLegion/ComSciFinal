/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.Arrays;

/**
 *
 * @author IVXX_LeGioN
 */
public class Life implements Interface {

    private int[][] grid;

    /**
     * Creates a game of life with the square size of the supplied integer
     *
     * @param x
     */
    public Life(int x) {
        grid = new int[x][x];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = 0;
            }
        }

    }

    /**
     * Creates a game of life based of a 2d array
     *
     * @param g
     */
    public Life(int[][] g) {
        grid = g;
    }

    /**
     * Returns the size of the current life game
     *
     * @return
     */
    public int getSize() {
        return grid.length;
    }

    /**
     * Sets all cells to a dead state ( 0 )
     */
    @Override
    public void killAllCells() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = 0;
            }
        }
    }

    /**
     * Sets the grid to a starting one, NOT USED IN GUI VERSION!!
     *
     * @param startGrid
     */
    @Override
    public void setPattern(int[][] startGrid) {
        grid = new int[startGrid.length][startGrid[0].length];
        for (int row = 0; row < startGrid.length; row++) {
            for (int col = 0; col < startGrid[0].length; col++) {
                grid[row][col] = startGrid[row][col];
            }
        }
    }

    /**
     * A helper method the counts the number of neighboring cells, and returns
     * the amount of neighbors
     *
     * @param cellRow
     * @param cellCol
     * @return
     */
    @Override
    public int countNeighbours(int cellRow, int cellCol) {
        int numNeighbours = 0;
        if (0 < cellRow && cellRow < grid.length - 1 && 0 < cellCol && cellCol < grid[0].length - 1) {
            if (grid[cellRow - 1][cellCol] == 1) { //Checks the Cell Above
                numNeighbours++;
            }

            if (grid[cellRow + 1][cellCol] == 1) { //Checks the Cell Below
                numNeighbours++;
            }

            if (grid[cellRow][cellCol + 1] == 1) { //Checks the Cell to the Right
                numNeighbours++;
            }

            if (grid[cellRow][cellCol - 1] == 1) { //Checks the Cell to the Left
                numNeighbours++;
            }
            if (grid[cellRow - 1][cellCol - 1] == 1) { //Checks the Top Left Corner
                numNeighbours++;
            }

            if (grid[cellRow - 1][cellCol + 1] == 1) { //Checks the Top Right Corner
                numNeighbours++;
            }

            if (grid[cellRow + 1][cellCol - 1] == 1) { //Checks the Bottom Left Corner
                numNeighbours++;
            }

            if (grid[cellRow + 1][cellCol + 1] == 1) { //Checks the Bottom Right Corner
                numNeighbours++;
            }
        }

        return numNeighbours;
    }

    /**
     * Applies the rules of life to a given cell
     *
     * @param cellRow
     * @param cellCol
     * @return
     */
    @Override
    public int applyRules(int cellRow, int cellCol) {
        if (countNeighbours(cellRow, cellCol) < 2) { //Underpopulation, alive cells die, dead cells stay dead
            return 0;
        } else if (countNeighbours(cellRow, cellCol) == 2) { //Sustained, alive cells atay alive, dead cells stay dead
            if (grid[cellRow][cellCol] == 1) { //Alive
                return 1;
            } else { //Dead
                return 0;
            }
        } else if (countNeighbours(cellRow, cellCol) > 3) { //Overpopulation, alive cells die, dead cells stay dead
            return 0;
        } else { //Equal to 3. Birth, alive cells stay alive, dead cells become alive
            return 1;
        }
    }

    /**
     * Advances the rules of life to all cells
     */
    @Override
    public void takeStep() {
        int[][] nextGen = new int[grid.length][grid[0].length];
        for (int row = 0; row < nextGen.length; row++) {
            for (int col = 0; col < nextGen[0].length; col++) {
                nextGen[row][col] = applyRules(row, col);
            }
        }
        for (int row = 0; row < nextGen.length; row++) {
            for (int col = 0; col < nextGen[0].length; col++) {
                grid[row][col] = nextGen[row][col];
            }
        }
    }

    /**
     * Returns a string of the life game
     *
     * @return
     */
    @Override
    public String toString() {
        String output = "";
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                output += grid[row][col] + " ";
            }
            output += "\n";
        }

        return (output);
    }

    /**
     * Returns the value ( 0 or 1 ) of the given cell
     *
     * @param row
     * @param col
     * @return
     */
    public int getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Sets the given cell @ row, col to the supplied integer( 0 or 1)
     *
     * @param row
     * @param col
     * @param value
     */
    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.deepHashCode(this.grid);
        return hash;
    }

    /**
     * Checks if two life objects are equal and returns a integer depending on
     * the outcome
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Life other = (Life) obj;
        if (!Arrays.deepEquals(this.grid, other.grid)) {
            return false;
        }
        return true;
    }

}
