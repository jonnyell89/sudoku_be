package com.example.sudoku_be.config;

import java.util.Set;

public class GridTestConfig {

    public static final Set<Integer> DUMMY_UNIT = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public static final int[][] DUMMY_GRID = new int[][]{

            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 1, 5, 6, 7, 8, 9, 4},
            {5, 6, 4, 8, 9, 1, 2, 3, 7},
            {8, 9, 7, 2, 3, 4, 5, 6, 1},
            {3, 1, 5, 6, 7, 8, 9, 4, 2},
            {6, 4, 8, 9, 1, 2, 3, 7, 5},
            {9, 7, 2, 3, 4, 5, 6, 1, 8}
    };
}
