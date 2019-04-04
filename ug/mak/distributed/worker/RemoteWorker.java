package ug.mak.distributed.worker;

import ug.mak.distributed.pairs.TaskPair;
import ug.mak.distributed.pairs.TraverseTaskPair;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteWorker extends Remote {
    TaskPair pickTask() throws RemoteException;
    boolean updateWithResult(TraverseTaskPair pair) throws RemoteException;
    boolean addTask(TaskPair pair) throws RemoteException;
}
