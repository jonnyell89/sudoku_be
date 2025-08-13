package com.example.sudoku_be.services;

import org.springframework.stereotype.Service;

@Service
public class SudokuService {

    private Grid grid;

    public SudokuService() {

        this.grid = new Grid();
    }

    public void generatePuzzle() {

        GridGenerator.populateGrid(this.grid);
    }
}
