package fm.auth;

public class CredentialsResult {
  /**
   * The user name
   */
  private String userName;

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

  public String getUserLang() {
    return userLang;
  }

  public void setUserLang(String userLang) {
    this.userLang = userLang;
  }
}
