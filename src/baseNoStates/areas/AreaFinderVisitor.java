package baseNoStates.areas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Visitor to find area by id.
public class AreaFinderVisitor implements AreaVisitor {
  private static final Logger logger = LoggerFactory.getLogger("baseNoStates.milestone2.Milestone2Class");
  private final String targetId;
  private Area foundArea;

  public AreaFinderVisitor(String targetId) {
    this.targetId = targetId;
  }

  @Override
  public void visitPartition(Partition partition) {
    logger.info("Visiting partition: {}", partition.getName());
    if (partition.getName().equals(targetId)) {
      foundArea = partition; // Match found, store the area
    } else {
      // Continue traversing child areas
      for (Area child : partition.getAreas()) {
        if (foundArea == null) { // Stop traversal if area is already found
          child.accept(this);
        }
      }
    }
  }

  @Override
  public void visitSpace(Space space) {
    logger.info("Visiting space: {}", space.getName());
    if (space.getName().equals(targetId)) {
      foundArea = space; // Match found, store the area.
      logger.info("Found target area in space: {}", space.getName());
    }
  }

  // Returns the Area found during the traversal, or null if not found.
  public Area getFoundArea() {
    if (foundArea == null) {
      logger.warn("Target area with ID '{}' not found.", targetId);
    }
    return foundArea;
  }
}
