package com.example.sudoku_be.utils;

import com.example.sudoku_be.services.Cell;
import com.example.sudoku_be.services.Grid;

import java.util.Arrays;
import java.util.Random;

public class GridGeneratorUtils {

    private static final Random random = new Random();

    public static int[] shuffleCellValues() { // Fisher-Yates Shuffle Algorithm.

        int[] cellValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = cellValues.length - 1; i > 0; i--) {

            int randomIndex = random.nextInt(i + 1);

            int j = cellValues[i];
            cellValues[i] = cellValues[randomIndex];
            cellValues[randomIndex] = j;
        }

        return cellValues;
    }

    public static void printGrid(Grid grid) {

        Cell[][] sudokuGrid = grid.getGrid(); // Access grid.

        for (int i = 0; i < sudokuGrid.length; i++) { // Iterate over rows.

            Cell[] row = grid.getRow(i); // Access row.

            int[] rowValues = new int[row.length];

            for (int j = 0; j < row.length; j++) { // Iterate over row.

                rowValues[j] = row[j].getValue(); // Append Cell.value to array.
            }

            System.out.println(Arrays.toString(rowValues));
        }
    }
}
