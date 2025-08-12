package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridGeneratorUtils;

public class GridGenerator {

    private final Grid grid;

    private int[] cellValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public GridGenerator(Grid grid) {

        this.grid = grid;
        this.cellValues = GridGeneratorUtils.shuffleCellValues(cellValues);
    }

    public boolean populateGrid() {

        Cell emptyCell = grid.findEmptyCell(); // Traversal

        if (emptyCell == null) { // Base case, terminates recursion if Grid contains no empty Cells.

            return true;
        }

        int rowIndex = emptyCell.getRowIndex();
        int colIndex = emptyCell.getColIndex();

        // GridGeneratorUtils.shuffleCellValues(cellValues);

        for (int value : cellValues) {

            if (grid.populateCell(rowIndex, colIndex, value)) {

                if (populateGrid()) {

                    return true;
                }

                grid.resetCell(rowIndex, colIndex);
            }
        }

        return false;
    }
}
