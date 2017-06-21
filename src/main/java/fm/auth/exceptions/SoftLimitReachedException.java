package fm.auth.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class SoftLimitReachedException extends BadCredentialsException {
  public SoftLimitReachedException(String msg) {
    super(msg);
  }
}
