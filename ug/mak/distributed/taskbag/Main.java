package ug.mak.distributed.taskbag;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {

    private static final int MASTER_PORT = 2000;

    public static void main(String[] args) {
        // task bag
        TaskBag taskBag = null;
        try {
            // create task bag
            System.out.println("Creating task bag...");
            taskBag = new TaskBag();
            System.out.println("Done.");

            // create socket
            System.out.println("Setting up port...");
            LocateRegistry.createRegistry(MASTER_PORT);
            System.out.println("Done.");

            // bind to it
            System.out.println("Binding...");
            Naming.rebind("rmi://localhost:" + MASTER_PORT + "/taskbag", taskBag);
            System.out.println("Done.");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
