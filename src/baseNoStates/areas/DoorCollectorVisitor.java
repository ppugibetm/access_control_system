package baseNoStates.areas;

import baseNoStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// Visitor to get all doors in an area.
public class DoorCollectorVisitor implements AreaVisitor {
  private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone2.Milestone2Class");
  private final List<Door> doors = new ArrayList<>();

  @Override
  public void visitPartition(Partition partition) {
    // No action, doors are in spaces.
  }

  @Override
  public void visitSpace(Space space) {
    logger.info("Visiting space: {}", space.getName());
    List<Door> spaceDoors = space.getDoorsGivingAccess();
    doors.addAll(spaceDoors);
    logger.debug("Collected {} doors from space: {}", spaceDoors.size(), space.getName());
  }

  public List<Door> getDoors() {
    return doors;
  }
}
