package com.example.sudoku_be.utils;

import com.example.sudoku_be.enums.Difficulty;
import com.example.sudoku_be.services.Cell;
import com.example.sudoku_be.services.Grid;

import java.util.Arrays;
import java.util.Random;

public class SudokuGridUtils {

    private static final Random random = new Random();

    public static int[] shuffleValues(int[] values) { // Fisher-Yates Shuffle Algorithm.

        int[] shuffledCellValues = values.clone();

        for (int i = shuffledCellValues.length - 1; i > 0; i--) {

            int randomIndex = random.nextInt(i + 1);

            int j = shuffledCellValues[i];

            shuffledCellValues[i] = shuffledCellValues[randomIndex];

            shuffledCellValues[randomIndex] = j;
        }

        return shuffledCellValues;
    }

    public static Cell[] shuffleCells(Cell[] cells) { // Fisher-Yates Shuffle Algorithm.

        Cell[] shuffledCells = cells.clone();

        for (int i = shuffledCells.length - 1; i > 0; i--) {

            int randomIndex = random.nextInt(i + 1);

            Cell j = shuffledCells[i];

            shuffledCells[i] = shuffledCells[randomIndex];

            shuffledCells[randomIndex] = j;
        }

        return shuffledCells;
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

    public static int getRemovalTarget(Difficulty difficulty) {

        int[] difficultRange = difficulty.getRange();

        int randomIndex = random.nextInt(difficultRange.length);

        return difficultRange[randomIndex];
    }
}
