package baseNoStates.doorStates;

import baseNoStates.Door;

public class Locked extends DoorState {
    /*This class represents the state of the door when it is locked.
    It extends the DoorState abstract class and provides specific behaviors
    for a locked door.*/

    public Locked(Door door_c) {
        super(door_c);
    }

    @Override
    public String getName() {
        return "locked";
    }

    // Lock the door (already locked).
    @Override
    public void lock() {
        System.out.println("Door is already locked!");
    }

    // Unlock the door.
    @Override
    public void unlock() {
        System.out.println("Door unlocked.");
        door.setState(new Unlocked(door));
        System.out.println(door.getStateName());

    }

    @Override
    public void unlock_shortly() {


        System.out.println("-------------Door unlocked temporary.");
        door.setState(new UnlockedShortly(door));// la porta pasa a l'estat "Unlocked_shortly"
        System.out.println(door.getStateName());

        try {
            Thread.sleep(10000); // 10000 milliseconds = 10 seconds
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted: " + e.getMessage());
        }

        // Si passats 10 segos desde l'accio "Unlock_shortly", si la porta esta tancada
        // es passa a l'estat "Locked" , si està oberta, passa a l'estat "Propped"

        if (door.isClosed()) {
            System.out.println("-------------Door is locked");
            door.setState(new Locked(door));
        }else{
            System.out.println("-------------Door was left opened! Switching to propped state");// propped
            door.setState(new Propped(door));
        }
    }

    // Open the door (cannot, it is locked - it can only remain open if it is already).
    @Override
    public void open() {
        if (door.isClosed()) {
            System.out.println("Cannot open a locked door!");
        }
        else {
            System.out.println("Door already open.");
        }
    }

    // Close the door (if open).
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
}
