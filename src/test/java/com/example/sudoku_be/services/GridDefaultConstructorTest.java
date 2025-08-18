package com.example.sudoku_be.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.sudoku_be.config.GridConfig.CELL_VALUES_DEFAULT;
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

                assertEquals(CELL_VALUES_DEFAULT, grid[i][j].getValue(), String.format("Cell at grid[%d][%d] should be %d.", i, j, CELL_VALUES_DEFAULT));
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

                assertEquals(CELL_VALUES_DEFAULT, row[j].getValue(), String.format("Cell at row[%d] should be %d.", j, CELL_VALUES_DEFAULT));
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

                assertEquals(CELL_VALUES_DEFAULT, col[j].getValue(), String.format("Cell at col[%d] should be %d.", j, CELL_VALUES_DEFAULT));
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

                assertEquals(CELL_VALUES_DEFAULT, subgrid[j].getValue(), String.format("Cell at subgrid[%d] should be %d.", j, CELL_VALUES_DEFAULT));
            }
        }
    }

    @Test
    void testGetCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                Cell cell = sudokuGrid.getCell(i, j); // Access cell.

                assertEquals(grid[i][j], cell, String.format("Cell at grid[%d][%d] should be equal to zero.", i, j));
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
    void testIsCellSet() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                assertFalse(sudokuGrid.isCellSet(i, j), String.format("Cell at grid[%d][%d] should be equal to zero.", i, j));
            }
        }
    }

    @Test
    void testCountEmptyCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int emptyCellCount = GRID_SIZE * GRID_SIZE; // Number of empty cells in grid.

        assertEquals(emptyCellCount, sudokuGrid.countEmptyCells(), String.format("Grid should contain %d empty cells.", emptyCellCount));
    }

    @Test
    void testCountSetCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int setCellCount = GRID_SIZE * GRID_SIZE * CELL_VALUES_DEFAULT; // Number of set cells in grid.

        assertEquals(setCellCount, sudokuGrid.countSetCells(), String.format("Grid should contain %d set cells.", setCellCount));
    }

    @Test
    void testGetEmptyCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int emptyCellCount = GRID_SIZE * GRID_SIZE; // Number of empty cells in grid.

        Cell[] emptyCells = sudokuGrid.getEmptyCells(); // Empty cells in grid.

        assertEquals(emptyCellCount, emptyCells.length, String.format("Grid should contain %d empty cells.", emptyCellCount));

        for (Cell cell : emptyCells) {

            assertEquals(CELL_VALUES_DEFAULT, cell.getValue(), String.format("Cell at grid[%d][%d] should be equal to zero.", cell.getRowIndex(), cell.getColIndex()));
        }
    }

    @Test
    void testGetSetCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int setCellCount = GRID_SIZE * GRID_SIZE * CELL_VALUES_DEFAULT; // Number of set cells in grid.

        Cell[] setCells = sudokuGrid.getSetCells(); // Set cells in grid.

        assertEquals(setCellCount, setCells.length, String.format("Grid should contain %d set cells.", setCellCount));

        for (Cell cell : setCells) {

            assertEquals(CELL_VALUES_DEFAULT, cell.getValue(), String.format("Cell at grid[%d][%d] should be equal to zero.", cell.getRowIndex(), cell.getColIndex()));
        }
    }
}
