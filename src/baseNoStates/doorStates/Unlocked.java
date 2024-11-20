package baseNoStates.doorStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import baseNoStates.Door;

public class Unlocked extends DoorState {
    /*This class represents the state of the door when it is unlocked.
    It extends the DoorState abstract class and provides specific behaviors
    for an unlocked door.*/
    private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone1.Milestone1Class");
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
        logger.info("Door locked.");
        door.setState(new Locked(door));
    }

    // Unlock the door (already unlocked).
    @Override
    public void unlock() {
        logger.warn("Door is already unlocked!");
    }

    // Close the door (if opened).
    @Override
    public void close() {
        if (!door.isClosed()) {
            logger.info("Door closed.");
            door.setClosed(true);
        }
        else {
            logger.warn("Door is already closed!");
        }
    }

    // Open the door (if closed).
    @Override
    public void open() {
        if (door.isClosed()) {
            logger.info("Door open.");
            door.setClosed(false);
        }
        else {
            logger.info("Door is already open!");
        }
    }
}