package ug.mak.distributed.tasks;

import ug.mak.distributed.maze.Cell;
import ug.mak.distributed.maze.Maze;

import java.io.Serializable;
import java.time.LocalTime;

public class Task implements Serializable {
    int id;
    boolean taken;
    Maze maze;
    private Cell start;
    LocalTime timeTaken;

    public Task(Cell start, int id){
        this.start = start;
        taken = false;
        this.id = id;
    }

    public Task(Cell start){
        this.start = start;
        taken = false;
        this.id = -1;
    }

    public void take() {
        this.taken = true;
        this.timeTaken = LocalTime.now();
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

    public boolean isTaken() {
        return taken;
    }

    public LocalTime getTimeTaken() {
        return timeTaken;
    }

    public void reset() {
        this.taken = false;
        this.timeTaken = null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
