package com.example.sudoku_be.utils;

import com.example.sudoku_be.services.Cell;

public class GridUtils {

    public static Cell[][] initGrid(int gridSize) {

        Cell[][] grid = new Cell[gridSize][gridSize]; // Two-dimensional array containing nulls, or Cell references.

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j <grid[0].length; j++) {

                grid[i][j] = new Cell(i, j); // Initialises grid[i][j] with Cell object.
            }
        }

        return grid;
    }
}
