package ug.mak.distributed.tasks;

import ug.mak.distributed.maze.Cell;

import java.io.Serializable;
import java.util.List;

public class TraverseTask implements Serializable {
    private List<Cell> visitedCells;

    public TraverseTask(List<Cell> visitedCells){
        this.visitedCells = visitedCells;
    }

    public List<Cell> getVisitedCells() {
        return visitedCells;
    }
}
