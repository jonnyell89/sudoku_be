package com.example.sudoku_be.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.sudoku_be.config.SudokuGridConfig.GRID_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuGridParameterisedConstructorTest {

    private static final Set<Integer> DUMMY_UNIT = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    private SudokuGrid sudokuGrid;

    @BeforeEach
    void initTest() {

        int[][] dummyGrid = new int[][]{

                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 1, 5, 6, 7, 8, 9, 4},
                {5, 6, 4, 8, 9, 1, 2, 3, 7},
                {8, 9, 7, 2, 3, 4, 5, 6, 1},
                {3, 1, 5, 6, 7, 8, 9, 4, 2},
                {6, 4, 8, 9, 1, 2, 3, 7, 5},
                {9, 7, 2, 3, 4, 5, 6, 1, 8}
        };

        sudokuGrid = new SudokuGrid(dummyGrid); // Instantiate SudokuGrid object with dummyGrid.
    }

    private boolean isCellValid(int cell) {

        return DUMMY_UNIT.contains(cell);
    }

    private boolean isUnitValid(int[] unit) {

        Set<Integer> unitSet = Arrays.stream(unit)
                .boxed()
                .collect(Collectors.toSet());

        return DUMMY_UNIT.equals(unitSet);
    }

    @Test
    void testSudokuGrid() {

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        assertNotNull(grid, "Grid should not be null.");

        assertEquals(GRID_SIZE, grid.length, String.format("Grid should contain %d rows.", GRID_SIZE));

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            assertEquals(GRID_SIZE, grid[i].length, String.format("Grid should contain %d columns.", GRID_SIZE));

            for (int j = 0; j < grid.length; j++) { // Iterate over columns.

                assertTrue(isCellValid(grid[i][j]), String.format("Cell at grid[%d][%d] should be between 1 and 9 inclusive.", i, j));
            }
        }
    }

    @Test
    void testGetRow() {

        int gridLength = sudokuGrid.getGrid().length; // Access grid length.

        for (int i = 0; i < gridLength; i++) { // Iterate over rows.

            int[] row = sudokuGrid.getRow(i); // Access row.

            assertEquals(gridLength, row.length, String.format("Grid should contain %d equal rows.", GRID_SIZE));

            for (int j = 0; j < row.length; j++) { // Iterate over cells.

                assertTrue(isCellValid(row[j]), String.format("Cell at row[%d] should be between 1 and 9 inclusive.", j));
            }
        }
    }

    @Test
    void testGetCol() {

        int gridLength = sudokuGrid.getGrid().length; // Access grid length.

        for (int i = 0; i < gridLength; i++) { // Iterate over columns.

            int[] col = sudokuGrid.getCol(i); // Access column.

            assertEquals(gridLength, col.length, String.format("Grid should contain %d equal columns.", GRID_SIZE));

            for (int j = 0; j < col.length; j++) { // Iterate over cells.

                assertTrue(isCellValid(col[j]), String.format("Cell at col[%d] should be between 1 and 9 inclusive.", j));
            }
        }
    }

    @Test
    void testGetSubgrid() {

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                int[] subgrid = sudokuGrid.getSubgrid(i, j); // Access subgrid.

                assertEquals(GRID_SIZE, subgrid.length, String.format("Subgrid should contain %d cells.", GRID_SIZE));

                assertTrue(isUnitValid(subgrid), String.format("Subgrid at grid[%d][%d] should contain 1 to 9 inclusive.", i, j));
            }
        }
    }
}
