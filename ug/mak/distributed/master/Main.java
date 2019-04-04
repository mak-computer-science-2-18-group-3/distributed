package ug.mak.distributed.master;

import ug.mak.distributed.Constants;
import ug.mak.distributed.pairs.TaskPair;
import ug.mak.distributed.tasks.Task;
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
            Maze maze = Maze.buildMaze(RawMazes.maze2);
            // Set-up maze
            masterStub.init(maze);
            // Add ug.mak.distributed.tasks to the TaskBag
            masterStub.addTask(new TaskPair(Constants.ADD_TASK, new Task(maze.getCell(0, 0))));

            while (true){
                pause();
                clearScreen();
                masterStub.getMaze().print();
                System.out.println(masterStub.getAnalytics());
                if(masterStub.exitFound()){
                    break;
                }
            }
        }catch (Exception ex){ ex.printStackTrace();}
    }

    private static void pause() {
        // wait for a second
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
