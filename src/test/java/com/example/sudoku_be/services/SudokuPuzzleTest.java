package com.example.sudoku_be.services;

import com.example.sudoku_be.config.GridTestConfig;
import com.example.sudoku_be.enums.Difficulty;
import com.example.sudoku_be.utils.GridTestUtils;
import com.example.sudoku_be.utils.SudokuGridUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuPuzzleTest {

    @Test
    void testIsSolutionUnique() {

        Grid sudokuGrid = new Grid(GridTestUtils.convertIntGridToCellGrid(GridTestConfig.MEDIUM_GRID));

        boolean isUnique = SudokuPuzzle.isSolutionUnique(sudokuGrid);

        assertTrue(isUnique, "Grid should have one unique solution.");

        SudokuGridUtils.printGrid(sudokuGrid);
    }

    @Test
    void testRemoveCells() {

        Grid sudokuGrid = new Grid(GridTestUtils.convertIntGridToCellGrid(GridTestConfig.TEST_GRID));

        int targetCellRemovals = SudokuGridUtils.getTargetCellRemovals(Difficulty.MEDIUM);

        SudokuPuzzle.removeCells(sudokuGrid, targetCellRemovals);

        assertEquals(targetCellRemovals, sudokuGrid.countEmptyCells(), String.format("Grid should contain %d empty cells.", targetCellRemovals));

        SudokuGridUtils.printGrid(sudokuGrid);
    }
}
