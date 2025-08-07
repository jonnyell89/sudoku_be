package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridUtils;
import org.springframework.stereotype.Service;

import static com.example.sudoku_be.config.GridConfig.*;

@Service
public class Grid {

    private final GridValidator validator = new GridValidator();

    private Cell[][] grid;

    public Grid() {

        this.grid = GridUtils.initGrid(GRID_SIZE); // Grid initialised with default Cell objects.
    }

    public Grid(Cell[][] grid) {

        validator.validate(grid);

        this.grid = grid;
    }

    public Cell[][] getGrid() { // NO UNIT TEST

        return grid;
    }

    public void setGrid(Cell[][] grid) { // NO UNIT TEST

        validator.validate(grid);

        this.grid = grid;
    }

    public Cell[] getRow(int rowIndex) {

        return this.grid[rowIndex];
    }

    public Cell[] getCol(int colIndex) {

        Cell[] col = new Cell[this.grid.length]; // Array containing nulls, or Cell references.

        int colLength = col.length;

        for (int i = 0; i < colLength; i++) { // Iterate over column.

            col[i] = this.grid[i][colIndex]; // Set array element equal to Cell object.
        }

        return col;
    }

    public Cell[] getSubgrid(int rowIndex, int colIndex) {

        Cell[] subgrid = new Cell[this.grid.length]; // Array containing nulls, or Cell references.

        int subgridLength = this.grid.length / SUBGRID_SIZE;

        int startRow = Math.floorDiv(rowIndex, SUBGRID_SIZE) * SUBGRID_SIZE; // subgrid[0][j]
        int startCol = Math.floorDiv(colIndex, SUBGRID_SIZE) * SUBGRID_SIZE; // subgrid[i][0]

        int subgridIndex = 0;

        for (int i = 0; i < subgridLength; i++) { // Iterate over rows.

            for (int j = 0; j < subgridLength; j++) { // Iterate over columns.

                subgrid[subgridIndex] = this.grid[startRow + i][startCol + j]; // Set array element equal to Cell object.

                subgridIndex++;
            }
        }

        return subgrid;
    }

    public boolean isCellEmpty(int rowIndex, int colIndex) { // REQUIRES UNIT TEST

        return this.grid[rowIndex][colIndex].getValue() == 0;
    }

    public void resetCell(int rowIndex, int colIndex) { // REQUIRES UNIT TEST

        this.grid[rowIndex][colIndex].setValue(0);
    }
}
