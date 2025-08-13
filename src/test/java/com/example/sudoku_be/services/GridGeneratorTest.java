package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridGeneratorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.sudoku_be.config.GridConfig.CELL_DEFAULT;
import static com.example.sudoku_be.config.GridConfig.GRID_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class GridGeneratorTest {

    private Grid sudokuGrid;

    @BeforeEach
    void initTest() {

        sudokuGrid = new Grid(); // Instantiate default Grid object.
    }

    @Test
    void testPopulateGrid() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        assertNotNull(grid, "Grid should not be null.");

        assertEquals(GRID_SIZE, grid.length, String.format("Grid should contain %d rows.", GRID_SIZE));

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            assertEquals(GRID_SIZE, grid[i].length, String.format("Grid should contain %d columns.", GRID_SIZE));

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                assertEquals(CELL_DEFAULT, grid[i][j].getValue(), String.format("Cell at grid[%d][%d] should be %d.", i, j, CELL_DEFAULT));
            }
        }

        GridGenerator.populateGrid(sudokuGrid);

        GridGeneratorUtils.printGrid(sudokuGrid);
    }
}
