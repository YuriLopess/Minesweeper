package dev.yurilopes.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final int column;
    private final int row;

    private boolean open;
    private boolean marked;
    private boolean mined;

    private List<Field> neighbors = new ArrayList<>();

    Field(int column, int row) {
        this.column = column;
        this.row = row;
    }
}
