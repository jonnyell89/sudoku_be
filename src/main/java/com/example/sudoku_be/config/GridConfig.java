package com.example.sudoku_be.config;

import java.util.Set;

public class GridConfig {

    public static final int GRID_SIZE = 9;
    public static final int SUBGRID_SIZE = 3;
    public static final int CELL_DEFAULT = 0;

    public static final int UNIT_SIZE = 9;
    public static final int CONTAINING_UNIT_SIZE = 21; // Non-duplicated Cells from row plus column plus subgrid.

    public static final Set<Integer> UNIT_REFERENCE = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
}
