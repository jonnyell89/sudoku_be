package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.SudokuGridUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuGridTest {

    @Test
    void testPopulateGrid() {

        Grid sudokuGrid = new Grid(); // Instantiate default Grid object.

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        SudokuGrid.populateGrid(sudokuGrid); // Assign values to all Cells.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            Cell[] row = sudokuGrid.getRow(i);

            assertTrue(Validator.isUnitValid(row), String.format("Row at grid[%d][x] should contain 1 to 9 inclusive.", i));

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                Cell[] col = sudokuGrid.getCol(j);

                assertTrue(Validator.isUnitValid(col), String.format("Column at grid[x][%d] should contain 1 to 9 inclusive.", j));

                Cell[] subgrid = sudokuGrid.getSubgrid(i, j);

                assertTrue(Validator.isUnitValid(subgrid), String.format("Subgrid at grid[%d][%d] should contain 1 to 9 inclusive.", i, j));
            }
        }

        SudokuGridUtils.printGrid(sudokuGrid);
    }
}
