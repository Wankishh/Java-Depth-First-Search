package org.example;

import java.util.*;

public class Grid {
    private final int columns;
    private final int rows;

    private int visited = 0;

    private final List<Cell> cells;

    private Cell activeCell;

    private final Stack<Cell> stack;

    public Grid(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.cells = new ArrayList<>();
        this.stack = new Stack<>();
    }

    public void setupMaze() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                Cell cell = new Cell(row, col);
                cells.add(cell);
            }
        }

        this.activeCell = cells.get(0);
        this.activeCell.setVisited(true);
        this.visited = 1;
    }

    public void startDps() {
        this.recursivelyFindCell();
    }

    public int getCellIndex(int row, int col) {
        if (row < 0 || col < 0 || row > columns - 1 || col > rows - 1) {
            return -1;
        }

        return row + col * columns;
    }

    private Optional<Cell> getCellByIndex(int index) {
        Optional<Cell> cell = Optional.empty();

        try {
            cell = Optional.ofNullable(this.cells.get(index));
        } catch(IndexOutOfBoundsException ignored) { }

        return cell;
    }

    public Optional<Cell> getNextCell() {
        int currentRow = this.activeCell.getRow();
        int currentCol = this.activeCell.getColumn();
        List<Cell> neighbours = new ArrayList<>();

        int topIndex = this.getCellIndex(currentRow, currentCol - 1);
        int rightIndex = this.getCellIndex(currentRow + 1, currentCol);
        int bottomIndex  = this.getCellIndex(currentRow, currentCol + 1);
        int leftIndex = this.getCellIndex(currentRow - 1, currentCol);

        Optional<Cell> top = this.getCellByIndex(topIndex);
        Optional<Cell> right = this.getCellByIndex(rightIndex);
        Optional<Cell> bottom  = this.getCellByIndex(bottomIndex);
        Optional<Cell> left = this.getCellByIndex(leftIndex);

        if(top.isPresent() && !top.get().getVisited()) {
            neighbours.add(top.get());
        }

        if(right.isPresent() && !right.get().getVisited()) {
            neighbours.add(right.get());
        }

        if(bottom.isPresent() && !bottom.get().getVisited()) {
            neighbours.add(bottom.get());
        }

        if(left.isPresent() && !left.get().getVisited()) {
            neighbours.add(left.get());
        }

        if(neighbours.size() > 0) {
            int min = 0;
            int max = neighbours.size() - 1;

            int randomInt = (int)Math.floor(Math.random()*(max-min+1)+min);
            return Optional.ofNullable(neighbours.get(randomInt));
        }

        return Optional.empty();
    }

    public void recursivelyFindCell() {
        Optional<Cell> optionalCell = this.getNextCell();
        if(optionalCell.isPresent()) {
            Cell next = optionalCell.get();
            if(!next.getVisited()) {
                visited++;
            }
            next.setVisited(true);
            this.activeCell = next;
            this.stack.push(next);
            this.recursivelyFindCell();
        } else if(!this.stack.isEmpty()) {
            this.activeCell = this.stack.pop();
            this.recursivelyFindCell();
        } else {
            System.out.println("No More cells. Finished, Total Visited: " + visited + " of " + (rows * columns));
        }
    }
}
