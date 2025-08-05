package com.example.sudoku_be.services;

import org.junit.jupiter.api.Test;

import static com.example.sudoku_be.config.SudokuGridConfig.GRID_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuGridTest {

    @Test
    void testSudokuGrid() {

        SudokuGrid sudokuGrid = new SudokuGrid(); // Instantiate grid.

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        assertNotNull(grid, "Grid should not be null.");

        assertEquals(GRID_SIZE, grid.length, "Grid should have 9 equal rows.");

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            assertEquals(GRID_SIZE, grid[i].length, "Grid should have 9 equal columns.");

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                assertEquals(0, grid[i][j], String.format("Cell at grid[%d][%d] should be 0.", i, j));
            }
        }
    }

    @Test
    void testGetRow() {

        SudokuGrid sudokuGrid = new SudokuGrid(); // Instantiate grid.

        int gridLength = sudokuGrid.getGrid().length; // Access grid length.

        for (int i = 0; i < gridLength; i++) { // Iterate over rows.

            int[] row = sudokuGrid.getRow(i); // Access row.

            assertEquals(gridLength, row.length, "Grid should have 9 equal rows.");

            for (int j = 0; j < row.length; j++) { // Iterate over cells.

                assertEquals(0, row[j], String.format("Cell at row[%d] should be 0.", j));
            }
        }
    }

    @Test
    void testGetCol() {

        SudokuGrid sudokuGrid = new SudokuGrid(); // Instantiate grid.

        int gridLength = sudokuGrid.getGrid().length; // Access grid length.

        for (int i = 0; i < gridLength; i++) { // Iterate over columns.

            int[] col = sudokuGrid.getCol(i); // Access column.

            assertEquals(gridLength, col.length, "Grid should have 9 equal columns.");

            for (int j = 0; j < col.length; j++) { // Iterate over cells.

                assertEquals(0, col[j], String.format("Cell at col[%d] should be 0.", j));
            }
        }
    }

    @Test
    void testGetSubgrid() {

        SudokuGrid sudokuGrid = new SudokuGrid(); // Instantiate grid.

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                int[] subgrid = sudokuGrid.getSubgrid(i, j); // Access subgrid.

                assertEquals(0, subgrid[j], String.format("Cell at subgrid[%d] should be 0.", j));
            }
        }
    }
}
