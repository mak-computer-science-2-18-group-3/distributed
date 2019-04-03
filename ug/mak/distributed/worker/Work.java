package ug.mak.distributed.worker;

import ug.mak.distributed.taskbag;
import worker.Worker;

public class Work {
    public Work(){}

    public static void main(String[] args){
        try{
            // Getting the Registry
            Registry registry = LocateRegistry.getRegistry(null);
            // Looking up the registry for the remote object
            TaskBag workStub = (TaskBag) registry.lookup("taskbag");

            Worker worker = new Worker(workStub);
            // pairIn()
            Pair pairIn = worker.pickTask();
            // Compute Task
            // Result of computation
            worker.computeTask(pairIn);
            // Add result to taskbag
            // worker.addTask(result);
            // Next Task
            // If no more tasks
            // Pair nextTask = pairIn++;
            // add next task for next worker

        }catch (Exception ex){ ex.printStackTrace();}
    }
}
