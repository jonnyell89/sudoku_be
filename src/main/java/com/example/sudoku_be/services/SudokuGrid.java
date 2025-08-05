package com.example.sudoku_be.services;

import org.springframework.stereotype.Service;

import static com.example.sudoku_be.config.SudokuGridConfig.*;

@Service
public class SudokuGrid {

    private int[][] grid;

    public SudokuGrid() {

        this.grid = new int[GRID_SIZE][GRID_SIZE]; // Instantiate two-dimensional array of specific length.

        // new int[GRID_SIZE][GRID_SIZE] defaults to a two-dimensional array of zeroes.

//        int gridLength = this.grid.length;
//        for (int i = 0; i < gridLength; i++) { // Iterate over rows.
//            Arrays.fill(this.grid[i], 0); // Fill rows with specific value.
//        }
    }

    public SudokuGrid(int[][] grid) {

        validateGrid(grid);

        this.grid = grid;
    }

    private void validateGrid(int[][] grid) {

        if (grid == null) {
            throw new IllegalArgumentException("SudokuGrid should not be null.");
        }

        if (grid.length != GRID_SIZE) {
            throw new IllegalArgumentException(String.format("SudokuGrid should contain %d rows.", GRID_SIZE));
        }

        for (int i = 0; i < grid.length; i++) {

            if (grid[i].length != GRID_SIZE) {
                throw new IllegalArgumentException(String.format("SudokuGrid should contain %d columns.", GRID_SIZE));
            }

            for (int j = 0; j < grid[0].length; j++) {

                int cell = grid[i][j];

                if (cell < CELL_DEFAULT || cell > GRID_SIZE) {
                    throw new IllegalArgumentException(String.format("SudokuGrid cell should contain values between %d and %d inclusive.", CELL_DEFAULT, GRID_SIZE));
                }
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int[] getRow(int rowIndex) {
        return this.grid[rowIndex];
    }

    public int[] getCol(int colIndex) {

        int[] col = new int[this.grid.length]; // Instantiate array of specific length.

        int colLength = col.length;

        for (int i = 0; i < colLength; i++) { // Iterate over column.

            col[i] = this.grid[i][colIndex]; // Set array element equal to specific cell.
        }

        return col;
    }

    public int[] getSubgrid(int rowIndex, int colIndex) {

        int[] subgrid = new int[this.grid.length]; // Instantiate array of specific length.

        int subgridLength = this.grid.length / SUBGRID_SIZE;

        int startRow = Math.floorDiv(rowIndex, SUBGRID_SIZE) * SUBGRID_SIZE;
        int startCol = Math.floorDiv(colIndex, SUBGRID_SIZE) * SUBGRID_SIZE;

        for (int i = 0; i < subgridLength; i++) {

            for (int j = 0; j < subgridLength; j++) {

                subgrid[i] = this.grid[startRow + i][startCol + j];
            }
        }

        return subgrid;
    }
}
