package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridUtils;
import org.springframework.stereotype.Service;

import static com.example.sudoku_be.config.GridConfig.*;

@Service
public class Grid {

    private final GridValidator gridValidator = new GridValidator();

    private final Cell[][] grid;

    public Grid() { // Default constructor.

        this.grid = GridUtils.initGrid(GRID_SIZE); // Grid initialised with default Cell objects.
    }

    public Grid(Cell[][] grid) { // Parameterised constructor.

        gridValidator.validate(grid);

        this.grid = grid;
    }

    public Cell[][] getGrid() { // NO UNIT TEST

        return this.grid;
    }

    public Cell[] getRow(int rowIndex) {

        return getGrid()[rowIndex];
    }

    public Cell[] getCol(int colIndex) {

        Cell[][] grid = getGrid(); // Access grid.

        Cell[] col = new Cell[grid.length]; // Array containing nulls, or Cell references.

        for (int i = 0; i < col.length; i++) { // Iterate over column.

            col[i] = grid[i][colIndex]; // Set array element equal to Cell object.
        }

        return col;
    }

    public Cell[] getSubgrid(int rowIndex, int colIndex) {

        Cell[][] grid = getGrid(); // Access grid.

        int subgridLength = grid.length / SUBGRID_SIZE;

        Cell[] subgrid = new Cell[subgridLength * subgridLength]; // Array containing nulls, or Cell references.

        int startRow = Math.floorDiv(rowIndex, SUBGRID_SIZE) * SUBGRID_SIZE; // subgrid[0][j]
        int startCol = Math.floorDiv(colIndex, SUBGRID_SIZE) * SUBGRID_SIZE; // subgrid[i][0]

        int subgridIndex = 0;

        for (int i = 0; i < subgridLength; i++) { // Iterate over rows.

            for (int j = 0; j < subgridLength; j++) { // Iterate over columns.

                subgrid[subgridIndex] = grid[startRow + i][startCol + j]; // Set array element equal to Cell object.

                subgridIndex++;
            }
        }

        return subgrid;
    }

    public int getGridIndex(int rowIndex, int colIndex) {

        return rowIndex * getGrid().length + colIndex;
    }

    public Cell getCell(int rowIndex, int colIndex) {

        return getGrid()[rowIndex][colIndex];
    }

    public void resetCell(int rowIndex, int colIndex) {

        getCell(rowIndex, colIndex).setValue(0);
    }

    public boolean isCellEmpty(int rowIndex, int colIndex) {

        return getCell(rowIndex, colIndex).getValue() == 0;
    }

    public Cell findNextEmptyCell(int rowIndex, int colIndex) {

        Cell[][] grid = getGrid(); // Access grid.

        for (int i = rowIndex; i < grid.length; i++) { // Iterate over rows.

            int colStart = (i == rowIndex) ? colIndex : 0;

            for (int j = colStart; j < grid[i].length; j++) { // Iterate over columns.

                if (isCellEmpty(i, j)) {

                    return getCell(i, j);
                }
            }
        }

        return null;
    }
}
