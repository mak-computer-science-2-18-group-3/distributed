package ug.mak.distributed.maze;

public class Main {
    public static void main(String[] args) {
        Maze maze = Maze.buildMaze(RawMazes.maze1);
        maze.print();
    }
}
