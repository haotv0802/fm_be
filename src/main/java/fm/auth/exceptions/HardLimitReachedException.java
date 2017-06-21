package fm.auth.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class HardLimitReachedException extends BadCredentialsException {
  public HardLimitReachedException(String msg) {
    super(msg);
  }
}
