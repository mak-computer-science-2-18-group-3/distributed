package ug.mak.distributed;

import ug.mak.distributed.maze.Cell;
import ug.mak.distributed.maze.Maze;

public class Task {
    boolean taken;
    Maze maze;
    private Cell start;

    public Task(Cell start){
        this.start = start;
        taken = false;
    }

    public void take() {
        this.taken = true;
    }

    public boolean isNotTaken() {
        return !taken;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Cell getStart() {
        return start;
    }
}
