package ug.mak.distributed.worker;

import tasks.TraverseTask;
import ug.mak.distributed.Constants;
import ug.mak.distributed.Pair;
import ug.mak.distributed.Task;
import ug.mak.distributed.maze.Cell;
import ug.mak.distributed.maze.Maze;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

class Work {

    private RemoteWorker remoteWorker;

    Work(RemoteWorker object) {
        remoteWorker = object;
    }

    // compute the task
    void computeTask(Pair pair) {
        Task task = (Task) pair.getObject();
        Maze maze = task.getMaze();
        Cell cell = task.getStart();

        Cell topCell, leftCell, bottomCell, rightCell;

        topCell = maze.getCell(cell.row - 1, cell.col);
        rightCell = maze.getCell(cell.row, cell.col + 1);
        bottomCell = maze.getCell(cell.row + 1, cell.col);
        leftCell = maze.getCell(cell.row, cell.col - 1);

        List<Cell> visitedCells = new ArrayList<>();

        while (cell != null && !cell.isVisited()) {

            String direction = "";
            int possibleDirections = 0;

            // for scenario where we have more than one possible direction
            List<Cell> nextTaskCells = new ArrayList<>();

            if (cell.isTopOpen() && topCell != null && !topCell.isVisited()) {
                possibleDirections++;
                nextTaskCells.add(topCell);
                direction = "TOP";
            } else if (cell.isRightOpen() && rightCell != null && !rightCell.isVisited()) {
                possibleDirections++;
                nextTaskCells.add(rightCell);
                direction = "RIGHT";
            } else if (cell.isBottomOpen() && bottomCell != null && !bottomCell.isVisited()) {
                possibleDirections++;
                nextTaskCells.add(bottomCell);
                direction = "BOTTOM";
            } else if (cell.isLeftOpen() && leftCell != null && !leftCell.isVisited()) {
                possibleDirections++;
                nextTaskCells.add(leftCell);
                direction = "LEFT";
            }

            cell.visit();
            visitedCells.add(cell);

            if (possibleDirections > 1) {
                endComputeWithNextTasks(nextTaskCells, visitedCells);
                return;
            } else if (possibleDirections < 1) {
                // dead end, probably end
                endCompute(visitedCells);
                return;
            } else if (maze.isEndCell(cell)) {
                endComputeWithEndCell(cell, visitedCells);
            } else {
                int row = cell.row;
                int col = cell.col;
                switch (direction) {
                    case "TOP": {
                        row = cell.row - 1;
                        break;
                    }
                    case "DOWN": {
                        row = cell.row + 1;
                        break;
                    }
                    case "LEFT": {
                        col = cell.col - 1;
                        break;
                    }
                    case "RIGHT": {
                        col = cell.col + 1;
                        break;
                    }
                }
                cell = maze.getCell(row, col);
            }
        }

        endCompute(visitedCells);
    }

    private void endComputeWithEndCell(Cell cell, List<Cell> visitedCells) {
        endCompute(visitedCells);

        // todo
    }

    private void endCompute(List<Cell> visitedCells) {
        Pair pairOut = new Pair(Constants.TRAVERSE_TASK, new TraverseTask(visitedCells));
        try {
            remoteWorker.updateWithResult(pairOut);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void endComputeWithNextTasks(List<Cell> nextTaskCells, List<Cell> visitedCells) {
        endCompute(visitedCells);
        for (Cell nextTaskCell : nextTaskCells) {
            Pair pairOut = new Pair(Constants.ADD_TASK, new Task(nextTaskCell));
            try {
                remoteWorker.addTask(pairOut);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
