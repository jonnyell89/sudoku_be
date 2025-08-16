package com.example.sudoku_be.services;

import com.example.sudoku_be.config.GridTestConfig;
import com.example.sudoku_be.enums.Difficulty;
import com.example.sudoku_be.utils.GridGeneratorUtils;
import com.example.sudoku_be.utils.GridTestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridGeneratorTest {

    private Grid sudokuGrid;

    @Test
    void testPopulateGrid() {

        sudokuGrid = new Grid(); // Instantiate default Grid object.

        Cell[][] grid = sudokuGrid.getGrid(); // Access grid.

        GridGenerator.populateGrid(sudokuGrid); // Assign values to all Cells.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            Cell[] row = sudokuGrid.getRow(i);

            assertTrue(GridValidator.isUnitValid(row), String.format("Row at grid[%d][x] should contain 1 to 9 inclusive.", i));

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                Cell[] col = sudokuGrid.getCol(j);

                assertTrue(GridValidator.isUnitValid(col), String.format("Column at grid[x][%d] should contain 1 to 9 inclusive.", j));

                Cell[] subgrid = sudokuGrid.getSubgrid(i, j);

                assertTrue(GridValidator.isUnitValid(subgrid), String.format("Subgrid at grid[%d][%d] should contain 1 to 9 inclusive.", i, j));
            }
        }

        GridGeneratorUtils.printGrid(sudokuGrid);
    }

    @Test
    void testIsSolutionUnique() {

        sudokuGrid = new Grid(GridTestUtils.convertIntGridToCellGrid(GridTestConfig.MEDIUM_GRID));

        boolean isUnique = GridGenerator.isSolutionUnique(sudokuGrid);

        assertTrue(isUnique, "Grid should have one unique solution.");

        GridGeneratorUtils.printGrid(sudokuGrid);
    }

    @Test
    void testRemoveCells() {

        sudokuGrid = new Grid(GridTestUtils.convertIntGridToCellGrid(GridTestConfig.TEST_GRID));

        int targetCellRemovals = GridGeneratorUtils.getTargetCellRemovals(Difficulty.MEDIUM);

        GridGenerator.removeCells(sudokuGrid, targetCellRemovals);

        assertEquals(targetCellRemovals, sudokuGrid.countEmptyCells(), String.format("Grid should %d empty cells.", targetCellRemovals));

        GridGeneratorUtils.printGrid(sudokuGrid);
    }
}
