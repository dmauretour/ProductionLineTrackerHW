package sample;

/** @author Dory Mauretour. Contains details about the employee and procedures for each object. */
class Employee {

  private final StringBuilder name;
  private String username;
  private String password;
  private String email;

  /**
   * The setEmail method has one parameter and sets the email.
   *
   * @param name receives a string builder value as an argument.
   */
  private void setEmail(StringBuilder name) {
    String[] splitArray = name.toString().split(" ");
    if (splitArray.length > 1) {
      this.email = (splitArray[0] + "." + splitArray[1]).toLowerCase() + "@oracleacademy.Test";
    } else {
      this.email = (splitArray[0]).toLowerCase() + "@oracleacademy.Test";
    }
  }
  /**
   * Setusername method has one parameter and sets the username.
   *
   * @param name receives a string builder value as an argument.
   */
  private void setUsername(StringBuilder name) {
    String[] splitArray = name.toString().split(" ");
    if (splitArray.length > 1) {
      this.username = (splitArray[0].substring(0, 1) + splitArray[1]).toLowerCase();
    } else {
      this.username = (splitArray[0]).toLowerCase();
    }
  }
  /**
   * CheckName method splits the string value parameter.
   *
   * @param name receives a string builder value as an argument.
   * @return returns a boolean value.
   */
  private boolean checkName(StringBuilder name) {
    String[] splitArray = name.toString().split(" ");

    return splitArray.length == 1 ? false : true;
  }
  /**
   * isValidPassword method has one parameter and checks if password entered is valid.
   *
   * @param password receives a string value as an argument.
   * @return returns a boolean value.
   */
  private boolean isValidPassword(String password) {

    if (!password.matches(".*[A-Z].*")) return false;

    if (!password.matches(".*[a-z].*")) return false;

    if (!password.matches(".*[~!.......].*")) return false;

    return true;
  }
  /**
   * Employee class constructor with two parameters.
   *
   * @param name receives a string value as an argument.
   * @param password receives a string value as an argument.
   */
  public Employee(String name, String password) {
    StringBuilder sName = new StringBuilder(name);
    StringBuilder defaultUsername = new StringBuilder("default");
    StringBuilder defaultEmail = new StringBuilder("user");
    this.name = sName;
    this.password = password;

    if (checkName(sName)) {
      setUsername(sName);
      setEmail(sName);
    } else {
      setUsername(defaultUsername);
      setEmail(defaultEmail);
    }

    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }
  /**
   * getName method of string builder type.
   *
   * @return returns a string builder value.
   */
  public StringBuilder getName() {
    return name;
  }

  /**
   * getPassword method of String type.
   *
   * @return returns a string value.
   */
  public String getPassword() {
    return password;
  }

  /**
   * getUsername method of string type.
   *
   * @return returns a string value.
   */
  public String getUsername() {
    return username;
  }

  /**
   * getEmail method of string type.
   *
   * @return returns a string value.
   */
  public String getEmail() {
    return email;
  }
  /**
   * Class toString method outputs the data as set in the string formatter.
   *
   * @return returns a string value.
   */
  @Override
  public String toString() {
    String str1 =
        String.format(
            "Employee Details\nName : %s\nUsername : %s\nEmail : %s\nInitial Password : %s",
            this.name, this.username, this.email, this.password);
    return str1;
  }
}
