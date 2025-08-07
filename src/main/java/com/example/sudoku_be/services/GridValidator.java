package com.example.sudoku_be.services;

import org.springframework.stereotype.Service;

import static com.example.sudoku_be.config.GridConfig.CELL_DEFAULT;
import static com.example.sudoku_be.config.GridConfig.GRID_SIZE;

@Service
public class GridValidator {

    public void validate(Cell[][] grid) {

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

            for (int j = 0; j < grid[0].length; j++) {

                Cell cell = grid[i][j]; // Access cell.

                if (cell.getValue() < CELL_DEFAULT || cell.getValue() > GRID_SIZE) {
                    throw new IllegalArgumentException(String.format("Grid cell should contain values between %d and %d inclusive.", CELL_DEFAULT, GRID_SIZE));
                }
            }
        }
    }
}
