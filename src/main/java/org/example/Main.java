package org.example;

public class Main {
    public static void main(String[] args) {
        /**
         * Because max depth of recursion, please do not exceed more that 33x33
         */
        Grid grid = new Grid(30, 30);
        grid.setupMaze();
        grid.startDps();
    }
}