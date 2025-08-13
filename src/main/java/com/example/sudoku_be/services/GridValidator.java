package com.example.sudoku_be.services;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.sudoku_be.config.GridConfig.UNIT_REFERENCE;

public class GridValidator {

    public static boolean isCellValid(Cell cell) {

        return UNIT_REFERENCE.contains(cell.getValue());
    }

    public static boolean isUnitValid(Cell[] unit) {

        Set<Integer> unitSet = Arrays.stream(unit)
                .map(Cell::getValue)
                .collect(Collectors.toSet());

        return UNIT_REFERENCE.equals(unitSet);
    }

    public static boolean isUnitValid(Set<Cell> unitSet) {

        Set<Integer> valueSet = unitSet.stream()
                .map(Cell::getValue)
                .collect(Collectors.toSet());

        return UNIT_REFERENCE.equals(valueSet);
    }
}
