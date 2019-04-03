package ug.mak.distributed.taskbag;

import ug.mak.distributed.Pair;
import ug.mak.distributed.Task;
import ug.mak.distributed.master.Callback;
import ug.mak.distributed.maze.Maze;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskBag extends UnicastRemoteObject implements Master, Client {
    private Maze maze;
    private Callback masterCallback;
    private List<Task> tasks;

    TaskBag() throws RemoteException {
        tasks = new ArrayList<>();
    }

    @Override
    public Pair pickTask() {
        if (maze == null || tasks.size() < 1){
            return null;
        }
        return null;
    }

    @Override
    public boolean updateWithResult(Pair pair) {
        return false;
    }

    @Override
    public boolean setupMaze(Maze maze, Callback callback) {
        this.maze = maze;
        this.masterCallback = callback;
        return false;
    }

    @Override
    public boolean addTask(Pair pair) {
        // todo
        return false;
    }
}
