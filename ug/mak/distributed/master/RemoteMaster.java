package ug.mak.distributed.master;

import ug.mak.distributed.maze.Maze;
import ug.mak.distributed.pairs.TaskPair;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMaster extends Remote {
    boolean setupMaze(Maze maze) throws RemoteException;
    boolean addTask(TaskPair pair) throws RemoteException;
    Maze getMaze() throws RemoteException;
    String getAnalytics() throws RemoteException;
}
