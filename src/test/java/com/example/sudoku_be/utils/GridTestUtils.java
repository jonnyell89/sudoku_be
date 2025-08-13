package com.example.sudoku_be.utils;

import com.example.sudoku_be.services.Cell;

public class GridTestUtils {

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
