package ug.mak.distributed.master;

import ug.mak.distributed.Pair;
import ug.mak.distributed.maze.Maze;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMaster extends Remote {
    boolean setupMaze(Maze maze) throws RemoteException;
    boolean addTask(Pair pair) throws RemoteException;
    Maze getMaze() throws RemoteException;
}
