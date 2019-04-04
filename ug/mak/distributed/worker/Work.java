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
            Pair result = worker.computeTask(pairIn);
            // Update
            if (worker.updateWithResult(result)) {
                return result;
            }
        }catch (Exception ex){ ex.printStackTrace();}
    }
}
