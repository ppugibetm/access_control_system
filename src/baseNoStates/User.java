package baseNoStates;

public class User {
  private final String name;
  private final String credential;
  private final String group; //afegim aquesta variable per tal de facilitzar el codi

  public User(String name, String credential, String group) {
    this.name = name;
    this.credential = credential;
    this.group = group;
  }

  public String getCredential() {
    return credential;
  }

  public String getGroup() {
    return group;
  }


  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
