package ug.mak.distributed.taskbag;

import tasks.TraverseTask;
import ug.mak.distributed.Pair;
import ug.mak.distributed.Task;
import ug.mak.distributed.maze.Maze;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskBag extends UnicastRemoteObject implements Master, Worker {
    private Maze maze;
    private List<Task> tasks;

    TaskBag() throws RemoteException {
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
        for (int i = 0; i <= traverseTask.getEnd()[0] - traverseTask.getStart()[0]; i++) {
            for (int j = 0; j < traverseTask.getEnd()[1] - traverseTask.getStart()[1]; j++) {
                maze.getCell(i, j).visit();
            }
        }
        return true;
    }

    @Override
    public boolean setupMaze(Maze maze) {
        this.maze = maze;
        return false;
    }

    @Override
    public boolean addTask(Pair pair) {
        Task task = (Task) pair.getObject();
        this.tasks.add(task);
        return true;
    }
}
