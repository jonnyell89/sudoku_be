package com.example.sudoku_be.services;

import java.util.Objects;

public class Cell {

    private int rowIndex;
    private int colIndex;
    private int value;

    public Cell(int rowIndex, int colIndex) {

        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.value = 0;
    }

    public Cell(int rowIndex, int colIndex, int value) {

        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.value = value;
    }

    public int getRowIndex() { return rowIndex; }

    public void setRowIndex(int rowIndex) { this.rowIndex = rowIndex; }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) { this.colIndex = colIndex; }

    public int getValue() {
        return value;
    }

    public void setValue(int value) { this.value = value; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true; // The same object in memory.
        if (object == null || getClass() != object.getClass()) return false; // Of different classes.
        Cell cell = (Cell) object; // Cast object to Cell.
        return rowIndex == cell.rowIndex && colIndex == cell.colIndex && value == cell.value; // Compare all fields.
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex, value);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "rowIndex=" + rowIndex +
                ", colIndex=" + colIndex +
                ", value=" + value +
                '}';
    }
}
