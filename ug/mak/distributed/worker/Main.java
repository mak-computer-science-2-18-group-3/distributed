package ug.mak.distributed.worker;


import ug.mak.distributed.pairs.TaskPair;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args){
        try{
            // Getting the Registry
            Registry registry = LocateRegistry.getRegistry(null);
            // Looking up the registry for the remote object
            RemoteWorker remoteWorker = (RemoteWorker) registry.lookup("taskbag");

            Work worker = new Work(remoteWorker);
            // pairIn()
            TaskPair pairIn = remoteWorker.pickTask();
            // Compute Task
            // Result of computation
            worker.computeTask(pairIn);
        }catch (Exception ex){ ex.printStackTrace();}
    }

    public static void pause() {
        // wait for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
