package baseNoStates.doorStates;

import baseNoStates.Door;
import java.util.Observer;
import java.util.Observable;
import java.util.concurrent.locks.Lock;
import baseNoStates.Clock;
import baseNoStates.requests.RequestReader;
import baseNoStates.requests.RequestRefresh;

public class UnlockedShortly extends DoorState implements Observer {
    private static final Clock clock = new Clock();

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
        System.out.println("Door locked.");
        door.setState(new Locked(door));
    }

    @Override
    public void unlock() {

        System.out.println("Door is already unlocked!");
    }

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

    @Override
    public void open() {
        if (door.isClosed()) {
            System.out.println("Door open.");
            door.setClosed(false);
        }
        else {
            System.out.println("Door is already open!");
        }
    }

    // Update method (part of the Observer pattern)
    // It uploads the state of a door based on the notification
    // received from Clock Observable object.
    // This method is executed when the observable executes
    // the notifyObserver() method.
    @Override
    public void update(Observable o, Object arg) {
        if (!door.isClosed()) {
            door.setState(new Propped(door));
            System.out.println("Changed to propped");
        }
        else {
            door.setState(new Locked(door));
        }

        RequestRefresh requestRefresh = new RequestRefresh();
        requestRefresh.process();
    }
}