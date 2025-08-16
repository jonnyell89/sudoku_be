package com.example.sudoku_be.enums;

public enum Difficulty {

    EASY(new int[]{45, 46, 47, 48, 49}),
    MEDIUM(new int[]{50, 51, 52, 53, 54}),
    HARD(new int[]{55, 56, 57, 58, 59}),
    EXPERT(new int[]{60, 61, 62, 63, 64});

    private final int[] range;

    Difficulty(int[] range) {

        this.range = range;
    }

    public int[] getRange() {
        return range;
    }
}
