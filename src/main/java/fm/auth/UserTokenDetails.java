package fm.auth;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class UserTokenDetails implements Serializable {

  private final String remoteAddress;

  private final String addressStr;

  public UserTokenDetails(HttpServletRequest request) {

    String ipAddress = request.getHeader("X-FORWARDED-FOR");
    if (ipAddress == null) {
      ipAddress = request.getRemoteAddr();
    }
    remoteAddress = ipAddress;

    /*
    * TODO in the original init_maint the ip is actuuly modified to somethnig else
    * */
    addressStr = "TCP_ADR_STR";
  }

  public String getRemoteAddress() {
    return remoteAddress;
  }

  public String getAddressStr() {
    return addressStr;
  }

  @Override
  public String toString() {
    return "usernameTokenDetails{" +
        "remoteAddress='" + remoteAddress + '\'' +
        ", addressStr='" + addressStr + '\'' +
        '}';
  }
}

