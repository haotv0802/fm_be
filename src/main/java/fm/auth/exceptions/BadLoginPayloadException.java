package fm.auth.exceptions;


import org.springframework.security.core.AuthenticationException;

public class BadLoginPayloadException extends AuthenticationException {

  public BadLoginPayloadException(String msg) {
    super(msg);
  }

}
