package com.example.sudoku_be.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.sudoku_be.config.GridConfig.CELL_DEFAULT;
import static com.example.sudoku_be.config.GridConfig.GRID_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class GridDefaultConstructorTest {

    private Grid sudokuGrid;

    @BeforeEach
    void initTest() {

        sudokuGrid = new Grid(); // Instantiate default Grid object.
    }

    @Test
    void testGrid() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        assertNotNull(grid, "Grid should not be null.");

        assertEquals(GRID_SIZE, grid.length, String.format("Grid should contain %d rows.", GRID_SIZE));

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            assertEquals(GRID_SIZE, grid[i].length, String.format("Grid should contain %d columns.", GRID_SIZE));

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                assertEquals(CELL_DEFAULT, grid[i][j].getValue(), String.format("Cell at grid[%d][%d] should be %d.", i, j, CELL_DEFAULT));
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

                assertEquals(CELL_DEFAULT, row[j].getValue(), String.format("Cell at row[%d] should be %d.", j, CELL_DEFAULT));
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

                assertEquals(CELL_DEFAULT, col[j].getValue(), String.format("Cell at col[%d] should be %d.", j, CELL_DEFAULT));
            }
        }
    }

    @Test
    void testGetSubgrid() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                Cell[] subgrid = sudokuGrid.getSubgrid(i, j); // Access subgrid.

                assertEquals(GRID_SIZE, subgrid.length, String.format("Subgrid should contain %d cells.", GRID_SIZE));

                assertEquals(CELL_DEFAULT, subgrid[j].getValue(), String.format("Cell at subgrid[%d] should be %d.", j, CELL_DEFAULT));
            }
        }
    }

    @Test
    void testGetGridIndex() {

        Cell[][] grid = sudokuGrid.getGrid();

        int flatGridIndex = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                assertEquals(flatGridIndex, sudokuGrid.getGridIndex(i, j), String.format("Cell at grid[%d][%d] should have flatGridIndex equal to %d.", i, j, flatGridIndex));

                flatGridIndex++;
            }
        }
    }

    @Test
    void testGetCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                Cell cell = sudokuGrid.getCell(i, j);

                assertEquals(grid[i][j], cell, String.format("Cell at grid[%d][%d] should be equal to sudokuGrid.getCell(%d, %d)", i, j, i, j));
            }
        }
    }

    @Test
    void testIsCellEmpty() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                assertTrue(sudokuGrid.isCellEmpty(i, j), String.format("Cell at grid[%d][%d] should be equal to zero.", i, j));
            }
        }
    }

    @Test
    void testFindNextEmptyCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int emptyCells = 0;

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                Cell nextEmptyCell = sudokuGrid.findNextEmptyCell(i, j);

                assertEquals(grid[i][j], nextEmptyCell, String.format("Cell at grid[%d][%d] should be equal to zero.", i, j));

                emptyCells++;
            }
        }

        assertEquals(grid.length * grid[0].length, emptyCells, String.format("Grid should contain %d empty cells.", grid.length * grid[0].length));
    }
}
