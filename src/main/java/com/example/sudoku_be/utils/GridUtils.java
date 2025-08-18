package com.example.sudoku_be.utils;

import com.example.sudoku_be.services.Cell;

import static com.example.sudoku_be.config.GridConfig.CELL_VALUES_DEFAULT;
import static com.example.sudoku_be.config.GridConfig.GRID_SIZE;

public class GridUtils {

    public static Cell[][] initGrid(int gridSize) {

        Cell[][] grid = new Cell[gridSize][gridSize]; // Two-dimensional array containing nulls, or Cell references.

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j <grid[i].length; j++) {

                grid[i][j] = new Cell(i, j); // Initialises grid[i][j] with Cell object.
            }
        }

        return grid;
    }

    public static Cell[][] validateGrid(Cell[][] grid) {

        if (grid == null) {
            throw new IllegalArgumentException("Grid should not be null.");
        }

        if (grid.length != GRID_SIZE) {
            throw new IllegalArgumentException(String.format("Grid should contain %d rows.", GRID_SIZE));
        }

        for (int i = 0; i < grid.length; i++) {

            if (grid[i].length != GRID_SIZE) {
                throw new IllegalArgumentException(String.format("Grid should contain %d columns.", GRID_SIZE));
            }

            for (int j = 0; j < grid[i].length; j++) {

                Cell cell = grid[i][j]; // Access cell.

                if (cell.getValue() < CELL_VALUES_DEFAULT || cell.getValue() > GRID_SIZE) {
                    throw new IllegalArgumentException(String.format("Grid cell should contain values between %d and %d inclusive.", CELL_VALUES_DEFAULT, GRID_SIZE));
                }
            }
        }

        return grid;
    }
}
