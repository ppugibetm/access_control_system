package baseNoStates;

import baseNoStates.areas.Area;

import java.util.List;
import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;
  private final String group; //afegim aquesta variable per tal de facilitzar el codi
  private final List<Area> areas; // Accessible areas for the user

  public User(String name, String credential, String group, List<Area> areas) {
    this.name = name;
    this.credential = credential;
    this.group = group;
    this.areas = areas;
  }

  public String getCredential() {
    return credential;
  }

  public String getGroup() {
    return group;
  }

  // Add a new accessible area for the user
  public void addAccessArea(Area area) {
    areas.add(area);
  }

  // Check if the user has access to a certain area
  public boolean hasAcces(Area area) {
    return areas.contains(area);
  }

  // Get the list of areas the user has access to
  public List<Area> getAccessAreas() {
    return areas;
  }


  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
