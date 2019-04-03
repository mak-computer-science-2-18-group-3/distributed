package ug.mak.distributed.worker;

public class WorkerCallback {
    public WorkerCallback(){}

    public static void main(String[] args){
        try{
            // Getting the Registry
            Registry registry = LocateRegistry.getRegistry(null);
            // Looking up the registry for the remote object
            TaskBag workerStub = (TaskBag) registry.lookup("taskbag");
            // Calling the remote methods on the obtained object
            // pairIn()
            Pair pairIn = (Pair) workerStub.pickTask();
            // Compute Task
            Worker worker = new Worker();
            Pair result = (Pair) worker.computeTask(pairIn);
            // Retrieve pair from TaskBag | readPair()
            Pair readPair = workerStub.updateWithResult(result);
            // add pair to the TaskBag | pairOut()
            Pair pairOut = workerStub.addTask(result);
        }catch (Exception ex){ ex.printStackTrace();}
    }
}
