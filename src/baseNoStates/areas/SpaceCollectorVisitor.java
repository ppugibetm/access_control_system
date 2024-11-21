package baseNoStates.areas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// Visitor to collect all Space instances under a given Area.
public class SpaceCollectorVisitor implements AreaVisitor {
  private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone2.Milestone2Class");
  private final List<Space> spaces = new ArrayList<>(); // List to store collected spaces

  // Visits a Partition and continues traversing its child Areas.
  @Override
  public void visitPartition(Partition partition) {
    logger.info("Visiting partition: {}", partition.getName());
    // Traverse all child areas within the partition
    for (Area child : partition.getAreas()) {
      child.accept(this);
    }
  }

  // Visits a Space and adds it to the list of collected spaces.
  @Override
  public void visitSpace(Space space) {
    spaces.add(space); // Collect the Space
    logger.debug("Space added to the list: {}", space.getName());
  }

  //Returns the list of all collected Space instances.
  public List<Space> getCollectedSpaces() {
    logger.info("Collected {} spaces.", spaces.size());
    return spaces;
  }
}
