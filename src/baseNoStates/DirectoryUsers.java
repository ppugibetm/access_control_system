package baseNoStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

public final class DirectoryUsers {

  private static DirectoryUsers instance; // Singleton instance
  private final ArrayList<User> users = new ArrayList<>();

  // Private constructor to prevent instantiation
  private DirectoryUsers() {}

  // Public method to provide access to the instance
  public static DirectoryUsers getInstance() {
    if (instance == null) {
      instance = new DirectoryUsers();
    }
    return instance;
  }

  public void makeUsers() {
    // User creation logic as before...
    users.add(new User("Bernat", "12345","blank", new ArrayList<>()));
    users.add(new User("Blai", "77532","blank", new ArrayList<>()));

    users.add(new User("Ernest", "74984","employee", new ArrayList<>()));
    users.add(new User("Eulalia", "43295","employee", new ArrayList<>()));

    users.add(new User("Manel", "95783", "manager", new ArrayList<>()));
    users.add(new User("Marta", "05827","manager", new ArrayList<>()));

    users.add(new User("Ana", "11343","admin", new ArrayList<>()));
  }

  public User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null;
  }
}
