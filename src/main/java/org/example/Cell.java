package org.example;


public class Cell {
    private final int row;
    private final int column;

    private boolean visited = false;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setVisited(boolean visited) {
        System.out.println("Visited: " + row + ";" + column);
        this.visited = visited;
    }

    public boolean getVisited() {
        return this.visited;
    }
}
