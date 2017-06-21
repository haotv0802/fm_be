package fm.auth;

public class Credentials {
  /**
   * The user name
   */
  private String userName;

  /**
   * The user password
   */
  private String userPass;

  /**
   * User language
   */
  private String userLang;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }

  public String getUserLang() {
    return userLang;
  }

  public void setUserLang(String userLang) {
    this.userLang = userLang;
  }
}
