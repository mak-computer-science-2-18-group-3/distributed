package ug.mak.distributed.taskbag;

import ug.mak.distributed.tasks.TraverseTask;
import ug.mak.distributed.Pair;
import ug.mak.distributed.Task;
import ug.mak.distributed.master.RemoteMaster;
import ug.mak.distributed.maze.Cell;
import ug.mak.distributed.maze.Maze;
import ug.mak.distributed.worker.RemoteWorker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskBag extends UnicastRemoteObject implements RemoteMaster, RemoteWorker {
    private Maze maze;
    private List<Task> tasks;

    TaskBag() throws RemoteException {
        super();
        tasks = new ArrayList<>();
    }

    @Override
    public Pair pickTask() {
        if (maze == null || tasks.size() < 1){
            return null;
        }
        for (Task task : tasks) {
            if (task.isNotTaken()) {
                task.setMaze(maze);
                task.take();
                return new Pair("find path", task);
            }
        }
        return null;
    }

    @Override
    public boolean updateWithResult(Pair pair) {
        TraverseTask traverseTask = (TraverseTask) pair.getObject();
        List<Cell> visitedCells = traverseTask.getVisitedCells();
        for (Cell cell : visitedCells) {
            maze.getCell(cell.row, cell.col).visit();
        }
        return true;
    }

    @Override
    public boolean setupMaze(Maze maze) {
        this.maze = maze;
        return true;
    }

    @Override
    public boolean addTask(Pair pair) {
        Task task = (Task) pair.getObject();
        this.tasks.add(task);
        return true;
    }

    @Override
    public Maze getMaze() {
        return maze;
    }
}
