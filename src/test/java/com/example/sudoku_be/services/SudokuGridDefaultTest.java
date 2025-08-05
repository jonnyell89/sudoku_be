package com.example.sudoku_be.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.sudoku_be.config.SudokuGridConfig.CELL_DEFAULT;
import static com.example.sudoku_be.config.SudokuGridConfig.GRID_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuGridDefaultTest {

    private SudokuGrid sudokuGrid;

    @BeforeEach
    void initTest() {

        sudokuGrid = new SudokuGrid(); // Instantiate grid.
    }

    @Test
    void testSudokuGridDefault() {

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        assertNotNull(grid, "Grid should not be null.");

        assertEquals(GRID_SIZE, grid.length, String.format("Grid should contain %d rows.", GRID_SIZE));

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            assertEquals(GRID_SIZE, grid[i].length, String.format("Grid should contain %d columns.", GRID_SIZE));

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                assertEquals(CELL_DEFAULT, grid[i][j], String.format("Cell at grid[%d][%d] should be %d.", i, j, CELL_DEFAULT));
            }
        }
    }

    @Test
    void testGetRowDefault() {

        int gridLength = sudokuGrid.getGrid().length; // Access grid length.

        for (int i = 0; i < gridLength; i++) { // Iterate over rows.

            int[] row = sudokuGrid.getRow(i); // Access row.

            assertEquals(gridLength, row.length, String.format("Grid should contain %d equal rows.", GRID_SIZE));

            for (int j = 0; j < row.length; j++) { // Iterate over cells.

                assertEquals(CELL_DEFAULT, row[j], String.format("Cell at row[%d] should be %d.", j, CELL_DEFAULT));
            }
        }
    }

    @Test
    void testGetColDefault() {

        int gridLength = sudokuGrid.getGrid().length; // Access grid length.

        for (int i = 0; i < gridLength; i++) { // Iterate over columns.

            int[] col = sudokuGrid.getCol(i); // Access column.

            assertEquals(gridLength, col.length, String.format("Grid should contain %d equal columns.", GRID_SIZE));

            for (int j = 0; j < col.length; j++) { // Iterate over cells.

                assertEquals(CELL_DEFAULT, col[j], String.format("Cell at col[%d] should be %d.", j, CELL_DEFAULT));
            }
        }
    }

    @Test
    void testGetSubgridDefault() {

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                int[] subgrid = sudokuGrid.getSubgrid(i, j); // Access subgrid.

                assertEquals(GRID_SIZE, subgrid.length, String.format("Subgrid should contain %d cells.", GRID_SIZE));

                assertEquals(CELL_DEFAULT, subgrid[j], String.format("Cell at subgrid[%d] should be %d.", j, CELL_DEFAULT));
            }
        }
    }
}
