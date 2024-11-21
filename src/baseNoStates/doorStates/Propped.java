package baseNoStates.doorStates;

import baseNoStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class represents the state of the door when it is unlocked.
// It extends the DoorState abstract class and provides specific behaviors
// for an unlocked door.
public class Propped extends DoorState {
    private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone1.Milestone1Class");

    public Propped(Door doorC) {
        super(doorC);
    }

    @Override
    public String getName() {
        return "propped";
    }

    // Lock the door.
    @Override
    public void lock() {
        logger.info("--------Cannot lock the door as it is open."); // S'ha de tancar la porta primer
    }

    // Unlock the door (already unlocked).
    @Override
    public void unlock() {
        logger.info("Door is already unlocked!");
    }

    // Close the door (if opened).
    @Override
    public void close() {
        if (!door.isClosed()) {
            logger.info("Door closed.");
            door.setClosed(true);
            logger.info("Door locked after incidence being solved."); // Quan tanquem la porta desde l'estat propped, bloquejarem la porta
            door.setState(new Locked(door));
        } else {
            logger.info("Door is already closed!");
        }
    }

    @Override
    public void unlockShortly() {
        logger.info("Door is already unlocked!");
    }

    // Open the door (if closed).
    @Override
    public void open() {
        if (door.isClosed()) {
            logger.info("Door open.");
            door.setClosed(false);
        } else {
            logger.info("Door is already open!");
        }
    }
}