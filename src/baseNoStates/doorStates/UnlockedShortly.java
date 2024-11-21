package baseNoStates.doorStates;

import baseNoStates.Clock;
import baseNoStates.Door;
import baseNoStates.requests.RequestRefresh;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Represents a temporary state where the door remains unlocked for a short time.
// Implements the State pattern for door behavior and the Observer pattern to monitor
// the global clock for state transitions.
@SuppressWarnings("deprecation")
public class UnlockedShortly extends DoorState implements Observer {
  private static final Clock clock = Clock.getInstance();
  private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone1.Milestone1Class");

  public UnlockedShortly(Door doorC) {
    super(doorC);
    clock.addObserver(this);
    clock.startTimer(10);
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
    } else {
      logger.info("Door is already closed!");
    }
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      logger.info("Door open.");
      door.setClosed(false);
    } else {
      logger.info("Door is already open!");
    }
  }

  // Update method (part of the Observer pattern)

  @SuppressWarnings("deprecation")
  @Override
  public void update(Observable o, Object arg) {
    if (!door.isClosed()) {
      clock.deleteObserver(this);
      door.setState(new Propped(door));
      logger.warn("Changed to propped");
    } else {
      clock.deleteObserver(this);
      door.setState(new Locked(door));
    }

    RequestRefresh requestRefresh = new RequestRefresh();
    requestRefresh.process();
  }
}