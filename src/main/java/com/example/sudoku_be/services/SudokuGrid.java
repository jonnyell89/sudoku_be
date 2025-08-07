package com.example.sudoku_be.services;

import org.springframework.stereotype.Service;

import static com.example.sudoku_be.config.SudokuGridConfig.*;

@Service
public class SudokuGrid {

    private final SudokuGridValidator validator = new SudokuGridValidator();

    private int[][] grid;

    public SudokuGrid() {

        this.grid = new int[GRID_SIZE][GRID_SIZE]; // Instantiate two-dimensional array of zeroes.
    }

    public SudokuGrid(int[][] grid) {

        validator.validate(grid);

        this.grid = grid;
    }

    public int[][] getGrid() {

        return grid;
    }

    public void setGrid(int[][] grid) {

        validator.validate(grid);

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

        int startRow = Math.floorDiv(rowIndex, SUBGRID_SIZE) * SUBGRID_SIZE; // subgrid[0][j]
        int startCol = Math.floorDiv(colIndex, SUBGRID_SIZE) * SUBGRID_SIZE; // subgrid[i][0]

        int subgridIndex = 0;

        for (int i = 0; i < subgridLength; i++) { // Iterate over rows.

            for (int j = 0; j < subgridLength; j++) { // Iterate over columns.

                subgrid[subgridIndex] = this.grid[startRow + i][startCol + j]; // Set array element equal to specific cell.

                subgridIndex++;
            }
        }

        return subgrid;
    }
}
