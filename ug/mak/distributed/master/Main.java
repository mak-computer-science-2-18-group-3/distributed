package ug.mak.distributed.master;

import ug.mak.distributed.Constants;
import ug.mak.distributed.Pair;
import ug.mak.distributed.Task;
import ug.mak.distributed.maze.Maze;
import ug.mak.distributed.maze.RawMazes;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args){
        try{
            // Getting the Registry
            Registry registry = LocateRegistry.getRegistry(null);
            // Looking up the registry for the remote object
            RemoteMaster masterStub = (RemoteMaster) registry.lookup("taskbag");
            // Calling remote methods on the obtained object
            Maze maze = Maze.buildMaze(RawMazes.maze1);
            // Set-up maze
            masterStub.setupMaze(maze);
            // Add tasks to the TaskBag
            masterStub.addTask(new Pair(Constants.ADD_TASK, new Task(maze.getCell(0, 0))));

            while (true){
                pause();
                masterStub.getMaze().print();
            }
        }catch (Exception ex){ ex.printStackTrace();}
    }

    private static void pause() {
        // wait for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
