package baseNoStates.areas;

import baseNoStates.areas.Area;
import baseNoStates.Door;
import baseNoStates.doorStates.Locked;
import baseNoStates.doorStates.Unlocked;

import java.util.ArrayList;
import java.util.List;


public class Partition extends Area {
    /*This class represents a group of spaces or other partitions.
     It is a composite component in the Composite pattern, as it can contain other areas.
     Partition is the composite part of the Composite pattern, as it can contain other
     spaces or partitions. This allows hierarchical grouping of spaces, making it easier
     to manage access permissions on multiple levels (floor, department, building).*/
    private final List<Area> areas;

    public Partition(String name, Area parent) {
        super(name, parent);
        this.areas = new ArrayList<>();
    }

    // Add a space or partition to this partition
    @Override
    public void addArea(Area area) {
        areas.add(area);
    }

    // Get all doors that give access to any space in this partition
    @Override
    public List<Door> getDoorsGivingAccess() {
        List<Door> doors = new ArrayList<>();
        for (Area area : areas) {
            doors.addAll(area.getDoorsGivingAccess());
        }
        return doors;
    }

    // Check if this partition contains a specific area
    @Override
    public boolean containsArea(Area area) {
        if (areas.contains(area)) {
            return true;
        }
        for (Area a : areas) {
            if (a.containsArea(area)) {
                return true;
            }
        }
        return false;
    }

    // Get all areas (spaces and partitions) within this partition
    @Override
    public List<Area> getAreas() {
        return areas;
    }
}

