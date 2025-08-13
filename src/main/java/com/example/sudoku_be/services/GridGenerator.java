package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridGeneratorUtils;

public class GridGenerator {

    public static void populateGrid(Grid grid) {

        int[] cellValues = GridGeneratorUtils.shuffleCellValues();

        backtrackingAlgorithm(grid, cellValues);
    }

    private static boolean backtrackingAlgorithm(Grid grid, int[] cellValues) {

        Cell emptyCell = grid.findNextEmptyCell(); // Traversal

        if (emptyCell == null) { // Base case, terminates recursion if Grid contains no empty Cells.

            return true;
        }

        int rowIndex = emptyCell.getRowIndex();
        int colIndex = emptyCell.getColIndex();

        for (int value : cellValues) {

            if (grid.populateCell(rowIndex, colIndex, value)) {

                if (backtrackingAlgorithm(grid, cellValues)) { // Recursive step, continues to the next empty cell.

                    return true;
                }

                grid.resetCell(rowIndex, colIndex); // Backtrack
            }
        }

        return false;
    }
}
