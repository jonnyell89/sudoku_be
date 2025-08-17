package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.SudokuGridUtils;

import static com.example.sudoku_be.config.GridConfig.CELL_VALUES;

public class SudokuGrid {

    public static void setGrid(Grid grid) {

        int[] cellValues = SudokuGridUtils.shuffleValues(CELL_VALUES); // Ensures cellValues are shuffled only once, at the start of the recursive process.

        setGridRecursion(grid, cellValues);
    }

    private static boolean setGridRecursion(Grid grid, int[] cellValues) {

        Cell emptyCell = grid.findNextEmptyCell(); // Traversal

        if (emptyCell == null) return true; // Base case, terminates recursion if Grid contains no empty Cells.

        int rowIndex = emptyCell.getRowIndex(); // Unpacks i.
        int colIndex = emptyCell.getColIndex(); // Unpacks j.

        for (int value : cellValues) { // Iterates over shuffledCellValues.

            if (grid.setCell(rowIndex, colIndex, value)) { // Attempts to setCell with shuffledCellValue.

                if (setGridRecursion(grid, cellValues)) return true; // Recursive step, continues traversal until base case is met.

                grid.resetCell(rowIndex, colIndex); // Backtracks if there are no valid Cell placements.
            }
        }

        return false;
    }
}
