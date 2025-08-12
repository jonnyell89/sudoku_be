package com.example.sudoku_be.utils;

import java.util.Random;

public class GridGeneratorUtils {

    private static final Random random = new Random();

    public static int[] shuffleCellValues(int[] cellValues) { // Fisher-Yates Shuffle Algorithm

        for (int i = cellValues.length - 1; i > 0; i--) {

            int randomIndex = random.nextInt(i + 1);

            int j = cellValues[i];
            cellValues[i] = cellValues[randomIndex];
            cellValues[randomIndex] = j;
        }

        return cellValues;
    }
}
