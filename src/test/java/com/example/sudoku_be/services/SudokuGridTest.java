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

            for (int j = 0; j < grid.length; j++) { // Iterate over columns.

                assertEquals(0, grid[i][j], String.format("Cell at grid[%d][%d] should be 0.", i, j));
            }
        }
    }

    @Test
    void testGetRow() {

        SudokuGrid sudokuGrid = new SudokuGrid(); // Instantiate grid.

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            int[] gridRow = sudokuGrid.getRow(i); // Access row.

            assertEquals(grid.length, gridRow.length, "Grid should have 9 equal columns.");

            for (int j = 0; j < gridRow.length; j++) { // Iterate over columns.

                assertEquals(0, gridRow[j], String.format("Cell at gridRow[%d] should be 0.", j));
            }
        }
    }

    @Test
    void testGetCol() {

        SudokuGrid sudokuGrid = new SudokuGrid(); // Instantiate grid.

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            int[] gridCol = sudokuGrid.getCol(i); // Access column.

            assertEquals(grid.length, gridCol.length, "Grid should have 9 equal columns.");

            for (int j = 0; j < gridCol.length; j++) { // Iterate over columns.

                assertEquals(0, gridCol[j], String.format("Cell at gridCol[%d] should be 0.", j));
            }
        }
    }
}
