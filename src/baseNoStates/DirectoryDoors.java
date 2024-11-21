package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DirectoryDoors {
  private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone1.Milestone1Class");

  private static DirectoryDoors instance; // Singleton instance
  private ArrayList<Door> allDoors;

  // Private constructor to prevent instantiation
  private DirectoryDoors() {}

  // Public method to provide access to the instance
  public static DirectoryDoors getInstance() {
    if (instance == null) {
      instance = new DirectoryDoors();
    }
    return instance;
  }

  public void makeDoors() {
    // Door creation logic as before...
    Door d1 = new Door("D1");
    Door d2 = new Door("D2");
    Door d3 = new Door("D3");
    Door d4 = new Door("D4");
    Door d5 = new Door("D5");
    Door d6 = new Door("D6");
    Door d7 = new Door("D7");
    Door d8 = new Door("D8");
    Door d9 = new Door("D9");

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  public Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }

    logger.debug("door with id " + id + " not foundUWU");
    return null;
  }

  public ArrayList<Door> getAllDoors() {
    return allDoors;
  }
}
