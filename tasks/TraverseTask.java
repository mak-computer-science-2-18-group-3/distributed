package tasks;

import ug.mak.distributed.maze.Cell;

import java.util.List;

public class TraverseTask {
    private List<Cell> visitedCells;

    public TraverseTask(List<Cell> visitedCells){
        this.visitedCells = visitedCells;
    }

    public List<Cell> getVisitedCells() {
        return visitedCells;
    }
}
