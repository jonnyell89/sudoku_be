package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridGeneratorUtils;

import static com.example.sudoku_be.config.GridConfig.CELL_VALUES;

public class GridGenerator {

    public static void populateGrid(Grid grid) {

        int[] cellValues = GridGeneratorUtils.shuffleCellValues(CELL_VALUES);

        populateGridRecursion(grid, cellValues);
    }

    private static boolean populateGridRecursion(Grid grid, int[] cellValues) {

        Cell emptyCell = grid.findNextEmptyCell(); // Traversal

        if (emptyCell == null) { // Base case, terminates recursion if Grid contains no empty Cells.

            return true;
        }

        int rowIndex = emptyCell.getRowIndex();
        int colIndex = emptyCell.getColIndex();

        for (int value : cellValues) {

            if (grid.populateCell(rowIndex, colIndex, value)) {

                if (populateGridRecursion(grid, cellValues)) { // Recursive step, continues to the next empty cell.

                    return true;
                }

                grid.resetCell(rowIndex, colIndex); // Backtrack
            }
        }

        return false;
    }

    public static boolean isGridUnique(Grid grid) {

        int[] cellValues = GridGeneratorUtils.shuffleCellValues(CELL_VALUES);

        return isGridUniqueRecursion(grid, cellValues) <= 1;
    }

    private static int isGridUniqueRecursion(Grid grid, int[] cellValues) {

        Cell emptyCell = grid.findNextEmptyCell(); // Traversal

        if (emptyCell == null) {

            return 1;
        }

        int rowIndex = emptyCell.getRowIndex();
        int colIndex = emptyCell.getColIndex();

        int solutionCount = 0;

        for (int value : cellValues) {

            if (grid.populateCell(rowIndex, colIndex, value)) {

                solutionCount += isGridUniqueRecursion(grid, cellValues);

                if (solutionCount > 1) return solutionCount;

                grid.resetCell(rowIndex, colIndex);
            }
        }

        return solutionCount;
    }
}
