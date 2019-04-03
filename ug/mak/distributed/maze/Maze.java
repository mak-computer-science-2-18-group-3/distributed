package ug.mak.distributed.maze;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private List<List<Cell>> maze;

    private Maze(){
        maze = new ArrayList<>();
    }

    private void addRow(List<Cell> cells){
        maze.add(cells);
    }

    public Cell getCell(int row, int col){
        return maze.get(row).get(col);
    }

    public void print() {
        for (List<Cell> cells : maze) {
            for (Cell cell : cells) {
                System.out.print(cell.isTopOpen() ? "+  " : "+--");
            }
            System.out.println("+");
            for (Cell cell : cells) {
                System.out.print(cell.isLeftOpen() ? " " : "|");
                System.out.print(cell.isVisited() ? " X " : "   ");
            }
            System.out.println("|");
        }
        List<Cell> bottomRow = maze.get(maze.size() - 1);

        for (Cell cell : bottomRow) {
            System.out.print(cell.isBottomOpen() ? "+  " : "+--");
        }
        System.out.println("+");
    }

    public static Maze buildMaze(int[][][] rawMaze){
        Maze maze = new Maze();
        for (int[][] ints : rawMaze) {
            List<Cell> row = new ArrayList<>();
            for (int[] cell : ints) {
                row.add(new Cell(cell[0], cell[1], cell[2], cell[3]));
            }
            maze.addRow(row);
        }
        return maze;
    }
}
