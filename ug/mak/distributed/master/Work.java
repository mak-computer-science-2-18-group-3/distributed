package ug.mak.distributed.master;

public class MasterCallback{
    public MasterCallback(){}

    public static void main(String[] args){
        try{
            // Getting the Registry
            Registry registry = LocateRegistry.getRegistry(null);
            // Looking up the registry for the remote object
            TaskBag masterStub = (TaskBag) registry.lookup("taskbag");
            // Calling remote methods on the obtained object
            // Set-up maze
            masterStub.setupMaze(Maze maze, this);
            // Add tasks to the TaskBag
            masterStub.addTask(Pair pair);
        }catch (Exception ex){ ex.printStackTrace();}
    }
}
