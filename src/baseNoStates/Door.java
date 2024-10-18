package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;
import baseNoStates.doorStates.DoorState;
import baseNoStates.doorStates.Locked;


public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState state;

  public Door(String id) {
    this.id = id;
    this.closed = true;
    this.state = new Locked(this);
  }

  // Method to handle the change of the door state
  public void setState(DoorState stateTo) {
    state = stateTo;
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
//        if (closed) {
//          closed = false;
//        } else {
//          System.out.println("Can't open door " + id + " because it's already open");
//        }
        state.open();
        break;
      case Actions.CLOSE:
//        if (closed) {
//          System.out.println("Can't close door " + id + " because it's already closed");
//        } else {
//          closed = true;
//        }
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        // TODO
        System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  // Method to pass to the processRequest the name of the current state of the door
  public String getStateName() {
    return state.getName();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }

  // Method to set the door to open/closed
  public void setClosed(boolean c) {
    closed = c;
  }
}
