package dev.yurilopes.minesweeper.model;

import dev.yurilopes.minesweeper.excepiton.ExplosionException;

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

    boolean addNeighbors(Field neighbor) {
        boolean differentRow = row != neighbor.row;
        boolean differentColumn = column != neighbor.column;
        boolean diagonal = differentRow && differentColumn;

        int deltaRow = Math.abs(row - neighbor.row);
        int deltaColumn = Math.abs(column - neighbor.column);
        int generalDelta = deltaRow + deltaColumn;

        if (generalDelta == 1 && !diagonal) {
            neighbors.add(neighbor);
            return true;
        } else if (generalDelta == 2 && diagonal) {
            neighbors.add(neighbor);
            return true;
        } else {
            return false;
        }
    }

    void toggleMarking() {
        if (!open) {
            marked = !marked;
        }
    }

    boolean unlock() {  // Corrigido de "unclock" para "unlock"
        if (!open && !marked) {
            open = true;

            if (mined) {
                throw new ExplosionException();
            }

            if (safeNeighborhood()) {
                neighbors.forEach(n -> n.unlock());
            }
            return true;

        } else {
            return false;
        }
    }

    boolean safeNeighborhood() {
        return neighbors.stream().noneMatch(n -> n.mined);  // Corrigido de "=> n.mined" para "-> n.mined"
    }

    void toMine() {
        mined = true;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClose() {
        return !isOpen();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    boolean goalAchieved() {
        boolean unraveled = !mined && open;
        boolean secured = mined && marked;
        return unraveled || secured;
    }

    long minesInTheNeighborhood() {
        return neighbors.stream().filter(n -> n.mined).count();  // Corrigido de "=> n.mined" para "-> n.mined"
    }

    void restart() {
        open = false;
        mined = false;
        marked = false;
    }

    public String toString() {
        if(marked) {
            return "X";
        } else if (open && mined) {
            return "*";
        } else if (open && minesInTheNeighborhood() > 0) {
            return Long.toString(minesInTheNeighborhood());
        } else if (open) {
            return "";
        } else {
            return "?";
        }
    }
}
