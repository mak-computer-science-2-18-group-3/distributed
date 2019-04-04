package ug.mak.distributed.master;

import ug.mak.distributed.maze.Maze;
import ug.mak.distributed.pairs.TaskPair;
import ug.mak.distributed.tasks.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteMaster extends Remote {
    boolean init(Maze maze) throws RemoteException;
    boolean addTask(TaskPair pair) throws RemoteException;
    Maze getMaze() throws RemoteException;
    String getAnalytics() throws RemoteException;

    boolean exitFound() throws RemoteException;

    ArrayList<Task> getCurrentTasks() throws RemoteException;

    void restart(ArrayList<Task> toBeRestarted) throws RemoteException;
}
