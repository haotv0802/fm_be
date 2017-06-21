package fm.auth;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class UserTokenDetails implements Serializable {

  private final String remoteAddress;

  private final String imxAddressStr;

  public UserTokenDetails(HttpServletRequest request) {

    String ipAddress = request.getHeader("X-FORWARDED-FOR");
    if (ipAddress == null) {
      ipAddress = request.getRemoteAddr();
    }
    remoteAddress = ipAddress;

    /*
    * TODO in the original init_maint the ip is actuuly modified to somethnig else
    * */
    imxAddressStr = "IMX_TCP_ADR_STR";
  }

  public String getRemoteAddress() {
    return remoteAddress;
  }

  public String getImxAddressStr() {
    return imxAddressStr;
  }

  @Override
  public String toString() {
    return "ImxUsernameTokenDetails{" +
        "remoteAddress='" + remoteAddress + '\'' +
        ", imxAddressStr='" + imxAddressStr + '\'' +
        '}';
  }
}

