package com.example.sudoku_be.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.sudoku_be.config.SudokuGridConfig.GRID_SIZE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuGridDummyTest {

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

        sudokuGrid = new SudokuGrid(dummyGrid);
    }

    @Test
    void testGetSubgridWithDummyGrid() {

        int[][] grid = sudokuGrid.getGrid(); // Access grid.

        int[] dummySubgrid = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[0].length; j++) { // Iterate over columns.

                int[] subgrid = sudokuGrid.getSubgrid(i, j); // Access subgrid.

                assertEquals(GRID_SIZE, subgrid.length, String.format("Subgrid should contain %d cells.", GRID_SIZE));

                assertArrayEquals(dummySubgrid, subgrid, String.format("Subgrid at grid[%d][%d] should contain 1 to 9 inclusive.", i, j));
            }
        }
    }
}
