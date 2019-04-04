package ug.mak.distributed.worker;

import ug.mak.distributed.pairs.TaskPair;
import ug.mak.distributed.pairs.TraverseTaskPair;
import ug.mak.distributed.tasks.TraverseTask;
import ug.mak.distributed.Constants;
import ug.mak.distributed.tasks.Task;
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
    void computeTask(TaskPair pair) {
        Task task = pair.getTask();
        Maze maze = task.getMaze();
        Cell cell = task.getStart();

        List<Cell> visitedCells = new ArrayList<>();

        while (cell != null && !cell.isVisited()) {

            Cell topCell, leftCell, bottomCell, rightCell;

            topCell = maze.getCell(cell.row - 1, cell.col);
            rightCell = maze.getCell(cell.row, cell.col + 1);
            bottomCell = maze.getCell(cell.row + 1, cell.col);
            leftCell = maze.getCell(cell.row, cell.col - 1);

            String direction = "";
            int possibleDirections = 0;

            // for scenario where we have more than one possible direction
            List<Cell> nextTaskCells = new ArrayList<>();

            if (cell.isTopOpen() && topCell != null && !topCell.isVisited()) {
                possibleDirections++;
                nextTaskCells.add(topCell);
                direction = "TOP";
                System.out.println("Going " + direction + " is an option.");
            }
            if (cell.isRightOpen() && rightCell != null && !rightCell.isVisited()) {
                possibleDirections++;
                nextTaskCells.add(rightCell);
                direction = "RIGHT";
                System.out.println("Going " + direction + " is an option.");
            }
            if (cell.isBottomOpen() && bottomCell != null && !bottomCell.isVisited()) {
                possibleDirections++;
                nextTaskCells.add(bottomCell);
                direction = "BOTTOM";
                System.out.println("Going " + direction + " is an option.");
            }
            if (cell.isLeftOpen() && leftCell != null && !leftCell.isVisited()) {
                possibleDirections++;
                nextTaskCells.add(leftCell);
                direction = "LEFT";
                System.out.println("Going " + direction + " is an option.");
            }

            cell.visit();
            visitedCells.add(cell);

            System.out.println(possibleDirections + " possible directions found.");

            if (possibleDirections > 1) {
                System.out.println("Adding them to the task bag...");
                endComputeWithNextTasks(nextTaskCells, visitedCells);
                return;
            } else if (possibleDirections < 1) {
                // dead end, probably end
                System.out.println("No where to go. Finishing.");
                endCompute(visitedCells);
                return;
            } else if (maze.isEndCell(cell)) {
                System.out.println("Found end cell. Finishing.");
                endComputeWithEndCell(cell, visitedCells);
            } else {
                System.out.println("Going " + direction + "...");
                int row = cell.row;
                int col = cell.col;
                switch (direction) {
                    case "TOP": {
                        row = cell.row - 1;
                        break;
                    }
                    case "BOTTOM": {
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
        try {
            remoteWorker.exitFound(cell);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void endCompute(List<Cell> visitedCells) {
        TraverseTaskPair pairOut = new TraverseTaskPair(Constants.TRAVERSE_TASK, new TraverseTask(visitedCells));
        try {
            remoteWorker.updateWithResult(pairOut);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void endComputeWithNextTasks(List<Cell> nextTaskCells, List<Cell> visitedCells) {
        endCompute(visitedCells);
        for (Cell nextTaskCell : nextTaskCells) {
            TaskPair pairOut = new TaskPair(Constants.ADD_TASK, new Task(nextTaskCell));
            try {
                remoteWorker.addTask(pairOut);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
