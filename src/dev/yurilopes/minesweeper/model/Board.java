package dev.yurilopes.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int columns;
    private int rows;
    private int mines;

    private final List<Field> fields = new ArrayList<>();

    public Board(int columns, int rows, int mines) {
        this.columns = columns;
        this.rows = rows;
        this.mines = mines;

        generateFields();
        associateNeighbors();
        drawMines();
    }

    private void drawMines() {
        for (int r = 0; r < rows; r++ ) {
            for (int c = 0; c < columns; c++) {
                fields.add(new Field(r, c));
            }
        }
    }

    private void associateNeighbors() {
        for (Field f1: fields) {
            for (Field f2: fields) {
                f1.addNeighbors(f2);
            }
        }
    }

    private void generateFields() {
    }


}
