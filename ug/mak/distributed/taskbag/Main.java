package ug.mak.distributed.taskbag;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        clearScreen();
        // task bag
        TaskBag taskBag;
        try {
            // create task bag
            System.out.println("Creating task bag...");
            taskBag = new TaskBag();
            System.out.println("Done.");

            // create socket
            System.out.println("Setting up port...");
            System.setProperty("java.rmi.server.hostname","localhost");
            Registry registry = LocateRegistry.createRegistry(1048);
            System.out.println("Done.");

            // bind to it
            System.out.println("Binding...");
            registry.bind("taskbag", taskBag);
            System.out.println("Done.");

        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
