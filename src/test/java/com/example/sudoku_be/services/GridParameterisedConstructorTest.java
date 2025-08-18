package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.example.sudoku_be.config.GridConfig.*;
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

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                assertTrue(Validator.isCellValid(grid[i][j]), String.format("Cell at grid[%d][%d] should be between 1 and 9 inclusive.", i, j));
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

                assertTrue(Validator.isCellValid(row[j]), String.format("Cell at row[%d] should be between 1 and 9 inclusive.", j));
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

                assertTrue(Validator.isCellValid(col[j]), String.format("Cell at col[%d] should be between 1 and 9 inclusive.", j));
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

                assertTrue(Validator.isUnitValid(subgrid), String.format("Subgrid at grid[%d][%d] should contain 1 to 9 inclusive.", i, j));
            }
        }
    }

    @Test
    void testGetCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                Cell cell = sudokuGrid.getCell(i, j); // Access cell.

                assertEquals(grid[i][j], cell, String.format("Cell at grid[%d][%d] should be equal to sudokuGrid.getCell(%d, %d)", i, j, i, j));
            }
        }
    }

    @Test
    void testResetCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                sudokuGrid.resetCell(i, j);

                int cell = sudokuGrid.getCell(i, j).getValue(); // Access cell value.

                assertEquals(EMPTY_GRID[i][j], cell, String.format("Cell at grid[%d][%d] should be equal to zero.", i, j));
            }
        }
    }

    @Test
    void testFindNextEmptyCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        assertNull(sudokuGrid.findNextEmptyCell(), "Grid should contain no empty cells.");
    }

    @Test
    void testIsCellEmpty() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                assertFalse(sudokuGrid.isCellEmpty(i, j), String.format("Cell at grid[%d][%d] should not be equal to zero.", i, j));
            }
        }
    }

    @Test
    void testIsCellSet() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                assertTrue(sudokuGrid.isCellSet(i, j), String.format("Cell at grid[%d][%d] should contain a value between 1 and 9 inclusive.", i, j));
            }
        }
    }

    @Test
    void testIsCellValid() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                for (int cellValue : CELL_VALUES) {

                    assertFalse(sudokuGrid.isCellValid(i, j, cellValue), String.format("Cell at grid[%d][%d] should contain a value between 1 and 9 inclusive.", i, j));
                }
            }
        }
    }

    @Test
    void testSetCell() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int i = grid.length - 1; // The last row in the grid.

        Cell[] row = sudokuGrid.getRow(i); // Access last row in grid.

        Cell[] rowDeepCopy = new Cell[row.length];

        for (int j = 0; j < row.length; j++) { // Iterate over cells.

            rowDeepCopy[j] = new Cell(row[j].getRowIndex(), row[j].getColIndex(), row[j].getValue()); // Deep copy row.
        }

        for (int j = 0; j < grid.length; j++) { // Iterate over cells.

            sudokuGrid.resetCell(i, j);

            assertTrue(sudokuGrid.isCellEmpty(i, j), String.format("Cell at grid[%d][%d] should be equal to zero.", i, j));
        }

        for (int j = 0; j < grid.length; j++) { // Iterate over cells.

            for (int cellValue : CELL_VALUES) {

                if (sudokuGrid.setCell(i, j, cellValue)) continue; // Attempt to set cell value.
            }

            assertEquals(rowDeepCopy[j], grid[i][j], String.format("Cell at row[%d] should be equal to cell at grid[%d][%d].", j, i, j));
        }
    }

    @Test
    void testGetRelatedCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                Set<Cell> relatedCells = sudokuGrid.getRelatedCells(i, j);

                assertEquals(SUPER_UNIT_SIZE, relatedCells.size());

                assertTrue(Validator.isUnitValid(relatedCells), String.format("Related Cells at grid[%d][%d] should contain 1 to 9 inclusive.", i, j));
            }
        }
    }

    @Test
    void testCountEmptyCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int emptyCellCount = GRID_SIZE * GRID_SIZE * CELL_VALUES_DEFAULT; // Number of empty cells in grid.

        assertEquals(emptyCellCount, sudokuGrid.countEmptyCells(), String.format("Grid should contain %d empty cells.", emptyCellCount));
    }

    @Test
    void testCountSetCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int setCellCount = GRID_SIZE * GRID_SIZE; // Number of set cells in grid.

        assertEquals(setCellCount, sudokuGrid.countSetCells(), String.format("Grid should contain %d set cells.", setCellCount));
    }

    @Test
    void testGetEmptyCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int emptyCellCount = GRID_SIZE * GRID_SIZE * CELL_VALUES_DEFAULT; // Number of empty cells in grid.

        Cell[] emptyCells = sudokuGrid.getEmptyCells(); // Empty cells in grid.

        assertEquals(emptyCellCount, emptyCells.length, String.format("Grid should contain %d empty cells.", emptyCellCount));

        for (Cell cell : emptyCells) {

            assertNotEquals(CELL_VALUES_DEFAULT, cell.getValue(), String.format("Cell at grid[%d][%d] should not be equal to zero.", cell.getRowIndex(), cell.getColIndex()));
        }
    }

    @Test
    void testGetSetCells() {

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        int setCellCount = GRID_SIZE * GRID_SIZE; // Number of set cells in grid.

        Cell[] setCells = sudokuGrid.getSetCells(); // Set cells in grid.

        assertEquals(setCellCount, setCells.length, String.format("Grid should contain %d set cells.", setCellCount));

        for (Cell cell : setCells) {

            assertNotEquals(CELL_VALUES_DEFAULT, cell.getValue(), String.format("Cell at grid[%d][%d] should not be equal to zero.", cell.getRowIndex(), cell.getColIndex()));
        }
    }
}
