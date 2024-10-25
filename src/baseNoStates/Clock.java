package baseNoStates;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;

public class Clock extends Observable {
    /*The Clock class will handle the ten seconds timer to
    lock after the UnlockShortly state.
    It is part of the Observer pattern.
     */
    private final Timer timer;

    public Clock() {
        timer = new Timer();
    }

    // Initialize the timer with a delay in seconds
    public void startTimer(int delay) {
        TimerTask task = new TimerTask() {
            public void run() {
                setChanged(); // Mark object as modified.
                notifyObservers(); // Notify the observers.
                System.out.println("Clock timer ended");
            }
        };
        timer.schedule(task, delay * 1000L);
    }
}



