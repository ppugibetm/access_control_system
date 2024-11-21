package baseNoStates.areas;

import baseNoStates.Door;
import java.util.ArrayList;
import java.util.List;

// This class is the base class for all spaces and partitions in the system.
// It provides a common interface for managing access to spaces, either individual spaces
// or groups of spaces (partitions).
// The Area class is part of the Composite pattern, serving as the base class for both
// individual spaces and partitions. It allows the system to treat spaces and partitions
// uniformly, enabling hierarchical management of access permissions.
public abstract class Area {
  protected final String name;

  private final Area parent;


  public Area(String name, Area parent) {
    this.name = name;
    this.parent = parent;
    List<Area> children = new ArrayList<>();

    // If there is a parent, add this to its children.
    if (parent instanceof Partition) {
      parent.addArea(this);
    } else if (parent != null) {
      throw new IllegalArgumentException("An area can only have a partition as parent.");
    }
  }

  // Get parent
  public Area getParent() {
    return parent;
  }

  // Get the name of the area
  public String getName() {
    return name;
  }

  // Abstract method to get the doors giving access to this area
  public abstract List<Door> getDoorsGivingAccess();

  // Abstract method to add an area (used in partitions, not spaces)
  public abstract void addArea(Area area);

  // Abstract method to check if an area contains another area
  public abstract boolean containsArea(Area area);

  // Abstract method to get child areas (only in partitions)
  public abstract List<Area> getAreas();

  // Accept a visitor to perform operations in this area.
  public abstract void accept(AreaVisitor visitor);
}
