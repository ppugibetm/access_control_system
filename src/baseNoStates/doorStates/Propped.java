package baseNoStates.doorStates;

import baseNoStates.Door;

public class Propped extends DoorState {
    /*This class represents the state of the door when it is unlocked.
    It extends the DoorState abstract class and provides specific behaviors
    for an unlocked door.*/

    public Propped(Door door_c) {
        super(door_c);
    }

    @Override
    public String getName() {
        return "propped";
    }

    // Lock the door.
    @Override
    public void lock() {
        System.out.println("--------Cannot lock the door as it is open.");// S'ha de tancar la porta primer

    }

    // Unlock the door (already unlocked).
    @Override
    public void unlock() {
        System.out.println("Door is already unlocked!");
    }

    // Close the door (if opened).
    @Override
    public void close() {
        if (!door.isClosed()) {
            System.out.println("Door closed.");
            door.setClosed(true);
            System.out.println("Door locked after incidence being solved.");//Quan tanquem la porta desde l'estat propped, bloquejarem la porta
            door.setState(new Locked(door));
        }
        else {
            System.out.println("Door is already closed!");
        }
    }

    @Override
    public void unlock_shortly(){
        System.out.println("Door is already unlocked!");
    }

    // Open the door (if closed).
    @Override
    public void open() {
        if (door.isClosed()) {
            System.out.println("Door open.");
            door.setClosed(false);
        }
        else {
            System.out.println("Door is already open!");
        }
    }
}