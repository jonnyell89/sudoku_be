package com.example.sudoku_be.services;

import com.example.sudoku_be.utils.GridUtils;

import java.util.*;

import static com.example.sudoku_be.config.GridConfig.*;

public class Grid {

    private final Cell[][] grid;

    public Grid() { // Default constructor.

        this.grid = GridUtils.initGrid(GRID_SIZE); // Grid initialised with default Cell objects.
    }

    public Grid(Cell[][] grid) { // Parameterised constructor.

        this.grid = GridUtils.validateGrid(grid); // Grid Cell objects validated.
    }

    public Cell[][] getGrid() {

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

    public Cell getCell(int rowIndex, int colIndex) {

        return getGrid()[rowIndex][colIndex];
    }

    public void resetCell(int rowIndex, int colIndex) {

        getCell(rowIndex, colIndex).setValue(0);
    }

    public Cell findNextEmptyCell() {

        Cell[][] grid = getGrid(); // Access grid.

        for (int i = 0; i < grid.length; i++) { // Iterate over rows.

            for (int j = 0; j < grid[i].length; j++) { // Iterate over columns.

                if (isCellEmpty(i, j)) return getCell(i, j);
            }
        }

        return null;
    }

    public boolean isCellEmpty(int rowIndex, int colIndex) {

        return getCell(rowIndex, colIndex).getValue() == 0;
    }

    public boolean isCellValid(int rowIndex, int colIndex, int value) {

        Set<Cell> relatedCells = getRelatedCells(rowIndex, colIndex);

        for (Cell relatedCell : relatedCells) {

            if (relatedCell.getValue() == value) return false;
        }

        return true;
    }

    public boolean setCell(int rowIndex, int colIndex, int value) {

        if (isCellEmpty(rowIndex, colIndex) && isCellValid(rowIndex, colIndex, value)) {

            getCell(rowIndex, colIndex).setValue(value);

            return true;
        }

        return false;
    }

    public Set<Cell> getRelatedCells(int rowIndex, int colIndex) {

        Set<Cell> relatedCells = new HashSet<>();

        Cell[] row = getRow(rowIndex); // Access row.

        Cell[] col = getCol(colIndex); // Access column.

        Cell[] subgrid = getSubgrid(rowIndex, colIndex); // Access subgrid.

        Collections.addAll(relatedCells, row); // Add row to Set.

        Collections.addAll(relatedCells, col); // Add col to Set.

        Collections.addAll(relatedCells, subgrid); // Add subgrid to Set.

        return relatedCells;
    }

    public int countEmptyCells() {

        int emptyCells = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                if (isCellEmpty(i, j)) emptyCells++;
            }
        }

        return emptyCells;
    }

    public int countSetCells() {

        int setCells = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                if (!isCellEmpty(i, j)) setCells++;
            }
        }

        return setCells;
    }

    public Cell[] getSetCells() {

        int count = countSetCells();

        Cell[] setCells = new Cell[count];

        int index = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                if (getCell(i, j).getValue() != 0) {

                    setCells[index] = getCell(i, j);

                    index++;
                }
            }
        }

        return setCells;
    }
}
