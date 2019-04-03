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

            // pairIn()
            Pair pairIn = (Pair) workStub.pickTask();
            // Compute Task
            Worker worker = new Worker();
            // Result of computation
            Pair result = (Pair) worker.computeTask(pairIn);
            // Add result to taskbag
            workStub.addTask(result);
            // Next Task
            // If no more tasks
            Pair nextTask = pairIn++;
            // add next task for next worker

        }catch (Exception ex){ ex.printStackTrace();}
    }
}
