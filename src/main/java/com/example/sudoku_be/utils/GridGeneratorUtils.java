package com.example.sudoku_be.utils;

import com.example.sudoku_be.services.Cell;
import com.example.sudoku_be.services.Grid;

import java.util.Arrays;
import java.util.Random;

public class GridGeneratorUtils {

    private static final Random random = new Random();

    public static int[] shuffleCellValues(int[] cellValues) { // Fisher-Yates Shuffle Algorithm.

        int[] shuffledCellValues = cellValues.clone();

        for (int i = shuffledCellValues.length - 1; i > 0; i--) {

            int randomIndex = random.nextInt(i + 1);

            int j = shuffledCellValues[i];

            shuffledCellValues[i] = shuffledCellValues[randomIndex];

            shuffledCellValues[randomIndex] = j;
        }

        return shuffledCellValues;
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
