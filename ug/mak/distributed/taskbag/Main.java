package ug.mak.distributed.taskbag;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        // task bag
        TaskBag taskBag;
        try {
            // create task bag
            System.out.println("Creating task bag...");
            taskBag = new TaskBag();
            System.out.println("Done.");

            // create socket
            System.out.println("Setting up port...");
            Registry registry = LocateRegistry.getRegistry();
            System.out.println("Done.");

            // bind to it
            System.out.println("Binding...");
            registry.bind("taskbag", taskBag);
            System.out.println("Done.");

        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
