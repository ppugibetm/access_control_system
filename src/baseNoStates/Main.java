package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void wait(int seconds) {
    try {
      Thread.sleep(1000*seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    DirectoryDoors.makeDoors();
    DirectoryAreas.makeAreas(DirectoryDoors.getAllDoors());
    DirectoryUsers.makeUsers();
    new WebServer();
  }
}
