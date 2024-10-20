package baseNoStates.areas;

import baseNoStates.Door;
import baseNoStates.doorStates.Locked;
import baseNoStates.doorStates.Unlocked;
import java.util.ArrayList;
import java.util.List;


public class Space extends Area {
    /*This class represents a physical space (like a room or corridor).
     It is a leaf component in the Composite pattern, as it cannot contain other areas.
     Space is part of the Composite structure, representing an individual space. Unlike
     partitions, it does not contain other areas, but it participates in the hierarchy
     as a leaf node.*/
    private final List<Door> doors;

    public Space(String name, Area parent) {
        super(name, parent);
        this.doors = new ArrayList<>();
    }

    // Add a door that provides access to this space
    public void addDoor(Door door) {
        doors.add(door);
    }

    // Get all doors that give access to this space
    @Override
    public List<Door> getDoorsGivingAccess() {
        return doors;
    }

    // Spaces cannot contain other areas, so this is not applicable
    @Override
    public void addArea(Area area) {
        throw new UnsupportedOperationException("A space cannot contain other areas.");
    }

    // Check if a space contains another area (not applicable for spaces)
    @Override
    public boolean containsArea(Area area) {
        return this.equals(area);
    }

    // Spaces cannot contain other areas, so the method getAreas is no applicable
    @Override
    public List<Area> getAreas() {
        throw new UnsupportedOperationException("A space cannot contain other areas.");
    }
}

