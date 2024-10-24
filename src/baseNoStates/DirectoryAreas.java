package baseNoStates;

import baseNoStates.areas.Area;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;

import java.util.ArrayList;
import java.util.List;


public class DirectoryAreas {


    // Root area.
    private static Area root;

    private static List<Door> allDoors = new ArrayList<>();
    private static List<Area> allAreas = new ArrayList<>();

    public static void makeAreas(ArrayList<Door> doors) {
        allDoors = doors;

        root = new Partition("building", null);
        allAreas.add(root);

        // Basement
        Partition basement = new Partition("basement", root);
        allAreas.add(basement);

        Space parking = new Space("parking", basement);
        basement.addArea(parking);
        allAreas.add(parking);
        parking.addDoor(allDoors.get(0));
        parking.addDoor(allDoors.get(1));

        // Ground floor
        Partition groundFloor = new Partition("ground_floor", root);
        allAreas.add(groundFloor);

        Space hall = new Space("hall", groundFloor);
        groundFloor.addArea(hall);
        allAreas.add(hall);
        hall.addDoor(allDoors.get(2));
        hall.addDoor(allDoors.get(3));

        Space room1 = new Space("room1", groundFloor);
        groundFloor.addArea(room1);
        allAreas.add(room1);
        room1.addDoor(allDoors.get(4));

        Space room2 = new Space("room2", groundFloor);
        groundFloor.addArea(room2);
        allAreas.add(room2);
        room2.addDoor(allDoors.get(5));

        // Floor 1
        Partition floor1 = new Partition("floor1", root);
        allAreas.add(floor1);

        Space room3 = new Space("room3", floor1);
        floor1.addArea(room3);
        allAreas.add(room3);
        room3.addDoor(allDoors.get(7));

        Space corridor = new Space("corridor", floor1);
        floor1.addArea(corridor);
        allAreas.add(corridor);
        corridor.addDoor(allDoors.get(6));

        Space it = new Space("it", floor1);
        floor1.addArea(it);
        allAreas.add(it);
        it.addDoor(allDoors.get(8));

        // Stairs
        Partition stairs = new Partition("stairs", root);
        allAreas.add(stairs);

        // Exterior
        Partition exterior = new Partition("exterior", root);
        allAreas.add(exterior);
    }

    public static Area findAreaById(String id) {
        if (id.equals(root.getName())) { return root; }

        for (Area area : allAreas) {
            if (id.equals(area.getName())) { return area; }
        }

        return null;
    }

    public static Door findDoorById(String id) {
        for (Door door : allDoors) {
            if (id.equals(door.getId())) { return door; }
        }

        return null;
    }

    public static List<Door> getAllDoors() {
        return allDoors;
    }
}
