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

    public void print(){
        for (int i = 0; i < maze.size(); i++) {
            for (int j = 0; j < maze.get(i).size(); j++) {
                // todo
            }
        }
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
