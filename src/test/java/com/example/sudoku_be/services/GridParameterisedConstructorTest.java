package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.sudoku_be.config.GridConfig.GRID_SIZE;
import static com.example.sudoku_be.config.GridTestConfig.EMPTY_GRID;
import static com.example.sudoku_be.config.GridTestConfig.TEST_GRID;
import static org.junit.jupiter.api.Assertions.*;

public class GridParameterisedConstructorTest {

    private Grid sudokuGrid;

    @BeforeEach
    void initTest() {

        sudokuGrid = new Grid(GridTestUtils.convertIntGridToCellGrid(TEST_GRID)); // Instantiate parameterised Grid object.
    }

    @Test
    void testGrid() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        assertNotNull(grid, "Grid should not be null.");

        assertEquals(GRID_SIZE, grid.length, String.format("Grid should contain %d rows.", GRID_SIZE));

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            assertEquals(GRID_SIZE, grid[i].length, String.format("Grid should contain %d columns.", GRID_SIZE));

            for (int j = 0; j < grid.length; j++) { // Iterate over columns.

                assertTrue(GridTestUtils.isCellValid(grid[i][j]), String.format("Cell at grid[%d][%d] should be between 1 and 9 inclusive.", i, j));
            }
        }
    }

    @Test
    void testGetRow() {

        int gridLength = sudokuGrid.getGrid().length; // Access grid length.

        for (int i = 0; i < gridLength; i++) { // Iterate over rows.

            Cell[] row = sudokuGrid.getRow(i); // Access row.

            assertEquals(gridLength, row.length, String.format("Grid should contain %d equal rows.", GRID_SIZE));

            for (int j = 0; j < row.length; j++) { // Iterate over cells.

                assertTrue(GridTestUtils.isCellValid(row[j]), String.format("Cell at row[%d] should be between 1 and 9 inclusive.", j));
            }
        }
    }

    @Test
    void testGetCol() {

        int gridLength = sudokuGrid.getGrid().length; // Access grid length.

        for (int i = 0; i < gridLength; i++) { // Iterate over columns.

            Cell[] col = sudokuGrid.getCol(i); // Access column.

            assertEquals(gridLength, col.length, String.format("Grid should contain %d equal columns.", GRID_SIZE));

            for (int j = 0; j < col.length; j++) { // Iterate over cells.

                assertTrue(GridTestUtils.isCellValid(col[j]), String.format("Cell at col[%d] should be between 1 and 9 inclusive.", j));
            }
        }
    }

    @Test
    void testGetSubgrid() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                Cell[] subgrid = sudokuGrid.getSubgrid(i, j); // Access subgrid.

                assertEquals(GRID_SIZE, subgrid.length, String.format("Subgrid should contain %d cells.", GRID_SIZE));

                assertTrue(GridTestUtils.isUnitValid(subgrid), String.format("Subgrid at grid[%d][%d] should contain 1 to 9 inclusive.", i, j));
            }
        }
    }

    @Test
    void testGetCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                Cell cell = sudokuGrid.getCell(i, j);

                assertEquals(grid[i][j], cell, String.format("Cell at grid[%d][%d] should be equal to sudokuGrid.getCell(%d, %d)", i, j, i, j));
            }
        }
    }

    @Test
    void testResetCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                sudokuGrid.resetCell(i, j);

                int cell = sudokuGrid.getCell(i, j).getValue();

                assertEquals(EMPTY_GRID[i][j], cell, String.format("Cell at grid[%d][%d] should be equal to zero.", i, j));
            }
        }
    }

    @Test
    void testIsCellEmpty() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                assertFalse(sudokuGrid.isCellEmpty(i, j), String.format("Cell at grid[%d][%d] should not be equal to zero.", i, j));
            }
        }
    }

    @Test
    void testCellSequence() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        assertFalse(sudokuGrid.isCellEmpty(8, 8), String.format("Cell at grid[8][8] should contain %d", sudokuGrid.getCell(8, 8).getValue()));

        sudokuGrid.resetCell(8, 8);

        assertTrue(sudokuGrid.isCellEmpty(8, 8), String.format("Cell at grid[8][8] should contain %d", sudokuGrid.getCell(8, 8).getValue()));

        Cell emptyCell = sudokuGrid.findNextEmptyCell();

        assertNotNull(emptyCell, "Cell at grid[8][8] should be zero.");

        assertEquals(emptyCell, sudokuGrid.getCell(8, 8), "Cell at grid[8][8] should be zero.");
    }
}
