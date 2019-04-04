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

            while(true){
                pause();
                // pairIn()
                TaskPair pairIn = remoteWorker.pickTask();

                if (pairIn == null){
                    System.out.println("No task found. Waiting...");
                    continue;
                }
                // Compute Task
                // Result of computation
                worker.computeTask(pairIn);
            }
        }catch (Exception ex){ ex.printStackTrace();}
    }

    private static void pause() {
        // wait for half a second
        try {
            Thread.sleep(500);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
