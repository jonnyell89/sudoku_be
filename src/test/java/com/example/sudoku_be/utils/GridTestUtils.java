package com.example.sudoku_be.utils;

import com.example.sudoku_be.services.Cell;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.sudoku_be.config.GridConfig.UNIT_REFERENCE;

public class GridTestUtils {

    public static boolean isCellValid(Cell cell) {

        return UNIT_REFERENCE.contains(cell.getValue());
    }

    public static boolean isUnitValid(Cell[] unit) {

        Set<Integer> unitSet = Arrays.stream(unit)
                .map(Cell::getValue)
                .collect(Collectors.toSet());

        return UNIT_REFERENCE.equals(unitSet);
    }

    public static boolean isUnitSetValid(Set<Cell> unitSet) {

        Set<Integer> valueSet = unitSet.stream()
                .map(Cell::getValue)
                .collect(Collectors.toSet());

        return UNIT_REFERENCE.equals(valueSet);
    }

    public static Cell[][] convertIntGridToCellGrid(int[][] intGrid) {

        Cell[][] cellGrid = new Cell[intGrid.length][intGrid.length];

        for (int i = 0; i < cellGrid.length; i++) {

            for (int j = 0; j < cellGrid[i].length; j++) {

                cellGrid[i][j] = new Cell(i, j);
                cellGrid[i][j].setValue(intGrid[i][j]);
            }
        }

        return cellGrid;
    }

    public static int[][] convertCellGridToIntGrid(Cell[][] cellGrid) {

        int[][] intGrid = new int[cellGrid.length][cellGrid.length];

        for (int i = 0; i < intGrid.length; i++) {

            for (int j = 0; j < intGrid[i].length; j++) {

                intGrid[i][j] = cellGrid[i][j].getValue();
            }
        }

        return intGrid;
    }
}
