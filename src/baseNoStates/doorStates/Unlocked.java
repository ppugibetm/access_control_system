package baseNoStates.doorStates;

import baseNoStates.Door;

public class Unlocked extends DoorState {
    /*This class represents the state of the door when it is unlocked.
    It extends the DoorState abstract class and provides specific behaviors
    for an unlocked door.*/

    public Unlocked(Door door_c) {
        super(door_c);
    }

    @Override
    public String getName() {
        return "unlocked";
    }

    // Lock the door.
    @Override
    public void lock() {
        System.out.println("Door locked.");
        door.setState(new Locked(door));
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
        }
        else {
            System.out.println("Door is already closed!");
        }
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