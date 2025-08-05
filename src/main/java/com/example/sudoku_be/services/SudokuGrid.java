package com.example.sudoku_be.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.example.sudoku_be.config.SudokuGridConfig.GRID_SIZE;
import static com.example.sudoku_be.config.SudokuGridConfig.SUBGRID_SIZE;

@Service
public class SudokuGrid {

    private int[][] grid;

    public SudokuGrid() {

        this.grid = new int[GRID_SIZE][GRID_SIZE]; // Instantiate two-dimensional array of specific length.

        int gridLength = this.grid.length;

        for (int i = 0; i < gridLength; i++) { // Iterate over rows.

            Arrays.fill(this.grid[i], 0); // Fill rows with specific value.
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
