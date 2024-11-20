package baseNoStates.doorStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import baseNoStates.Clock;
import baseNoStates.Door;

public class Locked extends DoorState {
    /*This class represents the state of the door when it is locked.
    It extends the DoorState abstract class and provides specific behaviors
    for a locked door.*/
    private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone1.Milestone1Class");
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
        logger.info("Door is already locked!");
    }

    // Unlock the door.
    @Override
    public void unlock() {
        logger.info("Door unlocked.");
        door.setState(new Unlocked(door));
        logger.info(door.getStateName());

    }

    @Override
    public void unlock_shortly() {
        logger.debug("-------------Door unlocked temporary.");
        Clock clock = Clock.getInstance();


        door.setState(new UnlockedShortly(door));// la porta pasa a l'estat "Unlocked_shortly"
        logger.info(door.getStateName());
    }

    // Open the door (cannot, it is locked - it can only remain open if it is already).
    @Override
    public void open() {
        if (door.isClosed()) {
            logger.info("Cannot open a locked door!");
        }
        else {
            logger.info("Door already open.");
        }
    }

    // Close the door (if open).
    @Override
    public void close() {
        if (!door.isClosed()) {
            logger.info("Door closed.");
            door.setClosed(true);
        }
        else {
            logger.info("Door is already closed!");
        }
    }
}
