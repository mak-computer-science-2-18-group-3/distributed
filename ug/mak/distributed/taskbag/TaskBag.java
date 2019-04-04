package ug.mak.distributed.taskbag;

import ug.mak.distributed.pairs.TaskPair;
import ug.mak.distributed.pairs.TraverseTaskPair;
import ug.mak.distributed.tasks.TraverseTask;
import ug.mak.distributed.tasks.Task;
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
    private ArrayList<Task> tasks;
    private Cell exit;

    TaskBag() throws RemoteException {
        super();
        tasks = new ArrayList<>();
    }

    @Override
    public synchronized TaskPair pickTask() {
        if (maze == null || tasks.size() < 1){
            return null;
        }
        for (Task task : tasks) {
            if (task.isNotTaken()) {
                task.setMaze(maze);
                task.take();
                return new TaskPair("find path", task);
            }
        }
        return null;
    }

    @Override
    public synchronized boolean updateWithResult(TraverseTaskPair pair) {
        TraverseTask traverseTask = pair.getTraverseTask();
        List<Cell> visitedCells = traverseTask.getVisitedCells();
        for (Cell cell : visitedCells) {
            maze.getCell(cell.row, cell.col).visit();
        }
        return true;
    }

    @Override
    public boolean init(Maze maze) {
        this.tasks.clear();
        exit = null;
        this.maze = maze;
        return true;
    }

    @Override
    public synchronized boolean addTask(TaskPair pair) {
        Task task = pair.getTask();
        task.setId(tasks.size());
        this.tasks.add(task);
        return true;
    }

    @Override
    public synchronized void exitFound(Cell cell) {
        exit = cell;
        tasks.clear();
    }

    @Override
    public Maze getMaze() {
        return maze;
    }

    @Override
    public String getAnalytics() {
        int remainingTasks = 0;
        for (Task task : tasks) {
            if (task.isNotTaken()) {
                remainingTasks++;
            }
        }
        return "Remaining tasks: " + remainingTasks + "\n";
    }

    @Override
    public boolean exitFound() {
        return exit != null;
    }

    @Override
    public ArrayList<Task> getCurrentTasks(){
        return tasks;
    }

    @Override
    public void restart(ArrayList<Task> toBeRestarted){
        for (Task task : toBeRestarted) {
            tasks.get(task.getId()).reset();
        }
    }
}
