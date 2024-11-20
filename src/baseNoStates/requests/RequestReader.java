package baseNoStates.requests;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
import baseNoStates.DirectoryDoors;
import baseNoStates.DirectoryUsers;
import baseNoStates.Door;
import baseNoStates.User;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
        + "credential=" + credential
        + ", userName=" + userName
        + ", action=" + action
        + ", now=" + now
        + ", doorID=" + doorId
        + ", closed=" + doorClosed
        + ", authorized=" + authorized
        + ", reasons=" + reasons
        + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    DirectoryUsers directoryUsers = DirectoryUsers.getInstance();
    User user = directoryUsers.findUserByCredential(credential);
    DirectoryDoors directoryDoors = DirectoryDoors.getInstance();
    Door door = directoryDoors.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {

    if (user == null) {
      authorized = false;
      addReason("user doesn't exists");
    } else {


      LocalDate currentDate = now.toLocalDate();
      LocalTime currentTime = now.toLocalTime();
      DayOfWeek currentDay = now.getDayOfWeek();

      // Check user's group
      switch (user.getGroup()) {
        case "blank":
          authorized = false;
          addReason("no privileges for blank users");
          break;

        case "employee":
          // Employees: Sep. 1 this year to Mar. 1 next year, weekdays 9-17h, ground floor and first floor
          if (!isDateWithinRange(currentDate, LocalDate.of(currentDate.getYear(), 9, 1), LocalDate.of(currentDate.getYear() + 1, 3, 1))) {
            authorized = false;
            addReason("outside allowed date range for employees");
          } else if (!isWeekday(currentDay)) {
            authorized = false;
            addReason("outside allowed days for employees");
          } else if (!isTimeWithinRange(currentTime, 9, 17)) {
            authorized = false;
            addReason("outside allowed hours for employees");
          } else if (!isEmployeeDoor(door)) {
            authorized = false;
            addReason("employees are not allowed in this area");
          } else {
            authorized = true;
          }
          break;

        case "manager":
          // Managers: Sep. 1 this year to Mar. 1 next year, weekdays + Saturday, 8-20h, all spaces
          if (!isDateWithinRange(currentDate, LocalDate.of(currentDate.getYear(), 9, 1), LocalDate.of(currentDate.getYear() + 1, 3, 1))) {
            authorized = false;
            addReason("outside allowed date range for managers");
          } else if (!isWeekdayOrSaturday(currentDay)) {
            authorized = false;
            addReason("outside allowed days for managers");
          } else if (!isTimeWithinRange(currentTime, 8, 20)) {
            authorized = false;
            addReason("outside allowed hours for managers");
          } else {
            authorized = true;
          }
          break;

        case "admin":
          // Admin: tots els privilegis i permissos on sigui i quan sigui
          authorized = true;
          break;

        default:
          authorized = false;
          addReason("unknown group");
          break;
      }
    }
  }

  // Mètode d'ajuda per comprovar si l'hora actual es troba dins de l'interval permès
  private boolean isDateWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
    return !date.isBefore(startDate) && !date.isAfter(endDate);
  }

  // Helper method to check if the current time is within the allowed range
  private boolean isTimeWithinRange(LocalTime time, int startHour, int endHour) {
    return !time.isBefore(LocalTime.of(startHour, 0)) && !time.isAfter(LocalTime.of(endHour, 0));
  }

  // Mètode d'ajuda per comprovar si el dia és laborable (de dilluns a divendres)
  private boolean isWeekday(DayOfWeek day) {
    return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
  }

  // Mètode auxiliar per comprovar si el dia és laborable o dissabte
  private boolean isWeekdayOrSaturday(DayOfWeek day) {
    return day != DayOfWeek.SUNDAY;
  }


  // "Employees" tenen acces a totes les portes menys D1 i D2
  private boolean isEmployeeDoor(Door door) {

    return "D1" != door.getId() && "D2" != door.getId();
  }

}

