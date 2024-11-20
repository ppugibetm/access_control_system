package baseNoStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;

public class Clock extends Observable {
    /*The Clock class will handle the ten seconds timer to
    lock after the UnlockShortly state.
    It is part of the Observer pattern.
     */

    private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone2.Milestone2Class");

    private final Timer timer;

    // Step 1: Create a private static instance
    private static Clock instance;

    // Step 2: Private constructor
    private Clock() {
        timer = new Timer();
    }

    // Step 3: Public method to access the single instance
    public static synchronized Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    // Initialize the timer with a delay in seconds
    public void startTimer(int delay) {
        TimerTask task = new TimerTask() {
            public void run() {
                setChanged(); // Mark object as modified.
                notifyObservers(); // Notify the observers.
                logger.info("Clock timer ended");
            }
        };
        timer.schedule(task, delay * 1000L);
    }
}
