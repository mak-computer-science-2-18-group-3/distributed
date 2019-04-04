package ug.mak.distributed.master;

import ug.mak.distributed.Constants;
import ug.mak.distributed.maze.Cell;
import ug.mak.distributed.pairs.TaskPair;
import ug.mak.distributed.tasks.Task;
import ug.mak.distributed.maze.Maze;
import ug.mak.distributed.maze.RawMazes;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        try{
            // Getting the Registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1048);
            // Looking up the registry for the remote object
            RemoteMaster masterStub = (RemoteMaster) registry.lookup("taskbag");
            // Calling remote methods on the obtained object
            Maze maze = Maze.buildMaze(RawMazes.maze3);
            // Set-up maze
            masterStub.init(maze);
            // Add ug.mak.distributed.tasks to the TaskBag
            masterStub.addTask(new TaskPair(Constants.ADD_TASK, new Task(maze.getCell(0, 0))));

            // start Master task to monitor the maze progress
            while (true){
                pause();
                clearScreen();
                Maze currentMaze = masterStub.getMaze();

                // print current state of the maze and the number of running tasks
                currentMaze.print();
                System.out.println(masterStub.getAnalytics());

                // Checking the health state of worker
                ArrayList<Task> tasks = masterStub.getCurrentTasks();
                ArrayList<Task> toBeRestarted = new ArrayList<>();
                for (Task task : tasks) {
                    Cell cell = currentMaze.getCell(task.getStart().row, task.getStart().col);
                    if (task.isTaken() && !cell.isVisited()){
                        LocalTime time = LocalTime.now();

                        // If worker is irrespondent for 5 seconds, we re-allocate their task to another worker
                        if (time.minusSeconds(5).isAfter(task.getTimeTaken())){
                            System.out.println("Task with id: " + task.getId() + " up for more than 5 seconds...");
                            System.out.println("Resetting task...");
                            toBeRestarted.add(task);
                        }
                    }
                }
                masterStub.restart(toBeRestarted);

                if(masterStub.exitFound()){
                    System.out.println("Exit found!");
                    break;
                }
            }
        }catch (Exception ex){
//            ex.printStackTrace();
            System.out.println("Failed to start Master");
        }
    }

    private static void pause() {
        // wait for a second
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
