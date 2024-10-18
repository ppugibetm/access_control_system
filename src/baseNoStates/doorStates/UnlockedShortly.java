package baseNoStates.doorStates;

import baseNoStates.Door;

public class UnlockedShortly extends DoorState {
    public UnlockedShortly(Door door_c) {
        super(door_c);
    }

    public String getName() {
        return "unlocked";
    }

    public void lock() {
        System.out.println("Door locked.");
        door.setState(new Locked(door));
    }

    public void unlock() {
        System.out.println("Door is already unlocked!");
    }

    public void close() {
        if (!door.isClosed()) {
            System.out.println("Door closed.");
            door.setClosed(true);
        }
        else {
            System.out.println("Door is already closed!");
        }
    }

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