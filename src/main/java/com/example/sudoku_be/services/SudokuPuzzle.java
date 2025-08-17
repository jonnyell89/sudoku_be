package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.SudokuGridUtils;

import static com.example.sudoku_be.config.GridConfig.CELL_VALUES;

public class SudokuPuzzle {

    public static void removeCells(Grid grid, int target) {

        Cell[] setCells = grid.getSetCells();

        Cell[] shuffledCells = SudokuGridUtils.shuffleCells(setCells);

        int successfulRemovals = 0;

        for (Cell cell : shuffledCells) {

            if (removeCell(grid, cell)) successfulRemovals++;

            if (target == successfulRemovals) break;
        }
    }

    private static boolean removeCell(Grid grid, Cell cell) {

        grid.resetCell(cell.getRowIndex(), cell.getColIndex());

        if (isSolutionUnique(grid)) return true;

        grid.getCell(cell.getRowIndex(), cell.getColIndex()).setValue(cell.getValue());

        return false;
    }

    public static boolean isSolutionUnique(Grid grid) {

        int[] cellValues = CELL_VALUES.clone(); // Unnecessary to shuffle cellValues.

        int[] solutionCount = {0}; // Mutable solution counter.

        isSolutionUniqueRecursion(grid, cellValues, solutionCount);

        return solutionCount[0] == 1; // Returns true if there is exactly one solution.
    }

    private static void isSolutionUniqueRecursion(Grid grid, int[] cellValues, int[] solutionCount) {

        if (solutionCount[0] > 1) return; // Terminates recursion if Grid contains more than one solution.

        Cell emptyCell = grid.findNextEmptyCell(); // Traversal

        if (emptyCell == null) { // Base case, terminates recursion and increments solutionCount if Grid contains no empty Cells.

            solutionCount[0]++;

            return;
        }

        int rowIndex = emptyCell.getRowIndex(); // Unpacks i;
        int colIndex = emptyCell.getColIndex(); // Unpacks j;

        for (int value : cellValues) { // Iterates over cellValues.

            if (grid.setCell(rowIndex, colIndex, value)) { // Attempts to setCell with cellValue.

                isSolutionUniqueRecursion(grid, cellValues, solutionCount); // Recursive step, continues traversal until base case is met.

                grid.resetCell(rowIndex, colIndex); // Backtracks after recursive tree has been fully explored.

                if (solutionCount[0] > 1) return; // Terminates recursion if Grid contains more than one solution.
            }
        }
    }
}
