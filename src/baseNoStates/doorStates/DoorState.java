package baseNoStates.doorStates;

import baseNoStates.Door;

// We used the State Design Pattern because it allows the door’s behavior to change dynamically
// based on its state (locked or unlocked). This pattern makes the system more flexible and easier
// to extend with new states, without modifying the existing code.

// This class defines the common behaviors (methods) that all door states must implement.
// It ensures that all concrete states (like LockedState and UnlockedState) provide implementations
// for opening, closing, locking, and unlocking the door.
public abstract class DoorState {
    protected final Door door; // Protected variable to hold a reference of the Door object

    // Constructor (initializes the DoorState with a Door object)
    public DoorState(Door doorC) {
        door = doorC;
    }

    // Method to handle opening of the door (overridden by subclasses)
    public void open() {
    }

    // Method to handle closing of the door (overridden by subclasses)
    public void close() {
    }

    // Method to handle locking of the door (overridden by subclasses)
    public void lock() {
    }

    // Method to handle unlocking of the door (overridden by subclasses)
    public void unlock() {
    }

    public void unlockShortly() {
    }

    // Abstract method to get the name of the door state (overridden by subclasses)
    public abstract String getName();
}