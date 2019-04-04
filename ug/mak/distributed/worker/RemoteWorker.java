package ug.mak.distributed.worker;

import ug.mak.distributed.Pair;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteWorker extends Remote {
    Pair pickTask() throws RemoteException;
    boolean updateWithResult(Pair pair) throws RemoteException;
    boolean addTask(Pair pair) throws RemoteException;
}
