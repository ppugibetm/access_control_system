package baseNoStates;

import java.util.ArrayList;


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
    DirectoryDoors directoryDoors = DirectoryDoors.getInstance();
    directoryDoors.makeDoors();


    DirectoryAreas directoryAreas = DirectoryAreas.getInstance();
    directoryAreas.makeAreas(new ArrayList<>(directoryDoors.getAllDoors()));
    DirectoryUsers.getInstance().makeUsers();

    WebServer webServer = WebServer.getInstance();

  }
}
