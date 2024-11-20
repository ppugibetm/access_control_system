package baseNoStates.doorStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baseNoStates.Door;
import java.util.Observer;
import java.util.Observable;
import java.util.concurrent.locks.Lock;
import baseNoStates.Clock;
import baseNoStates.requests.RequestReader;
import baseNoStates.requests.RequestRefresh;

public class UnlockedShortly extends DoorState implements Observer {
    private static final Clock clock = Clock.getInstance();
    private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone1.Milestone1Class");
    public UnlockedShortly(Door door_c) {
        super(door_c);
        this.clock.addObserver(this);
        this.clock.startTimer(10);
    }

    @Override
    public String getName() {
        return "unlocked_shortly";
    }

    @Override
    public void lock() {
        logger.info("Door locked.");
        door.setState(new Locked(door));
    }

    @Override
    public void unlock() {

        logger.info("Door is already unlocked!");
    }

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

    // Update method (part of the Observer pattern)

    @Override
    public void update(Observable o, Object arg) {
        if (!door.isClosed()) {
            door.setState(new Propped(door));
            logger.warn("Changed to propped");
        }
        else {
            door.setState(new Locked(door));
        }

        RequestRefresh requestRefresh = new RequestRefresh();
        requestRefresh.process();
    }
}