package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridGeneratorUtils;

import static com.example.sudoku_be.config.GridConfig.CELL_VALUES;

public class GridGenerator {

    public static void populateGrid(Grid grid) {

        int[] cellValues = GridGeneratorUtils.shuffleCellValues(CELL_VALUES); // Ensures cellValues are shuffled only once, at the start of the recursive process.

        populateGridRecursion(grid, cellValues);
    }

    private static boolean populateGridRecursion(Grid grid, int[] cellValues) {

        Cell emptyCell = grid.findNextEmptyCell(); // Traversal

        if (emptyCell == null) { // Base case, terminates recursion if Grid contains no empty Cells.

            return true;
        }

        int rowIndex = emptyCell.getRowIndex(); // Unpacks i.
        int colIndex = emptyCell.getColIndex(); // Unpacks j.

        for (int value : cellValues) { // Iterates over shuffledCellValues.

            if (grid.populateCell(rowIndex, colIndex, value)) { // Attempts to populateCell with shuffledCellValue.

                if (populateGridRecursion(grid, cellValues)) { // Recursive step, continues traversal until base case is met.

                    return true;
                }

                grid.resetCell(rowIndex, colIndex); // Backtracks if there are no valid Cell placements.
            }
        }

        return false;
    }

    public static boolean isGridUnique(Grid grid) {

        int[] cellValues = CELL_VALUES.clone(); // Unnecessary to shuffle cellValues.

        int[] solutionCount = {0}; // Mutable solution counter.

        isGridUniqueRecursion(grid, cellValues, solutionCount);

        return solutionCount[0] == 1; // Returns true if there is exactly one solution.
    }

    private static void isGridUniqueRecursion(Grid grid, int[] cellValues, int[] solutionCount) {

        if (solutionCount[0] > 1) return; // Terminates recursion if Grid contains more than one solution.

        Cell emptyCell = grid.findNextEmptyCell(); // Traversal

        if (emptyCell == null) { // Base case, terminates recursion and increments solutionCount if Grid contains no empty Cells.

            solutionCount[0]++;

            return;
        }

        int rowIndex = emptyCell.getRowIndex(); // Unpacks i;
        int colIndex = emptyCell.getColIndex(); // Unpacks j;

        for (int value : cellValues) { // Iterates over cellValues.

            if (grid.populateCell(rowIndex, colIndex, value)) { // Attempts to populateCell with cellValue.

                isGridUniqueRecursion(grid, cellValues, solutionCount); // Recursive step, continues traversal until base case is met.

                grid.resetCell(rowIndex, colIndex); // Backtracks after recursive tree has been fully explored.

                if (solutionCount[0] > 1) return; // Terminates recursion if Grid contains more than one solution.
            }
        }
    }
}
