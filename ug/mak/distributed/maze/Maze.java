package ug.mak.distributed.maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Serializable {
    private ArrayList<List<Cell>> maze;

    private Maze(){
        maze = new ArrayList<>();
    }

    private void addRow(List<Cell> cells){
        maze.add(cells);
    }

    public boolean isEndCell(Cell cell){
        return cell.row == maze.size() -1 && cell.col == maze.get(0).size() - 1;
    }

    public Cell getCell(int row, int col){
        try {
          return maze.get(row).get(col);
        }catch (Exception e) {
          return null;
        }
    }

    public void print() {
        for (List<Cell> cells : maze) {
            for (Cell cell : cells) {
                System.out.print(cell.isTopOpen() ? "+   " : "+---");
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
            System.out.print(cell.isBottomOpen() ? "+   " : "+---");
        }
        System.out.println("+");
    }

    public static Maze buildMaze(int[][][] rawMaze){
        Maze maze = new Maze();
        for (int i = 0; i < rawMaze.length; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < rawMaze[i].length; j++) {
              int[] cell = rawMaze[i][j];
                row.add(new Cell(cell[0], cell[1], cell[2], cell[3], i, j));
            }
            maze.addRow(row);
        }
        return maze;
    }
}
